/**
 * Authors: Katie Harris, Shelby Potts, and Sadie Roy
 * Go Fish - Project 4
 * May 30, 2014
 * PlayDemo.java: Implements the classes Card and Play
 */
import java.awt.Color;
import java.util.Scanner;

public class PlayDemo {
	public static final Color LIGHT_BLUE = new Color(0, 128, 255);
	private static int canvasHeight;
	private static int canvasWidth;

	public static void main(String[] args)
	{
		while(true)
		{
			boolean wantToPlay = false;

			Scanner keyboard = new Scanner(System.in);
			System.out.println("Welcome to Go Fish!");
			System.out.println("Would you like to play? Yes or No.");
			String answer = keyboard.nextLine();

			while(answer.equalsIgnoreCase("yes"))
			{
				wantToPlay = true;

				background(1050, 600);
				Play p = new Play();
				p.play();
			}

			if(answer.equalsIgnoreCase("no"))
			{
				wantToPlay = false;
				System.exit(0);
			}
		}
	}
	
	/**
	 * This method draws the background of the display window
	 * @param w = set width of the display
	 * @param h = set height of the display
	 */
	public static void background(int w, int h)
	{
		canvasHeight = h;
		canvasWidth = w;

		//dimensions of the canvas
		StdDraw.setCanvasSize(canvasWidth, canvasHeight);
		StdDraw.setXscale(0, canvasWidth);
		StdDraw.setYscale(canvasHeight, 0); // so we have (0, 0) in the top left

		// canvas color
		StdDraw.setPenColor(LIGHT_BLUE);
		StdDraw.filledRectangle(0, 0, canvasWidth, canvasHeight);

		// deck card
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledRectangle(100, 100, canvasWidth/ 17, canvasHeight/ 8);

		// bubbles
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledCircle(610, 20, 10);
		StdDraw.filledCircle(635, 0, 7);
		StdDraw.filledCircle(615, -10, 4);

		// text
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(515, 20, "Go Fish!");
		StdDraw.text(100, 100, "DECK");
	}

	/**
	 * This method redraws the background of the display window
	 */
	public static void redrawBackground()
	{
		// canvas color
		StdDraw.setPenColor(LIGHT_BLUE);
		StdDraw.filledRectangle(0, 0, canvasWidth, canvasHeight);

		// deck card
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledRectangle(100, 100, canvasWidth/ 17, canvasHeight/ 8);

		// bubbles
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledCircle(610, 20, 10);
		StdDraw.filledCircle(635, 0, 7);
		StdDraw.filledCircle(615, -10, 4);

		// text
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(515, 20, "Go Fish!");
		StdDraw.text(100, 100, "DECK");
	}
}
