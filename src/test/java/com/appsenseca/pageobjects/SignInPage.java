package com.appsenseca.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

	public void fillInUsername(WebDriver driver, String s) {
		WebElement usernameTextbox = driver.findElement(By.id("Email"));
		usernameTextbox.clear();
		usernameTextbox.sendKeys(s);
		WebElement nextButton = driver.findElement(By.id("next"));
		nextButton.click();		
	}

	public void fillInPassword(WebDriver driver, String s) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
		WebElement passwordTextbox = driver.findElement(By.id("Passwd"));
		passwordTextbox.clear();
		passwordTextbox.sendKeys(s);	
	}

	public static EmailHomePage clickSignIn(WebDriver driver) {
		WebElement signInButton = driver.findElement(By.id("signIn"));
		signInButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
		return PageFactory.initElements(driver, EmailHomePage.class);
	}

}
