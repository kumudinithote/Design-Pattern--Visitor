package numberPlay.driver;

import numberPlay.util.FileProcessor;
import numberPlay.util.Results;
import numberPlay.util.SpellCheckResults;
import numberPlay.util.TopKFreqWordsResults;
import numberPlay.visitorPattern.MyArrayList;
import numberPlay.visitorPattern.SpellCheckAnalyzer;
import numberPlay.visitorPattern.TopKMostFreqAnalyzer;
import numberPlay.visitorPattern.Visitor;
import numberPlay.visitorPattern.Element;;

/**
 * @author Kumudini Thote
 */
public class Driver {
	private static void runAnalysis(FileProcessor fileProcessor, Visitor... visitors) {
		Element myArrayList = new MyArrayList.Builder()
				.withFileProcessor(fileProcessor)
				.build();
		
		for (Visitor visitor : visitors) {
			myArrayList.accept(visitor);
		}
		
	}
	
	private static void persistResults(Results... analysisResults) {
		for (Results results : analysisResults) {
			results.writeToFile();
		}
	}

	public static void main(String[] args) {
		
		if ((args.length != 5) || (args[0].equals("${input}")) || (args[1].equals("${acceptableWordsFile}")) || (args[2].equals("${Dk}"))
			|| (args[3].equals("${topKOutputFile}"))  || (args[4].equals("${spellCheckOutputFile}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
			System.exit(0);
		}
		
		if(Integer.parseInt(args[2]) <= 0){
			System.err.println("Error: Incorrect Max size, K must be > 0.");
			System.exit(0);
		}
		
		String inputFilename = args[0];
		String acceptableWordsFilename = args[1];
		int k = Integer.parseInt(args[2]);
		String topKOutputFilename = args[3];
		String spellCheckOutputFilename = args[4];
		
		FileProcessor fileProcessor = new FileProcessor(inputFilename);

		Results topKFreqWordsResults = new TopKFreqWordsResults(topKOutputFilename);
		Visitor topKMostFreqAnalyzer = new TopKMostFreqAnalyzer(k, topKFreqWordsResults);

		Results spellCheckResults = new SpellCheckResults(spellCheckOutputFilename);
		Visitor spellCheckAnalyzer = new SpellCheckAnalyzer(acceptableWordsFilename, spellCheckResults);

		runAnalysis(fileProcessor, topKMostFreqAnalyzer, spellCheckAnalyzer);

		persistResults(topKFreqWordsResults, spellCheckResults);
	
		
	}
}
