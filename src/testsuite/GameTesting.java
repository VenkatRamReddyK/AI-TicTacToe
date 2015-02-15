/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsuite;

import game.Board;
import players.*;

/**
 *
 * @author venkatramreddykunta
 */
public class GameTesting {
    
    public static void main(String[] args) {        
        //testInhertance();       
        int size=3;
        Board board=new Board(size);
        board.startGame();
    }
}
