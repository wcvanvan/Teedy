pipeline {
    agent any
    stages{
        stage('Package') {
            steps {
              checkout scmGit(branches: [[name: '*/master']], extensions: [],
userRemoteConfigs: [[url: 'git@github.com:wcvanvan/Teedy.git']])
              sh 'mvn -B -DskipTests clean package'
} }
        // Building Docker images
        stage('Building image') {
          steps{
              docker build -t teedy2024_manual .
} }
        // Uploading Docker images into Docker Hub
        stage('Upload image') {
          steps{
                docker tag teedy2024_manual wcvanvan/teedy_local:v1.1
                docker push wcvanvan/teedy_local:v1.1
} }
          //Running Docker container
         stage('Run containers'){
          docker run -d -p 8084:8080 --name teedy_manual01 wcvanvan/teedy_local:v1.1
          docker run -d -p 8082:8080 --name teedy_manual02 wcvanvan/teedy_local:v1.1
          docker run -d -p 8083:8080 --name teedy_manual03 wcvanvan/teedy_local:v1.1
} }
steps{
} }
//your command
