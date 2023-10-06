package sk.ivankohut;

import io.vavr.Tuple;
import io.vavr.collection.Array;
import io.vavr.collection.Traversable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

class MainTest {

    private static final City NEW_YORK = new City("New York", "USA");
    private static final City LOS_ANGELES = new City("Los Angeles", "USA");
    private static final City LONDON = new City("London", "UK");

    private static final Person ALICE = new Person("Alice", 25, NEW_YORK, Array.of("English", "French"));
    private static final Person BOB = new Person("Bob", 30, LOS_ANGELES, Array.of("English"));
    private static final Person CHARLIE = new Person("Charlie", 28, NEW_YORK, Array.of("English", "German"));
    private static final Person DAVID = new Person("David", 35, LONDON, Array.of("Dutch", "German"));
    private static final Person EVE = new Person("Eve", 22, LOS_ANGELES, Array.of("French", "English", "Spanish"));

    private static final Traversable<Person> PEOPLE = Array.of(ALICE, BOB, CHARLIE, DAVID, EVE);

    @Test
    void peopleAtLeastYearsOld() {
        var result = Main.peopleAtLeastYearsOld(PEOPLE, 30);
        // verify
        assertThat(result).containsExactly(BOB, DAVID);
    }

    @Test
    void cityNames() {
        var result = Main.cityNames(PEOPLE);
        // verify
        assertThat(result).containsExactlyInAnyOrder(LONDON.name(), NEW_YORK.name(), LOS_ANGELES.name());
    }

    @Test
    void personByName() {
        var result = Main.personByName(PEOPLE, "Alice");
        // verify
        assertThat(result).containsExactly(ALICE);
    }

    @Test
    void averageAge() {
        var result = Main.averageAge(PEOPLE);
        // verify
        assertThat(result).isEqualTo(28.0, within(0.01));
    }

    @Test
    void averageAge_exceptionIfNoPeople() {
        var nobody = Array.<Person>empty();
        // exercise & verify
        assertThatThrownBy(() -> Main.averageAge(nobody)).isInstanceOf(IllegalArgumentException.class).hasMessage("No people!");
    }

    @Test
    void groupPeopleByCity() {
        var result = Main.groupPeopleByCity(PEOPLE);
        // verify
        assertThat(result).containsExactlyInAnyOrder(
                Tuple.of(NEW_YORK, Array.of(ALICE, CHARLIE)),
                Tuple.of(LOS_ANGELES, Array.of(BOB, EVE)),
                Tuple.of(LONDON, Array.of(DAVID))
        );
    }

    @Test
    void indexOfTheOldestPerson() {
        var result = Main.indexOfTheOldestPerson(PEOPLE);
        // verify
        assertThat(result).isEqualTo(3);
    }

    @Test
    void indexOfTheOldestPerson_minusOneIfNoPerson() {
        var result = Main.indexOfTheOldestPerson(Array.empty());
        // verify
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void commaSeparatedNames() {
        var result = Main.commaSeparatedNames(PEOPLE);
        // verify
        assertThat(result).isEqualTo("Alice, Bob, Charlie, David, Eve");
    }

    @Test
    void shortAndLongNamed() {
        var result = Main.shortAndLongNamed(PEOPLE, 3);
        // verify
        assertThat(result._1).containsExactly(BOB, EVE);
        assertThat(result._2).containsExactly(ALICE, CHARLIE, DAVID);
    }

    @Test
    void languagesInCity() {
        var result = Main.languagesInCity(PEOPLE, NEW_YORK);
        // verify
        assertThat(result).containsExactlyInAnyOrder("English", "French", "German");
    }

    @Test
    void sumOfAgesUsingFold() {
        var result = Main.sumOfAgesUsingFold(PEOPLE);
        // verify
        assertThat(result).isEqualTo(140);
    }

    @Test
    void sumOfAgesUsingFoldLeft() {
        var result = Main.sumOfAgesUsingFoldLeft(PEOPLE);
        // verify
        assertThat(result).isEqualTo(140);
    }
}
