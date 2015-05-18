package com.FCI.SWE.Controller;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.FCI.SWE.Services.UserServices;

public class UserControllerTest {

  @Test
  public void acceptFriendNotification() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void chatgroup() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void chatgroupStringStringStringStringStringString() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void chatgroupStringString() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void excuteService() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void excuteService2() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void home() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void index() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void makefriend() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void makefriend1() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void notification() {
    throw new RuntimeException("Test not implemented");
  }
//////////////////////////////////////////////////////////
   /*@DataProvider(name="login")
  public static Object[][] login(){
 	 return new Object[][]{{"login Successfully","n","n"},{"failed","q","q"},{"failed","n","q"},{"failed","q","n"}};
  }
  @Test(dataProvider = "login")
  public void loginService(String result,String name , String pass) {
	  
     Assert.assertEquals(result, UserController.home(name, pass));
  }*/
 
  //////////////////////////////////////////////////////////
  @DataProvider(name="register")
  public static Object[][] register(){
 	 return new Object[][]{{"Registered Successfully","n","n","n"},{"failed","q","q","q"},{"failed","n","q","g"},{"failed","q","n","h"}};
  }
  @Test(dataProvider = "register")
  public void loginService(String result,String name ,String mail, String pass) {
	  
     Assert.assertEquals(result, UserController.response(name,mail ,pass));
  }
  ///////////////////////////////////////////////////////////

  @Test
  public void sendmessage() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void sendmessageStringString() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void sendrequest() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void sendrequestString() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void signUp() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void signout() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void writeMessage() {
    throw new RuntimeException("Test not implemented");
  }
}
