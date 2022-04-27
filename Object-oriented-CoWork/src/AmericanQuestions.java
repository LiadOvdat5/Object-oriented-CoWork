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
		if(checkAnswer(content, allAnswers[aNum-1]))
			return false;
		return allAnswers[aNum-1].setContent(content);
	}
	
	
	//print Answer
	@Override
	public String printAnswers() {
		StringBuffer sBuffer = new StringBuffer();
		for(int i = 0; i < numOfAnswers-2; i++) {
			sBuffer.append((i+1) +") "  + allAnswers[i].getContent() + "\n");
		}
		return sBuffer.toString();
	}

	//return A
	public Answer getAnswer(int numOfAnswer) {
		return allAnswers[numOfAnswer-1];
	}
	
	//return num of answers
	public int getNumOfAnswers() {
		return numOfAnswers;
	}
	
	//Check if answer provided is identical 
	@Override
	public boolean checkAnswer(String cont, Answer ans) {
		return (cont.toLowerCase()).equals(ans.getContent().toLowerCase());
	}
	
	//Delete Answer
	public boolean deleteAnswer(int aNum) {
		if(aNum == numOfAnswers) {
			allAnswers[aNum-1] = null;
			numOfAnswers--;
			return true;
		}
		for(int i = aNum-1; i < numOfAnswers-1; i++)
			allAnswers[i] = allAnswers[i+1];
		
		allAnswers[numOfAnswers-1] = null;
		numOfAnswers--;
		
		checkForTrueAnswer();
		
		return true;
			
		}


	//Check if there is any true answer
	public void checkForTrueAnswer() {
		int counter = 0;
		for(int i = 0; i < numOfAnswers; i++) {
			if(allAnswers[i].getIsRight())
				counter++;
		}
		if(counter == 0)
			allAnswers[numOfAnswers-2].setRight();
				
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
