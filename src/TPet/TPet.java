package TPet;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;

/*
 * Team Project
 * Tamagotchi Pet
 * Team 11
 * 
 * The main class. Launches the game.
 */


public class TPet {
	public static void main(String[] args) {
//		Application.launch(TPetView.class, args);
		TPetModel model = new TPetModel();
//		model.getHappiness();
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				System.out.println("--------------------");
			}
		};
		timer.scheduleAtFixedRate(task, 0, 500);
		

	}
}
