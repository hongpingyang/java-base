package com.hong.py.java8;

import com.hong.py.pojo.Dish;
import com.hong.py.pojo.Trader;
import com.hong.py.pojo.Transaction;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        IntStream.Builder builder = IntStream.builder();
        builder.add(20);
        builder.add(13);
        builder.add(-2);
        builder.add(18);
        IntStream stream = builder.build();

        IntStream stream2=IntStream.builder()
                .add(20)
                .add(13)
                .add(-2)
                .add(18)
                .build();

        IntStream stream3=IntStream.builder()
                .add(20)
                .add(13)
                .add(-2)
                .add(18)
                .build();

        IntStream stream4=IntStream.builder()
                .add(20)
                .add(13)
                .add(-2)
                .add(18)
                .build();

        System.out.println(stream.max().getAsInt());
        System.out.println(stream2.sum());
        System.out.println(stream3.count());
        System.out.println(stream4.average());


        Collection books=new HashSet();
        books.add("spring");
        books.add("spring-mvc");
        books.add("mybatis");
        books.add("springboot");
        books.add("springCloud");
        books.add("高数");

        //集合讲的是数据，流讲的是计算。

        Object stream5 = books.stream().filter(p -> p.toString().contains("spring")).collect(Collectors.toList());
        //顺序串行执行
        Stream stream6 = books.stream().filter(p -> p.toString().contains("spring"));
        stream6.forEach(System.out::println);
        //只能迭代一次，这个会报错
        //stream6.forEach(System.out::println);

        //stream6.collect()




        books.stream().filter(p->p.toString().contains("spring")).forEach(System.out::println);
        //并行执行
        System.out.println(books.parallelStream().filter(p->p.toString().contains("spring")).count());


        System.out.println("===============");

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );

        //不会打印任何结果
        //重要的是，除非流水线上触发一个终端操作，否则中间操作不会执行任何处理——它们很懒。
        //判断是否是终端操作，就是看操作返回是否不是Stream。
        Stream<String> limit = menu.stream().filter((dish) ->
                {
                    System.out.println(dish.getName() + " filtering:");
                    return dish.getCalories() < 500;
                }
        ).map(dish ->
                {
                    System.out.println(dish.getName() + " mapping:");
                    return dish.getName();
                }
        ).limit(3);


        //执行这个以后，才打印
        List<String> collect = limit.collect(Collectors.toList());

        System.out.println("===============");

        List<Integer> integers = Arrays.asList(1, 2, 4, 6, 7, 8,4,3,9,11);
        //distinct 去重
        integers.stream().filter(o -> o % 2 == 0).distinct().forEach(System.out::println);
        //截断流
        integers.stream().filter(o -> o % 2 == 0).limit(3).forEach(System.out::println);
        //跳过
        integers.stream().filter(o -> o % 2 == 0).skip(2).limit(3).forEach(System.out::println);
        System.out.println("映射");
        integers.stream().filter(o -> o % 2 == 0).map(Math::negateExact).forEach(System.out::println);


        //流中是否有一个元素满足 >4
        boolean bo = integers.stream().filter(o -> o % 2 == 0).anyMatch(o -> o > 4);
        //流中是否每个元素满足 >4
        boolean bo1 = integers.stream().filter(o -> o % 2 == 0).allMatch(o -> o > 4);
        //流中没有元素满足 >4
        boolean bo2 = integers.stream().filter(o -> o % 2 == 0).noneMatch(o -> o > 4);

        //找到其中一个 Optional表示有可能找不到
        Optional<Integer> any = integers.stream().filter(o -> o % 2 == 0).findAny();
        //Optional ifPresent函数
        integers.stream().filter(o -> o % 2 == 0).findAny().ifPresent(System.out::println);
        //找到第一个
        Optional<Integer> first = integers.stream().filter(o -> o % 3 == 0).findFirst();
        first.ifPresent(System.out::println);

        //规约
        //求和
        Optional<Integer> reduce = integers.stream().reduce((a, b) -> (a + b));
        //初始值1
        Integer reduce1 = integers.stream().reduce(1, (a, b) -> (a + b));

        System.out.println("求最大值");
        //求最大值
        Optional<Integer> reduce2 = integers.stream().reduce((a, b) -> Math.max(a, b));
        reduce2.ifPresent(System.out::println);
        Optional<Integer> reduce3 = integers.stream().reduce(Integer::max);
        reduce3.ifPresent(System.out::println);
        //求最小值
        Optional<Integer> reduce4 = integers.stream().reduce(Integer::min);
        reduce4.ifPresent(System.out::println);

        System.out.println("========Trader========");

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                //new Transaction(alan, 2011, 600),
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println("找出2011年发生的所有交易，并按交易额排序（从低到高）。");
        transactions.stream().filter(o->o.getYear()==2011).
                sorted(Comparator.comparing(Transaction::getValue)).
                forEach(System.out::println);

        System.out.println("交易员都在哪些不同的城市工作过？");
        transactions.stream().map(o->o.getTrader().getCity()).
                distinct().
                forEach(System.out::println);

        System.out.println("查找所有来自于剑桥的交易员，并按姓名排序。");
        transactions.stream().filter(o->o.getTrader().getCity().equals("Cambridge")).
                map(o->o.getTrader()).
                distinct().
                sorted((o1,o2)->o1.getName().compareTo(o1.getName())).
                forEach(System.out::println);

        System.out.println("返回所有交易员的姓名字符串，按字母顺序排序");
        transactions.stream().map(o -> o.getTrader().getName()).
                distinct(). //需要去重
                sorted(Comparator.comparing(String::trim)).
                collect(Collectors.toList());

        System.out.println("有没有交易员是在米兰工作的？");
        Optional<Transaction> milan = transactions.stream().
                filter(o -> o.getTrader().getCity().equals("Milan")).findAny();
        milan.ifPresent(System.out::println);

        System.out.println("打印生活在剑桥的交易员的所有交易额");
        transactions.stream().filter(o->o.getTrader().getCity().equals("Cambridge")).
                map(o->o.getValue()).
                reduce((a,b)->(a+b)).ifPresent(System.out::println);

        System.out.println("所有交易中，最高的交易额是多少？");
        transactions.stream().map(o->o.getValue()).
                reduce((a, b) -> Math.max(a, b)).
                ifPresent(System.out::println);

        System.out.println("找到交易额最小的交易");
        //1:
        transactions.stream().sorted(Comparator.comparing(Transaction::getValue)).findFirst().ifPresent(System.out::println);
        //2:
        transactions.stream().reduce((a, b) -> a.getValue() < b.getValue() ? a : b);
        //3
        transactions.stream().min(Comparator.comparing(Transaction::getValue));
        //4 mapToInt转化为IntStream 原型流
        OptionalInt min = transactions.stream().mapToInt(Transaction::getValue).min();

        //
        min.orElse(0); //没有元素的话，就用默认值0

        //生成范围值
        IntStream range = IntStream.range(0, 100);
        LongStream range1 = LongStream.range(100, 200);

        //构建流
        Stream<String> stringStream = Stream.of("Java 8", "Lambda", "In", "Action");
        stringStream.forEach(System.out::println);

        int[] numbers = {2, 3, 5, 7, 11, 13};
        IntStream stream1 = Arrays.stream(numbers);
        System.out.println(stream1.sum());

        System.out.println("=====创造无限流=====");
        //创造无限流 不能无限计算下去，应该使用limit(n)加以限制
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        System.out.println("=====斐波纳契======");
        Stream.iterate(new int[]{0,1},
                t -> new int[]{t[1] ,t[0]+t[1]}).
                limit(20).
                forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));


        //用流收集数据
        //找出最大值
        menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))).ifPresent(System.out::println);
        //找出最小值
        menu.stream().collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories))).ifPresent(System.out::println);
        //汇总
        //menu.stream().collect(Collectors.summingInt(Dish::getCalories)).to;

    }
}
