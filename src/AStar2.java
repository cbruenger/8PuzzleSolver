import java.util.*;

// A*-Search for solving the 8 Puzzle using the Manhattan Distances as the heuristic
public class AStar2 {
	
	Set<Integer> explored = new HashSet<Integer>();	// used to store integer representation of each previously explored state
	PriorityQueue<Node> priorityQ = new PriorityQueue<Node>();	// used to order successor states by the heuristic cost
	int maxQueueSize = 0;	// variable contains the largest priorityQ size during search
		
		// Constructor takes in a BoardState
		public AStar2(BoardState inBoard){

			Node rootNode = new Node(inBoard);	// root node created with passed initial state
			rootNode.isAStarNode = true;	// node type set for comparable
			priorityQ.add(rootNode);	// root node added to queue
			maxQueueSize++;		// root is the only node in priorityQ so incremented by 1
			
		}
		
		// A*2-Search function
		public void search(){
			long aStar2StartTime = System.currentTimeMillis();		// time marker for beginning of search
			int nodesPopped = 0;	// variable tracks number of nodes popped off queue
			while (!priorityQ.isEmpty()){	// while loop executes while the queue is not empty
				Node temp = priorityQ.poll();	// leading node of queue is removed and stored in temp
				nodesPopped++;	// number of nodes popped off queue incremented by 1
				explored.add(temp.getCurrentState().getIntValue());	// int representation of state is hashed and stored 
				if (!temp.getCurrentState().isGoalState()){	// checks if goal state has been found, executes if not
					ArrayList<BoardState> successors = temp.getCurrentState().successorGenerator();	// array list containing successor states
					for (int i = 0; i < successors.size(); i++){	// loop through all successors
						BoardState successorState = successors.get(i);	// successor state assigned to object
						int intVal = successorState.getIntValue();	// int representation of state stored
						if (!explored.contains(intVal)){	// checks if state has already been explored
							int costSoFar = temp.getGCost() + successorState.getCostOfStateChange();	// gCost updated including state change
							int currentHCost = temp.getCurrentState().getManhattanDistances();	// current Manhattan Distance heuristic stored
							Node child = new Node(successorState, temp, costSoFar, currentHCost);	// child node created taking successor state, parent, updated g and h costs
							child.isAStarNode = true;	// node type set for comparable
							priorityQ.add(child);	// child node added to queue according to comparable
							if (maxQueueSize < priorityQ.size()){	// checks if priorityQ is larger than previous max
								maxQueueSize = priorityQ.size();	// assigns new max
							}
						}
					}
				}else{	// executes when goal state has been found
					long aStar2EndTime = System.currentTimeMillis();		// time marker for when goal state has been found
					double aStar2Time = aStar2EndTime - aStar2StartTime;	// total time from start of search to when goal state was found
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
					System.out.println("*                    A*2 SOLUTION PATH                   *");
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
					System.out.println("**************************************");
					System.out.println("*       GOAL STATE ACHEIVED!!!       *");
					System.out.println("**************************************");
					System.out.println("Search = A*2");
					System.out.println("Cost = " + temp.getGCost());
					System.out.println("Length of solution path = " + solutionLength);
					System.out.println("Nodes popped off the queue = " + nodesPopped);
					System.out.println("Max queue size during search = " + maxQueueSize);
					//System.out.println("A*2 took " + (aStar2Time / 1000) + " seconds to find goal state");
					System.out.println("**************************************");
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