package com.onebank.taskmaster.searchtask.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppConfigProperties {
    private Server server;
    private Database database;

    @Getter
    @Setter
    public static class Server {
        private int port;
    }

    @Getter
    @Setter
    public static class Database {
        private boolean enabled;
    }
}
