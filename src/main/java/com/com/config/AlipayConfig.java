package com.com.config;
import java.io.FileWriter;
import java.io.IOException;


public class  AlipayConfig {
	
	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016100100636905"; 
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDXNDAphMg3gGbc86/UFoqypZ0/VgQe8dZUPSz6ORNKE3fn92kHOyodQKPODPqWAokTGKl20/WKhihkN4t7hiurh3eBeGxxnNKZZEV68G+IjHrl7on5/PZRyGY9nQ22DzTIT7EDjx4YRKi7PUgDO1JZ/j4EnplZE9XjPdzaqhU8x+W9I1XDy9ojHKCoVj4MhIQ874MVO+L5qKqS9IeJUUgUyTb6ORvTE7Fqq/HWuzTugWNHzB01sknHJRzEzmwibNraEsfs2a4J9vXqNmVrRyVYRSmqTGSaeMGKfKn+kFPAslaKcI2Fnq3fZwLbiNnKbUcv3hehfSrJPfT0mMBBmF87AgMBAAECggEBAMttsuVcdmM4xoOw+5HR6TRsp5OafPS/iOpClgTG9Tv94UfusWK7g9j5JJ7/19T7+pWJp5ADV0WmAgKSGxEeSKOxv78sld5j7xQ/HArVw6jhmr9F2Tnt7O5sMHGnx7FE+JEt+9ewbzNjm9i7Whpr9jHJythVEAKLZJGWBfLR+pdjZybdR+Wwlqkqh/yoVkhDCN6Ims8N52ed4M7Gqqz1izifRgr/mrb3jk46KZEJXCTj2A3zXyrzoz8sIiz2S11iYaoWOsQB1TsZGWrfNYYChCAhybWGHV9q6JwgY8WY2A+lKFf206gueXeCPK9+rZXInx2Xb9buLj2wCFFSHWsVoJECgYEA77mRIOQG9b0SHn/GIHevMiMM5A5vpLLe4ZZqZCgZ21vdqHvGY4My8xUpMnFAwdOt0l48WpokNyd8bUCbHlgsnxrXCMPYyzBTjJP417Oj4aehPWjmqF7RPyUfkot0SBDAJrso8E0MetuISXUWv8EBwe4cAFGA/gnKDyh4wQeI5NMCgYEA5dBxzVKtlJewl9b/kWlOdV8/f1AE6nRvwdGA/uWxZJKg00y5w3zOqNBcrXlLAYcNJl2zwZK+5z5bt0sB6L6JcfLL8JK+C5QPu8Blhx39kBXS+l6+5wZgHZN0jiIoz83b3TeJc5RsW23XAzZfiPMsy/ry7twfpEAV/bv7gXe+OvkCgYB/0fIQF4WegbGnkXmS1j4T/Zxh/VCKANXWct1Hiho/XDxUGasuZEZGVoIg40IkFzoXcSe9J3Mn9WXi2w/aniksrKL7iXGO5bWmvz9vw9kd+MOAeu6B2Ay6HpE3EiRuSIAsJp8tgeelmmiQwl3e4IqPXOaRROQCkDWs32eDhQD5AQKBgQCP79EHL2xmb+d8An3JPnKUktZ7T8v0aH8CnswzWfuUKT4veZOzu7zYAMC7Kgfvaf2zdOa8K8QDo4vgLkuA0a3WP6K6B76eE9sS1fsA+KiykOi2RZc9rUGP5KYQy5ZSKPY3BKzDRkfjr9UGPwzxApWhIIj9T8NFpZngdNeejDgKSQKBgQCHmwbu+UNf9zKqWLDJ40qzENqWITI3NkDU89yKXGR9KkNHo/naGTLfY+8ddRsFf058pYOI9Qn0BneCv6/9lSMBWmcH7RorIZJ1iXBnewWc2u1KP+nw+f0scvxqSfAJ149EV56LPysYeIXycJMrmBHxTRmKVUV7XaRWmugewxSXtg==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjrlZixn9LRsNhB8h6mgJNJD9oK8gFC/rddXdKb1YWhBreMIyULccLOpJ32DGMgKHnpVSHWEoN+cv1EZ+iQ+u5L3ZjobDJrMRjJdzfysfMg2/hiyYpiIfB/IzuLtpROE84MF9aCwsglVHYDjfhpQH757lnUAZyTAwFoyN4gWMRSqV3o9aH//4igIVy0apd76wJNV8gGcH9HZpz4Gj0Pq9vWyoRep9rij7my0AaaWJL1aiShNpK8i7VrWIf7dexv++AFRDfegORs+M37KdXff6XsdprzXTmqdc09e5hFo9UCblqYFkg+gAUf+HhV0BRQ0z9Bri9xr4Z7AU6LG4Yq/WwQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/hotel/jsp/return_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/hotel/jsp/return_url.jsp";
	//public static String return_url = "https://www.baidu.com";
	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8"; 
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";



    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

