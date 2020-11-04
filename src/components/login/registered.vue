<template>
    <div id="registered">
        <div class="loginBox">
            <h2>注册</h2>
            <div class="item">
                <input type="text" id="name" v-model="username" placeholder="请输入用户名" required>
                <label>用户名</label>
            </div>
            <div class="item">
                <input type="password" id="password" v-model="password" placeholder="请输入密码" required>
                <label>密码</label>
            </div>
            <div class="item">
                <input type="text" id="email" v-model="email" placeholder="请输入邮箱" required>
                <label>邮箱</label>
            </div>
            <div class="item">
                <input type="text" id="mobile" v-model="mobile" placeholder="请输入手机号" required>
                <label>手机</label>
            </div>
            <div id="buttonfa">
                <button @click="registered" type="button">注册
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </button>
                <button @click="loginin" type="button">登录
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "registered",
        data: function () {
            return {
                username: "",
                password: "",
                email: "",
                mobile: ""
            }
        },
        methods: {
            registered() {
                this.$axios.post('/registered', {
                    data: {
                        username: this.username,
                        password: this.password,
                        email: this.email,
                        mobile: this.mobile
                    }
                }).then((response) => {
                    console.log(response.data)
                })
            },
            loginin() {
                this.$router.push("/login")
            }
        }
    }
</script>

<style scoped>
    * {
        background: #333333;
    }

    #registered {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        padding-bottom: 50px;
        box-sizing: border-box;
    }

    .loginBox {
        width: 400px;
        height: 550px;
        background: rgba(0, 0, 0, 0.2);
        box-shadow: 0px 15px 25px 0px rgba(0, 0, 0, 0.5);
        padding: 40px;
        box-sizing: border-box;
    }

    label {
        color: pink;
    }

    h2 {
        background: #292929;
        text-align: center;
        color: pink;
        margin-bottom: 30px;
    }

    .item {
        height: 45px;
        border-bottom: 1px solid #fff;
        margin-bottom: 40px;
        position: relative;
        background: #292929;
    }

    .item input {
        width: 100%;
        height: 100%;
        color: white;
        padding-top: 20px;
        box-sizing: border-box;
    }

    .item input:focus + label, .item input:valid + label {
        top: 0px;
        font-size: 12px;
    }

    .item input:not(:focus)::placeholder {
        opacity: 0;
    }

    .item label {
        position: absolute;
        background: #292929;
        left: 0;
        top: 15px;
        transition: all 0.075s linear;
    }

    button {
        background: transparent;
        padding: 10px 20px;
        overflow: hidden;
        margin-top: 30px;
        color: pink;
        outline: none;
        position: relative;
        border: none;
        letter-spacing: 2px;

    }

    button:hover {
        background: pink;
        border-radius: 5px;
        color: white;
        box-shadow: 0 0 5px 0 pink,
        0 0 25px 0 pink,
        0 0 50px 0 pink,
        0 0 100px 0 pink;
    }

    button > span {
        position: absolute;
    }

    button > span:nth-child(1) {
        left: -100%;
        top: 0;
        width: 100%;
        height: 2px;
        background: -webkit-linear-gradient(left, transparent, pink);
        animation: line1 1s linear infinite;
    }

    @keyframes line1 {

        50%,
        100% {
            left: 100%;
        }
    }

    button > span:nth-child(2) {
        right: 0;
        top: -100%;
        width: 2px;
        height: 100%;
        background: -webkit-linear-gradient(top, transparent, pink);
        animation: line2 1s 0.25s linear infinite;
    }

    @keyframes line2 {

        50%,
        100% {
            top: 100%;
        }
    }

    button > span:nth-child(3) {
        left: 100%;
        bottom: 0;
        width: 100%;
        height: 2px;
        background: -webkit-linear-gradient(left, pink, transparent);
        animation: line3 1s 0.5s linear infinite;
    }

    @keyframes line3 {

        50%,
        100% {
            left: -100%;
        }
    }

    button > span:nth-child(4) {
        left: 0;
        top: 100%;
        width: 2px;
        height: 100%;
        background: -webkit-linear-gradient(top, pink, transparent);
        animation: line4 1s 0.75s linear infinite;
    }

    @keyframes line4 {

        50%,
        100% {
            top: -100%;
        }
    }

    input {
        background: transparent;
        border: none;
        outline: none;
    }

    #buttonfa {
        background: #292929;
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>