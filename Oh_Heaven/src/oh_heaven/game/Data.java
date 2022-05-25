package oh_heaven.game;

import ch.aplu.jcardgame.CardGame;
import ch.aplu.jcardgame.Hand;
import ch.aplu.jcardgame.RowLayout;
import ch.aplu.jgamegrid.Actor;

public class Data extends CardGame {
    private Hand hand;
    private int score = 0;
    private int trick = 0;
    private int bid;
    private Actor scoreActor = new Actor();
    private RowLayout layout;

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

    public void addScore(int score) {this.score += score;}

    public void updateScores(int madeBidBonus) {
        addScore(trick);
        if (trick == bid) addScore(madeBidBonus);
    }

    public int getTrick() {
        return trick;
    }

    public void setTrick(int trick) {
        this.trick = trick;
    }

    public void addTrick(int trick) {
        this.trick += trick;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public void addBid(int bid) {this.bid += bid;}

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
}
