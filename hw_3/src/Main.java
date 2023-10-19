import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = List.of(new Person("denys", 9), new Person("bob", 18), new Person("bob", 18), new Person("john", 55));
        System.out.println(map2(persons));
        System.out.println(map3(persons));
        System.out.println(map4(persons));

    }

    private static Map<Integer, List<String>> map4(List<Person> persons) {
        /*
                d) Дан список Person {name, age}. Написать метод, который вернет Map/<Integer,List/>, где ключ это возраст, значение - лист строк-имен персонов.
        Подсказка: задача очень похожа на пункт a), на в качестве значений используется не лмст из Person, а лист ищ строк с именами. Т.е. решение как пункт a), но Сollectors.groupingBy() вызываем с дополнительным параметром - коллектором Mapping
         */
        return persons.stream().collect(Collectors.groupingBy(p -> p.getAge(), Collectors.mapping(p -> p.getName(), Collectors.toList())));
    }

    private static Map<Boolean, List<Person>> map3(List<Person> persons) {
        /*
        c) Дан список Person {name, age}. Написать метод, который вернет Map/<Boolean, List/>, где ключ false - если Person моложе 18 лет, true - если уже есть 18. Значение, список соответствующих персон
        Подсказка: обратите внимание на коллектор Сollectors.partitioningBy().
         */
        return persons.stream().collect(Collectors.partitioningBy(person -> person.getAge() >= 18));
    }

    private static Map<Integer, List<Person>> getPerson1(List<Person> persons) {
        /*
            b) Дан список Person {name, age}. Написать метод, который вернет Map/<String, Integer>, где ключ это имя, значение сколько раз встретилось это имя.
                Подсказка: обратите внимание на коллектор Сollectors.toMap().
         */
        return persons.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.toList()));
    }

    private static Map<String, Integer> map2(List<Person> persons) {
        /*
                a) Дан список Person {name, age}. Написать метод, который вернет Map/<Integer,List/>, где ключ это возраст, значение - лист персон.
                Подсказка: обратите внимание на коллектор Сollectors.groupingBy().
         */
        return persons.stream().collect(Collectors.toMap(key -> key.getName(), value -> 1, (v1, v2) -> v1 + 1));
    }

    private static class Person {
        String name;
        Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


}