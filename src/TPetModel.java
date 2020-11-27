import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import Stats.TPetAge;
import Stats.TPetHappiness;
import Stats.TPetHealth;
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
	
	public TPetModel() {
		stats = new ArrayList<TPetStat>();
		stats.add(new TPetAge());
		stats.add(new TPetHealth());
		stats.add(new TPetWeight());
		stats.add(new TPetHappiness());
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
}
