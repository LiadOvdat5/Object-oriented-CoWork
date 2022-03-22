
public class OpenQuestion extends Question {

	private Answer answer;
	
	public OpenQuestion(String content, String answer) { //Open Q C'tor
		super(content);
		answer = answer.toLowerCase();
		this.answer = new Answer(answer,true);
	}
	
	
	//open Q answer update
	public boolean updateAnswer(String newAnswer)
	{
		if(checkAnswer(newAnswer, this.answer))
			return false;
		return answer.setContent(newAnswer);
	}
 
	//Check if answer provided is identical 
	@Override
	public boolean checkAnswer(String cont, Answer ans) {
		return (cont.toLowerCase()).equals(answer.content.toLowerCase());
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


	



	
}
