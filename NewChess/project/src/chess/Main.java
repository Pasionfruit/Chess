package chess;
import pieces.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

import java.util.ListIterator;

public class Main extends JFrame implements MouseListener
{
	private static final long serialVersionUID = 1L;
	
	private static final int Height = 800;
	private static final int Width = 1200;

	private static Rook wr01, wr02, br01, br02;
	private static Knight wk01, wk02, bk01, bk02;
	private static Bishop wb01, wb02, bb01, bb02;
	private static Pawn wp[], bp[];
	private static Queen wq, bq;
	private static King wk, bk;

	private Cell c, previous;
	private int chance = 0;
	private Cell boardState[][];
	private ArrayList<Cell> destinationlist = new ArrayList<Cell>();
	private Player White = null, Black = null;

	private JPanel board = new JPanel(new GridLayout(8,8));
	private JPanel wdetails = new JPanel(new GridLayout(3,3));
	private JPanel bdetails = new JPanel(new GridLayout(3,3));
	private JPanel wcombopanel = new JPanel();
	private JPanel bcombopanel = new JPanel();
	private JPanel controlPanel, WhitePlayer, BlackPlayer, temp, displayTime, showPlayer, time;
	private JSplitPane split;

	private JLabel label, mov;
	private static JLabel CHNC;

	private Time timer;
	private BackgroundMusic backgroundMusic = new BackgroundMusic("BackgroundMusic.WAV"), celebrationMusic = new BackgroundMusic("CelebrationMusic.WAV");

	public static Main Mainboard;

	private boolean selected = false,end=false;

	private Container content;

	private ArrayList<Player> wplayer,bplayer;
	private ArrayList<String> Wnames = new ArrayList<String>();
	private ArrayList<String> Bnames = new ArrayList<String>();

	private JComboBox<String> wcombo,bcombo;

	private String wname = null, bname = null, winner = null;
	static String move;

	private Player tempPlayer;

	private JScrollPane wscroll,bscroll;
	private String[] WNames = {}, BNames = {};
	private JSlider timeSlider;

	private BufferedImage image;
	private Button rules, start, wselect, bselect, WNewPlayer, BNewPlayer;
	public static int timeRemaining = 60;
	public static void main(String[] args){
	
	//variable initialization
	wr01=new Rook("WR01","White_Rook.png",0);
	wr02=new Rook("WR02","White_Rook.png",0);
	br01=new Rook("BR01","Black_Rook.png",1);
	br02=new Rook("BR02","Black_Rook.png",1);
	wk01=new Knight("WK01","White_Knight.png",0);
	wk02=new Knight("WK02","White_Knight.png",0);
	bk01=new Knight("BK01","Black_Knight.png",1);
	bk02=new Knight("BK02","Black_Knight.png",1);
	wb01=new Bishop("WB01","White_Bishop.png",0);
	wb02=new Bishop("WB02","White_Bishop.png",0);
	bb01=new Bishop("BB01","Black_Bishop.png",1);
	bb02=new Bishop("BB02","Black_Bishop.png",1);
	wq=new Queen("WQ","White_Queen.png",0);
	bq=new Queen("BQ","Black_Queen.png",1);
	wk=new King("WK","White_King.png",0,7,3);
	bk=new King("BK","Black_King.png",1,0,3);
	wp=new Pawn[8];
	bp=new Pawn[8];
	for(int i=0;i<8;i++)
	{
		wp[i]=new Pawn("WP0"+(i+1),"White_Pawn.png",0);
		bp[i]=new Pawn("BP0"+(i+1),"Black_Pawn.png",1);
	}
	
	//Setting up the board
	Mainboard = new Main();
	Mainboard.setVisible(true);	
	Mainboard.setResizable(true);
	}
	
	//Constructor
	private Main()
    {
		timeRemaining=60;
		timeSlider = new JSlider();
		move="White";
		wname=null;
		bname=null;
		winner=null;
		board=new JPanel(new GridLayout(8,8));
		wdetails=new JPanel(new GridLayout(3,3));
		bdetails=new JPanel(new GridLayout(3,3));
		bcombopanel=new JPanel();
		wcombopanel=new JPanel();
		Wnames=new ArrayList<String>();
		Bnames=new ArrayList<String>();
		board.setMinimumSize(new Dimension(800,700));
		//ImageIcon img = new ImageIcon(this.getClass().getResource("icon.png"));
		//this.setIconImage(img.getImage());
		
		//Time Slider Details
		timeSlider.setMinimum(1);
		timeSlider.setMaximum(10);
		timeSlider.setValue(1);
		timeSlider.setMajorTickSpacing(1);
		timeSlider.setPaintLabels(true);
		timeSlider.setPaintTicks(true);
		timeSlider.addChangeListener(new TimeChange());
		
		//Fetching Details of all Players
		wplayer= Player.fetch_players();
		Iterator<Player> witr=wplayer.iterator();
		while(witr.hasNext())
			Wnames.add(witr.next().name());
				
		bplayer= Player.fetch_players();
		Iterator<Player> bitr=bplayer.iterator();
		while(bitr.hasNext())
			Bnames.add(bitr.next().name());
	    WNames=Wnames.toArray(WNames);	
		BNames=Bnames.toArray(BNames);
		
		Cell cell;
		board.setBorder(BorderFactory.createLoweredBevelBorder());
		pieces.Piece P;
		content = getContentPane();
		setSize (Width, Height);
		setTitle ("Chess");
		content.setBackground(Color.black);
		controlPanel = new JPanel();
		content.setLayout(new BorderLayout());
		controlPanel.setLayout(new GridLayout(3,3));
		controlPanel.setBorder(BorderFactory.createTitledBorder(null, "STATS", TitledBorder.TOP,TitledBorder.CENTER, new Font("Arial",Font.BOLD,30)));
		
		//Defining the Player Box in Control Panel
		WhitePlayer=new JPanel();
		WhitePlayer.setBorder(BorderFactory.createTitledBorder(null, "PLAYER ONE (WHITE)", TitledBorder.TOP,TitledBorder.CENTER, new Font("Arial",Font.BOLD,18)));
		WhitePlayer.setLayout(new BorderLayout());
		
		BlackPlayer=new JPanel();
		BlackPlayer.setBorder(BorderFactory.createTitledBorder(null, "PLAYER TWO (BLACK)", TitledBorder.TOP,TitledBorder.CENTER, new Font("Arial",Font.BOLD,18)));
	    BlackPlayer.setLayout(new BorderLayout());
		
	    JPanel whitestats=new JPanel(new GridLayout(3,3));
		JPanel blackstats=new JPanel(new GridLayout(3,3));
		wcombo=new JComboBox<String>(WNames);
		bcombo=new JComboBox<String>(BNames);
		wscroll=new JScrollPane(wcombo);
		bscroll=new JScrollPane(bcombo);
		wcombopanel.setLayout(new FlowLayout());
		bcombopanel.setLayout(new FlowLayout());
		wselect=new Button("Select");
		bselect=new Button("Select");
		wselect.addActionListener(new SelectHandler(0));
		bselect.addActionListener(new SelectHandler(1));
		WNewPlayer=new Button("New Player");
		BNewPlayer=new Button("New Player");
		WNewPlayer.addActionListener(new Handler(0));
		BNewPlayer.addActionListener(new Handler(1));
		wcombopanel.add(wscroll);
		wcombopanel.add(wselect);
		wcombopanel.add(WNewPlayer);
		bcombopanel.add(bscroll);
		bcombopanel.add(bselect);
		bcombopanel.add(BNewPlayer);
		WhitePlayer.add(wcombopanel,BorderLayout.NORTH);
		BlackPlayer.add(bcombopanel,BorderLayout.NORTH);
		whitestats.add(new JLabel("Player Name    :"));
		whitestats.add(new JLabel("# Games Played :"));
		whitestats.add(new JLabel("# Games Won    :"));
		blackstats.add(new JLabel("Player Name   :"));
		blackstats.add(new JLabel("# Games Played :"));
		blackstats.add(new JLabel("# Games Won    :"));
		WhitePlayer.add(whitestats,BorderLayout.WEST);
		BlackPlayer.add(blackstats,BorderLayout.WEST);
		controlPanel.add(WhitePlayer);
		controlPanel.add(BlackPlayer);
		
		//Defining all the Cells
		boardState=new Cell[8][8];
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
			{	
				P=null;
				if(i==0&&j==0)
					P=br01;
				else if(i==0&&j==7)
					P=br02;
				else if(i==7&&j==0)
					P=wr01;
				else if(i==7&&j==7)
					P=wr02;
				else if(i==0&&j==1)
					P=bk01;
				else if (i==0&&j==6)
					P=bk02;
				else if(i==7&&j==1)
					P=wk01;
				else if (i==7&&j==6)
					P=wk02;
				else if(i==0&&j==2)
					P=bb01;
				else if (i==0&&j==5)
					P=bb02;
				else if(i==7&&j==2)
					P=wb01;
				else if(i==7&&j==5)
					P=wb02;
				else if(i==0&&j==3)
					P=bk;
				else if(i==0&&j==4)
					P=bq;
				else if(i==7&&j==3)
					P=wk;
				else if(i==7&&j==4)
					P=wq;
				else if(i==1)
				P=bp[j];
				else if(i==6)
					P=wp[j];
				cell=new Cell(i,j,P);
				cell.addMouseListener(this);
				board.add(cell);
				boardState[i][j]=cell;
			}
		showPlayer = new JPanel(new FlowLayout());  
		showPlayer.add(timeSlider);
		JLabel setTime = new JLabel("SET TIMER (MIN):"); 

		start = new Button("START");
		start.setBackground(new Color(221, 221, 221));
        start.setForeground(new Color(0, 0, 0));
	    start.addActionListener(new START());
		start.setFocusable(false);
        start.setMaximumSize(null);
		start.setMinimumSize(new Dimension(120, 40));
        start.setPreferredSize(new Dimension(120, 40));

		rules = new Button("RULES");
		rules.setFont(new Font("Arial",Font.PLAIN,12));
		rules.setBackground(new Color(221, 221, 221));
		rules.setForeground(new Color(0, 0, 0));
		rules.setFocusable(false);
		start.setMaximumSize(null);
		rules.setMinimumSize(new Dimension(120, 40));
        rules.setPreferredSize(new Dimension(120, 40));

		rules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rulesButtonActionPerformed(evt);
			}
		});

		setTime.setFont(new Font("Arial",Font.BOLD,16));
		label = new JLabel("Time Starts now", JLabel.CENTER);
		  label.setFont(new Font("Arial", Font.BOLD, 30));
	      displayTime=new JPanel(new FlowLayout());
	      time=new JPanel(new GridLayout(4,3));
	      time.add(setTime);
	      time.add(showPlayer);
	      displayTime.add(start);
		  displayTime.add(rules);
	      time.add(displayTime);
	      controlPanel.add(time);
		board.setMinimumSize(new Dimension(800,700));
	
		temp=new JPanel(){
			private static final long serialVersionUID = 1L;
			     
			@Override
		    public void paintComponent(Graphics g) {
				  try {
			          image = ImageIO.read(this.getClass().getResource("frontpage.jpg"));
			       } catch (IOException ex) {
			            System.out.println("not found");
			       }
			   
				g.drawImage(image, 0, 0, null);
			}         
	    };

		try {
			backgroundMusic.play();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		
		temp.setMinimumSize(new Dimension(800,700));
		controlPanel.setMinimumSize(new Dimension(285,700));
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, controlPanel, temp);
		
	    content.add(split);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
	private void rulesButtonActionPerformed(ActionEvent evt) {
		JFrame rulesFrame = new JFrame("Chess Rules");
		rulesFrame.setSize(500, 300);
		rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		JTextArea textArea = new JTextArea();
		textArea.setText("The Rules:\n\n How to move the pieces \n King - Moves and captures in one space in any direction, if being threaten to be captured, must be protected with other pieces or moved to safety \n Queen - Moves or captures diagonally and in a straight Line \n Rook - Moves and captures in a straight line, up, or down \n Bishop - Moves or captures diagonally \n Knight - Moves and captures in an L-shape (two square in one direction and one in an adjacent side) \nPawn - May move two squares forward initally but after moving can only move forward one space and capture diagonally forward \n\n How to win \n CheckMate the opposing king by forcing his position where he can not move to a safe square and there is not a piece able to defend the King \n\n Special Features: \n Background Music, Recording of players stats (# of Games and # of wins), Visible points of possible moves, Timer - when the clock runs out the player loses a turn, and indicator of unsafe King. \n\n Unsupported typical features: \n En passant, Castling, and Pawn Promotion.");
		textArea.setEditable(false);
	
		JScrollPane scrollPane = new JScrollPane(textArea);
	
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rulesFrame.dispose(); 
			}
		});
	
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(goBackButton, BorderLayout.SOUTH);
	
		rulesFrame.add(panel);
	
		rulesFrame.setLocationRelativeTo(null);
		rulesFrame.setVisible(true);
	}
	
	public void changechance()
	{
		if (boardState[getKing(chance).getx()][getKing(chance).gety()].ischeck())
		{
			chance^=1;
			gameend();
		}
		if(destinationlist.isEmpty()==false)
			cleandestinations(destinationlist);
		if(previous!=null)
			previous.deselect();
		previous=null;
		chance^=1;
		if(!end && timer!=null)
		{
			timer.reset();
			timer.start();
			showPlayer.remove(CHNC);
			if(Main.move=="BLACK")
				Main.move="WHITE";
			else
				Main.move="BLACK";
			CHNC.setText(Main.move);
			showPlayer.add(CHNC);
		}
	}
	
	//A function to retrieve the Black King or White King
	private King getKing(int color)
	{
		if (color==0)
			return wk;
		else
			return bk;
	}
	
	//A function to clean the highlights of possible destination cells
    private void cleandestinations(ArrayList<Cell> destlist)      //Function to clear the last move's destinations
    {
    	ListIterator<Cell> it = destlist.listIterator();
    	while(it.hasNext())
    		it.next().removepossibledestination();
    }
    
    //A function that indicates the possible moves by highlighting the Cells
    private void highlightdestinations(ArrayList<Cell> destlist)
    {
    	ListIterator<Cell> it = destlist.listIterator();
    	while(it.hasNext())
    		it.next().setpossibledestination();
    }
    
  	//Function to check if the king will be in danger if the given move is made
    private boolean willkingbeindanger(Cell fromcell,Cell tocell)
    {
    	Cell newboardstate[][] = new Cell[8][8];
    	for(int i=0;i<8;i++)
    		for(int j=0;j<8;j++)
    		{	try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace(); System.out.println("There is a problem with cloning !!"); }}
    	
    	if(newboardstate[tocell.x][tocell.y].getpiece()!=null)
			newboardstate[tocell.x][tocell.y].removePiece();
    	
		newboardstate[tocell.x][tocell.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
		if(newboardstate[tocell.x][tocell.y].getpiece() instanceof King)
		{
			((King)(newboardstate[tocell.x][tocell.y].getpiece())).setx(tocell.x);
			((King)(newboardstate[tocell.x][tocell.y].getpiece())).sety(tocell.y);
		}
		newboardstate[fromcell.x][fromcell.y].removePiece();
		if (((King)(newboardstate[getKing(chance).getx()][getKing(chance).gety()].getpiece())).isindanger(newboardstate)==true)
			return true;
		else
			return false;
    }
    
    //A function to eliminate the possible moves that will put the King in danger
    private ArrayList<Cell> filterdestination (ArrayList<Cell> destlist, Cell fromcell)
    {
    	ArrayList<Cell> newlist = new ArrayList<Cell>();
    	Cell newboardstate[][] = new Cell[8][8];
    	ListIterator<Cell> it = destlist.listIterator();
    	int x,y;
    	while (it.hasNext())
    	{
    		for(int i=0;i<8;i++)
        		for(int j=0;j<8;j++)
        		{	try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}
    		
    		Cell tempc = it.next();
    		if(newboardstate[tempc.x][tempc.y].getpiece()!=null)
    			newboardstate[tempc.x][tempc.y].removePiece();
    		newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
    		x=getKing(chance).getx();
    		y=getKing(chance).gety();
    		if(newboardstate[fromcell.x][fromcell.y].getpiece() instanceof King)
    		{
    			((King)(newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
    			((King)(newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
    			x=tempc.x;
    			y=tempc.y;
    		}
    		newboardstate[fromcell.x][fromcell.y].removePiece();
    		if ((((King)(newboardstate[x][y].getpiece())).isindanger(newboardstate)==false))
    			newlist.add(tempc);
    	}
    	return newlist;
    }
    
    //A Function to filter the possible moves when the king of the current player is under Check 
    private ArrayList<Cell> incheckfilter (ArrayList<Cell> destlist, Cell fromcell, int color)
    {
    	ArrayList<Cell> newlist = new ArrayList<Cell>();
    	Cell newboardstate[][] = new Cell[8][8];
    	ListIterator<Cell> it = destlist.listIterator();
    	int x,y;
    	while (it.hasNext())
    	{
    		for(int i=0;i<8;i++)
        		for(int j=0;j<8;j++)
        		{	try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}
    		Cell tempc = it.next();
    		if(newboardstate[tempc.x][tempc.y].getpiece()!=null)
    			newboardstate[tempc.x][tempc.y].removePiece();
    		newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
    		x=getKing(color).getx();
    		y=getKing(color).gety();
    		if(newboardstate[tempc.x][tempc.y].getpiece() instanceof King)
    		{
    			((King)(newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
    			((King)(newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
    			x=tempc.x;
    			y=tempc.y;
    		}
    		newboardstate[fromcell.x][fromcell.y].removePiece();
    		if ((((King)(newboardstate[x][y].getpiece())).isindanger(newboardstate)==false))
    			newlist.add(tempc);
    	}
    	return newlist;
    }
    
    //A function to check if the King is check-mate. The Game Ends if this function returns true.
    public boolean checkmate(int color)
    {
    	ArrayList<Cell> dlist = new ArrayList<Cell>();
    	for(int i=0;i<8;i++)
    	{
    		for(int j=0;j<8;j++)
    		{
    			if (boardState[i][j].getpiece()!=null && boardState[i][j].getpiece().getcolor()==color)
    			{
    				dlist.clear();
    				dlist=boardState[i][j].getpiece().move(boardState, i, j);
    				dlist=incheckfilter(dlist,boardState[i][j],color);
    				if(dlist.size()!=0)
    					return false;
    			}
    		}
    	}
    	return true;
    }
	  
    @SuppressWarnings("deprecation")
	private void gameend()
    {
    	cleandestinations(destinationlist);
    	displayTime.disable();
    	timer.countdownTimer.stop();
		backgroundMusic.stop();
		celebrationMusic.play();
    	if(previous!=null)
    		previous.removePiece();
    	if(chance==0)
		{	White.updateGamesWon();
			White.Update_Player();
			winner=White.name();
		}
		else
		{
			Black.updateGamesWon();
			Black.Update_Player();
			winner=Black.name();
		}
		JOptionPane.showMessageDialog(board,"Checkmate!!!\n"+winner+" wins");
		
		WhitePlayer.remove(wdetails);
		BlackPlayer.remove(bdetails);
		displayTime.remove(label);
		
		displayTime.add(start);
		showPlayer.remove(mov);
		showPlayer.remove(CHNC);
		showPlayer.revalidate();
		showPlayer.add(timeSlider);
		
		split.remove(board);
		split.add(temp);
		WNewPlayer.enable();
		BNewPlayer.enable();
		wselect.enable();
		bselect.enable();
		end=true;
		Mainboard.disable();
		Mainboard.dispose();
		Mainboard = new Main();
		Mainboard.setVisible(true);
		Mainboard.setResizable(false);
		celebrationMusic.stop();
    }

	@Override
	public void mouseClicked(MouseEvent arg0){
		c=(Cell)arg0.getSource();
		if (previous==null)
		{
			if(c.getpiece()!=null)
			{
				if(c.getpiece().getcolor()!=chance)
					return;
				c.select();
				previous=c;
				destinationlist.clear();
				destinationlist=c.getpiece().move(boardState, c.x, c.y);
				if(c.getpiece() instanceof King)
					destinationlist=filterdestination(destinationlist,c);
				else
				{
					if(boardState[getKing(chance).getx()][getKing(chance).gety()].ischeck())
						destinationlist = new ArrayList<Cell>(filterdestination(destinationlist,c));
					else if(destinationlist.isEmpty()==false && willkingbeindanger(c,destinationlist.get(0)))
						destinationlist.clear();
				}
				highlightdestinations(destinationlist);
			}
		}
		else
		{
			if(c.x==previous.x && c.y==previous.y)
			{
				c.deselect();
				cleandestinations(destinationlist);
				destinationlist.clear();
				previous=null;
			}
			else if(c.getpiece()==null||previous.getpiece().getcolor()!=c.getpiece().getcolor())
			{
				if(c.ispossibledestination())
				{
					if(c.getpiece()!=null)
						c.removePiece();
					c.setPiece(previous.getpiece());
					if (previous.ischeck())
						previous.removecheck();
					previous.removePiece();
					if(getKing(chance^1).isindanger(boardState))
					{
						boardState[getKing(chance^1).getx()][getKing(chance^1).gety()].setcheck();
						if (checkmate(getKing(chance^1).getcolor()))
						{
							previous.deselect();
							if(previous.getpiece()!=null)
								previous.removePiece();
							gameend();
						}
					}
					if(getKing(chance).isindanger(boardState)==false)
						boardState[getKing(chance).getx()][getKing(chance).gety()].removecheck();
					if(c.getpiece() instanceof King)
					{
						((King)c.getpiece()).setx(c.x);
						((King)c.getpiece()).sety(c.y);
					}
					changechance();
					if(!end)
					{
						timer.reset();
						timer.start();
					}
				}
				if(previous!=null)
				{
					previous.deselect();
					previous=null;
				}
				cleandestinations(destinationlist);
				destinationlist.clear();
			}
			else if(previous.getpiece().getcolor()==c.getpiece().getcolor())
			{
				previous.deselect();
				cleandestinations(destinationlist);
				destinationlist.clear();
				c.select();
				previous=c;
				destinationlist=c.getpiece().move(boardState, c.x, c.y);
				if(c.getpiece() instanceof King)
					destinationlist=filterdestination(destinationlist,c);
				else
				{
					if(boardState[getKing(chance).getx()][getKing(chance).gety()].ischeck())
						destinationlist = new ArrayList<Cell>(filterdestination(destinationlist,c));
					else if(destinationlist.isEmpty()==false && willkingbeindanger(c,destinationlist.get(0)))
						destinationlist.clear();
				}
				highlightdestinations(destinationlist);
			}
		}
		if(c.getpiece()!=null && c.getpiece() instanceof King)
		{
			((King)c.getpiece()).setx(c.x);
			((King)c.getpiece()).sety(c.y);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {	
	}
	
	class START implements ActionListener {

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent arg0) {
		
		if(White==null||Black==null)
			{JOptionPane.showMessageDialog(controlPanel, "Please enter a name");
			return;}
		White.updateGamesPlayed();
		White.Update_Player();
		Black.updateGamesPlayed();
		Black.Update_Player();
		WNewPlayer.disable();
		BNewPlayer.disable();
		wselect.disable();
		bselect.disable();
		split.remove(temp);
		split.add(board);
		showPlayer.remove(timeSlider);
		mov=new JLabel("TURN:");
		mov.setFont(new Font("Arial",Font.BOLD,20));
		mov.setForeground(Color.RED);
		showPlayer.add(mov);
		CHNC=new JLabel(move);
		CHNC.setFont(new Font("Arial",Font.BOLD,20));
		CHNC.setForeground(Color.RED);
		showPlayer.add(CHNC);
		displayTime.remove(start);
		displayTime.add(label);
		timer=new Time(label);
		timer.start();
		}
	}
	
	class TimeChange implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent arg0)
		{
			timeRemaining=timeSlider.getValue()*60;
		}
	}
	
	
	class SelectHandler implements ActionListener {
		private int color;
		
		SelectHandler(int i)
		{
			color=i;
		}
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				tempPlayer=null;
				String n=(color==0)?wname:bname;
				JComboBox<String> jc=(color==0)?wcombo:bcombo;
				JComboBox<String> ojc=(color==0)?bcombo:wcombo;
				ArrayList<Player> pl=(color==0)?wplayer:bplayer;
				ArrayList<Player> opl=Player.fetch_players();
				if(opl.isEmpty())
					return;
				JPanel det=(color==0)?wdetails:bdetails;
				JPanel PL=(color==0)?WhitePlayer:BlackPlayer; 
				if(selected==true)
					det.removeAll();
				n=(String)jc.getSelectedItem();
				Iterator<Player> it=pl.iterator();
				Iterator<Player> oit=opl.iterator();
				while(it.hasNext())
				{	
					Player p=it.next();
					if(p.name().equals(n))
						{tempPlayer=p;
						break;}
				}
				while(oit.hasNext())
				{	
					Player p=oit.next();
					if(p.name().equals(n))
						{opl.remove(p);
						break;}
				}
				
				if(tempPlayer==null)
					return;
				if(color==0)
					White=tempPlayer;
				else
					Black=tempPlayer;
				bplayer=opl;
				ojc.removeAllItems();
				for (Player s:opl)
					ojc.addItem(s.name());
				det.add(new JLabel(" "+tempPlayer.name()));
				det.add(new JLabel(" "+tempPlayer.gamesplayed()));
				det.add(new JLabel(" "+tempPlayer.gameswon()));
				
				PL.revalidate();
				PL.repaint();
				PL.add(det);
				selected=true;
			}
			
		}
		
	class Handler implements ActionListener{
		private int color;
		Handler(int i)
		{
			color=i;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String n=(color==0)?wname:bname;
			JPanel j=(color==0)?WhitePlayer:BlackPlayer;
			ArrayList<Player> N=Player.fetch_players();
			Iterator<Player> it=N.iterator();
			JPanel det=(color==0)?wdetails:bdetails;
			n=JOptionPane.showInputDialog(j,"Enter your name");
				
				if(n!=null)
				{
				
				while(it.hasNext())
				{
					if(it.next().name().equals(n))
					{JOptionPane.showMessageDialog(j,"Player exists");
					return;}
				}
		
					if(n.length()!=0)
					{Player tem=new Player(n);
					tem.Update_Player();
					if(color==0)
						White=tem;
					else
						Black=tem;
					}
					else return;
				}
			else
				return;
			det.removeAll();
			det.add(new JLabel(" "+n));
			det.add(new JLabel(" 0"));
			det.add(new JLabel(" 0"));
			j.revalidate();
			j.repaint();
			j.add(det);
			selected=true;
		}
	}	 
}