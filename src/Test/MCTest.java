package Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
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
import TPetEffect.TPetEffect1;
import TPetEffect.TPetEffect2;
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
		ctrl.getStats().get(TPetModel.StatIndex.TPetMoney.ordinal()).set(10000); // For testing only
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				ctrl.getStats().get(TPetModel.StatIndex.TPetMoney.ordinal()).set(10000);
				
				TPetController.getInstance().triggerEffect(TPetEffect1.class);
				
				TPetController.getInstance().triggerEffect(TPetEffect1.class);
				
				TPetController.getInstance().triggerEffect(TPetEffect2.class);
				
				ctrl.getStats().get(TPetModel.StatIndex.TPetMoney.ordinal()).set(1); // For testing only
				
				TPetController.getInstance().triggerEffect(TPetEffect1.class);
			}
			
		});
		
		assertTrue(ctrl.getModel() instanceof TPetModel);
		ctrl.getStats().get(TPetModel.StatIndex.TPetMoney.ordinal()).set(100000); // For testing only
		ctrl.getStats().get(TPetModel.StatIndex.TPetHungriness.ordinal()).set(1);
		
		List<Food> foods = new ArrayList<Food>();
		foods.add(new LargeMeal());
		foods.add(new Drug());
		foods.add(new LargeSnack());
		foods.add(new LittleMeal());
		foods.add(new LittleSnack());
		for(Food f : foods) {
			ctrl.feed(f);
		}
		
		TPetModel model = ctrl.getModel();
		model.refresh();
		
		ctrl.makeMoney();
		ctrl.goHospital();
		ctrl.autoSave();
		ctrl.loadGame("save.tpetdat");
		
		
		ctrl.getStats().get(TPetModel.StatIndex.TPetHungriness.ordinal()).set(1);
		ctrl.makeMoney();
		
		ctrl.getStats().get(TPetModel.StatIndex.TPetMoney.ordinal()).set(1);
		ctrl.goHospital();
		ctrl.getStats().get(TPetModel.StatIndex.TPetMoney.ordinal()).set(10000);
		
		TPetEffect1 e1 = new TPetEffect1();
		TPetEffect2 e2 = new TPetEffect2();
		for(int i = 0; i < 50; i++) {
			e1.update();
			e2.update();
		}
		
		
	}
}
