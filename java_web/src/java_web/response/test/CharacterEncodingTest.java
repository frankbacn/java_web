package java_web.response.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_web.bean.ImageBuder;

/**
 * Servlet implementation class CharacterEncodingTest
 */
@WebServlet("/CharacterEncodingTest")
public class CharacterEncodingTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		randomCheckImage(response);
	}
	private void randomCheckImage(HttpServletResponse response) throws IOException {
		ImageBuder imb = new ImageBuder(120, 20);
//		imb.drawENString(4);
//		imb.drawNum(4);
		imb.drawCNString(4);
		ImageIO.write(imb.getImage(), "jpeg", response.getOutputStream());
	}
	//�����ļ�������
	public void downLoad(HttpServletResponse response) throws FileNotFoundException, IOException {
		//1.��ȡ�����ļ�·�����ļ���
		String path = this.getServletContext().getRealPath("/GRUB�����б༭linux����.txt");
		String filename = path.substring(path.lastIndexOf('\\')+1);
		//2.��ȡ��д����http��Ӧ����
		File df = new File(path);
		FileInputStream fi = new FileInputStream(df);
		byte[] buff = new byte[1024];
		int len = 0;
		while((len = fi.read(buff))!=-1){
			response.getOutputStream().write(buff,0,len);
		}
		//3.֪ͨ������������ݵĴ�С
		response.setHeader("Pragma", "No-cache"); 
		response.setHeader("Cache-Control", "No-cache"); 
		response.setDateHeader("Expires", 0);
		//response.setHeader("content-type", this.getServletContext().getMimeType(filename));
		response.setHeader("Content-Length",df.length()+"");
		
		//4.֪ͨ�����������ļ�����,���������ļ���ʹ��url����
		response.addHeader("Content-Disposition", "attachment");
		//5.����
		fi.close();
	}
	/**
	 * ʹ���ַ�������������������Ϣ
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void writeWithWriter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("content-type", "text/html; charset=UTF-8");
		//����HTTP��Ӧͷ�ַ�������Ϣ
		response.setCharacterEncoding("UTF-8");
		//����http��Ӧ������ַ�����
		response.getWriter().append("��ʽ����").append(request.getContextPath());
	}
	/**
	 * ʹ���ֽ�������������������Ϣ
	 * @param response
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public void writeWithOutputStream(HttpServletResponse response) throws IOException, UnsupportedEncodingException {
		//response.getOutputStream().write("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>".getBytes());
		//ʹ��mate��ǩ�����������ҳ����Ϣ
//		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("content-type", "text/html; charset=UTF-8");
		//ʹ��setHeader��setContentType�����������HTTP��Ӧͷ��Ϣ
		response.getOutputStream().write("��ʽ����".getBytes("UTF-8"));
		//ʹ��getOutputStream����������д�������ַ��ֽ����ݣ�����ָ�����
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
