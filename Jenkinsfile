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
                sh 'mvn -Psonar -Dsonar.sourceEncoding=UTF-8 org.sonarsource.scanner.maven:sonar-maven-plugin:3.0.2:sonar'
            }
        }
        stage('Create Docker Image') {
            steps {
                echo 'Creating Docker image'
            }
        }
    }
}