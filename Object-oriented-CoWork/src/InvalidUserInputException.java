
public final class InvalidUserInputException extends GeneralSystemException {

	public InvalidUserInputException(String data) {
		super("The " + data +" you entered isnt valid , try again");
	}

}
