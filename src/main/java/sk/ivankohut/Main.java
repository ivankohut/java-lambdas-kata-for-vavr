package sk.ivankohut;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Map;
import io.vavr.collection.Traversable;
import io.vavr.control.Option;

import java.util.function.Function;

public class Main {

    private Main() {
        // empty
    }

    public static Iterable<Person> peopleAtLeastYearsOld(Traversable<Person> people, int age) {
        return people.filter(person -> person.age() >= age);
    }

    public static Iterable<String> cityNames(Traversable<Person> people) {
        return people.map(person -> person.city().name()).toSet();
    }

    public static Option<Person> personByName(Traversable<Person> people, String name) {
        return people.find(person -> person.name().equals(name));
    }

    public static double averageAge(Traversable<Person> people) {
        return people.map(Person::age).average().getOrElseThrow(() -> new IllegalArgumentException("No people!"));
    }

    public static Map<City, Traversable<Person>> groupPeopleByCity(Traversable<Person> people) {
        return people.groupBy(Person::city).mapValues(Function.identity());
    }

    public static int indexOfTheOldestPerson(Traversable<Person> people) {
        return people.zipWithIndex().maxBy(personWithIndex -> personWithIndex._1.age()).map(Tuple2::_2).getOrElse(-1);
    }

    public static String commaSeparatedNames(Traversable<Person> people) {
        return people.map(Person::name).mkString(", ");
    }

    public static Tuple2<Traversable<Person>, Traversable<Person>> shortAndLongNamed(Traversable<Person> people, int nameLengthLimit) {
        return people.partition(person -> person.name().length() <= nameLengthLimit).map(Tuple::of);
    }

    public static Iterable<String> languagesInCity(Traversable<Person> people, City city) {
        return people.filter(person -> city.equals(person.city())).flatMap(Person::languages).distinct();
    }

    public static int sumOfAgesUsingFold(Traversable<Person> people) {
        return people.map(Person::age).fold(0, Integer::sum);
    }

    public static int sumOfAgesUsingFoldLeft(Traversable<Person> people) {
        return people.foldLeft(0, (sum, person) -> sum + person.age());
    }
}
