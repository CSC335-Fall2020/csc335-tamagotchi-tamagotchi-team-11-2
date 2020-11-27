package Stats;

public class TPetAge extends TPetStat {
	
	
	public TPetAge() {
		super();
	}
	
	@Override
	public void update() {
		data += 1;
	}
	
	public int getAge() {
		return (int) Math.floor(data/1000);
	}

	
}
