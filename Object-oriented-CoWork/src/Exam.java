import java.util.Arrays;

public class Exam {
	String examName;
	Question[] allQuestions;
	int numOfQuestions;
	
	//C'tor for making exam without arguments.
	public Exam(String name) {
		allQuestions = new Question[1];
		numOfQuestions = 0;
		examName = name;
	}
	
	
	
	//add Q
	public boolean addQuestion(Question q) {
		if(isQuestionExist(q))
			return false;
		if(numOfQuestions == allQuestions.length) {
			allQuestions = Arrays.copyOf(allQuestions, numOfQuestions*2);
		}
		
		allQuestions[numOfQuestions++] = q;
		
		return true;
	}
	
	
	//Check if the question exists
	public boolean isQuestionExist(Question q) { 
		for(int i = 0; i < numOfQuestions; i++) 
			if(q.getContent().equals( allQuestions[i].getContent() )) 
				if(q.getClass().getSimpleName().equals( allQuestions[i].getClass().getSimpleName() ) ) 
					return true;
		
		return false;
			
		
	}
	
	//Check if the content exists
	public int isContentExist(String content) { 
	for(int i = 0; i < numOfQuestions; i++) 
		if(content.equals( allQuestions[i].getContent() ))
			return i;
		
	return -1;
	}
	
	//update Q content
	public boolean updateQuestionContent(String content, int num ) {
		
		int contentExist = isContentExist(content);
		if(contentExist != -1) 
			if(allQuestions[num-1].getClass().getSimpleName().equals(allQuestions[contentExist].getClass().getSimpleName()))
				return false;	
		
		allQuestions[num-1].updateContent(content);
		
		return true;
	}
	
	//update Q(A) content
	//open Q answer
	public boolean setOpenAnswer(String content, int qNum) { 
		if(isContentExist(content) == -1)
			return false;
		OpenQuestion temp = (OpenQuestion)allQuestions[qNum -1];
		return temp.updateAnswer(content);
	}
	
	//American Q answer
	public boolean setAmericanAnswer(String content, int qNum, int aNum) {
		if(isContentExist(content) == -1)
			return false;
		AmericanQuestions temp = (AmericanQuestions)allQuestions[qNum -1];
		return temp.updateAnswer(content, aNum);
	}
	
	
	//delete answer
	//Delete American Question
	public boolean deleteOpenAnswer(int qNum) {
		OpenQuestion temp = (OpenQuestion)allQuestions[qNum -1];
		return temp.deleteAnswer();
	}
	
	//Delete American Question
	public boolean deleteAmericanAnswer(int qNum,int aNum) {
		AmericanQuestions temp = (AmericanQuestions)allQuestions[qNum -1];
		return temp.deleteAnswer(aNum);
	}
	
	//toString
	public String toString() {//To String - print;
		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("The exam has: " + this.numOfQuestions + " questions. \n" );
		
		for(int i = 0; i < numOfQuestions; i++) {
			sBuffer.append("Exam details: \n" + allQuestions[i].toString() + "\n -------- END OF EXAM -------- \n" );
		}
		
		return sBuffer.toString();
	}
	

}
