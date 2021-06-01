package game.cards;

import game.Game;
import game.actions.AttackAction;

public class JokerCard extends Card {
    public JokerCard() {
        this.action = new AttackAction(5);
    }

    @Override
    public boolean isPlayable(Game context) {
        return true;
    }

    @Override
    public String toString() {
        return "Joker";
    }
}
