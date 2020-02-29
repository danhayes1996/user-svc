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
                sh 'mvn -Psonar -Dsonar.host.url=http://127.0.0.1:9000 -Dsonar.login=87b7830aca1fdb13e59760203ee5f0492bdab216
 org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.0.1398:sonar'
            }
        }
        stage('Create Docker Image') {
            steps {
                echo 'Creating Docker image'
            }
        }
    }
}
