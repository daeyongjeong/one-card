package game.cards;

import game.actions.Action;
import game.Game;

public abstract class Card {
    protected Action action;

    public abstract boolean isPlayable(Game context);

    public void play(Game context) {
        if (action != null)
            action.run(context);
    }

    public abstract String toString();

    public Action getAction() {
        return action;
    }
}