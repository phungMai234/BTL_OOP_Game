package characters;

public class Balloon extends Entity {
    private boolean isLife = true;
    private boolean canDelete = true;
    private String path = "../BTL_OOP_Game/image/balloom_left1.png";

    public Balloon(int x, int y) {
        super(x, y);
    }

    public String getPath() {
        return path;
    }

    public boolean isLife() {
        return isLife;
    }

    public void setLife(boolean life) {
        isLife = life;
    }
}
