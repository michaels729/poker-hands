package my.poker.app.evaluator.hand.analyze;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestHighCard {
    private static HandEvaluator eval;

    @BeforeClass
    public static void beforeClass() {
        eval = new HandEvaluator();
    }

    static Hand createHighCardHand1() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.ACE, Card.Suit.CLUB)); // 14
        cards.add(new Card(Card.Rank.THREE, Card.Suit.SPADE)); // 3
        cards.add(new Card(Card.Rank.JACK, Card.Suit.HEART)); // 11
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEART)); // 5
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.DIAMOND)); // 7
        return new Hand(cards); // Total: 40
    }

    static Hand createHighCardHand2() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.KING, Card.Suit.CLUB)); // 13
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.DIAMOND)); // 8
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEART)); // 5
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.HEART)); // 12
        cards.add(new Card(Card.Rank.TWO, Card.Suit.SPADE)); // 2
        return new Hand(cards); // Total: 40
    }

    static Hand createHighCardHand3() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.TWO, Card.Suit.CLUB));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.SIX, Card.Suit.DIAMOND));
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.SPADE));
        return new Hand(cards);
    }

    @Test
    public void testRankPriority() {
        Assert.assertTrue(eval.getHighCardScore(createHighCardHand1()) > eval.getHighCardScore(createHighCardHand2()));
    }

    @Test
    public void testScore() {
        Assert.assertTrue(eval.getHighCardScore(createHighCardHand1()) > eval.getHighCardScore(createHighCardHand3()));
    }

    @Test
    public void testDraw() {
        Assert.assertTrue(eval.getHighCardScore(createHighCardHand1()) == eval.getHighCardScore(createHighCardHand1()));
    }
}
