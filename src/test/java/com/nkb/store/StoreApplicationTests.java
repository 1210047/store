package com.nkb.store;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest

class StoreApplicationTests {

    @Test
    void contextLoads() {
        long count = 2000;
        Long a1 = new Long(1);
        int countint = Math.toIntExact(a1);

        System.out.println(count);
        System.out.println(a1);
        System.out.println(countint);
        System.out.println(a1==0);
    }

}
