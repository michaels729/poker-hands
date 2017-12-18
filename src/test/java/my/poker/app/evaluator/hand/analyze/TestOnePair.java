package my.poker.app.evaluator.hand.analyze;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestOnePair {
    private static HandEvaluator eval;

    @BeforeClass
    public static void beforeClass() {
        eval = new HandEvaluator();
    }

    static Hand createOnePairHand1() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUB)); // 10
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEART)); // 10
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.DIAMOND)); // 8
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.HEART)); // 7
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.SPADE)); // 4
        return new Hand(cards); // 39
    }

    static Hand createOnePairHand2() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.CLUB)); // 12
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.HEART)); // 12
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.DIAMOND)); // 8
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEART)); // 5
        cards.add(new Card(Card.Rank.TWO, Card.Suit.SPADE)); // 2
        return new Hand(cards); // 39
    }

    static Hand createOnePairHand3() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.TWO, Card.Suit.CLUB));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.DIAMOND));
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.SPADE));
        return new Hand(cards);
    }

    @Test
    public void testNotOnePair() {
        Assert.assertTrue(eval.getOnePairScore(TestHighCard.createHighCardHand1()) == 0);
        Assert.assertTrue(eval.getOnePairScore(TestHighCard.createHighCardHand2()) == 0);
        Assert.assertTrue(eval.getOnePairScore(TestHighCard.createHighCardHand3()) == 0);
    }

    @Test
    public void testRankPriority() {
        Assert.assertTrue(eval.getOnePairScore(createOnePairHand1()) < eval.getOnePairScore(createOnePairHand2()));
    }

    @Test
    public void testScore() {
        Assert.assertTrue(eval.getOnePairScore(createOnePairHand1()) > eval.getOnePairScore(createOnePairHand3()));
    }

    @Test
    public void testDraw() {
        Assert.assertTrue(eval.getOnePairScore(createOnePairHand1()) == eval.getOnePairScore(createOnePairHand1()));
    }
}
