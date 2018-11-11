package src.characters;

import javax.swing.*;
import java.io.IOException;

public class Bomer extends Entity{

    private String path = "../BTL_OOP_Game/image/bomer.png";
    public Bomer(int x, int y){
        super(x,y);
    }

    public String getPath() {
        return path;
    }

    @Override
    public void render(String path) {

    }
}

