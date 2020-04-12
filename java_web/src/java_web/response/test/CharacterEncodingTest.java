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
	//中文文件名下载
	public void downLoad(HttpServletResponse response) throws FileNotFoundException, IOException {
		//1.获取下载文件路径及文件名
		String path = this.getServletContext().getRealPath("/GRUB命令行编辑linux启动.txt");
		String filename = path.substring(path.lastIndexOf('\\')+1);
		//2.读取并写出到http响应对象
		File df = new File(path);
		FileInputStream fi = new FileInputStream(df);
		byte[] buff = new byte[1024];
		int len = 0;
		while((len = fi.read(buff))!=-1){
			response.getOutputStream().write(buff,0,len);
		}
		//3.通知浏览器下载数据的大小
		response.setHeader("Pragma", "No-cache"); 
		response.setHeader("Cache-Control", "No-cache"); 
		response.setDateHeader("Expires", 0);
		//response.setHeader("content-type", this.getServletContext().getMimeType(filename));
		response.setHeader("Content-Length",df.length()+"");
		
		//4.通知浏览器传输的文件类型,并将下载文件名使用url编码
		response.addHeader("Content-Disposition", "attachment");
		//5.关流
		fi.close();
	}
	/**
	 * 使用字符流向浏览器输出中文信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void writeWithWriter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("content-type", "text/html; charset=UTF-8");
		//设置HTTP响应头字符编码信息
		response.setCharacterEncoding("UTF-8");
		//设置http响应对象的字符编码
		response.getWriter().append("显式中文").append(request.getContextPath());
	}
	/**
	 * 使用字节流向浏览器输出中文信息
	 * @param response
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public void writeWithOutputStream(HttpServletResponse response) throws IOException, UnsupportedEncodingException {
		//response.getOutputStream().write("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>".getBytes());
		//使用mate标签向浏览器传送页面信息
//		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("content-type", "text/html; charset=UTF-8");
		//使用setHeader或setContentType向浏览器发送HTTP响应头信息
		response.getOutputStream().write("显式中文".getBytes("UTF-8"));
		//使用getOutputStream方法向流中写入中文字符字节数据，采用指定码表
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
