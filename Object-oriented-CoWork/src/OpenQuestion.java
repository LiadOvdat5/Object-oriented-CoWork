
public class OpenQuestion extends Question {

	private Answer answer;
	
	public OpenQuestion(String content, String answer) { //Open Q C'tor
		super(content);
		answer = answer.toLowerCase();
		this.answer = new Answer(answer,true);
	}

	
	//print Answer
	@Override
	public String printAnswers() {
		return this.answer.getContent();
	}
	
	//return A
	public Answer getAnswer() {
		return this.answer;
	}
	
	public String toString() {//To String - print;
		StringBuffer sBuffer = new StringBuffer();
		if(answer != null)
			sBuffer.append(super.toString() + answer.toString() + "\n");
		sBuffer.append("\n");
		
		return sBuffer.toString();
	}
	
	@Override
	public String saveQuestion() {
		return getContent();
	}


	@Override
	public String saveAnswer() {
		return answer.getContent();
	}




	@Override
	public int answerLength() {
		return this.getAnswer().getContent().length();
	}


	



	
}
