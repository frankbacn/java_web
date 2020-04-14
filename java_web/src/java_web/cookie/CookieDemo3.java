package java_web.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_web.bean.ProductsDAO;

/**
 * Servlet implementation class CookieDemo3
 */
@WebServlet("/CookieDemo3")
public class CookieDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductsDAO dao = new ProductsDAO(this.getServletContext().getRealPath("/WEB-INF/products.xml"));
		String productID = request.getParameter("id");
		String productName = dao.getProductName(productID);
		
		Cookie[] cookies = request.getCookies(); //获取用户cookie信息
		if(cookies==null){ //如果cookie无内容
			if(!productName.equals("")) //如果直接访问产品页面且存在产品信息则为用户创建cookie
				response.addCookie(new Cookie("productid",productID+","));
			else{ //如果访问页面信息无效则直接重定向去首页
				response.sendRedirect("/java_web/CookieDemo2");
				return;
			}				
		}else{ //cookie有内容
			//获取cookie对应内容
			String pvalues="";
			for(int i = 0;i<cookies.length;i++){
				if("productid".equals(cookies[i].getName())){
					pvalues = cookies[i].getValue();
				}
			}
			//根据所浏览的产品修改cookie的顺序
			if(pvalues.indexOf(productID)>=0){
				pvalues = pvalues.replace(productID+",", "");
			}
			pvalues = productID+","+pvalues;
			response.addCookie(new Cookie("productid",pvalues));
		}
		
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		response.getWriter().append("<html>");
		response.getWriter().append("您浏览的商品是</br>");
		response.getWriter().append("<b>"+productName+"</b></br>");
		response.getWriter().append("<a href='/java_web/CookieDemo2'>首页</a>");
		response.getWriter().append("</html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
