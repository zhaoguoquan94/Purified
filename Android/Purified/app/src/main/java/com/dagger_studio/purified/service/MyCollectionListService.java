package com.dagger_studio.purified.service;

import java.util.List;

/**
 * Created by alex on 14-7-11.
 */
public interface MyCollectionListService {
    /**
     * 从数据库提取“我的收藏”并返回所有收藏的全部信息
     * @return List 里面储存了Mapping之后的封装类 EverythingMapping
     */
    public List<Object> getEverything();


    /**
     * 从数据库提取“我的收藏”并以标题+url的形式返回一个List用来显示简要清单
     * @return List 里面储存了Mapping之后的封装类 TitleMapping
     */
    public List<Object> getAllTitle();


    /**
     * 根据传入的ID来删除一个指定的Collection
     * @param id 一个字符串形式的ID，暂定为url+"&"+uid，方便提取
     * @return 返回是否成功
     */
    public boolean deleteMyCollectionById(String id);


    /**
     * 根据传入的ID来标记一个指定的Collection为已读
     * @param id 一个字符串形式的ID，暂定为url+"&"+uid，方便提取
     * @return 返回是否成功
     */
    public boolean markMyCollectionAsRead(String id);


    /**
     * 根据传入的ID来标记一个指定的Collection为已读
     * @param id 一个字符串形式的ID，暂定为url+"&"+uid，方便提取
     * @return 返回是否成功
     */
    public boolean markMyCollectionAsUnread(String id);

}
