import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordsInFiles {
	private HashMap<String, ArrayList<String>> wordMap;
	
	WordsInFiles(){
		wordMap = new HashMap<>();
	}
	
	@SuppressWarnings("unused")
	private void addWordsFromFile(File f) {
		FileResource file = new FileResource(f);
		for(String word : file.words()) {
			if(!wordMap.containsKey(word)) {
				ArrayList<String> arrListWord = new ArrayList<String>();
				arrListWord.add(f.getName());
				wordMap.put(word, arrListWord);
			}
			else {
				if(!wordMap.get(word).contains(f.getName())) {
					wordMap.get(word).add(f.getName());
				}
			}
		}
	}
	
	void buildWordFileMap() {
		wordMap.clear();
		DirectoryResource dir = new DirectoryResource();
		
		for(File f : dir.selectedFiles()) {
			addWordsFromFile(f);
		}
	}
	
	int maxNumber() {
		int base = 0;
		for (String word : wordMap.keySet()) {
			if (wordMap.get(word).size() > base) {
				base = wordMap.get(word).size();
			}
		}
		return base;
	}
	
	ArrayList<String> wordsInNumFiles(int number){
		ArrayList<String> arrList = new ArrayList<>();
		
		for (String word : wordMap.keySet()) {
			int wordArrSize = wordMap.get(word).size();
			if (wordArrSize == number) {
				arrList.add(word);
			}
		}
		return arrList;
	}
	
	void printFilesIn(String word) {
		ArrayList<String> filesWithWord = wordMap.get(word);
		System.out.println(word + " appears in " + filesWithWord);
		
	}
	
	public static void main(String[] args) {
		WordsInFiles wif = new WordsInFiles();
		wif.buildWordFileMap();
		wif.printFilesIn("tree");
		//wif.maxNumber();
		//System.out.println(wif.wordsInNumFiles(7).size());
		//System.out.println("\n"+wif.wordMap);
	}
	
	
	
	
	
	
	
	
	
	
}
