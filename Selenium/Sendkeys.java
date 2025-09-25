package Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class Sendkeys {
	
	public static void main (String[] args) throws InterruptedException {
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	
	driver.get("https://sandbox.quickpollindia.com/dashboard");
	WebElement Name = driver.findElement(By.className("header-login-btn"));
	Name.click();
	driver.findElement(By.name("email")).sendKeys("test12@gmail.com");
	
	driver.findElement(By.name("password")).sendKeys("12345678");
	driver.findElement(By.className("button-label-icon")).click();
	
	
	    
	//driver.close();
	
	//driver.close();
	}
} 
