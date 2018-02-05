/*
 * Name        : rockpaper.java
 * Author      : Jisook Kim
 * Description : a simple Rock–paper–scissors game using GUI
 */


package rockpaper;


import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class rockpaper2 extends JFrame implements ActionListener {
	
	static final int ROCK = 0;
	static final int PAPER = 1;
	static final int SCISSOR = 2;
	
	private JPanel panel, resultPanel;
	private JTextField output, information, com_result;
	private JButton rock, paper, scissor;
	
	// create the frame for Rock–paper–scissors game
	public rockpaper2() {
		setTitle("Rock–paper–scissors!");
		setSize(400,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panel = new JPanel();
		resultPanel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		
		information = new JTextField("Click one of buttons.");
		output = new JTextField(15);
		com_result= new JTextField(15);
		
		rock = new JButton("ROCK");
		paper = new JButton("PAPER");
		scissor = new JButton("SCISSOR");
		
		// register buttons to event listener
		rock.addActionListener(this);
		paper.addActionListener(this);
		scissor.addActionListener(this);
		
		panel.add(rock);
		panel.add(paper);
		panel.add(scissor);
		
		resultPanel.add(output);
		resultPanel.add(com_result);
		
		add(information, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		add(resultPanel, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		rockpaper2 s =  new rockpaper2();
	}
	
	// figure out who's the winner
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		// generate random numbers
		int num = (int)(Math.random()*3);
		String[] arg = new String[]{"rock","scissor","paper"};
		
		String computer = arg[num];
		
		// to see computer's choice
		com_result.setText(" Computer: " + computer);
		
		if(source == rock && computer == "rock" || source == scissor && computer == "scissor" 
				|| source == paper && computer == "paper")
			output.setText("End in a tie!");
		else if (source == rock && computer == "scissor" || source == scissor && computer =="paper"
			|| source == paper && computer == "rock")
			output.setText("You beat the computer!");
		else
			output.setText("You lost the game...");	
	}
	
}




