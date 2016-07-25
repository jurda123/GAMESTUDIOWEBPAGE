package sk.tsystems.gamestudio.games.minesweeper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



public class Settings implements Serializable{
	private final int rowCount;
	private final int columnCount;
	private final int mineCount ;
	
	public static final Settings  BEGINNER = new Settings(9, 9, 10);
	public static final Settings INTERMEDIATE = new Settings(16, 16, 40);
	public static final Settings EXPERT = new Settings(16, 30, 99);
	
	 private static final String SETTING_FILE = System.getProperty("user.home") + System.getProperty("file.separator") + "minesweeper.settings";;
	 
	public Settings(int rowCount, int columnCount, int mineCount){
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
	}
	@Override
	public boolean equals(Object o){
		if (o.equals(this)){
			return true;
		}
		
		return false;
	}
	
	public int hashCode(){
		return getColumnCount() * getRowCount() * getMineCount();
	}
	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}
	public void save(){
		 FileOutputStream out;
		try {
			out = new FileOutputStream(SETTING_FILE);
			ObjectOutputStream s = new ObjectOutputStream(out);
	        
	        s.writeObject(this);
	        out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	}
	public static Settings load(){
		Settings loadedSettings = new Settings(9,9,10);
			 FileInputStream in;
			try {
				in = new FileInputStream("register.bin");
				ObjectInputStream s = new ObjectInputStream(in);
		        loadedSettings = (Settings) s.readObject();
		        in.close();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return loadedSettings;
		 
		
	}
	
}
