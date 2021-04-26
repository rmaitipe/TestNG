package test;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ZipCodePair;
import com.ZipLimiter;

import test.dataprovider.DataProvider1;
import test.dro.ZipPairDRO;


/*
This v3 class is to Unit test the ZipLimiter class. 
*/
public class ZipLimiterTestv4 {
	
	Logger logger = LoggerFactory.getLogger(ZipLimiterTestv4.class);
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
    
    /*
    * Runs test cases for different scenarios comparing size of the lists and content of the lists
    * Test cases: No Merge, Sorted Merge, UnSorted Merge, Merge With BadData
    */
    @Test(dataProvider="getData", dataProviderClass=DataProvider1.class)
	public void zipLimiterDataTest(ZipPairDRO dro) {
		ZipLimiter zip = new ZipLimiter();
		List<ZipCodePair> outPutList = zip.testv3(dro.getInputZips());
		Assert.assertEquals(outPutList.toString().trim(),(dro.getExpectedZips()).trim(), "failure - expected result content match!");
	} 	
}
