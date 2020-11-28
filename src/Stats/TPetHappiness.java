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
		super(50, 10); //Initiate data at 50. 10 ticks per update.
		mood = MOOD.Normal;
	}
	
	@Override
	public void update() {
		if(!shouldUpdate()) return;
		
		if(((TPetHealth)TPetController.getInstance().getStats().
				get(TPetModel.StatIndex.TPetHealth.ordinal())).getIsSick()) {
			this.data -= 1;
		}
	}
	
}
