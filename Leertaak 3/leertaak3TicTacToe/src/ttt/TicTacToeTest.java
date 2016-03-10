package ttt;

import junit.framework.TestCase;
import ttt.*;

/**
 * Created by Serkan on 7-3-2016.
 */
public class TicTacToeTest extends TestCase {

    private TicTacToe game;

    public TicTacToeTest(String arg){
        super(arg);
        game = new TicTacToe();
    }


    public void testPositionValue(){
    // assertTrue win, draw, lose
        game.setHumanPlays();
        game.playMove(0);
        game.setHumanPlays();
        game.playMove(1);
        game.setHumanPlays();
        game.playMove(2);
        if(game.gameOver()){
            assertEquals(game.winner(),"human");
            //assertEquals(game.winner(),"human");
        }
        game = new TicTacToe();
        game.setHumanPlays();
        game.playMove(0);
        game.playMove(1);
        game.playMove(4);
        game.playMove(2);
        game.playMove(6);
        game.playMove(3);
        game.playMove(7);
        game.playMove(8);
        game.playMove(5);
        if (game.gameOver()){
            assertEquals(game.winner(),"nobody");
        }

    }

    public void testChooseMove(){
        game = new TicTacToe();
        game.setHumanPlays();
        game.playMove(0);
        game.playMove(1);
        game.playMove(4);
        game.playMove(2);
        game.playMove(6);
        game.playMove(3);
        game.playMove(7);
        game.chooseMove();
        if(game.gameOver()){
            assertEquals(game.winner(),"computer");
        }

    }
}
