pipeline {
    agent {
        label 'docker'
    }
    stages {
        stage('Building image') {
            steps {
                script {
                    dockerImage = docker.build "bobiologist/routes-traversal:$BUILD_NUMBER"
                }
            }
        }
        stage('Deploying image') {
            steps {
                script {
                    // Assume the Docker Hub registry by passing an empty string as the first parameter
                    docker.withRegistry('' , 'dockerhub') {
                        dockerImage.push()
                    }
                }
            }
        }
        
    }
}