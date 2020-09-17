package numberPlay.visitorPattern;

public class MyElement implements Element, Comparable<MyElement>{
	
	private String word;
	private int count;
	
	public MyElement(String word){
		this.word = word;
		this.count = 0;
	}
	
	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}
	
	@Override
    public int compareTo(MyElement ele) {
		if (this.count < ele.count) 
            return 1; 
        else if (this.count > ele.count) 
            return -1; 
        return 0; 
    }

	public void setCount(int count) {
		this.count = count;
	}

	public String getWord() {
		return word;
	}

	public String toString(){
		return this.word;
	}

}
