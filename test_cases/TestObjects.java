class Test{
    public static void main(String[] a){
       System.out.println(new Z().m(1));
    }
}

class Z {

    public int m(int a){
        int n;
        Z obj;
        obj = new Z();
        n = obj.sum(a,4);
        return n;
    }
    public int sum(int a, int b){
        return (a+b);    
    }
    

}
