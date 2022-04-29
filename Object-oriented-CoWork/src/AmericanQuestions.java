import java.util.Arrays;

public class AmericanQuestions extends Question {

	private static final int MAX_ANSWERS = 10;
	private Answer[] allAnswers;
	private int numOfAnswers;
	private Answer[] rightAnswers;
	private int rightAnswerCounter;
	
	public AmericanQuestions(String content, Answer[] answers) {   //American Q C'tor
		super(content);
		this.allAnswers =  Arrays.copyOf(answers, MAX_ANSWERS);//answers array from MAIN
		this.rightAnswers = new Answer[MAX_ANSWERS-2];
		
		
		this.rightAnswerCounter = 0; //add right answer to Eg. 0 and increase addrightAnswer by 1
		for(int i = 0; i<answers.length; i++) { //check number of right answers
			if(answers[i].getIsRight()) {
				this.rightAnswers[rightAnswerCounter++] = answers[i]; // Right answers will be same answers from all answers array
				
			}
		}
		boolean moreThanOne = (this.rightAnswerCounter > 1);
		boolean noneOf = (this.rightAnswerCounter == 0);
		allAnswers[answers.length]= new Answer("None of the above is true", noneOf); //initialize 2 auto answers
		allAnswers[answers.length + 1]= new Answer("More than one answer is true", moreThanOne); 
		
		this.numOfAnswers = answers.length+2; //set num of answer to num of answers inserted + 2 automatic answers
		
		if(moreThanOne) //if more than one answer is right than make all answers wrong except "moreThan1" ans
			for(int i = 0; i < this.numOfAnswers - 1; i++){
				if(this.allAnswers[i].isRight) {
					allAnswers[i].setFalse();
					
				}
			}
			
		
	}
	
	//American Q answer update
	public boolean updateAnswer(String content, int aNum) {
		if(checkAnswer(content, allAnswers[aNum-1]))
			return false;
		return allAnswers[aNum-1].setContent(content);
	}
	
	//Decrease rightAnswerCounter
	public void decreaseRightAnswersCounter(Answer answer) {
		for(int i = 0; i < rightAnswers.length; i++) {
			if(rightAnswers[i] != null)
				if(answer.getContent().equals(rightAnswers[i].getContent())) {
					rightAnswers[i] = null;
					rightAnswerCounter--;
			}
		}
	}
	
	//increase rightAnswerCounter
	public void increaseRightAnswersCounter(Answer answer) {
		for(int i = 0; i < rightAnswers.length; i++) {
			if(rightAnswers[i] == null) {
				rightAnswers[i] = answer;
				rightAnswerCounter++;
				return;
			}
		}
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
		return this.numOfAnswers;
	}
	
	//Check if answer provided is identical 
	@Override
	public boolean checkAnswer(String cont, Answer ans) {
		return (cont.toLowerCase()).equals(ans.getContent().toLowerCase());
	}
	
	
	
	//Delete Answer
	public boolean deleteAnswer(int aNum) {
		boolean match = false;
		int currentRightAnswerNum = 0;
		while(!match && currentRightAnswerNum != this.rightAnswerCounter) {
			if(checkAnswer(this.allAnswers[aNum].getContent(), this.rightAnswers[currentRightAnswerNum]) ) {
				this.rightAnswers[currentRightAnswerNum] = null;
				this.rightAnswerCounter--;
				match = true;
			}
			currentRightAnswerNum++;
		}
		
		
		if(aNum == this.numOfAnswers - 2) { //Standart Delete
			this.allAnswers[aNum-1] = null;
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
		
		if(rightAnswerCounter == 0) {
			allAnswers[numOfAnswers-2].setRight(); //none of the answers is right
			allAnswers[numOfAnswers-1].setFalse(); //more than one answer is false 
			
		}
		
		if(rightAnswerCounter > 1 ) {
			allAnswers[numOfAnswers-1].setRight(); //more than one answer is right 
		    allAnswers[numOfAnswers-2].setFalse(); //none of the answers is false
		    for(int i = 0; i < this.numOfAnswers - 1; i++){
				if(this.allAnswers[i].isRight) 
					allAnswers[i].setFalse();
		    }
		}
		
		if(rightAnswerCounter == 1) {
		    allAnswers[numOfAnswers-2].setFalse(); //none of the answers is false
			allAnswers[numOfAnswers-1].setFalse(); //more than one answer is false 
			for(int i = 0; i < this.rightAnswerCounter; i++) { //Set the only right answer to right
				if(this.rightAnswers[i] != null) {
					this.rightAnswers[i].setRight();
				}
			}
		}

		
		
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
