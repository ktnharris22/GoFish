/**
 * Authors: Katie Harris, Shelby Potts, and Sadie Roy
 * Go Fish - Project 4
 * May 30, 2014
 * Play.java: Runs the interactive game simulation
 */

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Play {

	private int guess = 0;
	private boolean win = false;
	private int turn = 1;
	private int compTurn = 1;
	private boolean guessCorrect = false;
	private boolean compGuessCorrect = false;
	private boolean inHand;
	private boolean guessNumber=true;

	ArrayList<Card> playerHand = new ArrayList<Card>();
	ArrayList<Card> compHand = new ArrayList<Card>();
	ArrayList<Card> deck = new ArrayList<Card>();

	Scanner keyboard = new Scanner(System.in);

	/**
	 * Constructor that prepares the start of the game.
	 * It creates and shuffles the deck.
	 * It then deals both the computer's hand and the player's hand, displays the player's hand, and then removes any pairs 
	 * in both the player's hand and the computer's hand
	 */
	public Play() 
	{
		createDeck();
		shuffle();
		deal();
		displayHand();
		removePairs();
	}

	/**
	 * Method that allows the player and the computer to take turns throughout the game
	 * until there is a winner
	 */
	public void play() 
	{
		while (!win) 
		{
			// The first turn or the guess is true
			while (guessCorrect == true || turn == 1)
			{
				playerTurn();
				turn++;
			}
			
			// The first turn or the guess is true
			while (compGuessCorrect == true || compTurn == 1) 
			{
				compTurn();
				compTurn++;
			}
		}
	}

	/**
	 * Method creates a deck with 52 cards, 4 cards per suit and value
	 */
	private void createDeck() 
	{
		for (int a = 0; a < 13; a++) 
		{
			Card c = new Card(a);
			for (int b = 0; b < 4; b++) 
			{
				deck.add(c);
			}
		}
	}

	/**
	 * Method that shuffles the deck of cards into a random order
	 */
	private void shuffle() 
	{
		for (int j = 0; j < 4; j++) 
		{
			for (int k = 0; k < 26; k++) 
			{
				int r = (int) (Math.random() * 52);
				Card c2 = deck.get(k);
				Card c3 = deck.get(r);
				deck.set(r, c2);
				deck.set(k, c3);
			}
		}
	}

	/**
	 * Method that removes 14 cards from the deck and deals 7 to the computer
	 * and the player
	 */
	private void deal() 
	{
		for (int v = 0; v < 14; v++) 
		{
			Card c3 = deck.get(v);
			if (v % 2 == 0) 
			{
				playerHand.add(c3);
			} 
			else 
			{
				compHand.add(c3);
			}
			deck.remove(v);
		}
	}

	/**
	 * Method that displays the player's hand on the screen
	 */
	public void displayHand() 
	{
		for (int c = 0; c < playerHand.size(); c++) 
		{
			if (playerHand.size() <= 7) 
			{
				playerHand.get(c).displayCard((c * 150) + 50);
			}
			if (playerHand.size() > 7) 
			{
				for (int d = 0; d < playerHand.size(); d++) 
				{
					if (d <= 6) 
					{
						playerHand.get(d).displayCard((d * 150) + 50);
					}
					// The displayExtras method is used for more than 7 cards so
					// they display underneath the first row of 7 cards in the player's hand
					if (d > 6) 
					{
						playerHand.get(d).displayExtras(((d - 7) * 150) + 50);
					}
				}
			}
		}
		
	}

	/**
	 * Method that puts down (takes out) any pairs in the player or computer's
	 * hand
	 */
	private void removePairs() 
	{
		for (int i = 0; i < playerHand.size(); i++) 
		{
			for (int j = i + 1; j < playerHand.size(); j++) 
			{
				// If cards are paired they are dropped from the hand to
				// simulate putting them down
				if (playerHand.get(i).getValue() == playerHand.get(j).getValue() && (j != i)) 
				{
					Card c1 = playerHand.get(i);
					Card c2 = playerHand.get(j);
					c1.setPaired(true);
					c2.setPaired(true);

					displayHand();
					System.out.println("Pair(s) found!");
					c1.setPaired(false);
					c2.setPaired(false);
					playerHand.remove(c1);
					playerHand.remove(c2);
					System.out.println("Hit 'enter' to remove the pair(s).");
					keyboard.nextLine();
				}
			}
		}
		
		PlayDemo.redrawBackground();
		displayHand();

		for (int i = 0; i < compHand.size(); i++) 
		{
			for (int j = i+1; j < compHand.size(); j++) 
			{
				if (compHand.get(i).getValue() == compHand.get(j).getValue()) 
				{
					Card c3 = compHand.get(i);
					Card c4 = compHand.get(j);
					compHand.remove(c3);
					compHand.remove(c4);
				}
			}
		}

		if (playerHand.isEmpty()) 
		{
			System.out.println("Congratulations! You Win!");
			win = true;
			System.exit(0);
		}

		if (compHand.isEmpty()) 
		{
			System.out.println("You Lose! The computer won. Sorry!");
			win = true;
			System.exit(0);
		}
	}

	/**
	 * Method that checks to make sure the player's guess is one of the cards in their hand
	 */
	private void setInHand() 
	{
		inHand = false;
		for (int j = 0; j < playerHand.size(); j++) 
		{

			if (guess == playerHand.get(j).getValue()) 
			{
				inHand = true;
			}
		}
	}
	
	
	/**
	 * Method that checks to make sure the guess entered by the player is an integer
	 */
	private void guessNumber()
	{
		try
		{
			System.out.println("Pick the number of one card in your deck to ask for from the computer (0 - 13).");
			guess=keyboard.nextInt();
			guessNumber=true;
		}
		catch(InputMismatchException exception)
		{
			System.out.println("This is not an integer. Please only use digits (0-13).");
			keyboard.nextLine();
			guessNumber=false;
		}
	}
	
	
	/**
	 * Method that allows the player to take a turn asking the computer for a
	 * card. If the player's guess is correct, the computer gives the player the
	 * card he/she asked for, the pairs are removed, and the player guesses again. 
	 * If the guess is wrong, the player is told to "Go Fish!" and draws a card from the deck.
	 */
	private void playerTurn() 
	{
		// If the player has put all of his/her pairs down, he/she has won the game
		if (playerHand.isEmpty() == true) 
		{
			win = true;
			System.out.println("Congratulations! You WIN!");
			System.exit(0);
		}
		
		guessNumber();
		while(guessNumber==false)
		{
			guessNumber();
		}
		
		keyboard.nextLine();
		setInHand();

		while (!inHand) 
		{
			System.out.println("The card you guessed is not in your hand. Please try again.");
			guessNumber();
			while(guessNumber==false)
			{
				guessNumber();
			}
			keyboard.nextLine();
			setInHand();
		}
		
		if (inHand) 
		{
			boolean inCompHand = false;
			for (int i = 0; i < compHand.size(); i++) 
			{
				/*
				 * If the computer has the card that is being asked for, take it
				 * from its hand and add it to the players hand.
				 */
				if (compHand.get(i).getValue() == guess) 
				{
					inCompHand = true;
					Card c1 = compHand.get(i);
					compHand.remove(c1);
					System.out.println("Comp says: You stole a card from me.");
					playerHand.add(c1);
					displayHand();
					// findPairs();
					removePairs();
					displayHand();
					guessCorrect = true;
					break;
				}
			}

			// If the computer does not have the card that was asked for,
			// the player draws another card
			if (!inCompHand) 
			{
				System.out.println("Go Fish!");
				playerHand.add(deck.get(deck.size() - 1));
				deck.remove(deck.size() - 1);
				PlayDemo.redrawBackground();
				displayHand();
				System.out.println("Please examine your hand (a card was added). When ready, hit 'enter' to continue.");
				keyboard.nextLine();
				removePairs();
				PlayDemo.redrawBackground();
				displayHand();
				guessCorrect = false;
				compTurn = 1;
				// The turn is over
			}
		}
	}

	/**
	 * Method that allows the computer to take a turn asking the player for a
	 * card. If the computer's guess is correct, the player gives the computer
	 * the card it asked for, removes any pairs, and guesses again. If the guess is wrong, the
	 * computer is told to "Go Fish!" and draws another card from the deck.
	 */
	private void compTurn() 
	{
		// If the computer has gotten rid of all of its cards, it is the winner
		if (compHand.isEmpty() == true) 
		{
			win = true;
			System.out.println("You Lose! The computer won. Sorry!");
			System.exit(0);
		}

		int r = (int) (Math.random() * compHand.size());
		Card compGuess = compHand.get(r);

		for (int i = 0; i < playerHand.size(); i++) 
		{
			// Computer guesses one of the player's cards correctly
			if (compGuess.getValue() == playerHand.get(i).getValue()) 
			{
				// It takes the card and places it in its hand
				System.out.println("Computer guessed Correctly ( "+ compGuess.getValue() + " )");
				compHand.add(playerHand.get(i));
				playerHand.remove(i);
				System.out.println("Card will be  removed. Hit 'enter' to continue.");
				keyboard.nextLine();
				PlayDemo.redrawBackground();
				displayHand();
				compGuessCorrect = true;
			}

			// If the computer guesses incorrectly it must add a card to its
			// hand from the deck
			else if ((i == playerHand.size() - 1) && compGuess.getValue() != playerHand.get(i).getValue()) 
			{
				System.out.println("Computer had to go fish.");
				compGuessCorrect = false;
				Card newCard = deck.get(deck.size() - 1);
				compHand.add(newCard);
				deck.remove(deck.size() - 1);
				turn = 1;
			}
		}
	}
}
