package my.poker.app.evaluator.hand.analyze;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestStraight {
    private static HandEvaluator eval;

    @BeforeClass
    public static void beforeClass() {
        eval = new HandEvaluator();
    }

    static Hand createStraightHand1() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEART)); // 10
        cards.add(new Card(Card.Rank.NINE, Card.Suit.CLUB)); // 9
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.HEART)); // 8
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.DIAMOND)); // 7
        cards.add(new Card(Card.Rank.SIX, Card.Suit.SPADE)); // 6
        return new Hand(cards); // 40
    }

    static Hand createStraightHand2() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.JACK, Card.Suit.SPADE)); // 11
        cards.add(new Card(Card.Rank.KING, Card.Suit.HEART)); // 13
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.HEART)); // 12
        cards.add(new Card(Card.Rank.ACE, Card.Suit.CLUB)); // 14
        cards.add(new Card(Card.Rank.TEN, Card.Suit.DIAMOND)); // 10
        return new Hand(cards); // 60
    }

    static Hand createStraightHand3() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEART)); // 3
        cards.add(new Card(Card.Rank.ACE, Card.Suit.CLUB)); // 1
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.DIAMOND)); // 5
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEART)); // 2
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.SPADE)); // 4
        return new Hand(cards); // 15
    }

    @Test
    public void testNotStraight() {
        Assert.assertTrue(eval.getStraightScore(TestHighCard.createHighCardHand1()) == 0);
        Assert.assertTrue(eval.getStraightScore(TestOnePair.createOnePairHand1()) == 0);
        Assert.assertTrue(eval.getStraightScore(TestTwoPair.createTwoPairHand1()) == 0);
        Assert.assertTrue(eval.getStraightScore(TestThreeOfAKind.createThreeOfAKindHand1()) == 0);
    }

    @Test
    public void testScore() {
        Assert.assertTrue(eval.getStraightScore(createStraightHand1()) < eval.getStraightScore(createStraightHand2()));
        Assert.assertTrue(eval.getStraightScore(createStraightHand1()) > eval.getStraightScore(createStraightHand3()));
    }

    @Test
    public void testDraw() {
        Assert.assertTrue(eval.getStraightScore(createStraightHand1()) == eval.getStraightScore(createStraightHand1()));
    }

    @Test
    public void testAce() {
        Assert.assertTrue(eval.getStraightScore(createStraightHand2()) != 0);
        Assert.assertTrue(eval.getStraightScore(createStraightHand3()) != 0);
    }
}
