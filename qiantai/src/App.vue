<template>
    <div>
        <div style="float: right">
            <div style=" float: left; margin-left: 50px; " class="dd">
                <li v-if="!this.$store.getters.getUser" style="float:left">
                    <el-button type="text" @click="login" style="color: #fff;display: inline-block;text-transform: uppercase;text-decoration: none;background: #0099FF;text-align: center;padding: 10px 40px;margin-top:94px;font-weight: bold;">登录</el-button>
                    <el-button type="text" @click="register = true" style="color: #fff;display: inline-block;text-transform: uppercase;text-decoration: none;background: #0099FF;text-align: center;padding: 10px 40px;font-weight: bold;">注册</el-button>
                </li>
                <li v-else>
                    <font color="black"> 欢迎 {{ username }} [{{ cx }}]</font>&nbsp;&nbsp;
                    <el-button type="text" @click="logout" style="color: #fff;display: inline-block;text-transform: uppercase;text-decoration: none;background:#0099FF; text-align: center; padding: 10px 40px;font-weight: bold;margin-top:94px;">退出</el-button>
                    <router-link :to="`/Collect/`" class="el-button el-button--text" style="color: #fff;display: inline-block;text-transform: uppercase;text-decoration: none;background:#0099FF;text-align: center; padding: 10px 40px;font-weight: bold;">我的收藏 </router-link>
                    <router-link to="/order" class="el-button el-button--text" style=" color: #fff;display: inline-block;text-transform: uppercase;text-decoration: none;background: #0099FF;text-align: center;padding: 10px 40px;font-weight: bold;">我的订单</router-link>
                    <router-link to="/shoppingCart" class="el-button el-button--text" style=" color: #fff;display: inline-block; text-transform: uppercase;text-decoration: none; background: #ff6700; text-align: center; padding: 10px 40px;font-weight: bold;">
                        <i class="el-icon-shopping-cart-full"></i> 购物车
                        <span class="num">({{ getNum }})</span>
                    </router-link>
                    <el-popover placement="top" width="180" v-model="visible">
                        <p>确定退出登录吗？</p>
                        <div style="text-align: right; margin: 10px 0 0">
                            <el-button size="mini" type="text" @click="visible = false">取消</el-button>
                            <el-button type="primary" size="mini" @click="logout">确定</el-button>
                        </div>
                    </el-popover>
                </li>
            </div>
        </div>
        <!-- 导航栏 start -->
        <div class="nav" style="width: 100%; height: 60px; background: white; color: gray; margin: 0 auto; border: white;">
            <div class="header-menu" style="margin: 0 auto;">
                <div><img src="./assets/imgs/hanfu_logo.png" alt="汉服 Logo" style="float: left;height: 50px;"/></div>
                <div style="width: 1160px; height: 100%; margin: 0 auto;">
                    <ul id="" class="menu">
                        <li class="menu-item" :class="{active: activeMenu === 'home'}">
                            <a href="/" @click="setActiveMenu('home')">首页</a>
                        </li>
                        <li class="menu-item" :class="{active: activeMenu === 'aboutHanfu'}">
                            <a href="#/HanfuKnowledge" style="cursor:pointer;" @click="setActiveMenu('aboutHanfu')">汉服知识</a>
                            <ul class="sub-menu">
                                <li class="menu-item"><router-link :to="`/HanfuKnowledge/`" @click="setActiveMenu('aboutHanfu')">关于汉服</router-link></li>
                                <li class="menu-item"><router-link :to="`/kepuxinxilisttp/`" @click="setActiveMenu('aboutHanfu')">科普信息</router-link></li>
                                <li class="menu-item"><router-link :to="`/HistoricalDevelopment/`" @click="setActiveMenu('aboutHanfu')">历史发展</router-link></li>
                            </ul>
                        </li>
                        <li class="menu-item" :class="{active: activeMenu === 'hanfuTreasure'}">
                            <a href="#/ArtPhotography" style="cursor:pointer" @click="setActiveMenu('hanfuTreasure')">汉服瑰宝</a>
                            <ul class="sub-menu">
                                <li class="menu-item"><router-link :to="`/ArtPhotography/`" @click="setActiveMenu('hanfuTreasure')">汉服图鉴</router-link></li>
                                <li class="menu-item"><router-link :to="`/xinwentongzhi/站内书籍推荐/`" @click="setActiveMenu('hanfuTreasure')">相关书籍</router-link></li>
                                <li class="menu-item"><router-link :to="`/shangpinlisttp/`" @click="setActiveMenu('hanfuTreasure')">汉服商城</router-link></li>
                            </ul>
                        </li>
                        <li class="menu-item" :class="{active: activeMenu === 'forum'}">
                            <a href="#/HanFuNews" style="cursor:pointer" @click="setActiveMenu('forum')">论坛交流</a>
                            <ul class="sub-menu">
                                <li class="menu-item"><router-link :to="`/HanFuNews/`" @click="setActiveMenu('forum')">汉服新闻</router-link></li>
                                <li class="menu-item"><router-link :to="`/hanfuhuodonglisttp/`" @click="setActiveMenu('forum')">汉服展览</router-link></li>
                                <li class="menu-item"><router-link :to="`/bbs/`" @click="setActiveMenu('forum')">同袍社区</router-link></li>
                            </ul>
                        </li>
                        <li class="menu-item" :class="{active: activeMenu === 'hanfuPuzzle'}">
                            <a href="/puzzle_easy.html" style="cursor:pointer" @click="setActiveMenu('hanfuPuzzle')">汉韵拼图</a>
                            <ul class="sub-menu">
                                <li class="menu-item">
                                    <a href="/puzzle_easy.html" @click="setActiveMenu('hanfuPuzzle')">简易模式</a>
                                </li>
                                <li class="menu-item">
                                    <a href="/puzzle_normal.html" @click="setActiveMenu('hanfuPuzzle')">一般模式</a>
                                </li>
                                <li class="menu-item">
                                    <a href="/puzzle_hard.html" @click="setActiveMenu('hanfuPuzzle')">困难模式</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- 导航栏 end -->
        <div>
            <div style="height: 800px;">
                <el-carousel class="lunBoTUList" >
                    <el-carousel-item v-for="item in carouselItems" :key="item.id" style="height:900px;" >
                        <img :src="'/api/'+item.tupian" style="height: 100%; width: 100%"/>
                    </el-carousel-item>
                </el-carousel>
                <div>
                    <span class="text_logo">匹罗衣之璀璨兮   珥碧瑶之华琚</span><br>
                    <span class="text_width">汉民族传统服饰</span><br>
                    <span class="text_width" style="left: 1140px;">—— 汉服</span>
                    <div class="detail_border"><a href="#/HanfuKnowledge" style="color: white;font-family: monospace;">点击了解</a></div>
                </div>
            </div>
        </div>
        <!-- </div> -->
        <!-- 登录模块 -->
        <MyLogin></MyLogin>
        <!-- 注册模块 -->
        <MyRegister :register="register" @fromChild="isRegister"></MyRegister>
        <!-- 主要区域容器 -->
        <el-main>
            <keep-alive>
                <router-view></router-view>
            </keep-alive>
        </el-main>
        <div class="bottom" style ="background: #3366CC;;">
            <div class="bb" style="width: 1000px;">
                <ul>
                    <li><a href="/">首页</a></li>
                    <li><span></span><a href="#/HanfuKnowledge">汉服知识</a></li>
                    <li><span></span><a href="#/ArtPhotography">汉服瑰宝</a></li>
                    <li><span></span><a href="#/HanFuNews">论坛交流</a></li>
                    <li><span></span><a href="/puzzle_normal.html">汉韵拼图</a></li>
                    <li><span></span><a href="/">联系我们</a></li>
                </ul>
            </div>
            <p>@ 汉服文化传播平台 版权所有 xxICP备xxxx号</p>
            <div class="back-to-top" @click="scrollToTop">
                <img style="width: 30px; height: 30px;" src="../src/assets/imgs/top.png" alt="Back to Top"/>
            </div>
            <!-- <el-button size="mini" icon="el-icon-top" style="float: right;margin-top: -50px;"></el-button> -->
        </div>
    </div>
</template>

<script>
import {mapActions} from "vuex";
import {mapGetters} from "vuex";

export default {
  beforeUpdate() {
    this.activeIndex = this.$route.path;
  },
  data() {
    return {
      carouselItems: [], // 存储轮播图数据
      activeIndex: "", // 头部导航栏选中的标签
      search: "", // 搜索条件
      register: false, // 是否显示注册组件
      visible: false, // 是否退出登录
      username: '',
      cx: '',
      activeMenu: 'home'  // 用来存储当前活动的菜单
    };
  },
  created() {
    // 获取浏览器localStorage，判断用户是否已经登录
    if (localStorage.getItem("user")) {
      // 如果已经登录，设置vuex登录状态
      this.setUser(JSON.parse(localStorage.getItem("user")));
      this.username = localStorage.getItem("username");
      this.cx = localStorage.getItem("cx");
    }
    this.fetchCarouselData();
    console.log(this.$store.getters.getUser)
  },
  computed: {
    ...mapGetters(["getUser", "getNum"])
  },
  watch: {},
  methods: {
    fetchCarouselData() {
      this.$axios
          .get("/api/front/bianhuantu", {})
          .then(res => {
            let data = res.data.data;
            let filteredData = [];
            data.forEach(item => {
              filteredData.push(item);
            });
            this.carouselItems = filteredData;
          })
          .catch(err => {
            return Promise.reject(err);
          });
    },
    ...mapActions(["setUser", "setShowLogin", "setShoppingCart"]),
    login() {
      // 点击登录按钮, 通过更改vuex的showLogin值显示登录组件
      this.setShowLogin(true);
    },
    hsghoutai() {
      const w = window.open("about:blank");
      w.location.href = 'http://localhost:9999/#/';
    },
    // 退出登录
    logout() {
    //   this.visible = true;
    this.$confirm('确定退出登录吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '已退出登录!'
          });
            // 清空本地登录信息
            localStorage.setItem("user", "");
            // 清空vuex登录信息
            this.setUser("");
            // this.$message.success("成功退出登录");
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消退出登录'
          });          
        });
    },
    // 接收注册子组件传过来的数据
    isRegister(val) {
      this.register = val;
    },
    scrollToTop() {
      window.scrollTo({
        top: 0,          // 滚动到页面顶部
        behavior: 'smooth'  // 平滑滚动
      });
    },
    setActiveMenu(menuName) {
      this.activeMenu = menuName;  // 更新当前活动的菜单项
    },
  },
  mounted() {
    // 初次加载时定位到首页
    this.setActiveMenu('home');
  }
};
</script>

<style>
@import "assets/css/index2.css";
@import "assets/css/responsive.css";
@import "assets/css/index.css";
@import "assets/css/base.css";
/* 全局CSS */
* {
  padding: 0;
  margin: 0;
  list-style: none;
  font-size: 16px;
}

.newsline TD {
  LINE-HEIGHT: 25px;
  BORDER-BOTTOM: #c9c9c9 1px dashed;
  text-align: left;
}

.newsline TD A:link {
  COLOR: #000
}

.newsline TD A:visited {
  COLOR: #000
}

.newsline TD A:hover {
  COLOR: #000
}

.newsline TD A:active {
  COLOR: #000
}

#app .el-header {
  padding: 0;
}

#app .el-main {
  min-height: 300px;
  padding: 20px 0;
}

#app .el-footer {
  padding: 0;
}

a,
a:hover {
  text-decoration: none;
}

/* 全局CSS END */

/* 顶部导航栏CSS */
.topbar {
  height: 40px;
  background-color: #3d3d3d;
  margin-bottom: 20px;
}

.topbar .nav {
  width: 1225px;
  margin: 0 auto;
}

.topbar .nav ul {
  float: right;
}

.topbar .nav li {
  float: left;
  height: 40px;
  color: #b0b0b0;
  font-size: 14px;
  text-align: center;
  line-height: 40px;
  margin-left: 20px;
}

.topbar .nav .sep {
  color: #b0b0b0;
  font-size: 12px;
  margin: 0 5px;
}

.topbar .nav li .el-button {
  color: #b0b0b0;
}

.topbar .nav .el-button:hover {
  color: #fff;
}

.topbar .nav li a {
  color: #b0b0b0;
}

.topbar .nav a:hover {
  color: #fff;
}


.topbar .nav .shopCart {
  width: 120px;
  background: #424242;
}

.topbar .nav .shopCart:hover {
  background: #fff;
}

.topbar .nav .shopCart:hover a {
  color: #ff6700;
}

.topbar .nav .shopCart-full {
  width: 120px;
  background: #ff6700;
}

.topbar .nav .shopCart-full a {
  color: white;
}

/* 顶部导航栏CSS END */

/* 顶栏容器CSS */
.el-header .el-menu {
  max-width: 1225px;
  margin: 0 auto;
  background-color: transparent;
}
.el-header .so {
  /*margin-top: 10px;*/
  width: 300px;
  float: right;
}

/* 顶栏容器CSS END */
.header {
  /*padding: 1em 0;*/
}

.w3layouts_logo {
  float: left;
}

.w3layouts_logo a {
  display: block;
  text-decoration: none;
}

h1, h2, h3, h4, h5, h6, a {
  font-family: 'Roboto Condensed', sans-serif;
  margin: 0;
}

.header-menu .menu a {
  font-size: 1.3em;
  /* font-weight:bold; */
  display: block;
  padding: 8px 20px;
  text-transform: uppercase;
  color: black;
}

.w3layouts_logo a h1 {
  padding-left: 20px;
  padding-top: 20px;
  font-size: 2.8em;
  color: #212121;
  text-transform: uppercase;

  padding-right: 1.67em;
  font-weight: 900;
  padding-bottom: 0.3em;
}

.w3layouts_logo a h1 span {

  color: #FF8D1B;
  font-size: .3em;
  display: block;
  font-weight: 300;
  letter-spacing: 5px;
  text-align: right;
  line-height: 0;
}

.w3_search input[type="text"] {
  outline: none;
  border: none;
  background: #EFEFEF;
  padding: 11px;
  text-align: center;
  font-size: 14px;
  color: #999;
  width: 71%;
  float: left;
  border-top-left-radius: 25px;
  border-bottom-left-radius: 25px;
}

.w3_search input[type="submit"] {
  outline: none;
  border: none;
  background: #212121;
  padding: 10px 0;
  font-size: 14px;
  color: #fff;
  width: 22%;
  border-top-right-radius: 25px;
  border-bottom-right-radius: 25px;
}

.w3l_sign_in_register {
  float: right;
  margin-top: 1em;
  width: 35%;
}

.w3l_sign_in_register ul li a {
  color: #fff;
  display: inline-block;
  text-transform: uppercase;
  text-decoration: none;
  background: #FF8D1B;
  text-align: center;
  padding: 6px 30px;
  font-weight: bold;
}

.fa-phone:before {
  content: "\f095";
}

.navbar-nav > li {
  float: left;
  border-right: 1px dotted #fff;
  width: 9%;
  text-align: center;
}

.navbar-default .navbar-nav > li > a {
  color: #fff;
  text-transform: uppercase;
}

.navbar-default .navbar-nav > li > a {
  color: #777;
}

.navbar-default .navbar-collapse, .navbar-default .navbar-form {
  border-color: #e7e7e7;
}

.navbar-nav > li > a {
  padding-top: 10px;
  padding-bottom: 10px;
  line-height: 20px;
}

.w1200 {
  overflow: visible;
  margin: 0 auto;
  width: 1200px;
  height: auto;
/* overflow: hidden; */
}

.nav > li {
  position: relative;
  display: block;
}

p, ul li, ol li {
  margin: 0;
  font-size: 14px;
}

article, aside, details, figcaption, figure, footer, header, hgroup, main, menu, nav, section, summary {
  display: block;
}
.w3l_sign_in_register ul li a {
  color: #fff;
  display: inline-block;
  text-transform: uppercase;
  text-decoration: none;
  background: #FF8D1B;
  text-align: center;
  padding: 6px 30px;
  font-weight: bold;
}

.owl-carousel {
  display: none;
  position: relative;
  width: 100%;
  -ms-touch-action: pan-y;
  background: #fff;
}

.img-responsive, .thumbnail > img, .thumbnail a > img, .carousel-inner > .item > img, .carousel-inner > .item > a > img {
  display: block;
  max-width: 100%;
  height: auto;
}

.w3l-movie-gride-agile {
  text-align: center;
  box-shadow: 0px 0px 10px rgba(255, 255, 255, 0.35);
  margin-right: 0%;
  margin-bottom: 3%;
  position: relative;
  padding-left: 0;
}


.hvr-shutter-out-horizontal {
  display: inline-block;
  vertical-align: middle;
  -webkit-transform: translateZ(0);
  transform: translateZ(0);
  box-shadow: 0 0 1px rgba(0, 0, 0, 0);
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  -moz-osx-font-smoothing: grayscale;
  position: relative;
  background: #e1e1e1;
  -webkit-transition-property: color;
  transition-property: color;
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
}

.banner_1 {
  width: 100%;
  height: 100%;
/* position: absolute; left: 0; top: 0; */
}
.lunBoTUList{
    z-index: -1;
    height: 800px;
    width: 100%;
}
.el-carousel--horizontal{
    overflow-x: inherit;
}
.menu-item-logo{
    width: 200px;
    height: 50px;
    /* margin-right: 50px;
    float: left; */
}
.text_logo{
    position: relative;
    bottom: 420px;
    left: 1000px;
    font-size: 28px;
    color: white;
    font-family: cursive;
    letter-spacing: 2px;
}
.text_width{
    position: relative;
    bottom: 400px;
    left: 1000px;
    font-size: 55px;
    font-weight: bolder;
    color: white;
    font-family: cursive;
    letter-spacing: 3px;
}
.detail_border{
    border: 1px solid white;
    width: 100px;
    height: 30px;
    line-height: 30px;
    position: relative;
    bottom: 380px;
    left: 1000px;
    color: white;
    text-align: center;
    font-family: cursive;
    font-size: 18px;

}
.back-to-top {
  position: fixed;
  right: 20px;
  bottom: 20px;
  cursor: pointer;
  z-index: 1000; /* 保证按钮在页面顶部显示 */
}

.back-to-top img {
  width: 40px; /* 可以根据需求调整按钮大小 */
  height: 40px;
}
/* 默认的菜单样式 */
.menu-item a {
  padding: 10px 15px;
  display: block;
  text-decoration: none;
  /* background-color: #333; */
  color:white;
}

/* 激活菜单项的样式 */
.menu-item.active > a {
  background-color: #dfdfdf;  /* 背景色 */
  color: #333;  /* 文字颜色 */
}

/* Hover效果 */
.menu-item > a:hover {
  background-color: #f0f0f0;
  color: #333;
}
</style>
