package com.appsenseca.pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.appsenseca.util.WebUtil;

public class EmailHomePage {

	public SignInPage signOut(WebDriver driver) {
		WebUtil.click(driver, By.cssSelector(".gb_9a.gbii"));
		
		WebUtil.click(driver, By.id("gb_71"));
		
		WebUtil.waitForElementVisible(driver, By.id("signIn"));
	
		return PageFactory.initElements(driver, SignInPage.class);
	}

	public boolean isInboxExist(WebDriver driver) {
		return WebUtil.isElementExist(driver, By.partialLinkText("Inbox"));
	}


	public ComposeEmailPage clickComposeButton(WebDriver driver) {
		WebUtil.click(driver, By.cssSelector("div[role='button'][gh = 'cm']"));
		
		return PageFactory.initElements(driver, ComposeEmailPage.class);
	}

	public void clickInboxWithNewEmail(WebDriver driver) {
		WebUtil.waitForElementVisible(driver, By.linkText("Inbox (4)"));
		WebUtil.click(driver, By.linkText("Inbox (4)"));
	}

	public EmailViewPage clickNewEmail(WebDriver driver) {
		WebUtil.waitForElementVisible(driver, By.cssSelector("div[class='y6'] b"));
		WebUtil.click(driver, By.cssSelector("div[class='y6'] b"));
		return PageFactory.initElements(driver, EmailViewPage.class);
	}
}
