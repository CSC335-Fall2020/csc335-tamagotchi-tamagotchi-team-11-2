package Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Stats.TPetAge;
import Stats.TPetHappiness;
import Stats.TPetHealth;
import Stats.TPetHungriness;
import Stats.TPetMoney;
import Stats.TPetStat;
import Stats.TPetWeight;
import TPet.TPetController;
import TPet.TPetModel;
import TPet.TPetView;
import javafx.application.Application;
import javafx.application.Platform;

public class StatsTest {
	
	public StatsTest() {
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
	public void testStats() {
		TPetHealth health = new TPetHealth();
		assertEquals(health.get(), 100);
		health.decreaseHealth(10);
		assertEquals(health.get(), 90);
		assertFalse(health.getIsSick());
		health.decreaseHealth(100);
		health.decreaseHealth(-101);
		health.update();
		health.set(2);
		health.update();
		health.hospital();
		health.hospital();
		health.hospital();
		
		TPetAge age = new TPetAge();
		assertEquals(age.get(), 0);
		age.update();
		assertEquals(age.get(), 1);
		assertEquals(age.getAge(), 1);
		
		TPetWeight weight = new TPetWeight();
		assertEquals(weight.get(), 15);
		assertEquals(weight.minimumWeight(), weight.getIdealWeight()*0.6);
		assertEquals(weight.getIdealWeight(),15);
		TPetHungriness hungry = ((TPetHungriness)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetHungriness.ordinal()));
		hungry.set(1);
		assertTrue(hungry.isHungry());
		weight.update();
		TPetHealth healthFromModel = ((TPetHealth)TPetController.getInstance().getStats().get(TPetModel.StatIndex.TPetHealth.ordinal()));
		healthFromModel.set(1);
		healthFromModel.update();
		hungry.set(100);
		weight.update();
		weight.set(1);
		weight.loseWeight();
		assertEquals(weight.get(), 9);
		
		TPetHungriness hung = new TPetHungriness();
		hung.eat();
		hung.decreaseHungriness(100);
		hung.increaseHungriness(111);
		
		TPetHappiness hap = new TPetHappiness();
		for(int i = 0; i < 10; i++) {
			hap.update();
		}
		hap.beSad();
		hap.decreaseMood(100);
		for(int i = 0; i < 10; i++) {
			hap.update();
		}
		hap.increaseMood(111);
		for(int i = 0; i < 10; i++) {
			hap.update();
		}
		hungry.set(100);
		healthFromModel.set(100);
		healthFromModel.update();
		for(int i = 0; i < 10; i++) {
			hap.update();
		}
		
		TPetMoney money = new TPetMoney();
		money.earnMoney(100);
		money.spendMoney(100);
	}

}
