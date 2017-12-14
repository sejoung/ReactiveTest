package com.github.sejoung.reactive.cookies;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;

public class CookiesTest {
    public static void main(String[] args) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        // 아래의 쿠키 데이터를 많이 넣어서 사이즈를 늘리면서 테스트가 가능함
        headers.add("Cookie", "TEST=1" );
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/rest", HttpMethod.GET, new HttpEntity<String>(headers), String.class);

        System.out.println(response.getStatusCode());

    }
}
