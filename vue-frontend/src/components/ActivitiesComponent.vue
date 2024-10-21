<script setup>
import {computed, onMounted, watch} from 'vue'
import {useStore} from 'vuex'

const store = useStore()

const search = computed({
    get: () => store.state.activities.search,
    set: (value) => store.dispatch('activities/setSearch', value)
})

const headers = [
    {align: 'start', key: 'name', sortable: false, title: 'Activities'},
    {key: 'title', sortable: true, title: 'Title'},
    {key: 'price', sortable: true, title: 'Price ($)'},
    {key: 'currency', sortable: true, title: 'Currency'},
    {key: 'rating', sortable: true, title: 'Rating'},
    {key: 'specialOffer', sortable: true, title: 'Special Offer'},
]

const activities = computed(() => store.state.activities.activities)

onMounted(() => {
    store.dispatch('activities/fetchActivities')
})

watch(search, (newValue) => {
    if (newValue) {
        store.dispatch('activities/searchActivities', newValue)
    } else {
        store.dispatch('activities/fetchActivities')
    }
})
</script>

<template>
    <v-card title="Activities" flat>
        <template v-slot:text>
            <v-text-field
                v-model="search"
                label="Search"
                prepend-inner-icon="mdi-magnify"
                variant="outlined"
                hide-details
                single-line
            ></v-text-field>
        </template>

        <v-data-table
            :headers="headers"
            :items="activities"
            :search="search"
        ></v-data-table>
    </v-card>
</template>
