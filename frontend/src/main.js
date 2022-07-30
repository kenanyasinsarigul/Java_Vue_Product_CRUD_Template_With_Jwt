// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from "vue";
import App from "./App";
import router from "./router";
import Vuelidate from "vuelidate";
import VueToastr2 from "vue-toastr-2";
import axios from "axios";
import "vue-toastr-2/dist/vue-toastr-2.min.css";
import store from './store'

window.toastr = require("toastr");

//Konfigürasyon dosyası oluşturup oradan da çekilebilir.
axios.defaults.baseURL = "http://localhost:8080";
if (store.getters["Auth/isLoggedIn"]) {
  const jwtToken = store.getters["Auth/jwt"];
  axios.defaults.headers.common["Authorization"] = `Bearer ${jwtToken}`;
}

Vue.use(VueToastr2);
Vue.use(Vuelidate);

Vue.config.productionTip = false;

new Vue({
	render: h => h(App),
	router,
    store,
}).$mount('#app')

