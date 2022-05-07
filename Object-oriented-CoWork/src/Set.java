import java.nio.channels.NonWritableChannelException;
import java.security.PublicKey;
import java.util.Arrays;

public class Set<T> {
	private final int ENLARGE_FACTOR = 2;
	private T[] arr;
	private int currentSize;
	
	public Set(){
		arr = (T[])new Object[ENLARGE_FACTOR];
		currentSize = 0;
	}
	
	
	public String tosString() {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("[ \n");
		for(int i = 0; i < currentSize; i++)
			sBuffer.append(arr[i] + "\n");
		sBuffer.append("]");
		return sBuffer.toString();
	}
	
	public int getCurrentSize() {return currentSize;}
	public T get(int index) {return arr[index];}
	public int capacity() {return arr.length;}
	
	public void enlarge() {
		this.arr = Arrays.copyOf(arr, ENLARGE_FACTOR*arr.length);
	}
	
	public void add(T newValue) throws Exception {
		if(!(newValue instanceof Answer)) {
			throw new Exception("argument Not with the same type");
		}
		if(isExist(newValue)) {
			return;
		}
		if(currentSize == arr.length)
			enlarge();
		arr[currentSize++] = newValue;
		
	}
	
	public boolean isExist(T value) {
		for(int i = 0; i<currentSize; i++)
			if(value.equals(arr[i]))
				return true;
		return false;
	}
	
	
	
}
