
public final class InvalidUserInputException extends GeneralSystemException {

	public InvalidUserInputException(String data) {
		super("the " + data +" you entered isnt valid , try again");
	}

}
