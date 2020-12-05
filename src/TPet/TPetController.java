package TPet;

import java.util.List;

import Food.Food;
import Food.Food.FoodType;
import Stats.TPetHappiness;
import Stats.TPetHealth;
import Stats.TPetHungriness;
import Stats.TPetStat;

/*
 * Team Project
 * Tamagotchi Pet
 * Team 11
 * 
 * Controller class. Provides interfaces to others to interact with model.
 */

public class TPetController {
	private TPetModel model;
	public static TPetController _instance;
	
	public TPetController() {
		this(new TPetModel());
	}
	
	public TPetController(TPetModel model) {
		this.model = model;
		_instance = this;
	}
	
	public static TPetController getInstance() {
		return _instance;
	}
	
	public List<TPetStat> getStats(){
		return model.getStats();
	}
	
	public void feed(Food food) {
		List<TPetStat> stats = TPetController.getInstance().getStats();
		TPetHealth health = (TPetHealth)stats.get(TPetModel.StatIndex.TPetHealth.ordinal());
		TPetHappiness happiness = (TPetHappiness)stats.get(TPetModel.StatIndex.TPetHappiness.ordinal());
		TPetHungriness hungriness = (TPetHungriness)stats.get(TPetModel.StatIndex.TPetHungriness.ordinal());
		
		if(food.getType().equals(FoodType.Drug)) {
			health.decreaseHealth(10); // Eating drug makes health decrease by 10
		}
		
		happiness.set((int)(happiness.get() + food.getHappinessIncrease()));
		hungriness.set((int)(hungriness.get() + food.getHungryIncrease()));
	}
}
