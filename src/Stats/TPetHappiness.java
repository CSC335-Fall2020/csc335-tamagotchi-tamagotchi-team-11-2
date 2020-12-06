package Stats;

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
		super(100, 1); //Initiate data at 50. 10 ticks per update.
		mood = MOOD.Happy;
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
		
		if (hungry < 40 || sick) {
			data -= 5;
		}else if(hungry > 80 && !sick) {
			data += 5;
		}else { // 40 < hungry < 80 && !sick
			data += 1;
		}
		
		if(data < 0) {
			data = 0;
		} else if(data > 100) {
			data = 100;
		}
		if(data < 40) {
			mood = MOOD.Sad;
		} else if(data < 60) {
			mood = MOOD.Normal;
		} else {
			mood = MOOD.Happy;
		}
		System.out.println("happiness: " + data);
		//when sick, high percentage of getting sick, mood goes sad
		
		//when hungriness below 50, go sad
	}
	
	public MOOD getMood() {
		return mood;
	}
	
	public void beSad() {
		mood = MOOD.Sad;
	}
	
	public void increaseMood(double amount) {
		if (data < 100) {
			data += amount;
		}
	}
	
	public void decreaseMood(double amount) {
		if (0 < data) {
			data -= amount;
		}
	}
	
}
