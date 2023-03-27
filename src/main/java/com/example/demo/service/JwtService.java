package com.example.demo.service;

import com.example.demo.model.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    //Biến này là bí mật, chỉ có phía server biết.
    @Autowired
    public JwtProperties simpleProperties;

    //Truy xuất Username từ jwt token
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    //Truy xuất Claims từ jwt token
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Tạo Token cho người dùng
    public String generateToken(UserDetails userDetails) {

        return generateToken(new HashMap<>(), userDetails);
    }

    //Trong khi tạo mã--
    //1:Xác định các claims của Token, như là IssueAt, Expire, Subject and ID
    //2:Đăng kí sử dụng thuật toán HS256 tức là tạo ra 256bits và secret key
    //3:JWT compact thì nén nó thành chuỗi an toàn cho URL.
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Kiểm tra token hợp lệ.
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // Kiểm tra Token đã hết hạn.
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Truy xuất ngày hết hạn từ jwt token
    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    // Để truy xuất bất kì thông tin nào từ token,
    // cần phải có SECRET_KEY
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(simpleProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
