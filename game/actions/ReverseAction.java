package game.actions;

import game.Game;

public class ReverseAction implements Action {

    @Override
    public void run(Game context) {
        context.reversePlayDirection();
    }

    @Override
    public String toString() {
        return "reverse the direction of play";
    }
}
