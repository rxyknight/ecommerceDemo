package com.developerroy.demo.ecommerce.dao;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.developerroy.demo.ecommerce.model.Product;



public class FlickrDao {
	
	public static final String API_KEY = "2cb872ef7308d0f89a67be373d731671";
	public static final String METHOD = "flickr.photos.search";
	public static final String METHOD1 = "flickr.photos.getInfo";
	public static final String EXTRAS_TYPE = "tags";
	public static final String SORT_TYPE = "relevance";
	public static final String REST_ENDPOINT = "https://api.flickr.com/services/rest/";
	public static final String ENC = "UTF-8"; //encoding scheme
	public static final String SOAP2 = "soap2";
	
	private static FlickrDao flickrDao;
	private FlickrDao(){
		
	}
	public static FlickrDao getInstance(){
		if(flickrDao ==null){
			flickrDao = new FlickrDao();
			return flickrDao;
		}else{
			return flickrDao;
		}
	}
	public List<Product> searchPhotos(String keyword){
		List<Product> products = new ArrayList<Product>();
		try{
			String callUrlStr = REST_ENDPOINT+"?method="+METHOD+
			"&text="+URLEncoder.encode(keyword,ENC)+"&sort="+SORT_TYPE+
			"&extras="+EXTRAS_TYPE+
			"&api_key="+API_KEY;
			System.out.println(callUrlStr);
			URL callUrl = new URL(callUrlStr);			
			HttpURLConnection urlConnection = (HttpURLConnection)callUrl.openConnection();
			InputStream urlStream = urlConnection.getInputStream();
		
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document response = db.parse(urlStream);
			
			//print out all titles
			//System.out.println("The titles returned are: ");
			NodeList nl = response.getElementsByTagName("photo");
			
			for (int i = 0; i < 10; i ++){
				String title = nl.item(i).getAttributes().getNamedItem("title").getTextContent();
				String tags = nl.item(i).getAttributes().getNamedItem("tags").getTextContent().trim();
				String farmID = nl.item(i).getAttributes().getNamedItem("farm").getTextContent();
				String serverID = nl.item(i).getAttributes().getNamedItem("server").getTextContent();
				String id = nl.item(i).getAttributes().getNamedItem("id").getTextContent();
				String secret = nl.item(i).getAttributes().getNamedItem("secret").getTextContent();
				String imgUrl = "http://"+"farm"+farmID+".staticflickr.com/"+serverID+"/"+id+"_"+secret+"_s.jpg";
				Product pd = new Product(id, title, tags, imgUrl);
				products.add(pd);
				
			}
			urlConnection.disconnect();
			
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return products;
	}
	
	public Product getProductById(String productId){
		
		try{
			String callUrlStr = REST_ENDPOINT+"?method="+METHOD1+
					"&api_key="+API_KEY+"&photo_id="+productId;
			System.out.println(callUrlStr);
			URL callUrl = new URL(callUrlStr);			
			HttpURLConnection urlConnection = (HttpURLConnection)callUrl.openConnection();
			InputStream urlStream = urlConnection.getInputStream();
		
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document response = db.parse(urlStream);
			
			//print out all titles
			//System.out.println("The titles returned are: ");
			NodeList nl = response.getElementsByTagName("photo");
			
			String farmID = nl.item(0).getAttributes().getNamedItem("farm").getTextContent();
			String serverID = nl.item(0).getAttributes().getNamedItem("server").getTextContent();
			String id = nl.item(0).getAttributes().getNamedItem("id").getTextContent();
			String secret = nl.item(0).getAttributes().getNamedItem("secret").getTextContent();
			String imgUrl = "http://"+"farm"+farmID+".staticflickr.com/"+serverID+"/"+id+"_"+secret+"_s.jpg";
			
			nl = response.getElementsByTagName("title");
			String title = nl.item(0).getTextContent();
			nl = response.getElementsByTagName("tag");
			String tags=" ";
			for(int i = 0;i<nl.getLength();i++){
				tags=tags+nl.item(i).getTextContent()+" ";
			}
			tags=tags.trim();
			//System.out.println(tags);
			Product product = new Product(productId, title, tags, imgUrl);
			urlConnection.disconnect();
			return product;
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	public static void main(String args[]){
		List<Product> products;
		products = FlickrDao.getInstance().searchPhotos("dog");
		System.out.println(products);
		Product product = FlickrDao.getInstance().getProductById("14165229686");
		System.out.println(product);
		
	}
	
}
