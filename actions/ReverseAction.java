package actions;

import game.PlayManager;
import players.Player;

public class ReverseAction implements Action {

    @Override
    public void run(Player player) {
        PlayManager.getInstance().reversePlayDirection();
    }
}
