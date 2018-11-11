package src.gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import src.characters.Bomer;
import src.characters.Entity;
import src.characters.Tile;
import src.characters.Wall;

import javax.swing.*;

public class initGame extends JFrame implements KeyListener{
    private ArrayList<Entity> _array = new ArrayList<Entity>();
    private JPanel jPanel;
    private int height, width;

    public static void main(String[] args){
        initGame _game = new initGame("../BTL_OOP_Game/level/level1.txt");
        System.out.println(_game._array.size());
        _game.setTitle("Test JFrame");
        _game.setSize((int) ((_game.width +0.4)*50), (_game.height+1)*50);
        _game.setLocationRelativeTo(null);
        _game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Stop/Close program on exit
        _game.setVisible(true);
        _game.addKeyListener(_game);
    }

    public initGame(String path){
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String infor = br.readLine();
            height = Integer.parseInt(infor.split(" ")[0]);
            width = Integer.parseInt(infor.split(" ")[1]);
            for(int i = 0; i < height; i++){
                String oneLine = br.readLine();
                char[] arr = oneLine.toCharArray();
                for(int j=0; j< width; j++){
                    if (arr[j] == '#') {
                        Entity _wall = new Wall(j, i);
                        _array.add(_wall);
                    }
                    if (arr[j] == ' ') {
                        Entity _tile = new Tile(j, i);
                        _array.add(_tile);
                    }
                    if (arr[j] == '*') {
                        Entity _bomer = new Bomer(j, i);
                        _bomer.setCanMove(true);
                        _array.add(_bomer);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        jPanel = this.addEntityToJpanel();
        add(jPanel);
    }

    public JPanel addEntityToJpanel(){
        JPanel jPanel = new JPanel(null);
        jPanel.setLayout(null);
        jPanel.setSize((int) ((this.width +0.4)*50), (this.height+1)*50);
        for(int i=0; i < this._array.size(); i++){
            if(_array.get(i) instanceof Bomer){
                System.out.println(this._array.get(i).get_x());
                System.out.println(this._array.get(i).get_y());
            }
            JLabel label1 = new JLabel();
            label1.setIcon(new ImageIcon( this._array.get(i).getPath()));
            label1.setBounds(_array.get(i).get_x()*50, _array.get(i).get_y()*50, 50, 50);
            jPanel.add(label1);
        }
        return jPanel;
    }

    public Entity findEnity(){
        for(int i=0;i<this._array.size(); i++){
            if(this._array.get(i).isCanMove()){
                return _array.get(i);
            }
        }
        return null;
    }
    public void change(Entity _tmp){
        System.out.println("x:    " + _tmp.get_x());
        System.out.println("y:      " + _tmp.get_y());
        int a = _tmp.get_x();
        _tmp.set_x(a+1);
        JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon( _tmp.getPath()));
        label1.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        this.jPanel.add(label1);
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
        change(findEnity());
    }

    public void keyReleased(KeyEvent e) {

    }
}

