package com.appsenseca.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.appsenseca.util.WebUtil;

public class ComposeEmailPage {

	public void fillInRecipent(WebDriver driver, String recipent) {
		WebUtil.waitForElementVisible(driver, By.cssSelector("textarea[name='to']"));
		
		WebUtil.clearAndSendKeys(driver, By.cssSelector("textarea[name='to']"), recipent);		
	}

	public void fillInSubject(WebDriver driver, String subject) {
		WebUtil.clearAndSendKeys(driver, By.cssSelector("input[name='subjectbox']"), subject);
	}

	public void fillInEmailBody(WebDriver driver, String bodyText) {
		WebUtil.clearAndSendKeys(driver,By.cssSelector("div[aria-label='Message Body']"), bodyText);		
	}

	public EmailHomePage clickSend(WebDriver driver) {
		WebUtil.click(driver, By.cssSelector("div[data-tooltip-delay='800']"));
		return PageFactory.initElements(driver, EmailHomePage.class);
	}

}
