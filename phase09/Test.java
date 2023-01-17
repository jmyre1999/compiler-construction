class Test{
    public static void main(String[] a){
       System.out.println(new C().Demo());
    }
}

class A {
    int x;

    public int CallFib(Fib fib) {
        return fib.CalculateFib(0, 1);
    }
}

class B extends A {
    int[] array;

    public int setX(int i) {
        x = i;
        return 0;
    }

    public int getX() {
        return x;
    }

    public int[] createArray(int size) {
        return (new int[size]);
    }

    public int setArray(int[] array, int index, int val) {
        System.out.println(array.length);
        array[index] = val;
        return 0;
    }

    public int getArray(int[] array, int index) {
        return array[index];
    }

}

class C extends B {

    public int Demo() {
        int y;
        y = this.FibTest();
        y = this.xTest(6);
        y = this.arrayTest(y);
        return y;
    }

    public int FibTest() {
        Fib f;
        f = new Fib();
        return this.CallFib(f);
    }

    public int xTest(int a) {
        a = this.setX(a);
        return this.getX();
    }

    public int arrayTest(int size) {
        int a;
        array = this.createArray(size);
        a = this.setArray(array, 4, 11);
        return this.getArray(array, 4);
    }
}

class Fib {

    public int CalculateFib(int a,int b){
        int n_2;
        System.out.println(a);
    	if (1000 < b)
    	    n_2 = b;
    	else
            n_2 = this.CalculateFib(b,a+b);
    	return 0;
    }

}
