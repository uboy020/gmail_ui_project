import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailSignInTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Usman\\Documents\\tools and extensions\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 20);
	}
	
	@Test
	public void gmailLoginShouldBeSuccessful() throws InterruptedException{
		driver.get("http://gmail.com");
		WebElement usernameTextbox = driver.findElement(By.id("Email"));
		usernameTextbox.clear();
		usernameTextbox.sendKeys("021cleo@gmail.com");
		
		WebElement nextButton = driver.findElement(By.id("next"));
		nextButton.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
		
		WebElement passwordTextbox = driver.findElement(By.id("Passwd"));
		passwordTextbox.clear();
		passwordTextbox.sendKeys("cleo1234");
		
		WebElement signInButton = driver.findElement(By.id("signIn"));
		signInButton.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
		Assert.assertTrue("Inbox should exist", driver.findElements(By.partialLinkText("Inbox")).size() > 0);
		
		WebElement profileButton = driver.findElement(By.cssSelector(".gb_8a.gbii"));
		profileButton.click();
		
		WebElement signOut = driver.findElement(By.id("gb_71"));
		signOut.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signIn")));
		Assert.assertTrue("Should show sign in page", driver.findElements(By.id("signIn")).size() > 0);
		
		
		
		
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
	
}
