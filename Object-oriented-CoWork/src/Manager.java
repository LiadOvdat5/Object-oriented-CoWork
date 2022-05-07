import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.jar.Attributes.Name;
import java.util.zip.CheckedInputStream;

import javax.print.attribute.standard.DateTimeAtCreation;
import javax.xml.transform.Templates;
/*
public class Manager implements Serializable{

	Scanner scanner = new Scanner(System.in);

	private ArrayList <Exam> allExams;
	private ArrayList <Question> questionsArray;


	// C'tor of the manager
	public Manager() {
		this.allExams = new ArrayList<Exam>();
		this.questionsArray = new ArrayList<Question>();
		
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void inputDataFromBinary() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("questions.dat"));
		questionsArray = (ArrayList<Question>) inFile.readObject();
		inFile.close();
		for(int i = 0; i < this.questionsArray.size(); i++) //because its at start we need to say how many questions we have
			if(questionsArray[i] != null)
				numOfQuestions++;
	}
	
	// create Exam in blank space or increase the array by 2.
	public void AddExamToArray(Exam exam) {
		this.allExams.add(exam);
		exam.sortQestionsByAnswersLength();

	}

	

	public void questionsRepository() throws DataIdenticalException {
		

		ArrayList<Answer> ansArray = new ArrayList<Answer>(2); // Answer Array

		// 0-9 Q0
		ansArray.set(0, new Answer("Kabul", true));
		ansArray.set(1, new Answer("Nassau", false));

		addAmericanQToRepository("What is the capital of Afghanistan", ansArray); // Insert new American Q

		// 0-9 Q1
		ansArray.set(0, new Answer("Prague", false));
		ansArray.set(1, new Answer("Zagreb", true));

		addAmericanQToRepository("What is the capital of Belarus", ansArray); // Insert new American Q

		// 0-9 Q2
		ansArray.set(0, new Answer("vienna", false));
		ansArray.set(1, new Answer("Belmopan", false));

		addAmericanQToRepository("What is the capital of Croatia", ansArray); // Insert new American Q

		// 0-9 Q3
		ansArray.set(0, new Answer("Tallinn", true));
		ansArray.set(1, new Answer("Helsinki", false));

		addAmericanQToRepository("What is the capital of Estonia", ansArray); // Insert new American Q

		// 0-9 Q4
		ansArray.set(0, new Answer("Berlin", true));
		ansArray.set(1, new Answer("Munich", false));

		addAmericanQToRepository("What is the capital of Germany", ansArray); // Insert new American Q

		// 0-9 Q5
		ansArray.set(0, new Answer("Copenhagen", true));
		ansArray.set(1, new Answer("Odense", false));

		addAmericanQToRepository("What is the capital of Denemark", ansArray); // Insert new American Q

		// 0-9 Q6
		ansArray.set(0, new Answer("Stockholm", true));
		ansArray.set(1, new Answer("Dublin", false));

		addAmericanQToRepository("What is the capital of Sweden", ansArray); // Insert new American Q

		// 0-9 Q7
		ansArray.set(0, new Answer("Lima", true));
		ansArray.set(1, new Answer("Asuncion", false));

		addAmericanQToRepository("What is the capital of Peru", ansArray); // Insert new American Q

		// 0-9 Q8
		ansArray.set(0, new Answer("Zurich", false));
		ansArray.set(1, new Answer("Geneve", false));

		addAmericanQToRepository("What is the capital of Switzerland", ansArray); // Insert new
																				// American Q

		// 0-9 Q9
		ansArray.set(0, new Answer("Beijing", true));
		ansArray.set(1, new Answer("Hong Kong", false));

		addAmericanQToRepository("What is the capital of China", ansArray); // Insert new American Q

		// 10-19 Q10
		addOpenQToRepository("Who was the first prime minister of Israel", "David Ben Gurion");

		// 10-19 Q11
		addOpenQToRepository("In what year did World War II end", "1945");

		// 10-19 Q12
		addOpenQToRepository("What color do we get for mixing red, blue and yellow", "Black");

		// 10-19 Q13
		addOpenQToRepository("What is the name of Queen's lead singer", "freddie mercury");

		// 10-19 Q14
		addOpenQToRepository("In what year was the Yom Kipuer War", "1973");

		// 10-19 Q15
		addOpenQToRepository("What is the National fruit of India", "Mango");

		// 10-19 Q16
		addOpenQToRepository("Name the longest river on Earth", "Nile");

		// 10-19 Q17
		addOpenQToRepository("Who gave the universal law of gravitation", "Issac Newton");

		// 10-19 Q18
		addOpenQToRepository("Name the National game of the USA", "Baseball");

		// 10-19 Q19
		addOpenQToRepository("What is the National food of Israel", "Falafel");

	}

	// generate automatic Exam
	public void generateAutomaticExam(int numOfQ, String examName) throws DataIdenticalException {
		Exam automaticExam = new Exam(examName) ; // new exam
		isExamNameExists(automaticExam);
		
		int max = questionsArray.size() - 1, min = 0;
		for (int i = 0; i < numOfQ; i++) { 
			Question question = questionsArray.get((int) (Math.random() * (max - min + 1) + min));
			try {
				if(question instanceof AmericanQuestion)
					if(((AmericanQuestion)question).getNumOfAnswers() > 4 || (((AmericanQuestion)question).getMoreThanOneRight()))
						throw new Exception();
				addQuestionToExam(automaticExam, question);
			} catch (Exception e) {
				i--;
			}
		}
		
		AddExamToArray(automaticExam);
		
		
	}
	
	
	
	public void saveExamToFile(Exam exam) throws FileNotFoundException
	{
		exam.saveExamDeatails();
		
	}

	// Print Exams Name and Num
	public String getListOfExams() throws DataNotCreatedYetException {// To String - print;
		if (this.numOfExams == 0) 
			throw new DataNotCreatedYetException("Exams");
		
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
		for (int i = 0; i < this.numOfQuestions; i++)
			if (this.questionsArray[i].equals(q))
				return true;

		return false;

	}
	
	// check if question Exists
	public boolean isExamNameExists(Exam exam) throws DataIdenticalException {
		for (int i = 0; i < this.numOfExams; i++)
			if (this.allExams[i].equals(exam))
				throw new DataIdenticalException("exam name");
		return false;

		}

	
	public Question addAmericanQToRepository(String qContent, ArrayList<Answer> ansArray) throws DataIdenticalException {
		for(int i = 0; i < ansArray.length; i++) {
			for(int j = i+1; j < ansArray.length; j++) {
				if(ansArray[i].equals((ansArray[j]))){
					throw new DataIdenticalException("Answer");
				}
			}
		}
		AmericanQuestion tempQ = new AmericanQuestion(qContent, ansArray);
		return checkIfCanAddQuestionAndAddIfPossible(tempQ);
		
	}

	// add American Question to questions array
	public Question addOpenQToRepository(String qContent, String aContent) throws DataIdenticalException {
		OpenQuestion tempQ = new OpenQuestion(qContent, aContent);
		return checkIfCanAddQuestionAndAddIfPossible(tempQ);
		
	}
	public Question checkIfCanAddQuestionAndAddIfPossible(Question q) throws DataIdenticalException
	{
		if (isQuestionExist(q))
			throw new DataIdenticalException("question");
		if (this.numOfQuestions == this.questionsArray.length)
			this.questionsArray = Arrays.copyOf(this.questionsArray, this.numOfQuestions * 2);

		this.questionsArray[this.numOfQuestions++] = q;
		return q;
	}
	

	

	// add question to exam
	public boolean addQuestionToExam(Exam exam, Question question) throws DataIdenticalException {
		return exam.addQuestion(question);
	}

	// Check if the content exists
	public int isContentExist(String content) {
		for (int i = 0; i < this.numOfQuestions; i++)
			if (content.toLowerCase().equals(this.questionsArray[i].getContent().toLowerCase()))
				return i;

		return -1;
	}
	
	// update Q content
	public boolean updateQuestionContent(String content, Question question) throws DataIdenticalException {
		OpenQuestion updatedQuestion = new OpenQuestion(content,"");
		if(isQuestionExist(updatedQuestion)) {
			throw new DataIdenticalException("question");
		}
		return question.updateContent(content);
	}
	
	
	//Get Question 
	public Question getQuestion(int num) {
		return questionsArray[num-1];
	}
	
	public boolean deleteQuestion(int position)
	{
		if(position == this.numOfQuestions - 1)
		{
			this.questionsArray[position - 1] = null;
			this.numOfQuestions--;
		}
		else
		{
			this.questionsArray[position - 1] = null;
			for(int i = position - 1; i < this.numOfQuestions - 1; i++)
			{
				this.questionsArray[i] = this.questionsArray[i+1];
			}
			this.numOfQuestions--;
			
		}
		
		arrangeExams();
		
		return true;
	}
	
	

	//update answer for Open Question 
	public boolean updateOpenQAnswer(Answer answer ,String aContent) {
		return answer.checkContent(aContent);	
	}
	
	//update answer for American Question 
	public boolean updateAmericanQAnswer(Answer answer ,String aContent, boolean isRight, AmericanQuestion question) throws DataIdenticalException {
		Answer updatedAnswer = new Answer(aContent,isRight);
		
		if(question.isAnswerExists(updatedAnswer)){
			throw new DataIdenticalException("Answer");
		}
		
		if(!isRight && answer.isRight) { //if new answer is false but original was right
			question.RemoveAndDecreaseRightAnswersCounter(answer);
			answer.setFalse();
		}
		else if(isRight && !answer.isRight) { //if new answer is right but original was false
			question.increaseRightAnswersCounter(answer);
			answer.setRight();
		} 
		
		question.checkForTrueAnswer();
		
		 answer.setContent(aContent);
		 return true;
	}
	
	public void arrangeExams() {
		for(int i = 0; i < numOfExams; i++)
			allExams[i].arrangeExamQuestions();
	}
		
	// Select Exam
	public 	Exam selectExam(int examNum) throws DataNotCreatedYetException, InvalidUserInputException {
		if (this.numOfExams == 0) 
			throw new DataNotCreatedYetException("Exams");
		
		if(examNum < 1 || examNum > this.numOfExams) 
			throw new InvalidUserInputException("Exams");
		
		return allExams[examNum-1];
	}	
	
	// Select Question
	public Question selectQuestion(int qNum) throws InvalidUserInputException {
		
		
		if(qNum < 1 || qNum > this.numOfQuestions) 
			throw new InvalidUserInputException("question");
		
		return questionsArray[qNum-1];
	}	
	
	// Select American Answer
	public Answer selectAmericanAnswerandReturnAnswer(AmericanQuestion question,int aNum) throws InvalidUserInputException {
		
		checkAmericanAnswer(question, aNum);
		return question.getAnswer(aNum);

		}
	
	public void checkAmericanAnswer(AmericanQuestion question, int aNum)throws InvalidUserInputException{
	
		if(aNum < 1 || aNum > question.getNumOfAnswers() - 2) 
			throw new InvalidUserInputException("answer");
		
	}
	
	
	public boolean deleteAmericanQAnswer(AmericanQuestion q , int qPosition, Answer answer,int aPosition)
	{
		if(q.getNumOfAnswers() == 3)
		{
			  deleteQuestion(qPosition);
			  return false;
		}
		else
		{
			return q.deleteAmericanAnswer(answer,aPosition);
		}
	}
	
	//Select 1 for American 2 for open
	public int checkValidRange(int typeN, int min, int max) throws InvalidUserInputException {
		if(typeN < min || typeN > max)
			throw new InvalidUserInputException("Option");
		return typeN;
	}
	
	// to string
	public String printAllQuestions()  throws DataNotCreatedYetException {// To String - print;
		StringBuffer sBuffer = new StringBuffer();
		if (this.numOfQuestions == 0) 
			throw new DataNotCreatedYetException("questions");

		sBuffer.append("There are " + this.numOfQuestions + " Questions: \n");

		for (int i = 0; i < numOfQuestions; i++) {
			sBuffer.append((i + 1) + ") " + questionsArray[i].toString() + "\n");
		}
		return sBuffer.toString();
	}

	public String printExams()throws DataNotCreatedYetException 
	{
		String str = "";
		if (this.numOfExams == 0) 
			throw new DataNotCreatedYetException("exams");
		
		for(int i = 0; i < this.numOfExams; i++)
		{
			 str += this.allExams[i].toString();
		}
		return str;
	}

	public void exit() throws FileNotFoundException, IOException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("questions.dat"));
		outFile.writeObject(questionsArray);
		outFile.close();
	}
	
	public void cloneExam(Exam exam) throws CloneNotSupportedException {
		
		AddExamToArray(exam.clone());
		allExams[numOfExams-1].examName = exam.getExamName() + "  (duplicate)";
		
	
	}
		
	public String setOfAmericanAnswers() throws Exception {
		Set<Answer> americanAnswersSet = new Set<Answer>();
		for(int i = 0; i < numOfQuestions; i++) {
			if(questionsArray[i] instanceof AmericanQuestion) {
				((AmericanQuestion)questionsArray[i]).addAnswersToSet(americanAnswersSet);
			}
		}
		return americanAnswersSet.tosString();
		
	
	}
		
	
	

}

*/