package Food;

public class Food {
	public static enum FoodType{
		Snack,
		Meal,
		Drug
	}
	
	private FoodType type;
	private double hungryIncrease;
	private double happinessIncrease;

	private double price; 
	
	
	/**
     * Purpose: this is the constructor of the Food.
     *
     * @param  type is the FoodType the hungryIncrease and  happinessIncrease is the double of how
     * it can change the static, the price is double of the food.
     *
     * @return None.
     * 
     */
	public Food(FoodType type, double hungryIncrease, double happinessIncrease, double price) {
		this.type = type;
		this.hungryIncrease = hungryIncrease;
		this.happinessIncrease = happinessIncrease;
		this.price = price;
	}
	
	/**
     * Purpose: this is going to return the hungryIncrease.
     *
     * @param  None.
     *
     * @return hungryIncrease is the double.
     * 
     */
	public double getHungryIncrease() {
		return hungryIncrease;
	}
	
	
	/**
     * Purpose: this is going to return the happinessIncrease.
     *
     * @param  None.
     *
     * @return happinessIncrease is the double.
     * 
     */
	public double getHappinessIncrease() {
		return happinessIncrease;
	}
	
	/**
     * Purpose: this is going to get the food type.
     *
     * @param  None.
     *
     * @return type is the FoodType.
     * 
     */
	public FoodType getType() {
		return type;
	}
	
	/**
     * Purpose: this is going to get the price.
     *
     * @param  None.
     *
     * @return price is the double.
     * 
     */
	public double getPrice() {
		return price;
	}
}
