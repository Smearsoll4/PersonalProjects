package Maze;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MazePanel extends JPanel{

	private MazeNVector maze;

	public MazePanel(MazeNVector maze){
		setPreferredSize(new Dimension(maze.getMagnitude(0) * Main.CELL_SIZE, maze.getMagnitude(1) * Main.CELL_SIZE));
		this.maze = maze;
	}

	public void repaint(Graphics g){
		setPreferredSize(new Dimension(maze.getMagnitude(0) * Main.CELL_SIZE, maze.getMagnitude(1) * Main.CELL_SIZE));
		for (MazeCell mc : maze.data){
			int[] loc = maze.getLocation(mc);
			int relX = loc[0] * Main.CELL_SIZE;
			int relY = loc[1] * Main.CELL_SIZE;
			boolean[] walls = mc.getWalls();
			if (walls[0]){
				g.drawLine(relX, relY, relX, relY + Main.CELL_SIZE);
			} 
			if (walls[1]){
				g.drawLine(relX + Main.CELL_SIZE, relY, relX + Main.CELL_SIZE, relY + Main.CELL_SIZE);
			}
			if (walls[2]){
				g.drawLine(relX, relY, relX + Main.CELL_SIZE, relY);
			}
			if (walls[3]){
				g.drawLine(relX + Main.CELL_SIZE, relY + Main.CELL_SIZE, relX + Main.CELL_SIZE, relY + Main.CELL_SIZE);
			}
		}
	}
}