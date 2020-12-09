package TPet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Food.Drug;
import Food.Food;
import Food.LargeMeal;
import Food.LargeSnack;
import Food.LittleMeal;
import Food.LittleSnack;
import Stats.TPetHappiness;
import Stats.TPetHealth;
import Stats.TPetHungriness;
import Stats.TPetMoney;
import Stats.TPetStat;
import Stats.TPetWeight;
import TPetEffect.TPetEffect1;
import TPetEffect.TPetEffect2;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Team Project
 * Tamagotchi Pet
 * Team 11
 */

public class TPetView extends Application implements Observer{
	private static BorderPane borderpane = new BorderPane();
	public static final int IMAGE_WIDTH = 300;
	public static final int IMAGE_HEIGHT = 180;
	
	

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) throws Exception {

		//
		//--------------------image display starts--------------------
						String path = "img/normal.png";
						Image image = new Image(new FileInputStream(path));
						StackPane imagePane = new StackPane();
						imagePane.setMaxHeight(IMAGE_HEIGHT);
						imagePane.setMaxWidth(IMAGE_WIDTH);
						
						ImageView imageView = new ImageView();
						imageView.setImage(image);
						imageView.setFitWidth(IMAGE_WIDTH);
						imageView.setFitHeight(IMAGE_HEIGHT);
//						VBox imageBox = new VBox();
//						imageBox.getChildren().addAll(image);
						imagePane.getChildren().add(imageView);
						borderpane.setCenter(imagePane);
				
						
				
		//--------------------image desplay ends--------------------	
		//
		
		TPetModel model = new TPetModel();
		TPetController ctrl = new TPetController(model);
		model.deleteObservers();
		model.addObserver(this);
		
		
//		BorderPane borderpane = new BorderPane();
		
//		All stats
		List<TPetStat> stats = ctrl.getInstance().getStats();
		
		TPetStat health = stats.get(TPetModel.StatIndex.TPetHealth.ordinal());
		TPetStat mood = stats.get(TPetModel.StatIndex.TPetHappiness.ordinal());
		TPetStat hungriness = stats.get(TPetModel.StatIndex.TPetHungriness.ordinal());
		TPetStat weight = stats.get(TPetModel.StatIndex.TPetWeight.ordinal());
		TPetStat money = stats.get(TPetModel.StatIndex.TPetMoney.ordinal());
//--------------------top stats bar starts--------------------
		Label healthLabel = new Label("	Health:");
		Label moodLabel = new Label("	Mood:");
		Label hungrinessLabel = new Label("	Food:");
		Label weightLabel = new Label("	Weight:");
		Label moneyLabel = new Label("	Money:");
		
		
		
		//.get(TPetModel.StatIndex.TPetHappiness.ordinal()))
		

		DecimalFormat df = new DecimalFormat("0.0");
		
		String healthPoints = df.format(health.get());
		String moodStats = ((TPetHappiness)mood).getMood().toString();
		String hungrinessPoints = df.format(hungriness.get());
		String weightPoints = df.format(weight.get());
		String moneyPoints = df.format(money.get());
		
		Label healthPointsLabel = new Label(healthPoints);
		Label moodStatsLabel = new Label(moodStats);
		Label hungrinessPointsLabel = new Label(hungrinessPoints);
		Label weightPointsLabel = new Label(weightPoints);
		Label moneyPointsLabel = new Label(moneyPoints);
		
		HBox statsBar = new HBox();
		statsBar.getChildren().add(healthLabel);
		statsBar.getChildren().add(healthPointsLabel);
		statsBar.getChildren().add(moodLabel);
		statsBar.getChildren().add(moodStatsLabel);
		statsBar.getChildren().add(hungrinessLabel);
		statsBar.getChildren().add(hungrinessPointsLabel);
		statsBar.getChildren().add(weightLabel);
		statsBar.getChildren().add(weightPointsLabel);
		statsBar.getChildren().add(moneyLabel);
		statsBar.getChildren().add(moneyPointsLabel);
		
		statsBar.setPadding(new Insets(0,15,0,0));
		borderpane.setTop(statsBar);
		
		
//--------------------top stats bar ends--------------------
//
//--------------------right buttons starts--------------------
		
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(15,15,15,0));
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setHeaderText("");
		int buttonWidth = 80;
		
		//store
		Button store = new Button("Store");
		store.setOnMouseClicked((event)->{
//			((TPetHungriness)hungriness).eat();
			
			Button drug = new Button("Mental Care");
			Button Lmeal = new Button("Meal-Large");
			Button Smeal = new Button("Meal-Small");
			Button Lsnack = new Button("Snack-Large");
			Button Ssnack = new Button("Snack-Small");
			Button effectRedBubble = new Button("Effect: Red Bubble");
			Button effectAquaRect = new Button("Effect: Aqua Rect");
			
			effectRedBubble.setOnMouseClicked((evt) ->{
				if(ctrl.triggerEffect(TPetEffect1.class)) {
					a.setContentText("Success!");
				} else {
					a.setContentText("Failed! "
							+ "\nNot enough money or already activated! "
							+ "\nPrice: 1000");
				}
				a.show();
			});
			effectAquaRect.setOnMouseClicked((evt) ->{
				if(ctrl.triggerEffect(TPetEffect2.class)) {
					a.setContentText("Success!");
				} else {
					a.setContentText("Failed! "
							+ "\nNot enough money or already activated! "
							+ "\nPrice: 1000");
				}
				a.show();
			});
			
			drug.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					Food drug = new Drug();
					if(ctrl.feed(drug)) {
						a.setContentText("Success!");
					} else {
						a.setContentText("Failed! "
								+ "\nNot enough money! "
								+ "\nPrice: " + drug.getPrice());
					}
					a.show();
				}
				
			});
			
			Lmeal.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					Food drug = new LargeMeal();
					if(ctrl.feed(drug)) {
						a.setContentText("Success!");
					} else {
						a.setContentText("Failed! "
								+ "\nNot enough money! "
								+ "\nPrice: " + drug.getPrice());
					}
					a.show();
				}
				
			});
			
			
			Smeal.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					Food drug = new LittleMeal();
					if(ctrl.feed(drug)) {
						a.setContentText("Success!");
					} else {
						a.setContentText("Failed! "
								+ "\nNot enough money! "
								+ "\nPrice: " + drug.getPrice());
					}
					a.show();
				}
				
			});
			
			
			Lsnack.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					Food drug = new LargeSnack();
					if(ctrl.feed(drug)) {
						a.setContentText("Success!");
					} else {
						a.setContentText("Failed! "
								+ "\nNot enough money! "
								+ "\nPrice: " + drug.getPrice());
					}
					a.show();
				}
				
			});
			
			
			Ssnack.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					Food drug = new LittleSnack();
					if(ctrl.feed(drug)) {
						a.setContentText("Success!");
					} else {
						a.setContentText("Failed! "
								+ "\nNot enough money! "
								+ "\nPrice: " + drug.getPrice());
					}
					a.show();
				}
				
			});
			
			
			HBox storeHbox = new HBox();
			storeHbox.getChildren().addAll(drug, Lmeal, Smeal, Lsnack, Ssnack, effectRedBubble, effectAquaRect);
			storeHbox.setSpacing(10);
			storeHbox.setPadding(new Insets(10, 10, 10, 10));
			storeHbox.setAlignment(Pos.CENTER);
			
			
			Scene storeScene = new Scene(storeHbox, 750, 50);
			Stage storeStage = new Stage();
			storeStage.setScene(storeScene);
			storeStage.setTitle("Store");
			storeStage.showAndWait();
			
		});
		store.setPrefWidth(buttonWidth);
		vbox.getChildren().add(store);
		
		//exercise
		Button exercise = new Button("exercise");
		exercise.setOnMouseClicked((event)->{
			((TPetWeight)weight).loseWeight();
		});
		exercise.setPrefWidth(buttonWidth);
		vbox.getChildren().add(exercise);
		
		//hospital
		Button hospital = new Button("hospital");
		hospital.setOnMouseClicked((event)->{
//			((TPetHealth)health).hospital();
			
			if(ctrl.goHospital()) {
				a.setContentText("Success!");
			} else {
				a.setContentText("Failed! "
						+ "\nNot enough money! "
						+ "\nPrice: 500");
			}
			a.show();
		});
		hospital.setPrefWidth(buttonWidth);
		vbox.getChildren().add(hospital);
		
		//work

		Button work = new Button("work");
		work.setOnMouseClicked((event)->{
//			((TPetMoney)money).earnMoney(50);
			if(ctrl.makeMoney()) {
				a.setContentText("Success!");
			} else {
				a.setContentText("Failed!");
			}
			a.show();
		});
		work.setPrefWidth(buttonWidth);
		vbox.getChildren().add(work);
		
		Button save = new Button("Save");
		Button load = new Button("Load");
		save.setOnMouseClicked(new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
				if(ctrl.saveGame(TPetController.SAVEFILE_NAME)) {
					a.setContentText("Success!");
				} else {
					a.setContentText("Failed!");
				}
				a.show();
			}
		});
		load.setOnMouseClicked(new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
				if(ctrl.loadGame(TPetController.SAVEFILE_NAME)) {
					a.setContentText("Success!");
				} else {
					a.setContentText("Failed!");
				}
				a.show();
				
			}
		});
		save.setPrefWidth(buttonWidth);
		load.setPrefWidth(buttonWidth);
		vbox.getChildren().add(save);
		vbox.getChildren().add(load);
		
		borderpane.setRight(vbox);
		
//--------------------right buttons ends--------------------		

//--------------------	display	--------------------
		
		Scene scene = new Scene(borderpane, 600, 270);
		
		primaryStage.setTitle("Tomagotchi-T11");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static StackPane getImagePane() {
		return (StackPane) borderpane.getCenter();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		ArrayList<String> msg = (ArrayList<String>) arg;
		ObservableList<Node> children = ((HBox)borderpane.getTop()).getChildren();
		System.out.println("in update");
		
		String mood = "";
		for (int i = 1; i < children.size(); i += 2) {
			Node cur = children.get(i);
			if ( cur instanceof Label) {
				((Label) cur).setText(msg.get(i/2));
			}
			
			//when processing mood
			if(i == 3) {
				mood = msg.get(i/2);
			}
			if(i == 9) {
				break;
			}
		}

		ImageView imageView = ((ImageView)((StackPane)borderpane.getCenter()).getChildren().get(0));
		
		if(imageView instanceof ImageView) {
//			String path = "img/mood.png";
			Image image = null;
			try {
				String filename = "img/";
				if (msg.get(5).equals("true") {
					filename += "Sick";
				} else {
					filename += mood;
				}
				if (msg.get(6).equals("true")) {
					filename += "Hungry";
				} else {
					filename += msg.get(7);
				}
			 	image = new Image(new FileInputStream("img/" + mood + ".png"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(path);
			imageView.setImage(image);
		}
		
	}
	
}
