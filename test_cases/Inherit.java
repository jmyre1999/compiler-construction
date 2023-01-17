class Inherit{
    public static void main(String[] a){
        System.out.println(new NewClass().m());
    }
}

class ParentClass{
    int a;
    TestClass t;

}

class SubClass extends ParentClass{

    public int getA(){
        return a;
    }
    public int setA(int b){
        a = b;
        return 0;
    }


}

class NewClass extends SubClass {

     public int m(){
        int n;
        n = this.inheritTest(9);
        t = new TestClass();
        n = t.setB(n);
        return t.getB();
    }

    public int inheritTest(int c) {
        c = this.setA(c);
        return this.getA();
    }
}

class TestClass {
    int b;

    public int setB(int c){
        b = c;
        return 0;
    }
    public int getB() {
        return b;
    }
}