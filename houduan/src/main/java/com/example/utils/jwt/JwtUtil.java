package com.example.utils.jwt;

import com.example.common.Result;
import com.example.exception.CustomException;
import com.example.utils.MapWrapperUtils;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * 1.创建一个32-byte的密匙
     */

    private static final byte[] secret = "modhfaguafdkslsmxofangsnhpobcewm".getBytes();


    //生成一个token
    public static String creatToken(Map<String, Object> payloadMap) {

        //3.先建立一个头部Header
        /**
         * JWSHeader参数：1.加密算法法则,2.类型，3.。。。。。。。
         * 一般只需要传入加密算法法则就可以。
         * 这里则采用HS256
         *
         * JWSAlgorithm类里面有所有的加密算法法则，直接调用。
         */
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        //建立一个载荷Payload
        Payload payload = new Payload(new JSONObject(payloadMap));

        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            //建立一个密匙
            JWSSigner jwsSigner = new MACSigner(secret);

            //签名
            jwsObject.sign(jwsSigner);
        } catch (JOSEException e) {
            throw new CustomException("200","toke生成失败");
        }
        //生成token
        return jwsObject.serialize();
    }

    //解析一个token

    public static Result valid(String token) throws ParseException, JOSEException {
        Result returnData = null;
//        解析token

        JWSObject jwsObject = JWSObject.parse(token);

        //获取到载荷
        Payload payload = jwsObject.getPayload();

        //建立一个解锁密匙
        JWSVerifier jwsVerifier = new MACVerifier(secret);

        //判断token
        if (jwsObject.verify(jwsVerifier)) {
            //载荷的数据解析成json对象。
            JSONObject jsonObject = payload.toJSONObject();
            returnData = Result.success(jsonObject.get(MapWrapperUtils.KEY_USER_ID));
            //判断token是否过期
            if (jsonObject.containsKey("exp")) {
                Long expTime = Long.valueOf(jsonObject.get("exp").toString());
                Long nowTime = new Date().getTime();
                //判断是否过期
                if (nowTime > expTime) {
                    //已经过期
                    returnData = Result.error("-1","登录过期");
                }
            }
        } else {
            returnData = Result.error("-1","token令牌是个假的");
        }
        return returnData;
    }
}
