package Stats;

import java.io.FileNotFoundException;
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
	
	
	/**
     * Purpose: this method is going to update the static of the mood.
     *
     * @param  None.
     *
     * @return None.
     * 
     * 
     */
	@Override
	public void update() {
		if(!shouldUpdate()) return;
		List<TPetStat> stats = TPetController.getInstance().getStats();
		
		double hungry = ((TPetHungriness)stats.get(TPetModel.StatIndex.TPetHungriness.ordinal())).get();
		boolean sick = ((TPetHealth)stats.get(TPetModel.StatIndex.TPetHealth.ordinal())).getIsSick();
	
		//when sick, high percentage of getting sick, mood goes sad
		
		//when hungriness below 50, go sad
		
		//if health states is good with low percentage of getting sick
		//	and hungriness is above 80, go happy
//		System.out.println(((TPetHealth)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetHealth.ordinal())).getIsSick());
	
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
	
	
	/**
     * Purpose: this method is going to return the mood of the Tpet.
     *
     * @param  None.
     *
     * @return the mood is the Mood object.
     * 
     */
	public MOOD getMood() {
		return mood;
	}
	
	
	/**
     * Purpose: this method is going to set the mood of the Tpet to sad.
     *
     * @param  None.
     *
     * @return None.
     * 
     */
	public void beSad() {
		mood = MOOD.Sad;
	}
	
	/**
     * Purpose: this method is going to increase the mood.
     *
     * @param  the amount is the current mood double.
     *
     * @return None.
     * 
     */
	public void increaseMood(double amount) {
		if (data < 100) {
			data += amount;
		}
		if(data < 40) {
			mood = MOOD.Sad;
		} else if(data < 60) {
			mood = MOOD.Normal;
		} else {
			mood = MOOD.Happy;
		}
	}
	
	
	/**
     * Purpose: this method is going to decrease the mood.
     *
     * @param  the amount is the current mood double.
     *
     * @return None.
     * 
     */
	public void decreaseMood(double amount) {
		if (0 < data) {
			data -= amount;
		}
		if(data < 40) {
			mood = MOOD.Sad;
		} else if(data < 60) {
			mood = MOOD.Normal;
		} else {
			mood = MOOD.Happy;
		}
	}
	
}
