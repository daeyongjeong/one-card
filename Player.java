public class Player {
    protected Hand hand = new Hand();

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public Card playCardWith(Card card, Suit changedSuit) {
        hand.filterWith(card, changedSuit);
        // random card
        return null; //
    }

    public boolean isPlayableWith(Card card) {
        return true;
    }

    public Suit changeSuit() {
        // random suit
        return Suit.SPADES;
    }
}

/*
 * K 카드를 낸다 - 다음 순서를 자기 차례로 한다 - Q 카드를 낸다 - 다음 순서를 역방향으로 바꾼다 - J 카드를 낸다 - 다음 순서를
 * 하나 건너뛴다
 * 
 * - Joker 카드를 낸다. - 공격 수치를 5 올린다. - 방어할 수 없다. - A 카드를 낸다 - 공격 수치를 3 올린다. - A 또는
 * Joker로 방어할 수 있다. - 2 카드를 낸다 - 공격 수치를 2 올린다 - 2, A 또는 Joker로 방어할 수 있다.
 * 
 * - 7 카드를 낸다 - 다음에 내야 할 카드의 모양을 정할 수 있다.
 * 
 * - 나머지 카드를 낸다 - X
 */
