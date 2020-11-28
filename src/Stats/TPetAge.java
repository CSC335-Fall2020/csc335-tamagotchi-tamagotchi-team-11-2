package Stats;

public class TPetAge extends TPetStat {
	
	
	public TPetAge() {
		super();
	}
	
	@Override
	public void update() {
		if(!shouldUpdate()) return;
		data += 1;
	}
	
	public int getAge() {
		return (int) Math.floor(data/1000);
	}

	
}
