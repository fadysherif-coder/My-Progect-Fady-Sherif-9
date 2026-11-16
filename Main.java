class Semaphore {
    private int permits;

    public Semaphore(int permits) {
        this.permits = permits;
    }

    public synchronized void acquire()  {
        while (permits == 0) {
           try {
               wait();
           } catch (InterruptedException e) {
               
           }
        }
        permits--;
    }

    public synchronized void release() {
        permits++;
        notifyAll();
    }
}
 class phone extends Thread {
   int from ;
   int to;
   int amount;
   int times ;
   
   phone(int from,int to,int amount,int times){
    this.from = from;
    this.to = to;
    this.amount = amount;
    this.times = times;
   }
 
 public void run(){
    for(int i=0;i<times;i++){
        Main.semaphore.acquire();
         Main.balance[from] -= amount;
         Main.balance[to] += amount;
         Main.semaphore.release();

    }
 }}
public class Main {
    static Semaphore semaphore = new Semaphore(1);
    static int[] balance={200,300};
  
    public static void main(String[] args) throws InterruptedException {
        phone thread1 = new phone(0, 1, 5, 40);
        phone thread2 = new phone(1, 0, 8, 25);
        phone thread3 = new phone(0, 1, 3, 30);
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("Balance sara :" + balance[0]);
        System.out.println("Balance karim:" +balance[1]);
        System.out.println("total :" +(balance[0] +balance[1]));
    }
    
} 


