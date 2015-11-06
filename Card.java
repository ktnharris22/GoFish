/**
 * Authors: Katie Harris, Shelby Potts, and Sadie Roy
 * Go Fish - Project 4
 * May 30, 2014
 * Card.java: Creates a single card
 */

public class Card {

	private int value;
	private double xCoord = 50;
	private boolean paired = false;

	/**
	 * Constructor that initializes the instance variables
	 */
	public Card(int val)
	{
		value = val;
	}

	/**
	 * Method that determines if a card is face-up or face-down
	 */
	public void display()
	{
		System.out.print(value);
	}

	/**
	 * Method that determines if two cards are equal
	 */
	public boolean equals(Card otherCard)
	{
		return this.value == otherCard.value;
	}
	
	/**
	 * Method for displaying a single card when there are 7 or fewer cards on the top row
	 */
	public void displayCard(double x)
	{
		xCoord = x;
		// paired cards turn purple when they are displayed
		if (paired)
		{
			StdDraw.setPenColor(StdDraw.MAGENTA);
			StdDraw.filledRectangle(xCoord, 300, 1050/ 17, 600/ 8);
			
			// the value of the card is displayed in the corners of the card
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(xCoord - 40, 300 - 50, "" + value);
			StdDraw.text(xCoord + 40, 300 + 50, "" + value);
		}
		
		else
		{
			// cards that are not paired remain white
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledRectangle(xCoord, 300, 1050/ 17, 600/ 8);
			
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(xCoord - 40, 300 - 50, "" + value);
			StdDraw.text(xCoord + 40, 300 + 50, "" + value);
		}
	}
	
	/**
	 * Method for displaying cards on the second row when there are more than 7 in a hand
	 */
	public void displayExtras(double x)
	{
		xCoord = x;
		if (paired)
		{
			StdDraw.setPenColor(StdDraw.MAGENTA);
			StdDraw.filledRectangle(xCoord, 500, 1050/ 17, 600/ 8);
			
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(xCoord - 40, 500 - 50, "" + value);
			StdDraw.text(xCoord + 40, 500 + 50, "" + value);
		}
		else
		{
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledRectangle(xCoord, 500, 1050/ 17, 600/ 8);

			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(xCoord - 40, 500 - 50, "" + value);
			StdDraw.text(xCoord + 40, 500 + 50, "" + value);
		}
	}
	
	/**
	 * Gets the value of the card
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * Gets the x-coordinate of the cards to be drawn on the screen
	 */
	public double getX()
	{
		return xCoord;
	}
	
	/**
	 * Returns true if the values of two cards form a pair and returns false if the values do not match
	 */
	public boolean getPaired()
	{
		return paired;
	}
	
	/**
	 * Sets cards as paired
	 */
	public void setPaired(boolean p)
	{
		paired = p;
	}
}

