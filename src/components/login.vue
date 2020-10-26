<template>
    <div id="login">
        <form>
            用户名：<input type="text" id="name" v-model="username" placeholder="请输入账号或邮箱"><br>
            密码：<input type="password" id="password" v-model="password" placeholder="请输入密码"><br>
            <input type="submit" @click="loginin" value="登录">
            <button @click="registered" type="button">注册</button>
        </form>
    </div>
</template>

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
                this.$axios.post('/api/login', {
                    data: {
                        username: this.username,
                        password: this.password
                    }
                }).then((response) => {
                    console.log("response",response);
                    if(response.status === 401){
                        alert("无权限");
                    }else if (response.status === 403){
                        alert("禁止");
                    }else if (response.status === 200){
                        this.$router.push("manage")
                    }
                })
            },
            registered(){
                this.$router.push("registered")
            }
        }
    }

</script>
