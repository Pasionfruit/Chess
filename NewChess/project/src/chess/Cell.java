package chess;

import java.awt.*;
import javax.swing.*;
import pieces.*;

public class Cell extends JPanel implements Cloneable{
	
	//Member Variables
	private static final long serialVersionUID = 1L;
	private boolean ispossibledestination;
	private JLabel content;
	private Piece piece;
	int x,y;                           
	private boolean isSelected=false;
	private boolean ischeck=false;
	
	public Cell(int x,int y,Piece p)
	{		
		this.x=x;
		this.y=y;
		
		setLayout(new BorderLayout());
	
	 if((x+y)%2==0)
	  setBackground(new Color(92, 47, 10));
	
	 else
	  setBackground(new Color(255, 242, 214));
	 
	 if(p!=null)
		 setPiece(p);
	}
	
	// A constructor that takes a cell as argument and returns a new cell will the same data but different reference
	public Cell(Cell cell) throws CloneNotSupportedException
	{
		this.x=cell.x;
		this.y=cell.y;
		setLayout(new BorderLayout());
		if((x+y)%2==0)
			setBackground(new Color(92, 47, 10));
		else
			setBackground(Color.white);
		if(cell.getpiece()!=null)
		{
			setPiece(cell.getpiece().getcopy());
		}
		else
			piece=null;
	}
	
	public void setPiece(Piece p)    // Inflate a cell with a piece
	{
		piece=p;
		ImageIcon img=new javax.swing.ImageIcon(this.getClass().getResource(p.getPath()));
		content=new JLabel(img);
		this.add(content);
	}
	
	public void removePiece() // Remove a piece from the cell
	{
		if (piece instanceof King)
		{
			piece=null;
			this.remove(content);
		}
		else
		{
			piece=null;
			this.remove(content);
		}
	}
	
	public Piece getpiece()    // Access piece of a particular cell
	{
		return this.piece;
	}
	
	public void select()    // Mark a cell indicating it's selection
	{
		this.setBorder(BorderFactory.createLineBorder(Color.yellow,8));
		this.isSelected=true;
	}
	
	public boolean isselected() // Return if the cell is under selection
	{
		return this.isSelected;
	}
	
	public void deselect() // Delselect the cell
	{
		this.setBorder(null);
		this.isSelected=false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int cellSize = getWidth(); 
	
		if (ispossibledestination) {
			Graphics2D g2d = (Graphics2D) g;
			
			// Set the white color for the outline
			g2d.setColor(Color.WHITE);
			
			// Draw the white outline of the oval
			g2d.setStroke(new BasicStroke(4)); // Adjust the thickness of the outline as needed
			g2d.drawOval(cellSize / 4, cellSize / 4, cellSize / 2, cellSize / 2);
			
			// Set the color for the filled oval
			g2d.setColor(new Color(128, 0, 32)); // Change to your desired filled oval color
			
			// Fill the oval
			g2d.fillOval(cellSize / 4, cellSize / 4, cellSize / 2, cellSize / 2);
		}
	}
	
	public void setpossibledestination()     // Function to highlight a cell to indicate that it is a possible valid move
	{
		this.ispossibledestination = true;
		repaint();
	}
	
	public void removepossibledestination()      // Remove the cell from the list of possible moves
	{
		this.setBorder(null);
		this.ispossibledestination=false;
		repaint();
	}
	
	public boolean ispossibledestination()    // Check if the cell is a possible destination 
	{
		return this.ispossibledestination;
	}
	
	public void setcheck()     // Highlight the current cell as checked (For King)
	{
		this.setBackground(Color.RED);
		this.ischeck=true;
	}
	
	public void removecheck()   // Deselect check
	{
		this.setBorder(null);
		if((x+y)%2==0)
			setBackground(new Color(92, 47, 10));
		else
			setBackground(new Color(255, 242, 214));
		this.ischeck=false;
	}
	
	public boolean ischeck()    // Check if the current cell is in check
	{
		return ischeck;
	}
}