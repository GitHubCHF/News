package test.message.com.messagetestdemo.bean;

public class LoginBean {
    /**
     * session_id : 2ncovisen9ejl5gsco9didd293
     * status : success
     * msg : 登录成功!
     * user_id : 943689
     * username : w150514300
     * truename : 光年
     * province : 上海
     * city : 上海
     * vip_level : 0
     * is_mysite : 0
     * is_from_login : 1
     * is_blog : 0
     * bbs_intro :
     * is_expire : 0
     * icon :
     * job_corp :
     * gold_member : 0
     * homesite : http://i.zhixiaoren.com/huike/943689/
     * is_vip : 0
     * zhaoshangurl :
     * xingxiaourl :
     * wmpurl :
     * tokey : 4013Zyaywcby7QuPy066w2f9eRSt0443FEW0FZ7tayK%2FtUv1sZA%2F
     * score_status : {"status":"success","msg":"每日登陆：增加10.0积分"}
     */

    private String session_id;
    private String status;
    private String msg;
    private String user_id;
    private String username;
    private String truename;
    private String province;
    private String city;
    private int vip_level;
    private int is_mysite;
    private int is_from_login;
    private int is_blog;
    private String bbs_intro;
    private int is_expire;
    private String icon;
    private String job_corp;
    private int gold_member;
    private String homesite;
    private String is_vip;
    private String zhaoshangurl;
    private String xingxiaourl;
    private String wmpurl;
    private String tokey;
    private ScoreStatusBean score_status;

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getVip_level() {
        return vip_level;
    }

    public void setVip_level(int vip_level) {
        this.vip_level = vip_level;
    }

    public int getIs_mysite() {
        return is_mysite;
    }

    public void setIs_mysite(int is_mysite) {
        this.is_mysite = is_mysite;
    }

    public int getIs_from_login() {
        return is_from_login;
    }

    public void setIs_from_login(int is_from_login) {
        this.is_from_login = is_from_login;
    }

    public int getIs_blog() {
        return is_blog;
    }

    public void setIs_blog(int is_blog) {
        this.is_blog = is_blog;
    }

    public String getBbs_intro() {
        return bbs_intro;
    }

    public void setBbs_intro(String bbs_intro) {
        this.bbs_intro = bbs_intro;
    }

    public int getIs_expire() {
        return is_expire;
    }

    public void setIs_expire(int is_expire) {
        this.is_expire = is_expire;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getJob_corp() {
        return job_corp;
    }

    public void setJob_corp(String job_corp) {
        this.job_corp = job_corp;
    }

    public int getGold_member() {
        return gold_member;
    }

    public void setGold_member(int gold_member) {
        this.gold_member = gold_member;
    }

    public String getHomesite() {
        return homesite;
    }

    public void setHomesite(String homesite) {
        this.homesite = homesite;
    }

    public String getIs_vip() {
        return is_vip;
    }

    public void setIs_vip(String is_vip) {
        this.is_vip = is_vip;
    }

    public String getZhaoshangurl() {
        return zhaoshangurl;
    }

    public void setZhaoshangurl(String zhaoshangurl) {
        this.zhaoshangurl = zhaoshangurl;
    }

    public String getXingxiaourl() {
        return xingxiaourl;
    }

    public void setXingxiaourl(String xingxiaourl) {
        this.xingxiaourl = xingxiaourl;
    }

    public String getWmpurl() {
        return wmpurl;
    }

    public void setWmpurl(String wmpurl) {
        this.wmpurl = wmpurl;
    }

    public String getTokey() {
        return tokey;
    }

    public void setTokey(String tokey) {
        this.tokey = tokey;
    }

    public ScoreStatusBean getScore_status() {
        return score_status;
    }

    public void setScore_status(ScoreStatusBean score_status) {
        this.score_status = score_status;
    }

    public static class ScoreStatusBean {
        /**
         * status : success
         * msg : 每日登陆：增加10.0积分
         */

        private String status;
        private String msg;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
