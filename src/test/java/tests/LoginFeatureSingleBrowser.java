package tests;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import testbase.TestBase;
import utilities.Utilities;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class LoginFeatureSingleBrowser {
	public static Logger logger = Logger.getLogger(LoginFeatureSingleBrowser.class);
	LoginPage lp;
	DashboardPage dp;
	
  @Test(priority=1, description="Successful Login Test", groups="Sanity")
  public void verifyloginSucsess() throws IOException {
	  Reporter.log("\n \300 Login To Application \300");
	  logger.info("Login To Application");
	  lp.loginToApplication(TestBase.prop.getProperty("user"),TestBase.prop.getProperty("pass"));
	  Reporter.log("\n \300 Verify Dashboard \300");
	  logger.info("Verify Dashboard");
	  boolean b = dp.ifLogoutOptionVisbile();
	  Utilities.addScreenshotToReport();
	  Assert.assertTrue(b);
  }
  @BeforeClass(alwaysRun=true)
  public void beforeMethod() throws IOException 
  {
	  TestBase.getInstance();
	  lp=new LoginPage(TestBase.driver); 
	  dp=new DashboardPage(TestBase.driver);
  }

  @AfterClass(alwaysRun=true)
  public void afterMethod() {
	  TestBase.driver.quit();
  }

  @Test(priority=2, description="Successful Login Test", enabled=true , groups="Regression")
  public void verifySizeofLeftPanelOnDashboard() throws IOException
  {
	  //verifyloginSucsess();
	  int a = dp.getNumberOfOptionOfLeftPanel();
	  Reporter.log("\n \300 Total count:"+a+"\300");
	  logger.info("Total count:"+a);
	  Assert.assertEquals(10, a);
	  //Assert.assertTrue(false);
  }
  
  @Test(priority=3, description="Successful Login Test", enabled=true, dependsOnMethods="verifySizeofLeftPanelOnDashboard")
  public void ListOfTextOnLeftPanel_dependentMethod() 
  {
	  List<String> a = dp.getTextOfOptionOfLeftPanel();
	  Reporter.log("\n \300 Total count:"+a+"\300");
	  logger.info("All Strings:"+a);
	  Assert.assertTrue(a.get(0).contains("Dashboard"));
  }
}
