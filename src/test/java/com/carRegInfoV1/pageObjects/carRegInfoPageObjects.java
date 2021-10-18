package com.carRegInfoV1.pageObjects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class carRegInfoPageObjects {
	WebDriver ldriver;

	public carRegInfoPageObjects(WebDriver rdriver)
	{
	ldriver=rdriver;
	PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(name="vrm")
	WebElement txtEnterRegistration;
	
	@FindBy(xpath="//button[text()='Free Car Check']")
	public WebElement btnFreeCarCheck;
	
	@FindBy(xpath="//dl[@class='jsx-3517272246 ']//dt[contains(text(),'Registration')]//following-sibling::dd[@class='jsx-3496807389']")
	public WebElement lblRegistration;
	
	@FindBy(xpath="//dl[@class='jsx-3517272246 ']//dt[contains(text(),'Make')]//following-sibling::dd[@class='jsx-3496807389']")
	public	WebElement lblMake;
	
	@FindBy(xpath="//dl[@class='jsx-3517272246 ']//dt[contains(text(),'Model')]//following-sibling::dd[@class='jsx-3496807389']")
	public WebElement lblModel;
	
	@FindBy(xpath="//dl[@class='jsx-3517272246 ']//dt[contains(text(),'Colour')]//following-sibling::dd[@class='jsx-3496807389']")
	public	WebElement lblColour;
	
	@FindBy(xpath="//dl[@class='jsx-3517272246 ']//dt[contains(text(),'Year')]//following-sibling::dd[@class='jsx-3496807389']")
	public	WebElement lblYear;
	
	
	public void fnEnterRegistration(String val)
	{
		txtEnterRegistration.sendKeys(val);	
		
	}
	
	
	public void fnClickButton()
	{
		btnFreeCarCheck.click();
	}
	
	
	public String fnGetValueLabel(WebElement we)
	{
		
		return we.getText();
	}
	
	public void createOutputFile() throws IOException, InterruptedException 
	{
		//REGISTRATION,MAKE,MODEL,COLOR,YEAR
		FileWriter fw1 = new FileWriter(System.getProperty("user.dir")+"//src//test//java//Output_Created1.txt", false);
		BufferedWriter writer1 = new BufferedWriter(fw1);
		writer1.write("REGISTRATION,MAKE,MODEL,COLOR,YEAR");
		writer1.newLine();
		writer1.close();
		fw1.close();
	}//createOutputFile
	
	public void appendOutputFile(String str) throws IOException, InterruptedException 
	{
		FileWriter fw2 = new FileWriter(System.getProperty("user.dir")+"//src//test//java//Output_Created1.txt", true);
		BufferedWriter writer2 = new BufferedWriter(fw2);
		writer2.write(str);
		writer2.newLine();
		Thread.sleep(1000);
		writer2.close();
		fw2.close();
	}//appendOutputFile
	
	public static ArrayList<String> readInputFile() throws FileNotFoundException,IOException, InterruptedException
	{
		File Inputfile1 = new File(System.getProperty("user.dir")+"//src//test//java/Input_write1.txt");
		Pattern pt = Pattern.compile("[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}");
		ArrayList<String> CarRegNums = new ArrayList<String>();
		Scanner sc = new Scanner(Inputfile1);
		String reg = null;	
		while (sc.hasNextLine()) {
			reg = sc.nextLine();			
			Matcher mt = pt.matcher(reg);
			while (mt.find()) {
				CarRegNums.add(mt.group());
			} // while (mt.find())
		} // while (sc.hasNextLine())
		sc.close();
		return CarRegNums;
	}//readInputFile
	
	
	public void compareCSV() throws IOException, InterruptedException
	{
		HashSet<String> f1= new HashSet<String> (FileUtils.readLines(new File(System.getProperty("user.dir")+"//src//test//java//Output_Created1.txt")));
		HashSet<String> f2= new HashSet<String> (FileUtils.readLines(new File(System.getProperty("user.dir")+"//src//test//java//Expected_Output.txt")));
		f1.removeAll(f2);
		System.out.println(f1.toString());
		createResultFile(f1.toString());
	}//compareCSV
	
	public void createResultFile(String str1) throws IOException, InterruptedException 
	{
		FileWriter fw = new FileWriter(System.getProperty("user.dir")+"//src//test//java//ExecutionResult.txt", false);
		BufferedWriter writer = new BufferedWriter(fw);
		writer.write(str1);
		writer.newLine();
		writer.close();
		fw.close();
	}//createResultFile
	
}