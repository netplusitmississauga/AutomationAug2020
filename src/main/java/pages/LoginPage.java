package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private WebDriver dr;
	public LoginPage(WebDriver driver)
	{
		this.dr=driver;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(id="Email")
	private WebElement email;
	
	@FindBy(css="#Password")
	private WebElement password;
	
	@FindBy(xpath="//*[@type='submit']")
	private WebElement submit;
	
	public void loginToApplication(String user, String pass) 
	{
		System.out.println(user);
		System.out.println(pass);
		email.clear();
		password.clear();
		email.sendKeys(user);
		password.sendKeys(pass);
		submit.click();
	}
	
}
