package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import board.Board;
import board.PipeGameBoard;
import board.Solution;
import board.Step;
import cacheManager.CacheManager;
import cacheManager.FileCacheManager;
import searcher.AStarSearcher;
import solver.PipeSolver;
import solver.Solver;

/**
 * has the responsibility of closing the streams
 */
public class PipeClientHandler implements ClientHandler {
	
	private CacheManager cacheManager = new FileCacheManager();
	private Solver solver = new PipeSolver(new AStarSearcher());
	
	private int size = Integer.MAX_VALUE;
	private PipeGameBoard gameBoard = null; 
	
	@Override
	public void inClient(InputStream inFromClient) {
        try {
            BufferedReader inFClient = new BufferedReader(new InputStreamReader(inFromClient));
        	List<char[]> inputFromClient = new ArrayList<char[]>();
            String line;
            while (!(line = inFClient.readLine()).equals("done")) {
            	inputFromClient.add(line.toCharArray());
            }
 
            // build State from message.
            char[][] charArray = inputFromClient.toArray(new char[inputFromClient.size()][]);
            Board board = new Board(charArray);
			this.gameBoard = new PipeGameBoard(board);
			
			// update client size by board size
			this.setSize(board.getSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public void outClient(OutputStream outToClient) {
        
        PrintWriter outTC = new PrintWriter(outToClient);

		PipeGameBoard gameBoard = this.gameBoard;
		// Check if state-solve exists in cache manager
		Solution solution;
		if (cacheManager.isSolutionExist(gameBoard.getInitialState())) {
			// load solution from cache manager
			solution = this.cacheManager.load(gameBoard.getInitialState());
		} else {
			// send it to solver and save it in cache manager
			solution = solver.solve(gameBoard);
			cacheManager.save(gameBoard.getInitialState(), solution);
		}
		if(solution == null) {
			System.out.println("no route could be found");
		} else {

			for (Step step : solution.getStepList()) {
				outTC.println(step.toString());
			}
		}

		outTC.println("done");
		outTC.flush();

		// close the connection
		outTC.close();
	}

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        
        try {
        	PrintWriter outTC = new PrintWriter(outToClient);
            BufferedReader inFClient = new BufferedReader(new InputStreamReader(inFromClient));
        	List<char[]> inputFromClient = new ArrayList<char[]>();
            String line;
            while (!(line = inFClient.readLine()).equals("done")) {
            	inputFromClient.add(line.toCharArray());
            }
 
            // build State from message.
            char[][] charArray = inputFromClient.toArray(new char[inputFromClient.size()][]);
            Board board = new Board(charArray);
			PipeGameBoard gameBoard = new PipeGameBoard(board);
			
			// update client size by board size
			this.setSize(board.getSize());

			// Check if state-solve exists in cache manager
			Solution solution;
            if (cacheManager.isSolutionExist(gameBoard.getInitialState())) {
            	// load solution from cache manager
            	solution = this.cacheManager.load(gameBoard.getInitialState());
            } else {
            	// send it to solver and save it in cache manager
            	solution = solver.solve(gameBoard);
            	cacheManager.save(gameBoard.getInitialState(), solution);
            }
            if(solution == null) {
            	System.out.println("no route could be found");
            } else {

            	for (Step step : solution.getStepList()) {
            		outTC.println(step.toString());
            	}
            }

        	outTC.println("done");
        	outTC.flush();
        	//System.out.println("problem solved");

            // close the connection
            inFClient.close();
            outTC.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
