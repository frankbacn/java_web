package java_web.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestDemo2
 */
@WebServlet("/RequestDemo2")
public class RequestDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//userLinkCheck(request, response);
		//getPostZHCN(request);
		getMethodZHCN(request);
		//request.getRequestDispatcher("/java_web/").forward(request, response);
	}
	/**
	 * 通过get方式获取用户提交非英文数据
	 * 手动对非英文字符进行编码解码
	 * 注：通过超链接方式向服务器提交数据时如果数据内容包含中文则需要对中文进行url编码
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void getMethodZHCN(HttpServletRequest request) throws UnsupportedEncodingException {
		//手工处理get方式提交的中文数据,先获取用户提交文本信息的二进制数据，再指定字符集对数据进行解吗
		System.out.println(new String(request.getParameter("username").getBytes("ISO8859-1"),"UTF-8"));
	}
	/**
	 * 获取中文用户提交信息
	 * POST方式获取
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void getPostZHCN(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println(request.getParameter("username"));
	}
	/**
	 * 防盗链代码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void userLinkCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.getHeader("referer")==null || !request.getHeader("referer").startsWith("http://localhost:8080")){
			//this.getServletContext().getRequestDispatcher("/").forward(request, response); //转发请求
			//使用重定向方式将客户端请求直接转至首页
			response.setStatus(302);
			response.sendRedirect("/java_web");
			return;
		}
		
		response.getWriter().append("正常访问web页面");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
