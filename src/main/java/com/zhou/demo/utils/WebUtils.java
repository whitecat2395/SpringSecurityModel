package com.zhou.demo.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;

public class WebUtils {
    public static String renderString(HttpServletResponse response,String jsonStr){
        try{
            response.setStatus(200);
            response.setContentType("application/json");
            response.getWriter().print(jsonStr);
            response.reset();//（清空缓冲区）
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
