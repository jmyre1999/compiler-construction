class Test{
    public static void main(String[] a){
       System.out.println(new Z().m(3));
    }
}

class Z {

    public int m(int a){
        int n;
        Z obj;
        obj = new Z();
        n = obj.sum(a,obj);
        return n;
    }
    public int sum(int a, Z obj){
        int b;
        b = obj.getConst();
        return (a+b);    
    }
    public int getConst(){
        return 7;
    }
    

}
