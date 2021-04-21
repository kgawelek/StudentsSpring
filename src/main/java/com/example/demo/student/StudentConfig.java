package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
           Student konrad =  new Student(
                    "Konrad",
                    LocalDate.of(2000, Month.JULY, 17),
                    "konrad@gmail.com"
            );

            Student jan =  new Student(
                    "Jan",
                    LocalDate.of(1990, Month.JULY, 10),
                    "jan@gmail.com"
            );

            repository.saveAll(
                    List.of(konrad, jan)
            );
        };
    }
}
