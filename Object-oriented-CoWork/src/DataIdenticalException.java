
public class DataIdenticalException extends GeneralSystemException {

	public DataIdenticalException(String data) {
		super("there is already  " + data + "  with the same content");
	}

}
