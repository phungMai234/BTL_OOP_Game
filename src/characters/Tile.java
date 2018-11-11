package src.characters;

public class Tile extends Entity {
    private String path = "../BTL_OOP_Game/image/grass.png";
    public Tile(int x, int y){
        super(x,y);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void render(String path) {

    }
}
