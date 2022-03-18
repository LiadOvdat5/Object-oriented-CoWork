import java.util.Arrays;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

import javax.print.attribute.standard.DateTimeAtCreation;




public class Manager {
	
	Scanner scanner = new Scanner(System.in); 

	private Exam [] allExams;
	private int numOfExams;
	private Question[] questionsArray; 
	
	
	//C'tor of the manager
	public Manager() {
		this.allExams = new Exam[1];
		this.numOfExams = 0;
	}
	
	
	//create Exam in blank space or increase the array by 2.
	public void ExamSpace(String name) {
		if(numOfExams == allExams.length-1) 
			allExams = Arrays.copyOf(allExams, numOfExams*2);
		allExams[numOfExams] = new Exam(name);
		numOfExams++;
		
		
	}
	
	//generate new Exam manually 
	public void generateExam(String nameOfExam) { //(Scheme) EXAM > QUES ARRAY > QUES (AMERICAN OR OPEN) > IF AMERICAN - ANS ARRAY 
		ExamSpace(nameOfExam); //Create Exam
		Exam currentExam = allExams[numOfExams-1]; //pointer

		System.out.println("how many american questions you want? "); 
		int numOfAmerican = scanner.nextInt();
		
		System.out.println("how many open questions you want? ");
		int numOfOpen = scanner.nextInt();

		for(int i = 0; i < numOfAmerican; i++) { // loop of American Q
			System.out.println("What is the American question, question content ? ");
			String qContent = scanner.next();
			
			System.out.println("how many asnswers? (up to 8) "); //Option for exception - no more than 8 answers provided.
			int numOfAnswers = scanner.nextInt();
			Answer[] ansArray = new Answer[numOfAnswers];
			
			for(int j = 0; j < numOfAnswers; j++) { // loop of answers for American - array of answers
				System.out.println("Answer num " + (i+1) + " content and true or false");
				String aContent = scanner.next();
				Boolean trueOrFalse = scanner.nextBoolean(); 
				
				ansArray[j] = new Answer(aContent, trueOrFalse);
				
			}
			
			currentExam.addQuestion(new AmericanQuestions(qContent, ansArray)); //add American question to exam
			
		}
		
		for(int i = 0; i < numOfOpen; i++) { // loop of Open Q
			System.out.println("What is the Open question, question content ? ");
			String qContent = scanner.next();
			
			System.out.println("Answer content");
			String aContent = scanner.next();
			
			currentExam.addQuestion(new OpenQuestion(qContent, aContent)); //add American question to exam
			
		}
			
		System.out.println(currentExam.toString()); 
		
	}
	
	
	public void questionsRepository() {
		questionsArray = new Question[20];
		
		Answer[] ansArray = new Answer[2]; //Answer Array
		ansArray[0] = new Answer("", false);
		ansArray[1] = new Answer("", false);
		
		//0-9
		questionsArray[0] = new AmericanQuestions("content", ansArray); //Insert new American Q
		
		
		//10-19
		questionsArray[1] = new OpenQuestion("", "");
		
		
		
	}
	
	
}
