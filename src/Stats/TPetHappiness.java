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
		super(50, 10); //Initiate data at 50. 10 ticks per update.
		mood = MOOD.Normal;
	}
	
	@Override
	public void update() {
		if(!shouldUpdate()) return;
		List<TPetStat> stats = TPetController.getInstance().getStats();
		
		double hungry = ((TPetHungriness)stats.get(TPetModel.StatIndex.TPetHungriness.ordinal())).get();
		boolean sick = ((TPetHealth)stats.get(TPetModel.StatIndex.TPetHealth.ordinal())).getIsSick();
		
		if(hungry > 80) {
			data += 5;
		} else {
			data -= 5;
		}
		
		if(sick) {
			data -= 10;
		} else {
			data += 5;
		}
		
		if(data < 0) {
			data = 0;
		} else if(data > 100) {
			data = 100;
		}
		if(data < 40) {
			mood = mood.Sad;
		} else if(data < 60) {
			mood = mood.Normal;
		} else {
			mood = mood.Happy;
		}
		//when sick, high percentage of getting sick, mood goes sad
		
		//when hungriness below 50, go sad
		
		//if health states is good with low percentage of getting sick
		//	and hungriness is above 80, go happy
//		System.out.println(((TPetHealth)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetHealth.ordinal())).getIsSick());
	}
	
	public MOOD getMood() {
		return mood;
	}
	
}
