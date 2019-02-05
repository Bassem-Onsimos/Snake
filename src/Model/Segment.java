package Model;

import Game.Game;
import GameEngine.Graphics.SpriteSheet;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Segment {

    private int x, y;
    private Direction direction;
    //
    private Game game;
    //
    private BufferedImage image;
    private SpriteSheet sprite;
    private int tileSize;

    public Segment(int x, int y, Direction direction, Game game) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.game = game;
        sprite = game.getSprite();
        tileSize = game.getTileSize();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /*
    public void drawBody(Graphics2D g) {
        g.setColor(Color.gray);
        g.fillOval(x * tileSize, y * tileSize, tileSize, tileSize);
    }
    
    public void drawHead(Graphics2D g, boolean alive) {
        if(alive) g.setColor(Color.red);
        else g.setColor(Color.white);
        
        g.fillOval(x * tileSize, y * tileSize, tileSize, tileSize);
    }
    
    public void drawTail(Graphics2D g) {
        g.setColor(Color.gray);
        g.fillOval(x * tileSize, y * tileSize, tileSize, tileSize);
    }
     */
    public void drawBody(Graphics2D g) {

        if (game.getSnake().getSnakeList().indexOf(this) != -1) {

            Direction prevDirection = game.getSnake().getSnakeList().get(game.getSnake().getSnakeList().indexOf(this) - 1).getDirection();

            switch (prevDirection) {

                case up: {

                    switch (direction) {

                        case up: {
                            image = sprite.cropImage(3, 2, tileSize, tileSize);
                            break;
                        }
                        case right: {
                            image = sprite.cropImage(3, 3, tileSize, tileSize);
                            break;

                        }
                        case left: {
                            image = sprite.cropImage(1, 2, tileSize, tileSize);
                            break;
                        }

                    }
                    break;
                }
                case down: {

                    switch (direction) {

                        case down: {
                            image = sprite.cropImage(3, 2, tileSize, tileSize);
                            break;
                        }
                        case right: {
                            image = sprite.cropImage(3, 1, tileSize, tileSize);
                            break;
                        }
                        case left: {
                            image = sprite.cropImage(1, 1, tileSize, tileSize);
                            break;
                        }

                    }
                    break;

                }
                case right: {

                    switch (direction) {

                        case up: {
                            image = sprite.cropImage(1, 1, tileSize, tileSize);
                            break;
                        }
                        case down: {
                            image = sprite.cropImage(1, 2, tileSize, tileSize);
                            break;
                        }
                        case right: {
                            image = sprite.cropImage(2, 1, tileSize, tileSize);
                            break;
                        }

                    }
                    break;

                }
                case left: {

                    switch (direction) {

                        case up: {
                            image = sprite.cropImage(3, 1, tileSize, tileSize);
                            break;
                        }
                        case down: {
                            image = sprite.cropImage(3, 3, tileSize, tileSize);
                            break;
                        }
                        case left: {
                            image = sprite.cropImage(2, 1, tileSize, tileSize);
                            break;
                        }

                    }
                    break;

                }

            }

            g.drawImage(image, x * tileSize, y * tileSize, tileSize, tileSize, null);

        }

    }

    public void drawHead(Graphics2D g) {

        switch (direction) {

            case up: {
                image = sprite.cropImage(4, 1, tileSize, tileSize);
                break;
            }
            case down: {
                image = sprite.cropImage(5, 2, tileSize, tileSize);
                break;
            }
            case right: {
                image = sprite.cropImage(5, 1, tileSize, tileSize);
                break;
            }
            case left: {
                image = sprite.cropImage(4, 2, tileSize, tileSize);
                break;
            }

        }

        g.drawImage(image, x * tileSize, y * tileSize, tileSize, tileSize, null);

    }

    public void drawTail(Graphics2D g) {

        if (game.getSnake().getSnakeList().contains(this)) {

            Direction prevDirection = game.getSnake().getSnakeList().get(game.getSnake().getSnakeList().indexOf(this) - 1).getDirection();

            switch (prevDirection) {

                case up: {
                    image = sprite.cropImage(4, 3, tileSize, tileSize);
                    break;
                }
                case down: {
                    image = sprite.cropImage(5, 4, tileSize, tileSize);
                    break;
                }
                case right: {
                    image = sprite.cropImage(5, 3, tileSize, tileSize);
                    break;
                }
                case left: {
                    image = sprite.cropImage(4, 4, tileSize, tileSize);
                    break;
                }

            }

            g.drawImage(image, x * tileSize, y * tileSize, tileSize, tileSize, null);
       }
    }

    public Direction getDirection() {
        return direction;
    }

}
