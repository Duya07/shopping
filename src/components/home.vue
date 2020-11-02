<template>
    <el-container>
        <el-header><shopHeader></shopHeader></el-header>
        <el-main></el-main>
        <el-footer></el-footer>
    </el-container>
</template>

<script>
    import shopHeader from "@/components/header/shopHeader";

    export default {
        name: "home",
        components: {
            shopHeader
        }
    }
</script>

<style>
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
    }
</style>