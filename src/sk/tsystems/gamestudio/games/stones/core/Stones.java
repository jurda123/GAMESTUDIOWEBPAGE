package sk.tsystems.gamestudio.games.stones.core;

import sk.tsystems.gamestudio.games.Game;
import sk.tsystems.gamestudio.games.stones.consoleui.ConsoleUI;

public class Stones implements Game{
    private int score;
	@Override
	public int getScore() {
		return score;
		
	}

	@Override
	public void play() {
		ConsoleUI con = new ConsoleUI();
		con.run();
		score = con.getTime();
	
		
	}
	
	
	
}
