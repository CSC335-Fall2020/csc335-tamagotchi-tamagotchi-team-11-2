package Stats;

import TPet.TPetController;
import TPet.TPetModel;


/**
 * This class defines behavior of weight stat
 * @author tamagotchi-team11
 *
 */
public class TPetWeight extends TPetStat{
	private double idealWeight;
	private double minimumWeight;
	
	/**
     * Purpose: this is the constructor of the weight static.
     *
     * @param  None.
     *
     * @return None.
     * 
     */
	public TPetWeight() {
		super(15, 1);
		idealWeight = 15;
		minimumWeight = idealWeight * 0.6;
	}
	/**
     * Purpose: this method is going to update the static of the weight.
     *
     * @param  None.
     *
     * @return None.
     * 
     * 
     */
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
	
	
	/**
     * Purpose: this is going to decrease the weight data .
     *
     * @param  None.
     *
     * @return None.
     * 
     */
	public void loseWeight() {
		data -= 0.5;
		if (data < minimumWeight) {
			data = minimumWeight;
			//alert: cannot do anymore exercise because of vulnerable physical status
		}
	}
	
	/**
     * Purpose: this is going to return the ideal weight .
     *
     * @param  None.
     *
     * @return the ideal weight is a double.
     * 
     */
	public double getIdealWeight() {
		return idealWeight;
	}
	
	/**
     * Purpose: this is going to return the minimumWeight.
     *
     * @param  None.
     *
     * @return the minimumWeight is a double.
     * 
     */
	public double minimumWeight() {
		return minimumWeight;
	}
	
	
	/**
     * Purpose: this is going to return the size of the TPet.
     *
     * @param  None.
     *
     * @return Thin or Fat represent the size.
     * 
     */
	public String getSize() {
		if (data/idealWeight < 0.7) {
			return "Thin";
		} else if (data/idealWeight > 1.4) {
			return "Fat";
		}
		return "";
	}
}
