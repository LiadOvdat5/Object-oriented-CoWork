import java.io.Serializable;

public abstract class Question implements Serializable, Comparable<Question>, Cloneable {

	public static int serialNumberGenerator = 1;
	protected String content;
	protected int serialNumber;

	public Question(String content) {
		this.content = content;
		this.serialNumber = serialNumberGenerator++;
	}

	// Get the content of question
	public String getContent() {
		return this.content;
	}

	// Update question content
	public boolean updateContent(String content) {
		this.content = content;
		return true;
	}

	// public void setSerialNum()
	abstract String printAnswers() throws DataNotCreatedYetException;

	public String getQuestionType() {
		return this.getClass().getSimpleName();
	}

	public abstract String saveQuestion();

	public abstract String saveAnswer();

	public abstract int answerLength();

	@Override
	public int compareTo(Question o) {

		if (this.answerLength() > o.answerLength())
			return 1;
		else if (this.answerLength() < o.answerLength())
			return -1;
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Question))
			return false;
		Question tempQuestion = (Question) obj;
		if (this.content.toLowerCase().equals(tempQuestion.getContent().toLowerCase())
				&& this.getQuestionType().equals(tempQuestion.getQuestionType())) {
			return true;
		}
		return false;
	}

	public String toString() { // To String - print;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("Question number (serialNum in repository): " + this.serialNumber + "\n");
		sBuffer.append("  Question content: " + this.content + "? \n");

		return sBuffer.toString();
	}
}
