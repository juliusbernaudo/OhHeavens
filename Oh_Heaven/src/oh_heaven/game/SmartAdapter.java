package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class SmartAdapter implements IPlayerAdapter{
    private Data data;

    public SmartAdapter(Hand hand) {
        data = new Data();
        data.setHand(hand);
    }

    @Override
    public Card move() {
        return null;
    }

    @Override
    public Data info() {
        return null;
    }
}
