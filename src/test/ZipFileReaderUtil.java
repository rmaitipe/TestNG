package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ZipCodePair;

public class ZipFileReaderUtil {
	
	Logger logger = LoggerFactory.getLogger(ZipFileReaderUtil.class);
	
    public List<ZipCodePair> readFile(String fileInput){
		int lineNumber=0;
		List<ZipCodePair> zipInputMatchList = new ArrayList<>();
		String zipA;
		String zipB;
    	//try with resources
    	try (BufferedReader br = new BufferedReader(new FileReader(fileInput))){
	        String line = null;
	        logger.info("Reading and Validating contents of file  :" +fileInput);
	        while((line = br.readLine()) != null){
	        	 lineNumber++;
	             String [] strArray =line.split(",");
	             if (strArray[0].length()==6 && strArray[1].length()==6){
	            	 zipA =strArray[0].substring(1, 6);
	            	 zipB =strArray[1].substring(0, 5);
	         	 	 if (!validateStop(zipA) && !validateStop(zipB)){
	         	 		ZipCodePair pair= new ZipCodePair(Integer.parseInt(zipA),Integer.parseInt(zipB));
	         	 		zipInputMatchList.add(pair);
	         	 	 }
	             } else{
	            	 logger.error("Error at line number  : "+lineNumber+" Input is not in a 5 digit format");
	             }
	        }
        } catch (IOException e) {
            e.printStackTrace();
        }    
    	return zipInputMatchList;
    }
    
    /*
    * Indicates if the input from file is a valid integer along with the line number where the failure occurred
    *
    * @param  String The input is a zip code.
    * @return boolean Fails validation if not an Integer, Not 5 digits
    */
	public boolean validateStop(String zip) {
		boolean isStop = false;
	    	try{
	    		Integer.parseInt(zip);
	    	}
	    	catch(NumberFormatException e){
	    		logger.error("Input is not a valid number");
	    		isStop =true;
	    	}
	    	if(zip.length()!=5 && !isStop){
	    		logger.error("Input is not a 5 digit zip code");
	    		isStop =true;
	    	}
		return isStop;
	}

}
