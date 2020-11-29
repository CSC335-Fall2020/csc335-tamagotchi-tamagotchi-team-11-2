package TPet;

import java.util.List;

import Stats.TPetHealth;
import Stats.TPetStat;

/*
 * Team Project
 * Tamagotchi Pet
 * Team 11
 * 
 * Controller class. Provides interfaces to others to interact with model.
 */

public class TPetController {
	private TPetModel model;
	public static TPetController _instance;
	
	public TPetController() {
		this(new TPetModel());
	}
	
	public TPetController(TPetModel model) {
		this.model = model;
		_instance = this;
	}
	
	public static TPetController getInstance() {
		return _instance;
	}
	
	public List<TPetStat> getStats(){
		return model.getStats();
	}
}