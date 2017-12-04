package com.isoftstone.ipsa.recruitment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	WebDriver driver=null;
	public BaseClass()	{
		System.setProperty("webdriver.chrome.driver", "F:\\autoDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		Navigation navigation = driver.navigate();
		navigation.to("http://10.10.203.181/crmo/");
	}
	
	public void login(String username,String password){
		WebElement name =getElementById("Email");
		name.sendKeys(username);
		WebElement pwd=getElementById("Password");
		pwd.sendKeys(password);
		WebElement login =getElementByCss(".btn.btn-default");
		login.click();
		wait(5000);
	}
			
	public WebElement getElementById(String id)	{
		 return driver.findElement(By.id(id));
	}
	
	public  WebElement getElementByCss(String css) {
		return driver.findElement(By.cssSelector(css));
	}
	
	public  WebElement getElementByXPath(String path) {
		return driver.findElement(By.xpath(path));
	}
	
	public void wait(int milsecs){
		try{
			Thread.sleep(milsecs);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}
