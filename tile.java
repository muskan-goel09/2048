public class tile {
	private int value;
	
	public tile() {
		this.value = 0;
	}
	
	public tile(int val) {
		this.value = val;
	}
	
	public String to_String() {
		return "" + this.value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
		
}
