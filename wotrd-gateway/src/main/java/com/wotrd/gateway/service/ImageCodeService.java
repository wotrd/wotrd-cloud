package com.wotrd.gateway.service;

import com.wotrd.gateway.constant.GatewayConstant;
import com.wotrd.gateway.utils.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ImageCodeService
 * @Description: 验证码服务
 * @Author: wotrd
 * @Date: 2020/8/30 14:34
 */
@Slf4j
@Service
public class ImageCodeService implements HandlerFunction<ServerResponse> {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        // 图片输出流
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        // 生成图片验证码
        BufferedImage image = CaptchaUtil.createImage();
        // 生成文字验证码
        String randomText = CaptchaUtil.drawRandomText(image);
        // 保存到验证码到 redis 有效期两分钟
        String t = request.queryParam("t").get();
        redisTemplate.opsForValue().set(GatewayConstant.WOTRD_IMAGE_CODE_KEY + t, randomText.toLowerCase(), 60, TimeUnit.MINUTES);
        try {
            ImageIO.write(image, "jpeg", os);
        } catch (IOException e) {
            log.error("验证码生成失败", e);
            return Mono.error(e);
        }
        return ServerResponse
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(BodyInserters.fromResource(new ByteArrayResource(os.toByteArray())));

    }
}