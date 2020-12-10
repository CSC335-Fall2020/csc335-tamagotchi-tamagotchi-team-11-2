package Stats;

/**
 * This class defines behavior of stats
 * @author tamagotchi-team11
 *
 */
public class TPetStat {
	protected double data;
	protected int tickPerUpdate; //How many ticks needed for an update;
	protected int tick;
	
	public TPetStat() {
		this(0, 1);
	}
	
	
	/**
     * Purpose: this is the constructor of the health static.
     *
     * @param  data is the static integer, tickPerUpdate is the integer.
     *
     * @return None.
     * 
     */
	public TPetStat(int data, int tickPerUpdate) {
		this.data = data;
		this.tickPerUpdate = tickPerUpdate;
	}
	
	public void update() {
		
	}
	
	/**
     * Purpose: this is going to return the data .
     *
     * @param  None.
     *
     * @return data is the double.
     * 
     */
	public double get() {
		return data;
	}
	/**
     * Purpose: this is going to set the data .
     *
     * @param  data is the double that we want to change.
     *
     * @return None.
     * 
     */
	public void set(double data) {
		this.data = data;
	}
	
	
	/**
     * Purpose: this is going to check if it should be updated.
     *
     * @param  None.
     *
     * @return true for should be updated, false for not.
     * 
     */
	public boolean shouldUpdate() {
		this.tick += 1;
		return this.tick % this.tickPerUpdate == 0;
	}
	
}
