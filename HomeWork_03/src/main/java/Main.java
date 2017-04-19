import java.util.*;

/**
 * Created by norgant on 18.04.17.
 */
public class Main {
    public static final String OK = "OK";
    public static final String FAIL = "FAIL";
    public static final int DEFAULT_SIZE = 15;

    public static void main(String[] args) {
        //Для отладки всех методов
        // testMyArray();

        System.out.println("Creating new MyArrayList<Integer>...");
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        System.out.println("Creating int[] = {800,3,60}");
        Integer[] testArray = new Integer[]{800,3,60};
        System.out.println("AddAll result:");
        Collections.addAll(myArrayList, testArray);
        System.out.println(myArrayList);

        System.out.println();

        System.out.println("Creating second MyArrayList for Copy test");
        MyArrayList<Integer> mySecondArrayList = new MyArrayList<>();
        Collections.addAll(mySecondArrayList, new Integer[]{0,0,0});
        System.out.println("Copy result: ");
        Collections.copy(mySecondArrayList, myArrayList);
        System.out.println("First array: " + myArrayList + "   Second array: " + mySecondArrayList);

        System.out.println();

        Collections.sort(myArrayList, new MyComparator<>());

        System.out.println("Sort result: " + myArrayList);

    }

    private static void testMyArray() {
        System.out.println("Creating new MyArrayList");
        MyArrayList<Integer> testList = new MyArrayList<>();
        System.out.println(OK);

        System.out.println("Checking Empty...");
        if (testList.isEmpty()) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking Add(0)...");
        testList.add(0);
        if (testList.toString().equals("0")) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking Empty 2...");
        if (!testList.isEmpty()) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking Remove(int 0)...");
        testList.remove(0);
        if (testList.toString().equals("")) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking Empty 3...");
        if (testList.isEmpty()) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Filling from 0 to 15...");
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            testList.add(i);
        }

//        System.out.println(testList);
        if (testList.toString().equals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14")) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

//        System.out.println("Checking ToArray()...");
//        if (testList.toArray().equals(new Object[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14})) {
//            System.out.println(OK);
//        } else {
//            System.out.println(FAIL);
//        }


        System.out.println("Checking Size()...");
        int size = testList.size();
        if (size == DEFAULT_SIZE) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking IndexOf(5)...");
        int index = testList.indexOf(5);
        if (index == 5) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking Get(4)...");
        int result = testList.get(4);
        if (result == 4) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking Set(1, 0)...");
        result = testList.set(1,0);
        if (result == 1 && testList.get(1) == 0) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking LastIndexOf(0)...");
        result = testList.lastIndexOf(0);
        if (result == 1) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking Remove(Integer 5)...");
        testList.remove(new Integer(5));
        if (testList.toString().equals("0 0 2 3 4 6 7 8 9 10 11 12 13 14")) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking SubList(2, 10)...");
        List<Integer> testSubList = testList.subList(2, 10);
        if (testSubList.toString().equals("2 3 4 6 7 8 9 10")) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking Contains()...");
        if (testList.contains(8) && (!testList.contains(20))) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking Add(int index, Integer element)...");
        testList.add(2,0);
        if (testList.toString().equals("0 0 0 2 3 4 6 7 8 9 10 11 12 13 14")) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking Clear()...");
        testList.clear();
        if (testList.isEmpty() && testList.size() == 0 && testList.toString().equals("")) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking AddAll(Collection 0,1,2,3,4)...");
        Collection newCollection = new ArrayList<Integer>();
        newCollection.add(0);
        newCollection.add(1);
        newCollection.add(2);
        newCollection.add(3);
        newCollection.add(4);
        testList.addAll(newCollection);
        if (testList.toString().equals("0 1 2 3 4")) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking AddAll(int 2, Collection 5, 6, 7)...");
        newCollection = new ArrayList<Integer>();
        newCollection.add(5);
        newCollection.add(6);
        newCollection.add(7);
        testList.addAll(2, newCollection);
        if (testList.toString().equals("0 1 5 6 7")) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking RemoveAll(Collection 1,2)...");
        testList.clear();
        testList.add(0);
        testList.add(1);
        testList.add(3);
        testList.add(2);
        testList.add(2);
        testList.add(5);
        newCollection = new ArrayList<Integer>();
        newCollection.add(1);
        newCollection.add(2);
        testList.removeAll(newCollection);
        if (testList.toString().equals("0 3 5")) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking RetainAll(Collection 1,2)...");
        testList.clear();
        testList.add(0);
        testList.add(1);
        testList.add(3);
        testList.add(2);
        testList.add(2);
        testList.add(5);
        newCollection = new ArrayList<Integer>();
        newCollection.add(1);
        newCollection.add(2);
        testList.retainAll(newCollection);
        if (testList.toString().equals("1 2 2")) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }

        System.out.println("Checking Iterator...");
        Iterator<Integer> testIterator = testList.iterator();
        StringBuilder iteratorCheck = new StringBuilder();
        while (testIterator.hasNext()){
            iteratorCheck.append(testIterator.next());
        }
        if (iteratorCheck.toString().equals("122")) {
            System.out.println(OK);
        } else {
            System.out.println(FAIL);
        }
    }

    static class MyComparator<T> implements Comparator<T>{
        /**
         * Compares its two arguments for order.  Returns a negative integer,
         * zero, or a positive integer as the first argument is less than, equal
         * to, or greater than the second.<p>
         * <p>
         * In the foregoing description, the notation
         * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
         * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
         * <tt>0</tt>, or <tt>1</tt> according to whether the value of
         * <i>expression</i> is negative, zero or positive.<p>
         * <p>
         * The implementor must ensure that <tt>sgn(compare(x, y)) ==
         * -sgn(compare(y, x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
         * implies that <tt>compare(x, y)</tt> must throw an exception if and only
         * if <tt>compare(y, x)</tt> throws an exception.)<p>
         * <p>
         * The implementor must also ensure that the relation is transitive:
         * <tt>((compare(x, y)&gt;0) &amp;&amp; (compare(y, z)&gt;0))</tt> implies
         * <tt>compare(x, z)&gt;0</tt>.<p>
         * <p>
         * Finally, the implementor must ensure that <tt>compare(x, y)==0</tt>
         * implies that <tt>sgn(compare(x, z))==sgn(compare(y, z))</tt> for all
         * <tt>z</tt>.<p>
         * <p>
         * It is generally the case, but <i>not</i> strictly required that
         * <tt>(compare(x, y)==0) == (x.equals(y))</tt>.  Generally speaking,
         * any comparator that violates this condition should clearly indicate
         * this fact.  The recommended language is "Note: this comparator
         * imposes orderings that are inconsistent with equals."
         *
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return a negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @throws NullPointerException if an argument is null and this
         *                              comparator does not permit null arguments
         * @throws ClassCastException   if the arguments' types prevent them from
         *                              being compared by this comparator.
         */
        @Override
        public int compare(T o1, T o2) {
            if (o1.toString().length() > o2.toString().length()) return 1;
            if (o1.toString().length() < o2.toString().length()) return -1;
            return 0;
        }
    }

}
