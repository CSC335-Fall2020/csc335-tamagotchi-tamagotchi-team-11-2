package Stats;

/**
 * This class defines behavior of hungriness stat
 * @author tamagotchi-team11
 *
 */
public class TPetHungriness extends TPetStat {
	
	public TPetHungriness() {
		super(100, 1);
	}
	
	
	/**
     * Purpose: this method is going to update the static of the mood.
     *
     * @param  None.
     *
     * @return None.
     * 
     */
	@Override
	public void update() {
		if(!shouldUpdate()) return;
		if(data > 0) {
			
			data -= 0.1;
		}
	}  
	/**
     * Purpose: this method is going to do the eat and change the food static.
     *
     * @param  None.
     *
     * @return None.
     * 
     */
	public void eat() { //feed food.. food class
		data += 20;
		System.out.println("eat");
		if(data > 100) {
			System.out.println("eat too much");
			data = 100.1;
		}
	}
	
	/**
     * Purpose: this method is going to check if the TPet is hungry.
     *
     * @param  None.
     *
     * @return true if the TPet is hungry, false for not hungry.
     * 
     */
	public boolean isHungry() {
		return data < 20;
	}
	
	
	/**
     * Purpose: this method is going to check if the TPet is full.
     *
     * @param  None.
     *
     * @return true if the TPet is hungry, false for not hungry.
     * 
     */
	public boolean isFull() {
		return data > 80;
	}
	
	/**
     * Purpose: this method is going to increase the Hungriness.
     *
     * @param  the amount is the current Hungriness double.
     *
     * @return None.
     * 
     */
	public void increaseHungriness(double amount) {
		if (data < 100) {
			data += amount;
		}
	}
	/**
     * Purpose: this method is going to decrease the Hungriness.
     *
     * @param  the amount is the current Hungriness double.
     *
     * @return None.
     * 
     */
	public void decreaseHungriness(double amount) {
		if (0 < data) {
			data -= amount;
		}
	}
}
