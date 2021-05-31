package game;

import java.util.ArrayDeque;
import java.util.Deque;
import cards.Card;
import collections.Deck;
import players.Player;

public class Game {
    private final int NUMBER_OF_PLAYERS;
    private final int NUMBER_OF_INITIAL_CARDS;

    private Deck deck;
    private PlayManager playManager;
    private TableManager tableManager;

    public static void run() throws Exception {
        run(3);
    }

    public static void run(int numberOfPlayers) throws Exception {
        if (numberOfPlayers < 2 || numberOfPlayers > 5)
            throw new Exception("the number of players must be between 2 and 5");
        new Game(numberOfPlayers);
    }

    // setup game
    public Game(int numberOfPlayers) {
        NUMBER_OF_PLAYERS = numberOfPlayers;
        NUMBER_OF_INITIAL_CARDS = getNumberOfInitialCards(numberOfPlayers);

        setupDeck();
        setupPlayers();
        initialDeal();
        setupTable();
        faceUpFirstCard();
        faceDownLeftovers();
        System.out.println("setup done");
        play();
    }

    private int getNumberOfInitialCards(int numberOfPlayers) {
        if (numberOfPlayers == 2)
            return 7;
        else
            return 5;
    }

    private void setupDeck() {
        deck = new Deck();
        deck.shuffle();
    }

    private void setupPlayers() {
        playManager = PlayManager.getInstance();
        playManager.init(NUMBER_OF_PLAYERS);
        System.out.println(playManager.getActivePlayers().size());
    }

    private void initialDeal() {
        Deque<Player> activePlayers = playManager.getActivePlayers();
        for (int i = 0; i < NUMBER_OF_INITIAL_CARDS; i++) {
            for (Player player : activePlayers) {
                Card card = deck.draw();
                player.receiveCard(card);
            }
        }
    }

    private void setupTable() {
        tableManager = TableManager.getInstance();
    }

    private void faceUpFirstCard() {
        Card firstCard = deck.draw();
        tableManager.setupDiscardPile(firstCard);
    }

    private void faceDownLeftovers() {
        tableManager.setupStockPile(deck);
    }

    // update game
    private void play() {
        while (!isGameOver())
            gamePlay();
        gameOver();
        printResult();
    }

    private boolean isGameOver() {
        return playManager.isGameOver();
    }

    private void gamePlay() {
        Player player = playManager.getNextPlayer();
        player.playTurn();
    }

    private void gameOver() {
        Player player = playManager.getNextPlayer();
        playManager.playerLoses(player);
    }

    private void printResult() {
        Deque<Player> result = new ArrayDeque<Player>();
        result.addAll(playManager.getWinners());
        result.addAll(playManager.getLosers());
        for (Player player : result) {
            System.out.println(player.getName());
        }
    }
}
