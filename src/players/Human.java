/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import java.util.Scanner;
import game.Board.*;
/**
 *
 * @author venkatramreddykunta
 */
public class Human extends Player{

    public Human() {
        currentPlayer=PLAYERTYPE.HUMAN;
        currentCoin=COINNAME.H;
    }
    private boolean validInput(COINNAME[][] currentGameState,int input) {        
        if(currentGameState[input/3][input%3].equals(COINNAME.N)){
            return true;
        }
        return false;
    }
    public int move(COINNAME[][] currentGameState){
        System.out.println(currentPlayer+"'s move"); 
    
         System.out.println("Human Input a Number :");
        Scanner scanner=new Scanner(System.in);
//        System.out.println("current Turn "+currentTurn);
//        System.out.println("current Game "+currentGameState[0][0]);
        boolean readFirstTime=false;
        int input;
        
        do{            
            if(!readFirstTime)
                readFirstTime=true;
            else
               System.out.println("Input is invalid:");
            input=scanner.nextInt();                        
        }while(!(input>=0 && input<9 && validInput(currentGameState,input)));
//            System.out.println("Valid input:");
    return input;
    }
          
    
}
