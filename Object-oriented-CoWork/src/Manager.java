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
	}

	// create Exam in blank space or increase the array by 2.
	public void ExamSpace(String name) {
		if (numOfExams == allExams.length - 1)
			allExams = Arrays.copyOf(allExams, numOfExams * 2);
		allExams[numOfExams] = new Exam(name);
		numOfExams++;

	}

	// generate new Exam manually
	public void generateExam(String nameOfExam) { // (Scheme) EXAM > QUES ARRAY > QUES (AMERICAN OR OPEN) > IF AMERICAN
													// - ANS ARRAY
		ExamSpace(nameOfExam); // Create Exam
		Exam currentExam = allExams[numOfExams - 1]; // pointer

		System.out.println("how many american questions you want? ");
		int numOfAmerican = scanner.nextInt();

		System.out.println("how many open questions you want? ");
		int numOfOpen = scanner.nextInt();

		for (int i = 0; i < numOfAmerican; i++) { // loop of American Q
			System.out.println("What is the American question, question content ? ");
			String qContent = scanner.next();

			System.out.println("how many asnswers? (up to 8) "); // Option for exception - no more than 8 answers
																	// provided.
			int numOfAnswers = scanner.nextInt();
			Answer[] ansArray = new Answer[numOfAnswers];

			for (int j = 0; j < numOfAnswers; j++) { // loop of answers for American - array of answers
				System.out.println("Answer num " + (i + 1) + " content and true or false");
				String aContent = scanner.next();
				Boolean trueOrFalse = scanner.nextBoolean();

				ansArray[j] = new Answer(aContent, trueOrFalse);

			}

			currentExam.addQuestion(new AmericanQuestions(qContent, ansArray)); // add American question to exam

		}

		for (int i = 0; i < numOfOpen; i++) { // loop of Open Q
			System.out.println("What is the Open question, question content ? ");
			String qContent = scanner.next();

			System.out.println("Answer content");
			String aContent = scanner.next();

			currentExam.addQuestion(new OpenQuestion(qContent, aContent)); // add American question to exam

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

		questionsArray[0] = new AmericanQuestions("What is the capital of Belarus", ansArray); // Insert new American Q

		// 0-9 Q2
		ansArray[0] = new Answer("vienna", false);
		ansArray[1] = new Answer("Belmopan", false);

		questionsArray[0] = new AmericanQuestions("What is the capital of Croatia", ansArray); // Insert new American Q

		// 0-9 Q3
		ansArray[0] = new Answer("Tallinn", true);
		ansArray[1] = new Answer("Helsinki", false);

		questionsArray[0] = new AmericanQuestions("What is the capital of Estonia", ansArray); // Insert new American Q

		// 0-9 Q4
		ansArray[0] = new Answer("Berlin", true);
		ansArray[1] = new Answer("Munich", false);

		questionsArray[0] = new AmericanQuestions("What is the capital of Germany", ansArray); // Insert new American Q

		// 0-9 Q5
		ansArray[0] = new Answer("Copenhagen", true);
		ansArray[1] = new Answer("Odense", false);

		questionsArray[0] = new AmericanQuestions("What is the capital of Denemark", ansArray); // Insert new American Q

		// 0-9 Q6
		ansArray[0] = new Answer("Stockholm", true);
		ansArray[1] = new Answer("Dublin", false);

		questionsArray[0] = new AmericanQuestions("What is the capital of Sweden", ansArray); // Insert new American Q

		// 0-9 Q7
		ansArray[0] = new Answer("Lima", true);
		ansArray[1] = new Answer("Asuncion", false);

		questionsArray[0] = new AmericanQuestions("What is the capital of Peru", ansArray); // Insert new American Q

		// 0-9 Q8
		ansArray[0] = new Answer("Zurich", false);
		ansArray[1] = new Answer("Geneve", false);

		questionsArray[0] = new AmericanQuestions("What is the capital of Switzerland", ansArray); // Insert new
																									// American Q

		// 0-9 Q9
		ansArray[0] = new Answer("Beijing", true);
		ansArray[1] = new Answer("Hong Kong", false);

		questionsArray[0] = new AmericanQuestions("What is the capital of China", ansArray); // Insert new American Q

		// 10-19 Q10
		questionsArray[1] = new OpenQuestion("Who was the first prime minister of Israel?", "David Ben Gurion");

		// 10-19 Q11
		questionsArray[1] = new OpenQuestion("In what year did World War II end?", "1945");

		// 10-19 Q12
		questionsArray[1] = new OpenQuestion("What color do we get for mixing red, blue and yellow?", "Black");

		// 10-19 Q13
		questionsArray[1] = new OpenQuestion("What is the name of Queen's lead singer?", "freddie mercury");

		// 10-19 Q14
		questionsArray[1] = new OpenQuestion("In what year was the Yom Kipuer War?", "1973");

		// 10-19 Q15
		questionsArray[1] = new OpenQuestion("What is the name the National fruit of India?", "Mango");

		// 10-19 Q16
		questionsArray[1] = new OpenQuestion("Name the longest river on the Earth?", "Nile");

		// 10-19 Q17
		questionsArray[1] = new OpenQuestion("Who gave the universal law of gravitation?", "Issac Newton");

		// 10-19 Q18
		questionsArray[1] = new OpenQuestion("Name the National game of the USA", "Baseball");

		// 10-19 Q19
		questionsArray[1] = new OpenQuestion("What is the National food of Israel?", "Falafel");

	}

	// generate automatic Exam
	public void generateAutomaticExam() {  
		ExamSpace("Auto Exam"); // Create Exam
		Exam currentExam = allExams[numOfExams - 1]; // pointer

		System.out.println("How many questions you want? (up to 20) ");
		int numOfQ = scanner.nextInt();

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
