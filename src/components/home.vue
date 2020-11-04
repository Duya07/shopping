<template>
    <el-container>
        <el-header><shopHeader></shopHeader></el-header>
        <div id="b">
            <el-main><shopMain></shopMain></el-main>
        </div>
        <el-footer></el-footer>
    </el-container>
</template>

<script>
    import shopHeader from "@/components/header/shopHeader";
    import shopMain from "@/components/main/shopMain";

    export default {
        name: "home",
        components: {
            shopHeader,
            shopMain
        }
    }
</script>

<style scoped>
    #b {
        background: #333333;
    }

    .el-header, .el-footer {
        /*background-color: #B3C0D1;*/
        /*color: #333;*/
        text-align: center;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
    }

    .el-main {
        width: 70%;
        background-color: #E9EEF3;
        color: #333;
        margin: auto;
    }
</style>