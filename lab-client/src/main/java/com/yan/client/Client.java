package com.yan.client;

import com.yan.client.interpreter.ApplicationLoader;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        ApplicationLoader client = new ApplicationLoader(System.getenv("Groups"));
        client.run();
    }
}
