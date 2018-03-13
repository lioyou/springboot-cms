package com.leecp.jpa.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class TokenAuthenticationUtil {
    static final long EXPIRATIONTIME = 1000*60*30; // 30 min
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    //产生token
    public static void generateToken(HttpServletResponse response,String username){
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }
    //解析token，并验证
    public static boolean parseToken(HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            return true;
        }
        return false;
    }


//    public static boolean authencate(HttpServletRequest request){
//        String token = request.getHeader(HEADER_STRING);
//        if (token != null) {
//            // parse the token.
//            String user = Jwts.parser()
//                    .setSigningKey(SECRET)
//                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
//                    .getBody()
//                    .getSubject();
//           return true;
//        }
//        return false;
//    }
}
