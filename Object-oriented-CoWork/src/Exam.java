import java.util.Arrays;

public class Exam {
	 String examName;
	Question[] allQuestions;
	int numOfQuestions;

	// C'tor for making exam without arguments.
	public Exam(String name) {
		allQuestions = new Question[1];
		numOfQuestions = 0;
		examName = name;
	}

	// add Q
	public boolean addQuestion(Question q) {
		if (isQuestionExist(q))
			return false;
		if (numOfQuestions == allQuestions.length) {
			allQuestions = Arrays.copyOf(allQuestions, numOfQuestions * 2);
		}

		allQuestions[numOfQuestions++] = q;

		return true;
	}

	// Check if the question exists
	public boolean isQuestionExist(Question q) {
		for (int i = 0; i < numOfQuestions; i++)
			if (q.getContent().equals(allQuestions[i].getContent()))
				if (q.getClass().getSimpleName().equals(allQuestions[i].getClass().getSimpleName()))
					return true;

		return false;

	}

	// Check if the content exists
	public int isContentExist(String content) {
		for (int i = 0; i < numOfQuestions; i++)
			if (content.equals(allQuestions[i].getContent()))
				return i;

		return -1;
	}

	// update Q content
	public boolean updateQuestionContent(String content, int num) {

		int contentExist = isContentExist(content);
		if (contentExist != -1)
			if (allQuestions[num - 1].getClass().getSimpleName()
					.equals(allQuestions[contentExist].getClass().getSimpleName()))
				return false;

		allQuestions[num - 1].updateContent(content);

		return true;
	}

	// update Q(A) content
	// open Q answer
	public boolean setOpenAnswer(String content, int qNum) {
		if (isContentExist(content) == -1)
			return false;
		OpenQuestion temp = (OpenQuestion) allQuestions[qNum - 1];
		return temp.updateAnswer(content);
	}

	// American Q answer
	public boolean setAmericanAnswer(String content, int qNum, int aNum) {
		if (isContentExist(content) == -1)
			return false;
		AmericanQuestions temp = (AmericanQuestions) allQuestions[qNum - 1];
		return temp.updateAnswer(content, aNum);
	}

	// Delete Question (delete answer of open Q)
	public boolean deleteQuestion(int qNum) {
		if(qNum == numOfQuestions) {
			allQuestions[qNum-1] = null;
			numOfQuestions--;
			return true;
		}
		for(int i = qNum-1; i < numOfQuestions-1; i++) {
			allQuestions[i] = allQuestions[i+1];
		}
		allQuestions[numOfQuestions-1] = null;
		return true;
	}
	
		
	//Get Question by 
	public Question getQuestion(int num) {
		return allQuestions[num-1];
	}
	
	
	// toString
	public String toString() {// To String - print;
		StringBuffer sBuffer = new StringBuffer();

		sBuffer.append("Exam " + this.examName + " has: " + this.numOfQuestions + " questions. \n");

		sBuffer.append("Exam details: \n");
		for (int i = 0; i < numOfQuestions; i++) {
			sBuffer.append(allQuestions[i].toString());
		}
		sBuffer.append("\n -------- END OF EXAM -------- \n");

		return sBuffer.toString();
	}

	public String getExamName() {
		return this.examName;
	}
	
	public int getNumOfQuestions()
	{
		return this.numOfQuestions;
	}

	//Print Exams Name and Num
		public String getListOfQuestions() {//To String - print;
			StringBuffer sBuffer = new StringBuffer();
			
			sBuffer.append("There are " + this.numOfQuestions + " Questions: \n" );
			
			for(int i = 0; i < numOfQuestions; i++) {
				sBuffer.append((i+1) + ") " + allQuestions[i].getContent() + "\n");
			}
			return sBuffer.toString();
		}
	
	
	
	
	
	
	
}
