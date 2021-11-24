package com.zxz.game.io;

import com.zxz.game.score.Score;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 周贤筑
 * @version 1.0
 */
public class SaveToFile {
    //保存文件路径
    final static String filePath = "src\\score.txt";
    private static FileWriter fileWriter;

    public static void save() {
        try {
            Date day = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTIme = df.format(day);
            fileWriter = new FileWriter(filePath, true);
            fileWriter.write("游戏时间：" + currentTIme + ",得分：" + Score.scores + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
