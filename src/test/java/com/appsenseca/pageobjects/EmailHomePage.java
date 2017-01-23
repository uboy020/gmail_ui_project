package com.appsenseca.pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailHomePage {

	public SignInPage signOut(WebDriver driver) {
		WebElement profileButton = driver.findElement(By.cssSelector(".gb_8a.gbii"));
		profileButton.click();
		WebElement signOut = driver.findElement(By.id("gb_71"));
		signOut.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signIn")));
		
		return PageFactory.initElements(driver, SignInPage.class);
	}

	public boolean isElementsExist(WebDriver driver) {
		return driver.findElements(By.partialLinkText("Inbox")).size() > 0;
	}

	public boolean isSignInButtonExist(WebDriver driver) {
		return driver.findElements(By.id("signIn")).size() > 0;
		
	}

}
