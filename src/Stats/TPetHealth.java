package Stats;

import java.util.List;

import TPet.TPetController;
import TPet.TPetModel;


public class TPetHealth extends TPetStat {
	private boolean isSick;
	private int maxHealth = 100;
	
	
	/**
     * Purpose: this is the constructor of the health static.
     *
     * @param  None.
     *
     * @return None.
     * 
     * 
     */
	public TPetHealth() {
		super();
		data = maxHealth;
		isSick = false;
	}
	
	/**
     * Purpose: this method is going to set the health number.
     *
     * @param  health is the integer represent the health static.
     *
     * @return None.
     * 
     * 
     */
	public void set(int health) {
		this.data = health;
	}
	
	
	/**
     * Purpose: this method is going to check if the TPet is sick or not.
     *
     * @param  None.
     *
     * @return return true if the TPet is sick, false for not sick.
     * 
     * 
     */
	public boolean getIsSick() {
		return isSick;
	}
	
	
	/**
     * Purpose: this method is going to update the static of the health.
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
		
		
		//age
		//	when age >= 80% of lifeSpan, then the max health drops
		TPetAge age = ((TPetAge)stats.get(TPetModel.StatIndex.TPetAge.ordinal()));
		/*if(age.getAge() > age.getLifeSpan()) {
			maxHealth -= 0.1;
		}*/
		
		double hungry = ((TPetHungriness)stats.get(TPetModel.StatIndex.TPetHungriness.ordinal())).get();
		if (hungry < 40) {
			data -= 0.1;
		}
		
		
		double mood = ((TPetHappiness)stats.get(TPetModel.StatIndex.TPetHappiness.ordinal())).get();
		if (mood < 0) {
			data -= 0.1;
		}
		
		double weight = ((TPetWeight)stats.get(TPetModel.StatIndex.TPetWeight.ordinal())).get();
		double idealWeight = ((TPetWeight)stats.get(TPetModel.StatIndex.TPetWeight.ordinal())).getIdealWeight();
		double weightRate = weight/idealWeight;
		if (weightRate > 1.4 || weightRate < 0.6) {
			data -= 0.1;
		}
		
		if (data < 40) {
			sick();
		}
		
		if (data > 80) {
			cured();
		}
	}
	
	/**
     * Purpose: this method is going to set the static to sick.
     *
     * @param  None.
     *
     * @return None.
     * 
     * 
     */
	private void sick() {
		isSick = true;
	}
	
	/**
     * Purpose: this method is going to set the static to not sick.
     *
     * @param  None.
     *
     * @return None.
     * 
     * 
     */
	private void cured() {
		isSick = false;
	}
	
	/**
     * Purpose: this method is going to increase the health data.
     *
     * @param  None.
     *
     * @return None.
     * 
     * 
     */
	public void hospital() {
		data += 40;
		System.out.println("hospital");
		if(data >= 100) {
			System.out.println("hospital too much");
			data = 100;
		}
	}
	
	/**
     * Purpose: this method is going to decrease the health data.
     *
     * @param  amount is the health data.
     *
     * @return None.
     * 
     * 
     */
	public void decreaseHealth(int amount) {
		data -= amount;
		if(data < 0) {
			data = 0;
		} else if(data > maxHealth) {
			data = maxHealth;
		}
	}
}
