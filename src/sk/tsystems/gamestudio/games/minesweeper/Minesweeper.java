package sk.tsystems.gamestudio.games.minesweeper;

import sk.tsystems.gamestudio.games.Game;
import sk.tsystems.gamestudio.games.minesweeper.consoleUI.ConsoleUI;
import sk.tsystems.gamestudio.games.minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper implements Game {
	
	private long startMillis;
	 private BestTimes bestTimes;
	 private static Minesweeper instance;
	 Settings setting;
	 private int score;
	 
	
    public int getScore() {
		return score;
	}




	public void setScore(int score) {
		this.score = score;
	}




	public Settings getSetting() {
		return setting;
	}
    
    


	public void setSetting(Settings setting) {
		setting.save();
	}


	public long getStartMillis() {
		return startMillis;
	}

	/** User interface. */
    private UserInterface userInterface;
 
    /**
     * Constructor.
     */
    public Minesweeper() {
    	instance = this;
    	bestTimes = new BestTimes();
    	startMillis= System.currentTimeMillis();
        userInterface = new ConsoleUI();
        
        Field field = new Field(9, 9, 10);
        userInterface.newGameStarted(field);
        setting = Settings.load();
    }
    

    public static Minesweeper getInstance() {
    	if(instance != null){
		return instance;
    	}
    	else {
    		instance = new Minesweeper();
    		return instance;
    	}
	}


	public BestTimes getBestTimes() {
		return bestTimes;
	}


	/**
     * Main method.
     * @param args arguments
     */
    public void play() {
        new Minesweeper();
    }
    
    public int getPlayingSeconds(){
    	
    	Long time= System.currentTimeMillis() - startMillis;
    	int intTime= time.intValue();
    	return intTime/1000;
    }
}
