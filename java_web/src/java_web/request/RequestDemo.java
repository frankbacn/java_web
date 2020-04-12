package java_web.request;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestDemo
 */
@WebServlet("/RequestDemo")
public class RequestDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
//		getHTTPHeader(request, response);
//		getHttpReqHeader(request, response);
		writeLine(response, "��ȡ������ȫ�������ַ���Ϣ");
		writeLine(response, "QueryString="+request.getQueryString());
		writeLine(response, "");
		writeLine(response, "�ֱ��ȡ�������еĲ�����ֵ");
//		getAllParameters(request, response);
		getAllParameters2(request, response);
	}

	private void getAllParameters2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		for(Map.Entry<String,String[]> es :request.getParameterMap().entrySet()){
			String name = es.getKey();
			String[] values = es.getValue();
			for(String value:values){
				writeLine(response, name+"="+value);
			}
		}
	}

	private void getAllParameters(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�Ȼ�ȡ���в��������ٻ�ȡ������ֵ
		for(Enumeration<String> names = request.getParameterNames();names.hasMoreElements();){
			String name = names.nextElement();
			writeLine(response, name+"="+request.getParameter(name));
		}
	}

	private void getHttpReqHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
		writeLine(response, "�����ǿͻ��˼������������Ϣ��");
		writeLine(response, "RemoteAddr="+request.getRemoteAddr());
		writeLine(response, "RemoteHost="+request.getRemoteHost());
		writeLine(response, "RemotePort="+request.getRemotePort());
		writeLine(response, "LocalAddr="+request.getLocalAddr());
		writeLine(response, "");
		writeLine(response, "������HTTP����ͷ���ݣ�");
		//��ȡHTTP����ͷ������
		for(Enumeration<String> names = request.getHeaderNames();names.hasMoreElements();){
			String name = names.nextElement();
			writeLine(response, name+"="+request.getHeader(name));
		}
	}

	private void getHTTPHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().append("��ȡhttp����ͷ�����ݣ�</br>");
		response.getWriter().append(request.getRequestURI()+"</br>");
		response.getWriter().append(request.getRequestURL()+"</br>");
		writeLine(response, request.getMethod());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void writeLine(HttpServletResponse response,String str) throws IOException{
		response.getWriter().append(str+"</br>");
	}

}
