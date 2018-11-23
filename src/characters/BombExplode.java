package characters;

import sound.GameSound;

import javax.swing.*;

public class BombExplode extends Thread {
    private JPanel jPanel;
    public Entity _array[][];
    private Bomer _tmp;
    private Bomer _realty_bomer;

    public BombExplode(JPanel jPanel, Bomer _tmp, Entity _array[][]) {
        this._realty_bomer = _tmp;
        this.jPanel = jPanel;
        this._tmp = new Bomer(_tmp.get_x(), _tmp.get_y());
        this._array = _array;
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

            Thread.sleep(2000);
            GameSound.getIstance().getAudio(GameSound.BOMB_BANG).play();
            if (_array[_tmp.get_y() - 1][_tmp.get_x()].isCanDelete()) {
                Tile tile = new Tile(_tmp.get_y() - 1, _tmp.get_x() );
                if(_array[_tmp.get_y() - 1][_tmp.get_x()] instanceof Brick && ((Brick) _array[_tmp.get_y() - 1][_tmp.get_x()]).isHasItem()){
                    tile.setHasItem(true);
                    tile.setPath(((Brick) _array[_tmp.get_y() - 1][_tmp.get_x()]).getPathItem());
                    tile.setTypeItem(((Brick) _array[_tmp.get_y() - 1][_tmp.get_x()]).getTypeItem());
                }
                _array[_tmp.get_y() - 1][_tmp.get_x()] = tile;
                jLabel_top.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_vertical_top_last2.png"));
            }
            if (_array[_tmp.get_y() + 1][_tmp.get_x()].isCanDelete()) {
                Tile tile = new Tile(_tmp.get_y() + 1, _tmp.get_x() );
                if(_array[_tmp.get_y() + 1][_tmp.get_x()] instanceof Brick && ((Brick) _array[_tmp.get_y() + 1][_tmp.get_x()]).isHasItem()){
                    tile.setHasItem(true);
                    tile.setPath(((Brick) _array[_tmp.get_y() + 1][_tmp.get_x()]).getPathItem());
                    tile.setTypeItem(((Brick) _array[_tmp.get_y() + 1][_tmp.get_x()]).getTypeItem());
                }
                _array[_tmp.get_y() + 1][_tmp.get_x()] = tile;
                jLabel_down.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_vertical_down_last2.png"));
            }

            if (_array[_tmp.get_y()][_tmp.get_x() - 1].isCanDelete()) {
                Tile tile = new Tile(_tmp.get_y(), _tmp.get_x() - 1);
                if(_array[_tmp.get_y()][_tmp.get_x() - 1] instanceof Brick && ((Brick) _array[_tmp.get_y() + 1][_tmp.get_x()]).isHasItem()){
                    tile.setHasItem(true);
                    tile.setPath(((Brick) _array[_tmp.get_y() + 1][_tmp.get_x()]).getPathItem());
                    tile.setTypeItem(((Brick) _array[_tmp.get_y() + 1][_tmp.get_x()]).getTypeItem());
                }
                _array[_tmp.get_y() + 1][_tmp.get_x()] = tile;
                jLabel_left.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_horizontal_left_last2.png"));

            }
            if (_array[_tmp.get_y()][_tmp.get_x() + 1].isCanDelete()) {
                _array[_tmp.get_y()][_tmp.get_x() + 1] = new Tile(_tmp.get_y(), _tmp.get_x() + 1);
                jLabel_right.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_horizontal_right_last2.png"));
            }
            jLabel_center.setIcon(new ImageIcon("../BTL_OOP_Game/image/bomb_exploded2.png"));

            Thread.sleep(400);
            _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
            jLabel_center.setIcon(new ImageIcon(_array[_tmp.get_y()][_tmp.get_x()].getPath()));

            if(_array[_tmp.get_y() + 1][_tmp.get_x()] instanceof Tile){
                if(((Tile) _array[_tmp.get_y() + 1][_tmp.get_x()]).isHasItem()){
                    jLabel_down.setIcon(new ImageIcon(((Tile)_array[_tmp.get_y() + 1][_tmp.get_x()]).getPathItem()));
                }
                else{
                    jLabel_down.setIcon(new ImageIcon(_array[_tmp.get_y() + 1][_tmp.get_x()].getPath()));
                }
            }
            jLabel_left.setIcon(new ImageIcon(_array[_tmp.get_y()][_tmp.get_x() - 1].getPath()));
            jLabel_right.setIcon(new ImageIcon(_array[_tmp.get_y()][_tmp.get_x() + 1].getPath()));
            jLabel_top.setIcon(new ImageIcon(_array[_tmp.get_y() - 1][_tmp.get_x()].getPath()));
            _realty_bomer.set_bom_number(_realty_bomer.get_bom_number() - 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
