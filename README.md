Test application that shows off spring power of restful web services.
========

To run with InelliJ idea(13+)
-------
1. Open build.gradle from Intellij IDEA so it will recognize project structure right away
2. Right click on SpringResApp and select Run or Debug
3. In browser open http://localhost:8080/oauth/val1/val2
 
To run from command line:
-------
1. build project: ./gradlew build
2. run app: java -jar build/libs/gs-rest-service-0.1.0.jar   (alternetevly you can run it using gradle ./gradlew bootRun)
3. In browser open http://localhost:8080/oauth/val1/val2
