module.exports = {
  lintOnSave: false,
  publicPath: './',
  devServer: {
    open: true,
    https: false, // https： {type: Booleam}
    hotOnly: false, // 热更新
    proxy: {
      '/api': {
        // target: 'http://localhost:3000/', // 本地后端地址
        target: 'http://localhost:8888/', // 线上后端地址
        changeOrigin: true, //允许跨域
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
}
