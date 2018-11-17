package characters;

public class Brick extends Entity {
    private boolean canDelete = true;
    private String path = "../BTL_OOP_Game/image/brick_a.png";
    public Brick(int x, int y){
        super(x,y);
    }

    public String getPath() {
        return path;
    }
}
