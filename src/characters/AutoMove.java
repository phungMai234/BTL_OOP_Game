package characters;

import sound.GameSound;

import javax.swing.*;
import java.util.Random;

public class AutoMove extends Thread {
    private JPanel jPanel;
    public Entity _array[][];
    private Balloon _balloon;
    private JLabel jballoon;
    private Balloon _arrayBalloon[];
    private Bomer _bomer;

    public AutoMove(JPanel jPanel, Balloon _balloon[], JLabel jballoon, Entity _array[][], int index, Bomer _bomer) {
        this.jPanel = jPanel;
        this._balloon = _balloon[index];
        this._array = _array;
        this.jballoon = jballoon;
        this._arrayBalloon = _balloon;
        this._bomer = _bomer;
    }

    @Override
    public void run() {
        moveBallom();
    }

    public void moveBallom() {
        int direction = 0;
        Random random = new Random();
        while (_balloon.isLife() && _bomer.isLife()) {
            System.out.println(_balloon.isLife());
            direction = random.nextInt(4);
            String[] check = {"left", "right", "up", "down"};
            switch (check[direction]) {
                case "left":
                    if (checkMoveBalloon("left", _balloon)) {
                        moveLeft(_balloon);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }

                    break;
                case "right":
                    if (checkMoveBalloon("right", _balloon)) {
                        moveRight(_balloon);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }

                    break;
                case "up":
                    if (checkMoveBalloon("up", _balloon)) {
                        moveUp(_balloon);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }

                    break;
                case "down":
                    if (checkMoveBalloon("down", _balloon)) {

                        moveDown(_balloon);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }

                    break;
            }
        }

    }

    public void moveUp(Entity _tmp) {
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_y(_tmp.get_y() - 1);
        _array[_tmp.get_y()][_tmp.get_x()] = _tmp;
        jballoon.setIcon(new ImageIcon(_tmp.getPath()));
        jballoon.setBounds(_tmp.get_x() * 50, _tmp.get_y() * 50, 50, 50);
        jPanel.add(jballoon, 0);
        checkBomerDie(_tmp, _bomer);
    }

    public void moveDown(Entity _tmp) {
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_y(_tmp.get_y() + 1);
        _array[_tmp.get_y()][_tmp.get_x()] = _tmp;
        jballoon.setIcon(new ImageIcon(_tmp.getPath()));
        jballoon.setBounds(_tmp.get_x() * 50, _tmp.get_y() * 50, 50, 50);
        jPanel.add(jballoon, 0);
        checkBomerDie(_tmp, _bomer);
    }

    public void moveLeft(Entity _tmp) {
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_x(_tmp.get_x() - 1);
        _array[_tmp.get_y()][_tmp.get_x()] = _tmp;
        jballoon.setIcon(new ImageIcon(_tmp.getPath()));
        jballoon.setBounds(_tmp.get_x() * 50, _tmp.get_y() * 50, 50, 50);
        jPanel.add(jballoon, 0);
        checkBomerDie(_tmp, _bomer);
    }

    public void moveRight(Entity _tmp) {
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_x(_tmp.get_x() + 1);
        _array[_tmp.get_y()][_tmp.get_x()] = _tmp;
        jballoon.setIcon(new ImageIcon(_tmp.getPath()));
        jballoon.setBounds(_tmp.get_x() * 50, _tmp.get_y() * 50, 50, 50);
        jPanel.add(jballoon, 0);
        checkBomerDie(_tmp, _bomer);
    }

    public boolean checkMoveBalloon(String moveTo, Entity _tmp) {
        if (moveTo.equals("left") && !(_array[_tmp.get_y()][_tmp.get_x() - 1] instanceof Tile)) {
            return false;
        }
        if (moveTo.equals("right") && !(_array[_tmp.get_y()][_tmp.get_x() + 1] instanceof Tile)) {
            return false;
        }
        if (moveTo.equals("down") && !(_array[_tmp.get_y() + 1][_tmp.get_x()] instanceof Tile)) {
            return false;
        }
        if (moveTo.equals("up") && !(_array[_tmp.get_y() - 1][_tmp.get_x()] instanceof Tile)) {
            return false;
        }
        return true;
    }
    public void checkBomerDie(Entity _tmp, Bomer _bomer) {
        if (_tmp.get_y() == _bomer.get_y() && _tmp.get_x() == _bomer.get_x()) {
            GameSound sound = new GameSound("../BTL_OOP_Game/src/sound/bomber_die.wav", false);
            sound.start();
            _bomer.setLife(false);
            jPanel.setVisible(false);
            JLabel jLabel = new JLabel();
            jLabel.setIcon(new ImageIcon("../BTL_OOP_Game/image/lose.png"));
            jLabel.setBounds(0,0,17*50, 14*50);
            jPanel.add(jLabel, 0);
            jPanel.setVisible(true);
        }
    }
}
