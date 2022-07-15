module.exports = {
  publicPath: process.env.NODE_ENV === "production" ? "/edu-boss/" : "/",
  indexPath: "index.html",
  assetsDir: "static",
  lintOnSave: process.env.NODE_ENV !== "production",
  productionSourceMap: false,
  devServer: {
    open: true,
    port: 8081
  },



  configureWebpack: {

    module: {
  
      rules: [
  
        {
  
          include: /node_modules/,
  
          test: /\.mjs$/,
  
          type: 'javascript/auto'
  
        }
  
      ]
  
    }
  
  }

};





