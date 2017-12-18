package my.poker.app.evaluator.hand.analyze;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestThreeOfAKind {
    private static HandEvaluator eval;

    @BeforeClass
    public static void beforeClass() {
        eval = new HandEvaluator();
    }

    static Hand createThreeOfAKindHand1() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEART)); // 5
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUB)); // 10
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEART)); // 10
        cards.add(new Card(Card.Rank.TEN, Card.Suit.DIAMOND)); // 10
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.SPADE)); // 5
        return new Hand(cards); // 40
    }

    static Hand createThreeOfAKindHand2() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.JACK, Card.Suit.CLUB)); // 11
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEART)); // 2
        cards.add(new Card(Card.Rank.JACK, Card.Suit.HEART)); // 11
        cards.add(new Card(Card.Rank.JACK, Card.Suit.SPADE)); // 11
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.DIAMOND)); // 5
        return new Hand(cards); // 40
    }

    static Hand createThreeOfAKindHand3() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.TWO, Card.Suit.CLUB));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.DIAMOND));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.SPADE));
        return new Hand(cards);
    }

    @Test
    public void testNotThreeOfAKind() {
        Assert.assertTrue(eval.getThreeOfAKindScore(TestHighCard.createHighCardHand1()) == 0);
        Assert.assertTrue(eval.getThreeOfAKindScore(TestOnePair.createOnePairHand1()) == 0);
        Assert.assertTrue(eval.getThreeOfAKindScore(TestTwoPair.createTwoPairHand1()) == 0);
    }

    @Test
    public void testRankPriority() {
        Assert.assertTrue(eval.getThreeOfAKindScore(createThreeOfAKindHand1()) < eval.getThreeOfAKindScore(createThreeOfAKindHand2()));
    }

    @Test
    public void testScore() {
        Assert.assertTrue(eval.getThreeOfAKindScore(createThreeOfAKindHand1()) > eval.getThreeOfAKindScore(createThreeOfAKindHand3()));
    }

    @Test
    public void testDraw() {
        Assert.assertTrue(eval.getThreeOfAKindScore(createThreeOfAKindHand1()) == eval.getThreeOfAKindScore(createThreeOfAKindHand1()));
    }
}
