package lai_online;

public class Class16_4_Reservoir_Sampling {
		
	private int count;
	private Integer sample;
	
	public Class16_4_Reservoir_Sampling() {
		this.count = 0;
		this.sample = 0;
	}
	
	public void read(int value) {
		// write your implementation here
		count ++;
		int prob = (int)(Math.random() * count);
		// the current read value has the probability of 1/count to be as the current sample
		if (prob == 0) {
			sample = value;
		}
	}

	public Integer sample() {
		// write your implementation here
		return sample;
	}

}
