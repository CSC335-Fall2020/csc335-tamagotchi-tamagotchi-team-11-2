package TPet;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import Food.Food;
import Food.Food.FoodType;
import Stats.TPetHappiness;
import Stats.TPetHealth;
import Stats.TPetHungriness;

import Stats.TPetMoney;
import Stats.TPetStat;
import Stats.TPetWeight;
import TPetEffect.TPetEffect;
import javafx.scene.effect.Effect;

/*
 * Team Project
 * Tamagotchi Pet
 * Team 11
 * 
 * Controller class. Provides interfaces to others to interact with model.
 */

public class TPetController {
	public static final String SAVEFILE_NAME = "save.tpetdat";
	private TPetModel model;
	public static TPetController _instance;
	
	public TPetController() {
		this(new TPetModel());
	}
	
	public TPetController(TPetModel model) {
		this.model = model;
		_instance = this;
		
		// Try to load auto-saved save
		File file = new File(SAVEFILE_NAME);
		if(file.exists()) {
			loadGame(SAVEFILE_NAME);
		}
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
	
	public boolean feed(Food food) {
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
			
			if(hungriness.get() < 100) {
				hungriness.increaseHungriness(food.getHungryIncrease());
			}
			if(happiness.get() < 100) {
				happiness.increaseMood(food.getHappinessIncrease());
			}
			if(food.getType().equals(FoodType.Snack)) {
				TPetWeight weight = (TPetWeight) getStats().get(TPetModel.StatIndex.TPetWeight.ordinal());
				weight.set(weight.get() + food.getHungryIncrease() * 0.05);
			}
			money.spendMoney(food.getPrice());
			return true;
		}
		return false;
	}
	
	public boolean makeMoney() {

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
			return true;
		}
		return false;
		
	}
	
	public boolean goHospital() {
		List<TPetStat> stats = TPetController.getInstance().getStats();
		TPetMoney money = (TPetMoney)stats.get(TPetModel.StatIndex.TPetMoney.ordinal());
		TPetHealth health = (TPetHealth)stats.get(TPetModel.StatIndex.TPetHealth.ordinal());
		
		if (money.get() >= 500) {
			money.spendMoney(500);
			health.hospital();
			return true;
		}
		return false;
	}
	
	public void autoSave() {
		saveGame(SAVEFILE_NAME);
	}
	
	public boolean saveGame(String filename) {
		TPetSave save = new TPetSave();
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(save);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean loadGame(String filename) {
		TPetSave save = null;
		
		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			if(!(obj instanceof TPetSave)) return false;
			save = (TPetSave)obj;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		if(save == null) return false;
		
		List<TPetStat> stats = getStats();
		stats.get(TPetModel.StatIndex.TPetAge.ordinal()).set(save.getAge());
		stats.get(TPetModel.StatIndex.TPetHealth.ordinal()).set(save.getHealth());
		stats.get(TPetModel.StatIndex.TPetMoney.ordinal()).set(save.getMoney());
		stats.get(TPetModel.StatIndex.TPetHungriness.ordinal()).set(save.getHungriness());
		stats.get(TPetModel.StatIndex.TPetHappiness.ordinal()).set(save.getHappiness());
		stats.get(TPetModel.StatIndex.TPetWeight.ordinal()).set(save.getWeight());
		return true;
	}
	 
	public boolean triggerEffect(Class<? extends TPetEffect> c) {
		TPetMoney money = (TPetMoney)getStats().get(TPetModel.StatIndex.TPetMoney.ordinal());
		// If money < 1000, return false
		if(money.get() < 1000) {
			return false;
		}
		// 1000 per effect
		money.set(money.get() - 1000);
		
		boolean alreadyTriggered = false;
		for(TPetEffect e : model.getEffects()) {
			alreadyTriggered |= c.isInstance(e);
		}
		if(!alreadyTriggered) {
			try {
				model.addEffect(c.newInstance());
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return !alreadyTriggered;
	}
}
