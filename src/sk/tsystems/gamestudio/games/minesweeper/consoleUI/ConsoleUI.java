package sk.tsystems.gamestudio.games.minesweeper.consoleUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.tsystems.gamestudio.games.minesweeper.BestTimes;
import sk.tsystems.gamestudio.games.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.games.minesweeper.UserInterface;
import sk.tsystems.gamestudio.games.minesweeper.core.Clue;
import sk.tsystems.gamestudio.games.minesweeper.core.Field;
import sk.tsystems.gamestudio.games.minesweeper.core.GameState;
import sk.tsystems.gamestudio.games.minesweeper.core.Mine;
import sk.tsystems.gamestudio.games.minesweeper.core.Tile;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(
			System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public void newGameStarted(Field field) {
		this.field = field;

		do {
			if (field.getState() == GameState.SOLVED) {
				System.out.println("Congratulations, you win!");
				int score= field.getColumnCount()*field.getRowCount()*field.getMineCount() - Minesweeper.getInstance().getPlayingSeconds();
				Minesweeper.getInstance().setScore(score);
				System.exit(0);

			}
			if (field.getState() == GameState.FAILED) {
				System.out.println("Game over!");
				System.exit(0);
			}
			update();
			//printBestTimes();
			processInput();
			

		} while (true);
	}
	
	public void printBestTimes(){
		BestTimes bestTimes = new BestTimes();
//		bestTimes.addPlayerTime("bac", 123);
//		bestTimes.addPlayerTime("asd", 1234);
//		bestTimes.addPlayerTime("as", 12);
		String s= bestTimes.toString();
		System.out.print(s);
		
	}
	private String parseTime(){
		Minesweeper minesweeper = Minesweeper.getInstance();
		int time = minesweeper.getPlayingSeconds();
		if (time<60){
			return Integer.toString(time);
		}
		else{
			Formatter f = new Formatter();
			int mins = 0;
			int seconds = 0;
			mins = time/60;
			seconds= time%60;
			if(seconds<10){
				f.format(mins + ":0" + seconds);
				return f.toString();
			}
			else{
			f.format(mins + ":" + seconds);
			return f.toString();
			}
		}
	} 
	@Override
	public void update() {
		System.out.println("Remaining mines: " + field.getRemainingMineCount());
		System.out.println("Time: " + parseTime());

		System.out.print("  ");
		for (int i = 0; i < field.getColumnCount(); i++) {
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.println();
		Tile tile;

		for (int i = 0; i < field.getRowCount(); i++) {

			int znak = i + 65;
			char a = ((char) znak);
			System.out.print(a + " ");
			for (int j = 0; j < field.getColumnCount(); j++) {

				tile = field.getTile(i, j);
				if (tile.getState() == Tile.State.CLOSED) {
					System.out.print("-");
					System.out.print(" ");
				} else if (tile.getState() == Tile.State.MARKED) {
					System.out.print("M");
					System.out.print(" ");
				}

				else if (tile.getState() == Tile.State.OPEN) {

					if (tile instanceof Mine) {
						System.out.print("X");
						System.out.print(" ");
					} else if (tile instanceof Clue) {
						System.out.print(((Clue) tile).getValue());
						System.out.print(" ");
					}

				}

			}
			System.out.println();
		}

	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {
		
		System.out
				.println("X � ukon�enie hry, MA1 � ozna�enie dla�dice v riadku A "
						+ "a st�pci 1, OB4 � odkrytie dla�dice v riadku B a st�pci 4");
		String input = readLine();
		if (input.equalsIgnoreCase("x")) {
			System.out.print("GAME OVER");
			System.exit(1);
		}
		try{ handleInput(input);
			
		}catch (WrongFormatException ex) {
			System.out.println(ex.getMessage());	
		}

	}

	private void handleInput(String input) throws WrongFormatException {
		
		
		
		Pattern pattern = Pattern
				.compile("(O|o|m|M)([A-Z]|[a-z])([0-9]|[1-9][0-9])");
		Matcher matcher = pattern.matcher(input);
		if (!matcher.matches()) {
			throw new WrongFormatException("input is in incorrect format");
			
		} else {
			int row = (int) Character.toUpperCase(matcher.group(2).charAt(0)) - 65;
			int column = Integer.parseInt(matcher.group(3));
			char action = matcher.group(1).charAt(0);

			if (row > field.getRowCount() - 1
					|| column > field.getColumnCount() - 1) {
				System.out.println("out of bounds");
			} else {
				if (action == 'O' || action == 'o') {

					field.openTile(row, column);
				} else if (action == 'M' || action == 'm') {
					field.markTile(row, column);
				} else if (action == 'X' || action == 'x') {
					System.exit(0);
				}
			}
		}
	}
	
}
