package Stats;

import java.util.List;

import TPet.TPetModel;

public class TPetAge extends TPetStat {
	

	
	public TPetAge() {
		super(0, 1);
	}
	
	@Override
	public void update() {
		if(!shouldUpdate()) return;


		data += 1;
//		lifeSpan -= 1; // 1000 is related with the period in TPetModel scheduleAtFixedRate(x,x, period)

	}
	

	public int getAge() {
		return (int)data;

	}

	
}
