package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.CardAdapter;
import ch.aplu.jcardgame.CardListener;
import ch.aplu.jcardgame.Hand;

public class HumanAdapter implements IPlayerAdapter{
    public Data data;
    private Card selected = null;

    public HumanAdapter(Hand hand){
        data = new Data();
        data.setHand(hand);
        // Set up human player for interaction
        CardListener cardListener = new CardAdapter()  // Human Player plays card
        {
            public void leftDoubleClicked(Card card) { selected = card; data.getHand().setTouchEnabled(false); }
        };
        data.getHand().addCardListener(cardListener);
    }

    @Override
    public Card move() {
        return selected;
    }

    @Override
    public Data info() {
        return data;
    }

    public void setSelected(Card selected){
        this.selected = selected;
    }

    public Card getSelected() {
        return selected;
    }
}
