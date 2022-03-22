import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class Main {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		input.useDelimiter(System.getProperty("line.separator"));

		int selectedNumber;
		final int EXIT = 8;
		Manager manager = new Manager();

		// Hard Coded Questions for use while check
		Question[] hardCodedQ = hardCodedQuestions();

		System.out.println(
				"Please Enter the number of the program you want to exam: \r\n" + "1 - Present all exams (all Q&A) \r\n" // V
						+ "2 - Add Question to the Exam \r\n" // V
						+ "3 - Update content of an existing question\r\n" // V
						+ "4 - Update content of an existing answer \r\n" // V
						+ "5 - delete an answer to an existing question \r\n" + "6 - Create exam manually \r\n" // V
						+ "7 - Create exam automatically \r\n" // V
						+ "8 - Exit");

		selectedNumber = input.nextInt();

		while (selectedNumber != EXIT) {
			switch (selectedNumber) {

			case 1: { // Present all exams (all Q&A)
				System.out.println(manager.toString());
				break;
			}

			case 2: { // Add Question to the Exam
				int numOfExam = selectExam(manager);

				if (numOfExam == -1)
					break;

				Exam currentExam = manager.getExam(numOfExam); // pointer to the desired Exam

				int choice = -1;
				while (choice != 1 && choice != 2) {
					System.out.println("Press 1 for American Q and 2 for Open Q");
					choice = input.nextInt();
				}

				if (choice == 1)
					currentExam.addQuestion(createAmericanQ());
				else if (choice == 2)
					currentExam.addQuestion(createOpenQ());

				break;
			}

			case 3: { // Update content of an existing question

				int numOfExam = selectExam(manager);

				if (numOfExam == -1)
					break;

				Exam currentExam = manager.getExam(numOfExam); // pointer to the desired Exam

				System.out.println(currentExam.getListOfQuestions()); // print all questions

				int choice = selectQuestion(currentExam); // Select question
				if (choice == -1)
					break;

				boolean keepasking = true;
				while (keepasking) {
					System.out.println("enter the updated question (no need to put a '?' at the end)");
					String content = input.next();
					if (currentExam.updateQuestionContent(content, choice)) {
						System.out.println("question " + choice + " updated successfully! \n");
						keepasking = false;
					} else {
						System.out.println("there is already a question in the exam with the same content \n");
					}
				}
				break;
			}

			case 4: { // Update content of an existing answer
				int numOfExam = selectExam(manager);

				if (numOfExam == -1)
					break;

				Exam currentExam = manager.getExam(numOfExam); // pointer to the desired Exam

				System.out.println(currentExam.getListOfQuestions()); // print all questions

				int choice = selectQuestion(currentExam); // Select question
				if (choice == -1)
					break;

				boolean updated = false;
				if (currentExam.getQuestion(choice) instanceof OpenQuestion) {
					OpenQuestion tempOpenQuestion = (OpenQuestion) currentExam.getQuestion(choice);
					System.out.println("The Answer you would change: \n" + tempOpenQuestion.getAnswer().getContent()); // Print
																														// Answer

					while (!updated) {
						System.out.println("Please insert your Answer content (make sure its a different one!):");
						String newAnswer = input.next();
						updated = tempOpenQuestion.updateAnswer(newAnswer);

					}

				} else if (currentExam.getQuestion(choice) instanceof AmericanQuestions) {
					System.out.println("Choose the answer you want to change: \n"
							+ currentExam.getQuestion(choice).printAnswers()); // Print All Answers
					AmericanQuestions tempAmericanQuestion = (AmericanQuestions) currentExam.getQuestion(choice);

					int selectedAns = input.nextInt(); // Check if the number of answer is within range
					while (selectedAns < 1 || selectedAns > tempAmericanQuestion.getNumOfAnswers() - 2) {
						System.out.println(
								"invalid answer number, try again \n" + currentExam.getQuestion(choice).printAnswers()); // Print
																															// All
																															// Answers
						selectedAns = input.nextInt();
					}

					while (!updated) {
						System.out.println("Please insert your Answer content (make sure its a different one!):");
						String newAnswer = input.next();
						updated = tempAmericanQuestion.updateAnswer(newAnswer, selectedAns);

					}

				}
				System.out.println("Updated Succesfully ! \n");

				break;
			}

			case 5: {
				int numOfExam = selectExam(manager);

				if (numOfExam == -1)
					break;

				Exam currentExam = manager.getExam(numOfExam); // pointer to the desired Exam

				System.out.println(currentExam.getListOfQuestions()); // print all questions

				int choice = selectQuestion(currentExam); // Select question
				if (choice == -1)
					break;

				if (currentExam.getQuestion(choice) instanceof OpenQuestion) {
					System.out.println("Deleting the answer of Open question will delete the question, for not deleting press 1, otherwise press 2");
					if(input.nextInt() == 1) {
						break;
					} else {
						Boolean deleted = currentExam.deleteQuestion(choice);
						if(deleted) 
							System.out.println("The question deleted succesfully! "); 
					}
					
				} else if (currentExam.getQuestion(choice) instanceof AmericanQuestions) {
					System.out.println("Choose the answer you want to delete: \n"
							+ currentExam.getQuestion(choice).printAnswers()); // Print All Answers
					AmericanQuestions tempAmericanQuestion = (AmericanQuestions) currentExam.getQuestion(choice);

					int selectedAns = input.nextInt(); // Check if the number of answer is within range
					while (selectedAns < 1 || selectedAns > tempAmericanQuestion.getNumOfAnswers() - 2) {
						System.out.println("invalid answer number, try again \n" + currentExam.getQuestion(choice).printAnswers()); // Print All Answers
						selectedAns = input.nextInt();
					}
					
					Boolean deleted = tempAmericanQuestion.deleteAnswer(selectedAns);
					if(deleted) 
						System.out.println("The answer deleted succesfully! "); 
					
					if(tempAmericanQuestion.getNumOfAnswers() <= 2) {
						System.out.println("There are only auto answers, Question is deleted! ");
						currentExam.deleteQuestion(choice);
					}
				
				}
				
				

				break;
			}

			case 6: { // Create exam manually
				System.out.println("pick a name for the exam");
				String examName = input.next();

				System.out.println("how many american questions you want? ");
				int numOfAmerican = input.nextInt();

				System.out.println("how many open questions you want? ");
				int numOfOpen = input.nextInt();

				Question[] qArray = new Question[numOfAmerican + numOfOpen];

				for (int i = 0; i < numOfAmerican; i++) { // loop of American Q

					qArray[i] = createAmericanQ(); // add American question to qArray
				}

				for (int i = numOfAmerican; i < qArray.length; i++) // loop of Open Q
					qArray[i] = createOpenQ(); // add Open question to exam

				manager.generateExam(examName, qArray);
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
					+ "5 - delete an answer to an existing question \r\n" + "6 - Create exam manually \r\n"
					+ "7 - Create exam automatically \r\n" + "8 - Exit");
			selectedNumber = input.nextInt();

		}

		System.out.println("\n \n");
		System.out.println("Hope you enjoined the program !");

	}

	// Hard Coded Q
	// Hard Coded Questions for checks
	public static Question[] hardCodedQuestions() {
		Question[] questionsArray = new Question[5];

		Answer[] ansArray = new Answer[2]; // Answer Array

		// Q0
		ansArray[0] = new Answer("Kabul", true);
		ansArray[1] = new Answer("Nassau", false);

		questionsArray[0] = new AmericanQuestions("What is the capital of Afghanistan", ansArray); // Insert new
																									// American Q

		// Q1
		ansArray[0] = new Answer("Prague", false);
		ansArray[1] = new Answer("Zagreb", true);

		questionsArray[1] = new AmericanQuestions("What is the capital of Belarus", ansArray); // Insert new American Q

		// Q2
		ansArray[0] = new Answer("Beijing", true);
		ansArray[1] = new Answer("Hong Kong", false);

		questionsArray[2] = new AmericanQuestions("What is the capital of China", ansArray); // Insert new American Q

		// Q3
		questionsArray[3] = new OpenQuestion("Who was the first prime minister of Israel?", "David Ben Gurion");

		// Q4
		questionsArray[4] = new OpenQuestion("In what year did World War II end?", "1945");

		return questionsArray;

	}

	// Create American Q
	public static AmericanQuestions createAmericanQ() {
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
		return new AmericanQuestions(qContent, ansArray);
	}

	// Create Open Q
	public static OpenQuestion createOpenQ() {

		System.out.println("What is the Open question, question content ? ");
		String qContent = input.next();

		System.out.println("Answer content");
		String aContent = input.next();

		return new OpenQuestion(qContent, aContent);
	}

	// Selected Exam

	// Select Exam
	public static int selectExam(Manager m) {

		if (m.getNumOfExams() == 0) {
			System.out.println("you have to create an auto / manual exam first \n");
			return -1;
		}

		int numOfExam = -1;
		while (numOfExam < 1 || numOfExam > m.getNumOfExams()) {
			System.out.println("choose the num of the exam you want to deal with \n" + m.getListOfExams());
			numOfExam = input.nextInt();
		}
		return numOfExam;

	}

	// Select Question
	public static int selectQuestion(Exam currentExam) {
		int choice = -1, numOfQ = currentExam.getNumOfQuestions();
		if (numOfQ == 0) {
			System.out.println("there are no questions in the exam to update \n");
			return -1;
		}
		while (choice < 1 || choice > numOfQ) {
			System.out.println("Which Question whould you like to update?");
			choice = input.nextInt();
		}
		return choice;
	}

}
