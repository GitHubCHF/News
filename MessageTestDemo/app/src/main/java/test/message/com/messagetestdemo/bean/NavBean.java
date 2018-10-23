package test.message.com.messagetestdemo.bean;

import java.util.List;

public class NavBean {
    /**
     * date : 20170919
     * list : [{"type":"news","class_id":"1","cat_id":"0","cat_level":2,"class_name":"聚焦"},{"type":"news","class_id":"11","cat_id":"1","cat_level":2,"class_name":"行业"},{"type":"news","class_id":"21","cat_id":"4","cat_level":2,"class_name":"企业"},{"type":"news","class_id":"31","cat_id":"6","cat_level":2,"class_name":"评论"},{"type":"news","class_id":"41","cat_id":"7","cat_level":2,"class_name":"人物"},{"type":"news","class_id":"51","cat_id":"5","cat_level":2,"class_name":"法规"},{"type":"news","class_id":"61","cat_id":"8","cat_level":2,"class_name":"打传"},{"type":"news","class_id":"65","cat_id":"9","cat_level":2,"class_name":"视频"},{"type":"news","class_id":"81","cat_id":"23","cat_level":2,"class_name":"媒体"},{"type":"news","class_id":"91","cat_id":"35","cat_level":2,"class_name":"制度"},{"type":"news","class_id":"100","cat_id":"49","cat_level":2,"class_name":"产品"},{"type":"news","class_id":"110","cat_id":"65","cat_level":2,"class_name":"专家"},{"type":"news","class_id":"120","cat_id":"4","cat_level":1,"class_name":"直销宝典"},{"type":"news","class_id":"130","cat_id":"93","cat_level":2,"class_name":"直销事业"},{"type":"news","class_id":"140","cat_id":"94","cat_level":2,"class_name":"直销产品"},{"type":"news","class_id":"150","cat_id":"9","cat_level":1,"class_name":"健康"},{"type":"news","class_id":"160","cat_id":"96","cat_level":2,"class_name":"国学"},{"type":"news","class_id":"170","cat_id":"97","cat_level":2,"class_name":"佛学"},{"type":"zxh","class_id":"93","class_name":"直销数据"},{"type":"zxh","class_id":"173","class_name":"直企简历"},{"type":"zxh","class_id":"86","class_name":"直销早报"},{"type":"zxh","class_id":"88","class_name":"今日要闻"},{"type":"zxh","class_id":"140","class_name":"尚赫公益"},{"type":"zxh","class_id":"133","class_name":"宝哥"},{"type":"zxh","class_id":"113","class_name":"秦永楠"},{"type":"zxh","class_id":"127","class_name":"正和系统"},{"type":"zxh","class_id":"149","class_name":"董恩如"},{"type":"zxh","class_id":"153","class_name":"家和联盟"},{"type":"zxh","class_id":"174","class_name":"直销博谈"}]
     */

    private String date;
    private List<ListBean> list;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * type : news
         * class_id : 1
         * cat_id : 0
         * cat_level : 2
         * class_name : 聚焦
         */

        private String type;
        private String class_id;
        private String cat_id;
        private int cat_level;
        private String class_name;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getClass_id() {
            return class_id;
        }

        public void setClass_id(String class_id) {
            this.class_id = class_id;
        }

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public int getCat_level() {
            return cat_level;
        }

        public void setCat_level(int cat_level) {
            this.cat_level = cat_level;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }
    }
}