package com.FCI.SWE.Services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.FCI.SWE.Controller.ChatObserver;
import com.FCI.SWE.Controller.ChatSubject;
import com.FCI.SWE.Controller.Connection;
import com.FCI.SWE.Models.User;
//@Test(groups="UserEntity")
public class UserServicesTestintegration {
	
	@Test(dependsOnMethods="registration")
	public  void login()
	{
		String urlParameters = "uname=" + "ajj" + "&password=" +"a";

		String retJson = Connection.connect(
				"http://localhost:8888/rest/LoginService", urlParameters,
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
	///////////////////////////////////////////////////////////////////////
	@Test(dependsOnMethods="saveUser")
  	public  void registration()
  	{
    String serviceUrl = "http://localhost:8888/rest/RegistrationService";
	String urlParameters = "uname=" + "yya" + "&email=" + "kka"
			+ "&password=" + "a";
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	JSONParser parser = new JSONParser();
	Object obj;
	try {
		// System.out.println(retJson);
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		Assert.assertEquals(object.get("Status"), "OK");
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
  	}
    ///////////////////////////////////////////////////////////////
	@Test(dependsOnMethods="savefriendrequest") 
   	public  void sendrequest()
   	{
	    String urlParameters = "uname=" + "a" ;
	
		String retJson = Connection.connect(
				"http://localhost:8888/rest/sendrequestService", urlParameters,
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
    //////////////////////////////////////////////////////////////////////
    @Test(dependsOnMethods={"sendrequest"})
    public void makefriend() {

		String urlParameters = "uname=" + "w" + "&fname=" + "a";
		String retJson = Connection.connect(
				"http://localhost:8888/rest/makefriendService",urlParameters,
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
    /////////////////////////////////////////////////////////////////////////
    @Test(dependsOnMethods={"makefriend"})
    public void sendmessage() {
		String urlParameters = "fname=" + "h"+ "&message=" + "hi"; 

		String retJson = Connection.connect(
				"http://localhost:8888/rest/sendmessageService", urlParameters,
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
/////////////////////////////////////////////////////////////////////////
    @Test(dependsOnMethods={"savechatgroup"})
    public void chatgroup() {
		String urlParameters = "fname1=" + "a"+"fname2=" + "w"+"fname3=" + "m"+
				"fname4=" + "o"+"fname5=" + "r" +
				"conversationname=" + "friends"; 
	       
		String retJson = Connection.connect(
				"http://localhost:8888/rest/chatgroupService", urlParameters,
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
	
	
	
	
/////////////////////////////////////////////////////////////////////////
    @Test(dependsOnMethods={"savemessage"})
    public void writeMessage() {
		String urlParameters = "conversationname=" + "friends"+
				"message"+"ok"; 

		String retJson = Connection.connect(
				"http://localhost:8888/rest/writeMessageService", urlParameters,
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
/////////////////////////////////////////////////////////////////////////
  
/////////////////////////////////////////////////////////////////////////
    
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
    
	/* @DataProvider(name="SignOut")
	  public static Object[][] SignOut(){
	 		JSONObject object = new JSONObject();
	 		object.put("Status", "OK");
	 	 return new Object[][]{{object}};
	  }
	  @Test(dataProvider = "SignOut")
  public void SignOutService(JSONObject object) {
		  Assert.assertEquals(object.toJSONString(), UserServices.SignOutService());
  }
////////////////////////////////////////////////////////////////////////////////
  @DataProvider(name="chatgroup")
  public static Object[][] chatgroup(){
 		JSONObject object = new JSONObject();
 		object.put("Status", "OK");
 	 return new Object[][]{{object,"n","n","m","j","h"}};
  }
  @Test(dataProvider = "chatgroup")
  public void chatgroupService(JSONObject object, String fname1,String fname2,String fname3,String fname4,String fname5,String conversationname) {
	  Assert.assertEquals(object.toJSONString(), UserServices.chatgroupService(fname1,fname2,fname3,fname4,fname5,conversationname));
  }
  //////////////////////////////////////////////////////////////////////
 /* @DataProvider(name="login")
  public static Object[][] login(){
 		JSONObject object = new JSONObject();
 		object.put("Status", "OK");
 	 return new Object[][]{{object,"n","n"},{object,"q","q"},{object,"n","q"},{object,"q","n"}};
  }
  @Test(dataProvider = "login")
  public void loginService(JSONObject object, String name , String pass) {
     Assert.assertEquals(object.toJSONString(), UserServices.loginService(name, pass));
  }
//////////////////////////////////////////////////////////////////////
  @DataProvider(name="makefriend")
  public static Object[][] makefriend(){
 		JSONObject object = new JSONObject();
 		object.put("Status", "OK");
 	 return new Object[][]{{object,"n","n"},{object,"q","q"},{object,"n","q"},{object,"q","n"}};
  }
  @Test(dataProvider = "makefriend")
  public void makefriendService(JSONObject object, String name , String uname) {
	    Assert.assertEquals(object.toJSONString(), UserServices.makefriendService(name, uname));
  }
  ////////////////////////////////////////////////////////////////////////////////////
  @DataProvider(name="register")
  public static Object[][] register(){
 		JSONObject object = new JSONObject();
 		object.put("Status", "OK");
 	 return new Object[][]{{object,"n","n","n"},{object,"q","q","q"},{object,"n","q","h"},{object,"q","n","n"}};
  }
  @Test(dataProvider = "register")
  public void registrationService(JSONObject object, String name , String pass , String mail) {
	     Assert.assertEquals(object.toJSONString(), UserServices.registrationService(name, pass,mail));
  }
  
  
///////////////////////////////////////////////////////////////////////////
  @DataProvider(name="sendrequest")
  public static Object[][] sendrequest(){
 		JSONObject object = new JSONObject();
 		object.put("Status", "OK");
 	 return new Object[][]{{object,"n"},{object,"q"}};
  }
  @Test(dataProvider = "sendrequest")
  public void sendrequestService(JSONObject object, String name) {
	  Assert.assertEquals(object.toJSONString(), UserServices.sendrequestService(name));
  }
  ///////////////////////////////////////////////////////////////////////
  @DataProvider(name="writeMessage")
  public static Object[][] writeMessage(){
 		JSONObject object = new JSONObject();
 		object.put("Status", "OK");
 	 return new Object[][]{{object,"n","ll"},{object,"q","l"}};
  }
  @Test(dataProvider = "writeMessage")
  public void writeMessageService(JSONObject object, String message , String conversationname) {
	  Assert.assertEquals(object.toJSONString(), UserServices.writeMessageService(message,conversationname));
  }

  
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////integration testing ///////////////////////////////////

  @Test(dependsOnMethods={"loginService"})
public void  SignOutService1(JSONObject object){
	   SignOutService(object);
}
////////////////////////////////////////////////////////////////////
@Test(dependsOnMethods={"registrationService"})
public void  loginService1(JSONObject object, String name , String pass){
	loginService(object, name , pass);
}
///////////////////////////////////////////////////////////

@Test(dependsOnMethods={"loginService"})
public void  chatgroupService1(JSONObject object, String fname1,String fname2,String fname3
		,String fname4,String fname5,String conversationname){
	chatgroupService(object,fname1, fname2, fname3, fname4, fname5, conversationname);
}
///////////////////////////////////////////////////////////////////////////////////
@Test(dependsOnMethods={"sendrequestService"})
public void  makefriendService1(JSONObject object, String name , String uname){
	makefriendService( object, name , uname);
}
/////////////////////////////////////////////////////////////////////////////////
@Test(dependsOnMethods={"loginService1"})
public void  sendrequestService1(JSONObject object, String name ){
	sendrequestService( object,name);
}*/
//////////////////////////////////////////////////////////////////////////////////////
}