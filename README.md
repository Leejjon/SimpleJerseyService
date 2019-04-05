# SimpleJerseyService
With Java 11 and modules

Build by running 'mvn clean install'

Run the server using 'mvn exec:exec'

The mojo plugin will create a modulepath file which contains the absolute paths of every jar. This will obviously not work on the google app engine server.

The maven-dependency plugin will copy all jars to the appengine-staging folder. 

Now we can run on Windows with:
java -p simple-service-1.0-SNAPSHOT.jar;appengine-staging/ --module myModule/com.example.Main localhost

On Linux
java -p simple-service-1.0-SNAPSHOT.jar:appengine-staging/ --module myModule/com.example.Main localhost

On App Engine, see app.yaml file.


