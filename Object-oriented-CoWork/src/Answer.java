
public class Answer {

	String content;
	boolean isRight;

	public Answer(String content, boolean TrueOrFalse) {
		this.content = content;
		this.isRight = TrueOrFalse;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public boolean getIsRight() {
		return this.isRight;
	}
	
	public void setRight() {
		this.isRight = true;
	}
	
	public void setFalse() {
		this.isRight = false;
	}
	
	public boolean setContent(String cont) {
		if(cont.equals(this.content))
			return false;	
		this.content = cont;
		return true;
		
	}
	
	
	public String toString() { //To String - print answer;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("    Answer: " + this.content + " |  " + this.isRight);
		
		return sBuffer.toString();
	}
}
