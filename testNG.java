package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class testNG {
	
	RemoteWebDriver driver= new ChromeDriver();

	@Test (priority=1)
	void openApp(){
	
		driver.get("https://testautomationpractice.blogspot.com/");
		System.out.print("false");		
	}
	@Test (priority=2)
	void fillName(){
		
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("akash");
	}
	

}

