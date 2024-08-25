package itstep.learning.servlets;

import com.google.inject.Singleton;
import com.google.inject.name.Named;
import itstep.learning.services.HashService;
import itstep.learning.services.RandomStringGenerator;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class HomeServlet extends HttpServlet {
    private final HashService digestHashService;
    private final HashService signatureHashService;
    private final String viewsFolder;
    private final String resourcesFolder;
    private final RandomStringGenerator fileNameGenerator;
    private final RandomStringGenerator cryptoSaltGenerator;
    private final RandomStringGenerator otpGenerator;
    private final RandomStringGenerator passwordGenerator;

    @Inject
    public HomeServlet(
            @Named("digest") HashService digestHashService,
            @Named("signature") HashService signatureHashService,
            @Named("viewsFolder") String viewsFolder,
            @Named("resourcesFolder") String resourcesFolder,
            @Named("fileName") RandomStringGenerator fileNameGenerator,
            @Named("cryptoSalt") RandomStringGenerator cryptoSaltGenerator,
            @Named("otp") RandomStringGenerator otpGenerator,
            @Named("password") RandomStringGenerator passwordGenerator
    ) {
        this.digestHashService = digestHashService;
        this.signatureHashService = signatureHashService;
        this.viewsFolder = viewsFolder;
        this.resourcesFolder = resourcesFolder;
        this.fileNameGenerator = fileNameGenerator;
        this.cryptoSaltGenerator = cryptoSaltGenerator;
        this.otpGenerator = otpGenerator;
        this.passwordGenerator = passwordGenerator;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // ViewData["fromServlet"] = "HomeServlet"
        String fromServlet = digestHashService.digest("123") + " " + signatureHashService.digest("123") + " " + viewsFolder + " " + resourcesFolder + "<br>" +
                             "File Name: " + fileNameGenerator.generate() + "<br>" +
                             "Crypto Salt: " + cryptoSaltGenerator.generate() + "<br>" +
                             "OTP: " + otpGenerator.generate() + "<br>" +
                             "Password: " + passwordGenerator.generate();
        
        req.setAttribute("fromServlet", fromServlet);
        req.setAttribute("pageBody", "index.jsp");
        // return View("index.jsp")
        req.getRequestDispatcher("WEB-INF/views/_layout.jsp").forward(req, resp);
    }

}

/*
* Сервлети - спеціалізовані класи для мережних задач.
* У веб-проєктах грають роль контролерів (API-контролерів)
* Для підключення сервлету потрібно
* або зареєструвати його у web.xml
* або додати сервлетну анотацію
* або зареєструвати його у службі інжекції
*
* Д.З Реалізувати інжекцію генераторів випадкових рядків кількох типів
* - для імені файлу (символи нижнього реєстру, не містять спец.символів, без символів "/*?. ..\"
* - для криптографічної солі - без обмежень (навіть юнікод символи)
* - для OTP (one time password) - тільки цифри з довжиною 6 символів
* - для постійних паролів - те, що можно набрати з клавіатури (регістр, спец.символи)
* потрібно дотримуватись стилю та поведінки уже написаних в програмі сервісів
* */