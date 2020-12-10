package TPet;

import java.io.Serializable;
import java.util.List;

import Stats.TPetAge;
import Stats.TPetStat;

public class TPetSave implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double age;
	private double happiness;
	private double weight;
	private double hungriness;
	private double money;
	private double health;
	
	/**
     * Purpose: this method is constructor of the class and get the static of the TPet model.
     *
     * @param  None.
     *
     * @return None.
     */
	public TPetSave() {
		List<TPetStat> stats = TPetController.getInstance().getStats();
		age = stats.get(TPetModel.StatIndex.TPetAge.ordinal()).get();
		happiness = stats.get(TPetModel.StatIndex.TPetHappiness.ordinal()).get();
		weight = stats.get(TPetModel.StatIndex.TPetWeight.ordinal()).get();
		hungriness = stats.get(TPetModel.StatIndex.TPetHungriness.ordinal()).get();
		money = stats.get(TPetModel.StatIndex.TPetMoney.ordinal()).get();
		health = stats.get(TPetModel.StatIndex.TPetHealth.ordinal()).get();
	}
	/**
     * Purpose: this method is going to get the age.
     *
     * @param  None.
     *
     * @return Age is a integer.
     */
	public double getAge() {
		return age;
	}
	/**
     * Purpose: this method is going to get the happiness.
     *
     * @param  None.
     *
     * @return happiness is a double.
     */
	public double getHappiness() {
		return happiness;
	}
	/**
     * Purpose: this method is going to get the weight.
     *
     * @param  None.
     *
     * @return weight is a double.
     */
	public double getWeight() {
		return weight;
	}
	/**
     * Purpose: this method is going to get the hungriness.
     *
     * @param  None.
     *
     * @return hungriness is a double.
     */
	public double getHungriness() {
		return hungriness;
	}
	/**
     * Purpose: this method is going to get the money.
     *
     * @param  None.
     *
     * @return money is a double.
     */
	public double getMoney() {
		return money;
	}
	/**
     * Purpose: this method is going to get the health.
     *
     * @param  None.
     *
     * @return health is a double.
     */
	public double getHealth() {
		return health;
	}
}
