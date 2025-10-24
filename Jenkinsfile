pipeline {
    agent any

    parameters {
        string(name: 'BRANCH', defaultValue: 'main', description: 'Branch to build')
        string(name: 'TEST_TAGS', defaultValue: '', description: 'Test tags to run (e.g., @smoke)')
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Checking out branch: ${params.BRANCH}"
                git branch: "${params.BRANCH}", url: 'https://github.com/AlinaMinackova/automating_ui_api.git'
            }
        }

        stage('Prepare') {
            steps {
                sh 'chmod +x gradlew'
            }
        }

        stage('Build') {
            steps {
                echo 'Building Spring Boot application...'
                sh './gradlew build -x test'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    def testCommand = './gradlew test'
                    if (params.TEST_TAGS?.trim()) {
                        testCommand += " -Dtags=${params.TEST_TAGS}"
                        echo "Running tests with tags: ${params.TEST_TAGS}"
                    } else {
                        echo "Running all tests"
                    }
                    sh testCommand
                }
            }

        }

        stage('Allure Report') {
            steps {
                allure([
                    results: [[path: 'build/allure-results']]
                ])
            }
        }
    }

    post {
        success {
            echo 'All tests passed ✅'
        }
        failure {
            echo 'Some tests failed ❌'
        }
    }
}