/**Class: Programming Fundamentals III

 *Project #  : From Group Project

 *Author:    Jose Leiva
 *Completion Date: 

 *Project Description: This program is a matching game, the user will be presented with a target icon and an array of buttons. The user will have to match the target icon with buttons with the same icons from the button array within
 *a specified time. The user will earn points 100 points for every correctly matched icon and lose 100 points for every miss-matched icons. If the button clicked is a match with the target, the button will highlight green. If the button clicked
 *is NOT a match with the target, the button will highlight red. After the game expires, the user may end the game or start a new game.
 **/

package matchGame;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Scorecard{
	//constructor
	public Scorecard(String s){
		JFrame frame = new JFrame("Scores");
		JTextArea textarea = new JTextArea();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setPreferredSize (new Dimension(560, 290));
		frame.setLocation((int)(dim.getWidth()/3.5),(int)(dim.getHeight()/3));
		textarea.setBackground(Color.LIGHT_GRAY);
		Font font = new Font("Verdana", Font.BOLD, 14);
		textarea.setFont(font);

		textarea.append(s);
				
		frame.add(textarea);

		frame.pack();
		frame.setVisible(true);	
	}
}