package game.actions;

import game.Game;

public class OneMoreTurnAction implements Action {

    @Override
    public void run(Game context) {
        context.setOneMoreTurn();
    }

    @Override
    public String toString() {
        return "take another turn";
    }
}
