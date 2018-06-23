package solver;

import java.util.List;

import board.GameBoard;
import board.PipeGameBoard;
import board.Solution;
import searcher.BFSSearcher;
import searcher.BestFirstSearchSearcher;
import searcher.DFSSearcher;
import searcher.HillClimbingSearcher;
import searcher.AStarSearcher;

public class RunSolver {
    public static void main(String[] args) throws Exception {
        System.out.println("**** Solver Unittest ****");

        char[][] board0 = {
        		{'s', 'L'},
        		{'F', 'g'}
        };
        char[][] board1 = {	
        		{'s', '|', '|', '|', '|', '|', 'L'},
        		{' ', ' ', ' ', ' ', ' ', ' ', '-'},
        		{' ', ' ', ' ', ' ', 'g', '-', 'L'}
        };
        char[][] board2 = {	
        		{'s', '|', '|', '7', '|'},
        		{' ', ' ', ' ', '-', ' '},
        		{' ', ' ', ' ', '-', ' '},
        		{'|', '-', '-', 'J', 'L'},
        		{' ', ' ', ' ', ' ', '-'},
        		{' ', ' ', ' ', ' ', '-'},
        		{' ', ' ', ' ', ' ', 'g'},
        		{' ', ' ', ' ', ' ', ' '},
        		{' ', ' ', ' ', ' ', ' '},
        		{' ', ' ', ' ', ' ', ' '}
        };
        
        char[][] board3 = {
        		{'s','-','-','-','-','-','-','L'},
        		{'L','-','|','-','-','|','-','L'},
        		{'L','-','-','-','-','-','-','L'},
        		{'L','-','|','-','-','|','-','L'},
        		{'L','-','-','|','-','-','-','g'}
    	};
        char[][] board4 = {
        		{'s','-','J','|','-','-','-','L'},
        		{'L','-','F','-','-','|','-','L'},
        		{'L','-','-','-','7','-','-','L'},
        		{'L','-','-','|','J','-','-','g'}
    	};

        GameBoard gameBoard = new PipeGameBoard(board3);
        
        //Solver solver = new PipeSolver(new BestFirstSearchSearcher());
        //Solver solver = new PipeSolver(new BFSSearcher());
        //Solver solver = new PipeSolver(new DFSSearcher());
        Solver solver = new PipeSolver(new AStarSearcher());
        //Solver solver = new PipeSolver(new HillClimbingSearcher());
        Solution solution = solver.solve(gameBoard);
        if(solution == null) {
        	System.out.println("no route could be found");
        } else {
        	List<String> stepsList = solution.getStepsList();
        	System.out.println(stepsList.size() + " Steps");
        	for (String step : solution.getStepsList()) {
        		//System.out.println(step);
        	}
        }
    }
}
