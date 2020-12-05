package Stats;

import java.util.List;

import TPet.TPetController;
import TPet.TPetModel;

public class TPetHappiness extends TPetStat{
	private MOOD mood;
	
	public static enum MOOD{
		Happy,
		Sad,
		Normal
	}
	
	public TPetHappiness() {
		super(); //Initiate data at 50. 10 ticks per update.
		mood = MOOD.Normal;
	}
	
	@Override
	public void update() {
		if(!shouldUpdate()) return;
		//when sick, high percentage of getting sick, mood goes sad
		
		//when hungriness below 50, go sad
		
		//if health states is good with low percentage of getting sick
		//	and hungriness is above 80, go happy
//		System.out.println(((TPetHealth)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetHealth.ordinal())).getIsSick());

			
		double hungry = ((TPetHungriness)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetHungriness.ordinal())).get();
		boolean sick = ((TPetHealth)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetHealth.ordinal())).getIsSick();
		
		if (hungry > 80 && !sick) {
			mood = MOOD.Happy;
			data = 1;
		}else if(hungry < 50 || sick) {
			mood = MOOD.Sad;
			data = -1;
		}else {
			mood = MOOD.Normal;
			data = 0;
		}
	}
	
}
