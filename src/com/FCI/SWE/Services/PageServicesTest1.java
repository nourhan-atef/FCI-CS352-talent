package com.FCI.SWE.Services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.FCI.SWE.Controller.Connection;
import com.FCI.SWE.Models.Page;
import com.FCI.SWE.Models.Post;
import com.google.appengine.labs.repackaged.org.json.JSONException;

public class PageServicesTest1 {
	@Test
	public void createpage() {
		String urlParameters = "pagename=" + "manage" + "&type=" + "education" + "&category=" + "education" ;
		
		String retJson = Connection.connect(
				"http://localhost:8888/rest/createpageServicee", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			 Assert.assertEquals(object.get("Status"), "OK");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void likepage() {
		String urlParameters = "pagename=" + "sw";

		String retJson = Connection.connect(
				"http://localhost:8888/rest/likepageService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		System.out.print("likersss");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			Assert.assertEquals(object.get("Status"), "OK");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//////////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////////////////	
	
  /*
	@DataProvider(name="createpage")
	  public static Object[][] createpage(){
	 		JSONObject object = new JSONObject();   
	 		object.put("Status", "OK");
	        return new Object[][]{{object,"n","n","m"},{object,"n","sw","m"}};
	  }
	
	
	
	
	  @Test(dataProvider = "createpage")
	  public void createpageService(JSONObject object,String pagename, String type, String category) throws JSONException {
		    Assert.assertEquals(object, PageServices.createpostService(pagename, type, category));
	  }

	  @DataProvider(name="likepage")
	  public static Object[][] likepage(){
	 		JSONObject object = new JSONObject();   
	 		object.put("Status", "OK");
	        return new Object[][]{{object,"n"},{object,"nor"}};
	  }
	  @Test(dataProvider = "likepage")
  public void likepageService(JSONObject object,String pagename) {
		  Assert.assertEquals(object, PageServices.likepageService(pagename));
	  
  }
*/
	}
