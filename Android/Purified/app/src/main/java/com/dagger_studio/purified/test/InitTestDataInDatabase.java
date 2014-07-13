package com.dagger_studio.purified.test;

import android.app.Activity;

/**
 * Created by alex on 14-7-13.
 */
public class InitTestDataInDatabase {
    public InitTestDataInDatabase(){};
    public InitTestDataInDatabase(Activity activity){
        this.activity = activity;
    }

    public void loadDataBase(){

    }

    private String[] urls={"http://www.news.163.com","http://zhihu.com","http://today.hit.edu.cn","http://news.163.com/14/0522/13/9SRPNPSG0001124J.html","http://news.163.com/14/0522/18/9SSBI50R00014JB6.html"};//TODO
    private String[] titles={"我精选的咱们学业领域的新闻\n专门的设计者趣味新闻，希望喜欢~","你知道知乎有那么多逗比回答吗？\n我找了很久，精选了一些（持续更新）","Something about photography, wish you like!",};
    Activity activity;
}
