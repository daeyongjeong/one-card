package game;

import cards.Card;
import cards.Suit;
import collections.Deck;
import collections.CardStack;

public class TableManager {
    private static TableManager instance;

    private CardStack stockPile;
    private CardStack discardPile;
    private int attackLevel;
    private Suit changedSuit;

    public static TableManager getInstance() {
        if (instance == null)
            instance = new TableManager();
        return instance;
    }

    private TableManager() {
        stockPile = new CardStack();
        discardPile = new CardStack();
        attackLevel = 0;
    }

    public void setupDiscardPile(Card card) {
        discardPile.push(card);
    }

    public void setupStockPile(Deck deck) {
        stockPile.addAll(deck);
    }

    public Card getLastCard() {
        return discardPile.peek();
    }

    public Suit getChangedSuit() {
        return changedSuit;
    }

    public boolean getAttackState() {
        if (attackLevel > 0)
            return true;
        return false;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public void setChangedSuit(Suit changedSuit) {
        this.changedSuit = changedSuit;
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

    public void addAttackLevel(int attackLevel) {
        this.attackLevel += attackLevel;
    }

    public void playCard(Card card) {
        discardPile.push(card);
    }

    public void resetAttackLevel() {
        attackLevel = 0;
    }
}
