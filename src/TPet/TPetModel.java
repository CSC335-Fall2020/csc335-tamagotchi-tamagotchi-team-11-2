package TPet;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import Stats.TPetAge;
import Stats.TPetHappiness;
import Stats.TPetHealth;
import Stats.TPetHungriness;
import Stats.TPetStat;
import Stats.TPetWeight;



/*
 * Team Project
 * Tamagotchi Pet
 * Team 11
 * 
 * Model class. The core logics of the game
 */

public class TPetModel extends Observable{
	private List<TPetStat> stats;
	private Timer timer;
	
	public static enum StatIndex{
		TPetAge,
		TPetHealth,
		TPetWeight,
		TPetHappiness,
		TPetHungeriness
	}
	
	public TPetModel() {
		stats = new ArrayList<TPetStat>();
		stats.add(new TPetAge());
		stats.add(new TPetHealth());
		stats.add(new TPetWeight());
		stats.add(new TPetHappiness());
		stats.add(new TPetHungriness());

		timer = new Timer(true);
		System.out.println("a");
		timer.scheduleAtFixedRate(new UpdateTimer(), 0, 1000);
		System.out.println("b");
	}
	
	public void cancelTimer() {
		timer.cancel();
	}
	
	public void update() {
		for(TPetStat s : stats) {
			s.update();
		}
	}
	
	private TPetStat findStat(Class c) {
		for(TPetStat s : stats) {
			if(s.getClass().equals(c)) return s;
		}
		return null;
	}
	
	public int getAge() {
		return findStat(TPetAge.class).get();
	}
	
	public void setAge(int age) {
		findStat(TPetAge.class).set(age);
	}
	
	public int getHealth() {
		return findStat(TPetHealth.class).get();
	}
	
	public void setHealth(int health) {
		findStat(TPetHealth.class).set(health);
	}
	
	public int getHappiness() {
		return findStat(TPetHappiness.class).get();
	}
	
	public void setHappiness(int happiness) {
		findStat(TPetHappiness.class).set(happiness);
	}
	
	public int getWeight() {
		return findStat(TPetWeight.class).get();
	}
	
	public void setWeight(int weight) {
		findStat(TPetWeight.class).set(weight);
	}
	
	public int getHungriness() {
		return findStat(TPetHungriness.class).get();
	}
	
	public void setHungriness(int hungry) {
		findStat(TPetHungriness.class).set(hungry);
	}
	
	public List<TPetStat> getStats(){
		return stats;
	}
	
	private class UpdateTimer extends TimerTask{

		@Override
		public void run() {
			update();
			System.out.println("Age:" + (float)getAge());

			System.out.println("Age:" + getAge());
			System.out.println("Health:" + getHealth());
			System.out.println("Weight:" + getWeight());
			System.out.println("Happiness:" + getHappiness());
			System.out.println("Hungriness:" + getHungriness());
		}
	
	}
}
