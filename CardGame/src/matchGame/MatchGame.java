      /**Class: Programming Fundamentals III

        *Project #  : Group Project

        *Students:    Jackson Martinez

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
	//timer set to Private
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
	private int gameCount=0;
	
	//arrays
	String [] difficulty = {"Please select difficulty","easy(5X5)","medium(6X6)","hard(7X7)"};
	JButton [] buttons = new JButton[49];
	int [] buttonCheck = new int[49];
	String[] imageLocation = {"1.png","2.png","3.png","4.png","5.png","6.png"};
	int[] scoreSet = new int[11];

	//constructor
	public MatchGame(){
		difficultySelect = new JComboBox(difficulty);
		difficultySelect.setPreferredSize(new Dimension(300,50));
		
		main = new JPanel();
		main.setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		main.setPreferredSize (new Dimension(dim.width-100, dim.height-100));

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
					gameCount++;
					
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
					gameCount++;
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
					gameCount++;
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
				//disables the new game button after 10 games have been played
				if (gameCount >10){
					newGame.setEnabled(false);
				}
			}
			//Button logic to verify if button clicked matches the target variable for button index 0-48
			if(event.getSource()==buttons[0]){
				//disable the button after click
				buttons[0].setEnabled(false);
				if(buttonCheck[0]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[0].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[0].setBackground(Color.red);
					buttons[0].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[1]){
				//disable the button after click
				buttons[1].setEnabled(false);
				if(buttonCheck[1]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[1].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[1].setBackground(Color.red);
					buttons[1].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[2]){
				//disable the button after click
				buttons[2].setEnabled(false);
				if(buttonCheck[2]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[2].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[2].setBackground(Color.red);
					buttons[2].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[3]){
				//disable the button after click
				buttons[3].setEnabled(false);
				if(buttonCheck[3]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[3].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[3].setBackground(Color.red);
					buttons[3].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[4]){
				//disable the button after click
				buttons[4].setEnabled(false);
				if(buttonCheck[4]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[4].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[4].setBackground(Color.red);
					buttons[4].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[5]){
				//disable the button after click
				buttons[5].setEnabled(false);
				if(buttonCheck[5]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[5].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[5].setBackground(Color.red);
					buttons[5].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[6]){
				//disable the button after click
				buttons[6].setEnabled(false);
				if(buttonCheck[6]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[6].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[6].setBackground(Color.red);
					buttons[6].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[7]){
				//disable the button after click
				buttons[7].setEnabled(false);
				if(buttonCheck[7]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[7].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[7].setBackground(Color.red);
					buttons[7].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[8]){
				//disable the button after click
				buttons[8].setEnabled(false);
				if(buttonCheck[8]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[8].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[8].setBackground(Color.red);
				buttons[8].setOpaque(true);
				score -= 100;
				scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[9]){
				//disable the button after click
				buttons[9].setEnabled(false);
				if(buttonCheck[9]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[9].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[9].setBackground(Color.red);
					buttons[9].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[10]){
				//disable the button after click
				buttons[10].setEnabled(false);
				if(buttonCheck[10]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[10].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[10].setBackground(Color.red);
					buttons[10].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[11]){
				//disable the button after click
				buttons[11].setEnabled(false);
				if(buttonCheck[11]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[11].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[11].setBackground(Color.red);
					buttons[11].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[12]){
				//disable the button after click
				buttons[12].setEnabled(false);
				if(buttonCheck[12]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[12].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[12].setBackground(Color.red);
					buttons[12].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[13]){
				//disable the button after click
				buttons[13].setEnabled(false);
				if(buttonCheck[13]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[13].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[13].setBackground(Color.red);
					buttons[13].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[14]){
				//disable the button after click
				buttons[14].setEnabled(false);
				if(buttonCheck[14]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[14].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[14].setBackground(Color.red);
					buttons[14].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[15]){
				//disable the button after click
				buttons[15].setEnabled(false);
				if(buttonCheck[15]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[15].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[15].setBackground(Color.red);
					buttons[15].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[16]){
				//disable the button after click
				buttons[16].setEnabled(false);
				if(buttonCheck[16]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[16].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[16].setBackground(Color.red);
					buttons[16].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[17]){
				//disable the button after click
				buttons[17].setEnabled(false);
				if(buttonCheck[17]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[17].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[17].setBackground(Color.red);
					buttons[17].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[18]){
				//disable the button after click
				buttons[18].setEnabled(false);
				if(buttonCheck[18]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[18].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[18].setBackground(Color.red);
					buttons[18].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[19]){
				//disable the button after click
				buttons[19].setEnabled(false);
				if(buttonCheck[19]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[19].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[19].setBackground(Color.red);
					buttons[19].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[20]){
				//disable the button after click
				buttons[20].setEnabled(false);
				if(buttonCheck[20]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[20].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[20].setBackground(Color.red);
					buttons[20].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[21]){
				//disable the button after click
				buttons[21].setEnabled(false);
				if(buttonCheck[21]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[21].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[21].setBackground(Color.red);
					buttons[21].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[22]){
				//disable the button after click
				buttons[22].setEnabled(false);
				if(buttonCheck[22]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[22].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[22].setBackground(Color.red);
					buttons[22].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[23]){
				//disable the button after click
				buttons[23].setEnabled(false);
				if(buttonCheck[23]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[23].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[23].setBackground(Color.red);
					buttons[23].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[24]){
				//disable the button after click
				buttons[24].setEnabled(false);
				if(buttonCheck[24]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[24].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}
				else buttons[24].setBackground(Color.red);
					buttons[24].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[25]){
				//disable the button after click
				buttons[25].setEnabled(false);
				if(buttonCheck[25]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[25].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[25].setBackground(Color.red);
					buttons[25].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}if(event.getSource()==buttons[26]){
				//disable the button after click
				buttons[26].setEnabled(false);
				if(buttonCheck[26]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[26].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[26].setBackground(Color.red);
					buttons[26].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[27]){
				//disable the button after click
				buttons[27].setEnabled(false);
				if(buttonCheck[27]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[27].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[27].setBackground(Color.red);
					buttons[27].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[28]){
				//disable the button after click
				buttons[28].setEnabled(false);
				if(buttonCheck[28]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[28].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[28].setBackground(Color.red);
					buttons[28].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[29]){
				//disable the button after click
				buttons[29].setEnabled(false);
				if(buttonCheck[29]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[29].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[29].setBackground(Color.red);
					buttons[29].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[30]){
				//disable the button after click
				buttons[30].setEnabled(false);
				if(buttonCheck[30]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[30].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[30].setBackground(Color.red);
					buttons[30].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[31]){
				//disable the button after click
				buttons[31].setEnabled(false);
				if(buttonCheck[31]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[31].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[31].setBackground(Color.red);
					buttons[31].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[32]){
				//disable the button after click
				buttons[32].setEnabled(false);
				if(buttonCheck[32]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[32].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[32].setBackground(Color.red);
					buttons[32].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[33]){
				//disable the button after click
				buttons[33].setEnabled(false);
				if(buttonCheck[33]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[33].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[33].setBackground(Color.red);
					buttons[33].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[34]){
				//disable the button after click
				buttons[34].setEnabled(false);
				if(buttonCheck[34]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[34].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[34].setBackground(Color.red);
					buttons[34].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[35]){
				//disable the button after click
				buttons[35].setEnabled(false);
				if(buttonCheck[35]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[35].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[35].setBackground(Color.red);
					buttons[35].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[36]){
				//disable the button after click
				buttons[36].setEnabled(false);
				if(buttonCheck[36]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[36].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[36].setBackground(Color.red);
					buttons[36].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[37]){
				//disable the button after click
				buttons[37].setEnabled(false);
				if(buttonCheck[37]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[37].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[37].setBackground(Color.red);
					buttons[37].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[38]){
				//disable the button after click
				buttons[38].setEnabled(false);
				if(buttonCheck[38]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[38].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[38].setBackground(Color.red);
					buttons[38].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[39]){
				//disable the button after click
				buttons[39].setEnabled(false);
				if(buttonCheck[39]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[39].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[39].setBackground(Color.red);
					buttons[39].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[40]){
				//disable the button after click
				buttons[40].setEnabled(false);
				if(buttonCheck[40]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[40].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[40].setBackground(Color.red);
					buttons[40].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[41]){
				//disable the button after click
				buttons[41].setEnabled(false);
				if(buttonCheck[41]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[41].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[41].setBackground(Color.red);
					buttons[41].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[42]){
				//disable the button after click
				buttons[42].setEnabled(false);
				if(buttonCheck[42]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[42].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[42].setBackground(Color.red);
					buttons[42].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[43]){
				//disable the button after click
				buttons[43].setEnabled(false);
				if(buttonCheck[43]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[43].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[43].setBackground(Color.red);
					buttons[43].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[44]){
				//disable the button after click
				buttons[44].setEnabled(false);
				if(buttonCheck[44]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[44].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[44].setBackground(Color.red);
					buttons[44].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[45]){
				//disable the button after click
				buttons[45].setEnabled(false);
				if(buttonCheck[45]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[45].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[45].setBackground(Color.red);
					buttons[45].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[46]){
				//disable the button after click
				buttons[46].setEnabled(false);
				if(buttonCheck[46]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[46].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[46].setBackground(Color.red);
					buttons[46].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[47]){
				//disable the button after click
				buttons[47].setEnabled(false);
				if(buttonCheck[47]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[47].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[47].setBackground(Color.red);
					buttons[47].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
			}
			if(event.getSource()==buttons[48]){
				//disable the button after click
				buttons[48].setEnabled(false);
				if(buttonCheck[48]==controlValue){
					numClickedCorrect ++;
					possibleCorrect.setText(Integer.toString(numClickedCorrect));
					buttons[48].setBackground(Color.green);
					score += 200;
					scoreLabel.setText(Integer.toString(score));
				}else buttons[48].setBackground(Color.red);
					buttons[48].setOpaque(true);
					score -= 100;
					scoreLabel.setText(Integer.toString(score));
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
				scoreSet[gameCount] = score;
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
					scoreOut = scoreOut +("\tGame: " + i + "\t\tScore: " + scoreSet[i]+ "\n");
				}
				scoreOut = scoreOut +("\t--------------------------------------------------\n");
				scoreOut = scoreOut +("\tBest Game: " + bestGame + "\t\tHighscore:" + highScore);
				Scorecard endScore = new Scorecard(scoreOut);	
			    Window w = SwingUtilities.getWindowAncestor(MatchGame.this);
				w.setVisible(false);
		}
	}
}
