package webdriver;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class BTVN1407 {
	
	WebDriver driver; 
	@BeforeClass
	public void beforeClass() {
		  driver = new FirefoxDriver();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  driver.manage().window().maximize();
		
	}
	@SuppressWarnings("deprecation")
	@Test
    public void ex_01() throws InterruptedException {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//div[@id='start']//child::button")).click();
		//sử dụng implicit wait
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']//child::h4")).getText(), "Hello World!");
		Thread.sleep(100);
		
	}   
	@Test
	public void ex_02() throws InterruptedException{
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		Thread.sleep(100);
		driver.findElement(By.xpath("//div[@id='start']//child::button")).click();
		Thread.sleep(100);
		//sử dụng explicit wait
		WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(50));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#loading")));
		Thread.sleep(100);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']//child::h4")).getText(), "Hello World!");
	}
	@Test
	public void ex_03() throws InterruptedException{
		
		//1.Truy cập trang: https://kyna.vn/   ,  
		driver.get("https://kyna.vn/");

		WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(50));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@id ='cs_chat_iframe']")));
		
		//2. open chat Iframe
		driver.findElement(By.xpath("//iframe[@id ='cs_chat_iframe']")).click();
		Thread.sleep(500);
		driver.switchTo().frame("cs_chat_iframe");
		
		//3. Submit button trong chat 
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bottom meshim_widget_widgets_Bottom bottom_medium desktop']//child::input")));
		driver.findElement(By.xpath("//div[@class='bottom meshim_widget_widgets_Bottom bottom_medium desktop']//child::input")).click();
		Thread.sleep(500);
		
		//4.Verify dòng chữ “Tên của bạn chưa được nhập” xuất hiện
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='editing field profile_field']//child::div[@class='error_message meshim_widget_widgets_ErrorMessage ng-binding']")).getText(), "Tên của bạn chưa được nhập ");
		
		//5. Từ iFrame quay về màn hình chính
		driver.switchTo().defaultContent();

		//5,6. không làm thêm được vì không tắt được popup Thông báo
	};
    @Test
    public void ex_04() throws InterruptedException {
    	//1.Truy cập trang 
    	driver.get("http://live.techpanda.org/");
    	
    	//2.Cick vào Mobile tab
    	driver.findElement(By.xpath("//li[@class='level0 nav-1 first']//child::a")).click();
    	
    	//3. Add sản phẩm Sony Xperia vào để compare (Add to Compare)
    	driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2/following-sibling::div[@class='actions']//child::ul[@class='add-to-links']//descendant::li//child::a[@class='link-compare']")).click();
    	WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(50));
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='success-msg']")));
    	
    	//4. Verify text hiển thị: The product Sony Xperia has been added to comparison list.
    	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//child::ul//descendant::li//descendant-or-self::span")).getText(), "The product Sony Xperia has been added to comparison list.");
    	
    	//5. Add sản phẩm Samsung Galaxy vào để compare (Add to Compare)
    	driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//child::ul[@class='add-to-links']//descendant::li//child::a[@class='link-compare']")).click();
    	WebDriverWait explicitWait1 = new WebDriverWait(driver,Duration.ofSeconds(50));
    	explicitWait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='success-msg']")));
    	
    	//6. Verify text hiển thị: The product Samsung Galaxy has been added to comparison list.
    	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//child::ul//descendant::li//descendant-or-self::span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
    	//7. Nhấn Compare Button
    	driver.findElement(By.xpath("//button[@class='button']//child::span[text()='Compare']")).click();
    	Thread.sleep(500);
    	//8. Switch qua cửa sổ mới (chứa 2 sản phẩm đã đc add)
    	String currentWindown = driver.getWindowHandle();
    	Set<String> allWindowns = driver.getWindowHandles();
    	for(String windownHandle:allWindowns) {
    	     if(!windownHandle.equals(currentWindown)) {
    	    	 driver.switchTo().window(windownHandle);
    			break;
    		}}
    	
    	//9. Verify title của cửa sổ: Products Comparison List - Magento Commerce
    	//Assert.assertEquals(driver.findElement(By.xpath("////head//child::title")).getText(),"Products Comparison List - Magento Commerce");
    	
    	//10.Đóng cửa sổ, chuyển về Parent Window
    	driver.switchTo().window(currentWindown);
    	
    	//11. Nhấn nút Clear All và accept Alert
    	driver.findElement(By.xpath("//a[text()='Clear All']")).click();
    	Alert alert = driver.switchTo().alert();
    	alert.accept();
    	
    	//12. Verify message: The comparison list was cleared.
    	WebDriverWait explicitWait2 = new WebDriverWait(driver,Duration.ofSeconds(7));
    	explicitWait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='success-msg']")));
    	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//child::ul//descendant::li//descendant-or-self::span")).getText(), "The comparison list was cleared.");
    }
	
	@AfterClass
	 public void afterClass() {
	  driver.quit();
	 }


}
