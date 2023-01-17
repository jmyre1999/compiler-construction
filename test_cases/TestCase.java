class TestCase {
    public static void main(String[] a){
        System.out.println(new B().Test());
    }
}


class B {
    int size;
    int count;

    public int Test() {
        int[] array;

        size = 3;
        array = new int[size];
        array[2] = 7;
        return array[2];
    }
}