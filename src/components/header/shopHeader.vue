<template>
    <div>
        <el-menu mode="horizontal" @select="handleSelect">
            <el-menu-item index="1">首页</el-menu-item>
            <el-submenu index="2">
                <template slot="title">分类</template>
                <el-menu-item v-for="(item,i) in result" :key="item" :index="i.toString()">{{ item }}</el-menu-item>
            </el-submenu>
        </el-menu>
    </div>


</template>

<script>
    export default {
        name: "shopHeader",
        data() {
            return {
                result: []
            }
        },
        methods: {
            handleSelect(key) {
                console.log(key);
            },
        },
        created: function () {
            let _this = this;
            _this.$nextTick(function () {
                this.$axios.post('/menu').then((response) => {
                    let data = response.data;
                    let length = data.length;
                    for (let i = 0; i < length; i += 3) {
                        let a = data.slice(i, i+3)
                        this.result.push(a[0] + " / " + a[1] + " / " + a[2])
                    }
                })
            });
        }
    }
</script>

<style>

</style>