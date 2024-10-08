package itstep.learning.filters;

import com.google.inject.Singleton;

import javax.servlet.*;
import java.io.IOException;

@Singleton
public class CharsetFilter implements Filter {
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Прямий хід (від сервера до сервлета)
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("Filter works");
        // Передача роботи наступному фільтру / сервлету
        chain.doFilter(request, response);
        // Зворотній хід (від JSP до сервера)
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}

/*
* Фільтри (сервлетні фільтри) - реалізація концепції "Middleware"
* послідовного проходження блоків ПЗ (об'єктів) на шляху оброблення
* запиту і формування відповіді
* Фільтр має три методи життєвого циклу
* init - cтворення фільтру (об'єкта класу)
*
* */