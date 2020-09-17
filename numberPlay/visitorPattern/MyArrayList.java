package numberPlay.visitorPattern;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import numberPlay.util.FileProcessor;

public class MyArrayList implements Element {
	private FileProcessor fileProcessor;
	private ArrayList<ArrayList<MyElement>> listOfList;
	
	
	public MyArrayList(Builder builder){
		this.fileProcessor = builder.fileProcessor;
		listOfList = new ArrayList<ArrayList<MyElement>>();
		
		String line;
		try {
			line = this.fileProcessor.poll();
			while(line != null){
				//System.out.println(line);
				processLine(line);
				line = fileProcessor.poll();
			}
		} catch (IOException e) {
			System.out.println("ERROR : IOException");
		}
		
	}
	
	private void processLine(String line){
		//System.out.println(line);
		String[] sentenses = line.split("\\.");
		for(String sentense : sentenses){
			ArrayList<MyElement> myElements = new ArrayList<MyElement>();
			String[] words = sentense.trim().split(" ");
			for(String word : words){
				MyElement myElement = new MyElement(word);
				myElements.add(myElement);
			}
			listOfList.add(myElements);
			//System.out.println(myElements.size());
		}
	}
	
	public static class Builder {
		private FileProcessor fileProcessor;
		public Builder(){}
		
		public MyArrayList build() 
	        { 
	            return new MyArrayList(this); 
	        }

		public Builder withFileProcessor(FileProcessor fileProcessor) {
			this.fileProcessor = fileProcessor;
			return this;
		} 
	 }

	@Override
	public void accept(Visitor visitor) {
		//System.out.println("We are in accept method");
		visitor.visit(this);
	}
	
	public Iterator<ArrayList<MyElement>> getIterator(){
		return listOfList.iterator();
	}
}
