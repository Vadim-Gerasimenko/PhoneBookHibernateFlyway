const path = require("path");
const webpack = require("webpack");

const {CleanWebpackPlugin} = require("clean-webpack-plugin");
const {VueLoaderPlugin} = require("vue-loader");

const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
    entry: "./js/script.js",
    output: {
        path: path.resolve(__dirname, "..", "resources/static"),
        filename: "script.js",
        assetModuleFilename: "[path][name][ext]?[contenthash]"
    },
    devtool: "source-map",
    target: ["web", "es5"],
    devServer: {
        hot: true,
        open: true,
        proxy: [
            {
                context: ["/api"],
                target: "http://localhost:8080"
            }
        ]
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader",
                    options: {
                        presets: ["@babel/preset-env"]
                    }
                }
            },
            {
                test: /\.css$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    "css-loader"
                ]
            },
            {
                test: /\.(png|jpg|gif|svg|ttf|eot|woff|woff2)$/,
                type: "asset/resource"
            },
            {
                test: /\.scss$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    "css-loader",
                    "sass-loader"
                ]
            },
            {
                test: /\.vue$/,
                use: "vue-loader"
            }
        ]
    },
    plugins: [
        new VueLoaderPlugin(),
        new CleanWebpackPlugin(),
        new MiniCssExtractPlugin({
            filename: "style.css"
        }),
        new HtmlWebpackPlugin({
            template: "index.html"
        }),
        new webpack.DefinePlugin({
            __VUE_OPTIONS_API__: "true",
            __VUE_PROD_DEVTOOLS__: "false",
            __VUE_PROD_HYDRATION_MISMATCH_DETAILS__: "false"
        })
    ]
};