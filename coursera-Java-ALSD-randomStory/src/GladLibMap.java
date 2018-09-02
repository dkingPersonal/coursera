import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.duke.FileResource;
import edu.duke.URLResource;

public class GladLibMap {

	private HashMap<String, ArrayList<String>> map;
	private Random myRandom;
	private static String dataSourceDirectory = "C:\\GladLibData\\data\\data";
	private ArrayList<String> wordsUsedList;
	
	public GladLibMap(){
		wordsUsedList = new ArrayList<>();
		map = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		String[] categories = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
		for (String category: categories) {
			ArrayList<String> categoryList= readIt(source+"\\"+category+".txt");
			map.put(category, categoryList);
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {	
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return randomFrom(map.get(label));
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		
		if (wordsUsedList.indexOf(sub) == -1) {
			wordsUsedList.add(sub);
        }
        else {
            sub = getSubstitute(w.substring(first+1,last));
        }
		
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	int totalWordsInMap() {
		int sum = 0;
		for(String category : map.keySet()) {
			sum = map.get(category).size() + sum;
		}
		return sum;
	}
	
	int totalWordsConsidered() {
		ArrayList<String> categoryUsed = new ArrayList<>();
		FileResource fr = new FileResource("C:\\GladLibData\\data\\madtemplate2.txt");
		for (String word : fr.words()) {
			if(word != processWord(word)) {
				String cat = word.substring(word.indexOf("<") + 1, word.indexOf(">"));
				if(!categoryUsed.contains(cat)) {
					categoryUsed.add(cat);
				}
			}
		}
		System.out.println(categoryUsed);
		//System.out.println(map.keySet());
		
		int count = 0;
		for(String used : categoryUsed) {
			if(map.get(used) != null)
				count += map.get(used).size();
		}
		return count;
	}
	
	public void makeStory(){
		wordsUsedList.clear();
		String story = fromTemplate("C:\\GladLibData\\data\\madtemplate2.txt");
		printOut(story, 60);
	}
	
	public static void main(String[] args) {
		GladLibMap glm = new GladLibMap();
		glm.makeStory();
		System.out.println("\nTotal words in map are "+glm.totalWordsInMap()+"\n");
		System.out.println("Total words considered are " + glm.totalWordsConsidered());
	}
}
