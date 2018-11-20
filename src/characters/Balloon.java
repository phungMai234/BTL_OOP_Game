package characters;

public class Balloon extends Entity{
    private boolean canDelete = true;
    private String path = "../BTL_OOP_Game/image/balloom_left1.png";
    public Balloon(int x, int y){
        super(x,y);
    }

    public String getPath() {
        return path;
    }
}
