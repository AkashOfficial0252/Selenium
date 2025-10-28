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
public class testing {
	public static void main(String[] args) throws InterruptedException, IOException {

	WebDriver driver = new ChromeDriver();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


		driver.get("https://dev.ipranker.com/");
	

	
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[normalize-space()='Dashboard Login']")).click();
		Thread.sleep(1000);

		js.executeScript("document.body.style.zoom='50%'");

		driver.findElement(By.xpath("//input[@placeholder='your@email.com']")).sendKeys("sikewej413@haotuwu.com");
		driver.findElement(By.xpath("//input[@placeholder='••••••••']")).sendKeys("Akku@0252");
		driver.findElement(By.id("rememberMe")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Sign in']")).click();

		Thread.sleep(2000);

		try {
			WebElement toast = driver.findElement(By.cssSelector(".toast-container"));
			js.executeScript("arguments[0].style.display='none';", toast);
		} catch (Exception e) {
		}

		WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='IP Ranker']")));
		logo.click();

		js.executeScript("document.body.style.zoom='30%'");
		Thread.sleep(1500);
	//void login ends here


		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/IP-Ranker ips data.xlsx");
		XSSFWorkbook WB = new XSSFWorkbook(file);
		XSSFSheet sheet = WB.getSheetAt(0);

		int TOTALROWS = sheet.getLastRowNum();
		int TOTALCELLS = sheet.getRow(0).getLastCellNum();

		System.out.println("TOTAL NUMER OF IPS--> " + TOTALROWS);
		System.out.println("TOTAL NUMER OF COLUMNS--> " + TOTALCELLS);

		for (int r = 0; r <= TOTALROWS; r++) {
			XSSFRow CurrentRow = sheet.getRow(r);
				XSSFCell cell = CurrentRow.getCell(1);
				if (cell == null)
					continue;

				String ips = cell.toString();
				System.out.println(ips);

				WebElement input = driver.findElement(By.xpath("//input[contains(@placeholder,'Enter IP address')]"));
				input.clear();
				input.sendKeys(ips);
				Thread.sleep(2000);

				WebElement analyzeBtn = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//button[normalize-space()='Analyze IP']")));
				analyzeBtn.click();

				Thread.sleep(6000);

				String result;

				try {
					result = driver.findElement(By.xpath(
							"(//div[contains(@class,'grid grid-cols-1 md:grid-cols-2')])[1]//span[contains(@class,'font-bold')]"))
							.getText();
				} catch (Exception e) {
					result = "Not Found / Error";
				}

				XSSFCell resultCell = CurrentRow.getCell(1);
				if (resultCell == null) {
					resultCell = CurrentRow.createCell(1);
				}
				resultCell.setCellValue(result);

				System.out.println("Result saved successfully for --> " + ips);
			
		}

		file.close();
		FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/IP-Ranker ips data.xlsx");
		WB.write(out);
		out.close();
		WB.close();
	//void code ends 
}//public class ends here
}
