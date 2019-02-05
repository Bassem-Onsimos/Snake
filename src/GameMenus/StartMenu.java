
package GameMenus;

import Game.Game;
import GameEngine.GameState.State;
import GameMenu.AbstractMenu;
import GameMenu.MenuItem;
import GameMenu.SubMenuInitializer;

public class StartMenu extends AbstractMenu {
    
    Game game;
    
    public StartMenu(Game game) {
        super(game);
        this.game = game;
    }

    @Override
    public void initiate() {
        
        setBackgroundOpacity(opaque);
        setTitle("Snake");
        
        addItem(new MenuItem("New Game") {
            @Override
            public void function() {
                game.setState(State.inGame);
                game.reset();
            }
        });
        
        addItem(new SubMenuInitializer("Level") {
            @Override
            public void initiate() {
                
                addSubMenuItem(new MenuItem("Beginner") {
                    @Override
                    public void function() {
                        game.setSpeed(0.15f);
                    }
                });
                
                addSubMenuItem(new MenuItem("Intermediate") {
                    @Override
                    public void function() {
                        game.setSpeed(0.1f);
                    }
                });
                
                addSubMenuItem(new MenuItem("Advanced") {
                    @Override
                    public void function() {
                        game.setSpeed(0.06f);
                    }
                });
                
            }
        });
        
        addItem(new MenuItem("Exit") {
            @Override
            public void function() {
                System.exit(0);
            }
        });
        
        
    }
    
}
