package test.message.com.messagetestdemo.bean;

import java.util.List;


public class NewsBannerBean {

    /**
     * cid : 0
     * page : 1
     * page_count : 15
     * total_count : 63554
     * total_page : 4237
     * pic_list : [{"news_id":"1746","title":"传销式瑞波联储未死，冒牌瑞波币再现圈钱","pic":"http://oss.zhixiaoren.com/news/2018/09/13/16577309661536801392.jpg","url":"http://news.zhixiaoren.com/20180912/73384.html","intro":"","replys":"0","show_type":1,"marks":"","marks_style":"c4c4c4","type":"pic_news"},{"news_id":"1745","title":"医疗器械行业迎来新投资热点\u200b，第三方服务加速兴起","pic":"http://oss.zhixiaoren.com/news/2018/09/13/7399880351536801455.png","url":"http://news.zhixiaoren.com/20180912/73360.html","intro":"","replys":"0","show_type":1,"marks":"","marks_style":"c4c4c4","type":"pic_news"},{"news_id":"1744","title":"国务院金融研究员、中国人民银行参事室领导一行莅临宇航人参观调研","pic":"http://oss.zhixiaoren.com/news/2018/09/13/4115670401536801488.png","url":"http://news.zhixiaoren.com/20180912/73356.html","intro":"","replys":"1","show_type":1,"marks":"","marks_style":"c4c4c4","type":"pic_news"}]
     * top_recommend : [{"news_id":"19052","title":"虚假销售比特币挖矿机 90后男子骗了近亿","pic":"http://dingyue.zhixiaoren.com/Uploads/2018/09/12/5b98e1b3d7d81.jpg","url":"http://news.zhixiaoren.com/20180912/73372.html","click_cnt":1512,"intro":"这两年，随着比特币大受关注，比特币挖矿机价格也是水涨船高","replys":0,"show_type":0,"listorder":0,"pics":[],"marks":"今日要闻","marks_style":"0000FF","display":"list_content","type":"subscribe"}]
     * dingyue_list : []
     * news_list : [{"news_id":"2168","title":"关爱留守儿童 真情共筑期望 权健集团\u201c心愿中国\u201d公益行活动走进湖南长沙","pic":"http://oss.zhixiaoren.com/news/2018/09/12/4208512391536720160.jpg","url":"","intro":"2018年9月7日，时值第34个教师节来临之日。以关爱留","replys":"0","listorder":"7","click_cnt":"1060","show_type":1,"pics":[{"url":"http://oss.zhixiaoren.com/news/2018/09/12/19953342251536720180.jpg"},{"url":"http://oss.zhixiaoren.com/news/2018/09/12/14495608081536720175.jpg"},{"url":"http://oss.zhixiaoren.com/news/2018/09/12/16064093501536720175.jpg"}],"image_number":"18","marks":"","marks_style":"c4c4c4","display":"list_photo","type":"pic_news"}]
     */

    private String cid;
    private String page;
    private int page_count;
    private String total_count;
    private int total_page;
    private List<PicListBean> pic_list;
    private List<TopRecommendBean> top_recommend;
    private List<?> dingyue_list;
    private List<NewsListBean> news_list;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public List<PicListBean> getPic_list() {
        return pic_list;
    }

    public void setPic_list(List<PicListBean> pic_list) {
        this.pic_list = pic_list;
    }

    public List<TopRecommendBean> getTop_recommend() {
        return top_recommend;
    }

    public void setTop_recommend(List<TopRecommendBean> top_recommend) {
        this.top_recommend = top_recommend;
    }

    public List<?> getDingyue_list() {
        return dingyue_list;
    }

    public void setDingyue_list(List<?> dingyue_list) {
        this.dingyue_list = dingyue_list;
    }

    public List<NewsListBean> getNews_list() {
        return news_list;
    }

    public void setNews_list(List<NewsListBean> news_list) {
        this.news_list = news_list;
    }

    public static class PicListBean {
        /**
         * news_id : 1746
         * title : 传销式瑞波联储未死，冒牌瑞波币再现圈钱
         * pic : http://oss.zhixiaoren.com/news/2018/09/13/16577309661536801392.jpg
         * url : http://news.zhixiaoren.com/20180912/73384.html
         * intro :
         * replys : 0
         * show_type : 1
         * marks :
         * marks_style : c4c4c4
         * type : pic_news
         */

        private String news_id;
        private String title;
        private String pic;
        private String url;
        private String intro;
        private String replys;
        private int show_type;
        private String marks;
        private String marks_style;
        private String type;

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getReplys() {
            return replys;
        }

        public void setReplys(String replys) {
            this.replys = replys;
        }

        public int getShow_type() {
            return show_type;
        }

        public void setShow_type(int show_type) {
            this.show_type = show_type;
        }

        public String getMarks() {
            return marks;
        }

        public void setMarks(String marks) {
            this.marks = marks;
        }

        public String getMarks_style() {
            return marks_style;
        }

        public void setMarks_style(String marks_style) {
            this.marks_style = marks_style;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class TopRecommendBean {
        /**
         * news_id : 19052
         * title : 虚假销售比特币挖矿机 90后男子骗了近亿
         * pic : http://dingyue.zhixiaoren.com/Uploads/2018/09/12/5b98e1b3d7d81.jpg
         * url : http://news.zhixiaoren.com/20180912/73372.html
         * click_cnt : 1512
         * intro : 这两年，随着比特币大受关注，比特币挖矿机价格也是水涨船高
         * replys : 0
         * show_type : 0
         * listorder : 0
         * pics : []
         * marks : 今日要闻
         * marks_style : 0000FF
         * display : list_content
         * type : subscribe
         */

        private String news_id;
        private String title;
        private String pic;
        private String url;
        private int click_cnt;
        private String intro;
        private int replys;
        private int show_type;
        private int listorder;
        private String marks;
        private String marks_style;
        private String display;
        private String type;
        private List<?> pics;

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getClick_cnt() {
            return click_cnt;
        }

        public void setClick_cnt(int click_cnt) {
            this.click_cnt = click_cnt;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getReplys() {
            return replys;
        }

        public void setReplys(int replys) {
            this.replys = replys;
        }

        public int getShow_type() {
            return show_type;
        }

        public void setShow_type(int show_type) {
            this.show_type = show_type;
        }

        public int getListorder() {
            return listorder;
        }

        public void setListorder(int listorder) {
            this.listorder = listorder;
        }

        public String getMarks() {
            return marks;
        }

        public void setMarks(String marks) {
            this.marks = marks;
        }

        public String getMarks_style() {
            return marks_style;
        }

        public void setMarks_style(String marks_style) {
            this.marks_style = marks_style;
        }

        public String getDisplay() {
            return display;
        }

        public void setDisplay(String display) {
            this.display = display;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<?> getPics() {
            return pics;
        }

        public void setPics(List<?> pics) {
            this.pics = pics;
        }
    }

    public static class NewsListBean {
        /**
         * news_id : 2168
         * title : 关爱留守儿童 真情共筑期望 权健集团“心愿中国”公益行活动走进湖南长沙
         * pic : http://oss.zhixiaoren.com/news/2018/09/12/4208512391536720160.jpg
         * url :
         * intro : 2018年9月7日，时值第34个教师节来临之日。以关爱留
         * replys : 0
         * listorder : 7
         * click_cnt : 1060
         * show_type : 1
         * pics : [{"url":"http://oss.zhixiaoren.com/news/2018/09/12/19953342251536720180.jpg"},{"url":"http://oss.zhixiaoren.com/news/2018/09/12/14495608081536720175.jpg"},{"url":"http://oss.zhixiaoren.com/news/2018/09/12/16064093501536720175.jpg"}]
         * image_number : 18
         * marks :
         * marks_style : c4c4c4
         * display : list_photo
         * type : pic_news
         */

        private String news_id;
        private String title;
        private String pic;
        private String url;
        private String intro;
        private String replys;
        private String listorder;
        private String click_cnt;
        private int show_type;
        private String image_number;
        private String marks;
        private String marks_style;
        private String display;
        private String type;

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getReplys() {
            return replys;
        }

        public void setReplys(String replys) {
            this.replys = replys;
        }

        public String getListorder() {
            return listorder;
        }

        public void setListorder(String listorder) {
            this.listorder = listorder;
        }

        public String getClick_cnt() {
            return click_cnt;
        }

        public void setClick_cnt(String click_cnt) {
            this.click_cnt = click_cnt;
        }

        public int getShow_type() {
            return show_type;
        }

        public void setShow_type(int show_type) {
            this.show_type = show_type;
        }

        public String getImage_number() {
            return image_number;
        }

        public void setImage_number(String image_number) {
            this.image_number = image_number;
        }

        public String getMarks() {
            return marks;
        }

        public void setMarks(String marks) {
            this.marks = marks;
        }

        public String getMarks_style() {
            return marks_style;
        }

        public void setMarks_style(String marks_style) {
            this.marks_style = marks_style;
        }

        public String getDisplay() {
            return display;
        }

        public void setDisplay(String display) {
            this.display = display;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
