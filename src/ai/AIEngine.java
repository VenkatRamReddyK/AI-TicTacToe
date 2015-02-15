/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;
import game.*;
import game.Board.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author venkatramreddykunta
 */
public class AIEngine {

//    PLAYERTYPE currentAiTurn;
    COINNAME[][] currentGameState;
    Score[] allScores;             
    List<Integer> nextPossibleMoveIndices;
    
    public AIEngine() {        
        
    }
    
    public List<Integer> getUnCoveredMoves(COINNAME[][] _currentGameState){
    List<Integer> unCoveredMoves = new ArrayList<>();        
        
        for(int i=0;i<3;i++){
            for (int j = 0; j < 3; j++) {
                if(_currentGameState[i][j].equals(COINNAME.N)){
                    unCoveredMoves.add(3*i+j);
                }
            }
        }
        return unCoveredMoves;
        
    }
    
    public int move(COINNAME[][] currentGameState) {
        // initializing current game state
        this.currentGameState=currentGameState;
        COINNAME[][] nextGameState;
        int nextIndexToBeTraversed;
        // finding next possible moves
        //Set<Integer> nextPossibleMoveIndices=new HashSet<>(getUnCoveredMoves(currentGameState));
         nextPossibleMoveIndices = new ArrayList<>(getUnCoveredMoves(currentGameState));        
// calculating number of scores to be calculated
        allScores=new Score[nextPossibleMoveIndices.size()];
        for (int i = 0; i < allScores.length; i++) {
            allScores[i]=new Score();
        }        
        // 
        
        for(int scoreIndex=0;scoreIndex<allScores.length;scoreIndex++){
            // clonning current game state
            Score currentScore=allScores[scoreIndex];
            nextGameState=cloneGameState(currentGameState);
            nextIndexToBeTraversed=(int)nextPossibleMoveIndices.get(scoreIndex);
            nextGameState[nextIndexToBeTraversed/3][nextIndexToBeTraversed%3]=COINNAME.A;    

            System.out.println(" - - - - [ "+COINNAME.A+" ] is   Moving  - - - : [ "+nextIndexToBeTraversed+" ]");
            System.out.println("Next Game State:");
            Board.display(nextGameState);            
             if(GameSpecs.isGameOver(nextGameState,COINNAME.A)) 
            {   
                    System.out.println("This path returns a win !");
                    currentScore.score[9-nextPossibleMoveIndices.size()][0]+=1;
                    System.out.println("Score:"+currentScore.score[9-nextPossibleMoveIndices.size()][0]);
                    continue; // update score function here 
            }
            // Check for Stalemate of the Game
            else if(GameSpecs.isStalemate(nextGameState)){ //stalemate condition
                  System.out.println("This path returns a Stalemate !");
                  currentScore.score[9-nextPossibleMoveIndices.size()][1]+=1;
                  System.out.println("Score:"+currentScore.score[9-nextPossibleMoveIndices.size()][1]);
                  continue;
            }else{
                
                 computeScore(nextGameState, COINNAME.H,scoreIndex);                
            }           
        }
        displayAllScores();
        return getBestScore();        
    }
    
      
    public void computeScore(COINNAME[][] currentGameState,COINNAME currentCoin,int scoreIndex){
        Score currentScore=allScores[scoreIndex];  
        COINNAME[][] _nextGameState;
        System.out.println("Computing Score for Score index: [ "+scoreIndex+" ] - Turn: "+currentCoin);
        Board.display(currentGameState);
        List<Integer> _nextPossibleMoveIndices= new ArrayList<>(getUnCoveredMoves(currentGameState));        

        for(Integer nextMoveIndex:_nextPossibleMoveIndices){
            _nextGameState=cloneGameState(currentGameState);
            _nextGameState[nextMoveIndex/3][nextMoveIndex%3]=currentCoin;
            
            System.out.println(" - - - - [ "+currentCoin+" ]  Moved to - - - : [ "+nextMoveIndex+" ]");
            System.out.println("Next Game State:");
            Board.display(_nextGameState);
            System.out.println("      Uncovered Moves:  "+_nextPossibleMoveIndices);            
            if(GameSpecs.isGameOver(_nextGameState,currentCoin)) 
            {    if(currentCoin.equals(COINNAME.A)){
                    System.out.println("This path returns a win !");
                    currentScore.score[9-_nextPossibleMoveIndices.size()][0]+=1;
                    System.out.println("Score:"+currentScore.score[9-_nextPossibleMoveIndices.size()][0]);
                 }
                else{
                    System.out.println("This path returns a Loose !");
                    currentScore.score[9-_nextPossibleMoveIndices.size()][2]+=1;
                    System.out.println("Score:"+currentScore.score[9-_nextPossibleMoveIndices.size()][2]);
                }
                continue; // update score function here 
            }
            // Check for Stalemate of the Game
            else if(GameSpecs.isStalemate(_nextGameState)){ //stalemate condition
                  System.out.println("This path returns a Stalemate !");
                  currentScore.score[9-_nextPossibleMoveIndices.size()][1]+=1;
                  System.out.println("Score:"+currentScore.score[9-_nextPossibleMoveIndices.size()][1]);
                  continue;
            }else{
                COINNAME nextCoin=getNextCoin(currentCoin);
                computeScore(_nextGameState,nextCoin,scoreIndex);
            }
        }
    }
private COINNAME[][] cloneGameState(COINNAME[][] _currentGameState){
         COINNAME[][] _nextGameState=new COINNAME[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                _nextGameState[i][j]=_currentGameState[i][j];
            }
        }
        return _nextGameState;
    }
      
    public COINNAME getNextCoin(COINNAME currentCoin) {
        return (currentCoin.equals(COINNAME.A))?COINNAME.H:COINNAME.A;
    }
    
    private int getBestScore() {
            return decideMove();
    }
    //
     public int decideMove(){
        //maxScoreIndex
        int[] winner=new int[allScores.length];
        int index=0,minScore=Integer.MAX_VALUE,winIndex=0;

       
        for(Score currentScore:allScores){            
            for(int i=0;i<9;i++){
                int WinScore=currentScore.score[i][0];
                int drawScore=currentScore.score[i][1];
                int looseScore=currentScore.score[i][2];
                    if(WinScore>=1){                        
                    winner[index]=-(int)Math.pow(10,i+1)*(WinScore); //-10*WinScore*(i+1);
                    System.out.println("Win status ["+winner[index]+"] "+(index+1)); 
                    break;
                    }else if(drawScore>=1){

                        winner[index]=(int)Math.pow(10,i+1)*(drawScore);
                        System.out.println("Win status ["+winner[index]+"] "+(index+1)); 
                    break;
                    }
                    else if(looseScore>=1){

                        winner[index]=(int)Math.pow(10,i+1)*(looseScore);
                        System.out.println("Win status ["+winner[index]+"] "+(index+1)); 
                    break;
                    }
            }
            index++;
        }        
        for(int i=0;i<allScores.length;i++)
        {
            System.out.println("Current score of [ "+i+" ] is : "+winner[i]+", MAX SCORE:"+minScore);
            if(minScore>winner[i]){
                minScore=winner[i];
                winIndex=i;
            }
        }
         
        return nextPossibleMoveIndices.get(winIndex);
    }
    
     public void displayAllScores(){
         
         for(int i=0;i<allScores.length;i++){
             System.out.println("- - - - - Scores for iteration : "+i+"\n");
             allScores[i].display();
         }
     }
}
