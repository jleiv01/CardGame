      /**Class: Programming Fundamentals III

        *Project #  : Group Project

        *Students:    Jackson Martinez, Jose Leiva, Gale Sanchez, Mark Cavazos, David Ponoder

        *Completion Date: 

        *Project Description: This program is a matching game, the user will be presented with a target icon and an array of buttons. The user will have to match the target icon with buttons with the same icons from the button array within
        *a specified time. The user will earn points 100 points for every correctly matched icon and lose 100 points for every miss-matched icons. If the button clicked is a match with the target, the button will highlight green. If the button clicked
        *is NOT a match with the target, the button will highlight red. After the game expires, the user may end the game or start a new game.
       **/


package matchGame;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Welcome extends JPanel{
	private JPanel welcome, eastPanel, centerPanel, northPanel;
	private JLabel directions1, directions2, directions3, directions4, directions5, directions6, directions7, directionScreenshot;
	private JButton close;

	
	public Welcome(){
		ImageIcon directionsIcon = new ImageIcon("img/DirectionScreenshot.png");
		//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//panels
		welcome = new JPanel();
		eastPanel = new JPanel();
		centerPanel = new JPanel();
		northPanel = new JPanel();
		
		//panel formatting
		welcome.setLayout(new BorderLayout());
		northPanel.setPreferredSize(new Dimension(100, 200));
		eastPanel.setPreferredSize(new Dimension(600, 600));

		//Components
		directions1 = new JLabel("Directions:\n 1) Select a difficulty.\n ");
		directions1.setHorizontalAlignment(SwingConstants.LEFT);
		directions2 = new JLabel("2) Match this icon with as many as you can on the board.\n"
				+ "Match all possible to get the bonus!");
		directions2.setHorizontalAlignment(SwingConstants.LEFT);
		directions3 = new JLabel("3) Try to match as many as you can within the time limit.\n");
		directions3.setHorizontalAlignment(SwingConstants.LEFT);
		directions4 = new JLabel("4) You earn 100 points for a correct match and lose 100 points for an incorrect match.");
		directions4.setHorizontalAlignment(SwingConstants.LEFT);
		directions5 = new JLabel("5) This is the amount you have gotten correct so far.          ");
		directions5.setHorizontalAlignment(SwingConstants.LEFT);
		directions6 = new JLabel("6) Hit this button to start a new game.");
		directions6.setHorizontalAlignment(SwingConstants.LEFT);
		directions7 = new JLabel("7) HIt this button to end the game and display the scorecard.");
		directions7.setHorizontalAlignment(SwingConstants.LEFT);
		directionScreenshot = new JLabel(directionsIcon);
		close = new JButton("Start Game!");
		close.addActionListener(new ButtonListener());
		
		//add panels and components
		add(welcome);
		eastPanel.add(directions1);
		eastPanel.add(directions2);
		eastPanel.add(directions3);
		eastPanel.add(directions4);
		eastPanel.add(directions5);
		eastPanel.add(directions6);
		eastPanel.add(directions7);
		centerPanel.add(directionScreenshot);
		northPanel.add(close);
		welcome.add(eastPanel, BorderLayout.EAST);
		welcome.add(centerPanel, BorderLayout.WEST);
		welcome.add(northPanel, BorderLayout.NORTH);
	}
	//ButtonListener to close the window and take user to the start game window
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==close){
				MatchGame game = new MatchGame();
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				game.setPreferredSize (new Dimension(dim.width-200, dim.height-200));
				JFrame frame = new JFrame ("Match Game");
				frame.getContentPane().add(game);
				frame.pack();
			    frame.setVisible(true);
			    Window w = SwingUtilities.getWindowAncestor(Welcome.this);
				w.setVisible(false);
				
			}
		}
	}
}
