package java_web.cookie;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieDemo1")
public class CookieDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		lastAccess(request, response);
	}

	private void lastAccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies;
		//根据客户端浏览器所带的Cookie显示上次访问时间
		if((cookies = request.getCookies())!=null) {//判断客户端是否传送了Cookie
			response.getWriter().append("上次访问时间是：");
			for(int i = 0;i<cookies.length;i++) {//循环获取所需的Cookie数据
				Cookie c = cookies[i];
				if("accesstime".equals(c.getName())) {//如果Cookie的名称是访问时间，则取出病将其中的值在页面中显示
					response.getWriter().append(new Date(Long.valueOf(c.getValue())).toLocaleString());
				}
			}
		}else { //如果时第一次访问则显示为首次访问，并向客户端发送Cookie
			response.getWriter().append("第一次访问");
		}
		response.addCookie(new Cookie("accesstime",System.currentTimeMillis()+""));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
