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
    private Entity _array[][] = new Entity[50][50];
    private JLabel listLabel[][] = new JLabel[50][50];
    private JPanel jPanel = new JPanel();
    private int height, width;
    private JLabel bomer = new JLabel();
    private Entity _bomer;

    public static void main(String[] args){
        initGame _game = new initGame("../BTL_OOP_Game/level/level1.txt");
        _game.setTitle("Test JFrame");
        _game.setSize( (int)(_game.width+0.4)*50, (_game.height+1)*50);
        _game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _game.setVisible(true);
    }

    public initGame(String path){
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String infor = br.readLine();
            height = Integer.parseInt(infor.split(" ")[0]);
            width = Integer.parseInt(infor.split(" ")[1]);
            System.out.println(height + " " + width);
            for(int i = 0; i < height; i++){
                String oneLine = br.readLine();
                char[] arr = oneLine.toCharArray();
                for(int j=0; j< width; j++){
                    if (arr[j] == '#') {
                        Entity _wall = new Wall(j, i);
                        _array[i][j] = _wall;
                        JLabel label = new JLabel();
                        label.setIcon(new ImageIcon( _wall.getPath()));
                        label.setBounds(_wall.get_x()*50, _wall.get_y()*50, 50, 50);
                        jPanel.add(label);
                    }
                    if (arr[j] == ' ') {
                        Entity _tile = new Tile(j, i);
                        _array[i][j] = _tile;
                        JLabel label = new JLabel();
                        label.setIcon(new ImageIcon( _tile.getPath()));
                        label.setBounds(_tile.get_x()*50, _tile.get_y()*50, 50, 50);
                        jPanel.add(label);
                    }
                    if (arr[j] == '*') {
                        _bomer = new Bomer(j, i);
                        _bomer.setCanMove(true);
                        _array[i][j] = _bomer;
                        bomer.setIcon(new ImageIcon( _bomer.getPath()));
                        bomer.setBounds(_bomer.get_x()*50, _bomer.get_y()*50, 50, 50);
                        jPanel.add(bomer);
                    }
                }
            }
            jPanel.setLayout(null);
            this.add(jPanel);
            this.addKeyListener(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Entity findEnity(){
        for(int i=0;i<_array.length; i++){
            for(int j=0 ; j<_array[i].length; j++){
                if(_array[i][j]!=null){
                    if(_array[i][j] instanceof Bomer){
                        System.out.println(i + " " + j);
                        return _array[i][i];
                    }
                }
            }
        }
        return null;
    }
    public void turnRight(Entity _tmp){
        System.out.println(_tmp.get_x() + " " + _tmp.get_y());
        Entity cur = new Tile(_tmp.get_x(), _tmp.get_y());
        JLabel jLabel = new JLabel();
        jPanel.add(jLabel);
        jLabel.setLayout(null);
        jLabel.setIcon(new ImageIcon(cur.getPath()));
        jLabel.setBounds((_tmp.get_x())*50, _tmp.get_y()*50, 50, 50);
        _tmp.set_x(_tmp.get_x()+1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
    }
    public void turnLeft(Entity _tmp){
        System.out.println(_tmp.get_x() + " " + _tmp.get_y());
        Entity cur = new Tile(_tmp.get_x(), _tmp.get_y());
        JLabel jLabel = new JLabel();
        jLabel.setLayout(null);
        jPanel.add(jLabel);
        jLabel.setIcon(new ImageIcon(cur.getPath()));
        jLabel.setBounds((_tmp.get_x())*50, _tmp.get_y()*50, 50, 50);

        _tmp.set_x(_tmp.get_x()-1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);

    }
    public void turnUp(Entity _tmp){
        System.out.println(_tmp.get_x() + " " + _tmp.get_y());
        Entity cur = new Tile(_tmp.get_x(), _tmp.get_y());
        JLabel jLabel = new JLabel();
        jLabel.setLayout(null);
        jPanel.add(jLabel);
        jLabel.setIcon(new ImageIcon(cur.getPath()));
        jLabel.setBounds(_tmp.get_x()*50, (_tmp.get_y())*50, 50, 50);
        _tmp.set_y(_tmp.get_y()-1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
    }
    public void turnDown(Entity _tmp){
        System.out.println(_tmp.get_x() + " " + _tmp.get_y());
        Entity cur = new Tile(_tmp.get_x(), _tmp.get_y());
        JLabel jLabel = new JLabel();
        jLabel.setLayout(null);
        jPanel.add(jLabel);
        jLabel.setIcon(new ImageIcon(cur.getPath()));
        jLabel.setBounds(_tmp.get_x()*50, (_tmp.get_y())*50, 50, 50);

        _tmp.set_y(_tmp.get_y()+1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'a': {
                turnLeft(_bomer);
                break;
            }
            case 'd': {
                turnRight(_bomer);
                break;
            }
            case 'w': {
                turnUp(_bomer);
                break;
            }
            case 's': {
                turnDown(_bomer);
                break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}

