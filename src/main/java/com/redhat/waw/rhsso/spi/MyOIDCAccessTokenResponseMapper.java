package com.redhat.waw.rhsso.spi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.keycloak.models.ClientSessionContext;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ProtocolMapperModel;
import org.keycloak.models.UserSessionModel;
import org.keycloak.protocol.oidc.mappers.AbstractOIDCProtocolMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAccessTokenMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAccessTokenResponseMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAttributeMapperHelper;
import org.keycloak.protocol.oidc.mappers.OIDCIDTokenMapper;
import org.keycloak.protocol.oidc.mappers.UserInfoTokenMapper;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.IDToken;

public class MyOIDCAccessTokenResponseMapper extends AbstractOIDCProtocolMapper implements OIDCAccessTokenResponseMapper, OIDCAccessTokenMapper, OIDCIDTokenMapper, UserInfoTokenMapper {

	private static final Logger logger = Logger.getLogger(MyOIDCAccessTokenResponseMapper.class.getName());

	public static final String PROVIDER_ID = "oidc-my-mapper";
	
	private static final List<ProviderConfigProperty> configProperties = new ArrayList<>();

	static {
        OIDCAttributeMapperHelper.addTokenClaimNameConfig(configProperties);
        OIDCAttributeMapperHelper.addIncludeInTokensConfig(configProperties, MyOIDCAccessTokenResponseMapper.class);
    }
	
	@Override
	public AccessTokenResponse transformAccessTokenResponse(AccessTokenResponse accessTokenResponse, ProtocolMapperModel mappingModel,
            KeycloakSession session, UserSessionModel userSession, ClientSessionContext clientSessionCtx) {		
	    logger.log(Level.INFO, "Transforming OIDC Access Token Response");	
	    accessTokenResponse.setTokenType("Bearer");
	    accessTokenResponse.setOtherClaims("other", "claim");
		return accessTokenResponse;
	}

     @Override
	 public String getDisplayCategory() {
	        return "Token mapper";
	 }

     @Override 
	 public String getDisplayType() {
	        return "Hello World Mapper";
	 }

     @Override
	 public String getHelpText() {
	        return "Adds a hello world text to the claim";
	 }

     @Override
	 public List<ProviderConfigProperty> getConfigProperties() {
	        return configProperties;
	 }

	 @Override   
     public String getId() {
	        return PROVIDER_ID;
	 }
	 
	 @Override
	 protected void setClaim(IDToken token, ProtocolMapperModel mappingModel, UserSessionModel userSession, KeycloakSession keycloakSession, ClientSessionContext clientSessionCtx) {
			 OIDCAttributeMapperHelper.mapClaim(token, mappingModel, "hello world");
	 }
}