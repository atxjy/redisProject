package com.TBT.utils;

import com.TBT.pojo.Emp;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;

public class JwtUtil {

	private static String private_key= "jyfuyfyfugigkhksrshyfkhljjhulhgjfhkihgh";

	// 把需要放入的数据放入到token
	public static String getToken(Emp emp) {

		// 设置超时时间
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.SECOND, 60000);

		// 创建JWT -- 使用JWT.create()方法创建
		// 此时builer对象中默认设置了header--标头，类型默认为JWT
		Builder builder = JWT.create();

		String token = builder// head
				.withAudience(emp.getEmpno().toString()) // 负载
				.withClaim("idcard", 3798221)
				// .withClaim("", null)
				.withExpiresAt(instance.getTime()) // 超时时间

				.sign(Algorithm.HMAC256(emp.getEname()));// 签名（解析时要用）
				// .sign(Algorithm.HMAC256(private_key));// 签名（解析时要用）
		return token;
	}

}
