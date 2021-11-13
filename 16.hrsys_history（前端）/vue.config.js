/*
*Vue-CLI项目的核心配置文件
*/
const webpack = require("webpack");
module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8090/',
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },
};