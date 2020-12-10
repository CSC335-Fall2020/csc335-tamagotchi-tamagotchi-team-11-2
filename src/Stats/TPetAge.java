package Stats;

import java.util.List;

import TPet.TPetModel;

/**
 * This class defines behavior of age stat
 * @author tamagotchi-team11
 *
 */
public class TPetAge extends TPetStat {
	

	
	public TPetAge() {
		super(0, 1);
	}
	
	/**
     * Purpose: this method is going to update the static of the age.
     *
     * @param  None.
     *
     * @return None.
     * 
     */
	@Override
	public void update() {
		if(!shouldUpdate()) return;


		data += 1;
//		lifeSpan -= 1; // 1000 is related with the period in TPetModel scheduleAtFixedRate(x,x, period)

	}
	
	/**
     * Purpose: this method is going to return the age.
     *
     * @param  None.
     *
     * @return age is a integer.
     * 
     */
	public int getAge() {
		return (int)data;

	}

	
}
