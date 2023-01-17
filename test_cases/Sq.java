class Sq{
    public static void main(String[] a){
       System.out.println(new Cal().CalculatePower(7, 5));
    }
}

class Cal {

    public int CalculatePower(int a,int b){
    int n;
	if (b < 1)
	    n = 1;
	else
        n = a * (this.CalculatePower(a,b-1));
	return n;
    }

}
