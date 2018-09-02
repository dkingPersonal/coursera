
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
    	 FileResource file = new FileResource(filename);
    	 for(String line : file.lines()) {
    		 LogEntry parsedLog = WebLogParser.parseEntry(line);
    		 records.add(parsedLog);
    	 }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIps() {
    	 ArrayList<String> uniqueIps = new ArrayList<>();
    	 for(LogEntry le : records) {
    		 if(!uniqueIps.contains(le.getIpAddress())) {
    			 uniqueIps.add(le.getIpAddress());
    		 }
    	 }
    	 return uniqueIps.size();
     }
     
     public void printAllHigherThanNum(int num) {
    	 for(LogEntry le : records) {
    		 if(le.getStatusCode() > num) {
    			 System.out.println(le);
    		 }
    	 }
     }
     
     ArrayList<String> uniqueIPVisitsOnDay(String someday){
    	 ArrayList<String> uniqueIPsOnDay = new ArrayList<>();
    	 for(LogEntry le : records) {
    		 String date = le.getAccessTime().toString();
    		 date = date.substring(4, 10);
    		 if(!uniqueIPsOnDay.contains(le.getIpAddress())) {
    			 if(date.equals(someday)) {
    				 uniqueIPsOnDay.add(le.getIpAddress());
    			 }
    		 }
    	 }
    	 return uniqueIPsOnDay;
     }
     
     int countUniqueIPsInRange(int low, int high) {
    	 ArrayList<String> uniqueIPsInRange = new ArrayList<>();
    	 for(LogEntry le : records) {
    		 if(!uniqueIPsInRange.contains(le.getIpAddress())) {
	    		 if(le.getStatusCode() >= low && le.getStatusCode() <= high) {
	    			 uniqueIPsInRange.add(le.getIpAddress());   	
	    		}
    		 }
    	 }
    	 return uniqueIPsInRange.size();
     }
     
     HashMap<String,Integer> countVisitsPerIP(){
    	HashMap<String,Integer> counts = new HashMap<>();
    	for(LogEntry le : records) {
    		String ipAddress = le.getIpAddress();
    		if(!counts.containsKey(ipAddress)) {
    			counts.put(ipAddress, 1);
    		}
    		else {
    			counts.put(ipAddress, counts.get(ipAddress)+1);
    		}
    	}
    	 return counts;
     }
     
     int mostNumberVisitsByIP(HashMap<String,Integer> counts) {
    	 int max = 0;
    	 for(String key : counts.keySet()) {
    		 if(counts.get(key) > max) {
    			 max = counts.get(key);
    		 }
    	 }
    	 return max;
     }
     
     ArrayList<String> IPsMostVisits(HashMap<String,Integer> counts){
    	 int max = 0;
    	 for(String key : counts.keySet()) {
    		 if(counts.get(key) > max) {
    			 max = counts.get(key);
    		 }
    	 }
    	 
    	 ArrayList<String> IPsWithMax = new ArrayList<>();
    	 for(String key : counts.keySet()) {
    		 if(counts.get(key) == max) {
    			 IPsWithMax.add(key);
    		 }
    	 }
    	 
    	 return IPsWithMax;
     }
     
     HashMap<String, ArrayList<String>> IPsForDays(){
    	 HashMap<String, ArrayList<String>> dateToIPs = new HashMap<>();
    	 for(LogEntry le : records) {
    		 String date = le.getAccessTime().toString();
    		 date = date.substring(4, 10);
    		 if(!dateToIPs.containsKey(date)) {
    			 ArrayList<String> IPs = new ArrayList<>();
    			 IPs.add(le.getIpAddress());
    			 dateToIPs.put(date, IPs);
    		 }
    		 else {
    			 ArrayList<String> IPs = dateToIPs.get(date);
    			 IPs.add(le.getIpAddress());
    			 dateToIPs.put(date, IPs);
    		 }
    	 }
    	 return dateToIPs;
     }
     
     ArrayList<String> dayWithMostIPVisits(HashMap<String,Integer> counts) {
    	 HashMap<String, ArrayList<String>> dateToIPs = new HashMap<>();
    	 for(LogEntry le : records) {
    		 String date = le.getAccessTime().toString();
    		 date = date.substring(4, 10);
    		 if(!dateToIPs.containsKey(date)) {
    			 ArrayList<String> IPs = new ArrayList<>();
    			 IPs.add(le.getIpAddress());
    			 dateToIPs.put(date, IPs);
    		 }
    		 else {
    			 ArrayList<String> IPs = dateToIPs.get(date);
    			 IPs.add(le.getIpAddress());
    			 dateToIPs.put(date, IPs);
    		 }
    	 }
    	 
    	 int IPCountForDate = 0;
    	 for(String key : dateToIPs.keySet()) {
    		 if(dateToIPs.get(key).size() > IPCountForDate) {
    			 IPCountForDate = dateToIPs.get(key).size();
    		 }
    	 }
    	 
    	 ArrayList<String> datesWithMostIPVisits = new ArrayList<>();
    	 for(String key : dateToIPs.keySet()) {
    		 if(dateToIPs.get(key).size() == IPCountForDate) {
    			 datesWithMostIPVisits.add(key);
    		 }
    	 }
    	 
    	 return datesWithMostIPVisits;
     }
     
     void iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> IPsForDays, String date){
    	 ArrayList<String> IPs = new ArrayList<>();
    	 for(String key : IPsForDays().keySet()) {
    		 for(String IP : IPsForDays.get(key)) {
    			 IPs.add(IP);
    		 }
    	 }
    	 
    	 HashMap<String,Integer> IPsCount = new HashMap<>();
    	 for(String IP : IPs) {
    		 if(!IPsCount.containsKey(IP)) {
    			 IPsCount.put(IP, 1);
    		 }
    		 else {
    			 IPsCount.put(IP, IPsCount.get(IP)+1);
    		 }
    	 }
    	 System.out.println(IPsCount);
     }
}
