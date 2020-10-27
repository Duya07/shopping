<template>
    <div id="login">
        用户名：<input type="text" id="name" v-model="username" placeholder="请输入账号或邮箱"><br>
        密码：<input type="password" id="password" v-model="password" placeholder="请输入密码"><br>
        <input type="submit" @click="loginin" value="登录">
        <button @click="registered" type="button">注册</button>
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
                var jsonStr = {username: this.username, password: this.password};
                var json = JSON.stringify(jsonStr)

                this.$axios.post('/login', {
                    data: {
                        json
                    }
                    }).then((response) => {
                        if (response.data.codeMessage.code === "00000000"){
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
