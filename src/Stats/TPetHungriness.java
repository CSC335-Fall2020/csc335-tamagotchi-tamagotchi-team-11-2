package Stats;

import java.util.List;

public class TPetHungriness extends TPetStat {
	private int hungriness = 100;
	
	public TPetHungriness() {
		super(100, 10);
		
	}
	
	@Override
	public void update() {
		if(!shouldUpdate()) return;
		hungriness -= 1;
		List<TPetStat> stats = super.getStats();
		System.out.println("stats in hungry: " + stats);
		
	}  
	
	public void feed() { //feed food.. food class
		hungriness += 1;
	}
	//123
	public int get() {
		return hungriness;
	}
	
}
