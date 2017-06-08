package OddsChecker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class gettingData extends JFrame {
	static Match [] match = new Match[1000];
	static int countMatches = 0;
	static Scanner scan = new Scanner(System.in);
	private static JButton yesButton;
	private static JButton noButton;
	
	
	public static void main(String args[]){
		gettingData getData = new gettingData();
		
		System.out.print("Would you like to do?\n");
		System.out.print("(1) - Input data for a new matchup\n");
		System.out.print("(2) - Review data\n");
		System.out.print("(3) - Analyse data and get the most profitable matchup so far\n");
		int choice = scan.nextInt();
		switch(choice){
			case 1:
				getData.sendDataForMatch();
				break;
			case 2: 
				printOffPreviousMatchups();
				break;
			
			
		}
		
		
	}
	public static void printOffPreviousMatchups(){
		gettingData getData = new gettingData();
		try{
			for(int i = 0; i<match.length; i++){
				if(match[i].bestOddsForPlayer[0]!=0){
					System.out.print("Match "+i+": \n");
					System.out.print("\tContestant "+i+": ");
					System.out.print(match[i].nameA+"'s best odds were "+match[i].bestOddsForPlayer[0]+"\n");
					
					System.out.print("\tContestant "+i+": ");
					System.out.print(match[i].nameB+"'s best odds were "+match[i].bestOddsForPlayer[1]+"\n");
					System.out.print("*****************\n");
					
					double tot = calculateTotalArbitrage(match[i].bestOddsForPlayer[0], match[i].bestOddsForPlayer[1]);
					System.out.println("Total Arbitrage: "+tot);
				}
			}
			
			System.out.print("Would you like to do?\n");
			System.out.print("(1) - Restart and input completely new data for a new matchup\n");
			System.out.print("(2) - Review data\n");
			System.out.print("(3) - Analyse data and get the most profitable matchup so far\n");
			int choice = scan.nextInt();
			switch(choice){
				case 1:
					getData.sendDataForMatch();
					break;
				case 2: 
					printOffPreviousMatchups();
					break;
			}
			
		} catch (NullPointerException e) { 
	        // This block is to catch divide-by-zero error
	        System.out.println("Error: Null pointer exception");
		}
		catch(ArrayIndexOutOfBoundsException e){
	        System.out.println("Warning: ArrayIndexOutOfBoundsException");
	     }
	}
	public  void sendDataForMatch(){
		

		System.out.print("Enter the First Player's Name: ");
		String a = "";
		String b = "";
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		
		try {
            a = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
		if(a.length() == 0){
            System.out.println("Exiting...");
            System.exit(0);
        }
		
		
		System.out.print("Enter the Second Player's Name: ");
		
		try {
            b = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
		if(b.length() == 0){
            System.out.println("Exiting...");
            System.exit(0);
        }
			
		
		
	
		System.out.print("PLAYER 1's best odds (Numerator First then Denominator):");
		int fractionalOddsNumerator = scan.nextInt();
		int fractionalOddsDeonominator = scan.nextInt();
		double playerA = fract(fractionalOddsNumerator, fractionalOddsDeonominator);
		
		System.out.print("PLAYER 2's best odds (Numerator First then Denominator):");
		fractionalOddsNumerator = scan.nextInt();
		fractionalOddsDeonominator = scan.nextInt();
		double playerB = fract(fractionalOddsNumerator, fractionalOddsDeonominator);
		
		match[countMatches] = new Match(a,b,playerA,playerB);
		countMatches++;
		
		JFrame frame = new JFrame("Frame");
		
		
		int ans = JOptionPane.showConfirmDialog(null,"Do you want to create another matchup?");
		
		
		
		if(ans==0){//yes
			for(int clear = 0; clear < 20; clear++) {
			    System.out.println("\b") ;
			}
			System.out.print("Matchup Saved\n");
			sendDataForMatch();
		}
		else {
			System.out.print("Would you like to do?\n");
			System.out.print("(1) - Input data for a new matchup\n");
			System.out.print("(2) - Review data\n");
			System.out.print("(3) - Analyse data and get the most profitable matchup so far\n");
			int choice = scan.nextInt();
			switch(choice){
				case 1:
					sendDataForMatch();
					break;
				case 2: 
					printOffPreviousMatchups();
					break;
			}
		}
		
	}
	

	
	public static double fract(int a, int b){
		double fractBeforeConv = ((double)a/(double)b);
		double fractAfterConv = fractBeforeConv+1; //This is our Decimal Odds
		return fractAfterConv;
	}
	
	public static double calculateTotalArbitrage(double oddsPlayerA, double oddsPlayerB){
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
