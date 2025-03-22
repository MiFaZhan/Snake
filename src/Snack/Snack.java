package Snack;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 *  存放每个点的X、Y坐标
 */
class Node {
    private int x , y;
    public Node() {}
    public Node(int a , int b){
        x = a;
        y = b;
    }
    public Node(Node tmp) {
        x = tmp.getX();
        y = tmp.getY();
    }
    int getX() {
        return x;
    }
    int getY() {
        return y;
    }
    void setX(int a) {
        x = a;
    }
    void setY(int b) {
        y = b;
    }
}


public class Snack {

    static final int DIR [][] = {{0 , -1} , {0 , 1} , {-1 , 0} , {1 , 0}};
    private List<Node> lt = new ArrayList<Node>();
    private int curDir; //当前运动方向

    /**
     *  初始化
     *  蛇的初始方向是向右，Right=3
     */
    public Snack() {
        curDir = 3;
        lt.add(new Node(350 , 250));
    }

    /**
     *  List的长度表示蛇的长度
     * @return
     */
    int length() {
        return lt.size();
    }

    int getDir() {
        return curDir;
    }

    /**
     * Graphics，绘图类
     *  画蛇
     *  找到每个下标指向的X、Y值，即组成蛇的每一点所在位置
     */
    void draw(Graphics g)
    {
        g.setColor(Color.black);
        for(int i = 0; i < lt.size(); i++) {
            g.fillRect(lt.get(i).getX(), lt.get(i).getY(), 10, 10);
        }
    }

    /**
     *  判断蛇是否咬到自己
     *  如果蛇的头部（0）与其他位置重合，则表示蛇咬到自己了
     * @return
     */
    boolean Dead() {
        for(int i = 1; i < lt.size(); i++) {
            if(lt.get(0).getX() == lt.get(i).getX() && lt.get(0).getY() == lt.get(i).getY())
                return true;
        }
        return false;
    }

    /**
     * 头部移动的位置
     * @return tmp：头部的位置坐标
     */
    Node headMove()
    {
//      当向上或向下时，curDir为0/1，此时X轴不变（即 Snack.snackWin.Size * DIR[curDir][0]的值为0），Y轴变化
//      当向左或向右时，curDir为2/3，此时Y轴不变（即 Snack.snackWin.Size * DIR[curDir][1]的值为0），X轴变化
        int tx = lt.get(0).getX() + snackWin.Size * DIR[curDir][0];
        int ty = lt.get(0).getY() + snackWin.Size * DIR[curDir][1];

//     用一个矩形代表蛇的一个节点，矩形的长宽为10，故设置Size也为10
//     当头部的位置大于游戏区域的宽度证明它到达了最右边，之后它需要从左边出现，即X坐标变为最左边的X坐标位置
        if(tx > snackWin.GameLocX + snackWin.GameWidth - snackWin.Size)
            tx = snackWin.GameLocX;
//     当头部的位置小于左边的X坐标，证明它向左走并到达最左边，之后它需要从右边出现，即X坐标变为最右边的X坐标位置
        if(tx < snackWin.GameLocX)
            tx = snackWin.GameWidth + snackWin.GameLocX - snackWin.Size;
//     与上同
        if(ty > snackWin.GameLocY + snackWin.GameHeight - snackWin.Size)
            ty = snackWin.GameLocY;
        if(ty < snackWin.GameLocY)
            ty = snackWin.GameHeight + snackWin.GameLocY - snackWin.Size;
        Node tmp = new Node(tx, ty);
        return tmp;
    }

    void move()
    {
//      头部的位置
        Node head = headMove();
//    新添加的节点的位置
        Node newNode = new Node();
        boolean eat = false;

//     abs()返回参数的绝对值
//     通过判断头部的X/Y坐标位置和食物的X/Y坐标位置，由于头部大小是10，故距离小于10就代表吃到了
        if(Math.abs(head.getX() - snackWin.rx) < 10 && Math.abs(head.getY() - snackWin.ry) < 10) {
            eat = true;
            newNode = new Node(lt.get(lt.size() - 1));
            snackWin.rx = (int)(Math.random() * (snackWin.GameWidth - 10) + snackWin.GameLocX);
            snackWin.ry = (int)(Math.random() * (snackWin.GameHeight - 10) + snackWin.GameLocY);
        }

//      把前一个点的位置给后一点（即移动）
        for(int i = lt.size() - 1; i > 0; i--)
            lt.set(i, lt.get(i - 1));
        lt.set(0, head);

        if(Dead()) {
//          弹出对话框，告知游戏结束
            JOptionPane.showMessageDialog(null, "Snake eating itself", "Message", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
//        如果吃到了食物，则eat为true。此时添加节点、分数加一，速度加一
        if(eat) {
            lt.add(newNode);
            snackWin.score++;
            snackWin.speed++;
        }
    }

    /**
     *  改变蛇的方向
     * @param dir
     */
    void changeDir(int dir) {
        curDir = dir;
    }

}