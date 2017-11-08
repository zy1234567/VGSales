package com.ztstech.vgmate.data.events;

/**
 * Created by smm on 2017/11/8.
 * 发送资讯公告成功事件
 */

public class CreateShareEvent {

    /** 公告还是资讯 */
    public String type;

    public CreateShareEvent(String type){
        this.type = type;
    }

}
