import java.util.Arrays;

public class AmericanQuestions extends Question {

	private static final int MAX_ANSWERS = 10;
	private Answer[] allAnswers;
	private int numOfAnswers;
	
	public AmericanQuestions(String content, Answer[] answers) {   //American Q C'tor
		super(content);
		
		allAnswers =  Arrays.copyOf(answers, MAX_ANSWERS);//answers array from MAIN
		
		int counter = 0;
		for(int i = 0; i<answers.length; i++) { //check number of right answers
			if(answers[i].getIsRight())
				counter++;
		}
		boolean moreThanOne = (counter>1);
		boolean noneOf = (counter==0);
		allAnswers[answers.length]= new Answer("None of the above is true", noneOf); //initialize 2 auto answers
		allAnswers[answers.length + 1]= new Answer("More than one answer is true", moreThanOne); 
		
		this.numOfAnswers = answers.length+2; //set num of answer to num of answers inserted + 2 automatic answers
		
		if(moreThanOne) //if more than one q is right than make all answers wrong except "moreThan1" ans
			for(int i = 0; i < numOfAnswers-1; i++)
				allAnswers[i].isRight = false;
			

		
	}
	
	//American Q answer update
	public boolean updateAnswer(String content, int aNum) {
		return allAnswers[aNum-1].setContent(content);
	}
	
	
	//Delete Answer
		public boolean deleteAnswer(int aNum) {
			for(int i = aNum-1; i < numOfAnswers-1; i++)
				allAnswers[i] = allAnswers[i+1];
			allAnswers[numOfAnswers-1] = null;
			numOfAnswers--;
			return true;
			
		}
	
	public String toString() {//To String - print;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(super.toString());
		for(int i = 0; i < this.numOfAnswers; i++) {
			if(allAnswers[i] != null)
				sBuffer.append(allAnswers[i].toString() + "\n");
			
		}
		sBuffer.append("\n");
		
		return sBuffer.toString();
	}
	
}
