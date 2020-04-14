package java_web.bean;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 产品数据DAO类
 * 提供了初始化函数，根据给定路径初始化对象
 * 提供根据产品名称查询产品id方法和根据产品id获取产品名称的方法
 * 提供了获取所有产品信息的方法
 * @author zhongming
 *
 */
public class ProductsDAO {
	private File dataFile; 
	private Document dom;
	/**
	 * 构造函数
	 * 根据给定的dataFilePath路径加载xml文件
	 * @param dataFilePath
	 */
	public ProductsDAO(String dataFilePath){
		System.out.println(dataFilePath);
		if(dataFilePath.trim().length()!=0){
			dataFile = new File(dataFilePath);
			if(!dataFile.exists()){
				throw new RuntimeException("数据文件加载错误");
			}
			SAXReader reader = new SAXReader();
			try{
				dom = reader.read(dataFile);
			}catch (DocumentException e) {
				throw new RuntimeException("数据文件读取错误");
			}
		}
	}
	/**
	 * 根据给定的产品id查询产品名称
	 * 如果未查询到产品名称则返回空字符串
	 * @param id
	 * @return
	 */
	public String getProductName(String id){
		Element root = dom.getRootElement();
		for(Iterator<Element> it = root.elementIterator();it.hasNext();){
			Element product = it.next();
			if(id.equals(product.attribute("id").getValue())){
				return product.getText();
			}
		}
		return "";
	}
	/**
	 * 获取数据文件中所有产品信息，以HashMap集合返回
	 * @return
	 */
	public HashMap<String, String> getAllProducts(){
		HashMap<String, String> products = new HashMap<String, String>();
		for(Iterator<Element> it = dom.getRootElement().elementIterator();it.hasNext();){
			Element product = it.next();
			products.put(product.attributeValue("id"), product.getText());
		}
		return products;
	}
	/**
	 * 根据产品名称查询产品ID
	 * 如果未查询到结果返回空字符串
	 * @param productName
	 * @return
	 */
	public String getProductID(String productName){
		for(Iterator<Element> it = dom.getRootElement().elementIterator();it.hasNext();){
			Element product = it.next();
			if(productName.equals(product.getText())){
				return product.attributeValue("id");
			}
		}
		return "";
	}
}
