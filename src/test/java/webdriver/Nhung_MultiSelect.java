package webdriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Nhung_MultiSelect {
	
	WebDriver driver; 
	@BeforeClass
	public void beforeClass() {
		  driver = new FirefoxDriver();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  driver.manage().window().maximize();
		
	}
	@Test
    public void MultiSelect() throws InterruptedException {
		
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> elements = driver.findElements(By.xpath("//ol[@id = 'selectable']/li"));
		Actions action = new Actions(driver);	
		action.keyDown(Keys.COMMAND).perform();
	    elements.get(0).click();
	    elements.get(1).click();
	    elements.get(2).click();
	    elements.get(3).click();
	    elements.get(4).click();
	    elements.get(5).click();
	    action.keyUp(Keys.COMMAND).perform();
	    Thread.sleep(1000);
	    
		
	}
	
	@AfterClass
	 public void afterClass() {
	  driver.quit();
	 }


}
