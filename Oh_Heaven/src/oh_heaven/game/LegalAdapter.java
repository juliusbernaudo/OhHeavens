package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class LegalAdapter implements IPlayerAdapter{
    public Data data;
    private LegalPlayer legalPlayer;
    private Hand hand;

    public LegalAdapter(Hand hand){
        data = new Data();
        data.setHand(hand);
        legalPlayer = new LegalPlayer();
        this.hand = hand;
    }

    @Override
    public Card move() {
        if (hand.isEmpty()) {
            return null;
        }
        // if you are the leader then can choose any card in hand
        else if (data.getLeader() == data.getPlayerNum()) {
            return legalPlayer.randomCard(hand);
        }
        // if not then choose a legal card
        else {
            return legalPlayer.legalCard(hand, data);
        }

    }

    @Override
    public Data info() {
        return data;
    }
}
