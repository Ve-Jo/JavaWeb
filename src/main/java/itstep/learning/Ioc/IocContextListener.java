package itstep.learning.Ioc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class IocContextListener extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
            new ServiceModule(),
            new WebModule()
        );
    }
}

/*
* Веб-проєкт передбачає циркулювання подій змін життєвого циклу
* та підписування на них.
* */