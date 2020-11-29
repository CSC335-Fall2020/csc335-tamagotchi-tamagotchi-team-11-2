package Stats;

import java.util.ArrayList;
import java.util.List;

public class TPetStat {
	private List<TPetStat> stats = new ArrayList<TPetStat>();
	protected int data;
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
	
	public int get() {
		return data;
	}
	
	public void set(int data) {
//		this.data = data;
	}
	
	public boolean shouldUpdate() {
		this.tick += 1;
		return this.tick % this.tickPerUpdate == 0;
	}
	
	public void addStat(TPetStat stat) {
		stats.add(stat);
	}
	
	public List<TPetStat> getStats(){
		return stats;
	}
}
