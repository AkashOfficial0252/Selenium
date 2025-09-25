package Selenium.Selenium.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class testing {
    public static void main(String[] args) {

        // Step 1: Set ChromeDriver path
      
        // Step 2: Set up ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // Replace these paths with your actual profile path
        String userProfilePath = "C:\\Users\\akash Work\\AppData\\Local\\Google\\Chrome\\User Data\\";
        String profileDirectory = "Profile1"; // or "Default"

        options.addArguments("user-data-dir=" + userProfilePath);
        options.addArguments("profile-directory=" + profileDirectory);

        // Step 3: Launch Chrome with that profile
        WebDriver driver = new ChromeDriver(options);

        // Step 4: Open any initial page
        driver.get("https://sandbox.quickpollindia.com/dashboard");

        // Step 5: Open a new tab
        driver.switchTo().newWindow(WindowType.TAB);

        // Step 6: Open your target URL in the new tab
        String targetUrl = "https://sandbox.quickpollindia.com/create-form/survey/editor/7560998e-6fd9-4666-a03f-39b6c2637295"; // Change this
        driver.get(targetUrl);

        System.out.println("Opened Chrome with profile and navigated to: " + targetUrl);
    }
}
