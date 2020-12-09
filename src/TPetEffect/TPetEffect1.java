package TPetEffect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import TPet.TPetView;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TPetEffect1 extends TPetEffect{

	List<Group> elements = new ArrayList<>();
	
	public TPetEffect1() {
		for(int i = 0; i < 30; i++) {
			newParticle();
		}
	}
	
	public void addGroupToView(Group g) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				TPetView.getImagePane().getChildren().add(g);
			}
			
		});
	}
	
	public void removeGroupFromView(Group g) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				TPetView.getImagePane().getChildren().remove(g);
			}
			
		});
	}
	
	public void newParticle() {
		Group g = new Group();
		Circle circle = new Circle();
		circle.setLayoutX(TPetView.IMAGE_WIDTH);
		circle.setLayoutY(TPetView.IMAGE_HEIGHT);
		circle.setRadius(0);
		g.getChildren().add(circle);
		Effect1Element e = new Effect1Element();
		g.getChildren().add(e);
		elements.add(g);
		addGroupToView(g);
		
	}
	
	@Override
	public synchronized void update() {
		ImageView image = (ImageView)TPetView.getImagePane().getChildren().get(0);
		image.setLayoutX(0);
		image.setLayoutY(0);
		
		int counter = 0;
		for(Iterator<Group> i = elements.iterator(); i.hasNext();) {
			Group g = i.next();
			Effect1Element e = (Effect1Element)g.getChildren().get(1);
			if(e.counter == e.lifeSpan) {
				i.remove();
				counter++;
				removeGroupFromView(g);
				continue;
			}
			if(e.getCenterX() < 10 || e.getLayoutX() > TPetView.IMAGE_WIDTH * 2 ||
					e.getCenterY() < 10 || e.getLayoutY() > TPetView.IMAGE_HEIGHT * 2) {
				removeGroupFromView(g);
				i.remove();
				counter++;
				continue;
			}
			e.counter += 1;
			TranslateTransition transition = new TranslateTransition();
			transition.setDuration(Duration.millis(1100));
			transition.setAutoReverse(false);
			transition.setByY(-1 * e.speed);
			transition.setCycleCount(1);
			transition.setOnFinished((evt) -> {
				TranslateTransition t = (TranslateTransition)evt.getSource();
				t.stop();
			});
			transition.setNode(e);
			transition.playFrom(Duration.millis(100));
		}
		for(int i = 0; i < counter; i++) {
			newParticle();
		}
	}
	
	class Effect1Element extends Circle{
		private int counter;
		private int lifeSpan;
		private double speed;
		private double size;
		
		public Effect1Element() {
			reset();
		}
		
		public void reset() {
			this.counter = 0;
			do {
				this.lifeSpan = 5 + (int) (100 * (Math.random()/2));
				this.speed = 1 + 100 * Math.random();
			} while(speed * lifeSpan > TPetView.IMAGE_HEIGHT * 1.85 
					|| speed == 0 
					|| lifeSpan == 0
					|| speed * lifeSpan < TPetView.IMAGE_HEIGHT*1.8);
			this.size = 1 + 8 * Math.random();
			
			this.setCenterX(TPetView.IMAGE_WIDTH * 0.05 + TPetView.IMAGE_WIDTH * 1.90 * Math.random());
			this.setCenterY(size * -2 + TPetView.IMAGE_HEIGHT * 1.99 - (Math.random() * 10));
			
			this.counter = 0;
			this.setRadius(size);
			this.setOpacity(0.5);
			this.setFill(Color.RED);
		}
	}
}
