package characters;

public class Bomer extends Entity{
    private boolean canDelete = true;
    private String path = "../BTL_OOP_Game/image/bomer.png";
    public Bomer(int x, int y){
        super(x,y);
    }

    public String getPath() {
        return path;
    }
}

