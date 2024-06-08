package com.example.employeeoffice.controller.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
//public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        response.setContentType("text/html; charset=UTF-8");
//
//        String htmlResponse = "<html>" +
//                "<head>" +
//                "<style>" +
//                "body { background-color: #f8d7da; color: #721c24; font-family: Arial, sans-serif; text-align: center; padding: 50px; }" +
//                ".message { font-size: 24px; margin-top: 20px; }" +
//                "</style>" +
//                "</head>" +
//                "<body>" +
//                "<h1>🚫 Access Denied :</h1>" +
//                "<div class='message'>No Access Rights.</div>" +
//                "</body>" +
//                "</html>";
//
//        response.getWriter().write(htmlResponse);
//    }
//}

