package oh_heaven.game;

public class LegalAdapter implements IPlayerAdapter{
    public Data data;

    public LegalAdapter(int id){
        data = new Data();
    }

    @Override
    public void move() {

    }

    @Override
    public Data info() {
        return data;
    }
}
