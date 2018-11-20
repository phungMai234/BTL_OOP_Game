package characters;

import javax.swing.*;

public class BombExplode extends Thread {
    private JPanel jPanel;
    public Entity _array[][];
    private Bomer _tmp;
    private Bomer _realty_bomer;

    public Entity get_tmp() {
        return _tmp;
    }

    public void set_tmp(Bomer _tmp) {
        this._tmp = _tmp;
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public BombExplode() {

    }
    public BombExplode(JPanel jPanel, Bomer _tmp, Entity _array[][]) {
        this._realty_bomer = _tmp;
        this.jPanel = jPanel;
        this._tmp = new Bomer(_tmp.get_x(), _tmp.get_y());
        this._array = _array;
    }

    @Override
    public void run() {
        try {
            _array[_tmp.get_y()][_tmp.get_x()] = new Bomb(_tmp.get_y(), _tmp.get_x());
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

            jLabel_center.setIcon(new ImageIcon("../BTL_OOP_Game/image/bomb_exploded2.png"));


/* lỗi*/
            System.out.println(_tmp.get_y() + " " + _tmp.get_x());
            if (_array[_tmp.get_y() - 1][_tmp.get_x()] instanceof Tile) {
                System.out.println("top");
                jLabel_top.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_vertical_top_last2.png"));

            }
            else if (_array[_tmp.get_y() - 1][_tmp.get_x()] instanceof Bomer)
            {
                System.out.println("bomber top");
            }
            else if (_array[_tmp.get_y() - 1][_tmp.get_x()] instanceof Brick)
            {
                System.out.println("brick top");
            }
            else if (_array[_tmp.get_y() - 1][_tmp.get_x()] instanceof Wall)
            {
                System.out.println("wall top");
            }
            else if (_array[_tmp.get_y() - 1][_tmp.get_x()] instanceof Bomb)
            {
                System.out.println("bomb top");
            }



            if (_array[_tmp.get_y() + 1][_tmp.get_x()] instanceof Tile) {
                System.out.println("down");
                jLabel_down.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_vertical_down_last2.png"));

            }
            else if (_array[_tmp.get_y() + 1][_tmp.get_x()] instanceof Bomer)
            {
                System.out.println("bomber down");
            }
            else if (_array[_tmp.get_y() + 1][_tmp.get_x()] instanceof Brick)
            {
                System.out.println("brick down");
            }
            else if (_array[_tmp.get_y() + 1][_tmp.get_x()] instanceof Wall)
            {
                System.out.println("wall down");
            }
            else if (_array[_tmp.get_y() + 1][_tmp.get_x()] instanceof Bomb)
            {
                System.out.println("bomb down");
            }



            if (_array[_tmp.get_y()][_tmp.get_x() - 1] instanceof Tile) {

                System.out.println("left");
                jLabel_left.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_horizontal_left_last2.png"));

            }
            else if (_array[_tmp.get_y() ][_tmp.get_x() -1 ] instanceof Bomer)
            {
                System.out.println("bomber left");
            }
            else if (_array[_tmp.get_y()][_tmp.get_x() - 1] instanceof Brick)
            {
                System.out.println("brick left");
            }
            else if (_array[_tmp.get_y()][_tmp.get_x() - 1] instanceof Wall)
            {
                System.out.println("wall left");
            }
            else if (_array[_tmp.get_y()][_tmp.get_x() - 1] instanceof Bomb)
            {
                System.out.println("bomb left");
            }




            if (_array[_tmp.get_y()][_tmp.get_x() + 1] instanceof Tile) {
                System.out.println("right");
                jLabel_right.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_horizontal_right_last2.png"));

            }
            else if (_array[_tmp.get_y() ][_tmp.get_x() +1 ] instanceof Bomer)
            {
                System.out.println("bomber right");
            }
            else if (_array[_tmp.get_y()][_tmp.get_x() + 1] instanceof Brick)
            {
                System.out.println("brick right");
            }
            else if (_array[_tmp.get_y()][_tmp.get_x() +1] instanceof Wall)
            {
                System.out.println("wall right");
            }
            else if (_array[_tmp.get_y()][_tmp.get_x() + 1] instanceof Bomb)
            {
                System.out.println("bomb right");
            }
            Thread.sleep(1000);

            jLabel_center.setIcon(null);
            jLabel_down.setIcon(null);
            jLabel_left.setIcon(null);
            jLabel_right.setIcon(null);
            jLabel_top.setIcon(null);
            _realty_bomer.set_bom_number(_realty_bomer.get_bom_number()-1);
            System.out.println("done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   /* public void checkDirectionBombExplode(Entity _tmp) {

        if (this._array[_tmp.get_y()][_tmp.get_x() - 1] instanceof Tile) {
            JLabel jLabel_center = new JLabel();
            jLabel_center.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_vertical_top_last2.png"));
            jLabel_center.setBounds(_tmp.get_x() * 50, (_tmp.get_y() - 1) * 50, 50, 50);
            jPanel.add(jLabel_center);
        }
        if (this._array[_tmp.get_y()][_tmp.get_x() + 1] instanceof Tile) {
            JLabel jLabel_down = new JLabel();
            jLabel_down.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_vertical_down_last2.png"));
            jLabel_down.setBounds(_tmp.get_x() * 50, (_tmp.get_y() + 1) * 50, 50, 50);
            jPanel.add(jLabel_down);
        }
        if (this._array[_tmp.get_y() - 1][_tmp.get_x()] instanceof Tile) {
            JLabel jLabel_left = new JLabel();
            jLabel_left.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_horizontal_left_last2.png.png"));
            jLabel_left.setBounds((_tmp.get_x() - 1) * 50, _tmp.get_y() * 50, 50, 50);
            jPanel.add(jLabel_left);
        }
        if (this._array[_tmp.get_y() + 1][_tmp.get_x()] instanceof Tile) {
            JLabel jLabel_right = new JLabel();
            jLabel_right.setIcon(new ImageIcon("../BTL_OOP_Game/image/explosion_horizontal_right_last2.png.png"));
            jLabel_right.setBounds((_tmp.get_x() + 1) * 50, _tmp.get_y() * 50, 50, 50);
            jPanel.add(jLabel_right);
        }

    }*/
}
