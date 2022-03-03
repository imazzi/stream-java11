package fr.norsys;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest {

    private UserService userService;

    private List<User> users = new ArrayList<>();


    @Before
    public void setUp() {
        userService = new UserService();
        users.addAll(
                Arrays.asList(
                        new User("user1@norsys.fr", new Address(new Country("Morocco")), 22, 500),
                        new User("user2@norsys.fr", new Address(new Country("France")), 30, 858),
                        new User("user3@norsys.fr", new Address(new Country("Portugal")), 26, 963),
                        new User("user4@norsys.fr", new Address(new Country("USA")), 33, 875),
                        new User("user5@norsys.fr", new Address(new Country("France")), 21, 1050),
                        new User("user6@norsys.fr", new Address(new Country("Portugal")), 35, 993),
                        new User("user7-norsys.fr", new Address(new Country("Canada")), 60, 888)
                )
        );
    }

    /**
     * Stream.of / Collect
     * create a method that create a stream from an user array and collects it to a list
     */

    @Test
    public void shouldReturnAListFromArray() {

        User[] userArray = {new User("user1@norsys.fr", new Address(new Country("Morocco")), 22, 500),
                new User("user2@norsys.fr", new Address(new Country("France")), 30, 858),
                new User("user3@norsys.fr", new Address(new Country("Portugal")), 26, 963)};
        assertThat(userService.getListFromArray(userArray).size()).isEqualTo(3);
    }

    /**
     * filter / Collect
     * create a method that filters users list by country name
     */

    @Test
    public void shouldReturnAFilteredList() {
        List<User> result = userService.getUsersByCountryName(users, "France");
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).anyMatch(user -> user.getEmail().equals("user2@norsys.fr"));
        assertThat(result).anyMatch(user -> user.getEmail().equals("user5@norsys.fr"));
    }

    /**
     * Map / Collect
     * create a method that returns List of countries name from users list
     */

    @Test
    public void shouldReturnAListOfCountriesNameFromListOfUsers() {
        List<String> countries = userService.getCountriesNameFromUsers(users);
        assertThat(countries.size()).isEqualTo(7);
    }


    /**
     * distinct
     * creates a method that returns list of countries name distinct (with no duplicates)
     */

    @Test
    public void shouldReturnAListOfCountriesNameFromListOfUsersDistinct() {
        List<String> countries = userService.getCountriesNameFromUsersDistinct(users);
        assertThat(countries.size()).isEqualTo(5);
    }


    /**
     * distinct
     * creates a method that returns list of countries distinct (with no duplicates)
     */

    @Test
    public void shouldReturnAListOfCountriesFromListOfUsersDistinct() {
        List<Country> countries = userService.getCountriesFromUsersDistinct(users);
        assertThat(countries.size()).isEqualTo(5);
    }


    /**
     * All Match
     * create a method that returns true if ALL users emails are valid
     * else returns false
     */

    @Test
    public void shouldReturnTrueifAllUsersEmailAreValid() {
        assertThat(userService.areAllUsersEmailsValid(users)).isFalse();
    }

    /**
     * None Match
     * create a method that returns true if NONE of users emails are valid
     * else return false
     */

    @Test
    public void shouldReturnTrueIfNoneUsersEmailAreValid() {
        assertThat(userService.areAllUsersEmailsNotValid(users)).isFalse();
    }

    /**
     * Any Match
     * create a method that returns true if there is AT LEAST one valid email
     * else returns false
     */

    @Test
    public void shouldReturnTrueIfAtLeastOneUsersEmailAreValid() {
        assertThat(userService.areAtLeastOneUsersEmailsValid(users)).isTrue();
    }

    /**
     * Sorted
     * create a method that returns a sorted of countries names from users list
     */

    @Test
    public void shouldReturnAListOfCountriesNameFromListOfUsersSorted() {
        List<String> countries = userService.getCountriesNameFromUsersSorted(users);
        assertThat(countries).isSorted();
    }

    /**
     * distinct / Custom Sort
     * create a method that returns a non duplicate
     * sorted with Morocco being the first element and the rest sorted in the opposite of natural sort
     */

    @Test
    public void shouldReturnAListOfCountriesNameFromListOfUserCustomSorted() {
        List<String> countries = userService.getCountriesNameFromUsersCustomSorted(users);
        assertThat(countries.size()).isEqualTo(5);
        assertThat(countries.get(0)).isEqualTo("Morocco");
        assertThat(countries.get(1)).isEqualTo("USA");
        assertThat(countries.get(2)).isEqualTo("Portugal");
        assertThat(countries.get(3)).isEqualTo("France");
        assertThat(countries.get(4)).isEqualTo("Canada");
    }

    /**
     * Reduce
     * create a method that returns a concat of users email
     */

    @Test
    public void shouldReturnAConcatOfUsersEmail() {
        String expectedResult = "user1,user2,user3,user4,user5,user6";
        assertThat(userService.getUserEmailCombined(users)).isEqualTo(expectedResult);
    }

    /**
     * IntStream
     * average
     * create a method that returns the average of users age
     * throws NoSuchElementException if there is no element is the main stream
     */

    @Test
    public void shouldReturnAverageUsersAge() {
        assertThat(userService.getUserAverageAge(users)).isEqualTo(32.42857142857143);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenNoAverageIsFound() {
        userService.getUserAverageAge(new ArrayList<>());
    }

    /**
     * IntStream
     * sum
     * create a method that returns the sum of users login hours
     */

    @Test
    public void shouldReturnSumOfUsersLoginHours() {
        assertThat(userService.getSumOfUsersLoginHours(users)).isEqualTo(6127);
    }


    @Test
    public void shouldReturn0LoginHoursIfListIsEmpty() {
        assertThat(userService.getSumOfUsersLoginHours(new ArrayList<>())).isEqualTo(0);
    }

    /**
     * IntStream
     * max
     * create a method that returns the max login hours within users
     * throws NoSuchElementException if there is no element is the main stream
     */

    @Test
    public void shouldReturnMaxOfUsersLoginHours() {
        assertThat(userService.getMaxOfUsersLoginHours(users)).isEqualTo(1050);
    }


    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionMaxLoginHours() {
        userService.getMaxOfUsersLoginHours(new ArrayList<>());
    }

    /**
     * IntStream
     * min
     * create a method that returns the min login hours within users
     * throws NoSuchElementException if there is no element is the main stream
     */

    @Test
    public void shouldReturnMinOfUsersLoginHours() {
        assertThat(userService.getMinOfUsersLoginHours(users)).isEqualTo(500);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowsExceptionMinLoginHours() {
        userService.getMinOfUsersLoginHours(new ArrayList<>());
    }


    /**
     * Collectors
     * Collectors to Map
     * create a method that returns a MAP of with country name as key
     * and it's list of users as value
     */

    @Test
    public void shouldReturnMapOfUsersGroupedByCountryName() {
        Set set = new TreeSet(Arrays.asList("Morocco", "France", "Portugal", "USA", "Canada"));
        Map<String, List<User>> groupedUsers = userService.getGroupedUserByCountryName(users);
        assertThat(groupedUsers).hasSize(5);
        assertThat(groupedUsers.keySet()).isEqualTo(set);
        assertThat(groupedUsers.get("Morocco")).hasSize(1);
        assertThat(groupedUsers.get("France")).hasSize(2);
        assertThat(groupedUsers.get("Portugal")).hasSize(2);
        assertThat(groupedUsers.get("USA")).hasSize(1);
        assertThat(groupedUsers.get("Canada")).hasSize(1);
    }

    /**
     * Collectors
     * Grouping
     * create a method that returns a MAP of with country name as key
     * and it's list of users as value
     */

    @Test
    public void shouldReturnMapOfUsersGroupedByCountryNameGrouping() {
        Set set = new TreeSet(Arrays.asList("Morocco", "France", "Portugal", "USA", "Canada"));
        Map<String, List<User>> groupedUsers = userService.getGroupedUserByCountryNameGrouping(users);
        assertThat(groupedUsers).hasSize(5);
        assertThat(groupedUsers.keySet()).isEqualTo(set);
        assertThat(groupedUsers.get("Morocco")).hasSize(1);
        assertThat(groupedUsers.get("France")).hasSize(2);
        assertThat(groupedUsers.get("Portugal")).hasSize(2);
        assertThat(groupedUsers.get("USA")).hasSize(1);
        assertThat(groupedUsers.get("Canada")).hasSize(1);

    }

    /**
     * Collectors
     * joining
     * create a method that returns a concat of users email
     */

    @Test
    public void shouldReturnAConcatOfUsersEmailJoining() {
        String expectedResult = "user1,user2,user3,user4,user5,user6";
        assertThat(userService.getUserEmailCombinedJoining(users)).isEqualTo(expectedResult);
    }

    /**
     * Collectors
     * Partitioning By
     * create a method that returns a MAP with true as key and list of users older(orEqual) than 30 YO as value
     * and false as key and list of users younger than 30 YO
     */

    @Test
    public void shouldReturnMapOfBooleanByAge() {
        Map<Boolean, List<User>> partitionedUsers = userService.getPartitionedUserByAge(users, 30);
        assertThat(partitionedUsers).hasSize(2);
        assertThat(partitionedUsers.get(true)).hasSize(4);
        assertThat(partitionedUsers.get(false)).hasSize(3);
    }

    /**
     * Collectors
     * Counting
     * create a method that returns a MAP with country name as key and NUMBER of it's users as value
     */

    @Test
    public void shouldReturnMapOfUsersGroupedByCountryNameAndNumberOfUsers() {
        Map<String, Long> groupedUsers = userService.getGroupedUserByCountryNameNumber(users);
        assertThat(groupedUsers).hasSize(5);
        assertThat(groupedUsers.get("Morocco")).isEqualTo(1);
        assertThat(groupedUsers.get("France")).isEqualTo(2);
        assertThat(groupedUsers.get("Portugal")).isEqualTo(2);
        assertThat(groupedUsers.get("USA")).isEqualTo(1);
        assertThat(groupedUsers.get("Canada")).isEqualTo(1);
    }


    /**
     * Collectors
     * reducing
     * create a method that returns a MAP With country name as key and SUM of it's users login hours as value
     */

    @Test
    public void shouldGetMapOfCountriesAndThereSumOfLoginHours() {
        Map<String, Integer> result = userService.getCountriesWithSumOfLoginHours(users);
        assertThat(result).hasSize(5);
        assertThat(result.get("Morocco")).isEqualTo(500);
        assertThat(result.get("France")).isEqualTo(1908);
        assertThat(result.get("Portugal")).isEqualTo(1956);
        assertThat(result.get("USA")).isEqualTo(875);
        assertThat(result.get("Canada")).isEqualTo(888);
    }

    /**
     * Collectors
     * Mapping
     * create a method that returns a MAP with country name as key and LIST of it's users emails as value
     */

    @Test
    public void shouldGetMapOfCountriesAndListOfEmails() {
        Map<String, List<String>> result = userService.getUserEmailsByCountryName(users);
        System.out.println(result);
        assertThat(result).hasSize(5);
        assertThat(result.get("Canada")).isEqualTo(Arrays.asList("user7-norsys.fr"));
        assertThat(result.get("USA")).isEqualTo(Arrays.asList("user4@norsys.fr"));
        assertThat(result.get("Morocco")).isEqualTo(Arrays.asList("user1@norsys.fr"));
        assertThat(result.get("France")).isEqualTo(Arrays.asList("user2@norsys.fr", "user5@norsys.fr"));
        assertThat(result.get("Portugal")).isEqualTo(Arrays.asList("user3@norsys.fr", "user6@norsys.fr"));
    }

    /**
     * Use above tools to solve this one
     */

    @Test
    public void shouldReturnMapOfUserPartitionedByAgeGroupedCountry() {
        Map<Boolean, Map<String, Long>> result = userService.getPartitionedUserByAgeGroupedByCountry(users, 30);
        assertThat(result.get(false)).hasSize(3);
        assertThat(result.get(false).get("Morocco")).isEqualTo(1);
        assertThat(result.get(false).get("France")).isEqualTo(1);
        assertThat(result.get(false).get("Portugal")).isEqualTo(1);
        assertThat(result.get(true)).hasSize(4);
        assertThat(result.get(true).get("Canada")).isEqualTo(1);
        assertThat(result.get(true).get("USA")).isEqualTo(1);
        assertThat(result.get(true).get("France")).isEqualTo(1);
        assertThat(result.get(true).get("Portugal")).isEqualTo(1);

    }


}

