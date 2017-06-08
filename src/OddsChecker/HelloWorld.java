package OddsChecker;


public class HelloWorld
{
  
  public static void main(String[] args)
  {
    
    int fractionalOddsNumerator = 4;
		int fractionalOddsDeonominator = 5;
		double playerA = fract(fractionalOddsNumerator, fractionalOddsDeonominator);
		
  }
  public static double fract(int a, int b){
		double fractBeforeConv = ((double)a/(double)b);
		System.out.print("fractBeforeConv: "+fractBeforeConv);
		double fractAfterConv = fractBeforeConv+1; //This is our Decimal Odds
		System.out.print("fractAfterConv: "+fractAfterConv);
		return fractAfterConv;
	}
}