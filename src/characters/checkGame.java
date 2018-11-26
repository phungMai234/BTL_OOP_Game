package characters;

import javax.swing.*;

public class checkGame extends Thread{
    private Balloon _arrayBalloon[];
    private JPanel jPanel;
    public checkGame(Balloon _arrayBalloon[], JPanel jPanel){
        this._arrayBalloon = _arrayBalloon;
        this.jPanel = jPanel;
    }

    @Override
    public void run() {
        checkWin(_arrayBalloon, jPanel);
    }
    public void checkWin(Balloon _arr[], JPanel tmp){
        for(int i=0; i<_arr.length; i++){
            if(_arr[i]!=null){
                if(_arr[i].isLife()){
                    return;
                }
            }
        }
        jPanel.setVisible(false);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(new ImageIcon("../BTL_OOP_Game/image/wingame.png"));
        jLabel.setBounds(0,0,17*50, 14*50);
        jPanel.add(jLabel, 0);
        jPanel.setVisible(true);
        System.out.println("done");
    }

}
