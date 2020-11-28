package Stats;

public class TPetHealth extends TPetStat {
	private boolean isSick;
	
	public TPetHealth() {
		super();
		data = 100;
		isSick = false;
	}

	public int getHealth() {
		return data;	//In percentage. Lower health means higher risk of sick
	}
	
	public boolean getIsSick() {
		return isSick;
	}
	
	@Override
	public void update() {
		data = Math.random() < 0.2 ? data : data - 1; //Simple algorithm. Needs improvement
		
		if(Math.random() < 0.05) {
			//Try to get sick
			if(Math.random() * 100 > data || data < 10) {
				sick();
			}
		}
		
	}
	
	private void sick() {
		isSick = true;
	}
}
