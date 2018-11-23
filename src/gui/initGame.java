package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import characters.Entity;
import characters.Wall;
import characters.*;

import javax.swing.*;

public class initGame extends JFrame implements KeyListener, ActionListener {
    private Entity _array[][] = new Entity[50][50];
    private JPanel jPanel;
    private JPanel panelInfo;
    private JPanel container;

    private int height, width;
    private int point = 0;
    private JLabel bomer = new JLabel();
    private Bomer _bomer;
    private List<Entity> balloonList = new ArrayList<Entity>();
    private List<JLabel> jballoonList = new ArrayList<JLabel>();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenuItem itemNewGame = new JMenuItem("New game");
    private JMenuItem itemExit = new JMenuItem("Exit");
    private JMenu menuHelp = new JMenu("Help");
    private JMenuItem itemConstruction = new JMenuItem("How to play");
    private JMenuItem itemAbout = new JMenuItem("About");

    private JLabel pointsLabel;
    public initGame()
    {
        jPanel = initGameGame("../BTL_OOP_Game/level/level1.txt");

        BufferedReader br = null;
        String infor = null;
        try {
            br = new BufferedReader(new FileReader("../BTL_OOP_Game/level/level1.txt"));
            infor = br.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }

        height = Integer.parseInt(infor.split(" ")[0]);
        width = Integer.parseInt(infor.split(" ")[1]);
        System.out.println(height + " " + width);

        itemNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        itemConstruction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        itemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        menuFile.add(itemNewGame);
        menuFile.add(itemExit);
        menuHelp.add(itemConstruction);
        menuHelp.add(itemAbout);

        menuBar.add(menuFile);
        menuBar.add(menuHelp);

        itemNewGame.addActionListener(this);
        itemExit.addActionListener(this);
        itemConstruction.addActionListener(this);
        itemAbout.addActionListener(this);

        panelInfo = new JPanel(new GridLayout());
        panelInfo.setBackground(Color.BLACK);

        pointsLabel = new JLabel("Points: " + point);// getPoint
        pointsLabel.setForeground(Color.white);
        pointsLabel.setHorizontalAlignment(JLabel.CENTER);

        panelInfo.add(pointsLabel);

        panelInfo.setPreferredSize(new Dimension((int)((width)*50), 30));
        jPanel.setPreferredSize( new Dimension((int)((width)*50), (height+1)*50));

        container = new JPanel(new BorderLayout());

        container.add(panelInfo, BorderLayout.NORTH);
        container.add(jPanel, BorderLayout.CENTER);


        setJMenuBar(menuBar);
        add(container);

        setTitle("Test JFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        pack();//must


    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == itemNewGame)
        {
            // add new  game
        }
        if(e.getSource() == itemExit)
        {
            System.exit(0);
        }
        if(e.getSource() == itemConstruction)
        {
           JOptionPane.showMessageDialog(this, "Movement: use W, S, A, D or UP, DOWN, LEFT, RIGHT\nPut bomb: use SPACE", "How to play", JOptionPane.PLAIN_MESSAGE);
        }
        if(e.getSource() == itemAbout)
        {
            JOptionPane.showMessageDialog(this, "Version: 1.0\nAuthors: HAP TIEN QUAN & PHUNG THI TUYET MAI\n", "About", JOptionPane.PLAIN_MESSAGE);
        }
    }
    public JLabel creatLabelEntity(Entity _tmp)
    {
        JLabel jLabel = new JLabel(new ImageIcon(_tmp.getPath()));
        jLabel.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        return jLabel;
    }
    public JPanel initGameGame(String path){
        JPanel jPanel = new JPanel(null);
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String infor = br.readLine();
            height = Integer.parseInt(infor.split(" ")[0]);
            width = Integer.parseInt(infor.split(" ")[1]);
            System.out.println(height + " " + width);
            for(int i = 0; i < height; i++){
                String oneLine = br.readLine();
                char[] arr = oneLine.toCharArray();
                for(int j=0; j< width; j++){
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
                    if(arr[j] == '!'){
                        Entity _brick = new Brick(j, i);
                        _array[i][j] = _brick;
                        ((Brick) _array[i][j]).setHasItem(true);
                        ((Brick) _array[i][j]).setPathItem("../BTL_OOP_Game/image/item1.png");
                        ((Brick) _array[i][j]).setTypeItem("LIMIT_BOM");
                        jPanel.add(creatLabelEntity(_brick));
                    }
                    if(arr[j] == '1')
                    {
                        JLabel jballoon;
                        Entity _balloon;

                        _balloon = new Balloon(j, i);
                        _balloon.setCanMove(true);
                        _array[i][j] = _balloon;
                        jballoon = creatLabelEntity(_balloon);

                        balloonList.add(_balloon);
                        jballoonList.add(jballoon);

                        Entity _tile = new Tile(j, i);

                        jPanel.add(creatLabelEntity(_tile), 0);
                        jPanel.add(jballoon, 0);

                    }
                }
            }

            this.addKeyListener(this);

            int i = 0;
            for(i = 0; i < balloonList.size(); i++)
            {

                AI ai = new AI(jPanel,balloonList.get(i), jballoonList.get(i),_array);
                ai.start();
                System.out.println("done");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jPanel;
    }

    public void turnRight(Entity _tmp){
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_x(_tmp.get_x()+1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
    }
    public void turnLeft(Entity _tmp){
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_x(_tmp.get_x()-1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));

        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
    }
    public void turnUp(Entity _tmp){
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_y(_tmp.get_y()-1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
    }
    public void turnDown(Entity _tmp){
        _array[_tmp.get_y()][_tmp.get_x()] = new Tile(_tmp.get_y(), _tmp.get_x());
        _tmp.set_y(_tmp.get_y()+1);
        bomer.setIcon(new ImageIcon( _tmp.getPath()));
        bomer.setBounds(_tmp.get_x()*50, _tmp.get_y()*50, 50, 50);
        jPanel.add(bomer, 0);
    }
    public void getBomb(Bomer _tmp)
    {
        BombExplode bombExplode= new BombExplode(jPanel, _tmp, _array);
        bombExplode.start();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'a': {
                if(checkMove('a', _bomer)){
                    turnLeft(_bomer);
                }
                break;
            }
            case 'd': {
                if(checkMove('d', _bomer)){
                    turnRight(_bomer);
                }
                break;
            }
            case 'w': {
                if(checkMove('w', _bomer)){
                    turnUp(_bomer);
                }
                break;
            }
            case 's': {
                if(checkMove('s', _bomer)){
                    turnDown(_bomer);
                }
                break;
            }
            case KeyEvent.VK_SPACE:
            {
                if(_bomer.get_limit_bom()==_bomer.get_bom_number()){
                    break;
                }
                else{
                    _array[_bomer.get_y()][_bomer.get_x()] = new Bomb(_bomer.get_x(), _bomer.get_y());
                    System.out.println(_bomer.get_y() + " " + _bomer.get_x());
                    if(_array[_bomer.get_y()][_bomer.get_x()] instanceof Bomb){
                        System.out.println("ok");
                    }
                    _bomer.set_bom_number(_bomer.get_bom_number()+1);
                    getBomb(_bomer);

                    // vi du
                    point++;
                    pointsLabel.setText("Points: " + point);
                    break;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    private boolean checkMove(char tmp, Entity _tmp){
        if(_array[_tmp.get_y()][_tmp.get_x()-1] instanceof Bomb){
            System.out.println("ok");
        }
        if(tmp == 'a' && !(_array[_tmp.get_y()][_tmp.get_x()-1] instanceof Tile)){
            return false;
        }
        if(tmp == 's' && !(_array[_tmp.get_y()+1][_tmp.get_x()] instanceof Tile)){
            return false;
        }
        if(tmp == 'd' && !(_array[_tmp.get_y()][_tmp.get_x()+1] instanceof Tile)){
            return false;
        }
        if(tmp == 'w' && !(_array[_tmp.get_y()-1][_tmp.get_x()] instanceof Tile)){
            return false;
        }
        return true;
    }


}