package TPet;

import java.io.Serializable;
import java.util.List;

import Stats.TPetAge;
import Stats.TPetStat;

public class TPetSave implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double age;
	private double happiness;
	private double weight;
	private double hungriness;
	private double money;
	private double health;
	
	public TPetSave() {
		List<TPetStat> stats = TPetController.getInstance().getStats();
		age = stats.get(TPetModel.StatIndex.TPetAge.ordinal()).get();
		happiness = stats.get(TPetModel.StatIndex.TPetHappiness.ordinal()).get();
		weight = stats.get(TPetModel.StatIndex.TPetWeight.ordinal()).get();
		hungriness = stats.get(TPetModel.StatIndex.TPetHungriness.ordinal()).get();
		money = stats.get(TPetModel.StatIndex.TPetMoney.ordinal()).get();
		health = stats.get(TPetModel.StatIndex.TPetHealth.ordinal()).get();
	}
	
	public double getAge() {
		return age;
	}
	
	public double getHappiness() {
		return happiness;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getHungriness() {
		return hungriness;
	}
	
	public double getMoney() {
		return money;
	}
	
	public double getHealth() {
		return health;
	}
}
