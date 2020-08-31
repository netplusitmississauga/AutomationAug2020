package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

	private WebDriver dr;
	public DashboardPage(WebDriver driver)
	{
		this.dr=driver;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(linkText="Logout")
	private WebElement logout;
	
	@FindBy(xpath="//li[@class='treeview']/descendant::span[text()='System']")
	private WebElement systemInPanel;
	
	@FindBy(xpath="//*[@class='treeview-menu']/descendant::span[text()='System information']")
	private WebElement sysInfo;
	
	@FindBy(css="ul[data-widget='tree']>li")
	private List<WebElement> panelLeft;
	
	@FindBy(xpath="//div[@class='main-sidebar']/div/ul/li/a/span")
	private List<WebElement> panelText;
	
	public boolean ifLogoutOptionVisbile() 
	{
		boolean b=false;
		try 
		{
			b=logout.isDisplayed();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public void clickOnSystemInLeftPanel()
	{
		systemInPanel.click();
	}
	
	public void clickOnSystemInfo()
	{
		sysInfo.click();
	}
	
	public int getNumberOfOptionOfLeftPanel()
	{	
		return panelLeft.size();	
	}
	public List<String> getTextOfOptionOfLeftPanel()
	{	
		List<String> str=new ArrayList<String>();
		for(WebElement s: panelText)
		{
			str.add(s.getText());
		}
		return str;	
	}
}
