package br.com.clima.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AutorizacaoConfig extends AuthorizationServerConfigurerAdapter{
	
	private static final Integer ACCESS_TOKEN_VALIDITY_IN_SECONDS = 60 * 60 * 24;
    private static final Integer REFRESH_TOKEN_VALIDITY_IN_SECONDS = 60 * 60 * 24;
    
    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    
    @Autowired
	private AuthenticationManager authenticationManager;
	
	@Override  
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {  
	    security.tokenKeyAccess("permitAll()")  
	            .checkTokenAccess("isAuthenticated()")  
	            .allowFormAuthenticationForClients();  
	}
	
	@Override  
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {  
		
		clients.inMemory()  
	            .withClient(clientId)  
	            //.secret(new BCryptPasswordEncoder(10).encode(clientSecret))  
	            .secret(clientSecret)  
	            .authorizedGrantTypes("password", "authorization_code", "refresh_token")  
	            .scopes("read", "write", "trust")  
	            .authorities("ADMIN", "USER")
	            .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_IN_SECONDS)  
	            .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_IN_SECONDS);
	}
	
	@Override  
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {  
	    endpoints.authenticationManager(authenticationManager)
	    	.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);  
	}
}
