package Selenium;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class cchange {

	public static void main(String[] args) {
		 ChromeOptions opt=new ChromeOptions();
opt.addArguments("--user-data-dir=C:\\seleniumprofile");

ChromeDriver dr=new ChromeDriver(opt);
dr.get("https://www.google.com/");

}
}