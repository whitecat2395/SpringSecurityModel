package com.zhou.demo.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {
    public static String renderString(HttpServletResponse response,String jsonStr){
        try{
            response.setStatus(200);
            response.setContentType("application/json");
            response.getWriter().print(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
