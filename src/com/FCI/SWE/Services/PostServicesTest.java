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

public class PostServicesTest {
	
@Test	
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

@Test
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
@Test	
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
@Test
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

//////////////////////////////////////////////////////////////
	@Test
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
	
	
/*	
 @DataProvider(name="createpost")
  public static Object[][] createpost(){
 		JSONObject object = new JSONObject();   
 		object.put("Status", "OK");
        return new Object[][]{{object,"n","n","m","j"},{object,"n","sw","m","j"}};
  }
  @Test(dataProvider = "createpost")
  public void createpostService(JSONObject object,String postcontent,String pagename,String feeling,String postprivacy) throws JSONException {
	    Assert.assertEquals(object, PostServices.createpostService(postcontent, pagename,feeling,postprivacy));
  }
  


////////////////////////////////////////////////////////////////////////
  @DataProvider(name="hashtagstatistic")
  public static Object[][] hashtagstatistic(){
	  Vector<String>vec=new Vector<String>();
	  vec.add("kkkknlnl");
        return new Object[][]{{vec,"#h"},{vec,"#n"}};
  }
  @Test(dataProvider = "hashtagstatistic")
  public void hashtagstatisticService(Vector vec,String hashName) throws JSONException {
	    Assert.assertEquals(vec, PostServices.hashtagstatisticService(hashName));
  }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @DataProvider(name="share")
  public static Object[][] share(){
 		JSONObject object = new JSONObject();   
 		object.put("Status", "OK");
        return new Object[][]{{object,"n",""},{object,"k","3:00"}};
  }
  @Test(dataProvider = "share")
  public void shareService(JSONObject object,String ownername,String  time) throws JSONException {
	    Assert.assertEquals(object, PostServices.shareService(ownername, time));
  }
//////////////////////////////////////////////////////////////////////////////////////////////////  so2al
  @DataProvider(name="showpost")
  public static Object[][] showpost(){
	  //current user n
	  Vector<String>vec=new Vector<String>();
	  vec.add("kkkknlnl");
        return new Object[][]{{vec},{vec}};
  }
  @Test(dataProvider = "showpost")
 
  public void showpostService(Vector vec) throws JSONException {
	  Assert.assertEquals(vec, PostServices.showpostService());
  
  }
  //////////////////////////////////////////////////////////////////////////////////////// so2al
  
  @DataProvider(name="timeline")
  public static Object[][] timeline(){
	  //current user n
	  Vector<String>vec=new Vector<String>();
	  vec.add("kkkknlnl");
        return new Object[][]{{vec},{vec}};
  }
  @Test(dataProvider = "timeline")
 
  public void timelineService(Vector vec) throws JSONException {
	  Assert.assertEquals(vec, PostServices.timelineService());
  }
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////integration testing ////////////////////////////////////////////////
  @Test(dependsOnMethods={"getPost"})
  public void  timelineService1(Vector vec ) throws JSONException{
	  timelineService( vec);
  }
  ////////////////////////////////////////////////////
  @Test(dependsOnMethods={"getPost"})
  public void  showpostService1(Vector vec ) throws JSONException{
	  showpostService( vec);
  }
  ////////////////////////////////////////////////////////////////////
  @Test(dependsOnMethods={"createpostService"})
  public void  shareService1(JSONObject object,String ownername,String  time) throws JSONException{
	  shareService(object,ownername, time);
  }	
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Test(dependsOnMethods={"getPost"})
  public void  hashtagstatisticService1(Vector vec,String hashName ) throws JSONException{
	  hashtagstatisticService( vec,hashName);
  }
  ///////////////////////////////////////////////////////////////////////////
*/
	}
