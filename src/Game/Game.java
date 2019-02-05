
package Game;

import GameMenus.PostGameMenu;
import GameMenus.PauseMenu;
import GameMenus.StartMenu;
import GameEngine.AbstractGame;
import GameEngine.Graphics.BufferedImageLoader;
import GameEngine.Graphics.SpriteSheet;
import GamePanel.GameData;
import GamePanel.PanelItem;
import Model.Apple;
import Model.Direction;
import Model.Snake;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Game extends AbstractGame{
    
    private int rows, columns;
    private int tileSize;
    //
    private Snake snake;
    private Apple apple;
    private Direction direction;
    //
    private float speed;
    //
    private boolean gameStarted;
    //
    private int score, highScore;
    private PanelItem scoreLabel, highScoreLabel;
    //
    private SpriteSheet sprite;

    public Game(int width, int height, float scale) {
        super(width, height, scale);
    }

    @Override
    public void initiate() {
        
        setResizable(false);
        setDebugInfoDisplayed(false);
        
        scoreLabel = new PanelItem("Score", "0");
        highScoreLabel = new PanelItem("High Score", "0");
        
        addGamePanel(new GameData() {
            
            @Override
            public void initiate() {
                
                this.addItem(scoreLabel);
                this.addItem(highScoreLabel);
                
            }
        
        });
        
        setStartMenu(new StartMenu(this));
        setPauseMenu(new PauseMenu(this));
        setPostGameMenu(new PostGameMenu(this));
        
        speed = 0.1f;
        
        tileSize = 20;
        columns = getWidth() / tileSize;
        rows = getHeight() / tileSize;
    
        try {
            BufferedImageLoader loader = new BufferedImageLoader();
            sprite = new SpriteSheet(loader.loadImage("/img/snakeSprite.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        snake = new Snake(this);
        apple = new Apple(this);
        setScore(0);
    }

    @Override
    public void update() {
        if(gameStarted) {
            
            if(getInput().isKeyDown(KeyEvent.VK_UP) && snake.getDirection()!=Direction.down) direction = Direction.up;
            else if(getInput().isKeyDown(KeyEvent.VK_DOWN) && snake.getDirection()!=Direction.up) direction = Direction.down;
            else if(getInput().isKeyDown(KeyEvent.VK_RIGHT) && snake.getDirection()!=Direction.left) direction = Direction.right;
            else if(getInput().isKeyDown(KeyEvent.VK_LEFT) && snake.getDirection()!=Direction.right) direction = Direction.left;
            
            snake.update();
            
        }
        else {
            if(getInput().isKeyUp(KeyEvent.VK_SPACE)){             
                gameStarted = true;              
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(snake != null) snake.render(g);
        apple.render(g);
    }
    
    public void reset() {
        snake = null;
        snake = new Snake(this);
        apple.reallocate();    
        setScore(0);
        gameStarted = false;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public Apple getApple() {
        return apple;
    }

    public Snake getSnake() {
        return snake;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }  

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;       
        scoreLabel.setData(Integer.toString(score));

        if(score > highScore){
            highScore = score;
            highScoreLabel.setData(Integer.toString(highScore));
        }
        
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getTileSize() {
        return tileSize;
    }

    public SpriteSheet getSprite() {
        return sprite;
    }
    
    
    
}
