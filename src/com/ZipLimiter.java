package com;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.ZipFileReaderUtil;
/*
	This class uses the zipInputPair.txt as a source to read a zip code range and then compresses the range where values overlap 
	Values in the file are assumed to be in comma separated pairs
*/
public class ZipLimiter {
	
	private List<ZipCodePair> zipOutputMatchList;
	private List<ZipCodePair> zipInputMatchList;
	private String zipA;
	private String zipB;
	private static String fileInput ="resources/test/zipInputMergeSortedPairs.txt";//default value given for Test
	private int lineNumber;
	
	Logger logger = LoggerFactory.getLogger(ZipLimiter.class);
    /*
    * Returns the compressed List<Pair> after combining overlapping ranges.
    *
    * @param  String The location of the text file with input zip ranges.
    * @return List<Pair> merged list of zip codes after combining overlapping ranges
    */
	public List<ZipCodePair> test(String fileInput){
		lineNumber=0;
        zipOutputMatchList = new ArrayList<>();
        ZipFileReaderUtil readerUtil=new ZipFileReaderUtil();
        zipInputMatchList = readerUtil.readFile(fileInput);    
        logger.info("Before Ziplimiter merge operation");
        zipInputMatchList.forEach(item->logger.info(item.toString()));
	    zipInputMatchList.sort(Comparator.comparing(ZipCodePair::getZipA));
	    zipOutputMatchList = mergeBoundries(zipInputMatchList);
	    logger.info("After Ziplimiter merge operation");
	    zipOutputMatchList.forEach(item->logger.info(item.toString()));
	    return zipOutputMatchList;	  
	}

    /*
    * Iterate and merge the zip codes where overlap exists
    *
    * @param  List<Pair> This input list consists of Pair values created from data read from file.
    * @return List<Pair> This output list consists of the merged Pair values.
    */
	private List<ZipCodePair> mergeBoundries(List<ZipCodePair> zipList) {
		for (ZipCodePair current: zipList){
			//new range is out of existing range, add it
			if (zipOutputMatchList.isEmpty()){
				zipOutputMatchList.add(current);
			} else{
				ZipCodePair prevPair =zipOutputMatchList.get(zipOutputMatchList.size()-1);
				if(prevPair.getZipA()<=current.getZipA() && prevPair.getZipB()>=current.getZipB()){
					//already included
				} else if(prevPair.getZipA()>current.getZipB() || prevPair.getZipB()<current.getZipA()){
					zipOutputMatchList.add(current);//New Range
				} else{
					ZipCodePair temp =new ZipCodePair(prevPair.getZipA(),current.getZipB());
					zipOutputMatchList.remove(zipOutputMatchList.size()-1);
					zipOutputMatchList.add(temp);
				}
			}
		}
		return zipOutputMatchList;
	}

	public static void main(String[] args){
		ZipLimiter zip = new ZipLimiter();
		zip.test(fileInput);
	}
	
    /*
    * Returns the compressed List<Pair> after combining overlapping ranges.
    *
    * @param  String with input zip ranges.
    * @return List<Pair> merged list of zip codes after combining overlapping ranges
    */
	public List<ZipCodePair> testv3(String line){
        zipOutputMatchList = new ArrayList<>();
        zipInputMatchList = new ArrayList<>();
   	  	String [] strArray1 =line.split("\\|");
   	    ZipFileReaderUtil readerUtil=new ZipFileReaderUtil();
   	  	for (String s:strArray1) {
   	  		String [] strArray2 =s.split(",");
   	  		if (strArray2[0].length()==5 && strArray2[1].length()==5){
   	  			zipA =strArray2[0];
   	  			zipB =strArray2[1];
   	  			if (!readerUtil.validateStop(zipA) && !readerUtil.validateStop(zipB)){
   	  				ZipCodePair pair= new ZipCodePair(Integer.parseInt(zipA),Integer.parseInt(zipB));
   	  				zipInputMatchList.add(pair);
   	  			}
   	  		} else{
   	  			logger.error("Error at line number  : "+lineNumber+" Input is not in a 5 digit format");
   	  		}
   	  	}
	    zipInputMatchList.sort(Comparator.comparing(ZipCodePair::getZipA));
	    zipOutputMatchList = mergeBoundries(zipInputMatchList);
	    zipOutputMatchList.forEach(item->logger.info(item.toString()));
	    return zipOutputMatchList;
	}
	
    /*
     * Returns the compressed List<Pair>.
     *
     * @param  String with input zip ranges.
     * @return List<Pair>  list of zip codes
     */
 	public List<ZipCodePair> parsev3(String line){
 	  zipOutputMatchList = new ArrayList<>();
 	  String [] strArray1 =line.split("|");
 	  for (String s:strArray1) {
 		  String [] strArray2 =s.split(",");
          if (strArray2[0].length()==6 && strArray2[1].length()==6){
        	  zipA =strArray2[0].substring(1, 6);
        	  zipB =strArray2[1].substring(0, 5);
      	 	  ZipCodePair pair= new ZipCodePair(Integer.parseInt(zipA),Integer.parseInt(zipB));
      	 	  zipOutputMatchList.add(pair);
          } else{
        	  logger.error("Error at line : "+line+" Input is not in a 5 digit format");
          }
 	  }
 	  return zipOutputMatchList;
 	}
}
