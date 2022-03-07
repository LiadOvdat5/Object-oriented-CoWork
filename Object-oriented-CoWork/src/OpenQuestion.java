
public class OpenQuestion extends Question {

	private Answer answer;
	
	public OpenQuestion(String content, String answer) { //Open Q C'tor
		super(content);
		this.answer = new Answer(answer,true);
	}

	
	public String toString() {//To String - print;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(super.toString() + answer.toString() + "\n");
		
		return sBuffer.toString();
	}
}
