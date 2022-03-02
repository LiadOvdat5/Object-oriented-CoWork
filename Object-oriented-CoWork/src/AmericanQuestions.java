
public class AmericanQuestions extends Question {
	public AmericanQuestions(String content) {// to delete
		super(content);
		
	}

	private static final int MAX_ANSWERS = 10;
	private Answer [] allAnswers;
	private int numOfAnswers;
	
	//public AmericanQuestion(String content, Answers [] answers) {
		
	//}
	
	public  boolean checkAnswer() {
		return true;
	}
}
