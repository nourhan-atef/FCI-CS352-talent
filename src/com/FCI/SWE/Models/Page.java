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

public class Page {
	private static String pagename;
	private static String type;
	private static String category;
	
	
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
