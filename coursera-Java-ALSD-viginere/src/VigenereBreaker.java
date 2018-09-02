import java.io.File;
import java.util.*;
import java.util.Map.Entry;

import edu.duke.*;

public class VigenereBreaker {
    
	public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i+=totalSlices) {
        	sb = sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i++) {
            String str = sliceString(encrypted, i, klength);
            int partOfKey = cc.getKey(str);
            key[i] = partOfKey;
        }
        return key;
    }
    
    HashSet<String> readDictionary(FileResource fr){
    	HashSet<String> dictionary = new HashSet<>();
    	for(String word : fr.words()) {
    		String wordLower = word.toLowerCase();
    		dictionary.add(wordLower);
    	}
    	return dictionary;
    }
    
    int countWords(String message, HashSet<String> dictionary) {
    	String[] words = message.toLowerCase().split("\\W+");
    	int wordCount = 0;
    	for(String word : words) {
    		if(dictionary.contains(word)) {
    			wordCount+=1;
    		}
    	}
    	return wordCount;
    }
    
    String breakForLanguage(String encrypted, HashSet<String> dictionary) {    	
    	int maxWordCountidx = 0;
    	
    	for(int i = 1; i < 100; i++) {
    		int[] key = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
        	VigenereCipher vc = new VigenereCipher(key);
        	String decrypted = vc.decrypt(encrypted);
        	
        	int wordCount = countWords(decrypted, dictionary);
        	if(wordCount > maxWordCountidx) {
        		maxWordCountidx = wordCount;
        	}	
    	}
		
    	for(int i = 1; i < 100; i++) {
    		int[] key = tryKeyLength(encrypted, i, 'e');
        	VigenereCipher vc = new VigenereCipher(key);
        	String decrypted = vc.decrypt(encrypted);
        	
        	int wordCount = countWords(decrypted, dictionary);
        	if(wordCount == maxWordCountidx) {
        		StringBuilder sb = new StringBuilder();
        		for(int num : key) {
        			sb.append(num+" ");
        		}
        		return decrypted+"\n"+"Real word count:"+wordCount+"\n"+"Key:"+sb.toString()+"\n"+"Key Length:"+key.length;
        	}
    	}
    	return null;
    }
    
    char mostCommonCharIn(HashSet<String> dictionary) {
    	HashMap<Character, Integer> letterCounts = new HashMap<>();
    	for(String word : dictionary) {
    		for(int i = 0; i < word.length(); i++) {
    			char letter = word.charAt(i);
    			if(!letterCounts.containsKey(letter)) {
    				letterCounts.put(letter, 1);
    			}
    			else {
    				letterCounts.put(letter, letterCounts.get(letter)+1);
    			}
    		}
    	}
    	int maxValueInMap=(Collections.max(letterCounts.values())); 
        for (Entry<Character, Integer> entry : letterCounts.entrySet()) { 
            if (entry.getValue()==maxValueInMap) {
                return entry.getKey();     
            }
        }
		return 0;
    }
    
    void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
    	int max = 0;
    	for(String language : languages.keySet()){
    		HashSet<String> dictionary = languages.get(language);
    		int wordCount = countWords(language, dictionary);
    		if(wordCount > max) {
    			max = wordCount;
    		}
    	}
    	
    	for(String language : languages.keySet()){
    		HashSet<String> dictionary = languages.get("German");
    		String decrypted = breakForLanguage(encrypted, dictionary);
    		int wordCount = countWords(language, dictionary);
    		if(wordCount == max) {
    			System.out.println(decrypted+"\nLanguage: "+language);
    		}
    	}
    }
    
    public void breakVigenere () {
    	DirectoryResource dir = new DirectoryResource();
    	HashMap<String, HashSet<String>> languages = new HashMap<>();
    	
    	for(File file : dir.selectedFiles()) {
        	FileResource fr = new FileResource(file);
        	languages.put(file.getName(), readDictionary(fr));
    	}
    	
    	FileResource file = new FileResource("./messages/secretmessage4.txt");
    	String encrypted = file.asString();
    	//System.out.println(languages.keySet());
    	breakForAllLangs(encrypted, languages);
    }
    
    public static void main(String[] args) {
    	VigenereBreaker vb = new VigenereBreaker();
    	/**System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
    	FileResource fr = new FileResource("./messages/secretmessage1.txt");
    	String readFile = fr.asString();
    	int[] key = vb.tryKeyLength(readFile, 5, 'e');
    	for(int num : key) {
    		System.out.println(num);
    	}
    	vb.breakVigenere();**/
    	
    	vb.breakVigenere();
    }
}
