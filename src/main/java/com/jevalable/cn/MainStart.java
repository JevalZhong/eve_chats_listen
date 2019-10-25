package com.jevalable.cn;

import com.jevalable.cn.adaptor.MyFileListenerAdaptor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainStart {

    private  static String FILE_PATH;//定位的聊天文件位置
    private final static String FILE_SUFFIX = ".txt";//需要监听的文件名后缀
    public static long lineNum = 0;//当前标记的聊天行数。
    public static String FILE_LISTEN_NAME;//监听发额频道名称

    public static File MP3_FILE;//声音预警文件

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
            List<String> starList = Arrays.asList(starStrings);

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
