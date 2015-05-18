package com.FCI.SWE.ServicesModels;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import com.FCI.SWE.Services.PageServices;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONException;

public class UserEntityTest {
	@DataProvider(name="getUser")
	  public static Object[][] getUser(){
		UserEntity returnedUser = new UserEntity("n","n","n");
	        return new Object[][]{{returnedUser,"n","n","m"},{returnedUser,"n","sw","m"}};
	  }
	
	  @Test(dataProvider = "getUser")
	  public void getUser(UserEntity returnedUser ,String name, String pass) throws JSONException {
		    Assert.assertEquals(returnedUser, UserEntity.getUser(name, pass));
	  }
	  
	  @Test
	  public void saveUser() {
		 //throw new RuntimeException("Test not implemented");	  
	      Boolean b=true;
		  UserEntity UE = new UserEntity("s","e@ss","s");
		  Assert.assertEquals(UE.saveUser(),b);
	  }
	
  
  @Test
  public void savechatgroup() {
	  String fname1="n";
	  String fname2="q";
	  String fname3="w";
	  String fname4="e";
	  String fname5="a";
	  String conversationname="friends";
	  Boolean b=true;
	  UserEntity UE = new UserEntity("s","e@ss","s");
	  Assert.assertEquals(UE.savechatgroup(fname1,fname2,fname3,fname4,
				fname5,conversationname),b);
  }

  @Test
  public void savefriendrequest() {
	  String fname="a";
	  Boolean b=true;
	  UserEntity UE = new UserEntity("s","e@ss","s");
	  Assert.assertEquals(UE.savefriendrequest(fname),b);
  }
  
  @Test
  public void savemessage() {
	  String fname="a";
	  String message="hi";
	  Boolean b=true;
	  UserEntity UE = new UserEntity("s","e@ss","s");
	  Assert.assertEquals(UE.savemessage(fname,message),b);
  }

  @Test
  public void savewriteMessage() {
	  String message="hi";
	  String conversationname="friend";
	  Boolean b=true;
	  UserEntity UE = new UserEntity("s","e@ss","s");
	  Assert.assertEquals(UE.savewriteMessage(message,conversationname),b);
  }
}
