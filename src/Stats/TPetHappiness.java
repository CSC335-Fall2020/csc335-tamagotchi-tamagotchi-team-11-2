package Stats;

public class TPetHappiness extends TPetStat{
	private MOOD mood;
	
	public static enum MOOD{
		Happy,
		Sad,
		Normal
	}
	
	public TPetHappiness() {
		super();
		data = 50;
		mood = MOOD.Normal;
	}
	
	@Override
	public void update() {
	}
	
}
