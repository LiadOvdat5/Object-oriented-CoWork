
public class OpenQuestion extends Question {

	private Answer answer;
	
	public OpenQuestion(String content, String answer) { //Open Q C'tor
		super(content);
		this.answer = new Answer(answer,true);
	}
	
	
	//open Q answer update
	public boolean updateAnswer(String newAnswer)
	{
		return answer.setContent(newAnswer);
	}
 
	
	//Delete Answer
	public boolean deleteAnswer() {
		this.answer = null;
		return true;
	}
	
	public String toString() {//To String - print;
		StringBuffer sBuffer = new StringBuffer();
		if(answer != null)
			sBuffer.append(super.toString() + answer.toString() + "\n");
		
		return sBuffer.toString();
	}


	



	
}
