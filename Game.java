import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Game {
    private final int NUMBER_OF_PLAYERS;
    private final int NUMBER_OF_INITIAL_CARDS;

    private Deque<Player> activePlayers = new ArrayDeque<Player>();
    private Deque<Player> winners = new ArrayDeque<Player>();
    private Deque<Player> losers = new ArrayDeque<Player>();

    private Deck deck = new Deck();
    private Stack<Card> stockPile = new Stack<Card>();
    private Stack<Card> discardPile = new Stack<Card>();

    private boolean isPlayDirectionClockwise = true;
    private int attackLevel = 0;
    private Suit changedSuit;

    public Game() throws IOException {
        this(3);
    }

    public Game(int numberOfPlayers) throws IOException {
        if (numberOfPlayers < 2 || numberOfPlayers > 5)
            throw new IOException("the number of players must be between 2 and 5");

        NUMBER_OF_PLAYERS = numberOfPlayers;
        if (numberOfPlayers == 2)
            NUMBER_OF_INITIAL_CARDS = 7;
        else
            NUMBER_OF_INITIAL_CARDS = 5;

        activePlayers.add(new User());
        for (int i = 1; i < NUMBER_OF_PLAYERS; i++)
            activePlayers.add(new Player());
    }

    // main method
    public void run() {
        init();
        while (!isGameOver()) {
            play();
        }
    }

    public void init() {
        deck.shuffle();
        deal();
        discardPile.push(deck.draw());
        stockPile.addAll(deck);
    }

    public void deal() {
        for (int i = 0; i < NUMBER_OF_INITIAL_CARDS; i++) {
            for (Player player : activePlayers) {
                Card card = deck.draw();
                player.receiveCard(card);
            }
        }
    }

    public boolean isGameOver() {
        if (activePlayers.size() == 0)
            return true;
        return false;
    }

    public Player getNextPlayer() {
        if (isPlayDirectionClockwise)
            return activePlayers.removeFirst();
        else
            return activePlayers.removeLast();
    }

    public void play() {
        Player player = getNextPlayer();
        Card lastCard = discardPile.peek();
        if (player.isPlayableWith(lastCard)) {
            player.playCardWith(lastCard, changedSuit);
            if (lastCard.rank == Rank.SEVEN) {
                changedSuit = player.changeSuit();
            }
        } else if (attackLevel > 0) {
            while (attackLevel > 0) {
                Card card = draw();
                player.receiveCard(card);
                attackLevel--;
            }
        } else {
            Card card = draw();
            player.receiveCard(card);
        }
    }

    public Card draw() {
        if (stockPile.size() == 0) {
            Card card = discardPile.pop();
            stockPile.addAll(discardPile);
            // TODO: shuffle stockPile
            discardPile.clear();
            discardPile.push(card);
        }
        return stockPile.pop();
    }
}
