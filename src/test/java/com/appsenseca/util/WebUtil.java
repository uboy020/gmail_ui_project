package com.appsenseca.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.appsenseca.pageobjects.SignInPage;

public class WebUtil {

	public static SignInPage goToSignInPage(WebDriver driver) {
		driver.get("http://gmail.com");
		return PageFactory.initElements(driver, SignInPage.class);
	}
	
}
