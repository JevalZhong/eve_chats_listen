package com.jevalable.cn;

import com.jevalable.cn.adaptor.MyFileListenerAdaptor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainStart {

    private  static String FILE_PATH;
    private  static String FILE_SUFFIX = ".txt";
    public static long lineNum = 0;
    public static String FILE_LISTEN_NAME;

    public static File MP3_FILE;

    public static void main(String[] args){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入聊天日志文件夹定位：");
            FILE_PATH = sc.nextLine();

            System.out.print("请输入频道：");
            FILE_LISTEN_NAME = sc.nextLine();

            System.out.print("监听关键字，英文逗号隔开：");
            String stars = sc.nextLine();
            String[] starStrings = stars.split(",");
            List<String> starList =  Arrays.asList(starStrings);

            System.out.println("设置完成，正在监听聊天频道 =========================> ");

            //由于jar包读取不到包内mp3文件，所以生成tmp缓存文件处理
            MainStart mainStart = new MainStart();
            InputStream is = mainStart.getClass().getResourceAsStream("/mp3/110.mp3");
            MP3_FILE = new File(MainStart.FILE_PATH + "/musics/110.tmp");
            FileUtils.copyInputStreamToFile(is, MP3_FILE);

            MyFileAlterationMonitor monitor = new MyFileAlterationMonitor(
                    FILE_PATH,
                    FILE_SUFFIX,
                    new MyFileListenerAdaptor(starList)
            );
            monitor.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
