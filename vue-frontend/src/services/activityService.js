import axios from 'axios'

const API_URL = 'http://localhost:8080'

export default {
    async fetchActivities() {
        try {
            const response = await axios.get(`${API_URL}/activities`)
            return response.data
        } catch (error) {
            console.error('Error fetching activities:', error)
            throw error
        }
    },

    async searchActivities(searchTerm) {
        try {
            const response = await axios.get(`${API_URL}/search`, {
                params: { search: searchTerm }
            })
            return response.data
        } catch (error) {
            console.error('Error searching activities:', error)
            throw error
        }
    }
}
