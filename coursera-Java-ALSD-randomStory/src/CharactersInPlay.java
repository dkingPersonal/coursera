import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {
	private ArrayList<String> names;
	private ArrayList<Integer> counts;
	
	CharactersInPlay(){
		names = new ArrayList<>();
		counts = new ArrayList<>();
	}
	
	void update(String person) {
		int index = names.indexOf(person);
		if(index == -1) {
			names.add(person);
			counts.add(1);
		}
		else {
			int value = counts.get(index);
			counts.set(index, value+1);
		}
	}
	
	void findAllCharacters() {
		FileResource file = new FileResource("C:\\textfiles\\errors.txt");
		for(String line : file.lines()) {
			int stopIndex = line.indexOf('.');
			if(stopIndex != -1) {
				String name = line.substring(0, stopIndex);
				update(name);
			}
		}
	}
	
	void characterWithNumParts(int num1, int num2) {
		for(int i=0; i<names.size(); i++) {
			if(counts.get(i)>=num1 && counts.get(i)<=num2) {
				System.out.println(names.get(i));
			}
		}
	}
	
	void tester() {
		findAllCharacters();
		for(int i=0; i<names.size(); i++) {
			if(counts.get(i)>15) {
			System.out.println(names.get(i)+" Number: "+counts.get(i));
			}
		}
	}
	
	public static void main(String[] args) {
		CharactersInPlay cip = new CharactersInPlay();
		cip.tester();
		System.out.println("\n");
		cip.characterWithNumParts(10, 15);
	}
}
