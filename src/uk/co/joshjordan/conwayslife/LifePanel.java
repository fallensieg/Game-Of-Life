package uk.co.joshjordan.conwayslife;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LifePanel extends JPanel {

	public LifeGrid grid;
	
	
	public LifePanel(LifeGrid newGrid){
		this.grid = newGrid;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		double boxWidth = (double)this.getWidth() / grid.getGridLength();
		double boxHeight = (double)this.getHeight() / grid.getGridLength();
		
		
		//Draw Boxes
		for (int x = 0; x < grid.getGridLength(); x++) {
			for (int y = 0; y < grid.getGridLength(); y++) {
				if(grid.getGrid()[x][y] == true){
					g.setColor(Color.cyan);
					g.fillRect((int)(x * boxWidth), (int)(y * boxHeight), (int)(boxWidth), (int)(boxHeight));
				}
			}
		}
		
		//Draw Grid
		//Add 1 for the last line on the right
		for (int i = 0; i < grid.getGridLength() + 1; i++) {
			g.setColor(new Color(0,0,0));
			g.drawLine((int)(i * boxWidth), 0, (int)(i * boxWidth), this.getHeight());
			g.drawLine(0, (int)(i * boxHeight), this.getWidth(), (int)(i * boxHeight));
		}
	}
}
