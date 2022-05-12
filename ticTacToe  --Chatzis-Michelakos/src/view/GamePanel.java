package view;

import javax.swing.JPanel;

import control.GameController;
import model.GameModel;

public class GamePanel extends JPanel{

protected GameController gc;
	
	public GamePanel(GameController gc) {
		super();
		this.gc = gc;
	}
	
	
	
	protected GameController getController() {
		return this.gc;
	}	

	
	protected GameModel getModel() {
		return gc.getModel();
	}
}
