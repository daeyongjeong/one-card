public class User extends Player {
    @Override
    public void playCardWith(Card card) {
        hand.filter(card);
    }
}
