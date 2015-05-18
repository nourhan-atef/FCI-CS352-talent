package com.FCI.SWE.Models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.ServicesModels.PageEntity;
import com.FCI.SWE.ServicesModels.PostEntity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
<<<<<<< HEAD
/**
 * <h1>Page  class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author nada essam
 * @version 1.0
 * @since 2014-02-12
 */
=======

>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
public class Page {
	private static String pagename;
	private static String type;
	private static String category;
<<<<<<< HEAD
	/**
	 * Constructor accepts page data
	 * 
	 * @param pagename
	 *            page name
	 * @param type
	 *            type
	 * @param category
	 *           category
	 */
=======
	
>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
	
	public Page(String pagename, String type, String category) {
		super();
		this.pagename = pagename;
		this.type = type;
		this.category = category;
	}
	
	
	public static String getPagename() {
		return pagename;
	}
	public  void setPagename(String pagename) {
		this.pagename = pagename;
	}
	public static String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
<<<<<<< HEAD
	/**
	 * 
	 * This static method will form PageEntity class using json format contains
	 * page data
	 * 
	 * @param json
	 *            String in json format contains page data
	 * @return Constructed page entity
	 */
=======
	
>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
	public static PageEntity getPage(String pagename) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("page");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("pagename").toString().equals(pagename)) {
				PageEntity returnedPage = new PageEntity(entity.getProperty("pagename").toString(), null, null);
				
				return returnedPage;
			}
		}

		return null;
	}
	
	/*
	public static Page getPage(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			Page page = new Page(object.get("pagename").toString(), object.get(
					"type").toString(), object.get("category").toString());
			return page;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}*/

}
