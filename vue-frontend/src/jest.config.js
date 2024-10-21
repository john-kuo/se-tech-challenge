module.exports = {
    testEnvironment: 'jsdom',
    transform: {
        '^.+\\.vue$': '@vue/vue3-jest',
        '^.+\\.(js|jsx|ts|tsx)$': 'babel-jest'
    },
    moduleFileExtensions: ['vue', 'js', 'json', 'jsx', 'ts', 'tsx'],
    moduleNameMapper: {
        '^@/(.*)$': '<rootDir>/src/$1'
    },
    testEnvironmentOptions: {
        customExportConditions: ["node", "node-addons"],
    },
    transformIgnorePatterns: ['/node_modules/(?!@vue)'],
    testMatch: [
        '<rootDir>/src/tests/unit/**/*.spec.(js|jsx|ts|tsx)|**/__tests__/*.(js|jsx|ts|tsx)'
    ],
    setupFilesAfterEnv: ['<rootDir>/src/tests/setup.js'],
    rootDir: '.',
    roots: ['<rootDir>', '<rootDir>/src/']
}
