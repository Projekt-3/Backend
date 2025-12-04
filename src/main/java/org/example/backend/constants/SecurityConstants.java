package org.example.backend.constants;

public interface SecurityConstants {

    public static final String JWT_KEY = System.getenv("JWT_KEY");
    public static final String JWT_HEADER = "Authorization";
}
