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
	public boolean addQuestion(Question q) throws DataIdenticalException {
		isQuestionExist(q);
			
		if (numOfQuestions == allQuestions.length) {
			allQuestions = Arrays.copyOf(allQuestions, numOfQuestions * 2);
		}

		allQuestions[numOfQuestions++] = q;

		return true;
	}

	// Check if the question exists
	public boolean isQuestionExist(Question q) throws DataIdenticalException {
		for (int i = 0; i < numOfQuestions; i++)
			if (q.equals(this.allQuestions[i])) {
				throw new DataIdenticalException("question");			}

		return false;

	}

	// Check if the content exists
	public int isContentExist(String content) {
		for (int i = 0; i < numOfQuestions; i++)
			if (content.equals(allQuestions[i].getContent()))
				return i;

		return -1;
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

	public void arrangeExamQuestions() {
		for (int j=0; j<allQuestions.length; j++){
            if (allQuestions[j] == null){
                for (int k=j+1; k < allQuestions.length; k++){
                    allQuestions[k-1] = allQuestions[k];
                }
                allQuestions[allQuestions.length-1] = null;
            }
        }
		numOfQuestions--;
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
	
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Exam))
			return false;
		
		return ((Exam)object).getExamName().toLowerCase().equals(this.examName.toLowerCase());

	}
	
	
	
	
}
