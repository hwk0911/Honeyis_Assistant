package com.cafecoder.tistory.files;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDataTest {

    @Test
    public void testToString() {
        List<String> testData = new ArrayList<>();
        String answer =
                "2020-05-20 22:15 [찰떡핏/기본템]도톰 브이넥 반팔니트 소라 free 1";

        testData.add("2020-05-20 22:15");
        testData.add("[찰떡핏/기본템]도톰 브이넥 반팔니트");
        testData.add("소라/free");
        testData.add("1");

        //OrderData orderData = new OrderData(testData);

        //assertThat(orderData.toString()).isEqualTo(answer);
        //System.out.println(orderData.getDateValue());
    }
}