package Stats;

/**
 * This class defines behavior of money stat
 * @author tamagotchi-team11
 *
 */
public class TPetMoney extends TPetStat{
	public TPetMoney() {
		super(100, 1);
	}
	
	
	/**
     * Purpose: this method is going to update the static of the money.
     *
     * @param  None.
     *
     * @return None.
     * 
     */
	public void update() {
		
	}
	/**
     * Purpose: this method is going to increase the Money.
     *
     * @param  the amount is the current Money double.
     *
     * @return None.
     * 
     */
	public void earnMoney(double amount) {
		data += amount;
	}
	/**
     * Purpose: this method is going to decrease the Money.
     *
     * @param  the amount is the current Money double.
     *
     * @return None.
     * 
     */
	public void spendMoney(double d) {
		data -= d;
	}
}
