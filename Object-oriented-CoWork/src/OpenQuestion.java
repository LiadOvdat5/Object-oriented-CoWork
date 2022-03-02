
public class OpenQuestion extends Question {

	private Answer answer;
	
	public OpenQuestion(String content, String answer) {
		super(content);
		this.answer = new Answer(answer,true);
	}

	
	public  boolean checkAnswer() {
		return true;
	}
}
