package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class HellobootApplication {

    @Bean
    HelloService helloService() {
        return new SimpleHelloService();
    }

    @Bean
    HelloController helloController(HelloService helloService) {
        return new HelloController(helloService);
    }

    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();
                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(
                    servletContext -> {
                        servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this))
                                      .addMapping("/*");
                    });
                webServer.start();
            }
        };
        applicationContext.register(HellobootApplication.class);
        applicationContext.refresh(); // 템플릿 메소드 방식임, 상속해서 hook 메소드를 넣어주는 방식
    }
}