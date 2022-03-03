package fr.norsys;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class UserService {

    public List<User> getListFromArray(User[] userArray) {
        return Stream.of(userArray).collect(Collectors.toList());
    }

    public List<User> getUsersByCountryName(List<User> users, String countryName) {
        return users.stream().filter(user -> user.getAddress().getCountry().getName()
                .equals(countryName))
                .collect(Collectors.toList());
    }

    public List<String> getCountriesNameFromUsers(List<User> users) {
        return users.stream().map(user -> user.getAddress().getCountry().getName()).collect(Collectors.toList());
    }

    public List<String> getCountriesNameFromUsersDistinct(List<User> users) {
        return users.stream()
                .map(user -> user.getAddress().getCountry().getName())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Country> getCountriesFromUsersDistinct(List<User> users) {
        return users.stream().map(user -> user.getAddress().getCountry()).distinct().collect(Collectors.toList());
    }

    public List<String> getCountriesNameFromUsersSorted(List<User> users) {
        return users.stream()
                .map(user -> user.getAddress().getCountry().getName())
                .sorted()
                .collect(Collectors.toList());
    }

    public Boolean areAllUsersEmailsValid(List<User> users) {
        return users.stream().allMatch(user -> user.getEmail().contains("@"));
    }

    public Boolean areAllUsersEmailsNotValid(List<User> users) {
        return users.stream().noneMatch(user -> user.getEmail().contains("@"));
    }

    public Boolean areAtLeastOneUsersEmailsValid(List<User> users) {
        return users.stream().anyMatch(user -> user.getEmail().contains("@"));
    }

    public List<String> getCountriesNameFromUsersCustomSorted(List<User> users) {
        return users.stream()
                .map(user -> user.getAddress().getCountry().getName())
                .distinct()
                .sorted((o1, o2) -> {
                    if("Morocco".equals(o1))
                        return -1;
                    else if("Morocco".equals(o2))
                        return 1;
                    return o2.compareTo(o1);
                })
                .collect(Collectors.toList());    }

    public String getUserEmailCombined(List<User> users) {
        return users.stream()
                .filter(user -> user.getEmail().contains("@"))
                .map(user -> user.getEmail().replace("@norsys.fr",""))
                .reduce( "", (s, s2) -> {
                    if(!s.equals(""))
                       return s.concat(",").concat(s2);
                    return s.concat(s2);
                });
    }

    public Double getUserAverageAge(List<User> users) {
        return users.stream().mapToInt(value -> value.getAge()).average().orElseThrow(NoSuchElementException::new);
    }

    public Integer getSumOfUsersLoginHours(List<User> users) {
        return users.stream().mapToInt(value -> value.getLoginHours()).sum();
    }

    public Integer getMaxOfUsersLoginHours(List<User> users) {
        return users.stream().mapToInt(value -> value.getLoginHours()).max().orElseThrow(NoSuchElementException::new);
    }

    public Integer getMinOfUsersLoginHours(List<User> users) {
        return users.stream().mapToInt(value -> value.getLoginHours()).min().orElseThrow(NoSuchElementException::new);
    }

    public Map<String, List<User>> getGroupedUserByCountryName(List<User> users) {
        return users.stream().collect(Collectors.toMap(user -> user.getAddress().getCountry().getName(),
                o -> {
                 List list1 = new ArrayList<>();
                 list1.add(o);
                 return list1;
                },(o, o2) -> {
                  o.addAll(o2);
                  return o;
                }));
    }

    public Map<String, List<User>> getGroupedUserByCountryNameGrouping(List<User> users) {
        return users.stream()
                .collect(Collectors
                        .groupingBy(user -> user.getAddress().getCountry().getName()));
    }

    public String getUserEmailCombinedJoining(List<User> users) {
        return users.stream()
                .filter(user -> user.getEmail().contains("@"))
                .map(user -> user.getEmail().replace("@norsys.fr",""))
                .collect(Collectors.joining(","));
    }

    public Map<Boolean, List<User>> getPartitionedUserByAge(List<User> users, int age) {
        return users.stream().collect(Collectors.partitioningBy(user -> user.getAge()>=30));
    }

    public Map<String, Long> getGroupedUserByCountryNameNumber(List<User> users) {
        return users.stream().collect(Collectors.groupingBy(user ->user.getAddress().getCountry().getName() ,Collectors.counting()));
    }

    public Map<Boolean, Map<String, Long>> getPartitionedUserByAgeGroupedByCountry(List<User> users, int age) {
        return users.stream().collect(
                Collectors.partitioningBy(age>=30
                        ,Collectors.groupingBy(user ->user.getAddress().getCountry().getName() ,Collectors.counting())));
    }

    public Map<String, Integer> getCountriesWithSumOfLoginHours(List<User> users) {
        return users.stream().collect(Collectors.groupingBy(user ->user.getAddress().getCountry().getName()
                ,Collectors.reducing(0, user -> user.getLoginHours(), (o, o2) -> o+o2)));

    }

    public Map<String, List<String>> getUserEmailsByCountryName(List<User> users) {
        return users.stream().collect(Collectors.groupingBy(user ->user.getAddress().getCountry().getName(),
                Collectors.mapping(User::getEmail,Collectors.toList())));
    }
}
