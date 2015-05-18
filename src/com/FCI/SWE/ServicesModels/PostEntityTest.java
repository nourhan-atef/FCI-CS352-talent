package com.FCI.SWE.ServicesModels;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PostEntityTest {

  @Test
  public void savePost() {
	  PostEntity PE= new PostEntity("ana t3bana men DSR","09:08","n","sadgdn"," ","public") ;
	  Boolean b=true;
	  Assert.assertEquals(PE.savePost("ana t3bana men DSR"," ","sadgdn","public"),b);
  }

  @Test
  public void saveseen() {
	  PostEntity PE= new PostEntity("ana t3bana men DSR","09:08","n","sadgdn"," ","public") ;
	  Boolean b=true;
	  Assert.assertEquals(PE.saveseen("n","ana t3bana men DSR","09:08","public","sadgdn"," ","5","6"),b);	 
  }

  
}
