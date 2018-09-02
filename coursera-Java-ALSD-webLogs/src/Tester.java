
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("short-test_log");
    	la.printAll();
    }
    
    public void testUniqueIps() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("weblog2_log");
    	System.out.println(la.countUniqueIps());
    	
    }
    
    public void testPrintAllHigherThanNum() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("weblog1_log");
    	la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("weblog2_log");
    	System.out.println(la.uniqueIPVisitsOnDay("Sep 27"));
    }
    
    public void testCountUniqueIPsInRange() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("weblog2_log");
    	System.out.println(la.countUniqueIPsInRange(400, 499));
    }
    
    public void testVisitsPerIP() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("weblog3-short_log");
    	System.out.println(la.countVisitsPerIP());
    }
    
    public void testMostVisitsPerIP() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("weblog2_log");
    	HashMap<String,Integer> map = la.countVisitsPerIP();
    	int result = la.mostNumberVisitsByIP(map);
    	System.out.println(result);
    }
    
   public void testIPsMostVisits() {
	   LogAnalyzer la = new LogAnalyzer();
   		la.readFile("weblog2_log");
   		HashMap<String,Integer> map = la.countVisitsPerIP();
   		System.out.println(la.IPsMostVisits(map));
   }
   
   public void testIPsForDays() {
	   LogAnalyzer la = new LogAnalyzer();
  		la.readFile("weblog3-short_log");
  		System.out.println(la.IPsForDays());
   }
   
   public void testDaysWithMostIPVisits() {
	   LogAnalyzer la = new LogAnalyzer();
 		la.readFile("weblog2_log");
   		HashMap<String,Integer> map = la.countVisitsPerIP();
 		System.out.println(la.dayWithMostIPVisits(map));
   }
   
   public void testIPsWithMostVisitsOnDay() {
	   LogAnalyzer la = new LogAnalyzer();
	   la.readFile("weblog2_log");
	   la.iPsWithMostVisitsOnDay(la.IPsForDays(), "Sep 30");
   }
    
    public static void main(String[] args) {
    	Tester test = new Tester();
    	//test.testLogAnalyzer();
    	test.testUniqueIps();
    	//test.testPrintAllHigherThanNum();
    	test.testUniqueIPVisitsOnDay();
    	test.testCountUniqueIPsInRange();
    	//test.testVisitsPerIP();
    	test.testMostVisitsPerIP();
    	test.testIPsMostVisits();
    	//test.testIPsForDays();
    	test.testDaysWithMostIPVisits();
    	test.testIPsWithMostVisitsOnDay();
    }
}
