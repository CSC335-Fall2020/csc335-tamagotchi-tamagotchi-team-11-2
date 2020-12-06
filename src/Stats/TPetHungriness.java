package Stats;

public class TPetHungriness extends TPetStat {
	
	public TPetHungriness() {
		super(100, 1);
	}
	
	@Override
	public void update() {
		if(!shouldUpdate()) return;
		if(data > 0) {
			
			data -= 0.1;
		}
	}  
	
	public void eat() { //feed food.. food class
		data += 20;
		System.out.println("eat");
		if(data > 100) {
			System.out.println("eat too much");
			data = 100.1;
		}
	}
	

	public boolean isHungry() {
		return data < 20;
	}
	
	public boolean isFull() {
		return data > 80;
	}
	
	public void increaseHungriness(double amount) {
		if (data < 100) {
			data += amount;
		}
	}
	
	public void decreaseHungriness(double amount) {
		if (0 < data) {
			data -= amount;
		}
	}
}
