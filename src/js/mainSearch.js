const LOCAL_STORAGE_KEY = "searchHistory";

class mainSearch { }

//mainSearch.xxx写法可以把xxx实现静态方法的效果，无需创建mainSearch的实例即可使用

//将[]存入localStorage
mainSearch.saveHistory = (arr) => {
    localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(arr));
}

//从localSotrage取出
mainSearch.loadHistory = () =>JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY))

//清空全部localStorage历史查询
mainSearch.removeAllHistory=()=>{localStorage.removeItem(LOCAL_STORAGE_KEY)}

export {
    mainSearch
}