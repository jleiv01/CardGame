      /**Class: Programming Fundamentals III

        *Project #  : From Group Project

        *Author:    Jose Leiva
        *Completion Date: 

        *Project Description: This program is a matching game, the user will be presented with a target icon and an array of buttons. The user will have to match the target icon with buttons with the same icons from the button array within
        *a specified time. The user will earn points 100 points for every correctly matched icon and lose 100 points for every miss-matched icons. If the button clicked is a match with the target, the button will highlight green. If the button clicked
        *is NOT a match with the target, the button will highlight red. After the game expires, the user may end the game or start a new game.
       **/


package matchGame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
public class MatchGameDriver {

	public static void main(String[] args) {

				  JFrame frame = new JFrame ("Welcome to Match Game!");
				  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

			      frame.setPreferredSize (new Dimension(dim.width-200,dim.height-200));
			   
			      Welcome welcome = new Welcome();

			      welcome.setPreferredSize(new Dimension(dim.width-200, dim.height-200));
			    
			      frame.getContentPane().add(welcome);
			      
			      frame.pack();
			      frame.setVisible(true);
	}
}


