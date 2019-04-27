package com.github.navisarv.oauth2.soap.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.context.TransportContextHolder;

@Component
public class OAuth2SoapClient {
	
	@Autowired
	private WebServiceTemplate wsTemplate;
	
	@SuppressWarnings("unchecked")
	public <T> T processSoapRequest(String uri, Object request, Class<T> responseType) {
		return (T) this.wsTemplate.marshalSendAndReceive(uri, request, (message) -> {
			new DefaultOAuth2SoapRequestAuthenticator().authenticate(new ClientCredentialsResourceDetails(),
					new DefaultOAuth2ClientContext(), TransportContextHolder.getTransportContext().getConnection());
		});
	}

}
