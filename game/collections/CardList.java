package game.collections;

import java.util.Collections;
import java.util.LinkedList;

import game.cards.Card;

public class CardList extends LinkedList<Card> {
    public void shuffle() {
        Collections.shuffle(this);
    }

    public Card getCard() {
        return this.pop();
    }
}
