package Snack;

import javax.swing.*;

public class greedySnackMain extends JFrame {
    snackWin snackwin;
    static final int Width = 800 , Height = 600 , LocX = 200 , LocY = 80;

    public greedySnackMain() {
        super("GreedySncak_SL");
        snackwin = new snackWin();
        //在JFrame窗口容器里添加其他组件
        add(snackwin);
        //设置组件的大小
        this.setSize(Width, Height);
        //设置组件的可见性
        this.setVisible(true);
        //设置组件的位置
        this.setLocation(LocX, LocY);
        //snackwin.requestFocus();
    }

    public static void main(String[] args) {
        new greedySnackMain();
    }
}
