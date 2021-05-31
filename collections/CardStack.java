package collections;

import java.util.Collections;
import java.util.Stack;
import cards.Card;

public class CardStack extends Stack<Card> {
    public void shuffle() {
        Collections.shuffle(this);
    }
}
