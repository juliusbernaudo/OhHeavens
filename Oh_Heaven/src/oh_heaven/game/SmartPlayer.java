package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

import static oh_heaven.game.Oh_Heaven.random;

public class SmartPlayer {
    // uses different methods to determine a smart play
    public static Card winningCard(Hand hand, Data data) {
        ArrayList<Card> trumpSuitsInHand;
        ArrayList<Card> leadSuitsInHand;
        trumpSuitsInHand = hand.getCardsWithSuit(data.getTrump());
        leadSuitsInHand = hand.getCardsWithSuit(data.getLeadSuit());

        // play smartest lead suit card
        if (hand.getNumberOfCardsWithSuit(data.getLeadSuit()) > 0) {
            return winningSuitCard(leadSuitsInHand, data);
        }
        // play smartest trump suit card
        else if (!trumpSuitsInHand.isEmpty()) {
            return winningSuitCard(trumpSuitsInHand, data);
        }
        // play worst card in hand
        else {
            return worstCard(data);
        }
    }

    // gets the lowest rank card in the players hand
    public static Card worstCard(Data data) {
        Card worstCard = data.getHand().get(0);
        for (int i = 1; i < data.getNumberOfCards(); i++) {
            if (rankGreater(worstCard, data.getHand().get(i))) {
                worstCard = data.getHand().get(i);
            }
        }
        return worstCard;
    }

    // Gives the best card possible
    // Preferences using Trump suit cards first as if a player does not have the lead Suit they may be able to win
    // using the trump suit
    public static Card startingMove(Data data) {
        ArrayList<Card> trumpSuitsInHand;
        trumpSuitsInHand = data.getHand().getCardsWithSuit(data.getTrump());
        Card bestCard;
        if (!trumpSuitsInHand.isEmpty()) {
            bestCard = trumpSuitsInHand.get(0);
            for (int i = 1; i < trumpSuitsInHand.size(); i++) {
                if (rankGreater(trumpSuitsInHand.get(i), bestCard)) {
                    bestCard = trumpSuitsInHand.get(i);
                }
            }
        } else {
            bestCard = data.getHand().get(0);
            for (int i = 1; i < data.getNumberOfCards(); i++) {
                if (rankGreater(data.getHand().get(i), bestCard)) {
                    bestCard = data.getHand().get(i);
                }
            }
        }
        return bestCard;
    }

    // Gives back a card of the same suit that will beat the current winning card
    // Else give back the worst legal ranking card
    public static Card winningSuitCard(ArrayList<Card> givenSuitsInHand, Data data) {
        Card worstSuitCard = null;
        for (int i = 0; i < givenSuitsInHand.size(); i++) {
            worstSuitCard = givenSuitsInHand.get(0);
            if (rankGreater(givenSuitsInHand.get(i), data.getCurrentWinningCard())) {
                return givenSuitsInHand.get(i);
            }
            if (rankGreater(worstSuitCard, givenSuitsInHand.get(i))) {
                worstSuitCard = givenSuitsInHand.get(i);
            }
        }
        return worstSuitCard;
    }

    public static boolean rankGreater(Card card1, Card card2) {
        return card1.getRankId() < card2.getRankId(); // Warning: Reverse rank order of cards (see comment on enum)
    }
}
