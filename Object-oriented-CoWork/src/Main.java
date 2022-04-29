import java.util.Currency;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class Main {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		input.useDelimiter(System.getProperty("line.separator"));

		Manager manager = new Manager();
		try {
			manager.questionsRepository();
		} catch (Exception e) {
			System.out.println("Repository had some problem");
		}
		

		int selectedNumber;
		final int EXIT = 8;


		System.out.println(
				"Please Enter the number of the program you want to exam: \r\n" + "1 - Present all exams (all Q&A) \r\n" // V
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
				try {
					addQuestion(manager);

				} catch (DataNotCreatedYetException e) {
					System.out.println(e.getMessage());
				} catch (InvalidUserInputException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Number is needed");
					input.nextLine();
				} catch (QuestionIdenticalException e) {
					System.out.println(e.getMessage());
				}
				catch (Exception e) {
					System.out.println("Something went wrong, try again");
					input.nextLine();
				}

				break;
			}

			case 3: { // Update content of an existing question
				try {
					UpdateContentOfQuestion(manager);
				} catch (DataNotCreatedYetException e) {
					System.out.println(e.getMessage());
				} catch (InvalidUserInputException e) {
					System.out.println(e.getMessage());
				} catch (QuestionIdenticalException e) {
					System.out.println(e.getMessage());
				}
				break;
			}

			case 4: { // Update content of an existing answer
				try {
					UpdateContentOfAnswer(manager);
				} catch (DataNotCreatedYetException e) {
					System.out.println(e.getMessage());
				} catch (InvalidUserInputException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
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
	public static Question createAmericanQ(Manager manager)
			throws InvalidUserInputException, QuestionIdenticalException {
		System.out.println("What is the American question, question content ? ");
		String qContent = input.next();

		System.out.println("how many answers? (up to 8) "); // Option for exception - no more than 8 answers provided
		int numOfAnswers = input.nextInt();
		manager.checkValidRange(numOfAnswers, 1, 8);

		Answer[] ansArray = new Answer[numOfAnswers]; // create array of answers

		for (int j = 0; j < numOfAnswers; j++) { // loop of answers for American - array of answers
			System.out.println("Answer num " + (j + 1) + " content:");
			String aContent = input.next();
			System.out.println("true or false");
			Boolean trueOrFalse = input.nextBoolean();

			ansArray[j] = new Answer(aContent, trueOrFalse);

		}

		return manager.addAmericanQToRepository(qContent, ansArray);

	}

	// Create Open Q
	public static Question createOpenQ(Manager manager) throws QuestionIdenticalException {

		System.out.println("What is the Open question, question content ? ");
		String qContent = input.next();

		System.out.println("Answer content");
		String aContent = input.next();

		return manager.addOpenQToRepository(qContent, aContent);

	}

	

	// 2 Add Question (to the Exam and or data base)
	public static void addQuestion(Manager manager) throws DataNotCreatedYetException, InvalidUserInputException,
			QuestionIdenticalException, InputMismatchException {

		System.out.println("Would you like to: \n1) Add new question to Exam  \n2) Add Question from database"
				+ " to exam \n3) Add question Only to Database  ");
		int questionLocation = input.nextInt();
		manager.checkValidRange(questionLocation, 1, 3);

		if (questionLocation == 1 || questionLocation == 2) {
			manager.getListOfExams();
			System.out.println("Please select exam: ");

			Exam currentExam = manager.selectExam(input.nextInt()); // selectExam(manager);
			if (questionLocation == 1) {
				System.out.println("Press 1 for American Q and 2 for Open Q");
				int choice = input.nextInt();
				manager.checkValidRange(choice, 1, 2);
				if (choice == 1)
					manager.addQuestionToExam(currentExam, createAmericanQ(manager));
				else
					manager.addQuestionToExam(currentExam, createOpenQ(manager));
			
			
			} else {
				System.out.println("Please select question: ");
				System.out.println(manager.getListOfQuestions());

				manager.addQuestionToExam(currentExam, manager.selectQuestion(input.nextInt())); // add to current exam
																									// the current
																									// question if
																									// checks

			}
		} else {
			System.out.println("Press 1 for American Q and 2 for Open Q");
			int choice = input.nextInt();
			manager.checkValidRange(choice, 1, 2);
			if (choice == 1)
				createAmericanQ(manager);
			else
				createOpenQ(manager);
		}
		
		return;

	}

	// 3 Update content of an existing question
	public static void UpdateContentOfQuestion(Manager manager) throws DataNotCreatedYetException, InvalidUserInputException, QuestionIdenticalException {
		System.out.println(manager.getListOfQuestions());
		System.out.println("Please select question: ");
		Question selectedQuestion = manager.selectQuestion(input.nextInt());

		System.out.println("enter the updated question content (no need to put a '?' at the end)");
		String content = input.next();
		
		manager.updateQuestionContent(content, selectedQuestion); 		
	}

	// 4 Update content of an existing answer
	public static void UpdateContentOfAnswer(Manager manager) throws DataNotCreatedYetException, InvalidUserInputException {
		System.out.println(manager.getListOfQuestions());
		System.out.println("Please select question: ");
		Question selectedQuestion = manager.selectQuestion(input.nextInt());

		
		
		
		
		
		
		boolean succeeded;
		if (selectedQuestion instanceof OpenQuestion) { // if question is open Q
			System.out.println("Please insert your new Answer content (make sure its a different one!):");
			succeeded = manager.updateOpenQAnswer(((OpenQuestion) selectedQuestion).getAnswer(), input.next());
		} else {											 // if question is American Q
			System.out.println(((AmericanQuestions)selectedQuestion).printAnswers());
			System.out.println("Please select Answer: ");
			Answer selectedAnswer = manager.selectAmericanAnswer((AmericanQuestions)selectedQuestion, input.nextInt());
			System.out.println("Please insert your new Answer content (make sure its a different one!):");
			String newAnswer = input.next();
			System.out.println("true or false");
			Boolean trueOrFalse = input.nextBoolean();
			succeeded = manager.updateAmericanQAnswer(selectedAnswer, newAnswer, trueOrFalse ,(AmericanQuestions)selectedQuestion);
		}

		if (!succeeded)
			System.out.println("Your answer content was identical \n");
		else {
			System.out.println("Your answer updated \n");
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
			int aNum = selectAmericanAnswer((AmericanQuestions) (manager.getQuestion(selectedQuestion)));
			AmericanQuestions tempAmericanQuestion = (AmericanQuestions) manager.getQuestion(selectedQuestion);

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
