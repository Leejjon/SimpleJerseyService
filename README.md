# SimpleJerseyService
With Java 11 and modules

Build by running 'mvn clean install'

Run the server using 'mvn exec:exec'

The mojo plugin will create a modulepath file which contains the absolute paths of every jar. This will obviously not work on the google app engine server.

The maven-dependency plugin will copy all jars to the appengine-staging folder. 

Now we can run with:
java -p classes;appengine-staging\ --module myModule/com.example.Main
