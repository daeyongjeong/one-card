package game;

import java.util.ArrayDeque;
import java.util.Deque;

import game.players.Computer;
import game.players.Player;
import game.players.User;

public class PlayerManager {
    private Deque<Player> activePlayers;
    private Deque<Player> winners;
    private Deque<Player> losers;
    private boolean isPlayDirectionClockwise;
    private boolean isOneMoreTurn;
    private boolean isJumpTurn;

    public PlayerManager(int numberOfPlayers) {
        isPlayDirectionClockwise = true;
        isOneMoreTurn = false;
        isJumpTurn = false;
        activePlayers = new ArrayDeque<>();
        winners = new ArrayDeque<>();
        losers = new ArrayDeque<>();

        activePlayers.add(new User("User"));
        for (int i = 0; i < numberOfPlayers - 1; i++)
            activePlayers.add(new Computer("Com" + (i + 1)));
    }

    public Deque<Player> getActivePlayers() {
        return activePlayers;
    }

    public Player getNextPlayer() {
        Player nextPlayer;
        if (isPlayDirectionClockwise)
            nextPlayer = getNextPlayerClockwise();
        else
            nextPlayer = getNextPlayerCounterClockwise();
        reset();
        return nextPlayer;
    }

    private Player getNextPlayerClockwise() {
        Player nextPlayer;
        if (isOneMoreTurn) {
            rotateNextPlayerCounterClockwise();
            nextPlayer = rotateNextPlayerClockwise();
        } else {
            if (isJumpTurn)
                rotateNextPlayerClockwise();
            nextPlayer = rotateNextPlayerClockwise();
        }
        return nextPlayer;
    }

    private Player getNextPlayerCounterClockwise() {
        Player nextPlayer;
        if (isOneMoreTurn) {
            rotateNextPlayerClockwise();
            nextPlayer = rotateNextPlayerCounterClockwise();
        } else {
            if (isJumpTurn)
                rotateNextPlayerCounterClockwise();
            nextPlayer = rotateNextPlayerCounterClockwise();
        }
        return nextPlayer;
    }

    private Player rotateNextPlayerClockwise() {
        Player nextPlayer = activePlayers.removeFirst();
        activePlayers.addLast(nextPlayer);
        return nextPlayer;
    }

    private Player rotateNextPlayerCounterClockwise() {
        Player nextPlayer = activePlayers.removeLast();
        activePlayers.addFirst(nextPlayer);
        return nextPlayer;
    }

    /** actions */
    public void setJumpTurn() {
        isJumpTurn = true;
    }

    public void setOneMoreTurn() {
        isOneMoreTurn = true;
    }

    public void reversePlayDirection() {
        isPlayDirectionClockwise = !isPlayDirectionClockwise;
        getNextPlayer();
    }

    private void reset() {
        isOneMoreTurn = false;
        isJumpTurn = false;
    }

    /** game over */
    public void winPlayer(Player player) {
        activePlayers.remove(player);
        winners.addLast(player);
    }

    public void losePlayer(Player player) {
        activePlayers.remove(player);
        losers.addFirst(player);
    }

    public boolean isGameOver() {
        return activePlayers.size() == 1;
    }

    public Deque<Player> getPlayersByRanking() {
        Deque<Player> result = new ArrayDeque<>();
        result.addAll(winners);
        result.addAll(activePlayers);
        result.addAll(losers);
        return result;
    }
}
