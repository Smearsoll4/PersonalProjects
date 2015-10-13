package Maze;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	static final int CELL_SIZE = 20;
	public static void main(String[] args){
		int[] v2d = new int[]{40, 40};
		MazeNVector maze = new MazeNVector(v2d);
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(v2d[0] * CELL_SIZE, v2d[1] * CELL_SIZE));
		f.getContentPane().add(p);
		f.pack();
		f.setVisible(true);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		Graphics g = p.getGraphics();
		
		//Waiting will prevent the race condition. Why does it happen in the first place?
		try {
			TimeUnit.MILLISECONDS.sleep(60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (MazeCell mc : maze.data){
			int[] loc = maze.getLocation(mc);
			int relX = loc[0] * CELL_SIZE;
			int relY = loc[1] * CELL_SIZE;
			boolean[] walls = mc.getWalls();
			if (walls[0]){
				g.setColor(Color.black);
				g.drawLine(relX, relY, relX, relY + CELL_SIZE);
			} else {
				g.setColor(Color.red);
				g.drawLine(relX + CELL_SIZE/2, relY + CELL_SIZE/2, relX - CELL_SIZE/2, relY + CELL_SIZE/2);
			}
			if (walls[1]){
				g.setColor(Color.black);
				g.drawLine(relX + CELL_SIZE, relY, relX + CELL_SIZE, relY + CELL_SIZE);
			} else {
				g.setColor(Color.red);
				g.drawLine(relX + CELL_SIZE/2, relY + CELL_SIZE/2, relX + CELL_SIZE + CELL_SIZE/2, relY + CELL_SIZE/2);
			}
			if (walls[2]){
				g.setColor(Color.black);
				g.drawLine(relX, relY, relX + CELL_SIZE, relY);
			} else {
				g.setColor(Color.red);
				g.drawLine(relX + CELL_SIZE/2, relY + CELL_SIZE/2, relX + CELL_SIZE/2, relY - CELL_SIZE/2);
			}
			if (walls[3]){
				g.setColor(Color.black);
				g.drawLine(relX, relY + CELL_SIZE, relX + CELL_SIZE, relY + CELL_SIZE);
			} else {
				g.setColor(Color.red);
				g.drawLine(relX + CELL_SIZE/2, relY + CELL_SIZE/2, relX + CELL_SIZE/2, relY + CELL_SIZE + CELL_SIZE/2);
			}
		}
	}
}
