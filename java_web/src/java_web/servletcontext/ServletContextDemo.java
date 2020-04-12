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
	 * 使用getInitParameterNames和getInitParameter方法获取web应用中配置的初始化参数
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
	 * getServletContextName返回web应用的名称
	 * 这个名称是配置在web。xml文件中的display-name标签中的值
	 * 此方法用户动态的向用户返回URL路径中的部分路径
	 * @param response
	 * @throws IOException
	 */
	public void contextNameUse(HttpServletResponse response) throws IOException {
		writeLine(response, this.getServletContext().getServletContextName());
		writeLine(response, "<a href='/"+this.getServletContext().getServletContextName()+"/NextDemo'>gotonext</a>");
		//使用getServletContextName方法动态获取web应用名称并用于生成URL
	}
	/**
	 * 使用外部类读取资源文件
	 * @throws IOException
	 */
	public void resourceDemo5() throws IOException {
		new DaoDemo().loadProperties2();
	}
	/**
	 * 读取WEB-INF目录下的配置文件
	 * @param response
	 * @throws IOException
	 */
	public void resourceDemo4(HttpServletResponse response) throws IOException {
		InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/test.properties");
		//使用getResourceAsStream获取一个配置文件的流对象
		readConf(response, in);
	}
	/**
	 * 第三种获取配置文件的方式
	 * 使用getResource方式获取某个虚拟目录的url
	 * 再通过url获取到读取流读取配置文件
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
	 * 第二种获取配置文件的方式
	 * 使用getRealPath先获取资源的实际路径文本，再通过将其封装为流对象进行读取
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
	 * 第一种获取配置文件的方式
	 * 使用getResourceAsStream方法直接获取资源的读取流
	 * @param response
	 * @throws IOException
	 */
	public void resourceDemo1(HttpServletResponse response) throws IOException {
		InputStream in = this.getServletContext().getResourceAsStream("test.properties");
		//使用getResourceAsStream获取一个配置文件的流对象
		readConf(response, in);
	}
	/**
	 * 读取properties配置文件
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
	 * 在web页面输出一行文本
	 * @param response
	 * @param str
	 * @throws IOException
	 */
	public void writeLine(HttpServletResponse response,String str) throws IOException{
		response.getWriter().append(str+"</br>");
	}
	public void demo2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RequestDispatcher rd = this.getServletContext().getNamedDispatcher("java_web.servletcontext.NextDemo");
		//使用getNamedDispatcher方法转发请求
		//getNamedDispatcher方法的参数必须是一个servlet的名字
		//当使用@WebServlet注解时，如未指定name属性则Servlet名称为全限定名
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/NextDemo");
		//使用getRequestDispatcher获取请求调度器对象
		//getRequestDispatcher方法的参数必须是一个绝对路径RUL(以/开始)
		rd.forward(request, response);
	}
	//获取Context属性信息
	public void demo1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletContext context = this.getServletContext();
		Enumeration<String> attrs = context.getAttributeNames();
		if(!attrs.hasMoreElements())
			response.getWriter().append("无属性信息</br>");
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
