package itstep.learning.Ioc;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import itstep.learning.services.*;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind( HashService.class )
                .annotatedWith( Names.named("digest") )
                .to( Md5HashService.class );
        bind( HashService.class )
                .annotatedWith( Names.named("signature") )
                .to( ShaHashService.class );
        bind( String.class )
                .annotatedWith( Names.named("viewsFolder") )
                .toInstance("views");
        bind( String.class )
                .annotatedWith( Names.named("resourcesFolder") )
                .toInstance("resources");

        bind(RandomStringGenerator.class)
                .annotatedWith(Names.named("fileName"))
                .to(FileNameGenerator.class);
        bind(RandomStringGenerator.class)
                .annotatedWith(Names.named("cryptoSalt"))
                .to(CryptoSaltGenerator.class);
        bind(RandomStringGenerator.class)
                .annotatedWith(Names.named("otp"))
                .to(OtpGenerator.class);
        bind(RandomStringGenerator.class)
                .annotatedWith(Names.named("password"))
                .to(PasswordGenerator.class);
    }
}

/*
* Модуль реєстрації сервісів (служб) універсального типу (не тільки веб-призначення)
*
* */