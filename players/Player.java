package players;

import cards.Card;
import cards.Suit;
import collections.CardList;
import game.PlayManager;
import game.TableManager;
import game.TurnView;

public abstract class Player {
    protected String name;
    protected TurnView view;
    protected CardList hand;
    protected CardList playableCards;

    public Player(String name) {
        this.name = name;
        view = TurnView.getInstance();
        hand = new CardList();
        playableCards = new CardList();
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public void playTurn() {
        view.showWhosTurn(name);
        view.showTableCard(getTableCard());
        view.showHandSize(hand.size());

        if (isPlayable()) {
            playCard();
        } else if (isInAttackState()) {
            int attackLevel = getAttackLevel();
            drawCards(attackLevel);
            resetAttackLevel();
        } else {
            drawCard();
        }
        endTurn();
    }

    private Card getTableCard() {
        return TableManager.getInstance().getLastCard();
    }

    private boolean isPlayable() {
        filterCard();
        if (playableCards.size() == 0)
            return false;
        return true;
    }

    private void filterCard() {
        Card criteria = TableManager.getInstance().getLastCard();
        playableCards.clear();
        for (Card card : hand) {
            if (card.canBePlayedWith(criteria)) {
                playableCards.add(card);
            }
        }
    }

    protected boolean isInAttackState() {
        return TableManager.getInstance().getAttackState();
    }

    private int getAttackLevel() {
        return TableManager.getInstance().getAttackLevel();
    }

    private void resetAttackLevel() {
        TableManager.getInstance().resetAttackLevel();
    }

    private void playCard() {
        Card card = getNextPlayCard();
        card.onPlay(this);
        hand.remove(card);
        TableManager.getInstance().playCard(card);
    }

    protected abstract Card getNextPlayCard();

    public abstract Suit changeSuit();

    protected void drawCards(int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            Card card = TableManager.getInstance().draw();
            receiveCard(card);
        }
    }

    protected void drawCard() {
        drawCards(1);
    }

    private void endTurn() {
        final int MAXIMUM_CARDS = 20;

        if (hand.isEmpty())
            PlayManager.getInstance().playerWins(this);
        if (hand.size() >= MAXIMUM_CARDS)
            PlayManager.getInstance().playerLoses(this);
    }

    public String getName() {
        return name;
    }
}
