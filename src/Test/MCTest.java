package Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Food.Drug;
import Food.Food;
import Food.LargeMeal;
import Food.LargeSnack;
import Food.LittleMeal;
import Food.LittleSnack;
import TPet.TPetController;
import TPet.TPetModel;
import TPet.TPetView;
import javafx.application.Application;
import javafx.application.Platform;

public class MCTest {
	public MCTest() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				Application.launch(TPetView.class);
				Platform.exit();
			}
			
		});
		thread.start();
		try {
			thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void MCTest() {
		TPetController ctrl = TPetController.getInstance();
		assertTrue(ctrl.getModel() instanceof TPetModel);
		ctrl.getStats().get(TPetModel.StatIndex.TPetMoney.ordinal()).set(100000); // For testing only
		
		List<Food> foods = new ArrayList<Food>();
		foods.add(new LargeMeal());
		foods.add(new Drug());
		foods.add(new LargeSnack());
		foods.add(new LittleMeal());
		foods.add(new LittleSnack());
		for(Food f : foods) {
			ctrl.feed(f);
		}
		
		ctrl.makeMoney();
		ctrl.goHospital();
		ctrl.autoSave();
		ctrl.loadGame("save.tpetdat");
		
		TPetModel model = ctrl.getModel();
		model.refresh();
	}
}
