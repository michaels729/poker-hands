package my.poker.app.evaluator.hand.analyze;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hand {
    public static final int HAND_SIZE = 5;
    private List<Card> cards;

    public Hand(List<Card> cards) {
        Collections.sort(cards, Comparator.comparingInt(a -> a.getRank().ordinal()));
        this.cards = cards;
    }

    /**
     * The cards in the hand sorted in increasing rank order, with Aces as the
     * highest rank.
     *
     * @return a set of cards sorted by rank in increasing order
     */
    public List<Card> getCards() {
        return cards;
    }
}
