package webdriver;

import java.time.Duration;
import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaiTapVeNha01 {
	
	WebDriver driver; 
	@BeforeClass
	public void beforeClass() {
		  driver = new FirefoxDriver();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  driver.manage().window().maximize();
		
	}
	@Test
	public  void TestCase01() throws InterruptedException {
		
	    
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		//gender 
		driver.findElement(By.xpath("//label[text() = 'Male']")).click();
		Thread.sleep(500);
		
		//firstname 
	    driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Nhung");
	    Thread.sleep(500);
	    
	    //lastname
	    driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Vo");
	    Thread.sleep(500);
	    
	    //Date
	    Select date = new Select( driver.findElement(By.xpath("//select[@name ='DateOfBirthDay']")));
	    date.selectByIndex(3);
	    Thread.sleep(500);
	    
	    //month
	    Select month = new Select( driver.findElement(By.xpath("//select[@name ='DateOfBirthMonth']")));
	    month.selectByValue("4");
	    Thread.sleep(500);
	    
	    //year
	    Select year = new Select( driver.findElement(By.xpath("//select[@name ='DateOfBirthYear']")));
	    year.selectByIndex(5);
	    Thread.sleep(500);
	    
	    //email
	    int now = LocalDateTime.now().getSecond();
	    driver.findElement(By.xpath("//input[@id ='Email']")).sendKeys("nhungvo.rose" + now + "@gmail.com");
	    Thread.sleep(500);
	    
	    //Company
	    driver.findElement(By.xpath("//input[@id ='Company']")).sendKeys("Tiki  company");
	    Thread.sleep(500);
	    
	    //checkbox
	    WebElement checkbox = driver.findElement(By.xpath("//input[@id='Newsletter']"));
	    Assert.assertTrue(checkbox.isSelected());
	    if(checkbox.isSelected()){ 
	        checkbox.click();
	   	
	    }
	    Thread.sleep(800);
	    
	    //PW
	    driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("H0ngnhung.");
	    Thread.sleep(500);
	    driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("H0ngnhung.");
	    Thread.sleep(500);
	    
	    //Submit  
	    driver.findElement(By.xpath("//button[@id='register-button']")).click();
	    Thread.sleep(2000);
	    
	    //check result
	    Assert.assertEquals("Your registration completed",driver.findElement(By.xpath("//div[@class ='result']")).getText());
	    Thread.sleep(2000);
	    
	    
	}
	
	@AfterClass
	 public void afterClass() {
	  driver.quit();
	 }

	
	

}
