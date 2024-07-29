package webdriver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic01_checkENV {
 WebDriver driver;

 @BeforeClass
 public void beforeClass() {
  driver = new FirefoxDriver();
  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  driver.manage().window().maximize();
  driver.get("https://www.facebook.com/");
 }

 @Test
 public void TC_01_ValidateCurrentUrl() {
  // Login Page Url matching
  String loginPageUrl = driver.getCurrentUrl();
  Assert.assertEquals(loginPageUrl, "https://www.facebook.com/");
 }


}