package test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ZipCodePair;
import com.ZipLimiter;
import com.opencsv.CSVReader;

import test.dro.ZipPairDRO;

/*
This v3 class is to Unit test the ZipLimiter class. 
*/
public class ZipLimiterTestv3 {
	
	Logger logger = LoggerFactory.getLogger(ZipLimiterTestv3.class);
	/*
	* These are the values to be compared against the data read from files in test case scenarios.
	* Called automatically before the Test class is run.
	*/
 	@BeforeClass
    public void setUp() {
 		logger.info("@Before - setUp");
    }
    
    @AfterClass
    public void tearDown() {
    	logger.info("@After - tearDown");
    }
    
	@DataProvider
	public ZipPairDRO[] getDataZip() throws IOException
	{
		//Rows - Number of times your test has to be repeated.
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
    
    /*
    * Runs test cases for different scenarios comparing size of the lists and content of the lists
    * Test cases: No Merge, Sorted Merge, UnSorted Merge, Merge With BadData
    */
    @Test(dataProvider = "getDataZip")
	public void zipLimiterDataTest(ZipPairDRO dro) {
		ZipLimiter zip = new ZipLimiter();
		List<ZipCodePair> outPutList = zip.testv3(dro.getInputZips());
		Assert.assertEquals(outPutList.toString().trim(),dro.getExpectedZips().trim(), "failure - expected result content match!");
	} 	
}
