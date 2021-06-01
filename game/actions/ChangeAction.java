package game.actions;

import game.Game;
import game.cards.Suit;
import game.players.Player;

public class ChangeAction implements Action {

    @Override
    public void run(Game context) {
        Player currentPlayer = context.getCurrentPlayer();
        Suit changedSuit = currentPlayer.changeSuit();
        context.setChangedSuit(changedSuit);
    }

    @Override
    public String toString() {
        return "the player calls out the new suit to play";
    }
}
