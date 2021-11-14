package com.zxz.game;

/**
 * @author 周贤筑
 * @version 1.0
 */
public class Snake {
    //该数组存储贪吃蛇x坐标
    private int[] snakeX = new int[200];
    //该数组存储贪吃蛇y坐标
    private int[] snakeY = new int[200];
    //长度
    private int length;
    //方向
    private String direction;
    //蛇存活状态
    private boolean isLive = true;

    public Snake() {
    }

    public Snake(int[] snakeX, int[] snakeY, int length, String direction) {
        this.snakeX = snakeX;
        this.snakeY = snakeY;
        this.length = length;
        this.direction = direction;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int[] getSnakeX() {
        return snakeX;
    }

    public void setSnakeX(int[] snakeX) {
        this.snakeX = snakeX;
    }

    public int[] getSnakeY() {
        return snakeY;
    }

    public void setSnakeY(int[] snakeY) {
        this.snakeY = snakeY;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
