package Selenium;;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;

public class IpCheckIpRankerExcel {
    public static void main(String[] args) {
        try {
            // ========== STEP 1: SETUP FILE PATHS ==========
            String inputFilePath = "input_ips.xlsx";   // Input Excel with IPs (column name: IP_address)
            String outputFilePath = "Output_ips.xlsx"; // Output Excel to store results

            // ========== STEP 2: OPEN INPUT EXCEL ==========
            FileInputStream fis = new FileInputStream(inputFilePath);
            Workbook inputWorkbook = new XSSFWorkbook(fis);
            Sheet inputSheet = inputWorkbook.getSheetAt(0); // Read first sheet
            fis.close();

            // ========== STEP 3: CREATE OUTPUT EXCEL ==========
            Workbook outputWorkbook = new XSSFWorkbook();
            Sheet outputSheet = outputWorkbook.createSheet("Results");

            // Create header row for output sheet
            Row header = outputSheet.createRow(0);
            header.createCell(0).setCellValue("IP_address");
            header.createCell(1).setCellValue("Proxy Status");
            header.createCell(2).setCellValue("Bot Status");
            header.createCell(3).setCellValue("Tor Status");

            // ========== STEP 4: SETUP SELENIUM DRIVER ==========
            WebDriver driver = new ChromeDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            driver.get("https://dev.ipranker.com/");
            
            

            driver.findElement(By.xpath("//a[normalize-space()='Dashboard Login']")).click();
             WebElement emailfiller=driver.findElement(By.xpath("//input[@placeholder='your@email.com']"));
             emailfiller.sendKeys("Akashji0252@gmail.com");
            
             WebElement PassWord=driver.findElement(By.xpath("//input[@placeholder='••••••••']"));
             PassWord.sendKeys("Akku@0252");
             driver.findElement(By.xpath("//input[@id='rememberMe']")).click();//click on remember me
             driver.findElement(By.xpath("//button[normalize-space()='Sign in']")).click();//click on sign-in button
           Thread.sleep(6000);
             
           driver.findElement(By.xpath("//span[normalize-space()='IP Ranker']")).click();

             
        // js.executeScript("document.body.style.zoom='35%'")::;
            Thread.sleep(1000);

            // ========== STEP 5: READ EACH IP AND CHECK ==========
            int rowCount = inputSheet.getLastRowNum();
            for (int i = 1; i <= rowCount; i++) {  // skip header row
                Row row = inputSheet.getRow(i);
                if (row == null) continue;

                Cell ipCell = row.getCell(0);
                if (ipCell == null) continue;

                String ipAddress = ipCell.getStringCellValue().trim();
                if (ipAddress.isEmpty()) continue;

                System.out.println("🔍 Checking IP: " + ipAddress);
                
              

                // Open IP Ranker website
             

                // Enter IP in input field
                WebElement input = driver.findElement(By.xpath("//input[contains(@placeholder,'Enter IP address')]"));
                input.clear();
                input.sendKeys(ipAddress);
                Thread.sleep(1000);

                // Click Analyze button
                WebElement analyzeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space()='Analyze IP']")));
                analyzeBtn.click();
                Thread.sleep(6000);

                // Try to get Proxy result text
                String proxyResult;
                try {
                    WebElement proxyElement = driver.findElement(
                            By.xpath("//div[contains(text(),'Proxy')]/following-sibling::div//span"));
                    proxyResult = proxyElement.getText();
                } catch (Exception e) {
                    proxyResult = "Not Found / Error";
                }

                System.out.println("Proxy Status for " + ipAddress + " => " + proxyResult);

                // ========== STEP 6: WRITE RESULT TO OUTPUT SHEET ==========
                Row outputRow = outputSheet.createRow(i);
                outputRow.createCell(0).setCellValue(ipAddress);
                outputRow.createCell(1).setCellValue(proxyResult);
                outputRow.createCell(2).setCellValue("Pending"); // Placeholder for Bot
                outputRow.createCell(3).setCellValue("Pending"); // Placeholder for Tor
            }

            // ========== STEP 7: SAVE OUTPUT EXCEL ==========
            FileOutputStream fos = new FileOutputStream(outputFilePath);
            outputWorkbook.write(fos);
            fos.close();

            // Close workbooks and browser
            inputWorkbook.close();
            outputWorkbook.close();
            driver.quit();

            System.out.println("✅ Done! Results saved in " + outputFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
