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
	
	public Food(FoodType type, double hungryIncrease, double happinessIncrease, double price) {
		this.type = type;
		this.hungryIncrease = hungryIncrease;
		this.happinessIncrease = happinessIncrease;
		this.price = price;
	}
	
	public double getHungryIncrease() {
		return hungryIncrease;
	}
	
	public double getHappinessIncrease() {
		return happinessIncrease;
	}
	
	public FoodType getType() {
		return type;
	}
	
	public double getPrice() {
		return price;
	}
}
