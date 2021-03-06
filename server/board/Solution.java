package board;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solution implements Serializable {
	
	private static final long serialVersionUID = 41L;
	State<?> goalState;
	State<?> initialState;
	List<Step> stepList = new LinkedList<Step>();
	
	public Solution(State<?> initialState, State<?> goalState) {
		this.initialState = initialState;
		this.goalState = goalState;
		
		State<?> currentState = goalState;
		while(currentState.getCameFrom() != null) {
			// add step
			stepList.add(currentState.getStep());
			currentState = currentState.getCameFrom();
		}
		Collections.reverse(stepList);
	}
	public void addStep(Step step) {
		stepList.add(step);
	}
	public State<?> getGoalState() {
		return goalState;
	}
	
	public List<Step> getStepList() {
		return stepList;
	}
}
