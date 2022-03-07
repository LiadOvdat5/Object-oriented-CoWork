
public abstract class Question {

	public static int serialNumberGenerator = 1;
	protected String content;
	protected int serialNumber;
	
	public Question(String content) {
		this.content = content;
		this.serialNumber = serialNumberGenerator++;
	}
	
	//Get the content of question
	public String getContent() { 
		return this.content;
	}
	
	//Update question content
	public boolean updateContent(String content) {
		this.content = content;
		return true;
	}
	
	
	
	public String toString() { //To String - print;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("Question number: " + this.serialNumber + "\n Question content: " + this.content + "? \n");
		
		return sBuffer.toString();
	}
}
