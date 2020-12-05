package Stats;

import java.util.List;

import TPet.TPetModel;

public class TPetAge extends TPetStat {
	
	//private int lifeSpan = 600; // Expected living time
	
	public TPetAge() {
		super(0, 1);
	}
	
	@Override
	public void update() {
		if(!shouldUpdate()) return;

		List<TPetStat> stats = super.getStats();
		//TPetHealth health = ((TPetHealth)stats.get(TPetModel.StatIndex.TPetHealth.ordinal()));
		data += 1;
	}
	
	public int getAge() {
		return (int)data;
	}
	
	/*public int getLifeSpan() {
		return lifeSpan; 
	}*/

	
}
