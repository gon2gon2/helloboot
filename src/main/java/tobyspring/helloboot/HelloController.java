package tobyspring.helloboot;

import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;

public class HelloController {
    private final HelloService helloService;

    public HelloController(SimpleHelloService helloService) {this.helloService = helloService;}

    @GetMapping("/hello")
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
