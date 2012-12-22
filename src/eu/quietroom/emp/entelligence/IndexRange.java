package eu.quietroom.emp.entelligence;

public class IndexRange {
	private int start;
	private int end;
	
	public IndexRange(int start, int end){
		this.start = start;
		this.end = end;
	}
	
	public int getStart(){
		return this.start;
	}
	
	public int getEnd(){
		return this.end;
	}
}
