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
    private JLabel bomer = new JLabel();

    public static void main(String[] args){
        initGame _game = new initGame("../BTL_OOP_Game/level/level1.txt");
        System.out.println(_game._array.size());
        _game.setTitle("Test JFrame");
        _game.setSize((int) ((_game.width +0.4)*50), (_game.height+1)*50);
        _game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Stop/Close program on exit
        _game.setVisible(true);
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
        this.addKeyListener(this);
    }

    public JPanel addEntityToJpanel(){
        JPanel jPanel = new JPanel(null);

        Entity tmp = new Bomer(0,0);
        for(int i=0; i < this._array.size(); i++){
            if(_array.get(i) instanceof Bomer){
                tmp.set_x(_array.get(i).get_x());
                tmp.set_y(_array.get(i).get_y());
            }
            JLabel label1 = new JLabel();
            jPanel.add(label1);
            label1.setIcon(new ImageIcon( this._array.get(i).getPath()));
            label1.setBounds(_array.get(i).get_x()*50, _array.get(i).get_y()*50, 50, 50);
        }
//        JLabel label1 = new JLabel();
//        jPanel.add(label1);
//        label1.setIcon(new ImageIcon( tmp.getPath()));
//        System.out.println(tmp.get_x() + " " + tmp.get_y());
//        label1.setBounds(tmp.get_x()*50, tmp.get_y()*50, 50, 50);
        jPanel.setLayout(null);
        jPanel.setSize((int) ((this.width +0.4)*50), (this.height+1)*50);
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
    public void turnRight(Entity _tmp){
        Entity cur = new Tile(_tmp.get_x(), _tmp.get_y());
        JLabel jLabel = new JLabel();
        jPanel.add(jLabel);
        jLabel.setIcon(new ImageIcon(cur.getPath()));
        jLabel.setBounds((_tmp.get_x())*50, _tmp.get_y()*50, 50, 50);
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
        _tmp.set_x(_tmp.get_x()+1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
    }
    public void turnLeft(Entity _tmp){
        Entity cur = new Tile(_tmp.get_x(), _tmp.get_y());
        JLabel jLabel = new JLabel();
        jPanel.add(jLabel);
        jLabel.setIcon(new ImageIcon(cur.getPath()));
        jLabel.setBounds((_tmp.get_x())*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
        _tmp.set_x(_tmp.get_x()-1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);

    }
    public void turnUp(Entity _tmp){
        Entity cur = new Tile(_tmp.get_x(), _tmp.get_y());
        JLabel jLabel = new JLabel();
        jPanel.add(jLabel);
        jLabel.setIcon(new ImageIcon(cur.getPath()));
        jLabel.setBounds(_tmp.get_x()*50, (_tmp.get_y())*50, 50, 50);
        jPanel.add(bomer, 0);
        _tmp.set_y(_tmp.get_y()-1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
    }
    public void turnDown(Entity _tmp){
        Entity cur = new Tile(_tmp.get_x(), _tmp.get_y());
        JLabel jLabel = new JLabel();
        jPanel.add(jLabel);
        jLabel.setIcon(new ImageIcon(cur.getPath()));
        jLabel.setBounds(_tmp.get_x()*50, (_tmp.get_y())*50, 50, 50);
        jPanel.add(bomer, 0);
        _tmp.set_y(_tmp.get_y()+1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);

    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'a': {
                turnLeft(findEnity());
                break;
            }
            case 'd': {
                turnRight(findEnity());
                break;
            }
            case 'w': {
                turnUp(findEnity());
                break;
            }
            case 's': {
                turnDown(findEnity());
                break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}

