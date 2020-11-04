<template>
    <div id="main">
        <search-box></search-box>
        <el-container>
            <el-aside><category></category></el-aside>
            <el-main>Main</el-main>
        </el-container>
    </div>
</template>

<script>
    import searchBox from "@/components/main/searchBox";
    import category from "@/components/main/category";

    export default {
        name: "shopMain",
        components: {
            searchBox,
            category
        }
    }
</script>

<style scoped>
    #main {
        height: 100vh;
    }
    .el-container {
        margin: 5px;
        height: 400px;
    }
</style>