package  characters;

import javax.swing.*;

public class Wall extends Entity {
    private boolean canDelete = false;
     private String path = "../BTL_OOP_Game/image/wall.png";
    public Wall(int x, int y){
        super(x,y);
    }
    public String getPath() {
        return path;
    }
}
