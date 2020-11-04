<template>
    <div id="searchBox">
        <el-row>
            <el-col :span="15" class="center">
                <el-input
                        v-model="search"
                        @focus="focus"
                        @blur="blur"
                        @keyup.enter.native="searchHandler"
                        placeholder="搜索商家或商品"
                >
                    <el-button slot="append" icon="el-icon-search" id="search" @click="searchHandler"></el-button>
                </el-input>
                <!---设置z-index优先级,防止被走马灯效果遮挡-->
                <el-card
                        @mouseenter="enterSearchBoxHanlder"
                        v-if="isSearch"
                        class="box-card"
                        id="search-box"
                        style="position:absolute;z-index:15"
                >
                    <dl v-if="isHistorySearch">
                        <dt class="search-title" v-show="history">历史搜索</dt>
                        <dt class="remove-history" v-show="history" @click="removeAllHistory">
                            <i class="el-icon-delete"></i>清空历史记录
                        </dt>
                        <el-tag
                                v-for="search in historySearchList"
                                :key="search.id"
                                closable
                                :type="search.type"
                                @close="closeHandler(search)"
                                style="margin-right:5px; margin-bottom:5px;"
                        >{{search.name}}</el-tag>
                        <dt class="search-title">热门搜索</dt>
                        <dd v-for="search in hotSearchList" :key="search.id">{{search}}</dd>
                    </dl>
                    <dl v-if="isSearchList">
                        <dd v-for="search in searchList" :key="search.id">{{search}}</dd>
                    </dl>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import { mainSearch } from '../../js/mainSearch'
    import { randomUtil } from '../../js/randomUtil'

    export default {
        name: "searchBox",
        data() {
            return {
                search: "", //当前输入框的值
                isFocus: false, //是否聚焦
                hotSearchList: ["暂无热门搜索"], //热门搜索数据
                historySearchList: [], //历史搜索数据
                searchList: ["暂无数据"], //搜索返回数据,
                history: false,
                types: ["", "success", "info", "warning", "danger"] //搜索历史tag式样
            };
        },
        methods: {
            focus() {
                this.isFocus = true;
                this.historySearchList =
                    mainSearch.loadHistory() == null ? [] : mainSearch.loadHistory();
                this.history = this.historySearchList.length == 0 ? false : true;
            },
            blur() {
                var self = this;
                this.searchBoxTimeout = setTimeout(function() {
                    self.isFocus = false;
                }, 300);
            },
            enterSearchBoxHanlder() {
                clearTimeout(this.searchBoxTimeout);
            },
            searchHandler() {
                //随机生成搜索历史tag式样
                let n = randomUtil.getRandomNumber(0, 5);
                let exist =
                    this.historySearchList.filter(value => {
                        return value.name === this.search;
                    }).length !== 0;
                if (!exist) {
                    this.historySearchList.push({ name: this.search, type: this.types[n] });
                    mainSearch.saveHistory(this.historySearchList);
                }
                this.history = this.historySearchList.length == 0 ? false : true;
            },
            closeHandler(search) {
                this.historySearchList.splice(this.historySearchList.indexOf(search), 1);
                mainSearch.saveHistory(this.historySearchList);
                clearTimeout(this.searchBoxTimeout);
                if (this.historySearchList.length == 0) {
                    this.history = false;
                }
            },
            removeAllHistory() {
                mainSearch.removeAllHistory();
            }
        },
        computed: {
            isHistorySearch() {
                return this.isFocus && !this.search;
            },
            isSearchList() {
                return this.isFocus && this.search;
            },
            isSearch() {
                return this.isFocus;
            }
        }

    }
</script>

<style scoped>
    .left {
        margin-left: 200px;
    }
    .center {
        margin-top: 15px;
        margin-left: 200px;
    }
    #search {
        background-color: #ffc300;
        border-radius: 0%;
    }
    .search-title {
        color: #bdbaba;
        font-size: 15px;
        margin-bottom: 5px;
    }
    .remove-history {
        color: #bdbaba;
        font-size: 15px;
        float: right;
        margin-top: -22px;
    }
    #search-box {
        width: 555px;
        height: 300px;
        margin-top: 0px;
        padding-bottom: 20px;
    }
</style>