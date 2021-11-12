package com.zxz.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author 周贤筑
 * @version 1.0
 * 游戏界面面板
 */
public class MyPanel extends JPanel {
    //蛇对象
    Snake snake = null;
    //食物对象
    Food food = null;
    //蛇存活状态
    boolean isLive = true;
    //蛇朝向，默认向右
    //String direction;
    //游戏默认暂停
    boolean isStart = false;
    //设置游戏所需图片
    Image bodyImg = null;
    Image bodyDownImg = null;
    Image bodyUpImg = null;
    Image bodyLeftImg = null;
    Image bodyRightImg = null;
    Image foodImg = null;
    //设置定时器
    Timer timer;

    //该数组存储贪吃蛇x坐标
    int[] snakeX = new int[200];
    //该数组存储贪吃蛇y坐标
    int[] snakeY = new int[200];

    public void init(){
        //初始化蛇对象
        snake = new Snake();
        //初始化方向为R
        snake.setDirection("R");
        //初始化积分
        Score.scores = 0;
        //初始化蛇长
        snake.setLength(3);
        //初始化蛇的头坐标
        snakeX[0] = 175;
        snakeY[0] = 275;
        //身体第一节
        snakeX[1] = 150;
        snakeY[1] = 275;
        //身体第二节
        snakeX[2] = 125;
        snakeY[2] = 275;

        snake.setSnakeX(snakeX);
        snake.setSnakeY(snakeY);
        //初始化食物坐标
        food = new Food(250,300);

        //初始化图片
        //image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        bodyImg = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/body.png"));
        bodyDownImg = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/down.png"));
        bodyUpImg = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/up.png"));
        bodyLeftImg = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/left.png"));
        bodyRightImg = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/right.png"));
        foodImg = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/food.png"));

        timer = new Timer(100, new ActionListener() {
            //蛇移动
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStart && isLive){
                    //后一节身体替换前一节身体
                    for (int i = snake.getLength() - 1; i > 0; i--) {
                        snakeX[i] = snakeX[i - 1];
                        snakeY[i] = snakeY[i - 1];
                    }
                    if(snake.getDirection() == "R"){
                        snakeX[0] += 25;
                        if(snakeX[0] > 800){
                            snakeX[0] = 0;
                        }
                    }else if(snake.getDirection() == "L"){
                        snakeX[0] -= 25;
                        if(snakeX[0] < 0){
                            snakeX[0] = 775;
                        }
                    }
                    else if (snake.getDirection() == "U"){
                        snakeY[0] -= 25;
                        if(snakeY[0] < 50){
                            snakeY[0] = 775;
                        }
                    }
                    else if (snake.getDirection() == "D"){
                        snakeY[0] += 25;
                        if(snakeY[0] > 800){
                            snakeY[0] = 75;
                        }
                    }
                    //蛇吃到食物
                    if(snakeX[0] == food.getX() && snakeY[0] == food.getY()){
                        snake.setLength(snake.getLength() + 1);
                        Score.scores += 10;
                        food.setX((int)((Math.random() * 31) + 1) * 25);//25 - 800
                        food.setY((int)((Math.random() * 28) + 3) * 25 );//75 - 800
                    }
                    //死亡条件
                    for (int i = 1; i < snake.getLength(); i++) {
                        if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                            isLive = false;
                        }
                    }
                    repaint();
                }
            }
        });
        //启动定时器
        timer.start();
    }

    public MyPanel(){
        init();
        //将焦点定位在面板上
        this.setFocusable(true);
        //加入监听
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_SPACE){
                    if(isLive){
                        isStart = !isStart;
                        repaint();
                    }else{
                        timer.stop();
                        init();//初始化游戏
                        isLive = true;
                    }
                }
                if(isStart){
                    switch(e.getKeyCode()){
                        case KeyEvent.VK_W:
                            if(snake.getDirection() != "D"){
                                snake.setDirection("U");
                                repaint();
                            }
                            break;
                        case KeyEvent.VK_A:
                            if(snake.getDirection() != "R"){
                                snake.setDirection("L");
                                repaint();
                            }
                            break;
                        case KeyEvent.VK_D:
                            if(snake.getDirection() != "L"){
                                snake.setDirection("R");
                                repaint();
                            }
                            break;
                        case KeyEvent.VK_S:
                            if(snake.getDirection() != "U"){
                                snake.setDirection("D");
                                repaint();
                            }
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //填充背景颜色
        this.setBackground(new Color(4, 245, 63, 62));
        //游戏区域
        g.fillRect(0,50,800,750);

        //画蛇头
        switch(snake.getDirection()){
            case "R":
                g.drawImage(bodyRightImg,snakeX[0],snakeY[0],this);
                break;
            case "L":
                g.drawImage(bodyLeftImg,snakeX[0],snakeY[0],this);
                break;
            case "U":
                g.drawImage(bodyUpImg,snakeX[0],snakeY[0],this);
                break;
            case "D":
                g.drawImage(bodyDownImg,snakeX[0],snakeY[0],this);
                break;
        }
        //画蛇身
        for (int i = 1; i < snake.getLength(); i++) {
            g.drawImage(bodyImg,snakeX[i],snakeY[i],this);
        }
        //画食物
        g.drawImage(foodImg,food.getX(),food.getY(),this);
        //画积分
        g.setColor(new Color(236, 9, 32));
        g.setFont(new Font("微软雅黑",Font.BOLD,30));
        g.drawString("当前得分：" + Score.scores,25,35);
        //死亡提示
        if(!isLive){
            g.setColor(new Color(241, 1, 1));
            g.setFont(new Font("微软雅黑",Font.BOLD,30));
            g.drawString("游戏结束，按下空格后重新开始游戏",300,35);
        }

        if(!isStart){
            g.setColor(new Color(249, 6, 239));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("点击空格开始游戏",300,35);
        }
    }
}
