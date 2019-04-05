# SimpleJerseyService
Only works with Java 11. 

Build by running 'mvn clean install'

Run the server using 'mvn exec:exec'

The mojo plugin will create a modulepath file which contains the absolute paths of every jar. This will obviously not work on the google app engine server.

The maven-dependency plugin will copy all jars to the appengine-staging folder. 

Now we can run on Windows from the target folder with:
java -p simple-service-1.0-SNAPSHOT.jar;appengine-staging/ --module myModule/com.example.Main localhost

On Linux from the target folder:
java -p simple-service-1.0-SNAPSHOT.jar:appengine-staging/ --module myModule/com.example.Main localhost

To deploy on App Engine, use 'mvn appengine:deploy'.

On App Engine, all jars are copied to the same folder. That's running via:
java -p ./ --module myModule/com.example.Main 0.0.0.0


