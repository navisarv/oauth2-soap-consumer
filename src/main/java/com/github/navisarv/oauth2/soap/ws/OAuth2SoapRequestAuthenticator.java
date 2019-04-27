package com.github.navisarv.oauth2.soap.ws;

import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.ws.transport.WebServiceConnection;

public interface OAuth2SoapRequestAuthenticator {

	void authenticate(OAuth2ProtectedResourceDetails resourceDetails, OAuth2ClientContext context,
			WebServiceConnection connection);

}
