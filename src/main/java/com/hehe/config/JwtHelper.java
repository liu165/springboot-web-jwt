package com.hehe.config;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtHelper {
    private Long EXPIRATION_TIME;
    private String SECRET;
  //  private final String TOKEN_PREFIX = "Bearer";
    private final String HEADER_STRING = "Authorization";

    public JwtHelper(String secret, long expire) {
        this.EXPIRATION_TIME = expire;//密钥
        this.SECRET = secret;
        System.out.println("正在初始化Jwthelper，expire="+expire);
    }

    public JSONObject generateToken(Map<String, Object> claims) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.SECOND, EXPIRATION_TIME.intValue());
        Date d = c.getTime();
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setExpiration(d)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        JSONObject json = new JSONObject();
        json.put("token",jwt);
        json.put("expire-time",new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(d) );
        return json;
    }

    public Map<String, Object> validateTokenAndGetClaims(HttpServletRequest request) {
        //token为请求接口设置的名字，可以随便起，不唯一
        //返回null就意味着认证失败了
        String token = request.getHeader("token");
        System.out.println("token is:"+token);
        if (token == null&&"".equals(token)) {
            return null;
        }
        //相当于之前保存的对象
        Map<String, Object> body = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return body;
    }
}