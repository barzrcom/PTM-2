package server;

import java.util.List;

public class SearchableGameBoard implements Searchable {	
	private GameBoard gameBoard;

	SearchableGameBoard(GameBoard gameBoard){
		this.gameBoard = gameBoard;
	}
	@Override
	public State getInitialState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGoalState(State state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<State> getAllPossibleStates(State state) {
		// TODO Auto-generated method stub
		return null;
	}

}
