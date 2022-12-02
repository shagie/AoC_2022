package net.shagie.aoc.twentytwentytwo;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AOCDay;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@SpringBootApplication
@SuppressWarnings("java:S106")  // System.out.println is fine
public class TwentyTwentyTwo implements CommandLineRunner, ApplicationContextAware {
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(TwentyTwentyTwo.class, args);
    }

    @Override
    @Order(1)
    public void run(String... args) {
        Map<String, AOCDay> beans = applicationContext.getBeansOfType(AOCDay.class);

        beans.values().stream()
                .filter(d -> Optional.ofNullable(d.getClass().getAnnotation(AOC.class))
                        .filter(od -> od.day() == Integer.parseInt(args[0])).isPresent())
                .forEach(day -> {
                    System.out.println(day.getClass().getAnnotation(Component.class).value());
                    day.partOne();
                    day.partTwo();
                });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
