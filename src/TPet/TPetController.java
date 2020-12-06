package TPet;

import java.util.List;

import Food.Food;
import Food.Food.FoodType;
import Stats.TPetHappiness;
import Stats.TPetHealth;
import Stats.TPetHungriness;

import Stats.TPetMoney;
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
	

	public TPetModel getModel() {
		return model;
	}
	
	public void feed(Food food) {
		List<TPetStat> stats = TPetController.getInstance().getStats();
		TPetMoney money = (TPetMoney)stats.get(TPetModel.StatIndex.TPetMoney.ordinal());
		
		if(food.getPrice() <= money.get()) {
			TPetHealth health = (TPetHealth)stats.get(TPetModel.StatIndex.TPetHealth.ordinal());
			TPetHappiness happiness = (TPetHappiness)stats.get(TPetModel.StatIndex.TPetHappiness.ordinal());
			TPetHungriness hungriness = (TPetHungriness)stats.get(TPetModel.StatIndex.TPetHungriness.ordinal());
			
	
			
			if(food.getType().equals(FoodType.Drug)) {
				health.decreaseHealth(10); // Eating drug makes health decrease by 10
			}
		
//		happiness.set((int)(happiness.get() + food.getHappinessIncrease()));
//		hungriness.set((int)(hungriness.get() + food.getHungryIncrease()));
			if(hungriness.get() < 100 || happiness.get() < 100) {
				happiness.increaseMood(food.getHappinessIncrease());
				hungriness.increaseHungriness(food.getHungryIncrease());
				money.spendMoney(food.getPrice());
			}
		}
	}
	
	public void makeMoney() {

		List<TPetStat> stats = TPetController.getInstance().getStats();
		TPetMoney money = (TPetMoney)stats.get(TPetModel.StatIndex.TPetMoney.ordinal());
		TPetHealth health = (TPetHealth)stats.get(TPetModel.StatIndex.TPetHealth.ordinal());
		TPetHappiness happiness = (TPetHappiness)stats.get(TPetModel.StatIndex.TPetHappiness.ordinal());
		TPetHungriness hungriness = (TPetHungriness)stats.get(TPetModel.StatIndex.TPetHungriness.ordinal());
		
		if (hungriness.get() > 20 && happiness.get() > 10 && health.get() > 5) {
			money.earnMoney(200);
			health.decreaseHealth(5);
			happiness.decreaseMood(10);
			hungriness.decreaseHungriness(20);
		}
		
	}
	
	public void goHospital() {
		List<TPetStat> stats = TPetController.getInstance().getStats();
		TPetMoney money = (TPetMoney)stats.get(TPetModel.StatIndex.TPetMoney.ordinal());
		TPetHealth health = (TPetHealth)stats.get(TPetModel.StatIndex.TPetHealth.ordinal());
		
		if (money.get() >= 500) {
			money.spendMoney(500);
			health.hospital();
		}
	}
	 
}
