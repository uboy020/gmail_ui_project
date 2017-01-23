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

import com.appsenseca.pageobjects.EmailHomePage;
import com.appsenseca.pageobjects.SignInPage;
import com.appsenseca.util.WebUtil;

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
		//1.Go to Gmail website
		SignInPage signInPage = WebUtil.goToSignInPage(driver);
		//2. Fill in username
		signInPage.fillInUsername(driver, "021cleo@gmail.com");
		//3. Fill in password
		signInPage.fillInPassword(driver, "cleo1234");
		//4. Click sign in
		EmailHomePage emailHomePage = SignInPage.clickSignIn(driver);
		//5. Verify sign in
		Assert.assertTrue("Inbox should exist",emailHomePage.isElementsExist(driver));
		
		//6. Sign out
		signInPage = emailHomePage.signOut(driver);
		//7. verify user did sign out
		Assert.assertTrue("Should show sign in page", emailHomePage.isSignInButtonExist(driver));
	}
	
	@Test
	public void gmailSendAndReceiveEmailTest() {
		//1. Sign in
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
		//2. Compose email
		WebElement composeButton = driver.findElement(By.cssSelector("div[role='button'][gh = 'cm']"));
		composeButton.click();
		//3. Fill in recipient
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea[name='to']")));
		WebElement toTextArea = driver.findElement(By.cssSelector("textarea[name='to']"));
		toTextArea.clear();
		toTextArea.sendKeys("021cleo@gmail.com");
		//4. Fill in subject
		WebElement subjectTextArea = driver.findElement(By.cssSelector("input[name='subjectbox']"));
		final String subject = "Test Gmail Send Email";
		subjectTextArea.clear();
		subjectTextArea.sendKeys(subject);
		//5. Fill in email body
		WebElement bodyTextArea = driver.findElement(By.cssSelector("div[aria-label='Message Body']"));
		final String body = "Hello Testers! Good Morning!";
		bodyTextArea.clear();
		bodyTextArea.sendKeys(body);
		//6. Click send
		WebElement sendButton = driver.findElement(By.cssSelector("div[data-tooltip-delay='800']"));
		wait.until(ExpectedConditions.visibilityOf(sendButton)).click();
		//7. Click inbox 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Inbox (4)")));
		WebElement inboxLinkage = driver.findElement(By.linkText("Inbox (4)"));
		inboxLinkage.click();
		//8. Click email
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='y6'] span[id=':6h'] b")));
		WebElement newEmail = driver.findElement(By.cssSelector("div[class='y6'] span[id=':6h'] b"));
		newEmail.click();
		//9. Verify the email subject and email body is correct
		WebElement subjectArea = driver.findElement(By.cssSelector("h2[class='hP']"));
		Assert.assertEquals("Email Subject should be the same", subject, subjectArea.getText());
		
		WebElement bodyArea = driver.findElement(By.cssSelector("div[class='nH aHU'] div[dir = 'ltr']"));
		Assert.assertEquals("Email Body should be the same", body, bodyArea.getText());
		//10.logout
		
		WebElement profileButton = driver.findElement(By.cssSelector(".gb_8a.gbii"));
		profileButton.click();
		
		WebElement signOut = driver.findElement(By.id("gb_71"));
		signOut.click();
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
	
}
