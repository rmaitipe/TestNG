package test;

import java.util.ArrayList;
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
This class is to Unit test the ZipLimiter class. 
It users the files to load different data sets and compare with expectations using Junit4 test cases
*/
public class ZipLimiterTest {
	
	Logger logger = LoggerFactory.getLogger(ZipLimiterTest.class);
	
	private ArrayList<ZipCodePair> testNonConflict;
	private ArrayList<ZipCodePair> testSorted;
	private ArrayList<ZipCodePair> testUnsorted;
	private ArrayList<ZipCodePair> testInputData;
	private ArrayList<ZipCodePair> testBadInput;
	
	/*
	* These are the values to be compared against the data read from files in test case scenarios.
	* Called automatically before the Test class is run.
	*/
 	@BeforeMethod
    public void setUp() {
 		logger.info("@Before - setUp");
    	this.testNonConflict = new ArrayList<>();
    	testNonConflict.add(new ZipCodePair(94200,94299));
    	testNonConflict.add(new ZipCodePair(94500,94699));
    	testNonConflict.add(new ZipCodePair(94800,94850));
    	testNonConflict.add(new ZipCodePair(95133,95133));
    	testNonConflict.add(new ZipCodePair(95135,95153));
    	testNonConflict.add(new ZipCodePair(96845,96950));
    	testNonConflict.add(new ZipCodePair(96955,96999));
    	
    	this.testSorted = new ArrayList<>();
    	testSorted.add(new ZipCodePair(94200,94699));
    	testSorted.add(new ZipCodePair(94800,94850));
    	testSorted.add(new ZipCodePair(95133,95153));
    	testSorted.add(new ZipCodePair(96845,96950));
    	
    	this.testUnsorted = new ArrayList<>();
    	testUnsorted.add(new ZipCodePair(94200,94699));
    	testUnsorted.add(new ZipCodePair(94800,94850));
    	testUnsorted.add(new ZipCodePair(95133,95153));
    	testUnsorted.add(new ZipCodePair(96845,96950));	    
    	
    	this.testInputData = new ArrayList<>();
    	testInputData.add(new ZipCodePair(94200,94299));
    	testInputData.add(new ZipCodePair(95133,95133));
    	testInputData.add(new ZipCodePair(94800,94850));
    	
    	this.testBadInput = new ArrayList<>();
    	testBadInput.add(new ZipCodePair(94200,94299));
    	testBadInput.add(new ZipCodePair(94800,94850));
    }
    
    @AfterMethod
    public void tearDown() {
    	logger.info("@After - tearDown");
        this.testNonConflict.clear();
        this.testSorted.clear();
        this.testUnsorted.clear();
        this.testInputData.clear();
        this.testBadInput.clear();
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
		Assert.assertEquals(7, test1.size());
		Assert.assertEquals(testNonConflict, test1);
		
		List<ZipCodePair> test2 = zip.test("resources/test/zipInputMergeSortedPairs.txt");
		Assert.assertEquals(4, test2.size());
		Assert.assertEquals(testSorted, test2);
		
		List<ZipCodePair> test3 = zip.test("resources/test/zipInputMergeUnsortedPairs.txt");
		Assert.assertEquals(4, test3.size());
		Assert.assertEquals(testUnsorted, test3);
		
		List<ZipCodePair> test4 = zip.test("resources/test/zipInputBadDataPairs.txt");
		Assert.assertEquals(2, test4.size());
		Assert.assertEquals(testBadInput, test4);
	}
    
}
