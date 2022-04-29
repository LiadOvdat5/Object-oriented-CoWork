
public class DataNotCreatedYetException extends GeneralSystemException {

	public DataNotCreatedYetException(String data) {
		super("there are no " + data +" in the repository right now");
	}

}
