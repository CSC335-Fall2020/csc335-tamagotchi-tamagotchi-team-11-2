package Stats;

import java.util.List;

import TPet.TPetController;
import TPet.TPetModel;


public class TPetHealth extends TPetStat {
	private boolean isSick;
	private int maxHealth = 100;
	
	public TPetHealth() {
		super();
		data = maxHealth;
		isSick = false;
	}
	
	public void set(int health) {
		this.data = health;
	}
	
	public boolean getIsSick() {
		return isSick;
	}
	
	@Override
	public void update() {
		if(!shouldUpdate()) return;
		//hungriness
		//	if hungriness below 40, health -0.1 per second
		
		//happiness
		//	if mood is sad,  health -0.1 per second
		
		//age
		//	when age >= 80% of lifeSpan, then the max health drops
		if(data > 0) {
			List<TPetStat> stats = TPetController.getInstance().getStats();
			TPetAge age = ((TPetAge)stats.get(TPetModel.StatIndex.TPetAge.ordinal()));
//			if(age.get() > age.getLifeSpan() * 0.8) {
//				maxHealth -= 0.1;
//				if (data > maxHealth) {
//					data = maxHealth;
//				}
//			}
//			
			double hungry = ((TPetHungriness)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetHungriness.ordinal())).get();
			if (hungry < 40) {
				data -= 0.1;
			}
			
			
			double mood = ((TPetHappiness)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetHappiness.ordinal())).get();
			if (mood < 40) {
				data -= 0.1;
			}
			
			double weight = ((TPetWeight)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetWeight.ordinal())).get();
			double idealWeight = ((TPetWeight)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetWeight.ordinal())).getIdealWeight();
			double rate = weight/idealWeight;
			if (rate > 1.4 || rate < 0.7) {
				data -= 0.1;
			}
		}
		
		if (data < 40) {
			sick();
		}
		
		if (data > 80) {
			cured();
		}
	}
	
	private void sick() {
		isSick = true;
	}
	
	private void cured() {
		isSick = false;
	}
	
	public void hospital() {
		data += 40;
		System.out.println("hospital");
		if(data >= 100) {
			System.out.println("hospital too much");
			data = 100;
		}
	}
	
	public void decreaseHealth(int amount) {
		data -= amount;
		if(data < 0) {
			data = 0;
		} else if(data > maxHealth) {
			data = maxHealth;
		}
	}
}
