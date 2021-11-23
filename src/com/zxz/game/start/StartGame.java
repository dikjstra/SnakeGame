package com.zxz.game.start;

import com.zxz.game.gamemap.GameMap;

import javax.swing.*;
import java.awt.*;

/**
 * @author 周贤筑
 * @version 1.0
 */
public class StartGame {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setTitle("贪吃蛇");
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //窗体坐标（居中）
        jf.setBounds((width-800)/2,(height - 800) / 2,800,800);
        //窗体大小不可调
        jf.setResizable(false);
        //关闭窗体即结束程序
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //创建面板
        GameMap map = new GameMap();
        jf.add(map);
        jf.setVisible(true);
    }
}
