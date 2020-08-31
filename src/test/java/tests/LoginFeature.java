package tests;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import testbase.TestBase;
import utilities.Utilities;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class LoginFeature {
	public static Logger logger = Logger.getLogger(LoginFeature.class);
	LoginPage lp;
	DashboardPage dp;

  @Parameters({"user","pass"})
  @Test(priority=1, description="Successful Login Test", groups="Sanity")
  public void verifyloginSucsess(String user, String pass) throws IOException {
	  Reporter.log("Login To Application");
	  logger.info("Login To Application");
	  //lp.loginToApplication(TestBase.prop.getProperty("user"),TestBase.prop.getProperty("pass"));
	  lp.loginToApplication(user,pass);
	  Reporter.log("Verify Dashboard:--> "+user+":"+pass);
	  logger.info("Verify Dashboard:--> "+user+":"+pass);
	  boolean b = dp.ifLogoutOptionVisbile();
	  Utilities.addScreenshotToReport();
	  Assert.assertTrue(b);
  }
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod() throws IOException 
  {
	  TestBase.getInstance();
	  lp=new LoginPage(TestBase.driver); 
	  dp=new DashboardPage(TestBase.driver);
  }

  @AfterMethod(alwaysRun=true)
  public void afterMethod() {
	  TestBase.driver.quit();
  }

  @Parameters({"user","pass"})
  @Test(priority=2, description="Successful Login Test", groups="Regression")
  public void verifySizeofLeftPanelOnDashboard(String user, String pass) throws IOException
  {
	  verifyloginSucsess(user, pass);
	  int a = dp.getNumberOfOptionOfLeftPanel();
	  Assert.assertEquals(10, a);
  }
}
