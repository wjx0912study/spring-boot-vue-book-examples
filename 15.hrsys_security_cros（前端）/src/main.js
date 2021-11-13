import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

import Qs from "qs";
Vue.prototype.qs = Qs;
Vue.config.productionTip = false;
Vue.prototype.axios = axios;
Vue.prototype.axios.defaults.withCredentials = true; //允许cookies
Vue.use(ElementUI);
new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
