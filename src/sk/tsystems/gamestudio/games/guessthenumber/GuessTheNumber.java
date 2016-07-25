package sk.tsystems.gamestudio.games.guessthenumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import sk.tsystems.gamestudio.games.Game;

public class GuessTheNumber implements Game {
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private int maxNumber;
	private int guesses = 0;

	public GuessTheNumber() {

	}

	public int getScore() {

		return maxNumber + 5 - guesses;
	}

	public void play() {

		System.out.println("Insert max number");
		while (true) {
			try {
				maxNumber = Integer.parseInt(readLine());
				break;
			} catch (IllegalArgumentException e) {
				System.err.println("Not an number entered. Enter number:");
			}

		}
		int secretNumber;
		Random random = new Random();
		secretNumber = random.nextInt(maxNumber);
		int guess = 0;
		do {
			System.out.print("Enter a guess (0-" + maxNumber + "): ");
			while (true) {
				try {
					guess = Integer.parseInt(readLine());
					guesses++;
					break;
				} catch (IllegalArgumentException e) {
					System.err.println("Not an number entered. \nEnter a guess (0-" + maxNumber + "): ");
				}
			}
			if (guess == secretNumber)
				System.out.println("Your guess is correct. Congratulations! number of turns: " + guesses);
			else if (guess < secretNumber)
				System.out.println("Your guess is smaller than the secret number.");
			else if (guess > secretNumber)
				System.out.println("Your guess is greater than the secret number.");
		} while (guess != secretNumber);
	}

	// public static void main(String[] args) {
	//
	// }

	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}
}
