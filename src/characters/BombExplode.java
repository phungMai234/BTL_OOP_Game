package characters;

import javax.swing.*;

public class BombExplode extends Thread{
    private JPanel jPanel;
    public Entity _array[][];
    private Entity _tmp ;

    public Entity get_tmp() {
        return _tmp;
    }

    public void set_tmp(Entity _tmp) {
        this._tmp = _tmp;
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }
    public BombExplode()
    {

    }
    public BombExplode(JPanel jPanel, Entity _tmp, Entity _array[][])
    {
        this.jPanel = jPanel;
        this._tmp = _tmp;
        this._array = _array;
    }
    @Override
    public void run()
    {
        try
        {
            _array[_tmp.get_y()][_tmp.get_x()] = new Bomb(_tmp.get_y(), _tmp.get_x());

            JLabel jLabel1 = new JLabel();
            jLabel1.setIcon(new ImageIcon(_array[_tmp.get_y()][_tmp.get_x()].getPath()));
            jLabel1.setBounds(_tmp.get_x() * 50, _tmp.get_y() * 50, 50, 50);

            jPanel.add(jLabel1, 0);

            Thread.sleep(2000);
            jLabel1.setIcon(new ImageIcon("C:\\Users\\Black\\Desktop\\BTL_OOP_Game\\image\\bomb_exploded2.png"));

            Thread.sleep(1000);
            jLabel1.setIcon(null);
            System.out.println("done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
