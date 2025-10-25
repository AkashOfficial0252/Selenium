package Selenium;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
public class IprankerIpModules {

	public static void main(String[] args) throws InterruptedException, IOException {
	String path=System.getProperty("user.dir")+"/input_ips.xlsx";
	ChromeOptions opt=new ChromeOptions();
	opt.addArguments("--user-data-dir=C:\\seleniumprofile");
	ChromeDriver driver=new ChromeDriver(opt);
	driver.get("https://www.makemytrip.com/");
	FileInputStream fis = new FileInputStream(path);
	XSSFWorkbook wbk=new XSSFWorkbook(fis);
	XSSFSheet sheet=wbk.getSheet("Sheet1");
	int rowct =sheet.getLastRowNum();
	System.out.println(rowct);
	XSSFRow row;
	XSSFCell cl;
	String Data;
<<<<<<< HEAD
	DataFormatter fr=new DataFormatter();//class in apache poi for to fetch the  cel data
//for(int i=0;i<=1;i++) {
//	row=sheet.getRow(i);
//	cl=row.getCell(0);
//	System.out.println("yhn");
//	Data=fr.formatCellValue(cl);
//	XSSFCell clr=row.createCell(1);
//	clr.setCellValue((String)Data);
//	
//	System.out.println(Data);
//}
//fis.close();
//FileOutputStream fos=new FileOutputStream(path);
//wbk.write(fos);
//fos.close();


=======
	DataFormatter fr=new DataFormatter();
for(int i=0;i<=i;i++) {
	row=sheet.getRow(i);
	cl=row.getCell(0);
	System.out.println("yhn");
	Data=fr.formatCellValue(cl);
	System.out.println(Data);
}
>>>>>>> c2bad5e906ca36afb507616d746c040fe7fadfa5
		
	}

}
