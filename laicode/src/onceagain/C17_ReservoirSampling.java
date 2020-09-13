package onceagain;

public class C17_ReservoirSampling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Consider an unlimited flow of data elements. 
	 * How do you sample one element from this flow, 
	 * such that at any point during the processing of the flow, 
	 * you can return a random element from the n elements read so far.
	 * You will implement two methods for a sampling class:
	 * 
	 * read(int value) - read one number from the flow
	 * sample() - return at any time the sample,
	 *  
	 * if n values have been read, the probability of returning any one of the n values is 1/n, 
	 * return null(Java)/INT_MIN(C++) if there is no value read so far
	 * You may need to add more fields for the class.
	 */
	
	private Integer counter; // how many numbers have been read so far 
	public Integer sample; // only need to maintain the current sample
	public C17_ReservoirSampling() {
		counter = 0;
		sample = null;
	}
	
	public void read(int value) {
		counter ++;
		int probability = (int)(Math.random() * counter);
		if (probability == 0) {
			sample = value;
		}
	}
	
	public Integer sample() {
		return sample;
	}

}
