package Food;

/**
 * This class defines type, hungriness increase, happinessIncrease and price 
 * of drug
 * @author tamagotchi-team11
 *
 */
public class Drug extends Food{

	/**
     * Purpose: this is the constructor of the Drug.
     *
     * @param  None.
     *
     * @return None.
     * 
     */
	public Drug() {
		super(FoodType.Drug, 1, 40, 100);
	}
	
//	public Drug(FoodType type, double hungryIncrease, double happinessIncrease) {
//		super(FoodType.Drug, 1, 40, 100);
//	}

}
