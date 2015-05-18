package com.FCI.SWE.Services;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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
import com.FCI.SWE.ServicesModels.PostEntity;
import com.google.appengine.labs.repackaged.org.json.JSONException;


public class PostServicesTestintegration {


		
	@Test(dependsOnMethods={"createpost"})
	public void timeline1() {
			
			String retJson = Connection.connect(
					"http://localhost:8888/rest/timelineService","",
					"POST", "application/x-www-form-urlencoded;charset=UTF-8");
			JSONParser parser = new JSONParser();
			Object obj;
			try {
				obj = parser.parse(retJson);
				JSONObject object = (JSONObject) obj;
				Assert.assertEquals(object.get("Status"), "OK");
			}
			catch(Exception e)
			{
			  e.printStackTrace();
			}
		
	}
	///////////////////////////////////////////////////////

	@Test(dependsOnMethods={"createpost"})
	public void hashtagtrends() {

		String retJson = Connection.connect(
				"http://localhost:8888/rest/hashtagtrendsService","",
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			Assert.assertEquals(object.get("Status"), "OK");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	////////////////////////////////////////////////////////////////
	@Test(dependsOnMethods={"createpost"})
	public void showpost() {
			
			String retJson = Connection.connect(
					"http://localhost:8888/rest/showpostService","",
					"POST", "application/x-www-form-urlencoded;charset=UTF-8");
			JSONParser parser = new JSONParser();
			Object obj;
			try {
				obj = parser.parse(retJson);
				JSONObject object = (JSONObject) obj;
				Assert.assertEquals(object.get("Status"), "OK");
			}
			catch(Exception e)
			{
			  e.printStackTrace();
			}
		
	}
	///////////////////////////////////////////////////////////////
	@Test(dependsOnMethods={"createpost"})
	public void hashtagstatistic() {
		
		String urlParameters = "hashtagname=" + "#h";

		String retJson = Connection.connect(
				"http://localhost:8888/rest/hashtagstatisticService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {	
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			Assert.assertEquals(object.get("Status"), "OK");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//////////////////////////////////////////////////////////////
	
	public void createpost() {
		String urlParameters = "&postcontent=" + "HI " + "&pagename=" + "hh"+
				"&feeling=" + "nice"+"&postprivacy=" + "a";

		String retJson = Connection.connect(
				"http://localhost:8888/rest/createpostService", urlParameters,
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

	////////////////////////////////////////////////////////////
	@Test(dependsOnMethods={"createpost"})
		public void share() {
			String urlParameters = "ownername=" + "a" 
					+"&time=" + "3:00";
			String retJson = Connection.connect(
					"http://localhost:8888/rest/shareService", urlParameters,
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
		///////////////////////////////////////////////////////
		}
