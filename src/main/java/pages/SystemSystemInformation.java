package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SystemSystemInformation {

	private WebDriver dr;
	public SystemSystemInformation(WebDriver driver)
	{
		this.dr=driver;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(css=".control-label")
	private List<WebElement> controlLabel;
	
	@FindBy(css=".form-text-row")
	private List<WebElement> controlValues;
	
	public Map<String,String> getListOfControlLabel()
	{
		Map<String,String> s = new HashMap<String, String>();
		for(int i=0;i<controlValues.size();i++)
		{
			s.put(controlLabel.get(i).getText().trim(), 
					controlValues.get(i).getText().trim());
		}
		return s;
	}
}
