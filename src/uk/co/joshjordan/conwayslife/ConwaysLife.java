package uk.co.joshjordan.conwayslife;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class ConwaysLife implements MouseListener, ActionListener, Runnable {

	boolean running = false;
	
	final int FRAME_WIDTH = 800;
	final int FRAME_HEIGHT = 800;
	
	final int GRID_WIDTH = 40;
	final int GRID_HEIGHT = 40;
	
	LifeGrid grid = new LifeGrid(GRID_WIDTH, GRID_HEIGHT);
	
	JFrame frame = new JFrame("Conway's Life");
	LifePanel panel = new LifePanel(grid);
	
	JButton step = new JButton("Step");
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	
	Container buttonContainer = new Container();
	
	public ConwaysLife(){
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.setLocation(400, 200);
		
		buttonContainer.setLayout(new GridLayout(1, 3));
		buttonContainer.add(step);		
		buttonContainer.add(start);
		buttonContainer.add(stop);
		
		step.addActionListener(this);
		start.addActionListener(this);
		stop.addActionListener(this);
		
		frame.add(buttonContainer, BorderLayout.SOUTH);
		
		panel.addMouseListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		//System.out.println(event.getX()  + "," + event.getY() );
		int row 	= Math.min(event.getX() / (panel.getWidth() / GRID_WIDTH), GRID_WIDTH - 1);
		int column 	= Math.min(event.getY() / (panel.getHeight() / GRID_HEIGHT), GRID_HEIGHT - 1);
		System.out.println(row + "," + column);
		grid.setValue(row, column);
		frame.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(step) == true){
			if(running == false){
				step();
				frame.repaint();			
			}
		}
		if(e.getSource().equals(start) == true){
			if(running == false){
				running = true;
				Thread t = new Thread(this);
				t.start();			
			}
		}
		if(e.getSource().equals(stop) == true){
			running = false;
		}
	}
	
	@Override
	public void run() {
		 
		while(running){
			step();
			frame.repaint();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void step(){
		
		boolean[][] newGrid = new boolean[GRID_WIDTH][GRID_HEIGHT];
		
		for (int x = 0; x < grid.getGridLength(); x++) {
			for (int y = 0; y < grid.getGridLength(); y++) {
				
				int neighbourCount = 0;
				//Above
				if(y > 0 
						&& grid.getGrid()[x][y - 1] == true){
					neighbourCount++;
				}
				//Below
				if(y < grid.getGridLength() - 1 
						&& grid.getGrid()[x][y + 1] == true){
					neighbourCount++;
				}
				//To the left
				if(x > 0 
						&& grid.getGrid()[x - 1][y] == true){
					neighbourCount++;
				}
				//To the right
				if(x < grid.getGridLength() - 1 
						&& grid.getGrid()[x + 1][y] == true){
					neighbourCount++;
				}				
				//Down and Right
				if(y < grid.getGridLength() - 1 && 
						x < grid.getGridLength() - 1 &&
						grid.getGrid()[x + 1 ][y + 1] == true){
					neighbourCount++;
				}
				//Down and Left
				if(y < grid.getGridLength() - 1 && 
						x > 0 &&
						grid.getGrid()[x - 1 ][y + 1] == true){
					neighbourCount++;
				}				
				//Up and Right
				if(y > 0 && 
						x < grid.getGridLength() - 1 &&
						grid.getGrid()[x + 1 ][y - 1] == true){
					neighbourCount++;
				}				
				//Up and Left
				if(y > 0 && 
						x > 0 &&
						grid.getGrid()[x - 1 ][y - 1] == true){
					neighbourCount++;
				}
				
				
				//Conways Life Rules
				if(grid.getGrid()[x][y] == true){
					//If its alive
					if(neighbourCount == 2 || neighbourCount == 3){ //if it has 2 or 3 neighbours
						newGrid[x][y] = true;
					}else{
						newGrid[x][y] = false;
					}
				}else{
					//if it is dead and it has 3 neighbours, back to life
					if(neighbourCount == 3){
						newGrid[x][y] = true;
					}
				}
			}
		}
		grid.setGrid(newGrid);
		
	}



	public static void main(String[] args){
		new ConwaysLife();
	}
}
