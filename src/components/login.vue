<template>
    <div id="login">
        <form>
            用户名：<input type="text" id="name"><br>
            密码：<input type="password" id="password"><br>
            <button @click="btnclick" type="button" id="loginButton">登录</button>
            <button type="button">注册</button>
        </form>
    </div>
</template>

<script>
    export default {
        name: 'login',
        methods:{
            btnclick(){
                console.log('sssssssss')
            }
        }
    }

</script>
