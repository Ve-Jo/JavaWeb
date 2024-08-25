package itstep.learning.Ioc;

import com.google.inject.servlet.ServletModule;
import itstep.learning.filters.*;
import itstep.learning.servlets.*;

public class WebModule extends ServletModule {
    @Override
    protected void configureServlets() {
        filter("/*").through(CharsetFilter.class);
        serve("/").with(HomeServlet.class);
        serve("/db").with(DbServlet.class);

    }
}

/*
* Модуль конфігурації веб-сутностей (сервлетів, фільтрів тощо)
* Він надає трерій варіант реєстрації фільтрів та сервлетів. Для ньотго
* необхідно додати для всіх класів фільтрів та сервлетів анотацію Singleton
* а також знімаємо інші форми реєстрації (анотації чи web.xml)
* */