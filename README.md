This is sample OIDC token and token response mapper for Red Hat SSO.  
Download it from https://raw.githubusercontent.com/jstakun/rhsso-spi-utils/master/oidc-mapper-0.0.1.jar or build it yourselves from this repo.  
Paste it to standalone/deployments directory of you Red Hat SSO instance or add the link as extension to Red Hat SSO operator Keycloak CRD in OpenShift/Kubernetes.    
Using Red Hat SSO admin console create mapping in selected Client using Mappers tab.   
This sample will work only with RH SSO 7.5+ (Keycloak 15.0+).   
Older RH SSO/Keycloak versions has no implementation of access token response transformation code (OIDCAccessTokenResponseMapper interface and transformAccessTokenResponse method). Use this jar: https://raw.githubusercontent.com/jstakun/rhsso-spi-utils/master/oidc-mapper-0.0.1-old.jar or remove this source code and build yourselves.  