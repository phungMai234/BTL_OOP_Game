package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import characters.Entity;
import characters.Wall;
import characters.*;

import javax.swing.*;

public class initGame extends JFrame implements KeyListener{
    private Entity _array[][] = new Entity[50][50];
    private JPanel jPanel = new JPanel();
    private int height, width;
    private JLabel bomer = new JLabel();
    private Bomer _bomer;
    private List<Balloon> balloonList = new ArrayList<Balloon>();
    private List<JLabel> jballoonList = new ArrayList<JLabel>();
    private JLabel jballoon = new JLabel();
    //private Balloon _balloon;

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
                        _wall.setCanDelete(false);
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
                        _array[i][j] = new Tile(j, i);
                        bomer = creatLabelEntity(_bomer);
                        jPanel.add(creatLabelEntity(_array[i][j]), 0);
                        jPanel.add(bomer, 0);
                    }
                    if (arr[j] == '@') {
                        Entity _brick = new Brick(j, i);
                        _array[i][j] = _brick;
                        jPanel.add(creatLabelEntity(_brick));
                    }
                    if(arr[j] == '!'){
                        Entity _brick = new Brick(j, i);
                        _array[i][j] = _brick;
                        ((Brick) _array[i][j]).setHasItem(true);
                        ((Brick) _array[i][j]).setPathItem("../BTL_OOP_Game/image/item1.png");
                        ((Brick) _array[i][j]).setTypeItem("LIMIT_BOM");
                        jPanel.add(creatLabelEntity(_brick));
                    }
                    if(arr[j] == '1')
                    {
                        JLabel jballoon;
                        Balloon _balloon;

                        _balloon = new Balloon(j, i);
                        _balloon.setCanMove(true);
                        _array[i][j] = _balloon;
                        jballoon = creatLabelEntity(_balloon);
                        balloonList.add(_balloon);
                        jballoonList.add(jballoon);
                        Entity _tile = new Tile(j, i);
                        jPanel.add(creatLabelEntity(_tile), 0);
                        jPanel.add(jballoon, 0);
                    }
                }
            }

            jPanel.setLayout(null);
            this.add(jPanel);
            this.addKeyListener(this);
            int i = 0;
            for(i = 0; i < balloonList.size(); i++)
            {
                AI ai = new AI(jPanel,balloonList.get(i), jballoonList.get(i),_array);
                ai.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnRight(Entity _tmp){
        _tmp.set_x(_tmp.get_x()+1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
    }
    public void turnLeft(Entity _tmp){
        _tmp.set_x(_tmp.get_x()-1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));

        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
    }
    public void turnUp(Entity _tmp){
        _tmp.set_y(_tmp.get_y()-1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
    }
    public void turnDown(Entity _tmp){
        _tmp.set_y(_tmp.get_y()+1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
    }
    public void getBomb(Bomer _tmp)
    {
            BombExplode bombExplode= new BombExplode(jPanel, _tmp, _array);
            bombExplode.start();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
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
                if(_bomer.get_limit_bom()==_bomer.get_bom_number()){
                    break;
                }
                else{
                    _array[_bomer.get_y()][_bomer.get_x()] = new Bomb(_bomer.get_x(), _bomer.get_y());
                    System.out.println(_bomer.get_y() + " " + _bomer.get_x());
                    if(_array[_bomer.get_y()][_bomer.get_x()] instanceof Bomb){
                        System.out.println("ok");
                    }
                    _bomer.set_bom_number(_bomer.get_bom_number()+1);
                    getBomb(_bomer);
                    break;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    private boolean checkMove(char tmp, Entity _tmp){
        if(_array[_tmp.get_y()][_tmp.get_x()-1] instanceof Bomb){
            System.out.println("ok");
        }
        if(tmp == 'a' && !(_array[_tmp.get_y()][_tmp.get_x()-1] instanceof Tile)){
            return false;
        }
        if(tmp == 's' && !(_array[_tmp.get_y()+1][_tmp.get_x()] instanceof Tile)){
            return false;
        }
        if(tmp == 'd' && !(_array[_tmp.get_y()][_tmp.get_x()+1] instanceof Tile)){
            return false;
        }
        if(tmp == 'w' && !(_array[_tmp.get_y()-1][_tmp.get_x()] instanceof Tile)){
            return false;
        }
        return true;
    }

}
