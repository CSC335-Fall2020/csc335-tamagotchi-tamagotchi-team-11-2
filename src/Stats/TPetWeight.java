package Stats;

import java.util.List;

import TPet.TPetController;
import TPet.TPetModel;

public class TPetWeight extends TPetStat{
	private double idealWeight;
	private double minimumWeight;
	public TPetWeight() {
		super(10, 1);
		idealWeight = 15;
		minimumWeight = idealWeight * 0.6;
	}
	public void update() {
		if(!shouldUpdate()) return;
		List<TPetStat> stats = super.getStats();
		
		
		TPetHungriness hungry = ((TPetHungriness)stats.get(TPetModel.StatIndex.TPetHungriness.ordinal()));
		
		//can decrease
		if (data > minimumWeight) {
			//when starving, decrease weight
			if (hungry.isHungry()) {
				data -= 0.1;
			}
		}
		
		//can increase
		if (hungry.isFull()) {
			boolean sick = ((TPetHealth)stats.get(TPetModel.StatIndex.TPetHealth.ordinal())).getIsSick();
			if(sick) {
				data += 0.005;
			}else {
				data += 0.01;
			}
		}
	}
	
	public void loseWeight() {
		data -= 1;
	}
	
	public double getIdealWeight() {
		return idealWeight;
	}
	
	public double minimumWeight() {
		return minimumWeight;
	}
}
