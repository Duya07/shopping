module.exports = {
    configureWebpack: {
        devServer: {
            port: 8081,
            proxy: {
                '/api': {
                    target: 'http://localhost:8080/',
                    changeOrigin: true, //是否跨域
                    pathRewrite: {
                        '/api': '' //规定请求地址以什么作为开头
                    }
                }
            }
        }
    }
}