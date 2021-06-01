package game.actions;

import game.Game;

public class JumpAction implements Action {

    @Override
    public void run(Game context) {
        context.setJumpTurn();
    }

    @Override
    public String toString() {
        return "jump the next player";
    }
}
