package ru.vtb.microservices.carpark.commons.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.util.Assert;

/**
 * @author rmorenko
 */
@Slf4j
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {

        private String internalToken ="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzEwNDYwOTcsInVzZXJfbmFtZSI6ImFkbWluaXN0cmF0b3IiLCJhdXRob3JpdGllcyI6WyJhZG1"
                + "pbmlzdHJhdG9yIl0sImxvY2F0aW9uSWQiOjEsImNsaWVudF9pZCI6ImNhcnNwYXJrIiwic2NvcGUiOlsiY2Fyc3BhcmsiXX0.MkjKVKn9t5Bv4Z8Z0a2UrrqPiGy_spG0LPRwdNKrjNOm2LO-"
                + "tyVQyUPa85hMR0-XBRkf9f-y1fM4U_vMZoTOFuPLJGPcLaoUyVyLtfVv9ZtTlG4Sv6bhGdDri1TEBXiaErhlT8Rr3VbPlr87ZGayUnAnNxnsdfFPEBD8owCvgcAsWg-Egh1I8OG-t8ngel6zgLW"
                + "Bbx2-3RxRFLe-ydFTKzqRW_-x5TkpInMYWmLc66pvVvIcYdlFNAGp0iNs1BJF3PeezXl-B6muIrNxBto1csQgkt_Q6O9kID7gMUNmG6_3qou4QCt3X0y6h6FO-aHBS88hxoOqsD5hkjA76bDX4A";

        private static final String AUTHORIZATION_HEADER = "Authorization";

        private static final String BEARER_TOKEN_TYPE = "Bearer";

        private final OAuth2ClientContext oauth2ClientContext;

        public OAuth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext) {
                Assert.notNull(oauth2ClientContext, "Context can not be null");
                this.oauth2ClientContext = oauth2ClientContext;
        }

        @Override
        public void apply(RequestTemplate template) {

                if (template.headers().containsKey(AUTHORIZATION_HEADER)) {
                       log.warn("The Authorization token has been already set");
                } else {
                        log.debug("Constructing Header {} for Token {}", AUTHORIZATION_HEADER, BEARER_TOKEN_TYPE);
                        template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE,
                                internalToken));
                }
        }
}