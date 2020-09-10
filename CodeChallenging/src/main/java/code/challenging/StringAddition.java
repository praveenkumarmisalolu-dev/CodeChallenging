package code.challenging;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import code.util.UtilMethods;

/**
 * @author praveen 
 * 
 * It provide a logic for String addition 
 * example : "15+12-13.2+11" = 24.8
 * 
 * steps: 
 *  1. Split the string using operator. 
 *  2. Storing in collection with there signature.
 *  3. Finally adding all values 
 *  
 *
 */


public class StringAddition {

	public static void main(String[] args) throws IOException {

		Logger loggerInstance = UtilMethods.getLoggerInstance(StringAddition.class);

		String stringData = UtilMethods.getPropertiesFile("String_Data");
		loggerInstance.info("************************************************************");

		loggerInstance.info("String data --->" + stringData);

		String[] split = stringData.split(UtilMethods.getPropertiesFile("postive_symbol"));

		ArrayList<Double> negative = new ArrayList<>();
		ArrayList<Double> positve = new ArrayList<>();

		for (String string : split) {
			if (string.contains(UtilMethods.getPropertiesFile("negative_symbol"))) {

				double process = new StringAddition().process(string);

				negative.add(process);

			} else {

				positve.add(Double.parseDouble(string));

			}

		}

		loggerInstance.info("Postive addition value -----> " + positve);
		loggerInstance.info("Negative addition value -----> " + negative);
		positve.addAll(negative);

		double finalvalue = 0;

		for (Double double1 : positve) {

			finalvalue = finalvalue + double1;

		}
		loggerInstance.info("Postive addition -  Negative additione -----> " + finalvalue);
		loggerInstance.info("************************************************************");
	}

	
	// Method process the negative values and return a double value
	public double process(String data) throws IOException {

		String[] split = data.split(UtilMethods.getPropertiesFile("negative_symbol"));
		double summing = 0;

		double finalProcess = 0;

		for (int i = 1; i < split.length; i++) {

			summing = summing + Double.parseDouble(split[i]);
		}

		finalProcess = Double.parseDouble(split[0]) - summing;

		return finalProcess;
	}

}
