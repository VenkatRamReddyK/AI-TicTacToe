/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;
import game.Board.*;
/**
 *
 * @author venkatramreddykunta
 */
public class Player {
    
 
    PLAYERTYPE currentPlayer;
    COINNAME currentCoin;
    public Player() {
        currentPlayer=PLAYERTYPE.NONE;
        currentCoin=COINNAME.N;
    }
    public PLAYERTYPE whichPlayer(){
        return this.currentPlayer;
    }
    public COINNAME whichCoin(){
        return this.currentCoin;
    }
    public int getFirstMove(COINNAME[][] currentGameState){
        // if First move is AI return -1 else return the index to which Human Moved
        int humanIndex=-1;
        outer:for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(currentGameState[i][j]==COINNAME.H){                    
                    humanIndex=3*i+j;
                    break outer;
                }
            }
        }
        return humanIndex;
        
    }
    public int move(COINNAME[][] currentGameState){
        //System.out.println(currentPlayer+"'s move"); 
        // first Move
            int nextMoveIndex=1;
            int humanMoveIndex=getFirstMove(currentGameState);
//            List<Integer> nextPossibleMoves=aiEngine.getUnCoveredMoves(currentGameState);
            // get human index 
            if(humanMoveIndex==-1){ 
                //First move is AI 
                
            }else{
                //First Move is Human
                do{
                    nextMoveIndex=(int)(Math.random()*9);
                    
                }while(nextMoveIndex==humanMoveIndex);
            }
            System.out.println("First Move: using Player Random:"+nextMoveIndex);            
            return nextMoveIndex;        
    }
}
