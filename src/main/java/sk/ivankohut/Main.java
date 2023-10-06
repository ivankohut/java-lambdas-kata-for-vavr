package sk.ivankohut;

import io.vavr.Tuple2;
import io.vavr.collection.Map;
import io.vavr.collection.Traversable;
import io.vavr.control.Option;

public class Main {

    private Main() {
        // empty
    }

    public static Iterable<Person> peopleAtLeastYearsOld(Traversable<Person> people, int age) {
        return null;
    }

    public static Iterable<String> cityNames(Traversable<Person> people) {
        return null;
    }

    public static Option<Person> personByName(Traversable<Person> people, String name) {
        return null;
    }

    public static double averageAge(Traversable<Person> people) {
        return 0;
    }

    public static Map<City, Traversable<Person>> groupPeopleByCity(Traversable<Person> people) {
        return null;
    }

    public static int indexOfTheOldestPerson(Traversable<Person> people) {
        return 0;
    }

    public static String commaSeparatedNames(Traversable<Person> people) {
        return null;
    }

    public static Tuple2<Traversable<Person>, Traversable<Person>> shortAndLongNamed(Traversable<Person> people, int nameLengthLimit) {
        return null;
    }

    public static Iterable<String> languagesInCity(Traversable<Person> people, City city) {
        return null;
    }

    public static int sumOfAgesUsingFold(Traversable<Person> people) {
        return 0;
    }

    public static int sumOfAgesUsingFoldLeft(Traversable<Person> people) {
        return 0;
    }
}
