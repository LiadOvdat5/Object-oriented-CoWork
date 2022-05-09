import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Exam implements Cloneable {
	private String examName;
	private ArrayList<Question> allQuestions;

	// C'tor for making exam without arguments.
	public Exam(String name) {
		this.allQuestions = new ArrayList<Question>();
		examName = ("Exam- " + name + " " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

	}

	// clone C'tor
	public Exam(Exam exam) {
		this.allQuestions = new ArrayList<Question>(exam.allQuestions);
		examName = (exam.getExamName());

	}

	public void setExamName(String name) {
		this.examName = name;
	}

	// add Q
	public boolean addQuestion(Question q) throws DataIdenticalException {
		isQuestionExist(q);
		this.allQuestions.add(q);
		return true;
	}

	// Check if the question exists
	public void isQuestionExist(Question q) throws DataIdenticalException {
		if (this.allQuestions.contains(q)) {
			throw new DataIdenticalException("question");
		}
		/*
		 * for (int i = 0; i < numOfQuestions; i++) if (q.equals(this.allQuestions[i]))
		 * { throw new DataIdenticalException("question"); }
		 * 
		 * return false;
		 */

	}

	// Check if the content exists
	public int isContentExist(String content) {
		for (int i = 0; i < allQuestions.size(); i++)
			if (content.equals(allQuestions.get(i).getContent()))
				return i;

		return -1;
	}

	// Get Question by
	public Question getQuestion(int num) {
		return allQuestions.get(num - 1);
	}

	// toString
	public String toString() {// To String - print;
		StringBuffer sBuffer = new StringBuffer();

		sBuffer.append(this.examName + " has: " + this.allQuestions.size() + " questions. \n");

		sBuffer.append("Exam details: \n");
		for (int i = 0; i < this.allQuestions.size(); i++) {
			sBuffer.append(allQuestions.get(i).toString());
		}
		sBuffer.append("\n -------- END OF EXAM -------- \n");

		return sBuffer.toString();
	}

	public String getExamName() {
		return this.examName;
	}

	public int getNumOfQuestions() {
		return allQuestions.size();
	}

	// Print Exams Name and Num
	public String getListOfQuestions() {// To String - print;
		StringBuffer sBuffer = new StringBuffer();

		sBuffer.append("There are " + this.allQuestions.size() + " Questions: \n");

		for (int i = 0; i < this.allQuestions.size(); i++) {
			sBuffer.append((i + 1) + ") " + allQuestions.get(i).getContent() + "\n");
		}
		return sBuffer.toString();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Exam))
			return false;

		return ((Exam) object).getExamName().toLowerCase().equals(this.examName.toLowerCase());

	}

	public void saveExamDeatails() throws FileNotFoundException {

		PrintWriter pwQuestions = new PrintWriter(examName + ".txt");
		PrintWriter pwAnswer = new PrintWriter("Solution for " + examName + ".txt");

		for (int i = 0; i < this.allQuestions.size(); i++) {
			pwQuestions.println("Question number " + (i + 1) + ": " + allQuestions.get(i).saveQuestion());
			pwAnswer.println("Answer to question number " + (i + 1) + ": " + allQuestions.get(i).saveAnswer());

		}
		pwAnswer.close();
		pwQuestions.close();

	}

	public void sortQestionsByAnswersLength() {
		Collections.sort(this.allQuestions);

	}

	public Exam clone() throws CloneNotSupportedException { // not in use
		Exam tempExam = (Exam) super.clone();
		tempExam.allQuestions = new ArrayList<Question>(this.allQuestions);
		return tempExam;

	}

}
