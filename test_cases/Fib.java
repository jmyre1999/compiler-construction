class Fib{
    public static void main(String[] a){
       System.out.println(new Cal().CalculateFib(0, 1));
    }
}

class Cal {

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
