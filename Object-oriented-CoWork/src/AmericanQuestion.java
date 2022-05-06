import java.util.Arrays;

public class AmericanQuestion extends Question {

	private static final int MAX_ANSWERS = 10;
	private Answer[] allAnswers;
	private int numOfAnswers;
	private Answer[] rightAnswers;
	private int rightAnswerCounter;
	
	public AmericanQuestion(String content, Answer[] answers) {   //American Q C'tor
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
	
	
	//Decrease rightAnswerCounter
	public void RemoveAndDecreaseRightAnswersCounter(Answer answer) {
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
	public String printAnswers() throws DataNotCreatedYetException {
		if(this.numOfAnswers == 2) {
			throw new DataNotCreatedYetException("answers");
		}
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
	public boolean checkAnswer(String cont, Answer ans) {
		return (cont.toLowerCase()).equals(ans.getContent().toLowerCase());
	}
	
	public boolean isAnswerExists(Answer a){
		for(int i = 0; i < this.numOfAnswers; i++) {
			if(checkAnswer(this.allAnswers[i].getContent(), a)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean getMoreThanOneRight() {
		return this.allAnswers[numOfAnswers-1].isRight;
	}
	
	//Delete Answer American Question
	public boolean deleteAmericanAnswer(Answer answer ,int aPosition) {
		if(answer.getIsRight()){
			RemoveAndDecreaseRightAnswersCounter(answer);
		}
		checkForTrueAnswer();
		this.allAnswers[aPosition - 1] = null;
		for(int i = aPosition - 1; i < this.numOfAnswers - 1; i++)
		{
			this.allAnswers[i] = this.allAnswers[i+1];
		}
		
		this.numOfAnswers--;
		return true;
		}
	
	//Check if there is any true answer
	public void checkForTrueAnswer() {
		
		if(this.rightAnswerCounter == 0) {
			this.allAnswers[this.numOfAnswers-2].setRight(); //none of the answers is right
			this.allAnswers[this.numOfAnswers-1].setFalse(); //more than one answer is false 
			
		}
		
		if(this.rightAnswerCounter > 1 ) {
			this.allAnswers[this.numOfAnswers-1].setRight(); //more than one answer is right 
		    for(int i = 0; i < this.numOfAnswers - 1; i++){	 // all other answers are false
				if(this.allAnswers[i].isRight) 
					this.allAnswers[i].setFalse();
		    }
		}
		
		if(this.rightAnswerCounter == 1) {
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
	
	@Override
	public String saveQuestion() {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(this.content + "\n");
		for(int i = 0; i < this.numOfAnswers; i++) {
			sBuffer.append( (i+1) + ")" + this.allAnswers[i].content + "\n");
		}
		return sBuffer.toString();
	
	}


	@Override
	public String saveAnswer() {
		StringBuffer sBuffer = new StringBuffer();
		for(int i = 0; i < this.numOfAnswers; i++) {
			sBuffer.append( (i+1) + ")" + this.allAnswers[i].content + " | " + this.allAnswers[i].isRight + "\n");
		}
		return sBuffer.toString();
	}


	
	
	


	@Override
	public int answerLength() {
		int length = 0;
		for(int i = 0; i < numOfAnswers; i++)
			length += allAnswers[i].getContent().length();
		return length;
	}
	
	
	
	
	
	
	
	
}
