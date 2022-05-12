package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import control.GameController;

public class PlayerPanel extends GamePanel{

	JButton selectPlayerBtn;
	int pos;
	String currentPlayer;
	JLabel plMark;
	JTextArea plStats;
		
	public PlayerPanel(GameController c, int pos) {
		super(c);
		this.pos=pos;		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));
		this.setBackground(Color.gray);
		this.setBorder(new LineBorder(Color.black,1,true));
		this.setAlignmentX(CENTER_ALIGNMENT);
		selectPlayerBtn = new JButton("Choose Player");
		selectPlayerBtn.setPreferredSize(new Dimension(150,40));
		selectPlayerBtn.setAlignmentX(CENTER_ALIGNMENT);
		selectPlayerBtn.addActionListener((e)->{changePlayer();});
		this.add(selectPlayerBtn);
				
		plMark = new JLabel(pos==0? "X" : "O");
		plMark.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,80));
		plMark.setAlignmentX(CENTER_ALIGNMENT);
		plMark.setHorizontalAlignment(JTextField.CENTER);
		plMark.setEnabled(true);
		Font markf = new Font("Serif", Font.BOLD,90);
		plMark.setFont(markf);
				
		plStats = new JTextArea(10,100);		
		plStats.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,400));
		plStats.setAlignmentX(CENTER_ALIGNMENT);
		plStats.setBackground(Color.gray);
		Font statsf = new Font("Serif", Font.BOLD,30);
		plStats.setFont(statsf);
		plStats.setEnabled(true);		
		plStats.setMargin(new Insets(10, 10, 10, 10));
		
		this.add(plMark);		
		this.add(plStats);
	}

	public void changePlayer() {
		String[] allPlayers = gc.getModel().getPlayerCatalogue().getPlayers();
		Arrays.sort(allPlayers);

		String selPlayer = (String) JOptionPane.showInputDialog(this, 
				"Choose a Player...",
				"Player selection",
				JOptionPane.PLAIN_MESSAGE,
				null,
				allPlayers,
				currentPlayer
				);
		
		if(selPlayer != null) {
			if (selPlayer.equals(gc.getModel().getGamePlayers()[pos==0?1:0])) {
				JOptionPane.showMessageDialog(gc.getView(), 						
						"Player already selected",
						"Ooops...",
						JOptionPane.ERROR_MESSAGE);
				return;
			}			
			this.currentPlayer=selPlayer;			
			gc.selectPlayer(selPlayer,pos);
			this.plStats.setText(currentPlayer);
			this.setPlayerStats(gc.getModel().getPlayerStats(currentPlayer));
			this.repaint();
		}
	}	

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	
	public JTextArea getPlStats() {
		return plStats;
	}
	
	public void setPlayerStats(String stats) {
		this.plStats.setText(stats);
	}

	public JButton getSelectPlayerBtn() {
		return selectPlayerBtn;
	}	
}
