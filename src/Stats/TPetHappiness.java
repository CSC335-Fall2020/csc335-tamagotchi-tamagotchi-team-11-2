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
		super(50, 3); //Initiate data at 50. 10 ticks per update.
		mood = MOOD.Normal;
	}
	
	@Override
	public void update() {
		if(!shouldUpdate()) return;
		//when sick, high percentage of getting sick, mood goes sad
		
		//when hungriness below 50, go sa
		List<TPetStat> stats = super.getStats();
		System.out.println("stats in happy: " + stats);
		
		//if health states is good with low percentage of getting sick
		//	and hungriness is above 80, go happy
		System.out.println(((TPetHealth)TPetController.getInstance().getStats().
		get(TPetModel.StatIndex.TPetHealth.ordinal())).getIsSick());
//		System.out.println("in happiness" + ((TPetHealth)TPetController.getInstance().getStats()));
//		if() {
//			this.data -= 1;
//		}
	}
	
}
