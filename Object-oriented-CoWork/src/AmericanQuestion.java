import java.util.ArrayList;

import java.util.Iterator;

public class AmericanQuestion extends Question{

	private static final int MAX_ANSWERS = 10;
	private ArrayList <Answer> allAnswers;
	private ArrayList <Answer> rightAnswers;
	
	
	public AmericanQuestion(String content, ArrayList <Answer> answers) throws GeneralSystemException {   //American Q C'tor
		super(content);
		if(answers.size() > MAX_ANSWERS) {
			throw new GeneralSystemException("There could only be up to 10 answers");
		}
		this.allAnswers = new ArrayList<Answer>();
		this.allAnswers.addAll(answers); 
		
		
		Iterator <Answer> itr = allAnswers.iterator(); 
		while(itr.hasNext()) {
			if(itr.next().getIsRight()) {
				this.rightAnswers.add(itr.next());
			}
		}
		/*
		for(int i = 0; i < allAnswers.size(); i++) { //check number of right answers
			if(answers.get(i).getIsRight()) {
				this.rightAnswers.add(answers.get(i++)); // Right answers will be same answers from all answers array
				
			}
		}
		*/
		boolean moreThanOne = (rightAnswers.size() > 1);
		boolean noneOf = (rightAnswers.size() == 0);
		allAnswers.add(new Answer("None of the above is true", noneOf)); //initialize 2 auto answers
		allAnswers.add(new Answer("More than one answer is true", moreThanOne)); 
		
		
		if(moreThanOne) //if more than one answer is right than make all answers wrong except "moreThan1" ans
			for(int i = 0; i < allAnswers.size()- 1; i++){
				if(this.allAnswers.get(i).isRight) {
					this.allAnswers.get(i).setFalse();
					
				}
			}
			
		
	}
	
	
	//Decrease rightAnswerCounter
	public void RemoveAndDecreaseRightAnswersCounter(Answer answer) {			
			//rightAnswers.removeIf(ans -> ans.equals(answer));
		this.rightAnswers.remove(answer);
	}
	
	
	
	//increase rightAnswerCounter
	public void increaseRightAnswersCounter(Answer answer) { 
			 rightAnswers.add(answer);
	}
	
	//print Answer
	@Override
	public String printAnswers(){
		
		StringBuffer sBuffer = new StringBuffer();
		for(int i = 0; i < allAnswers.size()-2; i++) {
			sBuffer.append((i+1) +") "  + allAnswers.get(i).getContent() + "\n");
		}
		return sBuffer.toString();
	}

	//return A
	public Answer getAnswer(int numOfAnswer) {
		return allAnswers.get(numOfAnswer-1);
	}
	
	//return num of answers
	public int getNumOfAnswers() {
		return this.allAnswers.size();
	}
	
	
	
	public boolean isAnswerExists(Answer a){
		return allAnswers.contains(a);
	}
	
	public boolean getMoreThanOneRight() {
		return this.allAnswers.get(allAnswers.size()-1).isRight;
	}
	
	//Delete Answer American Question
	public boolean deleteAmericanAnswer(Answer answer ,int aPosition) {
		if(answer.getIsRight()){
			RemoveAndDecreaseRightAnswersCounter(answer);
		}
		checkForTrueAnswer();
		this.allAnswers.remove(aPosition - 1);
		return true;
		}
	
	//Check if there is any true answer
	public void checkForTrueAnswer() {
		Iterator <Answer> itr = allAnswers.iterator(); 
		
		if(this.rightAnswers.size() == 0) {
			this.allAnswers.get(allAnswers.size()-2).setRight(); //none of the answers is right
			this.allAnswers.get(allAnswers.size()-1).setFalse(); //more than one answer is false 
			
		}
		
		if(this.rightAnswers.size() > 1 ) {
			this.allAnswers.get(allAnswers.size()-1).setRight(); //more than one answer is right 
		    for(int i = 0; i < this.allAnswers.size() - 1; i++){	 // all other answers are false
				if(allAnswers.get(i).isRight) 
					allAnswers.get(i).setFalse();
				
		    }
		}
		
		if(this.rightAnswers.size() == 1) {
		    allAnswers.get(allAnswers.size()-2).setFalse(); //none of the answers is false
			allAnswers.get(allAnswers.size() - 1).setFalse(); //more than one answer is false 
			Answer setRightAnswer = this.rightAnswers.get(0);
			allAnswers.get(allAnswers.indexOf(setRightAnswer)).setRight();
				}
			}
			/*
			for(int i = 0; i < allAnswers.size(); i++) { //Set the only right answer to right
				if(this.allAnswers.get(i) != null) {
					this.allAnswers.get(i).setRight();
				}
			}
			*/
		
	

	public String toString() {//To String - print;
		Iterator <Answer> itr = allAnswers.iterator(); 
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(super.toString());
		while(itr.hasNext()) {
			sBuffer.append(itr.next().toString() + "\n");	
			sBuffer.append("\n");
		}
			return sBuffer.toString();
			/*
			for(Answer ans: allAnswers)
				sBuffer.append(ans.toString() + "\n");	
			sBuffer.append("\n");
			return sBuffer.toString();
			*/
	}
		
		
	
	
	@Override
	public String saveQuestion() {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(this.content + "\n");
		for(Answer ans: allAnswers)
			sBuffer.append((allAnswers.indexOf(ans))+ ")" + ans.toString() + "\n");
		
		return sBuffer.toString();
	
	}


	@Override
	public String saveAnswer() {
		StringBuffer sBuffer = new StringBuffer();
		for(int i = 0; i < this.allAnswers.size(); i++) {
			sBuffer.append( (i+1) + ")" + this.allAnswers.get(i).content + " | " + this.allAnswers.get(i).isRight + "\n");
		}
		return sBuffer.toString();
	}


	
	
	


	@Override
	public int answerLength() {
		int length = 0;
		Iterator <Answer> itr = allAnswers.iterator(); 
		while(itr.hasNext()) {
			length += itr.next().getContent().length();
		}
		/*
		for(int i = 0; i < this.allAnswers.size(); i++)
			if(allAnswers.get(i) != null)
				length += allAnswers.get(i).getContent().length();
				*/
		return length;
	}


	public void addAnswersToSet(Set<Answer> americanAnswersSet) throws Exception {
		Iterator <Answer> itr = allAnswers.iterator(); 
		while(itr.hasNext()) {
			americanAnswersSet.add(itr.next());
		}
		/*
		for(int i = 0; i < this.allAnswers.size(); i++)
			americanAnswersSet.add(allAnswers.get(i));
			*/
	}
	
	
	
	
	
	
	
	
}
