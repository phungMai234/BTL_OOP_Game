package src.gui;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import src.characters.Entity;
import src.characters.Tile;
import src.characters.Wall;

import javax.swing.*;

public class initGame {
    private ArrayList<Entity> game = new ArrayList<Entity>();
    private int height, width;
    public static void main(String[] args){
        initGame abc = new initGame();
        abc.loadMap("C:/Users/ADMIN/Desktop/BTL_OOP_Game/level/level1.txt");

        JFrame jFrame = new JFrame("Oop");
        JPanel jPanel = new JPanel(null);

        jPanel.setSize((abc.width)*64, (abc.height)*64);
        jFrame.setSize((abc.width)*64, (abc.height)*64);
        for(int i=0; i < abc.game.size(); i++){
            if(abc.game.get(i) instanceof Wall){
                JLabel label1 = new JLabel();
                label1.setIcon(new ImageIcon(((Wall) abc.game.get(i)).getPath()));
                label1.setBounds(abc.game.get(i).get_x()*64, abc.game.get(i).get_y()*64, 64, 64);
                label1.setBackground(Color.red);
                jPanel.add(label1);
            }
            if(abc.game.get(i) instanceof Tile){
                JLabel label1 = new JLabel();
                label1.setIcon(new ImageIcon(((Tile) abc.game.get(i)).getPath()));
                label1.setBounds(abc.game.get(i).get_x()*64, abc.game.get(i).get_y()*64, 64, 64);
                label1.setBackground(Color.red);
                jPanel.add(label1);
            }
        }
        jFrame.add(jPanel);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void loadMap(String path){
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String infor = br.readLine();
            this.height = Integer.parseInt(infor.split(" ")[0]);
            this.width = Integer.parseInt(infor.split(" ")[1]);
            for(int i = 0; i < height; i++){
                String oneLine = br.readLine();
                char[] arr = oneLine.toCharArray();
                for(int j=0; j< width; j++){
                    if(arr[j] == '#'){
                        Entity _wall = new Wall(j, i);
                        game.add(_wall);
                    }
                    if(arr[j] == ' '){
                        Entity _tile = new Tile(j, i);
                        game.add(_tile);
                    }
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
