import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import VueMoment from 'vue-moment'

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  VueMoment,
  render: h => h(App)
}).$mount('#app')
