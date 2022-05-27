package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

import static oh_heaven.game.Oh_Heaven.random;

public class LegalPlayer {
    public static Card randomCard(Hand hand){
        int x = random.nextInt(hand.getNumberOfCards());
        return hand.get(x);
    }

    // return random Card from ArrayList
    public static Card randomCard(ArrayList<Card> list){
        int x = random.nextInt(list.size());
        return list.get(x);
    }

    // looks in hand if there is a card with trump suit
    // if not, look if there is a card with suit matching lead card suit
    // if not then pick random from hand
    public static Card legalCard(Hand hand, Data data) {
        ArrayList<Card> trumpSuitsInHand;
        ArrayList<Card> leadSuitsInHand;
        trumpSuitsInHand = hand.getCardsWithSuit(data.getTrump());
        leadSuitsInHand = hand.getCardsWithSuit(data.getLeadSuit());

        if (!trumpSuitsInHand.isEmpty()) {
            return randomCard(trumpSuitsInHand);
        }
        else if (!leadSuitsInHand.isEmpty()) {
            return randomCard(leadSuitsInHand);
        }
        else {
            return randomCard(hand);
        }

    }


}
