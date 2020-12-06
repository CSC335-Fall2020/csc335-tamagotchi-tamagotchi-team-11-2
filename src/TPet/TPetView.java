package TPet;
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
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Team Project
 * Tamagotchi Pet
 * Team 11
 */

public class TPetView extends Application implements Observer{
	private BorderPane borderpane = new BorderPane();
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) throws Exception {
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
		
		borderpane.setTop(statsBar);
		
		
//--------------------top stats bar ends--------------------
//
//--------------------right buttons starts--------------------
		
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(0,10,0,0));
		
		int buttonWidth = 80;
		
		//store
		Button store = new Button("Food Store");
		store.setOnMouseClicked((event)->{
//			((TPetHungriness)hungriness).eat();
			Button drug = new Button("Mental Care");
			Button Lmeal = new Button("Meal-Large");
			Button Smeal = new Button("Meal-Small");
			Button Lsnack = new Button("Snack-Large");
			Button Ssnack = new Button("Snack-Small");
			
			drug.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					Food drug = new Drug();
					ctrl.feed(drug);
				}
				
			});
			
			Lmeal.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					Food drug = new LargeMeal();
					ctrl.feed(drug);
				}
				
			});
			
			
			Smeal.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					Food drug = new LittleMeal();
					ctrl.feed(drug);
				}
				
			});
			
			
			Lsnack.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					Food drug = new LargeSnack();
					ctrl.feed(drug);
				}
				
			});
			
			
			Ssnack.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					Food drug = new LittleSnack();
					ctrl.feed(drug);
				}
				
			});
			
			
			HBox storeHbox = new HBox();
			storeHbox.getChildren().addAll(drug, Lmeal, Smeal, Lsnack, Ssnack);
			storeHbox.setSpacing(10);
			storeHbox.setPadding(new Insets(10, 10, 10, 10));
			
			
			
			Scene storeScene = new Scene(storeHbox, 600, 50);
			Stage storeStage = new Stage();
			storeStage.setScene(storeScene);
			storeStage.setTitle("Food Store");
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
			ctrl.goHospital();
		});
		hospital.setPrefWidth(buttonWidth);
		vbox.getChildren().add(hospital);
		
		//work

		Button work = new Button("work");
		work.setOnMouseClicked((event)->{
//			((TPetMoney)money).earnMoney(50);
			ctrl.makeMoney();
		});
		work.setPrefWidth(buttonWidth);
		vbox.getChildren().add(work);
		
		borderpane.setRight(vbox);
		
//--------------------right buttons ends--------------------		
//
//--------------------image display starts--------------------
		String path = "img/Normal.png";
		Image image = new Image(new FileInputStream(path));
		
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitWidth(300);
		imageView.setFitHeight(180);
//		VBox imageBox = new VBox();
//		imageBox.getChildren().addAll(image);
		borderpane.setCenter(imageView);
		
		
//--------------------image desplay ends--------------------	
//
//--------------------	display	--------------------
		
		Scene scene = new Scene(borderpane,500,204);
		
		primaryStage.setTitle("Tomagotchi-T11");
		primaryStage.setScene(scene);
		primaryStage.show();
		
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

		ImageView imageView = ((ImageView)borderpane.getCenter());
		
		if(imageView instanceof ImageView) {
//			String path = "img/Happy.png";
			Image image = null;
			try {
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
