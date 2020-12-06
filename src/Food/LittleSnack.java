package Food;

public class LittleSnack extends Food{
	
	public LittleSnack() {
		super(FoodType.Snack, 5, 5, 10);
	}

	public LittleSnack(FoodType type, double hungryIncrease, double happinessIncrease) {
		super(FoodType.Snack, 5, 5, 10);
	}

}
