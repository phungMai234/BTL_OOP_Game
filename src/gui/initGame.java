package gui;

import characters.*;
import sound.GameSound;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class initGame extends JFrame implements KeyListener {
    private Entity _array[][] = new Entity[500][500];
    private Balloon _arrayBallon[] = new Balloon[10];
    private JPanel jPanel;
    private int height, width;
    private JLabel bomer = new JLabel();
    private Bomer _bomer;
    private JLabel jballoon = new JLabel();
    private Balloon _balloon;

    public initGame() {
        GameSound sound = new GameSound("../BTL_OOP_Game/src/sound/nhacnen (online-audio-converter.com).wav", true);
        sound.start();
        jPanel = createGame("../BTL_OOP_Game/level/level1.txt");
        add(jPanel);
        setBounds(0,0,870, 740);
        setTitle("Test JFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JLabel creatLabelEntity(Entity _tmp) {
        JLabel jLabel = new JLabel(new ImageIcon(_tmp.getPath()));
        jLabel.setBounds(_tmp.get_x() * 50, _tmp.get_y() * 50, 50, 50);
        return jLabel;
    }

    public JPanel createGame(String path) {
        JPanel jPanel = new JPanel(null);
        newGame(path, jPanel);
        this.addKeyListener(this);
        return jPanel;
    }

    public void turnRight(Entity _tmp) {
        _tmp.set_x(_tmp.get_x() + 1);
        bomer.setIcon(new ImageIcon(_tmp.getPath()));
        bomer.setBounds(_tmp.get_x() * 50, _tmp.get_y() * 50, 50, 50);
        eatItem(_tmp);
        jPanel.add(bomer, 0);
    }

    public void turnLeft(Entity _tmp) {
        _tmp.set_x(_tmp.get_x() - 1);
        bomer.setIcon(new ImageIcon(_tmp.getPath()));
        bomer.setBounds(_tmp.get_x() * 50, _tmp.get_y() * 50, 50, 50);
        eatItem(_tmp);
        jPanel.add(bomer, 0);
    }

    public void turnUp(Entity _tmp) {
        _tmp.set_y(_tmp.get_y() - 1);
        bomer.setIcon(new ImageIcon(_tmp.getPath()));
        bomer.setBounds(_tmp.get_x() * 50, _tmp.get_y() * 50, 50, 50);
        eatItem(_tmp);
        jPanel.add(bomer, 0);
    }

    public void turnDown(Entity _tmp) {
        _tmp.set_y(_tmp.get_y() + 1);
        bomer.setIcon(new ImageIcon(_tmp.getPath()));
        bomer.setBounds(_tmp.get_x() * 50, _tmp.get_y() * 50, 50, 50);
        eatItem(_tmp);
        jPanel.add(bomer, 0);
    }

    public void getBomb(Bomer _tmp) {
        BombExplode bombExplode = new BombExplode(jPanel, _tmp, _array, _arrayBallon);
        bombExplode.start();

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (!_bomer.isLife() || win(_arrayBallon)) {
            return;
        }
        switch (e.getKeyChar()) {
            case 'a': {
                if (checkMove('a', _bomer)) {
                    turnLeft(_bomer);
                }
                break;
            }
            case 'd': {
                if (checkMove('d', _bomer)) {
                    turnRight(_bomer);
                }
                break;
            }
            case 'w': {
                if (checkMove('w', _bomer)) {
                    turnUp(_bomer);
                }
                break;
            }
            case 's': {
                if (checkMove('s', _bomer)) {
                    turnDown(_bomer);
                }
                break;
            }
            case KeyEvent.VK_SPACE: {
                if (_bomer.get_limit_bom() == _bomer.get_bom_number()) {
                    break;
                } else {
                    GameSound sound = new GameSound("../BTL_OOP_Game/src/sound/newbomb.wav", false);
                    sound.start();
                    _array[_bomer.get_y()][_bomer.get_x()] = new Bomb(_bomer.get_x(), _bomer.get_y());
                    _bomer.set_bom_number(_bomer.get_bom_number() + 1);
                    getBomb(_bomer);

                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    private boolean checkMove(char tmp, Entity _tmp) {
        if (tmp == 'a' && !(_array[_tmp.get_y()][_tmp.get_x() - 1] instanceof Tile)) {
            return false;
        }
        if (tmp == 'd' && !(_array[_tmp.get_y()][_tmp.get_x() + 1] instanceof Tile)) {
            return false;
        }
        if (tmp == 's' && !(_array[_tmp.get_y() + 1][_tmp.get_x()] instanceof Tile)) {
            return false;
        }
        if (tmp == 'w' && !(_array[_tmp.get_y() - 1][_tmp.get_x()] instanceof Tile)) {
            return false;
        }
        return true;
    }

    private boolean win(Balloon _arr[]) {
        for (int i = 0; i < _arr.length; i++) {
            if (_arr[i] != null) {
                if (_arr[i].isLife()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void eatItem(Entity _tmp) {
        if (((Tile) _array[_tmp.get_y()][_tmp.get_x()]).isHasItem()) {
            if (((Tile) _array[_tmp.get_y()][_tmp.get_x()]).getTypeItem().equals("LIMIT_BOM")) {
                ((Bomer) _tmp).set_limit_bom(((Bomer) _tmp).get_limit_bom() + 1);
            }
            if (((Tile) _array[_tmp.get_y()][_tmp.get_x()]).getTypeItem().equals("TIME_POWER")) {
                ((Bomer) _tmp).set_bom_time(((Bomer) _tmp).get_bom_time() / 2);
            }
            ((Tile) _array[_tmp.get_y()][_tmp.get_x()]).setHasItem(false);
            jPanel.add(creatLabelEntity(new Tile(_tmp.get_x(), _tmp.get_y())), 0);
            GameSound sound = new GameSound("../BTL_OOP_Game/src/sound/item.wav", false);
            sound.start();
        }
    }

    private void newGame(String path, JPanel jPanel) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String infor = br.readLine();
            height = Integer.parseInt(infor.split(" ")[0]);
            width = Integer.parseInt(infor.split(" ")[1]);
            for (int i = 0; i < height; i++) {
                String oneLine = br.readLine();
                char[] arr = oneLine.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j] == '#') {
                        Entity _wall = new Wall(j, i);
                        _wall.setCanDelete(false);
                        _array[i][j] = _wall;
                        jPanel.add(creatLabelEntity(_wall));
                    }
                    if (arr[j] == ' ') {
                        Entity _tile = new Tile(j, i);
                        _array[i][j] = _tile;
                        jPanel.add(creatLabelEntity(_tile), 0);
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
                        jPanel.add(creatLabelEntity(_brick), 0);
                    }
                    if (arr[j] == '!' || arr[j] == 't') {
                        Entity _brick = new Brick(j, i);
                        _array[i][j] = _brick;
                        ((Brick) _array[i][j]).setHasItem(true);
                        if (arr[j] == '!') {
                            ((Brick) _array[i][j]).setPathItem("../BTL_OOP_Game/image/powerup_bombs.png");
                            ((Brick) _array[i][j]).setTypeItem("LIMIT_BOM");
                        } else {
                            ((Brick) _array[i][j]).setPathItem("../BTL_OOP_Game/image/time.png");
                            ((Brick) _array[i][j]).setTypeItem("TIME_POWER");
                        }
                        jPanel.add(creatLabelEntity(_brick));
                    }
                    if (arr[j] == '+') {
                        System.out.println(i + ":" + j);
                        _balloon = new Balloon(j, i);
                        _balloon.setCanMove(true);
                        _array[i][j] = _balloon;
                        Entity _tile = new Tile(j, i);
                        jPanel.add(creatLabelEntity(_tile), 0);
                    }
                }
            }
            int _index = 0;
            for (int i = 0; i < _array.length; i++) {
                for (int j = 0; j < _array[i].length; j++) {
                    if (_array[i][j] instanceof Balloon) {
                        _arrayBallon[_index] = (Balloon) _array[i][j];
                        jballoon = creatLabelEntity(_array[i][j]);
                        AutoMove autoMove = new AutoMove(jPanel, _arrayBallon, jballoon, _array, _index, _bomer);
                        autoMove.start();
                        _index++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}