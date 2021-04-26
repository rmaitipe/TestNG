package csv.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import test.dataprovider.DataProvider1;
import test.dro.ZipPairDRO;

public class DataProviderForV4Test{

	
	/*The purpose of these package is to understand how data is setup for DataProvider Class
	 * test method declares that its data should be supplied by the DataProvider named "getdata"
	 * Here the data is converted to an object and passed
	 */ 
	
	Logger logger = LoggerFactory.getLogger(DataProviderForV4Test.class);
	
	@Test(dataProvider="getData", dataProviderClass=DataProvider1.class)
	public void setData(ZipPairDRO dro)
	{
		logger.info("your key is::"+dro.getKey());
		logger.info("your description is::"+dro.getDescription());
		logger.info("your inputpair is::"+dro.getInputZips());
		logger.info("your outputpair is::"+dro.getExpectedZips());
	}
	

    
}