package test;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ZipCodePair;
import com.ZipLimiter;


/*
This v2 class is to Unit test the ZipLimiter class. 
*/
public class ZipLimiterTestv2 {

	List<ZipCodePair> expectedtest1;
	List<ZipCodePair> expectedtest2;
	List<ZipCodePair> expectedtest3;
	List<ZipCodePair> expectedtest4;
	
	Logger logger = LoggerFactory.getLogger(ZipLimiterTestv2.class);
	/*
	* These are the values to be compared against the data read from files in test case scenarios.
	* Called automatically before the Test class is run.
	*/
 	@BeforeMethod
    public void setUp() {
 		logger.info("@Before - setUp");
 		ZipFileReaderUtil readerUtil = new ZipFileReaderUtil();
    	expectedtest1 = readerUtil.readFile("resources/test/expectedv2/expectedZipInputNoConflictPairs.txt");
		expectedtest2 = readerUtil.readFile("resources/test/expectedv2/expectedZipInputMergeSortedPairs.txt");
		expectedtest3 = readerUtil.readFile("resources/test/expectedv2/expectedZipInputMergeUnsortedPairs.txt");
		expectedtest4 = readerUtil.readFile("resources/test/expectedv2/expectedZipInputBadDataPairs.txt");
    }
    
    @AfterMethod
    public void tearDown() {
    	logger.info("@After - tearDown");
    }
    
    /*
    * Runs test cases for different scenarios comparing size of the lists and content of the lists
    * Test cases: No Merge, Sorted Merge, UnSorted Merge, Merge With BadData
    *
    */
    @Test
	public void zipLimiterDataTest() {
		ZipLimiter zip = new ZipLimiter();
		
		List<ZipCodePair> test1 = zip.test("resources/test/zipInputNoConflictPairs.txt");
		Assert.assertEquals(expectedtest1.size(), test1.size());
		Assert.assertEquals(expectedtest1, test1);
		
		List<ZipCodePair> test2 = zip.test("resources/test/zipInputMergeSortedPairs.txt");
		Assert.assertEquals(expectedtest2.size(), test2.size());
		Assert.assertEquals(expectedtest2, test2);
		
		List<ZipCodePair> test3 = zip.test("resources/test/zipInputMergeUnsortedPairs.txt");
		Assert.assertEquals(expectedtest3.size(), test3.size());
		Assert.assertEquals(expectedtest3, test3);
		
		List<ZipCodePair> test4 = zip.test("resources/test/zipInputBadDataPairs.txt");
		Assert.assertEquals(expectedtest4.size(), test4.size());
		Assert.assertEquals(expectedtest4, test4);
		
	}
    
}
