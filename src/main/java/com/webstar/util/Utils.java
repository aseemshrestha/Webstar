package com.webstar.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

public class Utils
{

    public static String getClientIp(HttpServletRequest request)
    {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    public static String upperCaseFirst(String input)
    {
        if (!checkIfStringIsNullOrEmpty(input)) {
            char[] chars = input.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            return new String(chars);
        }
        return input;

    }

    public static boolean emailValidator(String email)
    {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePhoneNumber(String phoneNo)
    {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}"))
            return true;
        //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
            return true;
        //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
            return true;
        //validating phone number where area code is in braces ()
        else //return false if nothing matches the input
            return phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}");

    }

    private static boolean checkIfStringIsNullOrEmpty(String input)
    {
        return input == "" || input == null;
    }

    public static <T> T parseJSONFromUrl(String content, Class<T> cl) throws IOException
    {
        ObjectMapper mapper = JacksonMapper.INSTANCE.getObjectMapper();
        URL jsonUrl = new URL(content);
        return mapper.readValue(jsonUrl, cl);
    }

    public static <T> T parseJSONLocal(String content, Class<T> cl) throws IOException
    {
        ObjectMapper mapper = JacksonMapper.INSTANCE.getObjectMapper();
        return mapper.readValue(content, cl);
    }

    public static <T> T parseJsonAsStream(InputStream input, Class<T> cl)
        throws IOException
    {
        ObjectMapper mapper = JacksonMapper.INSTANCE.getObjectMapper();
        return mapper.readValue(input, cl);
    }

    public static InputStream getResourceAsStream(String fileName) throws IOException
    {
        Resource resource = new ClassPathResource(fileName);
        return resource.getInputStream();

    }

    public static String uploadFile(String imagePath, HttpServletRequest request, MultipartFile file) throws Exception{
        File fileSaveDir = new File(imagePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        String absPath = request.getServletContext().getRealPath("/");
        byte[] bytes = file.getBytes();
        Path path = Paths.get(absPath + imagePath + file.getOriginalFilename());
       // usercomments.setImageUrl(path.toString());
        Files.write(path, bytes);
        return path.toString();
    }
}
