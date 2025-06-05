import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;


// http://localhost:8080/time?timezone=UTC%2B02:00 риклад запиту



@WebServlet(value = "/time")
    public class ServletTimeCookie extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
            resp.setContentType("text/html");
            try {
                PrintWriter writer = resp.getWriter();
                writer.write(getTimeByParameter(req, resp));
                writer.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        private String getTimeByParameter(HttpServletRequest req, HttpServletResponse resp) {
            String timeParameter = req.getParameter("timezone");
            if (timeParameter == null) {
                String timeZone= getCookiesTimezone(req);
                if( timeZone.equals("")){
                    return ServiceTime.getTimeUTC();
                }
                return ServiceTime.getTimeByZoneId(timeZone);
            } else {
                if (ServiceTime.checkZoneIdExist(timeParameter)) {
                    Cookie cookieTimeZone = new Cookie("timezone", timeParameter);
                    resp.addCookie(cookieTimeZone);
                    return ServiceTime.getTimeByZoneId(timeParameter);
                }
                return "The entered time zone does not exist.";

            }
        }

        private String getCookiesTimezone (HttpServletRequest req) {
            Cookie[] cookies = req.getCookies();
            String timeZone="";
            if (cookies.length == 0) {
                return timeZone;
            } else {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("timezone") && cookie.getValue() != null) {
                        timeZone = cookie.getValue();
                        return  timeZone;
                    }
                }
                return timeZone;
            }
        }
    }






