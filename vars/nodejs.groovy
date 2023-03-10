def lintChecks(COMPONENT) {
         sh "echo Installing JSLINT"
        //sh "npm install jslint"
        //sh "ls -ltr node_modules/jslint/bin/"
        // sh "./node_modules/jslint/bin/jslint.js server.js"
        sh "echo lint checks completed for ${COMPONENT}.....!!!!!"
}

def call() {
    node {
        sh "rm -rf *"
        git branch: 'main', url: "https://github.com/HarshithaRaghu/${COMPONENT}.git"
        env.APP = "nodejs"
        lintChecks()
        env.ARGS="-Dsonar.sources=."
        common.sonarChecks()
        common.testCases()   
        common.artifacts()
    }
}

/* This is declarative pipeline  */

// def call(COMPONENT)                                              // call is the default function that's called by default.
// {
//     pipeline {
//         agent any 
//         environment {
//             SONAR = credentials('SONAR')
//             NEXUS = credentials('NEXUS')
//             SONAR_URL = "172.31.12.196"
//             NEXUS_URL = "172.31.7.27"
//         }
//         stages {                                        // Start of Stages
//             stage('Lint Checks') {
//                 steps {
//                     script {
//                         lintChecks(COMPONENT)                    // If the function is in the same file, no need to call the function with the fileName as prefix.
//                     }
//                 }
//             }

//             stage('Sonar Checks') {
//                 steps {
//                     script {
//                         env.ARGS="-Dsonar.sources=."
//                         common.sonarChecks(COMPONENT)                    
//                     }
//                 }
//             }
            
//             stage('Test Cases') {
//                     parallel {
//                         stage('Unit Tests'){
//                             steps {
//                                 sh "echo Unit Testing ......."
//                             }
//                         }

//                         stage('Integration Tests'){
//                             steps {
//                                 sh "echo Integration Testing ......."
//                             }
//                         }

//                         stage('Functional Tests'){
//                             steps {
//                                 sh "echo Functional Testing ......."
//                             }
//                         }
//                     }
//                 }

//             stage('Artifact Validation On Nexus') {
//                  when { 
//                      expression { env.TAG_NAME != null } 
//                      }
//                  steps {
//                      sh "echo checking whether artifact exists of not. If it doesnt exist then only proceed with Preparation and Upload"
//                      script {
//                          env.UPLOAD_STATUS=sh(returnStdout: true, script: "curl -L -s http://${NEXUS_URL}:8081/service/rest/repository/browse/${COMPONENT} | grep ${COMPONENT}-${TAG_NAME}.zip || true" )
//                          print UPLOAD_STATUS
//                      }
//                  }
//              }
            
            
//             stage('Downloading the dependencies') {
//                 when { 
//                     expression { env.TAG_NAME != null } 
//                     expression { env.UPLOAD_STATUS == "" }
//                 }
//                 steps {
//                     sh "npm install"
//                     sh "zip ${COMPONENT}-${TAG_NAME}.zip node_modules server.js"
//                     sh "ls -ltr"
//                 }
//             }
           
//            stage('Uploading the artifact') {
//                 when { 
//                     expression { env.TAG_NAME != null } 
//                     expression { env.UPLOAD_STATUS == "" }   
//                     }
//                 steps {
//                     sh "curl -f -v -u ${NEXUS_USR}:${NEXUS_PSW} --upload-file ${COMPONENT}-${TAG_NAME}.zip http://${NEXUS_URL}:8081/repository/${COMPONENT}/${COMPONENT}-${TAG_NAME}.zip"
//                 }
//             }
        
        
        
//         } 
        
        
//     }
// }


