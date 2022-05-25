package oh_heaven.game;

import ch.aplu.jcardgame.CardGame;
import ch.aplu.jcardgame.Hand;
import ch.aplu.jcardgame.RowLayout;
import ch.aplu.jgamegrid.Actor;

public class Data extends CardGame {
    private Hand hand;
    private int score;
    private int trick;
    private int bid;
    private Actor scoreActor;
    private RowLayout layout;
    private boolean enforceRules = false;

    public Data() {
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTrick() {
        return trick;
    }

    public void setTrick(int trick) {
        this.trick = trick;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public Actor getScoreActor() {
        return scoreActor;
    }

    public void setScoreActor(Actor scoreActor) {
        this.scoreActor = scoreActor;
    }

    public RowLayout getLayout() {
        return layout;
    }

    public void setLayout(RowLayout layout) {
        this.layout = layout;
    }

    public boolean isEnforceRules() {
        return enforceRules;
    }

    public void setEnforceRules(boolean enforceRules) {
        this.enforceRules = enforceRules;
    }
}
