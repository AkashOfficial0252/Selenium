package Selenium;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
public class IprankerIpModules {

	public static void main(String[] args) throws InterruptedException, IOException {
	String path=System.getProperty("user.dir")+"/input_ips.xlsx";
	
	FileInputStream fis = new FileInputStream(path);
	XSSFWorkbook wbk=new XSSFWorkbook(fis);
	XSSFSheet sheet=wbk.getSheet("Sheet1");
	int rowct =sheet.getLastRowNum();
	System.out.println(rowct);
	XSSFRow row;
	XSSFCell cl;
	String Data;
	DataFormatter fr=new DataFormatter();
for(int i=0;i<=i;i++) {
	row=sheet.getRow(i);
	cl=row.getCell(0);
	System.out.println("yhn");
	Data=fr.formatCellValue(cl);
	System.out.println(Data);
}
		
	}

}
