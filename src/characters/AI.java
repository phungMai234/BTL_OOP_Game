package characters;

import javax.swing.*;
import java.util.Random;

public class AI {
    private JPanel jPanel;
    public Entity _array[][];
    private JLabel jballoon;
    private Balloon _balloon;
    //private Balloon realBalloon;

    public AI(JPanel jPanel, Balloon _balloon, JLabel jballoon, Entity _array[][])
    {
        this.jPanel = jPanel;
        this._balloon = _balloon;
        this.jballoon = jballoon;
        this._array = _array;
    }


    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public void set_array(Entity[][] _array) {
        this._array = _array;
    }


    public JPanel getjPanel() {
        return jPanel;
    }


    public Entity[][] get_array() {
        return _array;
    }

    public void moveBallom()
    {
        int direction = 0;
        Random random = new Random();
        int i=10;
        while (i>0) {
            i--;
            //for (Balloon b : balloonList) {
            direction = random.nextInt(4) + 1;
            System.out.println(direction);
            switch (direction)
            {
                case 1:
                    if(checkMoveBalloon(1, _balloon))
                        moveLeft(_balloon);
                    System.out.println("left");
                    break;
                case 2:
                    if(checkMoveBalloon(2,_balloon))
                        moveRight(_balloon);
                    System.out.println("right");
                    break;
                case 3:
                    if(checkMoveBalloon(3, _balloon))
                        moveUp(_balloon);
                    System.out.println("up");
                    break;
                case 4:
                    if(checkMoveBalloon(4, _balloon))
                        moveDown(_balloon);
                    System.out.println("down");
                    break;

            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void moveUp(Balloon _tmp)
    {
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_y(_tmp.get_y()-1);
        //JLabel jballoon = new JLabel();
        jballoon.setIcon(new ImageIcon( _tmp.getPath()));
        jballoon.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(jballoon, 0);
    }
    public void moveDown(Balloon _tmp)
    {
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_y(_tmp.get_y()+1);
        //JLabel jballoon = new JLabel();
        jballoon.setIcon(new ImageIcon( _tmp.getPath()));
        jballoon.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(jballoon, 0);
    }
    public void moveLeft(Balloon _tmp)
    {
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_y(_tmp.get_x()-1);
        //JLabel jballoon = new JLabel();
        jballoon.setIcon(new ImageIcon( _tmp.getPath()));
        jballoon.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(jballoon, 0);
    }
    public void moveRight(Balloon _tmp)
    {
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_y(_tmp.get_x()+1);
        //JLabel jballoon = new JLabel();
        jballoon.setIcon(new ImageIcon( _tmp.getPath()));
        jballoon.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(jballoon, 0);
    }
    public boolean checkMoveBalloon(int direction, Balloon _tmp){
        if(direction == 1 && !(_array[_tmp.get_y()][_tmp.get_x()-1] instanceof Tile)){
            return false;
        }
        if(direction == 4 && !(_array[_tmp.get_y()+1][_tmp.get_x()] instanceof Tile)){
            return false;
        }
        if(direction == 2 && !(_array[_tmp.get_y()][_tmp.get_x()+1] instanceof Tile)){
            return false;
        }
        if(direction == 3 && !(_array[_tmp.get_y()-1][_tmp.get_x()] instanceof Tile)){
            return false;
        }
        return true;
    }
}
