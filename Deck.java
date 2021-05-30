import java.util.LinkedList;
import java.util.Collections;

public class Deck extends LinkedList<Card> {
    public void shuffle() {
        Collections.shuffle(this);
    }

    public Card draw() {
        return null;
    }
}
