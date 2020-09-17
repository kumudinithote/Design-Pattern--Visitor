package numberPlay.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SpellCheckResults implements Results {

	private BufferedWriter writer;
	private String outputFileName;
	public ArrayList<String> wordMapping;

	public SpellCheckResults(String outputFileName) {
		this.outputFileName = outputFileName; 
		try {
			this.writer = new BufferedWriter(new FileWriter(outputFileName, false));
		} catch (IOException e) {
			System.out.println("Error while writing into file");
		}
		
		wordMapping = new ArrayList<String>();
	}
	
	@Override
	public void writeToFile() {
		try {
	    	this.writer = new BufferedWriter(new FileWriter(outputFileName, true));
			for(String line : wordMapping){
				writer.write(line);
				writer.newLine();
			}
		    writer.close();
		} catch (IOException e) {
			System.out.println("Error while writing into file");
		}
	}
	
	@Override
	public void writeToStdout() {
		
	}

}
