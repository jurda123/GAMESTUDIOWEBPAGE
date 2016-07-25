package sk.tsystems.gamestudio.services;

import java.util.ArrayList;

import sk.tsystems.gamestudio.entities.Score;
import sk.tsystems.gamestudio.entities.ScoreStringRepresentation;

public interface ScoreService {
	
	public void addScore();
	
	public ArrayList<ScoreStringRepresentation> getTopTenScore();
}
