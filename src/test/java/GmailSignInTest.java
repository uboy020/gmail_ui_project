import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.appsenseca.categories.Critical;
import com.appsenseca.categories.Major;
import com.appsenseca.pageobjects.ComposeEmailPage;
import com.appsenseca.pageobjects.EmailHomePage;
import com.appsenseca.pageobjects.EmailViewPage;
import com.appsenseca.pageobjects.SignInPage;
import com.appsenseca.util.WebUtil;

public class GmailSignInTest {
	WebDriver driver;
	
	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Usman\\Documents\\tools and extensions\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Category({Critical.class})
	@Test
	public void gmailLoginShouldBeSuccessful() throws InterruptedException{
		//1.Go to Gmail website
		SignInPage signInPage = WebUtil.goToSignInPage(driver);
		//2. Fill in username
		signInPage.fillInUsername(driver, "021cleo@gmail.com");
		//3. Fill in password
		signInPage.fillInPassword(driver, "cleo1234");
		//4. Click sign in
		EmailHomePage emailHomePage = signInPage.clickSignIn(driver);
		//5. Verify sign in
		Assert.assertTrue("Inbox should exist",emailHomePage.isInboxExist(driver));
		
		//6. Sign out
		signInPage = emailHomePage.signOut(driver);
		//7. verify user did sign out
		Assert.assertTrue("Should show sign in page", signInPage.isSignInButtonExist(driver));
	}
	
	@Category({Major.class})
	@Test
	public void gmailSendAndReceiveEmailTest() {
		//1. Sign in
		SignInPage signInPage = WebUtil.goToSignInPage(driver);
		signInPage.fillInUsername(driver, "021cleo@gmail.com");
		signInPage.fillInPassword(driver, "cleo1234");
		EmailHomePage emailHomePage = signInPage.clickSignIn(driver);
		Assert.assertTrue("Inbox should exist", emailHomePage.isInboxExist(driver));
		//2. Compose email
		ComposeEmailPage composeEmailPage = emailHomePage.clickComposeButton(driver);

		//3. Fill in recipient
		composeEmailPage.fillInRecipent(driver, "021cleo@gmail.com");
		
		//4. Fill in subject
		final String subject = "Test Gmail Send Email";
		composeEmailPage.fillInSubject(driver, subject);

		//5. Fill in email body
		final String body = "Hello Testers! Good Morning!";
		composeEmailPage.fillInEmailBody(driver, body);

		//6. Click send
		emailHomePage = composeEmailPage.clickSend(driver);

		//7. Click inbox again
		emailHomePage.clickInboxWithNewEmail(driver);
		
		//8. Click email
		EmailViewPage emailViewPage = emailHomePage.clickNewEmail(driver);

		//9. Verify the email subject and email body is correct
		String actualSubject = emailViewPage.getEmailSubject(driver);
		Assert.assertEquals("Email Subject should be the same", subject, actualSubject);
		
		String actualBody = emailViewPage.getEmailBody(driver);
		Assert.assertEquals("Email Body should be the same", body, actualBody);
		
		//10.logout
		signInPage = emailHomePage.signOut(driver);
		Assert.assertTrue("Should show sign in page", signInPage.isSignInButtonExist(driver));
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
	
}
