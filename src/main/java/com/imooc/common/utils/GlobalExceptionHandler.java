package com.imooc.common.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ArithmeticException.class)
    public void arithmeticException(ArithmeticException e, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset-utf-8");
        PrintWriter out = response.getWriter();
        out.write(e.getMessage().toString());
        System.out.println("算术运算错误！");
        out.flush();
        out.close();
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public void indexOutOfBoundsException(IndexOutOfBoundsException e, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset-utf-8");
        PrintWriter out = response.getWriter();
        out.write(e.getMessage().toString());
        System.out.println("数组下标索引越界！");
        out.flush();
        out.close();
    }
}
