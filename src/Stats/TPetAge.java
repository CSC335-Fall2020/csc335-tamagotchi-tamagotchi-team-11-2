package Stats;

import java.util.List;

public class TPetAge extends TPetStat {
	
	private int lifeSpan = 600; //10 mins
	private int age = 0;
	
	public TPetAge() {
		
	}
	
	@Override
	public void update() {
		if(!shouldUpdate()) return;

		List<TPetStat> stats = super.getStats();
		System.out.println("stats in age: " + stats);
//		data += 1;
		lifeSpan -= 1; // 1000 is related with the period in TPetModel scheduleAtFixedRate(x,x, period)
		try { 
			age += 1;
			if(age == lifeSpan) {
				//pet dies
			}
		}catch(Exception e){
			age = 0;
		}
		data = age;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getLifeSpan() {
		return lifeSpan; 
	}

	
}
