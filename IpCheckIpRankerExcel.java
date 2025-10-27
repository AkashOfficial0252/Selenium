package Selenium;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IpCheckIpRankerExcel {
	public static void main(String[] args) throws IOException, InterruptedException  {
		
		
		WebDriver driver = new ChromeDriver();
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        
	     //========================================================= GO TO WEBSITE=================================================================
	        
	        driver.get("https://dev.ipranker.com/");
	        //========================================================= LOG IN=================================================================
	         // driver.manage().window().maximize();
	          Thread.sleep(1000);
	          driver.findElement(By.xpath("//a[normalize-space()='Dashboard Login']")).click(); // CLICK ON LOGIN-DASHBOARD
	          Thread.sleep(1000);
	          js.executeScript("document.body.style.zoom='50%'");//ZOOM OUT THE  SCREEN
	          driver.findElement(By.xpath("//input[@placeholder='your@email.com']")).click(); // CLICK ON USERNAME 
	          Thread.sleep(1000);
	          driver.findElement(By.xpath("//input[@placeholder='your@email.com']")).sendKeys("sikewej413@haotuwu.com");//FILL USER NAME
	          
	          driver.findElement(By.xpath("//input[@placeholder='••••••••']")).click();//CLICK ON  PASSWORD
	          Thread.sleep(1000);
	          driver.findElement(By.xpath("//input[@placeholder='••••••••']")).sendKeys("Akku@0252");//FILL PASSWORD
	          driver.findElement(By.xpath("//input[@id='rememberMe']")).click();
	          driver.findElement(By.xpath("//button[normalize-space()='Sign in']")).click();//CLICK ON SIGN IN BUTTON
	          Thread.sleep(1000);	          
	          try {
	        	    WebElement toast = driver.findElement(By.cssSelector(".toast-container"));
	        	    js.executeScript("arguments[0].style.display='none';", toast);
	        	} catch (Exception e) {}

	        	WebElement logo = wait.until(
	        	        ExpectedConditions.elementToBeClickable(
	        	            By.xpath("//img[@alt='IP Ranker']")
	        	        )
	        	);
	        	logo.click();

	          js.executeScript("document.body.style.zoom='30%'");//ZOOM OUT THE SCREEN
	          Thread.sleep(1000);
	          
	          //=========================================================READ DATA FROM FILE=================================================================

	    // FileInputStream file = new FileInputStream ("C:\\\\Users\\\\Akash Tiwari\\\\eclipse-workspace\\\\Selenium\\\\input.xlsx"); //FOR HP
		FileInputStream file = new FileInputStream ("C:\\Users\\akash Work\\ECLIPSE-WORKSPACE-1\\excel file for ip ranker.xlsx"); // FRE WORK
		                           
		
		XSSFWorkbook WB= new XSSFWorkbook(file);
		XSSFSheet sheet =WB.getSheetAt(0);
		
		int TOTALROWS=sheet.getLastRowNum();
		int TOTALCELLS=sheet.getRow(0).getLastCellNum();
		
		System.out.println("TOTAL NUMER OF IPS-->"+TOTALROWS);// PRINT TOTAL ROWS
		System.out.println("TOTAL NUMER OF COLUMNS-->"+TOTALCELLS); //PRINT TOTAL CELLS
		
		
  for(int r = 1;r<=TOTALROWS;r++) {
	  XSSFRow CurrentRow= sheet.getRow(r);
	  XSSFCell cell = CurrentRow.getCell(0);// GET CELL DATA FROM ROW 1
	  String ips=(cell.toString());//CONVERT  DATA IN STRING FORM
	  System.out.println(ips+"\t"); 
		 
		  //========================================================= FILL IP ON IP BOX=================================================================	
		 WebElement input=driver.findElement(By.xpath("//input[contains(@placeholder,'Enter IP address to analyze (e.g., 192.168.1.1)')]"));
          input.clear();
          input.sendKeys(ips);
          Thread.sleep(2000);
           
           WebElement analyzeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Analyze IP']")));
           analyzeBtn.click();
           Thread.sleep(6000);
          
          		 //=========================================================GET PROXY RESULT ================================================================= 
          String IPresult;
          try {
        	  IPresult = driver.findElement(By.xpath("//div[@class='lg:col-span-2 grid grid-cols-1 md:grid-cols-2 gap-4']//div[1]//div[1]//span[1]")).getText();// new comment
              System.out.println("Proxy Result"+"-->"+IPresult);
              } 
          catch (Exception e) {
        	  IPresult = "Not Found / Error";
              }
          
          
          //=========================================================WRITE PROXY RESULTS ================================================================= 
          
          XSSFCell resultCell = CurrentRow.getCell(1);
          if (resultCell == null) 
              {
              resultCell =CurrentRow.createCell(1);
              }
          resultCell.setCellValue(IPresult);
          
          //=========================================================GET BOT RESULT================================================================= 
          String botResult;
           	try {
           		botResult =driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/section[1]/div[2]/div[2]/div[3]/div[3]/div[1]/div[2]/div[3]/div[1]/span[1]")).getText();
           		System.out.println("Bot Result--"+"-->"+botResult);    
           	    }
           	catch(Exception e){
           		botResult="Not Found / Error";
           		
           	}
		 
          //=========================================================WRITE  BOT RESULTS ================================================================= 
		     XSSFCell botResultCell=CurrentRow.getCell(2);
		     if(botResultCell==null) {
		    	 botResultCell = CurrentRow.createCell(2);
		     }
		     botResultCell.setCellValue(botResult);
		     
		     
		     
		     
          
          //System.out.println("result saved successfully for-->"+ips);
          }
         file.close();
         
         
         
  FileOutputStream output=new FileOutputStream("C:\\Users\\akash Work\\ECLIPSE-WORKSPACE-1\\excel file for ip ranker.xlsx"); // FOR WORK
  //FileOutputStream output=new FileOutputStream("C:\\Users\\Akash Tiwari\\eclipse-workspace\\Selenium\\input.xlsx"); // FOR HP
  WB.write(output);                               
  output.close();
  WB.close();
  

}
}

