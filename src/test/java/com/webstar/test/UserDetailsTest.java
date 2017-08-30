package com.webstar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.webstar.util.Security;

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
        String pass = Security.SALT.concat(Security.generateHash("aseem"));
        String pass1 = Security.SALT.concat(Security.generateHash("aseem"));
        assertEquals(pass,pass1);
       // InputStream input = getResourceAsStream("cateogry.json");
       // assertNotNull(input);
        
    }
    private InputStream getResourceAsStream(String fileName)
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream(fileName);
        return input;

    }
}
