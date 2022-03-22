import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

import javax.print.attribute.standard.DateTimeAtCreation;

public class Manager {

	Scanner scanner = new Scanner(System.in);

	private Exam[] allExams;
	private int numOfExams;
	private Question[] questionsArray;

	// C'tor of the manager
	public Manager() {
		this.allExams = new Exam[1];
		this.numOfExams = 0;
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
	public void generateExam(String nameOfExam, Question [] dataFromUser) { // (Scheme) EXAM > QUES ARRAY > QUES (AMERICAN OR OPEN) > IF AMERICAN
													// - ANS ARRAY
		ExamSpace(nameOfExam); // Create Exam
		Exam currentExam = allExams[numOfExams - 1]; // pointer
		for(int i = 0; i < dataFromUser.length; i++) //loop that adds the questions from the user to the current exam.
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

		questionsArray[0] = new AmericanQuestions("What is the capital of Afghanistan", ansArray); // Insert new
																									// American Q

		// 0-9 Q1
		ansArray[0] = new Answer("Prague", false);
		ansArray[1] = new Answer("Zagreb", true);

		questionsArray[1] = new AmericanQuestions("What is the capital of Belarus", ansArray); // Insert new American Q

		// 0-9 Q2
		ansArray[0] = new Answer("vienna", false);
		ansArray[1] = new Answer("Belmopan", false);

		questionsArray[2] = new AmericanQuestions("What is the capital of Croatia", ansArray); // Insert new American Q

		// 0-9 Q3
		ansArray[0] = new Answer("Tallinn", true);
		ansArray[1] = new Answer("Helsinki", false);

		questionsArray[3] = new AmericanQuestions("What is the capital of Estonia", ansArray); // Insert new American Q

		// 0-9 Q4
		ansArray[0] = new Answer("Berlin", true);
		ansArray[1] = new Answer("Munich", false);

		questionsArray[4] = new AmericanQuestions("What is the capital of Germany", ansArray); // Insert new American Q

		// 0-9 Q5
		ansArray[0] = new Answer("Copenhagen", true);
		ansArray[1] = new Answer("Odense", false);

		questionsArray[5] = new AmericanQuestions("What is the capital of Denemark", ansArray); // Insert new American Q

		// 0-9 Q6
		ansArray[0] = new Answer("Stockholm", true);
		ansArray[1] = new Answer("Dublin", false);

		questionsArray[6] = new AmericanQuestions("What is the capital of Sweden", ansArray); // Insert new American Q

		// 0-9 Q7
		ansArray[0] = new Answer("Lima", true);
		ansArray[1] = new Answer("Asuncion", false);

		questionsArray[7] = new AmericanQuestions("What is the capital of Peru", ansArray); // Insert new American Q

		// 0-9 Q8
		ansArray[0] = new Answer("Zurich", false);
		ansArray[1] = new Answer("Geneve", false);

		questionsArray[8] = new AmericanQuestions("What is the capital of Switzerland", ansArray); // Insert new
																									// American Q

		// 0-9 Q9
		ansArray[0] = new Answer("Beijing", true);
		ansArray[1] = new Answer("Hong Kong", false);

		questionsArray[9] = new AmericanQuestions("What is the capital of China", ansArray); // Insert new American Q

		// 10-19 Q10
		questionsArray[10] = new OpenQuestion("Who was the first prime minister of Israel", "David Ben Gurion");

		// 10-19 Q11
		questionsArray[11] = new OpenQuestion("In what year did World War II end", "1945");

		// 10-19 Q12
		questionsArray[12] = new OpenQuestion("What color do we get for mixing red, blue and yellow", "Black");

		// 10-19 Q13
		questionsArray[13] = new OpenQuestion("What is the name of Queen's lead singer", "freddie mercury");

		// 10-19 Q14
		questionsArray[14] = new OpenQuestion("In what year was the Yom Kipuer War", "1973");

		// 10-19 Q15
		questionsArray[15] = new OpenQuestion("What is the National fruit of India", "Mango");

		// 10-19 Q16
		questionsArray[16] = new OpenQuestion("Name the longest river on Earth", "Nile");

		// 10-19 Q17
		questionsArray[17] = new OpenQuestion("Who gave the universal law of gravitation", "Issac Newton");

		// 10-19 Q18
		questionsArray[18] = new OpenQuestion("Name the National game of the USA", "Baseball");

		// 10-19 Q19
		questionsArray[19] = new OpenQuestion("What is the National food of Israel", "Falafel");

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

	//Print Exams Name and Num
	public String getListOfExams() {//To String - print;
		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("There are " + this.numOfExams + " Exams: \n" );
		
		for(int i = 0; i < numOfExams; i++) {
			sBuffer.append((i+1) + ") " + allExams[i].getExamName() + "\n");
		}
		return sBuffer.toString();
	}
	
	//Get Num of exams
	public int getNumOfExams(){
		return this.numOfExams;
	}
		
	//Get Exam
	public Exam getExam(int numOfExam) {
		return this.allExams[numOfExam-1];
	}
	
	
	
	
	//to string
	public String toString() {//To String - print;
		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("There are " + this.numOfExams + " Exams: \n" );
		
		for(int i = 0; i < numOfExams; i++) {
			sBuffer.append(allExams[i].toString());
		}
		
		return sBuffer.toString();
	}
}
