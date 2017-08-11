import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yxzuo on 2017/8/10.
 */
public class ConsumerAndProducer {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        new Thread(new Producer(list, 10)).start();
        new Thread(new Consumer(list)).start();
    }

    static class Producer implements Runnable{
        private final int MAX;
        private List<Integer> list;

        public Producer(List<Integer> list, int max){
            this.list = list;
            this.MAX = max;
        }

        @Override
        public void run() {
            while(true){
                try {
                    synchronized (list){
                        while(list.size() == MAX){
                            System.out.println("full");
                            list.wait();
                        }
                        list.add(1);
                        System.out.println("produce, now is " + list.size());
                        list.notifyAll();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable{
        private List<Integer> list;

        public Consumer(List<Integer> list){
            this.list = list;
        }

        @Override
        public void run() {
            while(true){
                try {
                    synchronized (list){
                        while(list.size() == 0){
                            System.out.println("empty");
                            list.wait();
                        }
                        list.remove(0);
                        System.out.println("consume, now is " + list.size());
                        list.notifyAll();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


