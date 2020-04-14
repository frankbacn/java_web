package java_web.cookie;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_web.bean.ProductsDAO;

@WebServlet("/CookieDemo2")
public class CookieDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Cookie[] cookies = request.getCookies(); //获取用户cookie
		ProductsDAO dao = new ProductsDAO(this.getServletContext().getRealPath("/WEB-INF/products.xml"));
		//web页面第一部分，产品列表
		response.getWriter().append("<html>");
		response.getWriter().append("<div><div><b>商品信息</b></br>");
		//查询产品数据罗列产品列表
		for( Map.Entry<String,String> ent:dao.getAllProducts().entrySet()){
			response.getWriter().append("<a href='/java_web/CookieDemo3?id="+ent.getKey()+"'>"+ent.getValue()+"</a></br>");
		}
		//web页面第二部分，浏览产品目录
		response.getWriter().append("</div><div><b>曾经浏览过的商品</b></br>");
		//根据用户cookie情况输出浏览产品清单
		if(cookies!=null){ //用户向服务器发送了cookie
			String pvalues="";
			//获取有效cookie中的内容
			for(int i = 0;i<cookies.length;i++){
				if("productid".equals(cookies[i].getName())){
					pvalues = cookies[i].getValue();
				}
			}
			//根据cookie中的数据展示前10个浏览过的产品的信息
			String[] vistedProducts = pvalues.split(",");
			for(int i = 0;i<vistedProducts.length;i++){
				if(i>10){
					break;
				}
				response.getWriter().append("<a href='/java_web/CookieDemo3?id="+vistedProducts[i]+"'>"+dao.getProductName(vistedProducts[i])+"</a></br>");
			}
			
		}else{ //无cookie
			response.getWriter().append("您还没有浏览过任何商品，快去看看吧！");
		}
		response.getWriter().append("</div>");
		response.getWriter().append("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
