<template>
    <div id="login">
        用户名：<input type="text" id="name" v-model="username" placeholder="请输入账号或邮箱"><br>
        密码：<input type="password" id="password" v-model="password" placeholder="请输入密码"><br>
        <input type="submit" @click="loginin" value="登录">
        <button @click="registered" type="button">注册</button>
        <button @click="logout" type="button">登出</button>
    </div>
</template>

/*
localStorage.setItem("islogin", 1);
islogin：是否登录成功
*/
<script>
    export default {
        name: 'login',
        data: function(){
            return{
                username: "",
                password: ""
            }
        },
        methods: {
            loginin() {
                let jsonStr = {username: this.username, password: this.password};
                let json = JSON.stringify(jsonStr)

                this.$axios.post('/login', {
                    data: {
                        json
                    }
                    }).then((response) => {
                        if (response.data.codeMessage.code === "00000000"){
                            localStorage.setItem("islogin", 1);
                            this.$router.push("/success")
                        }else {
                            alert("用户名或密码错误")
                        }
                })
            },
            registered(){
                this.$router.push("/registered")
            },
            logout(){
                this.$axios.post('/logout').then((response) => {
                    if (response.data.codeMessage.code === "00000000") {
                        localStorage.setItem("islogin", 0);
                        this.$router.push("/")
                    }
                })
            }
        }
    }

</script>
