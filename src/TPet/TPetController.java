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
	
	/**
     * Purpose: this is the constructor of the class.
     *
     * @param None.
     *
     * @return None.
     */
	public TPetController() {
		this(new TPetModel());
	}
	
	/**
     * Purpose: this is the constructor of the class.
     *
     * @param  model is the TPetModel object.
     *
     * @return None.
     */
	public TPetController(TPetModel model) {
		this.model = model;
		_instance = this;
		
		// Try to load auto-saved save
		File file = new File(SAVEFILE_NAME);
		if(file.exists()) {
			loadGame(SAVEFILE_NAME);
		}
	}
	
	/**
     * Purpose: this method is going to get the instance of the class.
     *
     * @param  None.
     *
     * @return _instance is the instance of the controller.
     */
	public static TPetController getInstance() {
		return _instance;
	}
	
	/**
     * Purpose: this method is going to get the static of the TPet model.
     *
     * @param  None.
     *
     * @return A list of the TPetStat.
     */
	public List<TPetStat> getStats(){
		return model.getStats();
	}
	
	/**
     * Purpose: this method is going to return the TPet model.
     *
     * @param  None.
     *
     * @return the TPet model.
     */
	
	public TPetModel getModel() {
		return model;
	}
	
	/**
     * Purpose: this method is going to check if the money is enough for the food make the static change.
     *
     * @param  food is the Food object.
     *
     * @return true is the money is enough , false for the money is not enough.
     */
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
	
	
	/**
     * Purpose: this method is going to check if the TPet can make money and change the static of the Tpet.
     *
     * @param  None.
     *
     * @return true if the tpet can make money false if the tpet can not make the money.
     */
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
	
	/**
     * Purpose: this method is going to check if the money is enough for the hospital.
     *
     * @param  None.
     *
     * @return true if the money is enough, false if the money is not enough.
     */
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
	/**
     * Purpose: this method is going to save the game.
     *
     * @param  None.
     *
     * @return None.
     */
	public void autoSave() {
		saveGame(SAVEFILE_NAME);
	}
	
	
	/**
     * Purpose: this method is going to save the game in the given filename.
     *
     * @param  filename is the given filename.
     *
     * @return return true if the game can be saved , return false if the game can not be saved.
     * 
     * @throws IOException on read file.
     */
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
	
	/**
     * Purpose: this method is going to load the game from the given file.
     *
     * @param  filename is the given filename.
     *
     * @return return true if the game can be loaded , return false if the game can not be loaded.
     * 
     * @throws IOException on read file and the ClassNotFoundException.
     */
	
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
	 
	/**
	 * This method triggers effect. If a effect is already triggered, return false.
	 * 
	 * @param (Class of effect) c
	 * @return (boolean) successful
	 */
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
