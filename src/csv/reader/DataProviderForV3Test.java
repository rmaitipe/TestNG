package csv.reader;

import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import test.dro.ZipPairDRO;

public class DataProviderForV3Test{

	
	/*The purpose of these package is to understand how data is setup for DataProvider Class
	 * test method declares that its data should be supplied by the DataProvider named "getdata"
	 * Here the data is converted to an object and passed
	 */ 
	
	Logger logger = LoggerFactory.getLogger(DataProviderForV3Test.class);
	
	@Test(dataProvider="getData")
	public void setData(ZipPairDRO dro)
	{
		logger.info("your key is::"+dro.getKey());
		logger.info("your description is::"+dro.getDescription());
		logger.info("your inputpair is::"+dro.getInputZips());
		logger.info("your outputpair is::"+dro.getExpectedZips());
	}
	
	@DataProvider
	public ZipPairDRO[] getData() throws IOException
	{
		//Rows - Number of test cases.
		//Columns - Number of parameters in test data.
		ZipPairDRO[] list=new ZipPairDRO[1];
		try(CSVReader csvReader = new CSVReader(new FileReader("resources/test/v3/completev3.csv"), ',')) {
			String[] csvData =null;
			while ((csvData =csvReader.readNext()) !=null) {
		    	for (int i=0;i<1;i++) {
		    		list[i] =new ZipPairDRO(csvData[0],csvData[1],csvData[2],csvData[3]);
		    	}
		    } 
		} catch (Exception ex) {
	        ex.printStackTrace();
	    }
	return list;
	}
    
}