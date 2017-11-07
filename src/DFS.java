import java.util.*;

// Depth-First-Search for solving the 8 Puzzle
public class DFS {
	
	Set<Integer> explored = new HashSet<Integer>();	// used to store integer representation of each previously explored state
	Stack<Node> stack = new Stack<Node>();	// LIFO stack for successors of states
	int maxStackSize = 0;	// variable contains the largest stack size during search
		
		// Constructor takes in a BoardState
		public DFS(BoardState inBoard){

			Node rootNode = new Node(inBoard);	// root node created with passed initial state
			stack.push(rootNode);	// root node added to queue
			maxStackSize++;		// root is the only node on stack so incremented by 1

		}
		
		// Depth-First-Search function
		public void search(){
			long DFSStartTime = System.currentTimeMillis();		// time marker for beginning of search
			int nodesPopped = 0;	// variable tracks number of nodes popped off queue
			while (!stack.isEmpty()){	// while loop executes while the queue is not empty
				Node temp = stack.pop();	// leading node of stack is removed and stored in temp
				nodesPopped++;	// number of nodes popped off stack incremented by 1
				explored.add(temp.getCurrentState().getIntValue());	// int representation of state is hashed and stored 
				if (!temp.getCurrentState().isGoalState()){	// checks if goal state has been found, executes if not
					ArrayList<BoardState> successors = temp.getCurrentState().successorGenerator();	// array list containing successor states
					for (int i = 0; i < successors.size(); i++){	// loop through all successors
						BoardState successorState = successors.get(i);	// successor state assigned to object
						int intVal = successorState.getIntValue();	// int representation of state stored
						if (!explored.contains(intVal)){	// checks if state has already been explored
							int costSoFar = temp.getGCost() + successorState.getCostOfStateChange();	// gCost updated including state change
							Node child = new Node(successorState, temp, costSoFar, 0);	// child node created taking successor state, parent, and updated gCost
							stack.push(child);	// child node added to queue
							if (maxStackSize < stack.size()){	// checks if stack is larger than previous max
								maxStackSize = stack.size();	// assigns new max
							}
						}
					}
				}else{	// executes when goal state has been found
					long DFSEndTime = System.currentTimeMillis();		// time marker for when goal state has been found
					double DFSTime = DFSEndTime - DFSStartTime;		// total time from start of search to when goal state was found
					Stack<Node> pathToGoal = new Stack<Node>();	// stack created for solution path
					while (temp.getParentNode() != null){	// pushes temp node to a stack and all of its parents
						pathToGoal.push(temp);
						temp = temp.getParentNode();
					}
					pathToGoal.push(temp);
					int solutionLength = pathToGoal.size();	// depth of solution path
					
					// reports the type of search, the cost of the solution path, depth, the number of nodes popped off queue
					// the solution path starting at the initial state, the max size of the queue and time it took for the
					// search to find the goal state
					System.out.println("**********************************************************");
					System.out.println("*                    DFS SOLUTION PATH                   *");
					System.out.println("**********************************************************");
					System.out.println();
					
					temp = pathToGoal.pop();	// pop the initial state off the stack
					System.out.println(temp.getCurrentState().getMoveToCurrentState());	// prints notifying that this is initial state
					System.out.println();
					temp.getCurrentState().printBoardState();
					System.out.println();
					
					for (int i = 0; i < solutionLength - 1; i++){	// pops each node from stack and prints
						temp = pathToGoal.pop();
						System.out.println("Move the " + temp.getCurrentState().getCostOfStateChange() + " " + temp.getCurrentState().getMoveToCurrentState());
						System.out.println("Cost of move = " + temp.getCurrentState().getCostOfStateChange());
						System.out.println("Total Cost = " + temp.getGCost());
						System.out.println();
						temp.getCurrentState().printBoardState();
						System.out.println();
					}
					System.out.println("**********************************************************");
					System.out.println("*                 GOAL STATE ACHEIVED!!!                 *");
					System.out.println("**********************************************************");
					System.out.println("Search = DFS");
					System.out.println("Cost = " + temp.getGCost());
					System.out.println("Length of solution path = " + solutionLength);
					System.out.println("Nodes popped off the queue = " + nodesPopped);
					System.out.println("Max queue size during search = " + maxStackSize);
					//System.out.println("DFS took " + (DFSTime / 1000) + " seconds to find goal state");
					System.out.println("**********************************************************");
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					return;
				}
			}
		}
}