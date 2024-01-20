node('unix') {
    stage('Git checkout') {
		checkout scm
	}
	stage('Run tests') {
		withMaven(globalMavenSettingsConfig: '', jdk: '', maven: 'Default', mavenSettingsConfig: '', traceability: true) {
			sh 'mvn clean test -Dcucumber.filter.tags=@${tag} -Dtype.browser=${browser}'
		}
	}
	stage('Allure reports') {
		allure includeProperties: false, jdk: '', results: [[path: 'target/reports/allure-results']]
	}
}