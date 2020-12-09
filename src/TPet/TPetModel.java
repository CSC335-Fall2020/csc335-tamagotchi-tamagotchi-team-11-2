package TPet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import Stats.TPetAge;
import Stats.TPetHappiness;
import Stats.TPetHealth;
import Stats.TPetHungriness;
import Stats.TPetMoney;
import Stats.TPetStat;
import Stats.TPetWeight;
import TPetEffect.TPetEffect;
import TPetEffect.TPetEffect1;
import javafx.application.Platform;



/*
 * Team Project
 * Tamagotchi Pet
 * Team 11
 * 
 * Model class. The core logics of the game
 */

public class TPetModel extends Observable{
	private List<TPetStat> stats;
	private List<TPetEffect> effects;
	private Timer timer;
	private int autoSaveTicker = 0;
	
	public static enum StatIndex{
		TPetAge,
		TPetHealth,
		TPetWeight,
		TPetHappiness,
		TPetHungriness,
		TPetMoney;
	}
	
	public TPetModel() {
		stats = new ArrayList<TPetStat>();
		stats.add(new TPetAge());
		stats.add(new TPetHealth());
		stats.add(new TPetWeight());
		stats.add(new TPetHappiness());
		stats.add(new TPetHungriness());
		stats.add(new TPetMoney());
		
		effects = new ArrayList<TPetEffect>();
		//effects.add(new TPetEffect1());

		timer = new Timer(true);
//		System.out.println("a");
		timer.scheduleAtFixedRate(new UpdateTimer(), 0, 1000);
//		System.out.println("b");
		
	}
	
	public void addEffect(TPetEffect effect) {
		this.effects.add(effect);
	}
	
	public List<TPetEffect> getEffects(){
		return effects;
	}
	
	public void cancelTimer() {
		timer.cancel();
	}
	
	public void update() {
		for(TPetStat s : stats) {
			s.update();
		}
		for(TPetEffect e : effects) {
			e.update();
		}
		if(autoSaveTicker++ == 30) {
			autoSaveTicker = 0;
			TPetController.getInstance().autoSave();
		}
	}
	
	public List<TPetStat> getStats(){
		return stats;
	}
	public void refresh() {
		System.out.println("in refresh");
		System.out.println(this.countObservers());
		this.setChanged();
		//this.notifyObservers("abc");
//		this.notifyObservers();
	}
	private class UpdateTimer extends TimerTask{

		@Override
		public void run() {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					update();
//					System.out.println("Age:" + (float)getAge());
//
//					System.out.println("Age:" + getAge());
//					System.out.println("Health:" + getHealth());
//					System.out.println("Weight:" + getWeight());
//					System.out.println("Happiness:" + getHappiness());
//					System.out.println("Hungriness:" + getHungriness());
					
					DecimalFormat df = new DecimalFormat("0.0");
					
					String healthPoints = df.format(stats.get(TPetModel.StatIndex.TPetHealth.ordinal()).get());
					String moodStats = ((TPetHappiness)stats.get(TPetModel.StatIndex.TPetHappiness.ordinal())).getMood().toString();
					String hungrinessPoints = df.format(stats.get(TPetModel.StatIndex.TPetHungriness.ordinal()).get());
					String weightPoints = df.format(stats.get(TPetModel.StatIndex.TPetWeight.ordinal()).get());
					String moneyPoints = df.format(stats.get(TPetModel.StatIndex.TPetMoney.ordinal()).get());
					boolean sickStats = ((TPetHealth)stats.get(TPetModel.StatIndex.TPetHealth.ordinal()).getIsSick();
				        String sick = null;
					if (sickStats) {
						sick = "true";
					} else {
						sick = "false";
					}
					boolean hungryStats = ((TPetHungriness)stats.get(TPetModel.StatIndex.TPetHungriness.ordinal())).isHungry();
					String hungry = null;
					if (hungryStats) {
						hungry = "true";
					} else {
						hungry = "false";
					}
					String Fatness = ((TPetWeight)stats.get(TPetModel.StatIndex.TPetWeight.ordinal())).getSize();
							     
					ArrayList<String> msg = new ArrayList<String>(Arrays.asList(healthPoints, moodStats, hungrinessPoints, weightPoints, moneyPoints, sick, hungry, Fatness));
					setChanged();
					notifyObservers(msg);
				}
			});
			
		}
	
	}
}
