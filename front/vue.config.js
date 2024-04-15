const CompressionPlugin = require('compression-webpack-plugin');

module.exports = {
    devServer: {
        host: '0.0.0.0',
        port: 8080,
        proxy: {
            '/zwz': {
                target: 'http://127.0.0.1:8081',
                ws: true
            },
            "/files":{
                target: 'http://127.0.0.1:8081',
            },
            "/imGroup":{
                target: 'http://127.0.0.1:8081',
            },
            "/imsingle":{
                target: 'http://127.0.0.1:8081',
            }

        }
    },
    productionSourceMap: false,
    configureWebpack: {
        plugins: [
            new CompressionPlugin({
                test: /\.js$|\.html$|\.css/,
                threshold: 10240
            })
        ]
    }
}
