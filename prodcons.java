/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign4;

/**
 *
 * @author Deepak
 */


///

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;


class prodcons{
    //***********************MAIN***************************/
    public static int MAX=5;                                    // buffersize
    public static final int cnum=9;                        //total consumers for servicing

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    public static int service_time[]=new int[cnum];            //initializing array for service time
    public static void printQ(int[] Q,int front,int rear){
        int f=front;int r=rear;
        while(f!=r){
            System.out.print(Q[f]+" ");
            f+=1;
            f=f%MAX;
            
        }
    }
    public static void main(String args[]) throws InterruptedException
    {        
        Scanner sc=new Scanner(System.in);                    
        
        int choice = 0;int z=0;
        
        while(choice!=1 && choice!=2)                        // Total service personnel required
        {
            if(z==0)
            {
                System.out.println("Enter the number of Service personnel required(1/2): ");
                choice=sc.nextInt();
                z=1;
            }
            else
            {
                System.out.println(ANSI_RED+"Please enter correct value(either 1 or 2)!!"+ANSI_RESET);
                System.out.println("Enter the number of Service personnel required(1/2): ");
                choice=sc.nextInt();
            }
        }
        sc.close();
        for(int i=0;i<cnum;i++)                                // Generating random servicing time required by each customer
            service_time[i]=getRandomNumberInRange(1, 5);
        int max=MAX-1;
        
        System.out.println(ANSI_BLUE+"Waiting Queue length: "+ANSI_RESET+max);
        System.out.println(ANSI_RED+"Total customers: "+cnum+ANSI_RESET);
        //System.out.println(ANSI_BLUE+"Customer No.\t"+"Service time\t"+"Service Personnel\t"+"Curr queue\t"+"Remarks\t"+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"-----------------Waiting Queue is Empty, Now the Counter is CLOSED---------------"+ANSI_RESET);
        switch(choice)
        {
        case 1: System.out.println("SERVICING WITH SINGLE PERSONNEL");
                System.out.println(ANSI_BLUE+"Customer No.\t"+"Service time\t"+"Service Personnel\t"+"Curr queue\t"+"Remarks"+ANSI_RESET);
                final prodcons pc1 = new prodcons(); 
                Thread p1 = new Thread(new Runnable()                 // Creating producer thread for 1 service personnel case
                { 
                    @Override
                    public void run() 
                    { 
                        try
                        { 
                            pc1.producer1(); 
                        } 
                        catch(InterruptedException e) 
                        { 
                            e.printStackTrace(); 
                        } 
                    } 
                }); 
        
        
                Thread c1 = new Thread(new Runnable()                 // Creating consumer thread for 1 service personnel case
                { 
                    @Override
                    public void run() 
                    { 
                        try
                        { 
                            pc1.consumer1(); 
                        } 
                        catch(InterruptedException e) 
                        { 
                            e.printStackTrace(); 
                        } 
                    } 
                }); 
                //System.out.println(ANSI_GREEN+"Customer 1 arrives and wakes up the Service Personnel"+ANSI_RESET);
                System.out.println(ANSI_GREEN+"\t\t"+"\t\t"+"\t\t\t"+"--"+"\t\t"+"Customer 1 arrives and wakes up the Service Personnel"+ANSI_RESET);
                p1.start();
                c1.start();
                p1.join();
                c1.join();
                break;
        
        case 2: System.out.println("SERVICING WITH 2 SERVICE PERSONNEL");
                System.out.println(ANSI_BLUE+"Customer No.\t"+"Service time\t"+"Service Personnel\t"+"Curr queue\t"+"Remarks"+ANSI_RESET);
                final prodcons pc2 = new prodcons(); 
                Thread p2 = new Thread(new Runnable()                 // Creating producer thread for 2 service personnel case
                { 
                    @Override
                    public void run() 
                    { 
                        try
                        { 
                            pc2.producer2(); 
                        } 
                        catch(InterruptedException e) 
                        { 
                            e.printStackTrace(); 
                        } 
                    } 
                }); 
                
                Thread c21 = new Thread(new Runnable()                 // Creating consumer thread for 2 service personnel case
                { 
                    @Override
                    public void run() 
                    { 
                        try
                        { 
                            pc2.consumer21(); 
                        } 
                        catch(InterruptedException e) 
                        { 
                            e.printStackTrace(); 
                        } 
                    } 
                }); 
                
                Thread c22 = new Thread(new Runnable()                 //  Creating consumer thread for 2 service personnel case
                { 
                    @Override
                    public void run() 
                    { 
                        try
                        { 
                            pc2.consumer22(); 
                        } 
                        catch(InterruptedException e) 
                        { 
                            e.printStackTrace(); 
                        } 
                    } 
                });     
                //System.out.println(ANSI_GREEN+"Customer 1 arrives and wakes up the Service Personnel"+ANSI_RESET);
                System.out.println(ANSI_GREEN+"\t\t"+"\t\t"+"\t\t\t"+"--"+"\t\t"+"Customer 1 arrives and wakes up the Service Personnel"+ANSI_RESET);
                p2.start();
                c21.start();
                c22.start();
                p2.join();
                c21.join();
                c22.join();
                break;
                
        default: System.out.println(ANSI_RED+"Please restart the program as wrong input is given."+ANSI_RESET);
        }// Switch ends
        System.out.println(ANSI_YELLOW+"--------------------------------COUNTER IS CLOSED--------------------------------"+ANSI_RESET);
    }                        
    // Function for Random number generation
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return (int)(Math.random() * ((max - min) + 1)) + min;
    }
    /////////////////////////////////////////////////////////
    
    public static final int FULL = 0;
    public static final int EMPTY=MAX;
    public static int last=0,flag=0,slept=0,front=0,rear=0;

    static Semaphore semFull = new Semaphore(FULL);         // Semaphore for checking how many slots are FULL
    static Semaphore semEmp = new Semaphore(EMPTY);         // Semaphore for checking how many slots are EMPTY
    ReentrantLock lock = new ReentrantLock();                 // MUTEX LOCK
    int queue[]=new int[MAX];
    
    //**************Consumer Method for 1 service personnel case***********************//
    public void consumer1() throws InterruptedException
    {
        int item=0;
        do
        {
            semFull.acquire();
            /************************MUTEX region************************/
            synchronized (this) 
            {
                if(front==rear)
                {
                    //System.out.println(ANSI_RED+"Waiting queue is empty, Service personnel sleeps"+ANSI_RESET);
                    System.out.println(ANSI_RED+"\t\t"+"\t\t"+"\t\t\t"+"--"+"\t\t"+"Waiting queue is empty, Service personnel sleeps"+ANSI_RESET);
                    slept=1;
                }
                else
                {
                    item=queue[front];
                    //System.out.println(ANSI_GREEN+"Customer "+item+" is being serviced having service time "+service_time[item-1]+"sec"+ANSI_RESET);
                    System.out.print(ANSI_GREEN+item+"\t\t"+service_time[item-1]+" sec\t\t"+"\t1\t\t");
                    printQ(queue,front,rear);
                    System.out.println("\t\tBeing serviced"+ANSI_RESET);
                    front=(front+1)%MAX;
                    
                    if(last==1 && item==cnum-1)
                        flag=1;
                    Thread.sleep(service_time[item-1]*1000);
                }
            }
            /********************MUTEX region ENDS here********************/
            semEmp.release();
            
        }while(item!=cnum && flag!=1);
    }
    //*******1st Consumer Method for 2 Service personnel case*******/
    public void consumer21() throws InterruptedException
    {
        int item=0;
        do
        {
            semFull.acquire();
            /*************************MUTEX region*************************/
            lock.lock();
            if(front==rear)
            {
                //System.out.println(ANSI_RED+"Waiting Queue is empty and Service Personnel sleeps"+ANSI_RESET);
                System.out.println(ANSI_RED+"\t\t"+"\t\t"+"\t\t\t"+"--"+"\t\t"+"Waiting queue is empty, Service personnel sleeps"+ANSI_RESET);
                slept=1;
                lock.unlock();
            /********************MUTEX region ENDS here********************/
            }
            else
            {
                item=queue[front];
                //System.out.println(ANSI_GREEN+"Customer "+item+" is being serviced by service personnel 1 for time "+service_time[item-1]+"sec"+ANSI_RESET);
                System.out.print(ANSI_GREEN+item+"\t\t"+service_time[item-1]+" sec\t\t"+"\t1\t\t");
                printQ(queue,front,rear);
                System.out.println("\t\tBeing serviced"+ANSI_RESET);
                front=(front+1)%MAX;
                
                lock.unlock();
            /****************ORRR MUTEX region ENDS here********************/
                if(item==cnum)
                    flag=1;
                Thread.sleep(service_time[item-1]*1000);///////service_time[item]
            }
            
            semEmp.release();
            
        }while(item!=cnum && flag!=1);
        flag=1;
    }
    
    //**************2nd Consumer Method for 2 Service personnel case***********************//
    public void consumer22() throws InterruptedException
    {
        int item=0;
        do
        {
            semFull.acquire();
            /*************************Mutex region*************************/
            lock.lock();
            if(front==rear)
            {
                //System.out.println(ANSI_RED+"Waiting queue is empty, Service personnel sleeps"+ANSI_RESET);
                System.out.println(ANSI_RED+"\t\t"+"\t\t"+"\t\t\t"+"--"+"\t\t"+"Waiting queue is empty, Service personnel sleeps"+ANSI_RESET);
                lock.unlock();
                slept=1;
            /********************MUTEX region ENDS here********************/
            }
            else
            {
                item=queue[front];
                //System.out.println(ANSI_CYAN+"Customer "+item+" is being serviced by service personnel 2 for time "+service_time[item-1]+"sec"+ANSI_RESET);
                System.out.print(ANSI_CYAN+item+"\t\t"+service_time[item-1]+" sec\t\t"+"\t2\t\t");
                printQ(queue,front,rear);
                System.out.println("\t\tBeing serviced"+ANSI_RESET);
                front=(front+1)%MAX;
                
                lock.unlock();
            /******************ORRR MUTEX region ENDS here******************/
                
                if(item==cnum)
                    flag=1;
                Thread.sleep(service_time[item-1]*1000);
            }

            semEmp.release();
            
        }while(item!=cnum && flag!=1);
        flag=1;
    }
    
  //********************Producer Class for 1 Service personnel case***********************//
    public void producer1() throws InterruptedException
    {
        for(int i=1;i<=cnum;i++)
        {
            semEmp.acquire();
            /***************Mutex Region***********/
            synchronized (this) 
            { 
                // consumer thread waits while list 
                // is empty 
        
                if ((front==0 && rear==MAX-1)||(rear==(front-1)%MAX))
                {
                    //System.out.println(ANSI_RED+"Queue is full, Customer "+i+" leaves"+ANSI_RESET);
                    System.out.print(ANSI_RED+"\t\t"+"\t\t"+"\t\t\t");
                    printQ(queue,front,rear);
                    System.out.println("\t\tQueue is full, Customer "+i+" leaves"+ANSI_RESET);
                }
                else
                {
                    queue[rear]=i;
                    rear=(rear+1)%MAX;
                    if(slept==1)
                    {
                        //System.out.println(ANSI_PURPLE+"Customer "+ i +" arrives and wakes up service personnel"+ANSI_RESET);
                        System.out.println(ANSI_PURPLE+"\t\t"+"\t\t"+"\t\t\t"+"--"+"\t\t"+"Customer "+ i +" arrives and wakes up service personnel"+ANSI_RESET);
                        slept=0;
                    }
                    else
                    {
                        //System.out.println(ANSI_BLUE+"Customer "+i +" is added to the queue"+ANSI_RESET);
                        System.out.print(ANSI_BLUE+"\t\t"+"\t\t"+"\t\t\t");
                        printQ(queue,front,rear);
                        System.out.println("\t\tCustomer "+ i +" is added to the queue"+ANSI_RESET);
                    }
                }
            }//mutex ends    
        semFull.release();    
        }
        semFull.release();
    }      
    //********************Producer Class for 2 Service personnel case***********************//
    public void producer2() throws InterruptedException
    {
        for(int i=1;i<=cnum;i++)
        {
            semEmp.acquire();
            /*************************MUTEX region*************************/
            
            lock.lock(); 
            // consumer thread waits while queue 
            // is empty 
        
            if ((front==0 && rear==MAX-1)||(rear==(front-1)%MAX))
            {
                //System.out.println(ANSI_RED+"Queue is full, Customer "+i+" leaves"+ANSI_RESET);
                System.out.print(ANSI_RED+"\t\t"+"\t\t"+"\t\t\t");
                printQ(queue,front,rear);
                System.out.println("\t\tQueue is full, Customer "+i+" leaves"+ANSI_RESET);
            }
            else
            {
                queue[rear]=i;
                rear=(rear+1)%MAX;
                if(slept==1)
                {
                    //System.out.println(ANSI_PURPLE+"Customer "+ i +" arrives and wakes up service personnel"+ANSI_RESET);
                    System.out.println(ANSI_PURPLE+"\t\t"+"\t\t"+"\t\t\t"+"--"+"\t\t"+"Customer "+ i +" arrives and wakes up service personnel"+ANSI_RESET);
                    slept=0;
                }
                else
                {
                    //System.out.println(ANSI_BLUE+"Customer "+i +" is added to the queue"+ANSI_RESET);
                    System.out.print(ANSI_BLUE+"\t\t"+"\t\t"+"\t\t\t");
                    printQ(queue,front,rear);
                    System.out.println("\t\tCustomer "+ i +" is added to the queue"+ANSI_RESET);
                }
            }
            lock.unlock();
            /********************MUTEX region ENDS here********************/
        semFull.release();    
        }
        semFull.release();
    }
}
