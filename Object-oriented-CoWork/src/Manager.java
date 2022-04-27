import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.jar.Attributes.Name;
import java.util.zip.CheckedInputStream;

import javax.print.attribute.standard.DateTimeAtCreation;

public class Manager {

	Scanner scanner = new Scanner(System.in);

	private Exam[] allExams;
	private int numOfExams;
	private Question[] questionsArray;
	private int numOfQuestions;

	// C'tor of the manager
	public Manager() {
		this.allExams = new Exam[1];
		this.numOfExams = 0;
		this.numOfQuestions = 0;
		questionsRepository();
	}

	// create Exam in blank space or increase the array by 2.
	public void ExamSpace(String name) {
		if (numOfExams == allExams.length)
			allExams = Arrays.copyOf(allExams, numOfExams * 2);
		allExams[numOfExams] = new Exam(name);
		numOfExams++;

	}

	// generate new Exam manually
	public void generateExam(String nameOfExam, Question[] dataFromUser) { // (Scheme) EXAM > QUES ARRAY > QUES
																			// (AMERICAN OR OPEN) > IF AMERICAN
		// - ANS ARRAY
		ExamSpace(nameOfExam); // Create Exam
		Exam currentExam = allExams[numOfExams - 1]; // pointer
		for (int i = 0; i < dataFromUser.length; i++) // loop that adds the questions from the user to the current exam.
		{
			currentExam.addQuestion(dataFromUser[i]);
		}

		System.out.println(currentExam.toString());

	}

	public void questionsRepository() {
		questionsArray = new Question[20];

		Answer[] ansArray = new Answer[2]; // Answer Array

		// 0-9 Q0
		ansArray[0] = new Answer("Kabul", true);
		ansArray[1] = new Answer("Nassau", false);

		addAmericanQToArray("What is the capital of Afghanistan", ansArray); // Insert new American Q

		// 0-9 Q1
		ansArray[0] = new Answer("Prague", false);
		ansArray[1] = new Answer("Zagreb", true);

		addAmericanQToArray("What is the capital of Belarus", ansArray); // Insert new American Q

		// 0-9 Q2
		ansArray[0] = new Answer("vienna", false);
		ansArray[1] = new Answer("Belmopan", false);

		addAmericanQToArray("What is the capital of Croatia", ansArray); // Insert new American Q

		// 0-9 Q3
		ansArray[0] = new Answer("Tallinn", true);
		ansArray[1] = new Answer("Helsinki", false);

		addAmericanQToArray("What is the capital of Estonia", ansArray); // Insert new American Q

		// 0-9 Q4
		ansArray[0] = new Answer("Berlin", true);
		ansArray[1] = new Answer("Munich", false);

		addAmericanQToArray("What is the capital of Germany", ansArray); // Insert new American Q

		// 0-9 Q5
		ansArray[0] = new Answer("Copenhagen", true);
		ansArray[1] = new Answer("Odense", false);

		addAmericanQToArray("What is the capital of Denemark", ansArray); // Insert new American Q

		// 0-9 Q6
		ansArray[0] = new Answer("Stockholm", true);
		ansArray[1] = new Answer("Dublin", false);

		addAmericanQToArray("What is the capital of Sweden", ansArray); // Insert new American Q

		// 0-9 Q7
		ansArray[0] = new Answer("Lima", true);
		ansArray[1] = new Answer("Asuncion", false);

		addAmericanQToArray("What is the capital of Peru", ansArray); // Insert new American Q

		// 0-9 Q8
		ansArray[0] = new Answer("Zurich", false);
		ansArray[1] = new Answer("Geneve", false);

		addAmericanQToArray("What is the capital of Switzerland", ansArray); // Insert new
																				// American Q

		// 0-9 Q9
		ansArray[0] = new Answer("Beijing", true);
		ansArray[1] = new Answer("Hong Kong", false);

		addAmericanQToArray("What is the capital of China", ansArray); // Insert new American Q

		// 10-19 Q10
		addOpenQToArray("Who was the first prime minister of Israel", "David Ben Gurion");

		// 10-19 Q11
		addOpenQToArray("In what year did World War II end", "1945");

		// 10-19 Q12
		addOpenQToArray("What color do we get for mixing red, blue and yellow", "Black");

		// 10-19 Q13
		addOpenQToArray("What is the name of Queen's lead singer", "freddie mercury");

		// 10-19 Q14
		addOpenQToArray("In what year was the Yom Kipuer War", "1973");

		// 10-19 Q15
		addOpenQToArray("What is the National fruit of India", "Mango");

		// 10-19 Q16
		addOpenQToArray("Name the longest river on Earth", "Nile");

		// 10-19 Q17
		addOpenQToArray("Who gave the universal law of gravitation", "Issac Newton");

		// 10-19 Q18
		addOpenQToArray("Name the National game of the USA", "Baseball");

		// 10-19 Q19
		addOpenQToArray("What is the National food of Israel", "Falafel");

	}

	// generate automatic Exam
	public void generateAutomaticExam(int numOfQ) {
		ExamSpace("Auto Exam - General Knowledge"); // Create Exam
		Exam currentExam = allExams[numOfExams - 1]; // pointer

		int max = questionsArray.length - 1, min = 0;
		for (int i = 0; i < numOfQ; i++) { // loop of Q
			boolean b = false;
			while (!b) {
				Question question = questionsArray[(int) (Math.random() * (max - min + 1) + min)];
				b = currentExam.addQuestion(question);
			}

		}

		System.out.println(currentExam.toString());

	}

	// Print Exams Name and Num
	public String getListOfExams() {// To String - print;
		StringBuffer sBuffer = new StringBuffer();

		sBuffer.append("There are " + this.numOfExams + " Exams: \n");

		for (int i = 0; i < numOfExams; i++) {
			sBuffer.append((i + 1) + ") " + allExams[i].getExamName() + "\n");
		}
		return sBuffer.toString();
	}

	// Print Questions Name and Num
	public String getListOfQuestions() {// To String - print;
		StringBuffer sBuffer = new StringBuffer();

		sBuffer.append("There are " + this.numOfQuestions + " Questions: \n");

		for (int i = 0; i < numOfQuestions; i++) {
			sBuffer.append((i + 1) + ") " + questionsArray[i].getContent() + "\n");
		}
		return sBuffer.toString();
	}

	// Get Num of exams
	public int getNumOfExams() {
		return this.numOfExams;
	}

	// Get Num of Questions
	public int getNumOfQ() {
		return this.numOfQuestions;
	}

	// Get Exam
	public Exam getExam(int numOfExam) {
		return this.allExams[numOfExam - 1];
	}

	// check if question Exists
	public boolean isQuestionExist(Question q) {
		for (int i = 0; i < numOfQuestions; i++)
			if (questionsArray[i].equals(q))
				return true;

		return false;

	}

	// add American Question to questions array
	public boolean addAmericanQToArray(String qContent, Answer[] ansArray) {
		AmericanQuestions tempQ = new AmericanQuestions(qContent, ansArray);
		if (isQuestionExist(tempQ))
			return false;
		if (numOfQuestions == questionsArray.length)
			questionsArray = Arrays.copyOf(questionsArray, numOfQuestions * 2);

		questionsArray[numOfQuestions] = tempQ;
		numOfQuestions++;
		return true;

	}

	// add American Question to questions array
	public boolean addOpenQToArray(String qContent, String aContent) {
		OpenQuestion tempQ = new OpenQuestion(qContent, aContent);
		if (isQuestionExist(tempQ))
			return false;
		if (numOfQuestions == questionsArray.length)
			questionsArray = Arrays.copyOf(questionsArray, numOfQuestions * 2);

		questionsArray[numOfQuestions] = tempQ;
		numOfQuestions++;
		return true;

	}

	// add question to exam
	public boolean addQestionToExam(int numOfExam, int numOfQ) {
		return allExams[numOfExam].addQuestion(questionsArray[numOfQ - 1]);
	}

	// Check if the content exists
	public int isContentExist(String content) {
		for (int i = 0; i < numOfQuestions; i++)
			if (content.equals(questionsArray[i].getContent()))
				return i;

		return -1;
	}
	
	// update Q content
	public boolean updateQuestionContent(String content, int num) {
		int contentExist = isContentExist(content);
		if (contentExist != -1)
			if (questionsArray[num - 1].getClass().getSimpleName()
					.equals(questionsArray[contentExist].getClass().getSimpleName()))
				return false;

		questionsArray[num - 1].updateContent(content);

		return true;
	}

	
	//Get Question 
	public Question getQuestion(int num) {
		return questionsArray[num-1];
	}
	
	//update Open answer
	public boolean updateOpenAnswer(int qNum, String aContent) {
		return ((OpenQuestion)questionsArray[qNum]).updateAnswer(aContent);	
	}
	

	//update American answer
	public boolean updateAmericanAnswer(int qNum,int aNum ,String aContent) {
		return ((AmericanQuestions)questionsArray[qNum]).updateAnswer(aContent, aNum);	
	}
	
	
	// to string
	public String toString() {// To String - print;
		StringBuffer sBuffer = new StringBuffer();

		sBuffer.append("There are " + this.numOfExams + " Exams: \n");

		for (int i = 0; i < numOfExams; i++) {
			sBuffer.append(allExams[i].toString());
		}

		return sBuffer.toString();
	}


}
