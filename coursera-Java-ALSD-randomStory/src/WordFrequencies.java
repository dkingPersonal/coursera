import java.util.ArrayList;

import edu.duke.FileResource;

public class WordFrequencies {
	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
	
	WordFrequencies(){
		myWords = new ArrayList<>();
		myFreqs = new ArrayList<>();
	}
	
	void findUnique() {
		myWords.clear();
		myFreqs.clear();
		
		FileResource file = new FileResource("C:\\textfiles\\errors.txt");
		for(String word : file.words()) {
			word = word.toLowerCase();
			int index = myWords.indexOf(word);
			if(index == -1) {
				myWords.add(word);
				myFreqs.add(1);
			}
			else {
				int value = myFreqs.get(index);
				myFreqs.set(index, value+1);
			}
		}
	}
	
	int findIndexOfMax() {
		int base = 0;
		for(int i=0; i<myFreqs.size(); i++) {
			if(myFreqs.get(i) > myFreqs.get(base)){
				base = i;
			}
		}
		return base;
	}
	
	void tester() {
		findUnique();
		for(int i=0; i<myWords.size(); i++) {
			System.out.println("Word: "+myWords.get(i)+"\tFrequency: "+myFreqs.get(i));
		}
		int maxIndex = findIndexOfMax();
		System.out.println(myWords.get(maxIndex)+" is the word with the most occurences, "+myFreqs.get(maxIndex));
		System.out.println("NUMBEROF UNIQUE WORDS: "+myWords.size());
	}
	
	public static void main(String[] args) {
		WordFrequencies wf = new WordFrequencies();
		wf.tester();
	}
}
