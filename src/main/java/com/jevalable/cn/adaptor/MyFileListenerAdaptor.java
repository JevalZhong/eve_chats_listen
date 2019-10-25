package com.jevalable.cn.adaptor;

import com.jevalable.cn.MainStart;
import com.jevalable.cn.thread.Mp3Thread;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyFileListenerAdaptor extends FileAlterationListenerAdaptor {

    private List<String> starList;

    public MyFileListenerAdaptor(List<String> starList) {
        this.starList = starList;
    }

    @Override
    public void onFileChange(File file) {
        String fileChangePath = MainStart.FILE_LISTEN_NAME;
        try {
            if (file.getName().contains(fileChangePath)) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                List<String> lineString = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    lineString.add(line);
                }
                //获取之前预警的聊天行数
                long beforeLineNum = MainStart.lineNum;

                //预警行数更新
                MainStart.lineNum = lineString.size();

                //获取预警的消息。并判断设置的关键字是否包含在预警中。
                for (int i = (int)beforeLineNum; i < lineString.size(); i++){
                    String chatString = lineString.get(i).toLowerCase();
                    int starListCount = starList.stream()
                            .filter(chatString::contains)
                            .collect(Collectors.toList())
                            .size();
                    if (starListCount > 0){

                        System.out.println("频道预警！~！~！~！~！~！~！~！~！~！~滴度，滴度！");

                        //触发音效播放，110.MP3
                        Mp3Thread mp3Thread = new Mp3Thread();
//                        mp3Thread.run();
                        new Thread(mp3Thread).start();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onFileChange(file);
    }
}
