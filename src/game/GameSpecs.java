/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.Board.COINNAME;


/**
 *
 * @author venkatramreddykunta
 */
public class GameSpecs {

        public static boolean isStalemate(COINNAME[][] _currentGameState){
        int count=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(_currentGameState[i][j]==COINNAME.A ||_currentGameState[i][j]==COINNAME.H)
                    count++;
            }            
        } 
        if(count==9 && !(isGameOver(_currentGameState, COINNAME.A) || isGameOver(_currentGameState, COINNAME.H))){
            return true;
        }
        return false;
    }
    public static  String whoWon(COINNAME[][] _currentGameState){        
        if(GameSpecs.isGameOver(_currentGameState,COINNAME.A)){
            return "Computer";
        }
        else if(GameSpecs.isGameOver(_currentGameState,COINNAME.H)){
            return "HUMAN";
        }
        return "No One";   
    }    
    public static boolean isGameOver(COINNAME[][] _currentGameState, COINNAME coin){
        if( (_currentGameState[0][0]==coin && _currentGameState[0][1]==coin && _currentGameState[0][2]==coin)
          ||(_currentGameState[1][0]==coin && _currentGameState[1][1]==coin && _currentGameState[1][2]==coin)
          ||(_currentGameState[2][0]==coin && _currentGameState[2][1]==coin && _currentGameState[2][2]==coin)
          ||(_currentGameState[0][0]==coin && _currentGameState[1][0]==coin && _currentGameState[2][0]==coin)
          ||(_currentGameState[0][2]==coin && _currentGameState[1][2]==coin && _currentGameState[2][2]==coin)
          ||(_currentGameState[0][1]==coin && _currentGameState[1][1]==coin && _currentGameState[2][1]==coin)                          
          ||(_currentGameState[0][0]==coin && _currentGameState[1][1]==coin && _currentGameState[2][2]==coin)
          ||(_currentGameState[0][2]==coin && _currentGameState[1][1]==coin && _currentGameState[2][0]==coin)
          )
          return true;
        else return false;
    }    
     
}
