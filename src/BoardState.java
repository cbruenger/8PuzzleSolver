import java.util.ArrayList;

// A class to acquire the state of the environment and the successor states
public class BoardState {
		
		// Class variables which contain information about the current state
		private int piecesOutOfPlace = 0;
		private int manhattanDistances = 0;
		private int weightedManhattanDistances = 0;
		private int emptySpaceIndex;
		private int costOfStateChange;
		private String moveToCurrentState;
		private final int[] goalState = {1, 2, 3, 8, 0, 4, 7, 6, 5};
		private final int boardSize = goalState.length;
		private int[] currentState;
		
		// Constructor receives a board and a cost which represents
		// the state change cost from the previous state to the current
		public BoardState(int[] currentBoard, int cost, String move){
			currentState = currentBoard;
			assignPiecesOutOfPlace();
			assignManhattanDistances();
			assignWeightedManhattanDistances();
			assignEmptySpaceIndex();
			costOfStateChange = cost;
			moveToCurrentState = move;
		}
		
		// A function to check if the current state is the goal state
		public boolean isGoalState(){
			for (int i = 0; i < boardSize; i++){
				if (currentState[i] != goalState[i]) return false;
			}
			return true;
		}
		
		// Returns current board state 
		public int[] getCurrentBoardState(){
			return currentState;
		}
		
		// Returns an int representation of the numbers in the array to be stored in the explored 
		// hashset of the searches. Builds string and then parses as an int
		public int getIntValue() {
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < currentState.length; i++) {
				String s = Integer.toString(currentState[i]);
				stringBuilder.append(s);
			}
			String intString = stringBuilder.toString();
			return Integer.parseInt(intString);
			}
		
		
		// Returns the size of the array which represents the board
		public int getSize(){
			return boardSize;
		}
		
		
		// Called by constructor to count the number of pieces out of place
		private void assignPiecesOutOfPlace(){
			for (int i = 0; i < boardSize; i++){
				if (goalState[i] != currentState[i]){
					piecesOutOfPlace++;
				}
			}
		}
		
		// Returns the number of pieces out of place
		public int getPiecesOutOfPlace(){
			return piecesOutOfPlace;
		}
		
		// Called by the constructor and calculates the Manhattan distance total considering
		// only 1 cost per Manhattan Distance
		private void assignManhattanDistances(){
			// length of each row of the board must be known since iteration
			// is over the total length of board spaces 
			int lengthOfRow = 3;
			// nested loop iterating over all board spaces of current state and goal state.
			// When matching values are found, indices are divided by row length
			// and the absolute value of their difference is taken. Also the absolute value
			// of the difference of the modulo of indices and row length is taken. These values
			// are added together
			for (int i = 0; i < boardSize; i++){
				for (int p = 0; p < boardSize; p++){
					if (currentState[i] == goalState[p]){
						manhattanDistances += ((Math.abs(i/lengthOfRow - p/lengthOfRow)
											+ Math.abs(i%lengthOfRow - p%lengthOfRow)));
					}
				}
			}
					
		}
		
		// Returns the Manhattan distances 
		public int getManhattanDistances(){
			return manhattanDistances;
		}
		
		// Same as above function for calculating Manhattan Distances, but this also
		// considers the values of the pieces being moved along with their Manhattan Distances
		// See comments for Manhattan Distances. I've included a comment in this function
		// where the change has been made. This is used as a heuristic in AStar3
		private void assignWeightedManhattanDistances(){
			int lengthOfRow = 3;
			for (int i = 0; i < boardSize; i++){
				for (int p = 0; p < boardSize; p++){
					if (currentState[i] == goalState[p]){
						weightedManhattanDistances += (((Math.abs(i/lengthOfRow - p/lengthOfRow)
											+ Math.abs(i%lengthOfRow - p%lengthOfRow))
											// Multiply by value of piece to consider cost of each piece per move
											* currentState[i]));
					}
				}
			}
		}
		
		// Returns the weighted Manhattan distances considering cost of each piece per move
		public int getWeightedManhattanDistances(){
			return weightedManhattanDistances;
		}
		
		// Called by constructor to find the index of the empty space
		private void assignEmptySpaceIndex(){
			for (int i = 0; i < boardSize; i++){
				if (currentState[i] == 0) emptySpaceIndex = i;
			}
		}
		
		// Returns the index of the empty space
		public int getEmptySpaceIndex(){
			return emptySpaceIndex;
		}
		
		// Returns cost of state change from previous to current
		public int getCostOfStateChange(){
			return costOfStateChange;
		}
		
		// Returns the move from previous state to current
		public String getMoveToCurrentState(){
			return moveToCurrentState;
		}
		
		// Makes a copy of the state passed. Used for creating successor states
		// without changing current state
		public int[] getCurrentStateCopy(int[] stateToCopy){
			int[] newCopy = new int[boardSize];
			for (int i = 0; i < boardSize; i++){
				newCopy[i] = currentState[i];
			}
			return newCopy;
		}
		 
		// Generates the successors of the current state. Maximum of 
		// four states depending where the empty space is located.
		// Returns an ArrayList of the successors
		public ArrayList<BoardState> successorGenerator(){
			ArrayList<BoardState> successorStates = new ArrayList<BoardState>();
			int emptySpace = getEmptySpaceIndex();
			
			// Swap the empty space with the piece to the right
			if (emptySpace != 2 && emptySpace != 5 && emptySpace != 8){
				String left = "LEFT";
				swap(emptySpace + 1, successorStates, left);
			}
			
			// Swap the empty space with the piece to the left
			if (emptySpace != 0 && emptySpace != 3 && emptySpace != 6){
				String right = "RIGHT";
				swap(emptySpace - 1, successorStates, right);
			}
			
			// Swap the empty space with the piece above it
			if (emptySpace != 0 && emptySpace != 1 && emptySpace != 2){
				String down = "DOWN";
				swap(emptySpace - 3, successorStates, down);
			}
			
			// Swap the empty space with the piece below it
			if (emptySpace != 6 && emptySpace != 7 && emptySpace != 8){
				String up = "UP";
				swap(emptySpace + 3, successorStates, up);
			}
			return successorStates;
		}
				  
		// Creates a new BoardState in which the piece at swapIndex is 
		// swapped with the empty space. The new BoardState including the cost
		// of the swap is added to the successors ArrayList
		public void swap(int swapIndex, ArrayList<BoardState> stateIn, String moveIn){
			int[] boardCopy = getCurrentStateCopy(currentState);
			int emptySpace = getEmptySpaceIndex();
			boardCopy[emptySpace] = currentState[swapIndex];
			boardCopy[swapIndex] = currentState[emptySpace];
			stateIn.add(new BoardState(boardCopy, currentState[swapIndex], moveIn));
		}
		
		// Prints the current board state in the layout of a 3X3 board
		public void printBoardState(){
			System.out.println(" _________________");
			System.out.println("|     |     |     |");
			System.out.println("|  " + currentState[0] + "  |  " + currentState[1] + "  |  " + currentState[2] + "  |");
			System.out.println("|_____|_____|_____|");
			System.out.println("|     |     |     |");
			System.out.println("|  " + currentState[3] + "  |  " + currentState[4] + "  |  " + currentState[5] + "  |");
			System.out.println("|_____|_____|_____|");
			System.out.println("|     |     |     |");
			System.out.println("|  " + currentState[6] + "  |  " + currentState[7] + "  |  " + currentState[8] + "  |");
			System.out.println("|_____|_____|_____|");
			System.out.println();
		}		
}