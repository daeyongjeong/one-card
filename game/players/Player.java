package game.players;

import game.cards.Card;
import game.cards.GeneralCard;
import game.cards.Suit;
import game.collections.CardList;
import game.Game;
import game.View;

public abstract class Player {
    protected String name;
    protected View view;
    protected CardList hand;
    protected CardList playableCards;

    protected Player(String name) {
        this.name = name;
        view = new View();
        hand = new CardList();
        playableCards = new CardList();
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public void turn(Game context) {
        showTurnMessage(context);
        playTurn(context);
        endTurn(context);
    }

    private void showTurnMessage(Game context) {
        view.showWhosTurn(name);
        Card lastCard = context.getLastCard();
        if (lastCard instanceof GeneralCard && ((GeneralCard) lastCard).isSevenCard())
            view.showTableCardWithChangedSuit(lastCard, context.getChangedSuit());
        else
            view.showTableCard(lastCard);
        if (context.isAttackState())
            view.showAttackLevel(context.getAttackLevel());
        view.showHandSize(hand.size());
    }

    private void playTurn(Game context) {
        if (isPlayable(context)) {
            playCard(context);
        } else if (context.isAttackState()) {
            int attackLevel = context.getAttackLevel();
            drawCard(context, attackLevel);
            context.resetAttackLevel();
        } else {
            drawCard(context, 1);
        }
    }

    private boolean isPlayable(Game context) {
        filterCard(context);
        return !playableCards.isEmpty();
    }

    private void filterCard(Game context) {
        playableCards.clear();
        for (Card card : hand) {
            if (card.isPlayable(context)) {
                playableCards.add(card);
            }
        }
    }

    private void playCard(Game context) {
        Card card = getNextPlayCard();
        view.showPlayerPlayMessage(name, card);
        card.play(context);
        hand.remove(card);
        context.playCard(card);
    }

    protected abstract Card getNextPlayCard();

    protected void drawCard(Game context, int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            Card card = context.draw();
            receiveCard(card);
        }
        view.showPlayerDrawMessage(name, numberOfCards);
        view.showHandSizeAfterDraw(hand.size());
    }

    public abstract Suit changeSuit();

    private void endTurn(Game context) {
        final int MAXIMUM_CARDS = 20;

        if (hand.isEmpty()) {
            view.showWinMessage(name);
            context.winPlayer(this);
        }

        if (hand.size() >= MAXIMUM_CARDS) {
            view.showLoseMessage(name);
            context.losePlayer(this);
        }
    }

    public String getName() {
        return name;
    }
}
