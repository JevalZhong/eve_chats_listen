package com.jevalable.cn.thread;

import com.jevalable.cn.MainStart;
import javazoom.jl.player.Player;
import org.apache.commons.io.FileUtils;

import java.io.*;

public class Mp3Thread implements Runnable{
    @Override
    public void run() {
        //设置触发音效。
        try {

            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(MainStart.MP3_FILE));
            Player player = new Player(buffer);
            player.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
