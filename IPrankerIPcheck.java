package Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.*;
import java.time.Duration;


public class IPrankerIPcheck {
    public static void main(String[] args) throws Exception {
      
        // Input & output files
        File inputFile = new File("IPranker_IPs.txt");        // input file with IPs
        File outputFile = new File("IP_Ranker_Result.txt");   // output file

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        String ip;
        while ((ip = reader.readLine()) != null) {
            ip = ip.trim();
            if (ip.isEmpty()) continue;

            System.out.println("Checking: " + ip);
            driver.get("https://ipranker.com/");
           // driver.manage().window().maximize();
            Thread.sleep(1000);
             WebElement input=driver.findElement(By.xpath("//input[contains(@placeholder,'Enter IP address to analyze (e.g., 192.168.1.1)')]"));
            input.clear();
            input.sendKeys(ip);
             Thread.sleep(1000);
             
             
             
             WebElement analyzeBtn = wait.until(ExpectedConditions.elementToBeClickable(
            		    By.xpath("//button[normalize-space()='Analyze IP']")
            		));
            		analyzeBtn.click();
            		Thread.sleep(4000);
            

            String result;
            try {
                // Adjust this XPath/CSS based on site structure  

                result = driver.findElement(By.xpath("//div[@class='lg:col-span-2 grid grid-cols-1 md:grid-cols-2 gap-4']//div[1]//div[1]//span[1]")).getText();// new comment
                System.out.println("Ip result"+"=>"+result);
            } catch (Exception e) {
                result = "Not Found / Error";
            }

            // Write to output file
            writer.write(ip + " => " + result);
            writer.newLine();
        }

        reader.close();
        writer.close();
        //driver.quit();

        System.out.println("✅ Done! Results saved in results.txt");
    }

	
	}

	
		
	
