package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Nhung_DragAndDrop {
	
	WebDriver driver; 
	@BeforeClass
	public void beforeClass() {
		  driver = new FirefoxDriver();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  driver.manage().window().maximize();
		
	}
	@Test
	public void DragAndDrop() throws InterruptedException {
		
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement sourceButton = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetButton = driver.findElement(By.xpath("//div[@id='droptarget']"));
		Actions action = new Actions(driver);
		Thread.sleep(700);
		action.dragAndDrop(sourceButton, targetButton).build().perform();
		Thread.sleep(700);
		Assert.assertEquals("You did great!", targetButton.getText());
		Thread.sleep(700);
	}
	
	
	@AfterClass
	 public void afterClass() {
	  driver.quit();
	 }


}
