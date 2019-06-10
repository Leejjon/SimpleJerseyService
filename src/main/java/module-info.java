module myModule {
    requires java.ws.rs;
    requires java.xml.bind;
    requires java.json.bind;
    requires grizzly.http.server;
    requires com.sun.xml.bind;
    requires jersey.container.grizzly2.http;
    requires jersey.server;
    requires jersey.common;
    exports com.example;
}