/**Class: Programming Fundamentals III

 *Project #  : From Group Project

 *Author:    Jose Leiva
 *Completion Date: 

 *Project Description: This program is a matching game, the user will be presented with a target icon and an array of buttons. The user will have to match the target icon with buttons with the same icons from the button array within
 *a specified time. The user will earn points 100 points for every correctly matched icon and lose 100 points for every miss-matched icons. If the button clicked is a match with the target, the button will highlight green. If the button clicked
 *is NOT a match with the target, the button will highlight red. After the game expires, the user may end the game or start a new game.
 **/


package matchGame;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

import javax.swing.*;
public class MatchGame extends JPanel{
	//timer
	private Timer timer;

	//panels
	private JPanel main;
	private JPanel grid;
	private JPanel north;
	private JPanel south;


	//panel components
	private JButton newGame, endGame;
	private JLabel target,timerLabel,scoreLabel,scoreText, possibleCorrect, amountCorrect, startImage,gameOverDialog;
	private JComboBox difficultySelect;


	//instance variables
	private int selection,numClickedCorrect;
	private int timerCount=0;
	private int controlValue,score,numCorrect,numIncorrect;
	private int roundCount=0;

	//arrays
	String [] difficulty = {"Please select difficulty","easy(5X5)","medium(6X6)","hard(7X7)"};
	//Jbutton array of 49 buttons
	JButton [] buttons = new JButton[49];
	int [] buttonCheck = new int[49];
	String[] imageLocation = {"img/1.png","img/2.png","img/3.png","img/4.png","img/5.png","img/6.png"};
	int[] scoreSet = new int[11];

	//constructor
	public MatchGame(){
		difficultySelect = new JComboBox(difficulty);
		difficultySelect.setPreferredSize(new Dimension(300,50));

		main = new JPanel();
		main.setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		main.setPreferredSize (new Dimension(dim.width-300, dim.height-300));

		grid = new JPanel();
		north = new JPanel();
		north.setLayout(new GridLayout(1,3));
		north.setOpaque(true);
		target = new JLabel();

		south = new JPanel();
		south.setLayout(new GridLayout(1,3));

		difficultySelect.addActionListener(new ComboBoxListener());

		timerLabel = new JLabel();
		timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timerLabel.setFont(new Font("verdana",Font.PLAIN,25));
		scoreLabel = new JLabel(Integer.toString(score));
		scoreLabel.setHorizontalAlignment(SwingConstants.LEFT);
		scoreLabel.setFont(new Font("verdana",Font.PLAIN,25));
		scoreText = new JLabel("Score: ");
		scoreText.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreText.setFont(new Font("verdana",Font.PLAIN,25));
		possibleCorrect = new JLabel(Integer.toString(numClickedCorrect));
		possibleCorrect.setFont(new Font("verdana",Font.PLAIN,25));
		possibleCorrect.setHorizontalAlignment(SwingConstants.LEFT);
		amountCorrect = new JLabel("Amount Correct: ");
		amountCorrect.setFont(new Font("verdana",Font.PLAIN,20));
		amountCorrect.setHorizontalAlignment(SwingConstants.RIGHT);
		newGame = new JButton("New Game");
		newGame.addActionListener(new ButtonListener());
		endGame = new JButton("End Game");
		ImageIcon startImageIcon = new ImageIcon("Intro.png");
		startImage = new JLabel(startImageIcon);
		grid.add(startImage);

		//add 4 panels
		add(main);
		add(north);
		add(grid);
		add(south);

		//add 3 panels to main panel using BorderLayout
		main.add(grid, BorderLayout.CENTER);
		main.add(north, BorderLayout.NORTH);
		main.add(south, BorderLayout.SOUTH);

		//add components to panels
		north.add(difficultySelect);
		north.add(target);
		north.add(timerLabel);
		south.add(scoreText);
		south.add(scoreLabel);	
		south.add(amountCorrect);
		south.add(possibleCorrect);
		south.add(newGame);
		south.add(endGame);
		newGame.setEnabled(false);
		endGame.setEnabled(false);
		endGame.addActionListener(new EndGameButtonListener());
	}
	//ComboBoxListener used to determine the game displayed
	private class ComboBoxListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			grid.remove(startImage);
			newGame.setEnabled(true);
			endGame.setEnabled(true);
			if(event.getSource()==difficultySelect){
				String selected = difficultySelect.getSelectedItem().toString();

				// 5 X 5 game selected
				if(selected=="easy(5X5)"){
					numCorrect=0;
					numClickedCorrect=0;
					score=0;
					scoreLabel.setText(Integer.toString(score));
					Random random = new Random();
					controlValue = random.nextInt(5);
					difficultySelect.setEnabled(false);
					selection = 3;
					timerCount=5;
					roundCount++;

					ImageIcon targetIcon = new ImageIcon(imageLocation[controlValue]);
					target.setIcon(targetIcon);
					target.setHorizontalAlignment(SwingConstants.CENTER);
					main.add(grid, BorderLayout.CENTER);
					grid.setLayout(new GridLayout(5,5));
					for(int i = 0; i < 25; i++){
						Random r = new Random();
						buttonCheck[i] = r.nextInt(5);
						ImageIcon icon = new ImageIcon(imageLocation[buttonCheck[i]]);
						buttons[i] = new JButton(icon); //create button & add to array
						buttons[i].addActionListener(new ButtonListener());; //add an action listener to the current button
						grid.add(buttons[i]); //add that same button to the panel]
						if(buttonCheck[i]==controlValue)
							numCorrect++;
					}
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					grid.setOpaque(true);
					timer = new Timer(1000,new TimerListener());
					timer.start();

					// 6 X 6 game selected
				}else if(selected=="medium(6X6)"){
					numCorrect=0;
					numClickedCorrect=0;
					score=0;
					scoreLabel.setText(Integer.toString(score));
					Random random = new Random();
					controlValue = random.nextInt(5);
					difficultySelect.setEnabled(false);
					selection = 1;
					timerCount=5;
					roundCount++;
					ImageIcon targetIcon = new ImageIcon(imageLocation[controlValue]);
					target.setIcon(targetIcon);
					target.setHorizontalAlignment(SwingConstants.CENTER);
					main.add(grid, BorderLayout.CENTER);
					grid.setLayout(new GridLayout(6,6));//change size of grid 
					for(int i = 0; i < 36; i++){//change to number of buttons
						Random r = new Random();
						buttonCheck[i] = r.nextInt(5);
						ImageIcon icon = new ImageIcon(imageLocation[buttonCheck[i]]);
						buttons[i] = new JButton(icon); //create button & add to array
						buttons[i].addActionListener(new ButtonListener());; //add an action listener to the current button
						grid.add(buttons[i]); //add that same button to the panel
						if(buttonCheck[i]==controlValue)
							numCorrect++;
					}
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					grid.setOpaque(true);
					timer = new Timer(1000,new TimerListener());
					timer.start();

					// 7 X 7 game selected
				}else if(selected=="hard(7X7)"){
					numCorrect=0;
					numClickedCorrect=0;
					score=0;
					scoreLabel.setText(Integer.toString(score));
					Random random = new Random();
					controlValue = random.nextInt(5);
					difficultySelect.setEnabled(false);
					selection = 2;
					timerCount=5;
					roundCount++;
					ImageIcon targetIcon = new ImageIcon(imageLocation[controlValue]);
					target.setIcon(targetIcon);
					target.setHorizontalAlignment(SwingConstants.CENTER);
					main.add(grid, BorderLayout.CENTER);
					grid.setLayout(new GridLayout(7,7));
					for(int i = 0; i < 49; i++){
						Random r = new Random();
						buttonCheck[i] = r.nextInt(5);
						ImageIcon icon = new ImageIcon(imageLocation[buttonCheck[i]]);
						buttons[i] = new JButton(icon); //create button & add to array
						buttons[i].addActionListener(new ButtonListener());; //add an action listener to the current button
						grid.add(buttons[i]); //add that same button to the panel
						if(buttonCheck[i]==controlValue)
							numCorrect++;
					}
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					grid.setOpaque(true);
					timer = new Timer(1000,new TimerListener());
					timer.start();
				}
			}
		}

	}
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
			//disables the new game button after 10 games have been played
			//Bug found on this if statement
			if (roundCount ==10){
				newGame.setEnabled(false);
			}
			//Conditional statements to determine the action based on user selection
			
			if(event.getSource()==newGame){
				grid.removeAll();
				grid.revalidate();
				grid.repaint(); 
				timer.stop();
				timerLabel.setText("0");
				difficultySelect.setEnabled(true);
				difficultySelect.setSelectedIndex(0);
				controlValue=0;
				ImageIcon resetIcon = new ImageIcon(imageLocation[5]);
				target.setIcon(resetIcon);
				
			}
			
			
			
			//Button logic to verify if button clicked matches the target variable for button index 0-48
			int buttonPressed;
			for(buttonPressed=0; buttonPressed < buttons.length; buttonPressed++){
				if(event.getSource()==buttons[buttonPressed]){
					//disable the button after click
					buttons[buttonPressed].setEnabled(false);
					if(buttonCheck[buttonPressed]==controlValue){
						numClickedCorrect ++;
						possibleCorrect.setText(Integer.toString(numClickedCorrect));
						buttons[buttonPressed].setBackground(Color.green);
						score += 200;
						scoreLabel.setText(Integer.toString(score));
						buttonPressed++;
					}else buttons[buttonPressed].setBackground(Color.red);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
				}
			}
		}
	}
	//Timer Listener to put time restriction on game
	private class TimerListener implements ActionListener{
		public void actionPerformed	(ActionEvent e){
			if(0<= timerCount){
				timerLabel.setText("Time: "+Integer.toString(timerCount));
				timerCount--;
			}
			if(timerCount==-1){
				if(numClickedCorrect==numCorrect && timerCount==-1){
					score += 1000;
					scoreLabel.setText(Integer.toString(score)+" BONUS!");
					timer.stop();
				}
				grid.removeAll();
				grid.revalidate();
				grid.repaint();
				scoreSet[roundCount] = score;
				timerCount=-2;
				if(timerCount==-2){
					gameOverDialog = new JLabel("Game Over! Please click \"New Game\" or \"End Game\".");
					JOptionPane.showMessageDialog (null, gameOverDialog,"Game Over",JOptionPane.PLAIN_MESSAGE);
					timerCount--;
				}
			}
		}
	}
	//End Game button to close MatchGame and display score results
	private class EndGameButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			int highScore = 0;
			int bestGame = 0;
			String scoreOut = "";
			scoreOut = ("\n");
			for (int i=1; i<scoreSet.length; i++){
				if (scoreSet[i] > highScore){
					highScore = scoreSet[i];
					bestGame = i;
				}
				scoreOut = scoreOut +("\tRound: " + i + "\t\tScore: " + scoreSet[i]+ "\n");
			}
			scoreOut = scoreOut +("\t--------------------------------------------------\n");
			scoreOut = scoreOut +("\tBest Round: " + bestGame + "\t\tHighscore:" + highScore);
			Scorecard endScore = new Scorecard(scoreOut);	
			Window w = SwingUtilities.getWindowAncestor(MatchGame.this);
			w.setVisible(false);
		}

	}
}


