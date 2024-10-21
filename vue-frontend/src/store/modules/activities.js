// src/store/modules/activities.js

import activityService from '@/services/activityService'

export default {
    namespaced: true,
    state: () => ({
        activities: [],
        search: ''
    }),
    mutations: {
        SET_ACTIVITIES(state, activities) {
            state.activities = activities
        },
        SET_SEARCH(state, search) {
            state.search = search
        }
    },
    actions: {
        async fetchActivities({ commit }) {
            try {
                const activities = await activityService.fetchActivities()
                commit('SET_ACTIVITIES', activities)
            } catch (error) {
                console.error('Error fetching activities:', error)
            }
        },
        async searchActivities({ commit }, searchTerm) {
            try {
                const activities = await activityService.searchActivities(searchTerm)
                commit('SET_ACTIVITIES', activities)
            } catch (error) {
                console.error('Error searching activities:', error)
            }
        },
        setSearch({ commit }, search) {
            commit('SET_SEARCH', search)
        }
    },
    getters: {
        getActivities: (state) => state.activities,
        getSearch: (state) => state.search
    }
}
