import { mount } from '@vue/test-utils'
import { createStore } from 'vuex'
import ActivitiesComponent from '@/components/ActivitiesComponent.vue'

// Mock Vuetify components
const VCard = { template: '<div><slot></slot><slot name="text"></slot></div>' }
const VTextField = { template: '<input />' }
const VDataTable = { template: '<table></table>', props: ['headers', 'items', 'search'] }

const createVuexStore = (initialState = {}) => {
    return createStore({
        modules: {
            activities: {
                namespaced: true,
                state: {
                    search: '',
                    activities: [],
                    ...initialState
                },
                actions: {
                    fetchActivities: jest.fn(),
                    searchActivities: jest.fn(),
                    setSearch: jest.fn()
                }
            }
        }
    })
}

describe('ActivitiesComponent', () => {
    const mountComponent = (store) => {
        return mount(ActivitiesComponent, {
            global: {
                plugins: [store],
                stubs: {
                    VCard,
                    VTextField,
                    VDataTable
                }
            }
        })
    }

    it('renders correctly', () => {
        const store = createVuexStore()
        const wrapper = mountComponent(store)
        expect(wrapper.findComponent(VCard).exists()).toBe(true)
        expect(wrapper.findComponent(VTextField).exists()).toBe(true)
        expect(wrapper.findComponent(VDataTable).exists()).toBe(true)
    })

    it('dispatches fetchActivities action on mount', async () => {
        const store = createVuexStore()
        store.dispatch = jest.fn()  // Mock the dispatch method
        mountComponent(store)
        await new Promise(process.nextTick)
        expect(store.dispatch).toHaveBeenCalledWith('activities/fetchActivities')
    })

    it('computes activities from store state', () => {
        const activities = [
            { id: 1, name: 'Activity 1' },
            { id: 2, name: 'Activity 2' }
        ]
        const store = createVuexStore({ activities })
        const wrapper = mountComponent(store)
        expect(wrapper.vm.activities).toEqual(activities)
    })


    it('passes correct props to v-data-table', () => {
        const activities = [
            { id: 1, name: 'Activity 1' },
            { id: 2, name: 'Activity 2' }
        ]
        const store = createVuexStore({ activities })
        const wrapper = mountComponent(store)
        const dataTable = wrapper.findComponent(VDataTable)

        expect(dataTable.props('headers')).toEqual([
            {align: 'start', key: 'name', sortable: false, title: 'Activities'},
            {key: 'title', sortable: true, title: 'Title'},
            {key: 'price', sortable: true, title: 'Price ($)'},
            {key: 'currency', sortable: true, title: 'Currency'},
            {key: 'rating', sortable: true, title: 'Rating'},
            {key: 'specialOffer', sortable: true, title: 'Special Offer'},
        ])

        expect(dataTable.props('items')).toEqual(activities)
        expect(dataTable.props('search')).toBe('')
    })
})
