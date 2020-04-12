package java_web.servletcontext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_web.classloaderdemo.DaoDemo;

/**
 * Servlet implementation class ServletContextDemo
 */
@WebServlet("/ServletContextDemo")
public class ServletContextDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//demo1(request, response);
		//demo2(request, response);
		//resourceDemo1(response);
		//resourceDemo2(response);
		//resourceDemo3(response);
		//resourceDemo4(response);
		//resourceDemo5();
		//contextNameUse(response);
		//getInitParams(response);
		this.getServletContext().setAttribute("source", request.getRemoteAddr());
	}
	/**
	 * ʹ��getInitParameterNames��getInitParameter������ȡwebӦ�������õĳ�ʼ������
	 * @param response
	 * @throws IOException
	 */
	public void getInitParams(HttpServletResponse response) throws IOException {
		Enumeration<String> en = this.getServletContext().getInitParameterNames();
		while(en.hasMoreElements()){
			String name = en.nextElement();
			String value = this.getServletContext().getInitParameter(name);
			writeLine(response, name+"="+value);
		}
	}
	/**
	 * getServletContextName����webӦ�õ�����
	 * ���������������web��xml�ļ��е�display-name��ǩ�е�ֵ
	 * �˷����û���̬�����û�����URL·���еĲ���·��
	 * @param response
	 * @throws IOException
	 */
	public void contextNameUse(HttpServletResponse response) throws IOException {
		writeLine(response, this.getServletContext().getServletContextName());
		writeLine(response, "<a href='/"+this.getServletContext().getServletContextName()+"/NextDemo'>gotonext</a>");
		//ʹ��getServletContextName������̬��ȡwebӦ�����Ʋ���������URL
	}
	/**
	 * ʹ���ⲿ���ȡ��Դ�ļ�
	 * @throws IOException
	 */
	public void resourceDemo5() throws IOException {
		new DaoDemo().loadProperties2();
	}
	/**
	 * ��ȡWEB-INFĿ¼�µ������ļ�
	 * @param response
	 * @throws IOException
	 */
	public void resourceDemo4(HttpServletResponse response) throws IOException {
		InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/test.properties");
		//ʹ��getResourceAsStream��ȡһ�������ļ���������
		readConf(response, in);
	}
	/**
	 * �����ֻ�ȡ�����ļ��ķ�ʽ
	 * ʹ��getResource��ʽ��ȡĳ������Ŀ¼��url
	 * ��ͨ��url��ȡ����ȡ����ȡ�����ļ�
	 * @param response
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void resourceDemo3(HttpServletResponse response) throws MalformedURLException, IOException {
		URL url = this.getServletContext().getResource("/test.properties");
		writeLine(response, "url="+url.toString());
		readConf(response, url.openStream());
	}
	/**
	 * �ڶ��ֻ�ȡ�����ļ��ķ�ʽ
	 * ʹ��getRealPath�Ȼ�ȡ��Դ��ʵ��·���ı�����ͨ�������װΪ��������ж�ȡ
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void resourceDemo2(HttpServletResponse response) throws FileNotFoundException, IOException {
		String path = this.getServletContext().getRealPath("/test.properties");
		response.getWriter().append("filepath="+path+"</br>");
		FileInputStream in = new FileInputStream(path);
		readConf(response, in);
	}
	/**
	 * ��һ�ֻ�ȡ�����ļ��ķ�ʽ
	 * ʹ��getResourceAsStream����ֱ�ӻ�ȡ��Դ�Ķ�ȡ��
	 * @param response
	 * @throws IOException
	 */
	public void resourceDemo1(HttpServletResponse response) throws IOException {
		InputStream in = this.getServletContext().getResourceAsStream("test.properties");
		//ʹ��getResourceAsStream��ȡһ�������ļ���������
		readConf(response, in);
	}
	/**
	 * ��ȡproperties�����ļ�
	 * @param response
	 * @param in
	 * @throws IOException
	 */
	public void readConf(HttpServletResponse response, InputStream in) throws IOException {
		Properties prop = new Properties();
		prop.load(in);
		Set<String> keys = prop.stringPropertyNames();
		for(String key:keys){
			String value = prop.getProperty(key);
			response.getWriter().append(key+"="+value+"</br>");
		}
		in.close();
	}
	/**
	 * ��webҳ�����һ���ı�
	 * @param response
	 * @param str
	 * @throws IOException
	 */
	public void writeLine(HttpServletResponse response,String str) throws IOException{
		response.getWriter().append(str+"</br>");
	}
	public void demo2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RequestDispatcher rd = this.getServletContext().getNamedDispatcher("java_web.servletcontext.NextDemo");
		//ʹ��getNamedDispatcher����ת������
		//getNamedDispatcher�����Ĳ���������һ��servlet������
		//��ʹ��@WebServletע��ʱ����δָ��name������Servlet����Ϊȫ�޶���
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/NextDemo");
		//ʹ��getRequestDispatcher��ȡ�������������
		//getRequestDispatcher�����Ĳ���������һ������·��RUL(��/��ʼ)
		rd.forward(request, response);
	}
	//��ȡContext������Ϣ
	public void demo1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletContext context = this.getServletContext();
		Enumeration<String> attrs = context.getAttributeNames();
		if(!attrs.hasMoreElements())
			response.getWriter().append("��������Ϣ</br>");
		while(attrs.hasMoreElements()){
			String name = attrs.nextElement();
			response.getWriter().append(name+"="+"</br>");
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
