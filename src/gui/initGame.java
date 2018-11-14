package gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import characters.Entity;
import characters.Wall;
import characters.*;

import javax.swing.*;

public class initGame extends JFrame implements KeyListener{
    private Entity _array[][] = new Entity[50][50];
    private JPanel jPanel = new JPanel();
    private int height, width;
    private JLabel bomer = new JLabel();
    private Entity _bomer;

    public initGame()
    {
        initGame _game = new initGame("../BTL_OOP_Game/level/level1.txt");
        _game.setTitle("Test JFrame");
        _game.setSize( (int)((_game.width+0.4)*50), (_game.height+1)*50);
        _game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _game.setVisible(true);
    }

    public JLabel creatLabelEntity(Entity _tmp)
    {
        JLabel jLabel = new JLabel(new ImageIcon(_tmp.getPath()));
        jLabel.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        return jLabel;
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

                        jPanel.add(creatLabelEntity(_wall));
                    }
                    if (arr[j] == ' ') {

                        Entity _tile = new Tile(j, i);
                        _array[i][j] = _tile;

                        jPanel.add(creatLabelEntity(_tile));
                    }
                    if (arr[j] == '*') {

                        _bomer = new Bomer(j, i);
                        _bomer.setCanMove(true);
                        _array[i][j] = _bomer;

                        bomer = creatLabelEntity(_bomer);
                        jPanel.add(bomer);

                        Entity _tile = new Tile(j, i);
                        jPanel.add(creatLabelEntity(_tile));
                    }
                    if (arr[j] == '@') {
                        /*ghi đè cái gạch lên cỏ*/
                        Entity _brick = new Brick(j, i);
                        Entity _tile = new Tile(j, i);
                        _array[i][j] = _brick;

                        jPanel.add(creatLabelEntity(_brick));
                        jPanel.add(creatLabelEntity(_tile));
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


    public Entity findEnity(){// k dung
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
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());//mot mik dong nay can het cai co

        _tmp.set_x(_tmp.get_x()+1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0); // index = 0 laf j
    }
    public void turnLeft(Entity _tmp){
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_x(_tmp.get_x()-1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
    }
    public void turnUp(Entity _tmp){
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_y(_tmp.get_y()-1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
    }
    public void turnDown(Entity _tmp){
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_y(_tmp.get_y()+1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
    }
    public void getBomb(Entity _tmp)
    {
        _array[_tmp.get_y()][_tmp.get_x()] = new Bomb(_tmp.get_y(), _tmp.get_x());
        JLabel jLabel1 = new JLabel();
        jLabel1.setIcon(new ImageIcon( _array[_tmp.get_y()][_tmp.get_x()].getPath()));
        jLabel1.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(jLabel1, 0);


    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (e.getKeyChar()){
            case 'a': {
                if(checkMove('a', _bomer)){
                    turnLeft(_bomer);
                }
                break;
            }
            case 'd': {
                if(checkMove('d', _bomer)){
                    turnRight(_bomer);
                }
                break;
            }
            case 'w': {
                if(checkMove('w', _bomer)){
                    turnUp(_bomer);
                }
                break;
            }
            case 's': {
                if(checkMove('s', _bomer)){
                    turnDown(_bomer);
                }
                break;
            }
            case KeyEvent.VK_SPACE:
            {
                getBomb(_bomer);
                break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    private boolean checkMove(char tmp, Entity _tmp){
        if(tmp == 'a' && !(this._array[_tmp.get_y()][_tmp.get_x()-1] instanceof Tile)){
            return false;
        }
        if(tmp == 's' && !(this._array[_tmp.get_y()+1][_tmp.get_x()] instanceof Tile)){
            return false;
        }
        if(tmp == 'd' && !(this._array[_tmp.get_y()][_tmp.get_x()+1] instanceof Tile)){
            return false;
        }
        if(tmp == 'w' && !(this._array[_tmp.get_y()-1][_tmp.get_x()] instanceof Tile)){
            return false;
        }
        return true;
    }
}

