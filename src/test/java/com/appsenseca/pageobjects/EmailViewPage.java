package com.appsenseca.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmailViewPage {

	public String getEmailSubject(WebDriver driver) {
		WebElement subjectArea = driver.findElement(By.cssSelector("h2[class='hP']"));
		return subjectArea.getText();	
	}

	public String getEmailBody(WebDriver driver) {
		WebElement bodyArea = driver.findElement(By.cssSelector("div[class='nH aHU'] div[dir = 'ltr']"));	
		
		return bodyArea.getText();
	}

}
