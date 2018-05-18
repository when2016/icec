package org.icec.web.shiro.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.icec.web.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
@Component(value = "jwtUtil")
public class JwtUtil {
    @Value("${jwt.secret:a1g2y47dg3dj59fjhhsd7cnewy73j}")
    private String secret="a1g2y47dg3dj59fjhhsd7cnewy73j";

    // default 7 days
    //@Value("${jwt.expiration:604800}")
    private Long expiration=604800l;
    @Autowired
	private Environment env;
    public String generateToken(SysUser user) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", user.getLoginName());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .setIssuedAt(this.generateCurrentDate())
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();
            final Date iat = claims.getIssuedAt();
            final Date exp = claims.getExpiration();
            if (iat.before(lastPasswordReset) || exp.before(generateCurrentDate())){
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();
            refreshedToken = this.generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public TokenStatus verifyToken(String token) {
        TokenStatus result = TokenStatus.INVALID;
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();
            final Date exp = claims.getExpiration();
            if (exp.before(generateCurrentDate())){
                result = TokenStatus.EXPIRED;
            } else {
                result = TokenStatus.VALID;
            }
        } catch (Exception e) {
            result = TokenStatus.INVALID;
        }
        return result;
    }

    public String getUsernameFromToken(String token){
        String username = null;
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
}