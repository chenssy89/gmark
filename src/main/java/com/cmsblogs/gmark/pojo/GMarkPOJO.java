package com.cmsblogs.gmark.pojo;

/**
 * 实体类
 *
 * 只有一个实体对象，不需要区分VO、DTO 那么复杂，一个 POJO 就可以搞定
 */
public class GMarkPOJO {

    private String website;

    private String blogUrl;

    private String pictureFilePath;

    private String imageUrl;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
    }

    public String getPictureFilePath() {
        return pictureFilePath;
    }

    public void setPictureFilePath(String pictureFilePath) {
        this.pictureFilePath = pictureFilePath;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
