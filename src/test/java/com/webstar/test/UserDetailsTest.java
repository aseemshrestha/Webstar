package com.webstar.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith( SpringJUnit4ClassRunner.class )
@WebAppConfiguration
@SpringBootConfiguration

public class UserDetailsTest
{

    @Test
    public void dummyTest()
    {
        int i = 10;
        assertEquals(10, i);
    }

}
