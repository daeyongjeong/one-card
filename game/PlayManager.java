package game;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

import players.*;

public class PlayManager {
    private static PlayManager instance;

    private Deque<Player> activePlayers;
    private Deque<Player> winners;
    private Deque<Player> losers;
    private boolean isPlayDirectionClockwise;
    private boolean isOneMoreTurn;
    private boolean isJumpTurn;

    public static PlayManager getInstance() {
        if (instance == null)
            instance = new PlayManager();
        return instance;
    }

    private PlayManager() {
        isPlayDirectionClockwise = true;
        isOneMoreTurn = false;
        isJumpTurn = false;
        activePlayers = new ArrayDeque<Player>();
        winners = new ArrayDeque<Player>();
        losers = new ArrayDeque<Player>();
    }

    public void init(int numberOfPlayers) {
        activePlayers.add(new User("User"));
        for (int i = 0; i < numberOfPlayers - 1; i++)
            activePlayers.add(new Computer("Com" + (i + 1)));
    }

    public Deque<Player> getActivePlayers() {
        return activePlayers;
    }

    public boolean isGameOver() {
        if (activePlayers.size() == 1)
            return true;
        return false;
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
            nextPlayer = rotateNextPlayerCounterClockwise();
            nextPlayer = rotateNextPlayerClockwise();
        } else {
            if (isJumpTurn)
                nextPlayer = rotateNextPlayerClockwise();
            nextPlayer = rotateNextPlayerClockwise();
        }
        return nextPlayer;
    }

    private Player getNextPlayerCounterClockwise() {
        Player nextPlayer;
        if (isOneMoreTurn) {
            nextPlayer = rotateNextPlayerClockwise();
            nextPlayer = rotateNextPlayerCounterClockwise();
        } else {
            if (isJumpTurn)
                nextPlayer = rotateNextPlayerCounterClockwise();
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

    private void reset() {
        isOneMoreTurn = false;
        isJumpTurn = false;
    }

    public void playerWins(Player player) {
        activePlayers.remove(player);
        winners.addLast(player);
    }

    public void playerLoses(Player player) {
        activePlayers.remove(player);
        losers.addFirst(player);
    }

    public void setJumpTurn() {
        isJumpTurn = true;
    }

    public void setOneMoreTurn() {
        isOneMoreTurn = true;
    }

    public void reversePlayDirection() {
        isPlayDirectionClockwise = !isPlayDirectionClockwise;
    }

    public Deque<Player> getWinners() {
        return winners;
    }

    public Deque<Player> getLosers() {
        return losers;
    }
}
