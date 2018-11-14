package characters;

public class Bomb extends Entity{
    private boolean canDelete = true;
    private int timeExplore;
    private String path = "../BTL_OOP_Game/image/bomb.png";
    public Bomb(int x, int y){
        super(x,y);
        timeExplore = 30;

    }

    public int getTimeExplore() {
        return timeExplore;
    }

    public void setTimeExplore(int timeExplore) {
        this.timeExplore = timeExplore;
    }

    public String getPath() {
        return path;
    }
}

