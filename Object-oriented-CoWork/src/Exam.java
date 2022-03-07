import java.util.Arrays;

public class Exam {
	
	Question[] allQuestions;
	int numOfQuestions;
	
	public Exam() {
		allQuestions = new Question[1];
		numOfQuestions = 0;
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
	
	//delete answer
	
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
