package java_web.classloaderdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class DaoDemo {
	/**
	 * ��ȡ�����ļ�
	 * �����ļ�λ��λ��classesĿ¼��
	 * ʹ����װ����getClassLoader��ȡ��Դ�ļ�
	 * ��װ�����е�getResourceAsStream������ȡ������Դ��������������Դ����ʱͬʱ����
	 * @throws IOException
	 */
	public void loadProperties() throws IOException {
		/*
		 * ʹ����װ��������ȡ�����ļ�Ӧ��ע�����ļ��Ĵ�С
		 * ��Ϊ��װ������һ���Խ��ļ����ؽ��ڴ棬����������ڴ�����ķ���
		 */
		InputStream in = DaoDemo.class.getClassLoader().getResourceAsStream("test1.properties");
		//ͨ�����ļ��е�getClassLoader������ȡ��װ��������ͨ����װ���������ȡclassesĿ¼�е���Դ�ļ�
		if (in != null) {
			Properties prop = new Properties();
			prop.load(in);
			Set<String> keys = prop.stringPropertyNames();
			for (String key : keys) {
				String value = prop.getProperty(key);
				System.out.println(key + "=" + value + "</br>");
			}
			in.close();
		}
		
	}
	/**
	 * ��ȡ�����ļ�
	 * �����ļ�λ��λ��classesĿ¼��
	 * ʹ����װ����getClassLoader��ȡ��Դ�ļ���·����Ϣ
	 * ��ʹ�ô�ͳ���ļ���ȡ��ʽ��ȡ�ļ�
	 * �����������������ļ��ڸ��º��ܹ���ʱ��ȡ��������Ϣ
	 * @throws IOException
	 */
	public void loadProperties2() throws IOException {
		String properties_path = DaoDemo.class.getClassLoader().getResource("test1.properties").getPath();
		System.out.println(properties_path);
		FileInputStream in = new FileInputStream(properties_path);
		//ͨ�����ļ��е�getClassLoader������ȡ��װ��������ͨ����װ���������ȡclassesĿ¼�е���Դ�ļ�
		if (in != null) {
			Properties prop = new Properties();
			prop.load(in);
			Set<String> keys = prop.stringPropertyNames();
			for (String key : keys) {
				String value = prop.getProperty(key);
				System.out.println(key + "=" + value + "</br>");
			}
			in.close();
		}
		
	}

}
