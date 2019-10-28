package com.hehe.config;

import io.jsonwebtoken.*;

//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;



import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean {

   private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

   private static final String AUTHORITIES_KEY = "auth";

   private final String Secret;
   private final long tokenValidityInMilliseconds;






   public TokenProvider(
      @Value("${jwt.secret}") String Secret,
      @Value("${jwt.expire}") long tokenValidityInSeconds
   ) {
       //   @Value("${jwt.token-validity-in-seconds-for-remember-me}") long tokenValidityInSecondsForRememberMe
      this.Secret = Secret;
      this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
//      this.tokenValidityInMillisecondsForRememberMe = tokenValidityInSecondsForRememberMe * 1000;
   }

   @Override
   public void afterPropertiesSet() {

   }

    /**
     *
     *  生成token
     * @param claims 传入的map
     * @param rememberMe
     * @return
     */
   public String createToken(HashMap<String, Object> claims, boolean rememberMe) {

      long now = (new Date()).getTime();
      Date validity;
      if (rememberMe) {
          validity = new Date(now + this.tokenValidityInMilliseconds*1000);
      } else {
         validity = new Date(now + this.tokenValidityInMilliseconds*1000);
      }

      return Jwts.builder().setClaims(claims)
         .signWith(SignatureAlgorithm.HS512, Secret)
         .setExpiration(validity)
         .compact();
   }



    /**
     * 验证token，传入token验证是否合法
     * @param authToken
     * @return
     */
   public boolean validateToken(String authToken) {
      try {
         Jwts.parser().setSigningKey(Secret).parseClaimsJws(authToken);
         return true;
      } catch ( MalformedJwtException e) {
         log.info("Invalid JWT signature.");
         log.trace("Invalid JWT signature trace: {}", e);
      } catch (ExpiredJwtException e) {
         log.info("Expired JWT token.");
         log.trace("Expired JWT token trace: {}", e);
      } catch (UnsupportedJwtException e) {
         log.info("Unsupported JWT token.");
         log.trace("Unsupported JWT token trace: {}", e);
      } catch (IllegalArgumentException e) {
         log.info("JWT token compact of handler are invalid.");
         log.trace("JWT token compact of handler are invalid trace: {}", e);
      }
      return false;
   }
}
