package com.github.sejoung.reactive.test;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "14.0.92.196:7214");
        props.put("retries", 2);        // 재시도 횟수
        props.put("batch.size", 8000);    // 크기기반 일괄처리
        props.put("linger.ms", 10);    // 시간기반 일괄처리
        props.put("buffer.memory", 4800000); // 전송대기 버퍼
        props.put("max.request.size", 8000);    // 요청 최대크기
        props.put("min.insync.replicas", 2); // 최소 복제
        props.put("enable.idempotence", true); // 데이터 중복 개선.
        props.put("max.in.flight.requests.per.connection", 1); // 수신 미확인 요청의 최대 수

        props.put("compression.type", "gzip");
        props.put("acks", "all");
        props.put("request.timeout.ms", 5000);

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer producer = new KafkaProducer<>(props);

        AtomicInteger ai = new AtomicInteger(1);
        for (int i = 0; i < 3000; i++) {

            new Thread(() -> {
                try {

                    int index = ai.getAndIncrement();

                    ProducerRecord<Integer, String> record =new ProducerRecord<>("test", index,"Hello Mom " + index);
                    System.out.println("test "+index);
                    Future<RecordMetadata> rt = producer.send(record);


                    if(rt.isDone()) {
                        System.out.println(rt.get().topic());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
