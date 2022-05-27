package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class SmartAdapter implements IPlayerAdapter{
    public Data data;
    private SmartPlayer smartPlayer;
    private Hand hand;

    public SmartAdapter(Hand hand) {
        data = new Data();
        data.setHand(hand);
        this.hand = hand;
        smartPlayer = new SmartPlayer();
    }

    @Override
    public Card move() {
        if (hand.isEmpty()) {
            return null;
        }
        // if you are the leader then can choose any card in hand
        else if (data.getLeader() == data.getPlayerNum()) {
            return smartPlayer.startingMove(data);
        }
        else {
            return smartPlayer.winningCard(hand, data);
        }
    }

    @Override
    public Data info() {
        return data;
    }
}
