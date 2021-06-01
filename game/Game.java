package game;

import game.cards.Card;
import game.cards.GeneralCard;
import game.cards.Suit;
import game.collections.CardStack;
import game.collections.Deck;
import game.players.Player;

public class Game {
    private View view = new View();
    private PlayerManager playerManager;
    private Player currentPlayer;

    private Deck deck;
    private CardStack stockPile;
    private CardStack discardPile;

    private int attackLevel;
    private Suit changedSuit;

    /** game logic */
    public Game(int numberOfPlayers) {
        init(numberOfPlayers);
        play();
        gameOver();
    }

    private void init(int numberOfPlayers) {
        setupDeck();
        setupPlayers(numberOfPlayers);
        initialDeal(numberOfPlayers);
        faceUpFirstCard();
        faceDownLeftovers();
        resetAttackLevel();
    }

    private void play() {
        while (!playerManager.isGameOver()) {
            currentPlayer = playerManager.getNextPlayer();
            currentPlayer.turn(this);
            sleep(1000);
        }
    }

    private void gameOver() {
        view.showRanking(playerManager.getPlayersByRanking());
        sleep(3000);
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms); // for slow down game speed
        } catch (InterruptedException e) {
        }
    }

    /** init */
    private void setupDeck() {
        deck = new Deck();
        deck.shuffle();
    }

    private void setupPlayers(int numberOfPlayers) {
        playerManager = new PlayerManager(numberOfPlayers);
    }

    private void initialDeal(int numberOfPlayers) {
        for (int i = 0; i < getNumberOfInitialCards(numberOfPlayers); i++) {
            for (Player player : playerManager.getActivePlayers()) {
                Card card = deck.draw();
                player.receiveCard(card);
            }
        }
    }

    private int getNumberOfInitialCards(int numberOfPlayers) {
        if (numberOfPlayers == 2 || numberOfPlayers == 3)
            return 7;
        return 5;
    }

    private void faceUpFirstCard() {
        discardPile = new CardStack();
        Card card = deck.draw();
        discardPile.push(card);

        if (card instanceof GeneralCard && ((GeneralCard) card).isSevenCard())
            changedSuit = ((GeneralCard) card).getSuit();
    }

    private void faceDownLeftovers() {
        stockPile = new CardStack();
        stockPile.addAll(deck);
    }

    /** players */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void winPlayer(Player player) {
        playerManager.winPlayer(player);
    }

    public void losePlayer(Player player) {
        playerManager.losePlayer(player);
    }

    /** cards */
    public Card getLastCard() {
        return discardPile.peek();
    }

    public Card draw() {
        if (stockPile.empty()) {
            Card temp = discardPile.pop();
            stockPile.addAll(discardPile);
            stockPile.shuffle();
            discardPile.clear();
            discardPile.push(temp);
        }
        return stockPile.pop();
    }

    public void playCard(Card card) {
        discardPile.push(card);
    }

    /** actions */
    public boolean isAttackState() {
        return attackLevel > 0;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public void addAttackLevel(int attackLevel) {
        this.attackLevel += attackLevel;
    }

    public void resetAttackLevel() {
        attackLevel = 0;
    }

    public Suit getChangedSuit() {
        return changedSuit;
    }

    public void setChangedSuit(Suit changedSuit) {
        this.changedSuit = changedSuit;
        view.showChangedSuit(changedSuit);
    }

    public void setJumpTurn() {
        playerManager.setJumpTurn();
    }

    public void setOneMoreTurn() {
        playerManager.setOneMoreTurn();
    }

    public void reversePlayDirection() {
        playerManager.reversePlayDirection();
    }
}
