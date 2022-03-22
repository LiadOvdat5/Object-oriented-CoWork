
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
	
	//public void setSerialNum()
	abstract String printAnswers();
	
	//Check if answer provided is identical 
	public abstract boolean checkAnswer(String cont, Answer ans);
	
	
	public String toString() { //To String - print;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("Question number (serialNum in repository): " + this.serialNumber + "\n");
		sBuffer.append("  Question content: " + this.content + "? \n");

		return sBuffer.toString();
	}
}
