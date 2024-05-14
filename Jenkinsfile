pipeline {
    agent any
    stages{
        stage('Package') {
            steps {
              checkout scmGit(branches: [[name: '*/master']], extensions: [],
userRemoteConfigs: [[url: 'git@github.com:wcvanvan/Teedy.git']])
              sh 'mvn -B -DskipTests clean package'
            } 
        }
        // Building Docker images
        stage('Building image') {
          steps{
              sh 'docker build -t teedy2024_manual .'
          } 
        }
        // Uploading Docker images into Docker Hub
        stage('Upload image') {
          steps{
                sh 'docker tag teedy2024_manual wcvanvan/teedy_local:v1.1'
                sh 'docker push wcvanvan/teedy_local:v1.1'
          }
        }
          //Running Docker container
        stage('Run containers'){
          steps{
            sh 'docker run -d -p 8084:8080 --name teedy_manual04 wcvanvan/teedy_local:v1.1'
            sh 'docker run -d -p 8082:8080 --name teedy_manual05 wcvanvan/teedy_local:v1.1'
            sh 'docker run -d -p 8083:8080 --name teedy_manual06 wcvanvan/teedy_local:v1.1'
          }
        }
    }
}
//your command
