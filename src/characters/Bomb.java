package characters;

public class Bomb extends Entity {
    private boolean canDelete = true;
    private String path = "../BTL_OOP_Game/image/bomb.png";

    public Bomb(int x, int y) {
        super(x, y);
    }

    public String getPath() {
        return path;
    }
}

