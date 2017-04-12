package ru.otus.l21;

import java.lang.management.ManagementFactory;

/**
 * Created by Norgant.
 */
//VM options -Xmx1024m -Xms1024m
public class Main {

    private static final int SLEEP_TIME = 10 * 1000;
    private static final int SIZE = 10 * 1024 * 1024;
    private static final int NUMBER_OF_LOOPS = 4;
    private static Runtime runtime = Runtime.getRuntime();

    //Создаем массив объектов размера SIZE, очищаем и смотрим сколько освободилось памяти
    //Повторяем NUMBER_OF_LOOPS раз, вычислим среднее (Высвобождено памяти за NUMBER_OF_LOOPS
    // делим на SIZE * NUMBER_OF_LOOPS)

    //По непонятным мне причинам иногда на каком-то шаге результат заметно отличеается
    //возможно там где-то срабатывает GC без нашего участия
    //При этом такого не происходит если запускать тест отдельно на каждго вида объекта
    //при NUMBER_OF_LOOPS < 5


    public static void main(String... args) throws InterruptedException {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());

        System.out.println("Checking memory size for Object():");
        checkMemorySize(() -> new Object()); //Result: 20

        System.out.println("Checking memory size for \"\":");
        checkMemorySize(() -> ""); //Result: 4

        System.out.println("Checking memory size for String(\"\"):");
        checkMemorySize(() -> new String("")); //Result: 28

        System.out.println("Checking memory size for String(new char[0]):");
        checkMemorySize(() -> new String(new char[0])); //Result: 44

        System.out.println("Checking memory size for int[0]:");
        checkMemorySize(() -> new int[0]); //Result: 20

        System.out.println("Checking memory size for int[1]:");
        checkMemorySize(() -> new int[1]); //Result: 28

        System.out.println("Checking memory size for int[2]:");
        checkMemorySize(() -> new int[2]); //Result: 28

        System.out.println("Checking memory size for int[3]:");
        checkMemorySize(() -> new int[3]); //Result: 36

        System.out.println("Checking memory size for int[4]:");
        checkMemorySize(() -> new int[4]); //Result: 36

        System.out.println("Checking memory size for int[5]:");
        checkMemorySize(() -> new int[5]); //Result: 44
    }

    @FunctionalInterface
    private interface ObjectCreator{
        Object getNewObject();
    }

    public static void checkMemorySize(ObjectCreator obj) throws InterruptedException{
        long startMemory;
        long endMemory;
        int n = 0;
        int memoryForAllLoops = 0;

//        System.out.println("Starting the loop");
        while (n < NUMBER_OF_LOOPS) {
            System.out.println("-------- * Creating Array № " + n + " * --------");
            Object[] array = new Object[SIZE];
//            System.out.println("Array of size: " + array.length + " created");
//            System.out.println("Starting filling loop");
            for (int i = 0; i < SIZE; i++) {
                array[i] = obj.getNewObject();
            }
//            System.out.println("Array filled");
            startMemory = runtime.freeMemory();
//            System.out.println("Start free memory: " + startMemory);

            array = null;

//            System.out.println("Array cleared");
            System.gc();
//            System.out.println("!!! GC - Array collected !!!");

            Thread.sleep(SLEEP_TIME);
            endMemory = runtime.freeMemory();
//            System.out.println("End free memory: " + endMemory);
//            System.out.println("Memory for " + SIZE + " Objects = " + (endMemory - startMemory));
            if (endMemory > startMemory) {
                System.out.println("Memory per Object = " + (endMemory - startMemory) / SIZE);
                memoryForAllLoops += endMemory - startMemory;
                n++;
            } else  {
                //Иногда GC срабатывает не вовремя и мы получаем недостоверный результат,
                //в таком случае попробуем пересчитать
                System.out.println("Error: End free memory < Start free memory !!!");
                System.out.println("Restart creating Array № " + n);
            }
        }
        System.out.println();
        System.out.println("----------------------------");
        System.out.println("Average after " + NUMBER_OF_LOOPS + " loops:");
        System.out.println(memoryForAllLoops/(SIZE * NUMBER_OF_LOOPS) + " byte per Object");
        System.out.println("----------------------------");

        System.out.println();
        System.gc();
        Thread.sleep(SLEEP_TIME);
    }
}

