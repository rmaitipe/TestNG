package test.data.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;
import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.AbstractRowProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

public class DataProvider1 {

	Logger logger = LoggerFactory.getLogger(DataProvider1.class);

	@DataProvider (name="getData")
	public ZipPairDRO[] getData() throws IOException {
		//Rows - Number of test cases.
		//Columns - Number of parameters in test data.
		ZipPairDRO[] array = null;
		try(CSVReader csvReader = new CSVReader(new FileReader("resources/test/v3/completev3.csv"), ',')){
		
			//Adding parser to get file dimensions - improve if possible later.
			CsvDimension myDimensionProcessor  = parserLogic();
			int rowCount = (int) myDimensionProcessor.rowCount-1;//skip Column Info row- Custom
			
			array=new ZipPairDRO[rowCount];
			int i=0;
			csvReader.readNext();//skip Column Info row- Custom
			String[] csvData = null;
			
			while ((csvData =csvReader.readNext()) != null) {
				array[i] = new ZipPairDRO(csvData[0],csvData[1],csvData[2],csvData[3]);
				i++;
		    }
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
	    }
		return array;
	}

	 public CsvDimension parserLogic() throws FileNotFoundException {
		 
	    //Creates an implementation of AbstractRowProcessor, defined below.
	    CsvDimension myDimensionProcessor = new CsvDimension();
	
	    CsvParserSettings settings = new CsvParserSettings();
	
	    //This tells the parser that no row should have more than 2,000,000 columns
	    settings.setMaxColumns(2000000);
	
	    //Here you can select the column indexes you are interested in reading.
	    //The parser will return values for the columns you selected, in the order you defined
	    //By selecting no indexes here, no String objects will be created
	    settings.selectIndexes(/*nothing here*/);
	
	    //When you select indexes, the columns are reordered so they come in the order you defined.
	    //By disabling column reordering, you will get the original row, with nulls in the columns you didn't select
	    settings.setColumnReorderingEnabled(false);
	
	    //We instruct the parser to send all rows parsed to your custom RowProcessor. 
	    settings.setRowProcessor(myDimensionProcessor);
		CsvParser parser = new CsvParser(settings);
		parser.parse(new FileReader(new File("resources/test/v3/completev3.csv")));
		return myDimensionProcessor;
	 }
	 
	 static class CsvDimension extends AbstractRowProcessor {
		int lastColumn = -1;
		long rowCount = 0;

		@Override
		public void rowProcessed(String[] row, ParsingContext context) {
			rowCount++;
			if (lastColumn < row.length) {
				lastColumn = row.length;
			}
		}
	 }

}