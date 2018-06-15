pipeline {
    tools {
        maven 'Maven'
        jdk 'Oracle JDK'
    }

    agent any

    stages {
        stage('Build') {
            steps {
                git url: 'git@awesomatic.local:sales-tax.git', credentialsId: 'bec1ce9f-5db8-44df-8f83-a3d59daba71e', branch: env.BRANCH_NAME
                sh 'mvn clean verify'
            }
        }

        stage('Code Quality') {
            when {
                branch 'develop'
            }
            steps {
                sh """
                    mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=e04c71886ed387764038de6b7299311934152ab5
                """
            }
        }

        stage('Deploy to INT') {
            steps {
                input 'Deploy to INT?'
                sh 'echo "Deploying $PROJECT_NAME version 1.0.0.$BUILD_NUMBER to INT" sleep 5;'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            publishHTML([allowMissing: false,
                         reportTitles: 'Mutation Report',
                         alwaysLinkToLastBuild: true,
                         keepAll: true,
                         reportDir: 'target/pit-reports',
                         reportFiles: 'index.html',
                         reportName: 'PIT Report'
            ])
        }
//        failure {
//            emailext to: 'donald@awesomatic.co.za', subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!', body:
//                    '''
//                        $PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS:
//
//                        Check console output at $BUILD_URL to view the results.
//                    '''
//        }
    }
}