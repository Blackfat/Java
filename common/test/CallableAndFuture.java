package com.ahut.framework.test.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableAndFuture {
    
    public void execute(){
        // 固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        CompletionService<String> cs = new ExecutorCompletionService<String>(threadPool);
        for(int i= 0 ; i<5; i++){
            cs.submit(new Task());
        }
        // 关闭线程池，不再接受其他任务
        threadPool.shutdown();
        for(int i = 0; i < 5; i++) {
            try {
                System.out.println(cs.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public static void main(String[] args) {
        CallableAndFuture call = new CallableAndFuture();
        call.execute();
    }
    
    
    

}

class Task implements Callable<String>{

    @Override
    public String call() throws Exception {
        try{
            Thread.sleep(2000);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Thread.currentThread().getName();
    }
    
}
