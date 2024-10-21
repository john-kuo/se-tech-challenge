import { createStore } from 'vuex'
import activities from './modules/activities'

const store = createStore({
    modules: {
        activities
    }
})

export default store
