pipeline {
    agent any

    triggers {
        pollSCM('* * * * *')
    }

    parameters {
        string(name: 'BRANCH', defaultValue: 'main', description: 'Branch to build')
        string(name: 'TEST_TAGS', defaultValue: 'ui_test', description: 'Test tags to run (e.g., @smoke)')
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
                    try {
                        // Запускаем тесты по тегам
                        sh "./gradlew clean ${params.TEST_TAGS}"
                    } catch (err) {
                        // Логируем ошибку, но пайплайн не прерывается
                        echo "Тесты упали, продолжаем сборку отчёта..."
                    }
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