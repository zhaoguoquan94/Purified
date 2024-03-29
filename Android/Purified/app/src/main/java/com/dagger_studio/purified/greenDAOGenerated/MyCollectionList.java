package com.dagger_studio.purified.greenDAOGenerated;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table MY_COLLECTION_LIST.
 */
public class MyCollectionList {

    private Long id;
    private long expireLifeTime;
    private boolean isRead;
    private boolean isDivActivated;
    private boolean isTop;
    /** Not-null value. */
    private String url;
    /** Not-null value. */
    private String title;
    private String imgPath;

    public MyCollectionList() {
    }

    public MyCollectionList(Long id) {
        this.id = id;
    }

    public MyCollectionList(Long id, long expireLifeTime, boolean wisRead, boolean isDivActivated, boolean isTop, String url, String title, String imgPath) {
        this.id = id;
        this.expireLifeTime = expireLifeTime;
        this.isRead = isRead;
        this.isDivActivated = isDivActivated;
        this.isTop = isTop;
        this.url = url;
        this.title = title;
        this.imgPath = imgPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getExpireLifeTime() {
        return expireLifeTime;
    }

    public void setExpireLifeTime(long expireLifeTime) {
        this.expireLifeTime = expireLifeTime;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public boolean getIsDivActivated() {
        return isDivActivated;
    }

    public void setIsDivActivated(boolean isDivActivated) {
        this.isDivActivated = isDivActivated;
    }

    public boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(boolean isTop) {
        this.isTop = isTop;
    }

    /** Not-null value. */
    public String getUrl() {
        return url;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUrl(String url) {
        this.url = url;
    }

    /** Not-null value. */
    public String getTitle() {
        return title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

}
