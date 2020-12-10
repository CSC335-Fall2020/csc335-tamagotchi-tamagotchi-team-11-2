package TPetEffect;

import TPet.TPetView;
import javafx.application.Platform;
import javafx.scene.Group;

/**
 * This is superclass of TPetEffect. 
 * This class defines basic methods of a effect.
 * @author zhengxuanxie
 *
 */
public abstract class TPetEffect {
	//private int lifeSpan;
	
	TPetEffect() {
		
	}
	
	/**
	 * This method removes a given group from the view
	 * @param (Group) g
	 */
	protected void removeGroupFromView(Group g) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				TPetView.getImagePane().getChildren().remove(g);
			}
			
		});
	}
	
	/**
	 * This method adds a given group to the View
	 * @param (Group)g
	 */
	protected void addGroupToView(Group g) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				TPetView.getImagePane().getChildren().add(g);
			}
			
		});
	}
	
	public abstract void update();
}
