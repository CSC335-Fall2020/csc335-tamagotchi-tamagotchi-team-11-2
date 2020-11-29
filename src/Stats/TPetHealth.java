package Stats;

import java.util.List;


public class TPetHealth extends TPetStat {
	private boolean isSick;
	private int maxHealth = 100;
	private int health;
	
	public TPetHealth() {
		super();
		health = maxHealth;
		isSick = false;
	}

	public int getHealth() {
		return health;	//In percentage. Lower health means higher risk of sick
	}
	
	public void set(int health) {
		this.health = health;
	}
	
	public boolean getIsSick() {
		return isSick;
	}
	
	@Override
	public void update() {
		if(!shouldUpdate()) return;
		//hungriness
		//	if hungriness below 60, health -0.1 per second
		
		//happiness
		//	if mood is sad,  health -0.1 per second
		
		//age
		//	when age >= 80% of lifeSpan, then the max health drops
		
		
	}
	
	private void sick() {
		isSick = true;
	}
	
	public int get() {
		return health;
	}
}
