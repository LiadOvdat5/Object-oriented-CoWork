import java.util.Currency;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class Main {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		input.useDelimiter(System.getProperty("line.separator"));
		
		int selectedNumber;
		final int EXIT = 8;
		Manager manager = new Manager();

		System.out.println(
				"Please Enter the number of the program you want to exam: \r\n" 
						+ "1 - Present all exams (all Q&A) \r\n" // V
						+ "2 - Add Question (to the Exam and or data base) \r\n" // V
						+ "3 - Update content of an existing question\r\n" // V
						+ "4 - Update content of an existing answer \r\n" // V
						+ "5 - delete an answer to an existing question \r\n" + "6 - Create exam manually \r\n" // V
						+ "7 - Create exam automatically \r\n" // V
						+ "8 - Exit");

		selectedNumber = input.nextInt();

		while (selectedNumber != EXIT) {
			switch (selectedNumber) {

			case 1: { // Present all exams (all Q&A)
				System.out.println(manager.printAllQuestions());
				break;
			}

			case 2: { // Add Question (to the Exam and or data base)
				addQuestion(manager);
				break;
			}

			case 3: { // Update content of an existing question
				UpdateContentOfQuestion(manager);
				break;
			}

			case 4: { // Update content of an existing answer
				UpdateContentOfAnswer(manager);
				break;
			}

			case 5: { // delete answer to existing question
				deleteAnswerFromQuestion(manager);
				break;
			}

			case 6: { // Create exam manually
				createExamManually(manager);
				break;
			}

			case 7: { // Create exam automatically
				System.out.println("How many questions you want? (up to 20) ");
				int numOfQ = input.nextInt();
				manager.generateAutomaticExam(numOfQ);
				break;
			}
			case 8: {

			}
			}

			System.out.println("Please Enter the number of the program you want to exam: \r\n"
					+ "1 - Present all exams (all Q&A) \r\n" + "2 - Add Question to the Exam \r\n"
					+ "3 - Update content of an existing question\r\n" + "4 - Update content of an existing answer \r\n"
					+ "5 - Delete an answer to an existing question \r\n" + "6 - Create exam manually \r\n"
					+ "7 - Create exam automatically \r\n" + "8 - Exit");
			selectedNumber = input.nextInt();

		}

		System.out.println("\n \n");
		System.out.println("Hope you enjoined the program !");

	}

	// Create American Q
	public static int createAmericanQ(Manager manager) {
		System.out.println("What is the American question, question content ? ");
		String qContent = input.next();

		System.out.println("how many answers? (up to 8) "); // Option for exception - no more than 8 answers provided
		int numOfAnswers = input.nextInt();

		Answer[] ansArray = new Answer[numOfAnswers]; // create array of answers

		for (int j = 0; j < numOfAnswers; j++) { // loop of answers for American - array of answers
			System.out.println("Answer num " + (j + 1) + " content:");
			String aContent = input.next();
			System.out.println("true or false");
			Boolean trueOrFalse = input.nextBoolean();

			ansArray[j] = new Answer(aContent, trueOrFalse);

		}
		manager.addAmericanQToArray(qContent, ansArray);
		return manager.getNumOfQ();
	}

	// Create Open Q
	public static int createOpenQ(Manager manager) {

		System.out.println("What is the Open question, question content ? ");
		String qContent = input.next();

		System.out.println("Answer content");
		String aContent = input.next();

		manager.addOpenQToArray(qContent, aContent);
		return manager.getNumOfQ();
	}

	

	// Select Question
	public static int selectQuestion(Manager manager) {
		int choice = -1, numOfQ = manager.getNumOfQ();
		if (numOfQ == 0) {
			System.out.println("There are no questions \n");
			return -1;
		}
		System.out.println("Which Question whould you like to choose?");
		System.out.println(manager.getListOfQuestions()); // print all questions
		choice = input.nextInt();

		while (choice < 1 || choice > numOfQ) {
			System.out.println("Choose proper question");
			choice = input.nextInt();
		}
		return choice;
	}

	
	// Select American Answer
	public static int selectAmericanAnswer(AmericanQuestions q) { 
		int choice = -1, numOfA = q.getNumOfAnswers();

		System.out.println("Which Answer whould you like to choose?");
		System.out.println(q.printAnswers()); // print all questions
		choice = input.nextInt();

		while (choice < 1 || choice > numOfA) {
			System.out.println("Choose proper question");
			choice = input.nextInt();
		}
		return choice;
	
	}
	
	// 2 Add Question (to the Exam and or data base)
	public static void addQuestion(Manager manager) {
		int currentExam = -1;
		int questionLocation = -1;
		while (questionLocation != 1 && questionLocation != 2) {
			System.out.println("Would you like to: \n1) Add new question to Exam  \n2) Add Question from database"
					+ " to exam \n3) Add question Only to Database  ");
			questionLocation = input.nextInt();
		}
		if (questionLocation == 1 || questionLocation == 2) {
			currentExam = 1; //selectExam(manager);
			if (currentExam == -1)
				questionLocation = 3;

		}

		if (questionLocation == 2) {
			int selectQuestion = selectQuestion(manager);
			manager.addQestionToExam(currentExam, selectQuestion);
		}

		int choice = -1;
		while (choice != 1 && choice != 2) {
			System.out.println("Press 1 for American Q and 2 for Open Q");
			choice = input.nextInt();
		}

		if (questionLocation == 1 && choice == 1)
			manager.addQestionToExam(currentExam, createAmericanQ(manager));
		else if (questionLocation == 1 && choice == 2)
			manager.addQestionToExam(currentExam, createOpenQ(manager));
		else if (questionLocation == 3 && choice == 1)
			createAmericanQ(manager);
		else if (questionLocation == 3 && choice == 2)
			createOpenQ(manager);

	}

	// 3 Update content of an existing question
	public static void UpdateContentOfQuestion(Manager manager) {

		int selectedQuestion = selectQuestion(manager); // Select question
		if (selectedQuestion == -1)
			return;

		boolean keepasking = true;
		while (keepasking) {
			System.out.println("enter the updated question (no need to put a '?' at the end)");
			String content = input.next();
			if (manager.updateQuestionContent(content, selectedQuestion)) {
				System.out.println("question " + selectedQuestion + " updated successfully! \n");
				keepasking = false;
			} else {
				System.out.println("there is already a question in the exam with the same content \n");
			}
		}
	}

	// 4 Update content of an existing answer
	public static void UpdateContentOfAnswer(Manager manager) {

		int selectedQuestion = selectQuestion(manager); // Select question
		if (selectedQuestion == -1)
			return;
		
		Question currentQuestion = manager.getQuestion(selectedQuestion); //current question
		
		System.out.println("Please insert your new Answer content (make sure its a different one!):");
		String newAnswer = input.next(); //answer string 
		
		boolean succeeded;
		if(currentQuestion instanceof OpenQuestion) //if question is open Q
			succeeded =  manager.updateOpenAnswer(selectedQuestion, newAnswer);
		else {  									//if question is American Q
			int aNum = selectAmericanAnswer((AmericanQuestions)currentQuestion);
			succeeded =  manager.updateAmericanAnswer(selectedQuestion, aNum, newAnswer);
		}
		
		if(!succeeded)
			System.out.println("Your answer was identical");
		else {
			System.out.println("Your answer updated");
		}
		
	}

	// 5 delete an answer to an existing question
	public static void deleteAnswerFromQuestion(Manager manager) {
		

		int selectedQuestion = selectQuestion(manager); // Select question
		if (selectedQuestion == -1)
			return;

		
		
		if (manager.getQuestion(selectedQuestion) instanceof OpenQuestion) {
			System.out.println(
					"Deleting the answer of Open question will delete the question, for not deleting press 1, otherwise press 2");
			if (input.nextInt() == 1) {
				return;
			} else {
				if (manager.deleteQuestion(selectedQuestion))
					System.out.println("The question deleted succesfully! ");
				else 
					System.out.println("The was not question deleted ");
			}

		} else if (manager.getQuestion(selectedQuestion) instanceof AmericanQuestions) {
			int aNum = selectAmericanAnswer((AmericanQuestions)(manager.getQuestion(selectedQuestion)));
			AmericanQuestions tempAmericanQuestion = (AmericanQuestions)manager.getQuestion(selectedQuestion);

			

			Boolean deleted = tempAmericanQuestion.deleteAnswer(aNum);
			if (deleted)
				System.out.println("The answer deleted succesfully! ");

			if (tempAmericanQuestion.getNumOfAnswers() <= 2) { // TODO: relocate in Manager ?
				System.out.println("There are only auto answers, Question is deleted! ");
				manager.deleteQuestion(selectedQuestion);
			}

		}

	}

	// 6
	public static void createExamManually(Manager manager) {
		System.out.println("pick a name for the exam");
		String examName = input.next();

		System.out.println("how many american questions you want? ");
		int numOfAmerican = input.nextInt();

		System.out.println("how many open questions you want? ");
		int numOfOpen = input.nextInt();

		Question[] qArray = new Question[numOfAmerican + numOfOpen];

		for (int i = 0; i < numOfAmerican; i++) { // loop of American Q

			qArray[i] = manager.getQuestion(createAmericanQ(manager)); // add American question to qArray
		}

		for (int i = numOfAmerican; i < qArray.length; i++) // loop of Open Q
			qArray[i] = manager.getQuestion(createOpenQ(manager)); // add Open question to exam

		manager.generateExam(examName, qArray);
	}

}
