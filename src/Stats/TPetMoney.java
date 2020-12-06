package Stats;

public class TPetMoney extends TPetStat{
	public TPetMoney() {
		super(100, 1);
	}
	
	public void update() {
		
	}
	
	public void earnMoney(double amount) {
		data += amount;
	}
	
	public void spendMoney(double d) {
		data -= d;
	}
}
