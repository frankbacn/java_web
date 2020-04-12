package java_web.classloaderdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class DaoDemo {
	/**
	 * 读取配置文件
	 * 配置文件位置位于classes目录中
	 * 使用类装载器getClassLoader获取资源文件
	 * 类装载其中的getResourceAsStream方法获取到的资源对象流不会在资源更新时同时更新
	 * @throws IOException
	 */
	public void loadProperties() throws IOException {
		/*
		 * 使用类装载器来读取配置文件应关注配置文件的大小
		 * 因为类装载器会一次性将文件加载进内存，很容易造成内存溢出的发生
		 */
		InputStream in = DaoDemo.class.getClassLoader().getResourceAsStream("test1.properties");
		//通过类文件中的getClassLoader方法获取类装载器，再通过类装载器对象获取classes目录中的资源文件
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
	 * 读取配置文件
	 * 配置文件位置位于classes目录中
	 * 使用类装载器getClassLoader获取资源文件的路径信息
	 * 再使用传统的文件读取方式读取文件
	 * 这样可以做到配置文件在更新后能够及时获取到更新信息
	 * @throws IOException
	 */
	public void loadProperties2() throws IOException {
		String properties_path = DaoDemo.class.getClassLoader().getResource("test1.properties").getPath();
		System.out.println(properties_path);
		FileInputStream in = new FileInputStream(properties_path);
		//通过类文件中的getClassLoader方法获取类装载器，再通过类装载器对象获取classes目录中的资源文件
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
