import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
		Scanner input = new Scanner(System.in);

		int selectedNumber, checkNumber1, checkNumber2;
		final int EXIT = 8;
		Manager manager = new Manager();

		System.out.println("Please Enter the number of the program you want to exam: \r\n"
				+ "1 - [Present all exams (all Q&A)] \r\n"
				+ "2 - Add Question to the Exam \r\n"
				+ "3 - Update content of an existing question\r\n"
				+ "4 - Update content of an existing answer \r\n"
				+ "5 - delete an answer to an existing question \r\n" 
				+ "6 - Create exam manually \r\n"
				+ "7 - Create exam automatically \r\n"
				+ "8 – Exit");
				
		selectedNumber = input.nextInt();

		while (selectedNumber != EXIT) {
			switch (selectedNumber) {

			case 1: {
			System.out.println(manager.toString());	
				break;
			}

			case 2: {
				
				break;
			}

			case 3: {
			
				break;
			}

			case 4: {
				break;
			}

			case 5: {
			
				break;
			}

			case 6: {
				System.out.println("pick a name for the exam");
				String examName = input.next();
				manager.generateExam(examName);
				break;
			}

			case 7: {
				manager.generateAutomaticExam();
				break;
			}
			case 8:
			{
			
			}
			}

			System.out.println("Please Enter the number of the program you want to exam: \r\n"
					+ "1 - [KEREN Q(3) - Array numbers by sum of 2 before] \r\n"
					+ "2 - [KEREN Q(2) - first array index value = second array index+1 value - True/False] \r\n"
					+ "3 - [KEREN Q(6) - change lowercase to uppercase between two uppercase] \r\n"
					+ "4 - [KEREN Q(7) - Equal values in ordered arrays] \r\n"
					+ "5 - [KEREN Q(8) - Array is Mirrored] \r\n" 
					+ "6 - [KEREN Q(11) - print 4 colums of as many astricks as the user want up to 10] \r\n"
					+ "7 - [KEREN Q(12) - Print matrix of 0-10 raws and columns with user values and the highest number on the frame] \r\n"
					+ "8 – [KEREN Q(6) [Functions] - return in matrix if sum of raw is equal to sum of raw respectively ]\r\n"
					+ "9 – [KEREN Q(8) [Functions] - return the sum of two numbers in arrays]\r\n"
					+ "10 - EXIT\r\n");
			selectedNumber = input.nextInt();
		}

		System.out.println("\n \n");
		System.out.println("Hope you enjoined the program !");

	}
	
	

	

}
