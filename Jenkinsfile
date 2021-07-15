pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out...'
                git branch: 'track4',  credentialsId: 'devops-track4', url: 'https://bitbucket.org/RicardoNogueira0025/switch_2020_g3_devops.git'
            }
        }
        stage('Assemble') {
            steps {
                echo 'Assembling...'
                dir ('DEVOPS/Track 4'){
                sh 'chmod a+x gradlew'
                sh './gradlew clean assemble'
                }
                }
            }

        stage('Tests') {
                    steps {
                        echo 'Testing...'
                        dir ('DEVOPS/Track 4'){
                        sh './gradlew test jacocoTestReport'
                        junit '**/*.xml'
                        }
                        step([$class: 'JacocoPublisher',
                        execPattern: '**/build/jacoco/*.exec',
                        classPattern: '**/build/classes',
                        sourcePattern: 'src/main/java',
                        exclusionPattern: 'src/test*'
                        ])
                        }
                    }

        stage('Javadoc') {
                    steps{
                        echo 'Generating Javadoc'
                        dir ('DEVOPS/Track 4'){
                        sh './gradlew javadoc'
                        javadoc javadocDir: 'build/docs/javadoc', keepAll: false
                        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false,
                        reportDir: 'build/docs/javadoc', reportFiles: 'index.html', reportName: 'Generated Javadoc', reportTitles: 'Generated Javadoc'])
                        }
                        }
                    }

        stage('Assemble Frontend') {
                    steps{
                        echo 'Assembling Frontend...'
                        dir ('DEVOPS/Track 4/frontend'){
                        sh 'npm install'
                        sh 'CI=false npm run build'
                        }
                           }
                      }

        stage('Archiving') {
                    steps {
                        echo 'Archiving...'
                        dir ('DEVOPS/Track 4'){
                        echo 'Archiving Back End artifacts'
                        archiveArtifacts 'build/libs/*'
                        echo 'Archiving Front End artifacts'
                        archiveArtifacts 'frontend/build/**'
                        }
                        }
                    }

    stage('Build Docker Images') {
    steps {
        echo 'Building Front End Docker Image'
        dir('DEVOPS/Track 4/frontend') {
            script {
                docker.withRegistry('', 'docker-credentials') {
                    frontend_image = docker.build("titipipers/frontend_image")
                    frontend_image.push()
                }
            }
        }
        echo 'Building Back End Docker Image'
        dir('DEVOPS/Track 4') {
            script {
                docker.withRegistry('', 'docker-credentials') {
                    backend_image = docker.build("titipipers/backend_image")
                    backend_image.push()
                }
            }
        }
        echo 'Building Database Docker Image'
                dir('DEVOPS/Track 4/Database_Docker') {
                    script {
                        docker.withRegistry('', 'docker-credentials') {
                            database_image = docker.build("titipipers/database_image")
                            database_image.push()
                        }
                    }
                }
    }
}



}

}