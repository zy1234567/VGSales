package com.ztstech.vgsales.mapper;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public interface Mapper<B, M> {

    /**
     * 转换bean为model
     * @param bean
     * @return
     */
    M transform(B bean);
}
