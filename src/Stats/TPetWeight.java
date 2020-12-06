package Stats;

import TPet.TPetController;
import TPet.TPetModel;

public class TPetWeight extends TPetStat{
	private double idealWeight;
	private double minimumWeight;
	public TPetWeight() {
		super(15, 1);
		idealWeight = 15;
		minimumWeight = idealWeight * 0.6;
	}
	public void update() {
		//when hungriness is greater than 0 and data > minimum weight
		TPetHungriness hungry = ((TPetHungriness)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetHungriness.ordinal()));

		
		//can decrease
		if (data > minimumWeight) {
			//when starving, decrease weight
			if (hungry.isHungry()) {
				data -= 0.1;
			}
		}
		
		//can increase

		if (hungry.get() > 0 && hungry.isFull()) {
			boolean sick = ((TPetHealth)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetHealth.ordinal())).getIsSick();

			if(sick) {
				data += 0.005;
			}else {
				data += 0.01;
			}
		}
	}
	
	public void loseWeight() {
		data -= 0.5;
		if (data < minimumWeight) {
			data = minimumWeight;
			//alert: cannot do anymore exercise because of vulnerable physical status
		}
	}
	
	public double getIdealWeight() {
		return idealWeight;
	}
	
	public double minimumWeight() {
		return minimumWeight;
	}
}
