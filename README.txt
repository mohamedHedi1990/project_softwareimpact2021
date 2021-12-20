These notes indicate how you can run this project
====================================================
The project is spring boot application managed with Maven as management tool. So, before executing the project, you need to install the required dependecies and generate a jar file of the project by running :
mvn clean install
After that, you can run the project as spring boot application via the IDE or simply run this command:
java -jar {path-to-project}/target/impact-0.0.1-SNAPSHOT.jar

Installing java is required to execute this project. 

At runtime, the system will apply the "readModel" method of the "MatriceService" service which will analyze the content of an xml file called "Bicycle.xml" located under src / main / ressources and apply the algorithm described in the "Developped-Tools-description.pdf" file and detailed in our article to generate the appropriate matrix for this file.
If you want to run the project with another file, you should replace the content of this file and execute again the project. 

In the file "Developped-Tools-description.pdf" we analyse the project architecture, we explain the different phases of our algorithm and we present examples of the results.

