/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;


/**
 *
 * @author venkatramreddykunta
 */
public class Score {   
   public int[][] score;    
   //enum ScoreState{WIN,LOOSE,DRAW};
    public Score() {
        this.score=new int[9][3];        
    }

    public void addWins(int level){
        this.score[level][0]++;
    }    
    public void addDraws(int level){
        this.score[level][1]++;
    }
    public void addLooses(int level){
        this.score[level][2]++;
    }   
  
    public void display(){
        System.out.println("Score State:");
        System.out.println(" LEVEL:   WIN -  DRAW -  LOOSE ");
        
        for (int i = 0; i < 9; i++) {
            System.out.print("[  "+i+"  ]:  ");            
            System.out.print(" "+this.score[i][0]+"      ");
            System.out.print(" "+this.score[i][1]+"      ");
            System.out.print(" "+this.score[i][2]);
            System.out.println("");
        }
   
    }
  
}
