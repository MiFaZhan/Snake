package Snack;

import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class snackWin extends JPanel implements ActionListener, KeyListener {

    //     用数字代表方向
    static final int Up = 0 , Down = 1 , Left = 2 , Right = 3;
    //    GameWidth/GameHeight ：游戏区域的宽、高
//    GameLocX / GameLocY：游戏区域的左上角位置的坐标
    static final int GameLocX = 50, GameLocY = 50 , GameWidth = 700 , GameHeight = 500 , Size = 10;//Size:每次移动的位置大小以及增加的长度
    //    rx,ry：食物的坐标
    static int rx , ry , score = 0 , speed = 5;
    boolean startFlag = false;


    JButton startButton , stopButton , quitButton;
    Snack snack;

    public snackWin() {
        snack = new Snack();
//        随机设置食物的位置
        rx = (int)(Math.random() * (GameWidth - 10) + GameLocX);
        ry = (int)(Math.random() * (GameHeight - 10) + GameLocY);

        startButton = new JButton("开始");
        stopButton = new JButton("暂停");
        quitButton = new JButton("退出");

        setLayout(new FlowLayout(FlowLayout.LEFT));
        // 添加三个按键：开始、暂停、退出
        this.add(startButton);
        this.add(stopButton);
        this.add(quitButton);
        // 添加三个按键的监听事件
        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        quitButton.addActionListener(this);
        this.addKeyListener(this);
        new Thread(new snackThread()).start();
    }

    public void paint(Graphics g)
    {
        super.paint(g);   //没有会将button刷掉
//        设置画笔颜色
        g.setColor(Color.white);
//       用白色 填充一个矩形区域
        g.fillRect(GameLocX, GameLocY, GameWidth, GameHeight);
        g.setColor(Color.black);
//        drawRect用于画矩形；X、Y为左上角的位置，width和height为矩形的宽和高
//         用黑色化一个矩形
        g.drawRect(GameLocX, GameLocY, GameWidth, GameHeight);
//        绘制字符串
        g.drawString("Score: " + score + "        Speed: " + speed + "      速度最大为100" , 250, 25);
//        用绿色填充一个长宽为10的矩形 表示 食物
        g.setColor(Color.green);
        g.fillRect(rx, ry, 10, 10);
//        画蛇
        snack.draw(g);
    }

    public void actionPerformed(ActionEvent e) {
//      当开始按钮被点击时，开始按钮将不能再被点击
        if(e.getSource() == startButton) {
            startFlag = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        }
        if(e.getSource() == stopButton) {
            startFlag = false;
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
//        退出程序
        if(e.getSource() == quitButton) {
            System.exit(0);
        }
        this.requestFocus();
    }

    /**
     * 当键盘被按下时调用
     */
    public void keyPressed(KeyEvent e) {
//     当还没按下开始按钮时，按键不可用
        if(!startFlag)
            return ;

        switch(e.getKeyCode()) {
//         如果蛇的长度为1，则表示刚开始，什么方向都可以；
//         若蛇的长度不为1，则表示它在运动，此时不能操作它向反方向运动
            case KeyEvent.VK_UP:
                if(snack.length() != 1 && snack.getDir() == Down) break;
                snack.changeDir(Up);
                break;
            case KeyEvent.VK_DOWN:
                if(snack.length() != 1 && snack.getDir() == Up) break;
                snack.changeDir(Down);
                break;
            case KeyEvent.VK_LEFT:
                if(snack.length() != 1 && snack.getDir() == Right) break;
                snack.changeDir(Left);
                break;
            case KeyEvent.VK_RIGHT:
                if(snack.length() != 1 && snack.getDir() == Left) break;
                snack.changeDir(Right);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    class snackThread implements Runnable
    {
        public void run() {
            while(true) {
                try {
//                  通过线程休眠时间控制蛇的速度
                    Thread.sleep(100 - speed >= 0 ? 100 - speed : 0);
                    repaint(); // 调用paint()，重绘组件（刷新界面）
                    if(startFlag) {
                        snack.move();
                    }
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}