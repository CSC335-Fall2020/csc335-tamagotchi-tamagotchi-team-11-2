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
		data -= 0.1;
		if(data < 0) data = 0;
	}  
	
	public boolean isHungry() {
		return data < 20;
	}
	
	public boolean isFull() {
		return data > 90;
	}
}
