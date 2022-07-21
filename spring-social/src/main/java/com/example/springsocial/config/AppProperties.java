package com.example.springsocial.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

//Get data in yaml,
//app:
//        auth:
//        tokenSecret: 04ca023b39512e46d0c2cf4b48d5aac61d34302994c87ed4eff225dcf3b0a218739f3897051a057f9b846a69ea2927a587044164b7bae5e1306219d50b588cb1
//        tokenExpirationMsec: 864000000
//        cors:
//        allowedOrigins: http://localhost:3000 # Comma separated list of allowed origins
//        oauth2:
//        # After successfully authenticating with the OAuth2 Provider,
//        # we'll be generating an auth token for the user and sending the token to the
//        # redirectUri mentioned by the client in the /oauth2/authorize request.
//        # We're not using cookies because they won't work well in mobile clients.
//        authorizedRedirectUris:
//        - http://localhost:3000/oauth2/redirect
//        - myandroidapp://oauth2/redirect
//        - myiosapp://oauth2/redirect
@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    //Get token
    private final Auth auth = new Auth();

    //List redirect uri
    private final OAuth2 oauth2 = new OAuth2();

    //Get token
    @Getter
    @Setter
    public static class Auth {
        private String tokenSecret;
        private long tokenExpirationMsec;
    }

    @Getter
    public static final class OAuth2 {
        //List redirect uri
        //0 = "http://localhost:3000/oauth2/redirect" =>
        //1 = "myandroidapp://oauth2/redirect"
        //2 = "myiosapp://oauth2/redirect"
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }
}
