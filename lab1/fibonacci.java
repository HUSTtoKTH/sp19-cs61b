public class fibonacci{
    public static void main(String[] args) throws Exception{
        int n = Integer.parseInt(args[0]);
        System.out.println(fib(n));
        System.out.println(fib2(n));
    }

    public static int fib(int n){
        if(n == 0){
            return 0;
        }else if(n == 1){
            return 1;
        }else{
            return fib(n-1)+fib(n-2);
        }
    }

    public static int fib2(int n, int k, int f0, int f1) {
        
    }

    
}