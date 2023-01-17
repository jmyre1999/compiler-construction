class Error{
    public static void main(String[] a){
//        System.out.println(new Fac().ComputeFac(10));
        if (a.length) {
            System.out.println(1);
        }
        else {
            System.out.println(2);
        }
    }
}

class Fac extends Fac2{
    int shared;

    public int ComputeFac(int num, boolean num){
        int num_aux ;
        boolean temp ;
        if (0 < 1 && !(70 < (20 + 7 * 3)))
            num_aux = 1 ;
        else
            num_aux = num * (this.ComputeFac(num-1)) ;
        return num_aux ;
    }

}

class Fac2 extends Fac{
    Fac2 obj;
    boolean non;
    int[] some_array;
    int p;

    public int Wrapper() {
        int temp;

        while (xxx.SomeFunc()) {
            System.out.println(1);
            something = non.Woo();
        }

        if (some_array[non]) {
            obj = new Fac2();
            non = 2;
        }
        else {
            temp = obj.ComputeFac(p, p);
            some_array[true] = 1;
        }

        return true;
    }
}
