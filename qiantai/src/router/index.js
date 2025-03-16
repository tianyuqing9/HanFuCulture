/*
 * @Description: 路由配置
 * @Author: hai-27
 * @Date: 2020-02-07 16:23:00
 * @LastEditors: hai-27
 * @LastEditTime: 2020-02-27 13:58:48
 */
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routes = [
    // 首页
    {
        path: '/',
        name: 'Home',
        component: () => import('../views/Home.vue')
    },
    {
        path: '/collect',
        name: 'Collect',
        component: () => import('../views/Collect.vue'),
        meta: {
            requireAuth: true // 需要验证登录状态
        }
    },
    {
        path: '/error',
        name: 'Error',
        component: () => import('../components/Error.vue')
    },
    {
        path: '/xinwentongzhidetail/:id',
        name: 'xinwentongzhidetail',
        component: () => import('../views/xinwentongzhidetail.vue')
    },
    // 艺术摄影
    {
        path: '/ArtPhotography/',
        name: 'ArtPhotography',
        component: () => import('../views/ArtPhotography.vue')
    },
    // 相关书籍
    {
        path: '/xinwentongzhi/:lb',
        name: 'xinwentongzhi',
        component: () => import('../views/xinwentongzhi.vue')
    },
    // 汉服商城
    {
        path: '/shangpinlisttp',
        name: 'shangpinlisttp',
        component: () => import('../views/shangpinlisttp.vue')
    },
    {
        path: '/hanfuhuodongdetail/:id',
        name: 'hanfuhuodongdetail',
        component: () => import('../views/hanfuhuodongdetail.vue')
    },
    {
        path: '/shangpindetail/:id',
        name: 'shangpindetail',
        component: () => import('../views/shangpindetail.vue')
    },
    
    {
        path: '/yonghudetail/:id',
        name: 'yonghudetail',
        component: () => import('../views/yonghudetail.vue')
    },
    {
        path: '/yonghulisttp',
        name: 'yonghulisttp',
        component: () => import('../views/yonghulisttp.vue')
    },
    {
        path: '/kepuxinxidetail/:id',
        name: 'kepuxinxidetail',
        component: () => import('../views/kepuxinxidetail.vue')
    },
    // 汉服知识
    {
        path: '/HanfuKnowledge',
        name: 'HanfuKnowledge',
        component: () => import('../views/HanfuKnowledge.vue')
    },
    // 科普信息
    {
        path: '/kepuxinxilisttp',
        name: 'kepuxinxilisttp',
        component: () => import('../views/kepuxinxilisttp.vue')
    },
    
    // 历史发展
    {
        path: '/HistoricalDevelopment',
        name: 'HistoricalDevelopment',
        component: () => import('../views/HistoricalDevelopment.vue')
    },
    //汉服新闻
    {
        path: '/HanFuNews',
        name: 'HanFuNews',
        component: () => import('../views/HanFuNews.vue')
    },
    // 汉服新闻详情
    {
        path: '/HanFuNewsDetail/:id',
        name: 'HanFuNewsDetail',
        component: () => import('../views/HanFuNewsDetail.vue'),
        props: true  // 允许将路由参数作为 props 传递给组件
    },
    // 汉服展览
    {
        path: '/hanfuhuodonglisttp',
        name: 'hanfuhuodonglisttp',
        component: () => import('../views/hanfuhuodonglisttp.vue')
    },
    // 同袍社区
    {
        path: '/bbs',
        name: 'bbs',
        component: () => import('../views/bbs.vue')
    },
    // 汉韵拼图
    {
        path: '/HanfuPuzzle',
        name: 'HanfuPuzzle',
        component: () => import('../views/HanfuPuzzle.vue')
    },
    // {
    //     path: '/hanfuPuzzle',
    //     name: 'hanfuPuzzle',
    //     component: () => import('../views/hanfuPuzzle.html')
    // },
    {
        path: '/liuyan',
        name: 'Liuyan',
        component: () => import('../views/Liuyan.vue')
    },
    {
        path: '/order',
        name: 'Order',
        component: () => import('../views/Order.vue'),
        meta: {
            requireAuth: true // 需要验证登录状态
        }
    },
    {
        path: '/shoppingCart',
        name: 'ShoppingCart',
        component: () => import('../views/ShoppingCart.vue'),
        meta: {
            requireAuth: true // 需要验证登录状态
        }
    },
    {
        path: '/confirmOrder',
        name: 'ConfirmOrder',
        component: () => import('../views/ConfirmOrder.vue'),
        meta: {
            requireAuth: true // 需要验证登录状态
        }
    },
    
    {
        path: '/tiezidetail/:id',
        name: 'tiezidetail',
        component: () => import('../views/tiezidetail.vue')
    },
    {
        path: '/tiezi/:id',
        name: 'tiezi',
        component: () => import('../views/tiezi.vue')
    },
    
]

const router = new Router({
    // base: '/dist',
    // mode: 'history',
    routes
})

/* 由于Vue-router在3.1之后把$router.push()方法改为了Promise。所以假如没有回调函数，错误信息就会交给全局的路由错误处理。
vue-router先报了一个Uncaught(in promise)的错误(因为push没加回调) ，然后再点击路由的时候才会触发NavigationDuplicated的错误(路由出现的错误，全局错误处理打印了出来)。*/
// 禁止全局路由错误处理打印
const originalPush = Router.prototype.push;
Router.prototype.push = function push(location, onResolve, onReject) {
    if (onResolve || onReject)
        return originalPush.call(this, location, onResolve, onReject)
    return originalPush.call(this, location).catch(err => err)
}

export default router
