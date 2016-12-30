package com.iven.app.bean;

import java.util.List;

/**
 * @author Iven
 * @date 2016/12/30 10:28
 * @Description
 */

public class TestBean {

    /**
     * date : 20161230
     * stories : [{"images":["http://pic1.zhimg.com/099efceb17f9ea7d52dcc61ebe82e880.jpg"],"type":0,"id":9088824,"ga_prefix":"123010","title":"不习惯直系领导的管理风格，应该怎么办？"},{"images":["http://pic4.zhimg.com/90acad2b4705405f5f14c5d77f02239f.jpg"],"type":0,"id":9107334,"ga_prefix":"123009","title":"有哪些大牛在自己相关领域被打脸的故事？"},{"images":["http://pic3.zhimg.com/b8c62ca71fb016116db5e294fa8aa326.jpg"],"type":0,"id":8678472,"ga_prefix":"123008","title":"孩子说话爱用叠词，因为他们比我们想象的要聪明多了"},{"images":["http://pic1.zhimg.com/d4fdc01009ed51588b2f702e2ca23db8.jpg"],"type":0,"id":9108309,"ga_prefix":"123007","title":"这个百变的原生木材家具，却常常被我们浪费"},{"images":["http://pic3.zhimg.com/f32511a9a89ee288f49b8ea0e2b121ba.jpg"],"type":0,"id":9108380,"ga_prefix":"123007","title":"微信小程序和网页版程序的区别在哪里？"},{"images":["http://pic2.zhimg.com/9b71f6843759543390fb4b9843480079.jpg"],"type":0,"id":9108397,"ga_prefix":"123007","title":"2016 年度盘点 · 你被哪一波商业潮流影响了？"},{"images":["http://pic2.zhimg.com/ba40280ffbff7068cd33b717886f347d.jpg"],"type":0,"id":9106695,"ga_prefix":"123006","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic1.zhimg.com/051d54d521840485725dc9588fb1d63c.jpg","type":0,"id":9108397,"ga_prefix":"123007","title":"2016 年度盘点 · 你被哪一波商业潮流影响了？"},{"image":"http://pic2.zhimg.com/f388ad82ffe062736aeb3ada3a981129.jpg","type":0,"id":9108380,"ga_prefix":"123007","title":"微信小程序和网页版程序的区别在哪里？"},{"image":"http://pic2.zhimg.com/1c5fca7f9f79287e91ed260cbd143d21.jpg","type":0,"id":9107334,"ga_prefix":"123009","title":"有哪些大牛在自己相关领域被打脸的故事？"},{"image":"http://pic1.zhimg.com/a401147f1e886be5e268bed525e65edc.jpg","type":0,"id":9107484,"ga_prefix":"122917","title":"知乎好问题 · 初为人父觉得时间被孩子剥夺，该如何调整？"},{"image":"http://pic4.zhimg.com/383b26bbbd330a45e56e1ab0857f21bf.jpg","type":0,"id":9107014,"ga_prefix":"122915","title":"共享单车们准备进军海外，先挑了几个发达国家"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["http://pic1.zhimg.com/099efceb17f9ea7d52dcc61ebe82e880.jpg"]
         * type : 0
         * id : 9088824
         * ga_prefix : 123010
         * title : 不习惯直系领导的管理风格，应该怎么办？
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : http://pic1.zhimg.com/051d54d521840485725dc9588fb1d63c.jpg
         * type : 0
         * id : 9108397
         * ga_prefix : 123007
         * title : 2016 年度盘点 · 你被哪一波商业潮流影响了？
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
