package com.appsenseca.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.appsenseca.util.WebUtil;

public class EmailViewPage {

	public String getEmailSubject(WebDriver driver) {
		return WebUtil.getElementText(driver, By.cssSelector("h2[class='hP']"));	
	}

	public String getEmailBody(WebDriver driver) {
		return WebUtil.getElementText(driver,By.cssSelector("div[class='nH aHU'] div[dir = 'ltr']"));
	}

}
