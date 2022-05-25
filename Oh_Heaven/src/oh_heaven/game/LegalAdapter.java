package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class LegalAdapter implements IPlayerAdapter{
    public Data data;

    public LegalAdapter(Hand hand){
        data = new Data();
        data.setHand(hand);
    }

    @Override
    public Card move() {
        return null;
    }

    @Override
    public Data info() {
        return data;
    }
}
