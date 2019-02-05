
package Model;

import Game.Game;
import GameEngine.GameState.State;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Snake {
    
    private Game game;
    //
    private boolean alive;
    private static boolean initiated;
    private List<Segment> snakeList;    
    private Direction direction;
    //
    private Random rand = new Random();
    //
    private float time = 0;
    
    public Snake(Game game) {
        initiated = false;
        this.game = game;
        snakeList = Collections.synchronizedList(new LinkedList<>());

        direction = direction.right;
        game.setDirection(direction);
        
        for(int i=7; i>=0; i--)
            snakeList.add(new Segment((game.getColumns() / 2 - 4) + i, game.getRows() / 2 - 1, direction, game));
        
        alive = true;
        initiated = true;
    }
    
    public synchronized void update() {
        
        time += game.getElapsedTime();
        
        if(time >= game.getSpeed()) {
            
             direction = game.getDirection();
            
            Segment head = snakeList.get(0);
                        
            int newX = 0;
            int newY = 0;
            
            switch (direction) {
                case up:
                {
                    newX = head.getX();
                    newY = head.getY() - 1;
                    break;
                }
                case down:
                {
                    newX = head.getX();
                    newY = head.getY() + 1;
                    break;
                }
                case right:
                {
                    newX = head.getX() + 1;
                    newY = head.getY();
                    break;
                }
                case left:
                {
                    newX = head.getX() - 1;
                    newY = head.getY();
                    break;
                }

            }
            
            newX = newX % game.getColumns();
            newX = newX >= 0 ? newX : game.getColumns() - 1;
            newY = newY % game.getRows();
            newY = newY >= 0 ? newY : game.getRows() - 1;
            
            Segment newHead = new Segment(newX, newY, direction, game);
            
            for(Segment s : snakeList) {
                if(s.getX()==newHead.getX() && s.getY()==newHead.getY()) {
                    alive = false;
                    break;
                }               
            }
            
            snakeList.add(0, newHead);
                       
            if(alive) {
                                
                if(newHead.getX()==game.getApple().getX() && newHead.getY()==game.getApple().getY()) {
                    game.getApple().reallocate();                                  
                    game.setScore(game.getScore() + 10);
                }
                else
                    snakeList.remove(snakeList.size() - 1);
                
            }
            else {
                game.getPostGameMenu().setTitle("Score: " + game.getScore());
                game.setState(State.postGame);
            }
            
            time = 0;
        }
        
    }
    
    public void render(Graphics2D g) {
        
        if(initiated) {
            
            if(snakeList.get(0) != null) snakeList.get(0).drawHead(g);

            for(int i=1; i<snakeList.size()-1; i++)
                if(snakeList.get(i) != null) snakeList.get(i).drawBody(g);

            snakeList.get(snakeList.size()-1).drawTail(g);

            if(!alive && snakeList.get(snakeList.size()-1)!=null) snakeList.get(0).drawHead(g);
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public List<Segment> getSnakeList() {
        return snakeList;
    }

    public boolean isAlive() {
        return alive;
    }
 
}
