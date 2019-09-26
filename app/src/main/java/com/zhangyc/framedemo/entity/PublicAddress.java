package com.zhangyc.framedemo.entity;

public class PublicAddress {

   /* data: [
    {
        children: [ ],
        courseId: 13,
                id: 408,
            name: "鸿洋",
            order: 190000,
            parentChapterId: 407,
            userControlSetTop: false,
            visible: 1
    }*/

   private int courseId;
   private int id;
   private String name;
   private int order;
   private int parentChapterId;
   private boolean userControlSetTop;
   private int visible;

    public PublicAddress(int courseId, int id, String name, int order, int parentChapterId, boolean userControlSetTop, int visible) {
        this.courseId = courseId;
        this.id = id;
        this.name = name;
        this.order = order;
        this.parentChapterId = parentChapterId;
        this.userControlSetTop = userControlSetTop;
        this.visible = visible;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"courseId\":")
                .append(courseId);
        sb.append(",\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"order\":")
                .append(order);
        sb.append(",\"parentChapterId\":")
                .append(parentChapterId);
        sb.append(",\"userControlSetTop\":")
                .append(userControlSetTop);
        sb.append(",\"visible\":")
                .append(visible);
        sb.append('}');
        return sb.toString();
    }
}
