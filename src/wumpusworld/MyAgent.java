package wumpusworld;

/**
 * Contains starting code for creating your own Wumpus World agent.
 * Currently the agent only make a random decision each turn.
 * 
 * @author Johan Hagelbäck
 */
public class MyAgent implements Agent
{
    private World w;
    int rnd;
    
    /**
     * Creates a new instance of your solver agent.
     * 
     * @param world Current world state 
     */
    public MyAgent(World world)
    {
        w = world;   
    }
    
    /**
     * Asks your solver agent to execute an action.
     */
    public void doAction()
    {
        //Location of the player
        int cX = w.getPlayerX();
        int cY = w.getPlayerY();
        
        //Basic action:
        //Grab Gold if we can.
        if (w.hasGlitter(cX, cY))
        {
            w.doAction(World.A_GRAB);
            return;
        }
        
        //Basic action:
        //We are in a pit. Climb up.
        if (w.isInPit())
        {
            w.doAction(World.A_CLIMB);
            return;
        }
        
        //Test the environment
        if (w.hasBreeze(cX, cY))
        {
            System.out.println("I am in a Breeze");
        }
        if (w.hasStench(cX, cY))
        {
            System.out.println("I am in a Stench");
        }
        if (w.hasPit(cX, cY))
        {
            System.out.println("I am in a Pit");
        }
        if (w.getDirection() == World.DIR_RIGHT)
        {
            System.out.println("I am facing Right");
        }
        if (w.getDirection() == World.DIR_LEFT)
        {
            System.out.println("I am facing Left");
        }
        if (w.getDirection() == World.DIR_UP)
        {
            System.out.println("I am facing Up");
        }
        if (w.getDirection() == World.DIR_DOWN)
        {
            System.out.println("I am facing Down");
        }
        
        findMoveBasedOnPercepts(cX, cY);
    }    
    
    /**
     * Find move based on percepts
     * @param cX horizontal position
     * @param cY vertical position
     */
    public void findMoveBasedOnPercepts(int cX, int cY)
    {
        if (w.hasStench(cX, cY))
        {
            nextMoveIfPlayerInStench(cX, cY);
        }
        else if(w.getDirection() == World.DIR_UP)
        {
            nextMoveIfPlayerFacingUp(cX, cY);
        }
        else if(w.getDirection() == World.DIR_RIGHT)
        {
            nextMoveIfPlayerFacingRight(cX, cY);
        }
        else if(w.getDirection() == World.DIR_DOWN)
        {
            nextMoveIfPlayerFacingDown(cX, cY);
        }
        else if(w.getDirection() == World.DIR_LEFT)
        {
            nextMoveIfPlayerFacingLeft(cX, cY);
        }
    }
    
    /**
     * Gives the next move if the player is in stench.
     * Identify Wumpus and shoot arrow at it.
     * @param cX horizontal position
     * @param cY vertical position
     */
    public void nextMoveIfPlayerInStench(int cX, int cY)
    { 
         /**
          * Wumpus found at cX, cY-1 & player has no arrow
          */
        
        if(w.hasStench(cX-1, cY-1) && !w.hasArrow()) 
        {
            if(w.getDirection() == World.DIR_UP)
                w.doAction(World.A_MOVE);
            else if(w.getDirection() == World.DIR_LEFT)
                w.doAction(World.A_TURN_RIGHT);
            else if(w.getDirection() == World.DIR_DOWN)
                w.doAction(World.A_TURN_RIGHT);
            else
                w.doAction(World.A_TURN_LEFT);
            return;
        }
        
        /**
         * Wumpus found at cX, cY+1,  kill it || player has no arrows then try to escape
         */
        
        if (w.hasStench(cX, cY+2))  
        {
            if (w.getDirection() == World.DIR_UP && w.hasArrow())
                w.doAction(World.A_SHOOT);
            else if (w.getDirection() == World.DIR_RIGHT && w.hasArrow())
                w.doAction(World.A_TURN_LEFT);
            else if (w.getDirection() == World.DIR_DOWN && w.hasArrow())
                w.doAction(World.A_TURN_LEFT);
            else if (w.getDirection() == World.DIR_UP && !w.hasArrow())
                w.doAction(World.A_TURN_LEFT);
            else if (!w.hasArrow() && w.isValidPosition(cX-1, cY))
                w.doAction(World.A_MOVE);
            else if (!w.hasArrow())
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
            }
            else
                w.doAction(World.A_TURN_RIGHT);
        }
        
        /**
         * Wumpus found at cX+1, cY,  kill it || player has no arrows then try to escape
         */
        
        else if (w.hasStench(cX+2, cY))  
        {
            if (w.getDirection() == World.DIR_UP && w.hasArrow())
                w.doAction(World.A_TURN_RIGHT);
            else if (w.getDirection() == World.DIR_RIGHT && w.hasArrow())
                w.doAction(World.A_SHOOT);
            else if (w.getDirection() == World.DIR_LEFT && w.hasArrow())
                w.doAction(World.A_TURN_LEFT);
            else if (w.getDirection() == World.DIR_RIGHT && !w.hasArrow())
                w.doAction(World.A_TURN_LEFT);
            else if (!w.hasArrow() && w.isValidPosition(cX, cY+1))
                w.doAction(World.A_MOVE);
            else if (!w.hasArrow())
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
            }
            else
                w.doAction(World.A_TURN_LEFT);
        }
        
        /**
         *  Wumpus found at cX-1, cY, kill it || player has no arrows then try to escape
         */
        
        else if (w.hasStench(cX-2, cY))  
        {
            if (w.getDirection() == World.DIR_UP && w.hasArrow())
                w.doAction(World.A_TURN_LEFT);
            else if (w.getDirection() == World.DIR_RIGHT && w.hasArrow())
                w.doAction(World.A_TURN_LEFT);
            else if (w.getDirection() == World.DIR_DOWN && w.hasArrow())
                w.doAction(World.A_TURN_RIGHT);
            else if (w.getDirection() == World.DIR_LEFT && !w.hasArrow())
                w.doAction(World.A_TURN_RIGHT);
            else if (!w.hasArrow() && w.isValidPosition(cX, cY+1))
                w.doAction(World.A_MOVE);
            else if (!w.hasArrow())
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
            }
            else
                w.doAction(World.A_SHOOT);
        }
        
        /**
         *  Wumpus found at cX, cY-1, kill it || player has no arrows then try to escape
         */
        
        else if (w.hasStench(cX, cY-2))  
        {
            if (w.getDirection() == World.DIR_UP && w.hasArrow())
                w.doAction(World.A_TURN_LEFT);
            else if (w.getDirection() == World.DIR_RIGHT && w.hasArrow())
                w.doAction(World.A_TURN_RIGHT);
            else if (w.getDirection() == World.DIR_DOWN && w.hasArrow())
                w.doAction(World.A_SHOOT);
            else if (w.getDirection() == World.DIR_DOWN && !w.hasArrow())
                w.doAction(World.A_TURN_LEFT);
            else if (!w.hasArrow() && w.isValidPosition(cX, cY+1))
                w.doAction(World.A_MOVE);
            else if (!w.hasArrow())
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
            }
            else
                w.doAction(World.A_TURN_LEFT);
        }
        
                
        else if (w.hasStench(cX-1, cY+1))
        {
         /**
         *  Wumpus found at cX, cY+1, kill it || player has no arrows then try to escape
         */
            
            if (w.isVisited(cX-1, cY))   
            {
            if (w.getDirection() == World.DIR_UP && w.hasArrow())
                w.doAction(World.A_SHOOT);
            else if (w.getDirection() == World.DIR_RIGHT)
                w.doAction(World.A_TURN_LEFT);
            else if (w.getDirection() == World.DIR_DOWN)
                w.doAction(World.A_TURN_LEFT);
            else
                w.doAction(World.A_TURN_RIGHT);
            }
         
         /**
         *  Wumpus found at cX-1, cY, kill it || player has no arrows then try to escape
         */
            
            else if(w.isVisited(cX, cY+1)) 
            {
            if (w.getDirection() == World.DIR_UP && w.hasArrow())
                w.doAction(World.A_TURN_LEFT);
            else if (w.getDirection() == World.DIR_RIGHT && w.hasArrow())
                w.doAction(World.A_TURN_LEFT);
            else if (w.getDirection() == World.DIR_DOWN && w.hasArrow())
                w.doAction(World.A_TURN_RIGHT);
            else if (w.getDirection() == World.DIR_LEFT && !w.hasArrow())
                w.doAction(World.A_TURN_RIGHT);
            else if (!w.hasArrow() && w.isValidPosition(cX, cY+1))
                w.doAction(World.A_MOVE);
            else if (!w.hasArrow())
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
            }
            else
                w.doAction(World.A_SHOOT);
            }
        }
        else if (w.hasStench(cX+1, cY+1))
        {
         /**
         *  Wumpus found at cX+1, cY, kill it || player has no arrows then try to escape
         */
            if (w.isVisited(cX, cY+1))  
            {
            if (w.getDirection() == World.DIR_UP)
                w.doAction(World.A_TURN_RIGHT);
            else if (w.getDirection() == World.DIR_RIGHT)
               w.doAction(World.A_SHOOT);
            else if (w.getDirection() == World.DIR_DOWN)
                w.doAction(World.A_TURN_LEFT);
            else
                w.doAction(World.A_TURN_LEFT);
            }
            
         /**
         *  Wumpus found at cX, cY+1 , kill it || player has no arrows then try to escape
         */
            
            else if (w.isVisited(cX+1, cY))  
            {
            if (w.getDirection() == World.DIR_UP)
                w.doAction(World.A_SHOOT);
            else if (w.getDirection() == World.DIR_RIGHT)
                w.doAction(World.A_TURN_LEFT);
            else if (w.getDirection() == World.DIR_DOWN)
                w.doAction(World.A_TURN_LEFT);
            else
                w.doAction(World.A_TURN_RIGHT);
            }
        }
        else if (w.hasStench(cX+1, cY-1))
        {
            
         /**
         *  Wumpus found at cX, cY-1, kill it || player has no arrows then try to escape
         */
            
            if (w.isVisited(cX+1, cY)) 
            {
            if (w.getDirection() == World.DIR_UP)
                w.doAction(World.A_TURN_LEFT);
            else if (w.getDirection() == World.DIR_RIGHT)
                w.doAction(World.A_TURN_RIGHT);
            else if (w.getDirection() == World.DIR_DOWN)
               w.doAction(World.A_SHOOT);
            else
                w.doAction(World.A_TURN_LEFT);
            }
            
         /**
         *  Wumpus found at cX+1, cY, kill it || player has no arrows then try to escape
         */
            
            else if (w.isVisited(cX, cY-1))  
            {
            if (w.getDirection() == World.DIR_UP)
                w.doAction(World.A_TURN_RIGHT);
            else if (w.getDirection() == World.DIR_RIGHT)
                w.doAction(World.A_SHOOT);
            else if (w.getDirection() == World.DIR_DOWN)
                w.doAction(World.A_TURN_LEFT);
            else
                w.doAction(World.A_TURN_LEFT);
            }
        }
        else if (w.hasStench(cX-1, cY-1))
        {
            
         /**
         *  Wumpus found at cX-1, cY, kill it || player has no arrows then try to escape
         */
            
            if (w.isVisited(cX, cY-1)) 
            {
            if (w.getDirection() == World.DIR_UP)
                w.doAction(World.A_TURN_LEFT);
            else if (w.getDirection() == World.DIR_RIGHT)
               w.doAction(World.A_TURN_LEFT);
            else if (w.getDirection() == World.DIR_DOWN)
                w.doAction(World.A_TURN_RIGHT);
            else
               w.doAction(World.A_SHOOT);
            }
            
         /**
         *  Wumpus found at cX, cY-1, kill it || player has no arrows then try to escape
         */
            
            else if (w.isVisited(cX-1, cY))  
            {
            if (w.getDirection() == World.DIR_UP && w.hasArrow())
                w.doAction(World.A_TURN_LEFT);
            else if (w.getDirection() == World.DIR_RIGHT && w.hasArrow())
                w.doAction(World.A_TURN_RIGHT);
            else if (w.getDirection() == World.DIR_DOWN && w.hasArrow())
                w.doAction(World.A_SHOOT);
            else if (w.getDirection() == World.DIR_DOWN && !w.hasArrow())
                w.doAction(World.A_TURN_LEFT);
            else if (!w.hasArrow() && w.isValidPosition(cX, cY+1))
                w.doAction(World.A_MOVE);
            else if (!w.hasArrow())
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
            }
            else
                w.doAction(World.A_TURN_LEFT);
            }
        } 
        else if (!w.isValidPosition(cX-1, cY) && !w.isValidPosition(cX, cY-1) && w.hasArrow())
        {
            if(w.getDirection() == World.DIR_RIGHT)
                w.doAction(World.A_SHOOT);
            else if(w.getDirection() == World.DIR_LEFT)
                w.doAction(World.A_TURN_LEFT);
            else if(w.getDirection() == World.DIR_DOWN)
                w.doAction(World.A_TURN_LEFT);
            else
                w.doAction(World.A_TURN_RIGHT);
        }
        else if (w.isUnknown(cX, cY+1) && w.isUnknown(cX+1, cY) && !w.hasArrow())
        {
            w.doAction(World.A_MOVE);
        }
        else if (w.getDirection() == World.DIR_LEFT)
        {
            if (!w.isValidPosition(cX-1, cY))
            {
                if((!w.isValidPosition(cX, cY-1) || w.isVisited(cX, cY-1)) && (w.isVisited(cX+1, cY) || (w.isUnknown(cX+1, cY) && w.isVisited(cX+1, cY-1)) ))
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_SHOOT);
                }
                else
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
            }
            else if(!w.isValidPosition(cX+1, cY) && w.isVisited(cX-1, cY-1))
                w.doAction(World.A_MOVE);
            else 
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
            }
        }
        else if (w.getDirection() == World.DIR_RIGHT)
        {
            if (!w.isValidPosition(cX+1, cY))
            {
                if((!w.isValidPosition(cX, cY-1) || w.isVisited(cX, cY-1)) && (w.isVisited(cX-1, cY) || (w.isUnknown(cX-1, cY) && w.isVisited(cX-1, cY-1))))
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
                else
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_SHOOT);
                }
            }
            else if(!w.isValidPosition(cX-1, cY) && w.isVisited(cX+1, cY+1))
                w.doAction(World.A_MOVE);
            else 
            {
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
            }
        }
        if(w.getDirection() == World.DIR_UP && w.hasPit(cX, cY))
        {
            w.doAction(World.A_TURN_LEFT);
            w.doAction(World.A_MOVE);
        }
    }
    
    /**
     * Gives the next move if the heading up.
     * @param cX horizontal position
     * @param cY vertical position
     */
    public void nextMoveIfPlayerFacingUp(int cX, int cY)
    {     
        if(w.hasBreeze(cX, cY))
        {
            if(w.hasBreeze(cX, cY-1) && w.isVisited(cX, cY-2))
            {
                if(w.hasBreeze(cX, cY-2))
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                    return;
                }
                if(!w.hasBreeze(cX, cY-2))
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_MOVE);
                    return;
                }
            }
        }
        if(w.hasBreeze(cX, cY-1) && !w.isValidPosition(cX, cY-2))
        {
            w.doAction(World.A_MOVE);
        }
        if (w.getDirection() == World.DIR_UP)
        {
            if(!w.isValidPosition(cX-1, cY))
            {
                if(w.isVisited(cX+1, cY))
                {
                    w.doAction(World.A_MOVE);
                    w.doAction(World.A_TURN_RIGHT);
                }
                else
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                }
            }
            else if(!w.isValidPosition(cX+1, cY))
            {
                if(w.isVisited(cX-1, cY))
                {
                    if(w.isValidPosition(cX-2, cY) && w.hasBreeze(cX-1, cY))
                    {
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        w.doAction(World.A_MOVE);
                        w.doAction(World.A_TURN_LEFT);
                    }
                }
                else
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_MOVE);
                }
            }
            else if(!w.isValidPosition(cX, cY+1) && !w.isVisited(cX, cY+1))
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
            }
            else
            {
                w.doAction(World.A_TURN_RIGHT);
            }  
        }
    }
    
    /**
     * Gives the next move if the heading down.
     * @param cX horizontal position
     * @param cY vertical position
     */
    public void nextMoveIfPlayerFacingDown(int cX, int cY)
    { 
        if(w.hasBreeze(cX, cY)){
            if(w.hasBreeze(cX, cY+1) && w.isVisited(cX, cY-1)){
                w.doAction(World.A_MOVE);
                return;
            }
            if(w.hasBreeze(cX-1, cY-1) && w.hasBreeze(cX-1, cY)){
                w.doAction(World.A_TURN_LEFT);
                return;
            }
        }
        if (w.getDirection() == World.DIR_DOWN)
        {
            if(w.isValidPosition(cX, cY-1) && w.isUnknown(cX, cY-1))
            {
                w.doAction(World.A_MOVE);
                w.doAction(World.A_TURN_LEFT);
            }
            else if(w.isValidPosition(cX+1, cY) && w.isUnknown(cX+1, cY))
            {
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
            }
            else if(w.isValidPosition(cX-1, cY) && w.isUnknown(cX-1, cY))
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
            }
            else if(w.isValidPosition(cX+1, cY))
            {
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
            }
            else if(w.isValidPosition(cX-1, cY))
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
            }
            else
            {
                w.doAction(World.A_TURN_LEFT);
            }
        }
    }
    
    /**
     * Gives the next move if the heading right.
     * @param cX horizontal position
     * @param cY vertical position
     */
    public void nextMoveIfPlayerFacingRight(int cX, int cY)
    {     
        if(w.hasBreeze(cX, cY))
        {
            if(w.hasBreeze(cX-1, cY-1) && !w.isValidPosition(cX-2, cY-1)){
                w.doAction(World.A_MOVE);
            }
            if(!w.isValidPosition(cX+1, cY))
            {
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
            }
            if(w.hasBreeze(cX, cY-1) && w.hasStench(cX, cY-1) && w.isUnknown(cX-1, cY+1))
            {
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
                return;
            }
            if(w.hasBreeze(cX, cY-1) && w.hasStench(cX, cY-1) && w.isVisited(cX-1, cY+1))
            {
                w.doAction(World.A_MOVE);
                return;
            }
            if(w.isValidPosition(cX+1, cY) && w.isUnknown(cX+1, cY) && w.isValidPosition(cX+2, cY) && w.isUnknown(cX+2, cY))
            {
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
            }
            if(w.isValidPosition(cX+1, cY) && w.isUnknown(cX+1, cY) && !w.isValidPosition(cX+2, cY))
            {
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
            }
        }
        if (w.getDirection() == World.DIR_RIGHT)
        {
            if(w.isValidPosition(cX+1, cY))
            {
                if(w.isUnknown(cX+1, cY))
                    w.doAction(World.A_MOVE);
                else if(w.hasStench(cX-1, cY) || w.hasBreeze(cX-1, cY))
                    w.doAction(World.A_MOVE);
                else if(w.isVisited(cX+1, cY))
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_MOVE);
                }
            }
            else if(!(w.isValidPosition(cX+1, cY) && w.isValidPosition(cX, cY+1)) && w.isVisited(cX-1, cY) && w.isUnknown(cX, cY-1))
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
            }
            else if(!(w.isValidPosition(cX+1, cY) && w.isValidPosition(cX, cY+1)) && w.isVisited(cX, cY-1) && w.isUnknown(cX-1, cY))
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
            }
            else if(!w.isValidPosition(cX-1, cY) && w.isVisited(cX+1, cY) && w.isVisited(cX, cY-1) && !w.isValidPosition(cX, cY+1))
                w.doAction(World.A_MOVE);
            else
            {
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
                w.doAction(World.A_TURN_LEFT);
            }
        }
    }
    
    /**
     * Gives the next move if the heading left.
     * @param cX horizontal position
     * @param cY vertical position
     */
    public void nextMoveIfPlayerFacingLeft(int cX, int cY)
    { 
        if (w.getDirection() == World.DIR_LEFT)
        {
            if(w.isValidPosition(cX-1, cY))
            {
                if(w.isUnknown(cX-1, cY))
                    w.doAction(World.A_MOVE);
                else if(w.hasStench(cX+1, cY) || w.hasBreeze(cX+1, cY))
                    w.doAction(World.A_MOVE);
                else if(w.isVisited(cX-1, cY))
                { 
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                }
            }
            else if(!(w.isValidPosition(cX-1, cY) && w.isValidPosition(cX, cY+1)) && w.isVisited(cX+1, cY) && w.isUnknown(cX, cY-1))
            {
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
            }
            else if(!(w.isValidPosition(cX-1, cY) && w.isValidPosition(cX, cY+1)) && w.isVisited(cX, cY-1) && w.isUnknown(cX+1, cY))
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
            }
            else if(w.isVisited(cX-1, cY) && !w.isValidPosition(cX+1, cY) && w.isVisited(cX, cY-1) && !w.isValidPosition(cX, cY+1))
                w.doAction(World.A_MOVE);
            else
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
                w.doAction(World.A_TURN_RIGHT);
            }
        }
    }
}

