package com.appsenseca.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.appsenseca.util.WebUtil;

public class SignInPage {

	public void fillInUsername(WebDriver driver, String s) {
		WebUtil.clearAndSendKeys(driver, By.id("Email"), s);
		WebUtil.click(driver, By.id("next"));		
	}

	public void fillInPassword(WebDriver driver, String s) {
		WebUtil.waitForElementVisible(driver, By.id("Passwd"));
		WebUtil.clearAndSendKeys(driver, By.id("Passwd"), s);
	}

	public EmailHomePage clickSignIn(WebDriver driver) {
		WebUtil.click(driver, By.id("signIn"));
		
		WebUtil.waitForElementVisible(driver, By.partialLinkText("Inbox"));
		
		return PageFactory.initElements(driver, EmailHomePage.class);
	}
	
	public boolean isSignInButtonExist(WebDriver driver) {
		return WebUtil.isElementExist(driver, By.id("signIn"));
	}

}
