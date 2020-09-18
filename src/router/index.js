import Vue from 'vue'
import VueRouter from 'vue-router'
import login from '../components/login.vue'
import home from '../components/home.vue'

Vue.use(VueRouter)

//安装路由
const routes = [
    {
        path: "/",
        component: home
    },
    {
        path: "/login",
        component: login
    }
]

const router = new VueRouter({
    mode: 'hash',
    routes
})

export default router