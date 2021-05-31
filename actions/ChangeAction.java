package actions;

import cards.Suit;
import game.TableManager;
import players.Player;

public class ChangeAction implements Action {

    @Override
    public void run(Player player) {
        Suit changedSuit = player.changeSuit();
        TableManager.getInstance().setChangedSuit(changedSuit);
    }
}
