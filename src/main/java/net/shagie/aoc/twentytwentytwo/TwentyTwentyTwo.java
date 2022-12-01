package net.shagie.aoc.twentytwentytwo;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AOCMarker;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;

import java.util.Map;

@SpringBootApplication
@SuppressWarnings("java:S106")  // System.out.println is fine
public class TwentyTwentyTwo implements CommandLineRunner, ApplicationContextAware {
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(TwentyTwentyTwo.class, args);
    }

    @Override
    @Order(1)
    public void run(String... args) throws Exception {
        Map<String, AOCMarker> beans = applicationContext.getBeansOfType(AOCMarker.class);
        
        beans.values().stream()
                .filter(day -> day.getClass().getAnnotation(AOC.class).day() == Integer.parseInt(args[0]))
                .findFirst()
                .orElse(args1 -> System.out.println("No day found"))
                .run(args)
                ;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
