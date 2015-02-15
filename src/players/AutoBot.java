/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;
import AI.AIEngine;
import AI.Score;
import game.*;
import game.Board.*;
import java.util.List;

/**
 *
 * @author venkatramreddykunta
 */
public class AutoBot extends Player{
    AIEngine aiEngine;
    public AutoBot() {                           
        aiEngine=new AIEngine();
    }
    
    
    public int move(COINNAME[][] currentGameState){        
        return aiEngine.move(currentGameState);        
    }
    
}
