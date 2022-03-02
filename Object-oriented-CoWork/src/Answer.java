
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
	
	public boolean setContent(String cont) {
		this.content = cont;
		return true;
		
	}
}
