module myModule {
    requires java.ws.rs;
    requires java.json.bind;
    requires grizzly.http.server;
    requires jersey.container.grizzly2.http;
    requires jersey.server;
    requires jersey.common;
    requires lombok;
    requires java.logging;

    exports com.example;
}