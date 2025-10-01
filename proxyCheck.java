package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;


public class proxyCheck {
    public static void main(String[] args) throws Exception {
      
        // Input & output files
        File inputFile = new File("ipcheck.txt");        // input file with IPs
        File outputFile = new File("ipresults.txt");   // output file

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        WebDriver driver = new ChromeDriver();

        String ip;
        while ((ip = reader.readLine()) != null) {
            ip = ip.trim();
            if (ip.isEmpty()) continue;

            System.out.println("Checking: " + ip);
            driver.get("https://ip-api.com/" + ip);
           // driver.manage().window().maximize();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
            Thread.sleep(1000);
            

            String result;
            try {
                // Adjust this XPath/CSS based on site structure  

                result = driver.findElement(By.xpath("//span[44]")).getText();// new comment
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
