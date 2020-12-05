package Stats;

import java.util.List;

import TPet.TPetController;
import TPet.TPetModel;

public class TPetHungriness extends TPetStat {
	
	public TPetHungriness() {
		super(100, 1);
		
	}
	
	@Override
	public void update() {
		if(!shouldUpdate()) return;
		
		double mood = ((TPetHappiness)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetHappiness.ordinal())).get();
		
		if (mood < 0) {
			data -= 0.1;
		}
		
		
		data -= 0.1;
		
	}  
	
	public void feed() { //feed food.. food class
		data += 20;
	}
	
	
}
