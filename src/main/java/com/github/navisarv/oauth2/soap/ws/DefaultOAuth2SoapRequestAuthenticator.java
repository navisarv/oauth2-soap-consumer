package com.github.navisarv.oauth2.soap.ws;

import java.net.HttpURLConnection;
import java.util.Objects;

import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.http.AccessTokenRequiredException;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.ws.transport.WebServiceConnection;
import org.springframework.ws.transport.http.HttpComponentsConnection;

public class DefaultOAuth2SoapRequestAuthenticator implements OAuth2SoapRequestAuthenticator {
	@Override
	public void authenticate(OAuth2ProtectedResourceDetails resourceDetails, OAuth2ClientContext context,
			WebServiceConnection connection) {
		OAuth2AccessToken accessToken = new OAuth2RestTemplate(resourceDetails).getAccessToken();
		if (Objects.isNull(accessToken)) {
			throw new AccessTokenRequiredException(resourceDetails);
		}
		if (connection instanceof HttpURLConnection) {
			((HttpURLConnection) connection).addRequestProperty("Authorization",
					String.format("%s %s", accessToken.getTokenType(), accessToken.getValue()));
		} else if (connection instanceof HttpComponentsConnection) {
			// add header accordingly.
		}

	}

}
