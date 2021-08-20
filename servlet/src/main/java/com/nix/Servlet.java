package com.nix;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "sample-servlet", urlPatterns = "/sample")
public class Servlet extends HttpServlet {

    private static final long serialVersionUID = -8948379822734246956L;

    private static final List<String> userAgentList = new ArrayList<>();

    private static final Logger log = LoggerFactory.getLogger(Servlet.class);

    @Override
    public void init() {
        log.info(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter responseBody = resp.getWriter();

        String val = req.getRemoteHost() + " :: " + req.getHeader("User-Agent");
        userAgentList.add(val);

        resp.setContentType("text/html");
        responseBody.println("<h1 align=\"center\">List of users</h1>");

        for (int i = 0, userAgentListSize = userAgentList.size(); i < userAgentListSize; i++) {
            if (i == userAgentList.size() - 1) {
                responseBody.println("<p align=\"center\"><b>" + i + " | " + userAgentList.get(i) + "</b></p>");
            } else {
                responseBody.println("<p align=\"center\">" + i + " | " + userAgentList.get(i) + "</p>");
            }
        }
    }

    @Override
    public void destroy() {
        log.info(getServletName() + " destroyed");
    }
}
