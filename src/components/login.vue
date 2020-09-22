<template>
    <div id="login">
        <form>
            用户名：<input type="text" id="name" v-model="username" placeholder="请输入账号或邮箱"><br>
            密码：<input type="password" id="password" v-model="password" placeholder="请输入密码"><br>
            <button @click="btnclick" type="button" id="loginButton">登录</button>
            <button type="button">注册</button>
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
            btnclick() {
                this.$axios.get('/api/hello', {
                    params: {
                        username: this.username,
                        password: this.password
                    }
                }).then((response) => {
                    var data = response.data;
                    console.log(data);
                })
            }
        }
    }

</script>
