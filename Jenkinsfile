pipeline {
    agent any
    tools {
        maven 'maven 3.3.9'
    }
    stages {
        stage('Checkout from GIT') {
            steps {
                echo 'Checking out from GIT'
            }
        }
        stage('Checking Code Style') {
            steps {
                echo 'Checking code style'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Sonar') {
            steps {
                echo 'Running Sonar'
                sh 'mvn -Psonar -Dsonar.host.url=http://sonarqube:9000 org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.0.1398:sonar'
            }
        }
        stage('Create Docker Image') {
            steps {
                echo 'Creating Docker image'
            }
        }
    }
}
