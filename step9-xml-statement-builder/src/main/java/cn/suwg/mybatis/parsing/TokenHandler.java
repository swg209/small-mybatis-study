package cn.suwg.mybatis.parsing;

/**
 * 记号处理器.
 *
 * @Author: suwg
 * @Date: 2025/1/14
 */
public interface TokenHandler {

    String handleToken(String content);
}
