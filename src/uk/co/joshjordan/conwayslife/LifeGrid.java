package uk.co.joshjordan.conwayslife;

public class LifeGrid {


	private boolean[][] grid;
	
	public LifeGrid(int gridWidth, int gridHeight){
		grid = new boolean[gridWidth][gridHeight];
	}
	
	public void setValue(int row, int column){
		grid[row][column] = !grid[row][column];
	}
	
	public void setGrid(boolean[][] newGrid){
		grid = newGrid;
	}
	
	public boolean[][] getGrid(){
		return this.grid;
	}
	
	public int getGridLength(){
		return grid.length;
	}
}
