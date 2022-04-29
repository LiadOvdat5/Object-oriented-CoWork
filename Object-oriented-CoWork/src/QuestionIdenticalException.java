
public class QuestionIdenticalException extends GeneralSystemException {

	public QuestionIdenticalException(String QuestionType) {
		super("there is already a " + QuestionType + " question with the same content");
	}

}
