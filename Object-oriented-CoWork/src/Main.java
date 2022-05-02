import java.util.Currency;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class Main {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws DataNotCreatedYetException, DataIdenticalException {
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
				"Please Enter the number of the program you want to exam: \r\n" + "1 - Present database and exmas (all Q&A) \r\n" // V
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
				try {
					PresentInfo(manager);
			}catch (DataNotCreatedYetException e) {
				System.out.println(e.getMessage());
			} catch (InvalidUserInputException e) {
				System.out.println(e.getMessage());
			}
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
					System.out.println("you were asked to enter a number");
					input.nextLine();
				} catch (DataIdenticalException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
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
				} catch (InputMismatchException e) {
					System.out.println("you were asked to enter a number");
					input.nextLine();
				} catch (DataIdenticalException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println("Something went wrong, try again");
					input.nextLine();
				}

				break;
			}

			case 4: { // Update content of an existing answer
				try {
					UpdateContentOfAnswer(manager);

				} catch (DataNotCreatedYetException e) {
					System.out.println(e.getMessage());
				} catch (InvalidUserInputException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("you were asked to enter a number");
					input.nextLine();
				} catch (DataIdenticalException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println("Something went wrong, try again");
					input.nextLine();
				}

				break;
			}

			case 5: { // delete answer to existing question
				try {
					deleteAnswerFromQuestion(manager);
				} catch (DataNotCreatedYetException e) {
					System.out.println(e.getMessage());
				} catch (InvalidUserInputException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("you were asked to enter a number");
					input.nextLine();
				}
				break;

			}

			case 6: { // Create exam manually
			try {
				createExamManually(manager);
			} catch (DataNotCreatedYetException e) {
				System.out.println(e.getMessage());
			} catch (InvalidUserInputException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("you were asked to enter a number");
				input.nextLine();
			} catch (DataIdenticalException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Something went wrong, try again");
				input.nextLine();
			}

			break;
			}

			case 7: { // Create exam automatically
				System.out.println("How many questions you want? (up to 20) ");
				int numOfQ = input.nextInt();
				//manager.generateAutomaticExam(numOfQ);
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
	//present database / exams:
	public static void PresentInfo(Manager manager) throws InvalidUserInputException, DataNotCreatedYetException
	{
		System.out.println("What would you like to view: \n(1 Repository Questions \n(2 Exams created");
		int option = input.nextInt();
		manager.checkValidRange(option, 1, 2);
		if(option == 1) {
		System.out.println(manager.printAllQuestions());
		}
		else {
			System.out.println(manager.printExams());
		}
		
	}

	// Create American Q
	public static Question createAmericanQ(Manager manager) throws InvalidUserInputException, DataIdenticalException {
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
	public static Question createOpenQ(Manager manager) throws DataIdenticalException, InputMismatchException {

		System.out.println("What is the Open question, question content ? ");
		String qContent = input.next();

		System.out.println("Answer content");
		String aContent = input.next();

		return manager.addOpenQToRepository(qContent, aContent);

	}

	// 2 Add Question (to the Exam and or data base)
	public static void addQuestion(Manager manager) throws DataNotCreatedYetException, InvalidUserInputException,
			DataIdenticalException, InputMismatchException {

		System.out.println("Would you like to: \n1) Add new question to Exam  \n2) Add Question from database"
				+ " to exam \n3) Add question Only to Database  ");
		int questionLocation = input.nextInt();
		manager.checkValidRange(questionLocation, 1, 3);

		if (questionLocation == 1 || questionLocation == 2) {
			
			System.out.println("Please select exam: ");
			System.out.println(manager.getListOfExams());

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
	public static void UpdateContentOfQuestion(Manager manager)
			throws DataNotCreatedYetException, InvalidUserInputException, DataIdenticalException,InputMismatchException {
		System.out.println(manager.getListOfQuestions());
		System.out.println("Please select question: ");
		Question selectedQuestion = manager.selectQuestion(input.nextInt());

		System.out.println("enter the updated question content (no need to put a '?' at the end)");
		String content = input.next();

		manager.updateQuestionContent(content, selectedQuestion);
	}

	// 4 Update content of an existing answer
	public static void UpdateContentOfAnswer(Manager manager)
			throws DataNotCreatedYetException, InvalidUserInputException, DataIdenticalException, InputMismatchException {
		System.out.println(manager.getListOfQuestions());
		System.out.println("Please select question: ");
		Question selectedQuestion = manager.selectQuestion(input.nextInt());

		boolean succeeded;
		if (selectedQuestion instanceof OpenQuestion) { // if question is open Q
			System.out.println("your answer is: " + selectedQuestion.printAnswers());
			System.out.println("Please insert your new Answer content (make sure its a different one!):");
			succeeded = manager.updateOpenQAnswer(((OpenQuestion) selectedQuestion).getAnswer(), input.next());
		} else { // if question is American Q
			System.out.println(((AmericanQuestions) selectedQuestion).printAnswers());
			System.out.println("Please select Answer: ");
			Answer selectedAnswer = manager.selectAmericanAnswerandReturnAnswer((AmericanQuestions) selectedQuestion,
					input.nextInt());
			System.out.println("Please insert your new Answer content (make sure its a different one!):");
			String newAnswer = input.next();
			System.out.println("true or false?");
			Boolean trueOrFalse = input.nextBoolean();
			succeeded = manager.updateAmericanQAnswer(selectedAnswer, newAnswer, trueOrFalse,
					(AmericanQuestions) selectedQuestion);
		}

		if (!succeeded)
			System.out.println("Your answer content was identical \n");
		else {
			System.out.println("Your answer updated \n");
		}

	}

	// 5 delete an answer to an existing question
	public static void deleteAnswerFromQuestion(Manager manager) 
			throws DataNotCreatedYetException, InvalidUserInputException,InputMismatchException {
		System.out.println(manager.getListOfQuestions());
		System.out.println("Please select the question you want to delete its answer: ");
		int questionPosition = input.nextInt();
		Question selectedQuestion = manager.selectQuestion(questionPosition);

		if (selectedQuestion instanceof OpenQuestion) {
			System.out
					.println("note that deleting an answer to an open Question will also delete the question itself \n"
							+ "(1 continue \n (2 cancel");
			int choice = input.nextInt();
			manager.checkValidRange(choice, 1, 2);
			if (choice == 1) {
				manager.deleteQuestion(questionPosition);
				System.out.println("Question deleted successfully \n");
			} else {
				return;
			}

		} else if (selectedQuestion instanceof AmericanQuestions) {
			AmericanQuestions tempQ = (AmericanQuestions) selectedQuestion;
			System.out.println(
					"note that if you delete an answer and you are left with only 2 auto answers,then the question will be deleted also. \n"
							+ "(1 continue \n (2 cancel");

			int choice = input.nextInt();
			manager.checkValidRange(choice, 1, 2);
			if (choice == 1) {
				System.out.println(tempQ.printAnswers());
				int currentAnswerPosition = input.nextInt();
				manager.checkAmericanAnswer(tempQ, currentAnswerPosition);
				Answer answer = manager.selectAmericanAnswerandReturnAnswer(tempQ, currentAnswerPosition);
				Boolean deleted = manager.deleteAmericanQAnswer(tempQ, questionPosition, answer, currentAnswerPosition);
				if (deleted) {
					System.out.println("The answer deleted succesfully! ");
				} else {
					System.out.println("Question deleted");
				}
			} else {

				return;
			}

		}

	}

	// 6
	public static void createExamManually(Manager manager)
			throws InvalidUserInputException, DataNotCreatedYetException, DataIdenticalException,InputMismatchException {
		System.out.println("pick a name for the exam");
		String examName = input.next();

		Exam newExaM = new Exam(examName);
		boolean keepAsking = true;

		while (keepAsking) {
			System.out.println(
					"Would you like to: \n1) Add new question to Exam  \n2) Add Question from database" + " to exam");
			int questionLocation = input.nextInt();
			manager.checkValidRange(questionLocation, 1, 2);

			if (questionLocation == 1) {
				System.out.println("Press 1 for American Q and 2 for Open Q");
				int choice = input.nextInt();
				manager.checkValidRange(choice, 1, 2);
				if (choice == 1)
					manager.addQuestionToExam(newExaM, createAmericanQ(manager));
				else
					manager.addQuestionToExam(newExaM, createOpenQ(manager));

			} else {
				System.out.println("Please select question: ");
				System.out.println(manager.getListOfQuestions());

				manager.addQuestionToExam(newExaM, manager.selectQuestion(input.nextInt())); // add to current exam //
																								// the current //
																								// question if // checks

			}
			System.out.println("would you like to add another question? enter 'true' else 'false'");
			keepAsking = input.nextBoolean();
		}
		manager.AddExamToArray(newExaM);		
		System.out.println("Your new exam: \n");
		System.out.println(newExaM.toString());

	}
	/*public static void createAutomaticExam(Manager manager) throws DataIdenticalException
	{
		System.out.println("how many questions would you like in your automatic exam?");
		int numOfQuestions = input.nextInt();
		Exam newExam = manager.generateAutomaticExam(numOfQuestions);
		manager.AddExamToArray(newExam);
	}*/
}
