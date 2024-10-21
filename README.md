# Objective

## Installation Steps
to Run the project
```
docker compose up
```

Application can be access on
```
http://localhost:8081/
```

To run the frontend test
1. Navigate to the vue-frontend folder and run
```
npm install --save-dev
npm run test
```

## Frontend Implementation
1. Using Vuetify for the table view
2. Delegate search function to the backend
3. Use service to request APIs
4. Use state to store search and list activities
5Implement test cases for all the methods in ActivityComponent

## Backend Implementation
1. Search function is filtered by Stream and check each values from the JSON file

## Future Improvements
1. Use Spring Data to achieve better general search results
2. Use Redis to cache frequently searched results
3. Write test cases for the backend
4. Include test running in both FE and BE Docker files


