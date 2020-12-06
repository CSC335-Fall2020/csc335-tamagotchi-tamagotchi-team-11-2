package Stats;

public class TPetStat {
	protected double data;
	protected int tickPerUpdate; //How many ticks needed for an update;
	protected int tick;
	
	public TPetStat() {
		this(0, 1);
	}
	
	public TPetStat(int data, int tickPerUpdate) {
		this.data = data;
		this.tickPerUpdate = tickPerUpdate;
	}
	
	public void update() {
		
	}
	
	public double get() {
		return data;
	}
	
	public void set(int data) {
//		this.data = data;
	}
	
	public boolean shouldUpdate() {
		this.tick += 1;
		return this.tick % this.tickPerUpdate == 0;
	}
	
}
