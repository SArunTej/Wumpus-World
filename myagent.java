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
        System.out.print(cX+"--"+cY);
        
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
        
        //decide next move
        /*rnd = decideRandomMove();
        if (rnd==0)
        {
            w.doAction(World.A_TURN_LEFT);
            w.doAction(World.A_MOVE);
        }
        
        if (rnd==1)
        {
            w.doAction(World.A_MOVE);
        }
                
        if (rnd==2)
        {
            w.doAction(World.A_TURN_LEFT);
            w.doAction(World.A_TURN_LEFT);
            w.doAction(World.A_MOVE);
        }
                        
        if (rnd==3)
        {
            w.doAction(World.A_TURN_RIGHT);
            w.doAction(World.A_MOVE);
        }*/
        
      if (w.hasStench(cX, cY))
        {
            if (w.hasStench(cX, cY+2))  // Wumpus at cX, cY+1
            {
                if (w.getDirection()==0 && w.hasArrow())
                    w.doAction(World.A_SHOOT);
                else if (w.getDirection()==1 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==2 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==0 && !w.hasArrow())
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
            
            else if (w.hasStench(cX+2, cY))  // Wumpus at cX+1, cY
            {
                if (w.getDirection()==0 && w.hasArrow())
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==1 && w.hasArrow())
                    w.doAction(World.A_SHOOT);
                else if (w.getDirection()==2 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==1 && !w.hasArrow())
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
            
            else if (w.hasStench(cX-2, cY))  // Wumpus at cX-1, cY
            {
                if (w.getDirection()==0 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==1 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==2 && w.hasArrow())
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==3 && !w.hasArrow())
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
            
            else if (w.hasStench(cX, cY-2))  // Wumpus at cX, cY-1
            {
                if (w.getDirection()==0 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==1 && w.hasArrow())
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==2 && w.hasArrow())
                    w.doAction(World.A_SHOOT);
                else if (w.getDirection()==2 && !w.hasArrow())
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
                if (w.isVisited(cX-1, cY))   // Wumpus at cX, cY+1
                {
                if (w.getDirection()==0)
                    w.doAction(World.A_SHOOT);
                else if (w.getDirection()==1)
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==2)
                    w.doAction(World.A_TURN_LEFT);
                else
                    w.doAction(World.A_TURN_RIGHT);
                }
                else if(w.isVisited(cX, cY+1))  // Wumpus at cX-1, cY
                {
                if (w.getDirection()==0 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==1 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==2 && w.hasArrow())
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==3 && !w.hasArrow())
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
                if (w.isVisited(cX, cY+1))  // Wumpus at cX+1, cY
                {
                if (w.getDirection()==0)
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==1)
                   w.doAction(World.A_SHOOT);
                else if (w.getDirection()==2)
                    w.doAction(World.A_TURN_LEFT);
                else
                    w.doAction(World.A_TURN_LEFT);
                }
                
                else if (w.isVisited(cX+1, cY))  // Wumpus at cX, cY+1
                {
                if (w.getDirection()==0)
                    w.doAction(World.A_SHOOT);
                else if (w.getDirection()==1)
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==2)
                    w.doAction(World.A_TURN_LEFT);
                else
                    w.doAction(World.A_TURN_RIGHT);
                }
            }
            
            else if (w.hasStench(cX+1, cY-1))
            {
                if (w.isVisited(cX+1, cY))  // Wumpus at cX, cY-1
                {
                if (w.getDirection()==0)
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==1)
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==2)
                   w.doAction(World.A_SHOOT);
                else
                    w.doAction(World.A_TURN_LEFT);
                }
                
                else if (w.isVisited(cX, cY-1))  // Wumpus at cX+1, cY
                {
                if (w.getDirection()==0)
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==1)
                    w.doAction(World.A_SHOOT);
                else if (w.getDirection()==2)
                    w.doAction(World.A_TURN_LEFT);
                else
                    w.doAction(World.A_TURN_LEFT);
                }
            }
            
            else if (w.hasStench(cX-1, cY-1))
            {
                if (w.isVisited(cX, cY-1))  // Wumpus at cX-1, cY
                {
                if (w.getDirection()==0)
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==1)
                   w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==2)
                    w.doAction(World.A_TURN_RIGHT);
                else
                   w.doAction(World.A_SHOOT);
                }
                
                else if (w.isVisited(cX-1, cY))  // Wumpus at cX, cY-1
                {
                if (w.getDirection()==0 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==1 && w.hasArrow())
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==2 && w.hasArrow())
                    w.doAction(World.A_SHOOT);
                else if (w.getDirection()==2 && !w.hasArrow())
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
                if(w.getDirection()==0)
                    w.doAction(World.A_SHOOT);
                else if(w.getDirection()==1)
                    w.doAction(World.A_TURN_LEFT);
                else if(w.getDirection()==2)
                    w.doAction(World.A_TURN_LEFT);
                else
                    w.doAction(World.A_TURN_RIGHT);
            }
            
            else if (w.isUnknown(cX, cY+1) && w.isUnknown(cX+1, cY) && !w.hasArrow())
            {
                w.doAction(World.A_MOVE);
            }
            
            else if (w.getDirection()==3)
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
            
            else if (w.getDirection()==1)
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
            
            return;
        }
      
      if (w.getDirection() == 1)
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
          
        return;
      }
      if (w.getDirection() == 3)
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
          
        return;
      }
      if (w.getDirection()==0)
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
                w.doAction(World.A_MOVE);
                w.doAction(World.A_TURN_LEFT);
            }
            else
            {
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
            }
          }
          else
          {
              w.doAction(World.A_TURN_RIGHT);
          }
        return;  
      }
      
      if (w.getDirection()==2)
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
     * Genertes a random instruction for the Agent.
     */
   /* public int decideRandomMove()
    {
      return (int)(Math.random() * 4);
    }*/
    
    
}

