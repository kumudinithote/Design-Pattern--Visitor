package numberPlay.visitorPattern;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import numberPlay.util.EDIST;
import numberPlay.util.FileProcessor;
import numberPlay.util.Results;
import numberPlay.util.SpellCheckResults;

public class SpellCheckAnalyzer implements Visitor {

	FileProcessor fileProcessor;
	HashSet<String> acceptableWord;
	HashMap<String, HashSet<String>> correctWordMapping;
	SpellCheckResults spellCheckResults;
	
	public SpellCheckAnalyzer(String acceptableWordsFilename, Results spellCheckResults) {
		// TODO Auto-generated constructor stub
		try {
			this.fileProcessor = new FileProcessor(acceptableWordsFilename);
			this.acceptableWord = new HashSet<String>();
			this.correctWordMapping = new HashMap<String, HashSet<String>>();
			this.spellCheckResults = (SpellCheckResults) spellCheckResults;
			
			String line = this.fileProcessor.poll();
			while(line != null){
				//System.out.println(line);
				acceptableWord.add(line.toLowerCase());
				line = fileProcessor.poll();
			}
			
		} catch (InvalidPathException | SecurityException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR : InvalidPathException | SecurityException | IOException");
		}
	}
	
	/**
	 * @param element
	 * This method will Iterate the Arraylist to get the word and checks of the edit distance.
	 */
	@Override
	public void visit(Element element) {
		// TODO Auto-generated method stub
		MyArrayList myArrayList = (MyArrayList)element;
		
		Iterator<ArrayList<MyElement>> itertor = myArrayList.getIterator();
		
		while(itertor.hasNext()){
			//System.out.println(itertor.next());
			ArrayList<MyElement> myElements = itertor.next();
			
			Iterator<MyElement> myIntertor = myElements.iterator();
			while(myIntertor.hasNext()){
				String word = myIntertor.next().getWord();
				if(word.length() > 2){
					checkEditDistance(word);
				}
				
			}
		}
	}
	
	/**
	 * @param word
	 * It checks for the Edit Distance, if edit distance is 1,it will check the acceptable word with all the 
	 * suggested word and print it. 
	 * 
	 */
	
	public void checkEditDistance(String word){
		EDIST editst = new EDIST();
		for(String suggestedWord : acceptableWord){
			int editDistance = editst.editDist(word, suggestedWord, word.length(), suggestedWord.length());
			
			if(editDistance == 1){
				//System.out.println("found valid suggested word "+word+" "+suggestedWord);
				if(correctWordMapping.containsKey(word.toLowerCase())){
					correctWordMapping.get(word.toLowerCase()).add(suggestedWord);
				}else{
					HashSet<String> suggested = new HashSet<String>();
					suggested.add(suggestedWord);
					correctWordMapping.put(word.toLowerCase(), suggested);
				}
			}
		}
		
		if(correctWordMapping.containsKey(word.toLowerCase())){
			StringBuilder spellingMapping = new StringBuilder();
			spellingMapping.append("[ ");
			spellingMapping.append(word.toLowerCase());
			spellingMapping.append(" => { ");
			for(String correctWord : correctWordMapping.get(word.toLowerCase())){
				spellingMapping.append(correctWord+"   ");
			}
			spellingMapping.append(" } ]");
			spellCheckResults.wordMapping.add(spellingMapping.toString());
		}
	}

}
