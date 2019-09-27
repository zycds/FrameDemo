package com.zhangyc.framedemo.entity;

import java.util.List;

public class Article {

    private String apkLink;
    private int audit;
    private String author;
    private int chapterId;
    private String chapterName;
    private boolean collect;
    private int courseId;
    private String desc;
    private String envelopePic;
    private int id;
    private boolean fresh;
    private String link;
    private String niceDate;
    private String niceShareDate;
    private String origin;
    private String prefix;
    private String projectLink;
    private long publishTime;
    private long shareDate;
    private String shareUser;
    private int superChapterId;
    private String superChapterName;
    private String title;
    private int type;
    private int userId;
    private int visible;
    private int zan;
    private List<Tags> mTags;

    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public int getAudit() {
        return audit;
    }

    public void setAudit(int audit) {
        this.audit = audit;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getNiceShareDate() {
        return niceShareDate;
    }

    public void setNiceShareDate(String niceShareDate) {
        this.niceShareDate = niceShareDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public long getShareDate() {
        return shareDate;
    }

    public void setShareDate(long shareDate) {
        this.shareDate = shareDate;
    }

    public String getShareUser() {
        return shareUser;
    }

    public void setShareUser(String shareUser) {
        this.shareUser = shareUser;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public List<Tags> getTags() {
        return mTags;
    }

    public void setTags(List<Tags> tags) {
        mTags = tags;
    }

    public class Tags{
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"name\":\"")
                    .append(name).append('\"');
            sb.append(",\"url\":\"")
                    .append(url).append('\"');
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"apkLink\":\"")
                .append(apkLink).append('\"');
        sb.append(",\"audit\":")
                .append(audit);
        sb.append(",\"author\":\"")
                .append(author).append('\"');
        sb.append(",\"chapterId\":")
                .append(chapterId);
        sb.append(",\"chapterName\":\"")
                .append(chapterName).append('\"');
        sb.append(",\"collect\":")
                .append(collect);
        sb.append(",\"courseId\":")
                .append(courseId);
        sb.append(",\"desc\":\"")
                .append(desc).append('\"');
        sb.append(",\"envelopePic\":\"")
                .append(envelopePic).append('\"');
        sb.append(",\"id\":")
                .append(id);
        sb.append(",\"fresh\":")
                .append(fresh);
        sb.append(",\"link\":\"")
                .append(link).append('\"');
        sb.append(",\"niceDate\":\"")
                .append(niceDate).append('\"');
        sb.append(",\"niceShareDate\":\"")
                .append(niceShareDate).append('\"');
        sb.append(",\"origin\":\"")
                .append(origin).append('\"');
        sb.append(",\"prefix\":\"")
                .append(prefix).append('\"');
        sb.append(",\"projectLink\":\"")
                .append(projectLink).append('\"');
        sb.append(",\"publishTime\":")
                .append(publishTime);
        sb.append(",\"shareDate\":")
                .append(shareDate);
        sb.append(",\"shareUser\":\"")
                .append(shareUser).append('\"');
        sb.append(",\"superChapterId\":")
                .append(superChapterId);
        sb.append(",\"superChapterName\":\"")
                .append(superChapterName).append('\"');
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"type\":")
                .append(type);
        sb.append(",\"userId\":")
                .append(userId);
        sb.append(",\"visible\":")
                .append(visible);
        sb.append(",\"zan\":")
                .append(zan);
        sb.append(",\"mTags\":")
                .append(mTags);
        sb.append('}');
        return sb.toString();
    }
}
