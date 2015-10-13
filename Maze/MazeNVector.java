package Maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

public class MazeNVector extends NVector<MazeCell>{

	private ArrayList<MazeCell> unconnectedCells;
	private MazeCell beginCell, endCell;
	
	public MazeNVector(int[] values) {
		super(values, new MazeCell(values.length));
		
		unconnectedCells = new ArrayList<MazeCell>();
		int i = 0;
		while(i < getDataLength()){
			data[i] = new MazeCell(dimension);
			unconnectedCells.add(data[i]);
			i++;
		}//Fill Maze with new cells.
		
		beginCell = data[0];
		beginCell.connectCell();
		unconnectedCells.remove(beginCell);
		endCell = data[data.length - 1];
		long startTime = System.nanoTime();
		generateMaze(beginCell);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration * .000000001 + " s");
		endCell = beginCell;
	}

	private void generateMaze(MazeCell lastConnectedCell){
		Random r = new Random();
		int pos = 0;
		if (unconnectedCells.size() > 1){
			pos = r.nextInt(unconnectedCells.size());
			while(unconnectedCells.get(pos) == endCell){
				pos = r.nextInt(unconnectedCells.size());
			}
		}
		MazeCell nextCell = unconnectedCells.get(pos);
		connectCells(getLocation(lastConnectedCell), nextCell);
		if (unconnectedCells.size() > 0){
			generateMaze(nextCell);
		}
	}

	private void connectCells(int[] beginLoc, MazeCell currentCell) {
		int[] currentLoc = getLocation(currentCell);
		int[] difference = new int[beginLoc.length];
		
		if (currentCell != this.getValue(beginLoc)){
			for (int i = 0; i < currentLoc.length; i++){
				difference[i] = currentLoc[i] - beginLoc[i];
			}
			Random r = new Random();
			int dim;
			boolean movePositive = false;//if difference is less than zero, we must move in the positive direction to approach the beginLocation
			int[] newLoc;
			do{
				dim = r.nextInt(dimension);
				newLoc = Arrays.copyOf(currentLoc, currentLoc.length);
				if (difference[dim] != 0){
					movePositive = difference[dim] < 0;//if difference is less than zero, we must move in the positive direction to approach the beginLocation
					if (movePositive){
						newLoc[dim] = newLoc[dim] + 1;
					} else {
						newLoc[dim] = newLoc[dim] - 1;
					}
				}
			} while (difference[dim] == 0 || this.getValue(newLoc) == endCell);
			currentCell.connectCell(dim, movePositive);
			unconnectedCells.remove(currentCell);
			MazeCell newNext = this.getValue(newLoc);
			newNext.connectCell(dim, !movePositive);
			if (unconnectedCells.contains(newNext)){
				connectCells(beginLoc, newNext);
			}
		}
		
			
	}

}
