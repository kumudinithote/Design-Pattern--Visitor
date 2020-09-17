package numberPlay.visitorPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

import numberPlay.util.Results;
import numberPlay.util.TopKFreqWordsResults;


public class TopKMostFreqAnalyzer implements Visitor {

	private int topK;
	private TopKFreqWordsResults results;
	public TopKMostFreqAnalyzer(int k, Results topKFreqWordsResults) {
		this.topK = k;
		this.results = (TopKFreqWordsResults)topKFreqWordsResults;
	}
	
	/**
	 * @param element
	 * This method will Iterator the Arraylist sentence by sentence and get the word count and add that in the priority queue,
	 * get the frequency of the word,queue will appaend the those words to topk.
	 */

	@Override
	public void visit(Element element) {
		MyArrayList myArrayList = (MyArrayList)element;
		
		Iterator<ArrayList<MyElement>> itertor = myArrayList.getIterator();
		while(itertor.hasNext()){
			//System.out.println(itertor.next());
			
			ArrayList<MyElement> myElements = itertor.next();
			
			Iterator<MyElement> myIntertor = myElements.iterator();
			
			HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
			while(myIntertor.hasNext()){
				
				MyElement myElement = myIntertor.next();
				//System.out.println(myElement.getWord());
				if(wordCount.containsKey(myElement.toString().toLowerCase())){
					int count = wordCount.get(myElement.toString().toLowerCase());
					count++;
					wordCount.put(myElement.toString().toLowerCase(), count);
				}else{
					wordCount.put(myElement.toString().toLowerCase(), 1);
				}	
			}
			
			//System.out.println("hashMap count : "+wordCount.size());
			PriorityQueue<MyElement> priorityQueue = new PriorityQueue<>();
			for (Map.Entry<String,Integer> entry : wordCount.entrySet()){
				MyElement ele = new MyElement(entry.getKey());
				ele.setCount(entry.getValue());
				//System.out.println("word : "+ele.getWord());
				priorityQueue.add(ele);
			}
			//System.out.println("queue size : "+priorityQueue.size());
			StringBuilder topKWords = new StringBuilder();
			topKWords.append("[ ");
			for(int i = this.topK; i > 0 ; i--){
				topKWords.append(priorityQueue.poll().getWord()+"  ");
			}
			topKWords.append("]");
			
			this.results.topKWords.add(topKWords.toString());
			//System.out.println(topKWords.toString());
		}
		
	}

}
