import VueRouter from 'vue-router'
import login from '../components/login.vue'
import home from '../components/home.vue'

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
    routes
})

export default router