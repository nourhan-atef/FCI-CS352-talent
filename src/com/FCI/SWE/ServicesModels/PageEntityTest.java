package com.FCI.SWE.ServicesModels;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PageEntityTest {

  @Test
  public void savePage() {
	  String pagename="fci";
	  String type="education";
	  String category="education";
	  Boolean b=true;
	  PageEntity page=new PageEntity(pagename,type,category);
	  Assert.assertEquals(page.savePage(pagename,type,category),b);
    
  }

  @Test
  public void savePageLiker() {
	  String pagename="fci";
	  Boolean b=true;
	  PageEntity page=new PageEntity(pagename,"education","education");
	  Assert.assertEquals(page.savePageLiker(pagename),b);
    
  }
}
