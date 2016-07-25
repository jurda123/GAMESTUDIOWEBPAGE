package sk.tsystems.gamestudio.games.stones.consoleui;

import java.util.Scanner;

import sk.tsystems.gamestudio.games.stones.core.Field;

public class ConsoleUI {
	private Field field;

	private Scanner scanner;

	public ConsoleUI() {
		field = new Field(4, 4);
		if (field == null) {
			newField();
		}
		scanner = new Scanner(System.in);
	}

	public void run() {
		do {
			show();
			processInput();
		} while (!field.isSolved());
		System.out.println("You won the Game!");
	}

	public void show() {
		System.out.printf("Time: %03d s\n", field.getPlayingSeconds());
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				int value = field.getValueAt(row, column);
				if (value == Field.EMPTY_CELL) {
					System.out.printf("  ");
				} else {
					System.out.printf("%2d", value);
				}
				System.out.print("  ");
			}
			System.out.println();
		}
	}
	
	public  int getTime(){
		 return field.getPlayingSeconds();
	}

	private void processInput() {
		System.out.print("Enter input: ");
		String line = scanner.nextLine().toLowerCase().trim();

		try{
			int value = Integer.parseInt(line);
			field.move(value);
		}catch(NumberFormatException e) {}
		
		switch (line) {
		case "w":
		case "up":
			field.moveUp();
			break;
		case "a":
		case "left":
			field.moveLeft();
			break;
		case "s":
		case "down":
			field.moveDown();
			break;
		case "d":
		case "right":
			field.moveRight();
			break;
		case "x":
		case "exit":
			field.save();
			System.exit(0);
		case "n":
		case "new":
			newField();
		default:
			System.out.println("What's wrong kidda?");
			break;
		}
	}

	private void newField() {
		field = new Field(4, 4);
	}
}
