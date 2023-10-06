package sk.ivankohut;

import lombok.Data;

@Data
public class Person {

    private final String name;
    private final int age;
    private final City city;
    private final Iterable<String> languages;
}
