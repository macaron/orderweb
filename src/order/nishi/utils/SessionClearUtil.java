package order.nishi.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionClearUtil {
    public static void clearSession(HttpServletRequest request, String removeTarget) {
        HttpSession session = request.getSession();
        session.removeAttribute(removeTarget);
    }
}
