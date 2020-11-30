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
		//when hungriness is greater than 0 and data > minimum weight
		double hungry = ((TPetHungriness)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetHungriness.ordinal())).get();
		
		//can decrease
		if (data > minimumWeight) {
			//when stomach is empty, decrease weight
			if (hungry == 0) {
				data -= 0.1;
			}
		}
		
		//can increase
		if (hungry > 0) {
			boolean sick = ((TPetHealth)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetHealth.ordinal())).getIsSick();
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
