Exercise
========

Part 1 - Creating a plain Camel app  
------

1. Create a new Camel application using Maven (camel-archetype-java)  [*]
1. Open the application in your own IDE.
   (mvn eclipse:eclipse ?)
1. Run the example program.
1. Try to browse and understand the code at a high level.
1. Plenty of time? Then browse https://github.com/apache/camel/tree/master/examples

Part 2 - Creating a Spring Boot based Camel app
------
1. Modify the project created in part 1 to run with Spring Boot.
   (for Camel 2.15 it is necessary to block the main thread).
   
[*] mvn archetype:generate -DarchetypeGroupId=org.apache.camel.archetypes -DarchetypeArtifactId=camel-archetype-java -DarchetypeVersion=2.15.2 -DgroupId=dk.tbsalling.training.camel -DartifactId=firstapp
