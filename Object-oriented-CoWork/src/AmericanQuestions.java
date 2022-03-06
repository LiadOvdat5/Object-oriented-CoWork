import java.util.Arrays;

public class AmericanQuestions extends Question {

	private static final int MAX_ANSWERS = 10;
	private Answer[] allAnswers;
	private int numOfAnswers;
	
	public AmericanQuestions(String content, Answer[] answers) {   //American Q C'tor
		super(content);
		allAnswers = new Answer[MAX_ANSWERS];	
		allAnswers =  Arrays.copyOf(answers, answers.length);//answers array from MAIN
		int counter = 0;
		for(int i = 0; i<answers.length; i++) { //check number of right answers
			if(answers[i].getIsRight())
				counter++;
		}
		boolean moreThanOne = (counter>1);
		boolean noneOf = (counter==0);
		allAnswers[answers.length]= new Answer("More than one answer is true", moreThanOne); //initialize 2 auto answers
		allAnswers[answers.length+1]= new Answer("None of the above is true", noneOf);
		
		this.numOfAnswers = allAnswers.length;
		
		
	}
	
	
	
	
}
