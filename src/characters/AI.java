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
        System.out.println(_balloon.get_x() + " " + _balloon.get_y());
        this.jPanel = jPanel;
        this._balloon = _balloon;
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
            moveBallom();
    }

    public void moveBallom()
    {
        int direction = 0;
        Random random = new Random();
        while (true) {
            System.out.print(_balloon.get_x() + ":" + _balloon.get_y()+ "           ");
            direction = random.nextInt(4) + 1;
            String check = "";
            switch (direction){
                case 1:
                    check = "left";
                    break;
                case 2:
                    check = "right";
                    break;
                case 3:
                    check = "up";
                    break;
                case 4:
                    check = "down";
                    break;
            }
            switch (check)
            {
                case "left":
                    if(checkMoveBalloon("left", _balloon)) {
                        moveLeft(_balloon);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "right":
                    if(checkMoveBalloon("right",_balloon)) {
                        moveRight(_balloon);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;

                case "up":
                    if(checkMoveBalloon("up", _balloon)) {
                        moveUp(_balloon);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "down":
                    if(checkMoveBalloon("down", _balloon)) {
                        moveDown(_balloon);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            System.out.println(_balloon.get_x() + ":" + _balloon.get_y());
        }

    }
    public void moveUp(Entity _tmp)
    {
        //System.out.println("up------");
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_y(_tmp.get_y()-1);
        _array[_tmp.get_y()][_tmp.get_x()] = _tmp;
        jballoon.setIcon(new ImageIcon( _tmp.getPath()));
        jballoon.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(jballoon, 0);
    }
    public void moveDown(Entity _tmp)
    {
        //System.out.println("down------");
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_y(_tmp.get_y()+1);
        _array[_tmp.get_y()][_tmp.get_x()] = _tmp;
        jballoon.setIcon(new ImageIcon( _tmp.getPath()));
        jballoon.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(jballoon, 0);
    }
    public void moveLeft(Entity _tmp)
    {
        //System.out.println("left------");
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_x(_tmp.get_x()-1);
        _array[_tmp.get_y()][_tmp.get_x()] = _tmp;
        jballoon.setIcon(new ImageIcon( _tmp.getPath()));
        jballoon.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(jballoon, 0);
    }
    public void moveRight(Entity _tmp)
    {
        //System.out.println("right------");
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_x(_tmp.get_x()+1);
        _array[_tmp.get_y()][_tmp.get_x()] = _tmp;
        jballoon.setIcon(new ImageIcon( _tmp.getPath()));
        jballoon.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(jballoon, 0);
    }
    public boolean checkMoveBalloon(String moveTo, Entity _tmp){
        //System.out.println(_tmp.get_y() + ":" + _tmp.get_y() + "       " + moveTo);
        if(moveTo.equals("left") && !(_array[_tmp.get_y()][_tmp.get_x()-1] instanceof Tile)){
            return false;
        }
        if(moveTo.equals("right") && !(_array[_tmp.get_y()][_tmp.get_x()+1] instanceof Tile)){
            return false;
        }
        if(moveTo.equals("down") && !(_array[_tmp.get_y()+1][_tmp.get_x()] instanceof Tile)){
            return false;
        }
        if(moveTo.equals("up") && !(_array[_tmp.get_y()-1][_tmp.get_x()] instanceof Tile)){
            return false;
        }
        return true;
    }
}
