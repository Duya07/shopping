<template>
    <div id="registered">
        <form>
            用户名：<input type="text" id="name" v-model="username" placeholder="请输入用户名"><br>
            密码：<input type="password" id="password" v-model="password" placeholder="请输入密码"><br>
            邮箱：<input type="text" id="email" v-model="email" placeholder="请输入邮箱"><br>
            手机：<input type="text" id="mobile" v-model="mobile" placeholder="请输入手机号"><br>
            <button @click="registered" type="button">注册</button>
        </form>
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
                this.$axios.get('/api/registered', {
                    params: {
                        username: this.username,
                        password: this.password,
                        email: this.email,
                        mobile: this.mobile
                    }
                }).then((response) => {
                    console.log(response.data)
                })
            },
        }
    }
</script>

<style scoped>

</style>