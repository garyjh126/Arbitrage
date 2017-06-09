package OddsChecker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class gettingData extends JFrame {
	
	private static int fractionalOddsNumerator = 1;
	private static int fractionalOddsDenominator = 1;
	public Match [] match = new Match[1000];
	public int countMatches = 0;
	static Scanner scan = new Scanner(System.in);
	private static JButton yesButton;
	private static JButton noButton;
	static GUI gui = new GUI();
	
	
	
	
	///Main Method////
	public static void main(String args[]){
		gettingData getData = new gettingData();
		
		getData.introDialog();
		
		
	}
	
	
	
	
	public void introDialog(){
		gettingData getData = new gettingData();
		reviewData review = new reviewData();
		JFrame frame = new JFrame();
		Object[] options = {"Input data for a new matchup", "Review data", "Analyse data and get the most profitable matchup so far"};
		int n = JOptionPane.showOptionDialog(frame, "", "What would you like to do?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	
		switch(n){
			case 0:
				getData.sendDataForMatch();
				break;
			case 1: 
				review.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				review.setSize(1920,1980);
				review.setLayout(new GridLayout(4, 1));
				review.setVisible(true);
				break;
			
			
		}
	}
	
	public  void sendDataForMatch(){
		
		GUI gui = new GUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(1920,1980);
		gui.setLayout(new GridLayout(4, 1));
		gui.setVisible(true);
		
		
	}
	

	
	public double fract(int a, int b){
		double fractBeforeConv = ((double)a/(double)b);
		double fractAfterConv = fractBeforeConv+1; //This is our Decimal Odds
		return fractAfterConv;
	}
	
	public double calculateTotalArbitrage(double oddsPlayerA, double oddsPlayerB){
		double percA = (1/oddsPlayerA)*100;
		double percB = (1/oddsPlayerB)*100;
		double totalArbitragePercentage = percA+percB;
		return totalArbitragePercentage;
	}
}



class Match {
	//Each Player must have an associated 'best odds'
	
	double [] bestOddsForPlayer = new double [2]; //In the case of tennis(2 Players)
	String nameA = "";
	String nameB = "";
	Match(String na,String nb, double bestOddsForPOne, double bestOddsForPTwo){
		bestOddsForPlayer[0] = bestOddsForPOne;
		bestOddsForPlayer[1] = bestOddsForPTwo;
		nameA = na;
		nameB = nb;
	}
}

class reviewData extends JFrame{
	public static double [] arbitrageCollection;
	public static String [] justToPrintArb;
	public static JButton button=new JButton("Finished");
	public String [] part = new String[6000]; 
	public gettingData getData = new gettingData();
	private static int [] arr = {0,1,2,3,4,5};
	
	public reviewData(){
		
		try{
			for(int i = 0; i<getData.match.length; i++){
				if(getData.match[i].bestOddsForPlayer[0]!=0){
					
					part[arr[0]+i] = ("Match "+i+": \n");
					part[arr[1]+i] = ("\tContestant "+i+": ");
					part[arr[2]+i] = (getData.match[i].nameA+"'s best odds were "+getData.match[i].bestOddsForPlayer[0]+"\n");
					
					part[arr[3]+i] = ("\tContestant "+i+": ");
					part[arr[4]+i] = (getData.match[i].nameB+"'s best odds were "+getData.match[i].bestOddsForPlayer[1]+"\n");
					part[arr[5]+i] = ("*****************\n");
					
					arbitrageCollection[i] = getData.calculateTotalArbitrage(getData.match[i].bestOddsForPlayer[0], getData.match[i].bestOddsForPlayer[1]);
					
					justToPrintArb[i]=("Total Arbitrage: "+arbitrageCollection[i]);
					for(int j = 0; j<arr.length;j++){
						arr[j] += 5;
					}
				}
			}
			getData.introDialog();
		}
		catch (NullPointerException e) { 
	        // This block is to catch divide-by-zero error
			JOptionPane.showMessageDialog(null,"Error: Null pointer exception");
		}
		catch(ArrayIndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(null,"Warning: ArrayIndexOutOfBoundsException");
	     }
		

		for(int e = 0; e<arr.length;e++){
			arr[e] = e;
		}
		
		for(int gh = 0; gh<part.length; gh++){
			if(gh%6==0){
				JLabel [] contentsInSixes = new JLabel[6];
				contentsInSixes=createLabels(gh);
				printSix(contentsInSixes);
				
			}
		}
		
		
		
	}
	private JLabel[] createLabels(int startIndex){
        JLabel[] labels=new JLabel[6];
        for (int i=0;i<labels.length;i++){
            labels[i]=new JLabel(part[startIndex]);
            startIndex++;
        }
        return labels;
	}
	private void printSix(JLabel[] lab){
		for(int l = 0; l<lab.length;l++){
			add(lab[l]);
		}
	}
	
}

class GUI extends JFrame{
	
	private JTextField item1;
	private JTextField item2;
	private JTextField item3;
	private JTextField item4;
	private JTextField item3Denom;
	private JTextField item4Denom;
	private JPanel firstPanel;
	private JPanel secondPanel;
	private JPanel thirdPanel;
	private JPanel fourthPanel;
	public static JButton button=new JButton("Submit");
	public static int x=9;
	public gettingData getData = new gettingData();
	
	public GUI(){
		
		firstPanel = new JPanel();
		firstPanel.setBackground(Color.red);
		secondPanel= new JPanel();
		secondPanel.setBackground(Color.blue);
		thirdPanel  = new JPanel();
		thirdPanel.setBackground(Color.BLACK);
		fourthPanel = new JPanel();
		fourthPanel.setBackground(Color.yellow);
	
		
		
		//super("The Title");
		setLayout(new FlowLayout());
		
		item1 = new JTextField(30);
		TextPrompt tp1 = new TextPrompt("First Name", item1);
		tp1.changeAlpha(1.5f);
		add(item1);
		
		item2 = new JTextField(30);
		TextPrompt tp2 = new TextPrompt("Second Name", item2);
		tp2.changeAlpha(1.5f);
		add(item2);
		
		item3 = new JTextField(30);
		item3Denom = new JTextField(30);
		TextPrompt tp3 = new TextPrompt("PLAYER 1's best odds (Numerator):", item3);
		tp3.changeAlpha(1.5f);
		TextPrompt tp4 = new TextPrompt("PLAYER 1's best odds (Denominator):", item3Denom);
		tp4.changeAlpha(1.5f);
		add(item3);
		add(item3Denom);
		
		item4 = new JTextField(20);
		item4Denom = new JTextField(20);
		TextPrompt tp5 = new TextPrompt("PLAYER 2's best odds (Numerator):", item4);
		tp5.changeAlpha(1.5f);
		TextPrompt tp6 = new TextPrompt("PLAYER 2's best odds (Denominator):", item4Denom);
		tp6.changeAlpha(1.5f);
		add(item4);
		add(item4Denom);
		
	    item1.setPreferredSize( new Dimension( 250, 60 ) );
	    item2.setPreferredSize( new Dimension( 250, 60 ) );
	    item3.setPreferredSize( new Dimension( 250, 60 ) );
	    item4.setPreferredSize( new Dimension( 250, 60 ) );
	    item3Denom.setPreferredSize( new Dimension( 250, 60 ) );
	    item4Denom.setPreferredSize( new Dimension( 250, 60 ) );
	    
	    firstPanel.add(item1);
	    secondPanel.add(item2);
	    thirdPanel.add(item3);
	    thirdPanel.add(item3Denom);
	    fourthPanel.add(item4);
	    fourthPanel.add(item4Denom);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
	      
		fourthPanel.add(button);
		add(firstPanel);
		add(secondPanel);
		add(thirdPanel);
		add(fourthPanel);
		
		thehandler handler = new thehandler();
		button.addActionListener(handler);
		
	}
	
	private class thehandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
			String personA = item1.getText();
			String personB = item2.getText();
			
			int [] personAOdds = new int[2];
			int [] personBOdds = new int[2];
			
			personAOdds[0] = Integer.parseInt(item3.getText()); 
			personAOdds[1] = Integer.parseInt(item3Denom.getText()); 
			personBOdds[0] = Integer.parseInt(item4.getText()); 
			personBOdds[1] = Integer.parseInt(item4Denom.getText()); 
			
			double playerA = getData.fract(personAOdds[0],personAOdds[1]);
			double playerB = getData.fract(personBOdds[0],personBOdds[1]);
			getData.match[getData.countMatches] = new Match(personA,personB,playerA,playerB);
			getData.countMatches++;
			
			int ans = JOptionPane.showConfirmDialog(null,"Do you want to create another matchup?");
			if(ans==0){//yes
				JOptionPane.showMessageDialog(null,"Matchup Saved\n");
				getData.sendDataForMatch();
			}
			else {
				getData.introDialog();
			}
		}
	}
}

