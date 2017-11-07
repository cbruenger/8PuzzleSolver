import java.util.*;

// Uniform-Cost-Search for solving the 8 Puzzle
public class UniformCost {

	Set<Integer> explored = new HashSet<Integer>();	// used to store integer representation of each previously explored state
	PriorityQueue<Node> priorityQ = new PriorityQueue<Node>();	// used to order successor states by their cost
	int maxQueueSize = 0;	// variable contains the largest priorityQ size during search
		
		// Constructor takes in a BoardState
		public UniformCost(BoardState inBoard){

			Node rootNode = new Node(inBoard);	// root node created with passed initial state
			rootNode.isUCNode = true;	// node type set for comparable
			priorityQ.add(rootNode);	// root node added to queue
			maxQueueSize++;		// root is the only node in priorityQ so incremented by 1
			
		}
		
		// Uniform-Cost-Search function
		public void search(){
			long uniformCostStartTime = System.currentTimeMillis();		// time marker for beginning of search
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
							Node child = new Node(successorState, temp, costSoFar, 0);	// child node created taking successor state, parent, and updated gCost
							child.isUCNode = true;	// node type set for comparable
							priorityQ.add(child);	// child node added to queue according to comparable
							if (maxQueueSize < priorityQ.size()){	// checks if priorityQ is larger than previous max
								maxQueueSize = priorityQ.size();	// assigns new max
							}
						}
					}
				}else{	// executes when goal state has been found
					long uniformCostEndTime = System.currentTimeMillis();		// time marker for when goal state has been found
					double uniformCostTime = uniformCostEndTime - uniformCostStartTime;		// total time from start of search to when goal state was found
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
					System.out.println("*************************************************************");
					System.out.println("*                 UNIFORM COST SOLUTION PATH                *");
					System.out.println("*************************************************************");
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
					System.out.println("Search = UNIFORM COST");
					System.out.println("Cost = " + temp.getGCost());
					System.out.println("Length of solution path = " + solutionLength);
					System.out.println("Nodes popped off the queue = " + nodesPopped);
					System.out.println("Max queue size during search = " + maxQueueSize);
					//System.out.println("Uniform-Cost took " + (uniformCostTime / 1000) + " seconds to find goal state");
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