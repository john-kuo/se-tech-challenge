import { createApp } from 'vue'

// Vuetify
import 'vuetify/dist/vuetify.min.css'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import store from './store'


import App from './App.vue'

const vuetify = createVuetify({
    components,
    directives,
})

createApp(App).use(vuetify).use(store).mount('#app')
