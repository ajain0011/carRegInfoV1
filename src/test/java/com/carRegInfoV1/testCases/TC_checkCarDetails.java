package com.carRegInfoV1.testCases;

import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.junit.Test;
import com.carRegInfoV1.pageObjects.carRegInfoPageObjects;

public class TC_checkCarDetails extends BaseClass {

	@Test
	public void fnCheckCarDetails() throws InterruptedException, IOException {
		carRegInfoPageObjects obj = new carRegInfoPageObjects(driver);
		List<String> CarRegNums=obj.readInputFile();
		obj.createOutputFile();
		for (int x = 0; x < CarRegNums.size(); x++) {
			System.out.print("\nGoing to check: " + CarRegNums.get(x)+"\n");
			driver.navigate().to(baseURL);
			Thread.sleep(3000);
			obj.fnEnterRegistration(CarRegNums.get(x));
			obj.fnClickButton();
			Thread.sleep(2000);

			String regi = obj.fnGetValueLabel(obj.lblRegistration);
			String make = obj.fnGetValueLabel(obj.lblMake);
			String model = obj.fnGetValueLabel(obj.lblModel);
			String color = obj.fnGetValueLabel(obj.lblColour);
			String year = obj.fnGetValueLabel(obj.lblYear);

			String line = regi + ',' + make + ',' + model + ',' + color + ',' + year;
			if (line.length() > 6) 
			{ obj.appendOutputFile(line); }
			else 
			{ obj.appendOutputFile(CarRegNums.get(x)+": is not a valid car number."); }
		}//for
		
		obj.compareCSV();
	}// fnCheckCarDetails
	
}// class
