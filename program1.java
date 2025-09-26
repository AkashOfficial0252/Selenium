package Selenium;

import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class program1 {

	public static void main(String[] args) {
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.opencart.com/");
		
		
		String Title=driver.getTitle();
		
		System.out.println(Title);
		if (Title.equals("OpenCart - Open Source Shopping Cart Solution")) {
			System.out.println("title name varified");
		}
		else{
		System.out.println("title name unvarified");}

			
		List <WebElement> anchor=driver.findElements(By.tagName("a"));
		System.out.println("no of anchor tags"+" =>" +anchor.size());
		
		List<WebElement> imgTags=driver.findElements(By.tagName("img"));
		System.out.println("Number of image tag on web page"+"=>"+imgTags.size());
		
		driver.findElement(By.linkText("Features")).click();
		
	System.out.println("no of anchor tags"+" =>" +anchor.size());
	
	
		
		List<WebElement> imgtags=driver.findElements(By.tagName("img"));
		System.out.println("Number of image tag on web page"+"=>"+imgtags.size());
		
		List <WebElement> Anchor=driver.findElements(By.tagName("a"));
		System.out.println("no of anchor tags"+" =>" +Anchor.size());
		
		driver.findElement(By.xpath("//ul[@class='nav navbar-nav']//a[normalize-space()='Download']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='previous releases']")).click();
		
		WebElement Driver=driver.findElement(By.xpath("//h2[normalize-space()='How to install OpenCart']"));
		String value =Driver.getText();
		 System.out.println(value);
		 
		 
		//true result on displayed
	    WebElement result=driver.findElement(By.xpath("//h2[normalize-space()='How to install OpenCart']"));
		boolean result2 = result.isDisplayed();
		System.out.println(result2);	
		
		 driver.findElement(By.xpath("//a[text()='Marketplace']")).click();
		 //driver.navigate().back();
		 
		 
		 //chained x_path
		 
		 
		 driver.close();

	}
	}


