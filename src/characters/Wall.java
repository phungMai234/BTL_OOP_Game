package src.characters;

import javax.swing.*;

public class Wall extends Entity {
     private String path = "C:\\Users\\ADMIN\\Desktop\\BTL_OOP_Game\\image\\wall.png";
    public Wall(int x, int y){
        super(x,y);
    }
    @Override
    public void render(String path) {

    }

    public String getPath() {
        return path;
    }
}
