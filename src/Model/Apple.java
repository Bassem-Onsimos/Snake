
package Model;

import Game.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Apple {
    
    private Game game;
    //
    private int x, y;
    private int tileSize;
    //
    private Random rand = new Random();
    //
    private BufferedImage image;

    public Apple(Game game) {
        this.game = game;
        tileSize = game.getTileSize();
        image = game.getSprite().cropImage(1, 4, tileSize, tileSize);
        reallocate();
    }
    
    /*
    public void render(Graphics2D g) {
        g.setColor(Color.green);
        g.fillOval(x * tileSize, y * tileSize, tileSize, tileSize);
    }
    */
    
    public void render(Graphics2D g) {
        g.drawImage(image, x * tileSize, y * tileSize, tileSize, tileSize, null);
    }
    
    public void reallocate() {
        
        boolean overlap;
        int appleX;
        int appleY;
        
        do {
            overlap = false;
            appleX = rand.nextInt(game.getColumns());
            appleY = rand.nextInt(game.getRows());
            for(Segment s : game.getSnake().getSnakeList()) {
                if(s.getX() == appleX && s.getY() == appleY) {
                    overlap = true;
                    break;
                }
            }
        } while (overlap);
        
        x = appleX;
        y = appleY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
 
}
