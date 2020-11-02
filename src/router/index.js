import Vue from 'vue'
import VueRouter from 'vue-router'
import login from '../components/login.vue'
import home from '../components/home.vue'
import manage from "@/components/manage";
import registered from "@/components/registered";
import success from "@/components/success";

Vue.use(VueRouter)

//安装路由
const routes = [
    {
        path: "/",
        component: home
    },
    {
        path: "/index",
        component: home
    },
    {
        path: "/login",
        component: login
    },
    {
        path: "/manage",
        component: manage,
        beforeEnter: (to, from, next) => {
            let islogin = localStorage.getItem("islogin");
            islogin = Boolean(Number(islogin));

            if(to.path === "/manage" && from.path !== "/manage") {
                if (islogin) {
                    next();
                } else {
                    next("/");
                }
            }
        }
    },
    {
        path: "/registered",
        component: registered
    },
    {
        path: "/success",
        component: success
    }
]

const router = new VueRouter({
    mode: 'hash',
    routes
})

export default router