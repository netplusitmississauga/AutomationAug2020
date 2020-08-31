package testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import utilities.Utilities;

public class TestBase {

	public static WebDriver driver;
	public static Logger logger = Logger.getLogger(TestBase.class);
	public static Properties prop;
	public static String browser;
	public static String file="./src/main/resources/config/config.properties";
	public static WebDriver getInstance () throws IOException 
	{
		//PropertyConfigurator.configure("./src/log4j.properties");
		//************
		//Read browser from config file
		//************
		FileInputStream inStream = new FileInputStream(file);
		prop = new Properties();
		prop.load(inStream);
		//************
		browser=prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chrome"));
			driver = new ChromeDriver(); 
		}else if (browser.equalsIgnoreCase("firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", prop.getProperty("firefox"));
			driver = new FirefoxDriver(); 
		}else if(browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", prop.getProperty("IE"));
			driver = new InternetExplorerDriver(); 
		}else if(browser.equalsIgnoreCase("edge"))
		{
			//System.setProperty("webdriver.edge.driver", "./drivers/chromedriver.exe");
			driver = new EdgeDriver(); 
		}else if(browser.equalsIgnoreCase("cloud"))//saucelabs
		{
			DesiredCapabilities cap= new DesiredCapabilities();
			cap.acceptInsecureCerts();
			cap.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
			cap.setCapability(CapabilityType.ENABLE_PROFILING_CAPABILITY, true);
			//DesiredCapabilities cap1= DesiredCapabilities.chrome();
			ChromeOptions opt=new ChromeOptions();
			opt.setHeadless(false);
			System.out.println(opt.getCapabilityNames());
			System.out.println(opt.getPlatform());
			System.out.println(opt.getVersion());
			System.out.println(cap.getCapabilityNames());
			opt.merge(cap);
			driver=new RemoteWebDriver(new URL(""), cap);
		}
		else 
		{
			Throwable thr=new Throwable();
			thr.initCause(null);
		}
		//implicit wait
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		//Utilities.addScreenshotToReport();
		return driver;
		//driver.get("http://www.google.ca");
	}
}
