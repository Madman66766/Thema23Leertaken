package ttt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
class TicTacToe
{
	private static final int HUMAN        = 0; 
	private static final int COMPUTER     = 1; 
	public  static final int EMPTY       =  2;

	public  static final int HUMAN_WIN    = 0;
	public  static final int DRAW         = 1;
	public  static final int UNCLEAR      = 2;
	public  static final int COMPUTER_WIN = 3;

	private int [ ] [ ] board = new int[ 3 ][ 3 ];
	private char [ ] [ ] showBoard = new char[ 3 ][ 3 ];
    private Random random=new Random();  
	private int side=random.nextInt(2);  
	private int position=UNCLEAR;
	private char computerChar,humanChar;
	private ArrayList<Integer> scores = new ArrayList<>();
	private ArrayList<Integer> moves = new ArrayList<>();

	// Constructor
	public TicTacToe( )
	{
		scores.clear();
		moves.clear();
		clearBoard( );
		initSide();
		initBoard();
	}
	
	private void initSide()
	{
	    if (this.side==COMPUTER) { computerChar='X'; humanChar='O'; }
		else                     { computerChar='O'; humanChar='X'; }
    }

	private void initBoard(){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				showBoard[i][j] = '.';
			}
		}
	}
    
    public void setComputerPlays()
    {
        this.side=COMPUTER;
        initSide();
    }
    
    public void setHumanPlays()
    {
        this.side=HUMAN;
        initSide();
    }

	public boolean computerPlays()
	{
	    return side==COMPUTER;
	}

	public int chooseMove()
	{
		scores.clear();
		moves.clear();
	    Best best=chooseMove(COMPUTER,0);
	    return best.row*3+best.column;
	    //return 0;
    }
    
    // Find optimal move
	private Best chooseMove( int side, int depth )
	{
		int opp;              // The other side
		Best reply = null;           // Opponent's best reply
		int simpleEval;       // Result of an immediate evaluation
		int bestRow = 0;
		int bestColumn = 0;
		int value;
		int score;

		if( ( simpleEval = positionValue( ) ) != UNCLEAR )
			return new Best( simpleEval );

		// TODO: implementeren m.b.v. recursie/backtracking

		if(side == COMPUTER){
			opp = HUMAN;
			value = HUMAN_WIN;
		}else{
			opp = COMPUTER;
			value = COMPUTER_WIN;
		}

		for (int i = 0;i < 9;i++){
			int row = i/3;
			int col = i%3;

			if(squareIsEmpty(row,col)) {
				place(row, col, side);
				if (isAWin(side)) {
					if (side == HUMAN) {
						//bestRow = row;
						//bestColumn = col;
						score = 10 - depth;
					} else {
						//bestRow = row;
						//bestColumn = col;
						score = depth - 10;
					}
					scores.add(score);
					moves.add(i);
					place(row, col, EMPTY);
					break;
				} else {
					reply = chooseMove(opp, depth + 1);
				}
				place(row, col, EMPTY);
			}
		}
		if(side == HUMAN){
			int maxIndex = 0;
			for (int i = 0; i < scores.size(); i++){
				int number = scores.get(i);
				if ((number > scores.get(maxIndex))){
					maxIndex = i;
				}
			}
			int finalMove = moves.get(maxIndex);
			bestRow = finalMove/3;
			bestColumn = finalMove%3;
			return new Best(value,bestRow,bestColumn);
		}else{
			int minIndex = 0;
			for (int i = 0; i < scores.size(); i++){
				int number = scores.get(i);
				if ((number < scores.get(minIndex))){
					minIndex = i;
				}
			}
			int finalMove = moves.get(minIndex);
			bestRow = finalMove/3;
			bestColumn = finalMove%3;
			return new Best(value,bestRow,bestColumn);
		}
    }

   
    //check if move ok
    public boolean moveOk(int move)
    {
		if(move>=0 && move <=8){
			if (board[move/3 ][ move%3 ] == EMPTY){
			//if (showBoard[move/3 ][ move%3 ] == EMPTY){
				return true;
			}
		}
 	return false;
    }
    
    // play move
    public void playMove(int move)
    {
		board[move/3][ move%3] = this.side;
		if (side==COMPUTER){
			showBoard[move/3][move%3] = computerChar;
			this.side=HUMAN;
		}
		else{
			showBoard[move/3][move%3] = humanChar;
			this.side=COMPUTER;
		}
	}


	// Simple supporting routines
	private void clearBoard( )
	{
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				board[i][j] = 2;
			}
		}
	}


	private boolean boardIsFull( )
	{
		for (int [] row: board) {
			for (int elem: row) {
				if(elem == EMPTY){
					return false;
				}
			}
		}
		return true;
	}

	// Returns whether 'side' has won in this position
	private boolean isAWin( int side )
	{
		int positionVal = positionValue();
		if (side == COMPUTER && positionVal == COMPUTER_WIN){return true;}
	    if (side == HUMAN && positionVal == HUMAN_WIN){return true;}
		return false;
    }

	// Play a move, possibly clearing a square
	private void place( int row, int column, int piece )
	{
		board[ row ][ column ] = piece;
	}

	private boolean squareIsEmpty( int row, int column )
	{
		return board[ row ][ column ] == EMPTY;
	}

	// Compute static value of current position (human-win, computer-win, draw, unclear)
	private int positionValue( )
	{
		String[] winningPatterns = {"012","345","678","036","147","258","048","246"};

		for (String elem:winningPatterns) {
			int sub1 = Integer.parseInt(elem.substring(0,1));
			int sub2 = Integer.parseInt(elem.substring(1,2));
			int sub3 = Integer.parseInt(elem.substring(2,3));

			int char1 = board[sub1/3][sub1%3];
			int char2 = board[sub2/3][sub2%3];
			int char3 = board[sub3/3][sub3%3];

			if(char1 == COMPUTER && char2 == COMPUTER && char3 == COMPUTER){
				return COMPUTER_WIN;
			}else if(char1 == HUMAN && char2 == HUMAN && char3 == HUMAN){
				return HUMAN_WIN;
			}
		}
		if(boardIsFull()){
			return DRAW;
		}
		return UNCLEAR;
	}
	
	
	public String toString()
	{
		StringBuilder show = new StringBuilder();
		for (char [] row: showBoard) {
			for (char elem: row) {
				show.append(elem);
			}
		}
		String finalBoard = show.toString();

		return finalBoard.substring(0,3)+"\n"+finalBoard.substring(3,6)+"\n"+finalBoard.substring(6,9)+"\n";
	}  
	
	public boolean gameOver()
	{
	    this.position=positionValue();
	    return this.position!=UNCLEAR;
    }
    
    public String winner()
    {
        if      (this.position==COMPUTER_WIN) return "computer";
        else if (this.position==HUMAN_WIN   ) return "human";
        else                                  return "nobody";
    }
    
	
	private class Best
    {
       int row;
       int column;
       int val;

       public Best( int v )
         { this( v, 0, 0 ); }
      
       public Best( int v, int r, int c )
        { val = v; row = r; column = c; }
    } 
	
	
}

