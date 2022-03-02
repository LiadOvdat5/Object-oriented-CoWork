
public abstract class Question {

	public static int serialNumberGenerator = 1;
	protected String content;
	protected int serialNumber;
	
	public Question(String content) {
		this.content = content;
		this.serialNumber = serialNumberGenerator++;
	}
}
