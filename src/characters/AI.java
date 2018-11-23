package characters;

import javax.swing.*;
import java.util.Random;

public class AI extends Thread{
    private JPanel jPanel;
    public Entity _array[][];
    private Entity _balloon;
    private JLabel jballoon;

    public AI(JPanel jPanel, Entity _balloon, JLabel jballoon, Entity _array[][])
    {
        this.jPanel = jPanel;
        this._balloon = new Balloon(_balloon.get_x(), _balloon.get_y());
        this._array = _array;
        this.jballoon = jballoon;
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

    @Override
    public void run() {
        try {
            moveBallom();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void moveBallom()
    {
        int direction = 0;
        Random random = new Random();
        while (true) {

            direction = random.nextInt(4) + 1;
            switch (direction)
            {
                case 1:
                    if(checkMoveBalloon(1, _balloon)) {
                        moveLeft(_balloon);
                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("left");
                    }

                    break;
                case 2:
                    if(checkMoveBalloon(2,_balloon)) {
                        moveRight(_balloon);
                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("right");
                    }

                    break;
                case 3:
                    if(checkMoveBalloon(3, _balloon)) {
                        moveUp(_balloon);
                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("up");
                    }

                    break;
                case 4:
                    if(checkMoveBalloon(4, _balloon)) {
                        moveDown(_balloon);
                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("down");
                    }

                    break;

            }
        }
    }
    public void moveUp(Entity _tmp)
    {
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_y(_tmp.get_y()-1);
        _array[_tmp.get_y()][_tmp.get_x()] = _tmp;
        jballoon.setIcon(new ImageIcon( _tmp.getPath()));
        jballoon.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(jballoon, 0);
    }
    public void moveDown(Entity _tmp)
    {
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_y(_tmp.get_y()+1);
        _array[_tmp.get_y()][_tmp.get_x()] = _tmp;
        jballoon.setIcon(new ImageIcon( _tmp.getPath()));
        jballoon.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(jballoon, 0);
    }
    public void moveLeft(Entity _tmp)
    {
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_x(_tmp.get_x()-1);
        _array[_tmp.get_y()][_tmp.get_x()] = _tmp;
        jballoon.setIcon(new ImageIcon( _tmp.getPath()));
        jballoon.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(jballoon, 0);
    }
    public void moveRight(Entity _tmp)
    {
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_x(_tmp.get_x()+1);
        _array[_tmp.get_y()][_tmp.get_x()] = _tmp;
        jballoon.setIcon(new ImageIcon( _tmp.getPath()));
        jballoon.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(jballoon, 0);
    }
    public boolean checkMoveBalloon(int direction, Entity _tmp){
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
