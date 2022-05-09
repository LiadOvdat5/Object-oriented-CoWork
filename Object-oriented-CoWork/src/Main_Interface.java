import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public interface Main_Interface {
	//Question 1
	 void PresentInfo(Manager manager)
			throws InvalidUserInputException, DataNotCreatedYetException, FileNotFoundException;
	
	public Question createAmericanQ(Manager manager)
			throws InvalidUserInputException, DataIdenticalException, GeneralSystemException;
	
	
	public Question createOpenQ(Manager manager) throws DataIdenticalException, InputMismatchException;
	
	//Question 2
	public void addQuestion(Manager manager) throws DataNotCreatedYetException, InvalidUserInputException,
	DataIdenticalException, InputMismatchException,GeneralSystemException;
	
	// 3 Update content of an existing question
	public void UpdateContentOfQuestion(Manager manager) throws InputMismatchException, GeneralSystemException;
	
	// 4 Update content of an existing answer
	public void UpdateContentOfAnswer(Manager manager) throws DataNotCreatedYetException,
				InvalidUserInputException, DataIdenticalException, InputMismatchException;

	// 5 delete an answer to an existing question
	public void deleteAnswerFromQuestion(Manager manager) throws DataNotCreatedYetException, InvalidUserInputException, InputMismatchException;

	// 6
	public void createExamManually(Manager manager) throws InvalidUserInputException, DataNotCreatedYetException,
				DataIdenticalException, InputMismatchException,GeneralSystemException;

	// 7
	public void createAutomaticExam(Manager manager) throws DataIdenticalException, InvalidUserInputException;
	
	//8
	public void duplicateExam(Manager manager) throws DataNotCreatedYetException, InvalidUserInputException, CloneNotSupportedException;
	
	//9
	public void ShowSet(Manager manager) throws Exception;
	
	
}
