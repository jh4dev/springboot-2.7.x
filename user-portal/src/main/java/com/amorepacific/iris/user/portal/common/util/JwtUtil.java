package com.amorepacific.iris.user.portal.common.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.amorepacific.iris.user.portal.config.JwtConfig;
import com.amorepacific.iris.user.portal.dto.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtil {


    public static String generateJwtToken(UserDto user){
        JwtBuilder builder = Jwts.builder()
                .setSubject(user.getUserId())
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setExpiration(createExpireDateForOneYear())
                .signWith(createSigningKey());

        return builder.compact();
    }

    public static boolean isValidToken(String token){
        try{
            Claims claims = getClaimsFormToken(token);
            log.info("expireTime : {}", claims.getExpiration());
            log.info("userId : {}", claims.get("userId"));
            log.info("role : {}", claims.get("userRoleCd"));
            return true;
        } catch (ExpiredJwtException exception){
            log.error("Token Expired");
            return false;
        } catch (JwtException exception){
            log.error("Token Tampered");
            return false;
        } catch (NullPointerException exception){
            log.error("Token is null");
            return false;
        }
    }

    private static Map<String, Object> createHeader(){
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    public static String getTokenFromHeader(String header){
        return header.split("")[1];
    }

    private static Map<String, Object> createClaims(UserDto user){
        // 공개 클레임에 사용자의 이름과 이메일을 설정하여 정보를 조회할 수 있다.
        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", user.getUserId());
        claims.put("userName", user.getUserName());
        claims.put("userRoleCd", user.getUserRoleCd());
        claims.put("userTeamCd", user.getUserTeamCd());

        return claims;
    }

    private static Date createExpireDateForOneYear(){
        // 토큰 만료 시간은 30일으로 설정
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);

        return c.getTime();
    }

    private static Key createSigningKey(){
    	return Keys.hmacShaKeyFor(JwtConfig.getSecretKey().getBytes(StandardCharsets.UTF_8));
    	
    }

    private static Claims getClaimsFormToken(String token){
        return Jwts.parserBuilder().setSigningKey(createSigningKey()).build().parseClaimsJws(token).getBody();
    }

}