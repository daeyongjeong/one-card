package collections;

import java.util.Collections;
import java.util.LinkedList;
import cards.Card;

public class CardList extends LinkedList<Card> {
    public void shuffle() {
        Collections.shuffle(this);
    }

    public Card getCard() {
        return this.pop();
    }
}
