package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import testbase.TestBase;

public class Utilities extends TestBase{

	public static void scrollToElement(WebElement e) 
	{
		JavascriptExecutor j=(JavascriptExecutor) driver;
		j.executeScript("arguments[0].scrollIntoView(false);", e);
		j.executeScript("window.scrollBy(0,300);", "");
	}
	
	public static void captureScreen() throws IOException 
	{
		TakesScreenshot t=(TakesScreenshot) driver; 
		File file =t.getScreenshotAs(OutputType.FILE); 
		FileHandler.copy(file, new File(getDate()+"_image.jpg"));
	}
	
	public static void addScreenshotToReport() throws IOException 
	{
		TakesScreenshot t=(TakesScreenshot) driver; 
		String file1 =t.getScreenshotAs(OutputType.BASE64); 
		String path = "<img src=\"data:image/jpg;base64,"+file1+"\" width=\"1024\" height=\"768\" />";
		//path="<img src="+"\file:// alt="+"\"\"/ />";
		Reporter.log(path);
	}
	public static void waitForElementClickable(WebElement ele) 
	{
		WebDriverWait wait=new WebDriverWait(driver, 20);//Explicit
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public static void waitForElement(WebElement ele) 
	{
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public static String getDate() {
		String s = null;
		Date d = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("Y_MMMM_dd_H_m_s_S");
		s=sdf.format(d);
		//System.out.println(s);
		return s;
	}
}
