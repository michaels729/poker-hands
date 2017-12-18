package my.poker.app.evaluator.hand.analyze;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestEvaluateHands {
    private static HandEvaluator eval;

    @BeforeClass
    public static void beforeClass() {
        eval = new HandEvaluator();
    }

    @Test
    public void testMessages() {
        Assert.assertEquals("P1 won!", eval.getResult(TestStraightFlush.createStraightFlushHand1(), TestHighCard.createHighCardHand1()));
        Assert.assertEquals("P2 won!", eval.getResult(TestHighCard.createHighCardHand1(), TestStraightFlush.createStraightFlushHand1()));
        Assert.assertEquals("Draw...", eval.getResult(TestTwoPair.createTwoPairHand1(), TestTwoPair.createTwoPairHand1()));
    }

    @Test
    public void testRankLevels() {
        List<Hand> hands = Arrays.asList(
                TestStraightFlush.createStraightFlushHand1(),
                TestFourOfAKind.createFourOfAKindHand1(),
                TestFullHouse.createFullHouseHand1(),
                TestFlush.createFlushHand1(),
                TestStraight.createStraightHand1(),
                TestThreeOfAKind.createThreeOfAKindHand1(),
                TestTwoPair.createTwoPairHand1(),
                TestOnePair.createOnePairHand1(),
                TestHighCard.createHighCardHand1()
        );
        for (int i = 0; i < hands.size(); ++i) {
            for (int j = i; j < hands.size(); ++j) {
                if (i == j) {
                    Assert.assertEquals("Draw...", eval.getResult(hands.get(i), hands.get(j)));
                } else {
                    Assert.assertEquals("P1 won!", eval.getResult(hands.get(i), hands.get(j)));
                    Assert.assertEquals("P2 won!", eval.getResult(hands.get(j), hands.get(i)));
                }
            }
        }
    }
}
