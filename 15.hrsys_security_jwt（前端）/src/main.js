import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import Qs from 'qs'
Vue.prototype.qs = Qs
Vue.config.productionTip = false;
Vue.prototype.axios = axios;
Vue.use(ElementUI);
new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
// 添加请求拦截器http request
axios.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  if (localStorage.token) {
    config.headers.Authorization = localStorage.token
  }
  return config;
}, function (error) {
  // 对请求错误做些什么
  console.log("请求拦截器出错", error);
});