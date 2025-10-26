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
import org.openqa.selenium.By.ByXPath;
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
	        driver.get("https://dev.ipranker.com/");// GO TO WEBSITE
	         
	         // driver.manage().window().maximize();
	          Thread.sleep(1000);
	          driver.findElement(By.xpath("//a[normalize-space()='Dashboard Login']")).click();
	          Thread.sleep(1000);
	          js.executeScript("document.body.style.zoom='50%'");//ZOOM OUT THE  SCREEN
	          driver.findElement(By.xpath("//input[@placeholder='your@email.com']")).click();
	          Thread.sleep(1000);
	          driver.findElement(By.xpath("//input[@placeholder='your@email.com']")).sendKeys("sikewej413@haotuwu.com");//FILL USER NAME
	          
	          driver.findElement(By.xpath("//input[@placeholder='••••••••']")).click();
	          Thread.sleep(1000);
	          driver.findElement(By.xpath("//input[@placeholder='••••••••']")).sendKeys("Akku@0252");//FILL PASSWORD
	          driver.findElement(By.xpath("//input[@id='rememberMe']")).click();
	          driver.findElement(By.xpath("//button[normalize-space()='Sign in']")).click();//CLICK ON SIGN IN BUTTON
	          Thread.sleep(1000);
	          //driver.findElement(By.xpath("//a[@href='/' and contains(@class,'items-center')]")).click();//CLICK ON IOP RANKER ICON
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

		
		FileInputStream file = new FileInputStream ("C:\\Users\\Akash Tiwari\\eclipse-workspace\\Selenium\\input.xlsx");
		                             // for workpc-- C:\\Ecslipse-Data\\input.xlsx
		XSSFWorkbook WB= new XSSFWorkbook(file);
	
		XSSFSheet sheet =WB.getSheetAt(0);
		
		int TOTALROWS=sheet.getLastRowNum();
		int TOTALCELLS=sheet.getRow(0).getLastCellNum();
		
		System.out.println("TOTAL NUMER OF IPS-->"+TOTALROWS);
		System.out.println("TOTAL NUMER OF COLUMNS-->"+TOTALCELLS);
		
		
  for(int r = 0;r<=TOTALROWS;r++) {
	  XSSFRow CurrentRow= sheet.getRow(r);
	  
	  
	  for (int c=0;c<TOTALCELLS;c++) {
		  
		  XSSFCell cell = CurrentRow.getCell(c);
		  if (cell == null) continue;
		  
	
		 String ips=(cell.toString());
		 
		 System.out.println(ips+"\t");
		 
	
		 
		  
          
           WebElement input=driver.findElement(By.xpath("//input[contains(@placeholder,'Enter IP address to analyze (e.g., 192.168.1.1)')]"));
          input.clear();
          input.sendKeys(ips);
           Thread.sleep(2000);
           
           
           
           WebElement analyzeBtn = wait.until(ExpectedConditions.elementToBeClickable(
          		    By.xpath("//button[normalize-space()='Analyze IP']")
          		));
          		analyzeBtn.click();
          		Thread.sleep(6000);
          

          String result;
          try {
              // Adjust this XPath/CSS based on site structure  

              result = driver.findElement(By.xpath("//div[@class='lg:col-span-2 grid grid-cols-1 md:grid-cols-2 gap-4']//div[1]//div[1]//span[1]")).getText();// new comment
              System.out.println("Ip result"+"=>"+result);
          } catch (Exception e) {
              result = "Not Found / Error";
          }
		 
		 
		  XSSFCell resultCell = CurrentRow.getCell(1);
          if (resultCell == null) 
              {
              resultCell =CurrentRow.createCell(1);
             
              }
          resultCell.setCellValue(result);
          
          System.out.println("result saved successfully for-->"+ips);
	  }
		}
  file.close();
  
  FileOutputStream out=new FileOutputStream("C:\\Users\\Akash Tiwari\\eclipse-workspace\\Selenium\\input.xlsx");
  
  WB.write(out);
  out.close();
  WB.close();

}

	


}

