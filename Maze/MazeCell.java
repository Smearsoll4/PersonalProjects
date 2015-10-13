package Maze;

public class MazeCell {

	private int cellDim;
	/**
	*this array tells whether we should have a wall in a given direction.
	*the dimensions are grouped in twos. This will necessarily be an even length.
	*For a given dimension n (n >= 0) the existence of a wall in the negative direction is given by walls[n*2] 
	*and the existence of a wall in the positive direction is given by walls[n*2 + 1].
	*/
	private boolean[] walls;
	
	private boolean isConnected = false;
	
	public MazeCell(int mazeDimension){
		cellDim = mazeDimension;
		walls = new boolean[cellDim*2];
		for (int i = 0; i < walls.length; i++){
			walls[i] = true;
		}
	}
	
	/**
	 * connects cell in a given dimension and direction. Dimension must be  >= 0.
	 */
	public void connectCell(int dimension, boolean positive){
		isConnected = true;
		if (positive){
			walls[(dimension) * 2 + 1] = false;
		} else {
			walls[(dimension) * 2] = false;
		}
	}
	
	/**
	 * Connects the Cell without removing walls.
	 */
	public void connectCell(){
		isConnected = true;
	}


	public boolean isConected() {
		return isConnected;
	}

	public boolean[] getWalls() {
		return walls;
	}
}
