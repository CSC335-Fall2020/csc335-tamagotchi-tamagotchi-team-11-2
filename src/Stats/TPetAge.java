package Stats;

public class TPetAge extends TPetStat {
	
	private double lifeSpan = 600; //10 mins
	
	public TPetAge() {
		super(0, 1);
	}
	
	@Override
	public void update() {
		if(!shouldUpdate()) return;

		data += 1;
//		lifeSpan -= 1; // 1000 is related with the period in TPetModel scheduleAtFixedRate(x,x, period)

		if (data == lifeSpan) {
			//pet dies
		}
	}
	
	public double getLifeSpan() {
		return lifeSpan;
	}

	
}
