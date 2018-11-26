package characters;

import sound.GameSound;

import javax.swing.*;

public class BombExplode extends Thread {
    private JPanel jPanel;
    private Entity _array[][];
    private Bomer _tmp;
    private Bomer _realty_bomer;
    private Balloon _arrayBallon[];

    public BombExplode(JPanel jPanel, Bomer _tmp, Entity _array[][], Balloon _arrayBallon[]) {
        this._realty_bomer = _tmp;
        this.jPanel = jPanel;
        this._tmp = new Bomer(_tmp.get_x(), _tmp.get_y());
        this._array = _array;
        this._arrayBallon = _arrayBallon;
    }

    @Override
    public void run() {
        try {
            JLabel jLabel_center = new JLabel();
            JLabel jLabel_top = new JLabel();
            JLabel jLabel_down = new JLabel();
            JLabel jLabel_left = new JLabel();
            JLabel jLabel_right = new JLabel();

            jLabel_center.setIcon(new ImageIcon(_array[_tmp.get_y()][_tmp.get_x()].getPath()));
            jLabel_center.setBounds(_tmp.get_x() * 50, _tmp.get_y() * 50, 50, 50);
            jPanel.add(jLabel_center, 0);

            jLabel_top.setBounds(_tmp.get_x() * 50, (_tmp.get_y() - 1) * 50, 50, 50);
            jPanel.add(jLabel_top, 0);

            jLabel_down.setBounds(_tmp.get_x() * 50, (_tmp.get_y() + 1) * 50, 50, 50);
            jPanel.add(jLabel_down, 0);

            jLabel_left.setBounds((_tmp.get_x() - 1) * 50, _tmp.get_y() * 50, 50, 50);
            jPanel.add(jLabel_left, 0);

            jLabel_right.setBounds((_tmp.get_x() + 1) * 50, _tmp.get_y() * 50, 50, 50);
            jPanel.add(jLabel_right, 0);

            Thread.sleep(_realty_bomer.get_bom_time());
            checkBomerDie(_tmp, _realty_bomer);
            if (_array[_tmp.get_y() - 1][_tmp.get_x()].isCanDelete()) {
                checkBomerDie(_array[_tmp.get_y() - 1][_tmp.get_x()], _realty_bomer);
                deleteBallon(_tmp, "top");
                Tile tile = new Tile(_tmp.get_y() - 1, _tmp.get_x());
                if (_array[_tmp.get_y() - 1][_tmp.get_x()] instanceof Brick && ((Brick) _array[_tmp.get_y() - 1][_tmp.get_x()]).isHasItem()) {
                    tile.setHasItem(true);
                    tile.setPathItem(((Brick) _array[_tmp.get_y() - 1][_tmp.get_x()]).getPathItem());
                    tile.setTypeItem(((Brick) _array[_tmp.get_y() - 1][_tmp.get_x()]).getTypeItem());
                }
                _array[_tmp.get_y() - 1][_tmp.get_x()] = tile;
                jLabel_top.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_vertical_top_last2.png"));
            }
            if (_array[_tmp.get_y() + 1][_tmp.get_x()].isCanDelete()) {
                deleteBallon(_tmp, "down");
                checkBomerDie(_array[_tmp.get_y() + 1][_tmp.get_x()], _realty_bomer);
                Tile tile = new Tile(_tmp.get_y() + 1, _tmp.get_x());
                if (_array[_tmp.get_y() + 1][_tmp.get_x()] instanceof Brick && ((Brick) _array[_tmp.get_y() + 1][_tmp.get_x()]).isHasItem()) {
                    tile.setHasItem(true);
                    tile.setPathItem(((Brick) _array[_tmp.get_y() + 1][_tmp.get_x()]).getPathItem());
                    tile.setTypeItem(((Brick) _array[_tmp.get_y() + 1][_tmp.get_x()]).getTypeItem());
                }
                _array[_tmp.get_y() + 1][_tmp.get_x()] = tile;
                jLabel_down.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_vertical_down_last2.png"));
            }

            if (_array[_tmp.get_y()][_tmp.get_x() - 1].isCanDelete()) {
                deleteBallon(_tmp, "left");
                checkBomerDie(_array[_tmp.get_y()][_tmp.get_x() - 1], _realty_bomer);
                Tile tile = new Tile(_tmp.get_y(), _tmp.get_x() - 1);
                if (_array[_tmp.get_y()][_tmp.get_x() - 1] instanceof Brick && ((Brick) _array[_tmp.get_y()][_tmp.get_x() - 1]).isHasItem()) {
                    tile.setHasItem(true);
                    tile.setPathItem(((Brick) _array[_tmp.get_y()][_tmp.get_x() - 1]).getPathItem());
                    tile.setTypeItem(((Brick) _array[_tmp.get_y()][_tmp.get_x() - 1]).getTypeItem());
                }
                _array[_tmp.get_y()][_tmp.get_x() - 1] = tile;
                jLabel_left.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_horizontal_left_last2.png"));

            }
            if (_array[_tmp.get_y()][_tmp.get_x() + 1].isCanDelete()) {
                deleteBallon(_tmp, "right");
                checkBomerDie(_array[_tmp.get_y()][_tmp.get_x() + 1], _realty_bomer);
                Tile tile = new Tile(_tmp.get_y(), _tmp.get_x() + 1);
                if (_array[_tmp.get_y()][_tmp.get_x() + 1] instanceof Brick && ((Brick) _array[_tmp.get_y()][_tmp.get_x() + 1]).isHasItem()) {
                    tile.setHasItem(true);
                    tile.setPathItem(((Brick) _array[_tmp.get_y()][_tmp.get_x() + 1]).getPathItem());
                    tile.setTypeItem(((Brick) _array[_tmp.get_y()][_tmp.get_x() + 1]).getTypeItem());
                }
                _array[_tmp.get_y()][_tmp.get_x() + 1] = tile;
                jLabel_right.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_horizontal_right_last2.png"));

            }
            jLabel_center.setIcon(new ImageIcon("../BTL_OOP_Game/image/bomb_exploded2.png"));
            GameSound sound = new GameSound("../BTL_OOP_Game/src/sound/bomb_bang.wav", false);
            sound.start();
            Thread.sleep(200);
            checkBomerDie(_tmp, _realty_bomer);
            // check xem có item k
            //down
            if (_array[_tmp.get_y() + 1][_tmp.get_x()] instanceof Tile) {
                if (((Tile) _array[_tmp.get_y() + 1][_tmp.get_x()]).isHasItem()) {
                    System.out.println(((Tile) _array[_tmp.get_y() + 1][_tmp.get_x()]).getPathItem());
                    jLabel_down.setIcon(new ImageIcon(((Tile) _array[_tmp.get_y() + 1][_tmp.get_x()]).getPathItem()));
                } else {
                    jLabel_down.setIcon(new ImageIcon(_array[_tmp.get_y() + 1][_tmp.get_x()].getPath()));
                }
            }
            //left
            if (_array[_tmp.get_y()][_tmp.get_x() - 1] instanceof Tile) {
                if (((Tile) _array[_tmp.get_y()][_tmp.get_x() - 1]).isHasItem()) {
                    jLabel_left.setIcon(new ImageIcon(((Tile) _array[_tmp.get_y()][_tmp.get_x() - 1]).getPathItem()));
                } else {
                    jLabel_left.setIcon(new ImageIcon(_array[_tmp.get_y()][_tmp.get_x() - 1].getPath()));
                }
            }
            //right
            if (_array[_tmp.get_y()][_tmp.get_x() + 1] instanceof Tile) {
                if (((Tile) _array[_tmp.get_y()][_tmp.get_x() + 1]).isHasItem()) {
                    jLabel_right.setIcon(new ImageIcon(((Tile) _array[_tmp.get_y()][_tmp.get_x() + 1]).getPathItem()));
                } else {
                    jLabel_right.setIcon(new ImageIcon(_array[_tmp.get_y()][_tmp.get_x() + 1].getPath()));
                }
            }
            //top
            if (_array[_tmp.get_y() - 1][_tmp.get_x()] instanceof Tile) {
                if (((Tile) _array[_tmp.get_y() - 1][_tmp.get_x()]).isHasItem()) {
                    jLabel_top.setIcon(new ImageIcon(((Tile) _array[_tmp.get_y() - 1][_tmp.get_x()]).getPathItem()));
                } else {
                    jLabel_top.setIcon(new ImageIcon(_array[_tmp.get_y() - 1][_tmp.get_x()].getPath()));
                }
            }
            _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_x(), _tmp.get_y());
            jLabel_center.setIcon(new ImageIcon(_array[_tmp.get_y()][_tmp.get_x()].getPath()));
            _realty_bomer.set_bom_number(_realty_bomer.get_bom_number() - 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteBallon(Entity _tmp, String moveTo) {
        if (moveTo.equals("left")) {
            if (_array[_tmp.get_y()][_tmp.get_x() - 1] instanceof Balloon) {
                for (int i = 0; i < _arrayBallon.length; i++) {
                    if (_arrayBallon[i] != null) {
                        if (_arrayBallon[i].get_x() == _array[_tmp.get_y()][_tmp.get_x() - 1].get_x() && _arrayBallon[i].get_y() == _array[_tmp.get_y()][_tmp.get_x() - 1].get_y()) {
                            _arrayBallon[i].setLife(false);
                        }
                    }
                }
            }
        }
        if (moveTo.equals("right")) {
            if (_array[_tmp.get_y()][_tmp.get_x() + 1] instanceof Balloon) {
                for (int i = 0; i < _arrayBallon.length; i++) {
                    if (_arrayBallon[i] != null) {
                        if (_arrayBallon[i].get_x() == _array[_tmp.get_y()][_tmp.get_x() + 1].get_x() && _arrayBallon[i].get_y() == _array[_tmp.get_y()][_tmp.get_x() + 1].get_y()) {
                            _arrayBallon[i].setLife(false);
                        }
                    }
                }
            }
        }
        if (moveTo.equals("top")) {
            if (_array[_tmp.get_y() - 1][_tmp.get_x()] instanceof Balloon) {
                for (int i = 0; i < _arrayBallon.length; i++) {
                    if (_arrayBallon[i] != null) {
                        if (_arrayBallon[i].get_x() == _array[_tmp.get_y() - 1][_tmp.get_x()].get_x() && _arrayBallon[i].get_y() == _array[_tmp.get_y() - 1][_tmp.get_x()].get_y()) {
                            _arrayBallon[i].setLife(false);
                        }
                    }
                }
            }
        }
        if (moveTo.equals("down")) {
            if (_array[_tmp.get_y() + 1][_tmp.get_x()] instanceof Balloon) {
                for (int i = 0; i < _arrayBallon.length; i++) {
                    if (_arrayBallon[i] != null) {
                        if (_arrayBallon[i].get_x() == _array[_tmp.get_y() + 1][_tmp.get_x()].get_x() && _arrayBallon[i].get_y() == _array[_tmp.get_y() + 1][_tmp.get_x()].get_y()) {
                            _arrayBallon[i].setLife(false);
                        }
                    }
                }
            }
        }
        checkGame check = new checkGame(_arrayBallon, jPanel);
        check.start();
    }

    public void checkBomerDie(Entity _tmp, Bomer _bomer) {
        if (_tmp.get_x() == _bomer.get_x() && _tmp.get_y() == _bomer.get_y()) {
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
