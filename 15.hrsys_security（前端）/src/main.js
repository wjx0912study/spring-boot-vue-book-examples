import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import Qs from 'qs'
import store from './store'//引入store
import tools from "./util/tools";

Vue.prototype.tools = tools
Vue.prototype.qs = Qs
Vue.config.productionTip = false;
Vue.prototype.axios = axios;
Vue.use(ElementUI);
new Vue({
  router,store,
  render: h => h(App)
}).$mount("#app");
