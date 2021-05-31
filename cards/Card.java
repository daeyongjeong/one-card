package cards;

import actions.Action;
import game.TableManager;
import players.Player;

public abstract class Card {
    protected Action action;

    public abstract boolean canBePlayedWith(Card criteria);

    protected boolean isInAttackState() {
        return TableManager.getInstance().getAttackState();
    }

    public void onPlay(Player player) {
        if (action != null)
            action.run(player);
    }

    public abstract String toString();
}