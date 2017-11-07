// This class represents the nodes created in the searches 
// Nodes contain the current state, the parent node, the cost
// from the start to current as g, the cost of the heuristic being
// used as h, and the estimated cost from start to finish as f
// implements Comparable<Node> so nodes can be compared according
// to certain conditions
public class Node implements Comparable<Node>{

	private BoardState currentState;
	private Node parentNode;
	private int gCost;		//cost from start to current state
	private int hCost;		//cost of the heuristic
	private int fCost;		//cost estimate from start to finish
	public boolean isUCNode = false;		// search type that node is used for
	public boolean isBestFirstNode = false;		// search type that node is used for
	public boolean isAStarNode = false;		// search type that node is used for
	
	// Constructor for root
	public Node(BoardState stateIn){
		currentState = stateIn;
		parentNode = null;
		gCost = 0;
		hCost = 0;
		fCost = 0;
	}
	
	// Constructor for newly created nodes
	public Node(BoardState stateIn, Node parent, int g, int h){
		currentState = stateIn;		// state passed as current
		parentNode = parent;		// node passed as parent
		gCost = g;		// cost from start to current state
		hCost = h;		// heuristic cost
		fCost = gCost + hCost;	// estimated total cost heuristic considering g and another h
	}
	
	// Return the current state
	public BoardState getCurrentState(){
		return currentState;
	}
	
	// Return the parent node
	public Node getParentNode(){
		return parentNode;
	}
	
	// Return gCost which is cost-so-far
	public int getGCost(){
		return gCost;
	}
	
	// Return hCost which is cost-of-heuristic
	public int getHCost(){
		return hCost;
	}
	
	// Return fCost which is estimated-cost from start to finish
	public int getFCost(){
		return fCost;
	}
	
	// required function for Comparable interface that returns an int
	// which represents ordering of nodes when being stored in a 
	// priority queue. 
	@Override
	public int compareTo(Node NodeIn) {
		Node nodeToCompare = NodeIn;
		// Sorts by gCost for node type isUniform Cost
		if (this.isUCNode == true){
			return this.getGCost() - nodeToCompare.getGCost();
		// Sorts by hCost for node type isBestFirst
		}else if (this.isBestFirstNode == true){
			return this.getHCost() - nodeToCompare.getHCost();
		// Sorts by fCost for node type isAStar
		}else{
			return this.getFCost() - nodeToCompare.getFCost();
		}
	}
}
