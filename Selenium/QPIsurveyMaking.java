package Selenium.Selenium.Selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;



import java.util.HashMap;
import java.util.Map;
import java.time.Duration;


public class QPIsurveyMaking {
	public static void main(String[] args) throws InterruptedException {
		
		// Disable theChrome pop up of google password manager
		ChromeOptions options = new ChromeOptions();
		Map<String , Object> prefs =new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		prefs.put("profile.credentials_enable_service", false);
		 options.setExperimentalOption("prefs",prefs);
		
		WebDriver driver = new ChromeDriver(options); 
		// java script executor
		JavascriptExecutor js= (JavascriptExecutor) driver;
		
		// maximize the window
		driver.manage().window().maximize();
		
	
		// open URL on-Chrome
		driver.get("https://sandbox.quickpollindia.com/");
		
	
		driver.findElement(By.xpath("//button[normalize-space()='Log In']")).click();
		
		// Find and click email input field
		WebElement Login=driver.findElement(By.xpath("//input[@placeholder='Email Address']"));
	    Boolean isLoginEmail=Login.isDisplayed();
		System.out.println("login Email field is displayed-->"+" "+isLoginEmail);
		Login.sendKeys("test12@gmail.com");
		
		 // find and click password field
		 WebElement Password=driver.findElement(By.xpath("//input[@placeholder='Password']"));
		 Boolean isLoginPass = Password.isDisplayed();
		 System.out.println("login password field is displayed-->"+" "+isLoginPass);
		 Password.sendKeys("12345678");
	
		 // find and click login button
		  WebElement LoginButton=driver.findElement(By.xpath("//span[@class='label-text']"));
		   Boolean LoginButtonStatus=LoginButton.isDisplayed();
		   System.out.println("login Button displayed-->"+" "+LoginButtonStatus);
		   LoginButton.click();
		  		
		   // wait for 2 second
		   Thread.sleep(4000);
		   // find and click on new survey
		   WebElement createSurvey=driver.findElement(By.xpath("//*[text()='Create new']"));
		   Boolean createsurveyResult=createSurvey.isDisplayed();
		   System.out.println("creating survey option is displaying-->  "+createsurveyResult);
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		   createSurvey.click();
		   
		   //create new survey
		   WebElement newSurvey=driver.findElement(By.xpath("//div[@class='sidebar']//li[1]"));
		   Boolean newSurveyResult = newSurvey.isDisplayed();
		   System.out.println("new survey option displayed-->  "+newSurveyResult);
		   newSurvey.click();
		  Thread.sleep(1000);
		   // add free text question from left side bar
           WebElement FreeText=driver.findElement(By.xpath("//*[text()='Free Text']"));
		   Boolean FreeTextResult= FreeText.isDisplayed();
		   System.out.println("Free text option displayed-->  "+FreeTextResult);
		   FreeText.click();
		   
		   Thread.sleep(1000);
		   // write and  free text question  

		   driver.findElement(By.xpath("//p[text()='Click to enter a question']")).click();
		   driver.findElement(By.cssSelector("input[placeholder='Enter your question']")).sendKeys("Free text question no-1 page-1");
		   Thread.sleep(1000);
		   driver.findElement(By.xpath("//button[@class='brandPrimary button-md btn-color-white rightContentSaveBtn']")).click();
		   Thread.sleep(3000);// saver question 
		          					////////////////////////////////////////
		          					///////////////////////////////////////////
		          					///////////////////////////////////////////
		          					///////////////////////////////////////////
		          					///////////////////////////////////////////
		          					///////////////////////////////////////////
		          					///////////////////////////////////////////
		          					/////////////////set the title name////////////////////////
		   driver.findElement(By.xpath("//img[@style='cursor: pointer;']")).click();
		   Thread.sleep(2000);
		   //////////////////////////add question name////////////////////////////////
		   driver.findElement(By.xpath("//input[@placeholder='Enter survey title']")).sendKeys("Automated Survey 9");////////////////////////////////////////////////////////
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("//span[text()='Save']")).click();
		   Thread.sleep(2000);
		   
		   
		 //add multiple question from leftbar
		   
		   WebElement Multiple=driver.findElement(By.xpath("//div[text()='Multiple']"));
		   Boolean MultipleResult= FreeText.isDisplayed();
		   System.out.println("mcq option displayed-->  "+MultipleResult);
		  Multiple.click();
		   
		   // write question name 
		   driver.findElement(By.xpath("//p[text()='Click to enter a question']")).click();
		   driver.findElement(By.cssSelector("input[placeholder='Enter your question']")).sendKeys("Free text question no-2 page-1");
		   //write options name 
		   driver.findElement(By.xpath("//label[normalize-space()='Option text here']")).click();
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("//input[@class='radioEditInputField' or @placeholder='Option text here']")).sendKeys("radio option 1");
		   Thread.sleep(2000);
		   js.executeScript("window.scrollBy(0, 500)");
		   driver.findElement(By.xpath("//span[normalize-space()='Add']")).click();
		   driver.findElement(By.xpath("//label[normalize-space()='Option text here']")).click();
		   driver.findElement(By.xpath("//input[@class='radioEditInputField' or @placeholder='Option text here']")).sendKeys("radio option 2");
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("//span[normalize-space()='Add']")).click();
		   driver.findElement(By.xpath("//label[normalize-space()='Option text here']")).click();
		   driver.findElement(By.xpath("//input[@class='radioEditInputField' or @placeholder='Option text here']")).sendKeys("radio option 3");
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("//span[normalize-space()='Add']")).click();
		   driver.findElement(By.xpath("//label[normalize-space()='Option text here']")).click();
		   driver.findElement(By.xpath("//input[@class='radioEditInputField' or @placeholder='Option text here']")).sendKeys("radio option 4");
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("//div[@class='createSurveyRightContent']//button[@class='brandPrimary button-md btn-color-white rightContentSaveBtn']//span[@class='button-label-icon']")).click();// save radio MCQ question
		   Thread.sleep(1000);
			  js.executeScript("window.scrollBy(0, 200)");
		   // add Rating question
			  
			  
			  driver.findElement(By.xpath("//div[text()='Rating']")).click();
		   
		 //driver.quit();
		
	}

}
