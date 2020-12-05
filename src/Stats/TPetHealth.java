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
		List<TPetStat> stats = TPetController.getInstance().getStats();
		
		
		//age
		//	when age >= 80% of lifeSpan, then the max health drops
		TPetAge age = ((TPetAge)stats.get(TPetModel.StatIndex.TPetAge.ordinal()));
		/*if(age.getAge() > age.getLifeSpan()) {
			maxHealth -= 0.1;
		}*/
		
		double hungry = ((TPetHungriness)stats.get(TPetModel.StatIndex.TPetHungriness.ordinal())).get();
		if (hungry < 40) {
			data -= 0.1;
		}
		
		
		double mood = ((TPetHappiness)stats.get(TPetModel.StatIndex.TPetHappiness.ordinal())).get();
		if (mood < 0) {
			data -= 0.1;
		}
		
		double weight = ((TPetWeight)stats.get(TPetModel.StatIndex.TPetWeight.ordinal())).get();
		double idealWeight = ((TPetWeight)stats.get(TPetModel.StatIndex.TPetWeight.ordinal())).getIdealWeight();
		double weightRate = weight/idealWeight;
		if (weightRate > 1.4 || weightRate < 0.6) {
			data -= 0.1;
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
		data = data + 40 > maxHealth ? maxHealth : data + 40;
	}
}
