// Main class for solving the 8 puzzle
public class EightPuzzleMain {

	public static void main(String[] args){
		
		int[] easyBoard = {1, 3, 4, 8, 6, 2, 7, 0, 5};		// variable representing easy board configuration
		int[] mediumBoard = {2, 8, 1, 0, 4, 3, 7, 6, 5};	// variable representing medium board configuration
		int[] hardBoard = {5, 6, 7, 4, 0, 8, 3, 2, 1};		// variable representing hard board configuration
		
		
		///////////////////////////////////////////////////////////////////////
		//*******************************************************************//
		//*								    *//
		//*	Below, the BoardState objects and instances are created	    *//
		//* 	for each search type with each of the configurations:	    *//
		//*	easy, medium, hard 					    *//
		//*								    *//
		//*******************************************************************//
		///////////////////////////////////////////////////////////////////////
		
		
		BoardState easyBoardStateBFS = new BoardState(easyBoard, 0, "INITIAL STATE");		// BFS
		BoardState mediumBoardStateBFS = new BoardState(mediumBoard, 0, "INITIAL STATE");
		BoardState hardBoardStateBFS = new BoardState(hardBoard, 0, "INITIAL STATE");
		BFS easyBFS = new BFS(easyBoardStateBFS);
		BFS mediumBFS = new BFS(mediumBoardStateBFS);
		BFS hardBFS = new BFS(hardBoardStateBFS);

		BoardState easyBoardStateDFS = new BoardState(easyBoard, 0, "INITIAL STATE");		// DFS
		BoardState mediumBoardStateDFS = new BoardState(mediumBoard, 0, "INITIAL STATE");
		BoardState hardBoardStateDFS = new BoardState(hardBoard, 0, "INITIAL STATE");
		DFS easyDFS = new DFS(easyBoardStateDFS);
		DFS mediumDFS = new DFS(mediumBoardStateDFS);
		DFS hardDFS = new DFS(hardBoardStateDFS);

		BoardState easyBoardStateUC = new BoardState(easyBoard, 0, "INITIAL STATE");			// UNIFORM COST
		BoardState mediumBoardStateUC = new BoardState(mediumBoard, 0, "INITIAL STATE");
		BoardState hardBoardStateUC = new BoardState(hardBoard, 0, "INITIAL STATE");
		UniformCost easyUniformCost = new UniformCost(easyBoardStateUC);
		UniformCost mediumUniformCost = new UniformCost(mediumBoardStateUC);
		UniformCost hardUniformCost = new UniformCost(hardBoardStateUC);

		BoardState easyBoardStateBestFirst = new BoardState(easyBoard, 0, "INITIAL STATE");		// BEST FIRST
		BoardState mediumBoardStateBestFirst = new BoardState(mediumBoard, 0, "INITIAL STATE");
		BoardState hardBoardStateBestFirst = new BoardState(hardBoard, 0, "INITIAL STATE");
		BestFirst easyBestFirst = new BestFirst(easyBoardStateBestFirst);
		BestFirst mediumBestFirst = new BestFirst(mediumBoardStateBestFirst);
		BestFirst hardBestFirst = new BestFirst(hardBoardStateBestFirst);		
		
		BoardState easyBoardStateAStar1 = new BoardState(easyBoard, 0, "INITIAL STATE");			// A*1
		BoardState mediumBoardStateAStar1 = new BoardState(mediumBoard, 0, "INITIAL STATE");
		BoardState hardBoardStateAStar1 = new BoardState(hardBoard, 0, "INITIAL STATE");
		AStar1 easyAStar1 = new AStar1(easyBoardStateAStar1);
		AStar1 mediumAStar1 = new AStar1(mediumBoardStateAStar1);
		AStar1 hardAStar1 = new AStar1(hardBoardStateAStar1);		
		
		BoardState easyBoardStateAStar2 = new BoardState(easyBoard, 0, "INITIAL STATE");			// A*2
		BoardState mediumBoardStateAStar2 = new BoardState(mediumBoard, 0, "INITIAL STATE");
		BoardState hardBoardStateAStar2 = new BoardState(hardBoard, 0, "INITIAL STATE");
		AStar2 easyAStar2 = new AStar2(easyBoardStateAStar2);
		AStar2 mediumAStar2 = new AStar2(mediumBoardStateAStar2);
		AStar2 hardAStar2 = new AStar2(hardBoardStateAStar2);
		
		BoardState easyBoardStateAStar3 = new BoardState(easyBoard, 0, "INITIAL STATE");			// A*3
		BoardState mediumBoardStateAStar3 = new BoardState(mediumBoard, 0, "INITIAL STATE");
		BoardState hardBoardStateAStar3= new BoardState(hardBoard, 0, "INITIAL STATE");
		AStar3 easyAStar3 = new AStar3(easyBoardStateAStar3);
		AStar3 mediumAStar3 = new AStar3(mediumBoardStateAStar3);
		AStar3 hardAStar3 = new AStar3(hardBoardStateAStar3);

		
		
		///////////////////////////////////////////////////////////////////////
		//*******************************************************************//
		//*								    *//
		//*	Search calls for all search types are listed below with	    *//
		//*	easy, medium and hard configurations listed for each. 	    *//
		//*	Simply uncomment the line of code for the search call	    *//
		//*	you want to make.					    *//
		//*								    *//
		//*******************************************************************//																	//
		///////////////////////////////////////////////////////////////////////
		
		
//		easyBFS.search();			// BFS
//		mediumBFS.search();
//		hardBFS.search();
//
//		
//		easyDFS.search();			// DFS
//		mediumDFS.search();
//		hardDFS.search();
//		
//	
//		easyUniformCost.search();		// UNIFORM-COST
//		mediumUniformCost.search();
//		hardUniformCost.search();
//		
//		
//		easyBestFirst.search();			// BEST-FIRST
//		mediumBestFirst.search();
//		hardBestFirst.search();		
//
//
//		easyAStar1.search();			// A*1
//		mediumAStar1.search();			
//		hardAStar1.search();
//		
//		
//		easyAStar2.search();			// A*2
//		mediumAStar2.search();
//		hardAStar2.search();
//		
//		
//		easyAStar3.search();			// A*3 
//		mediumAStar3.search();
		hardAStar3.search();


	}
}