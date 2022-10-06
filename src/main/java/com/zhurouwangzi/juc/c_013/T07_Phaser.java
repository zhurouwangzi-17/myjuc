package com.zhurouwangzi.juc.c_013;

import java.util.concurrent.Phaser;

/**
 * phase：阶段
 * 这个类算是阶段性的栅栏
 */
public class T07_Phaser {

    static MarriagePhaser phaser = new MarriagePhaser();

    public static void main(String[] args) {
        //设置phaser的条件
        phaser.bulkRegister(7);

        for(int i =0;i<5;i++){
            new Thread(new Person("p"+i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    static class MarriagePhaser extends Phaser{

        /**
         * phase代表阶段、registeredParties代表有多少人注册到了这个阶段
         */
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0://1阶段
                    System.out.println("所有人都到齐了！"+registeredParties);
                    System.out.println();
                    return false;
                case 1://2阶段
                    System.out.println("所有人吃饭！"+registeredParties);
                    System.out.println();
                    return false;
                case 2://3阶段
                    System.out.println("所有人离开！"+registeredParties);
                    System.out.println();
                    return false;
                case 3://4阶段
                    System.out.println("新郎新娘入洞房！"+registeredParties);
                    return true;
                default:
                    return true;
            }
        }
    }


    static class Person implements Runnable{
        public Person(String name) {
            this.name = name;
        }

        private String name;

        public void arrive(){
            System.out.printf("%s 到达现场：\n",name);
            phaser.arriveAndAwaitAdvance();
        }

        public void eat(){
            System.out.printf("%s 吃饭：\n",name);
            phaser.arriveAndAwaitAdvance();
        }

        public void leave(){
            System.out.printf("%s 吃完走人：\n",name);
            phaser.arriveAndAwaitAdvance();
        }

        public void hug(){
            //只有是新郎新娘的时候，才将这个人注册到入洞房这个阶段上去
            if("新郎".equals(name)||"新娘".equals(name)){
                System.out.printf("%s 入洞房：\n",name);
                phaser.arriveAndAwaitAdvance();
            }else{
                //此人不是新郎和新娘，将此人从入洞房这个阶段上注销掉
                phaser.arriveAndDeregister();
            }
        }

        @Override
        public void run() {

            //1阶段 到达现场
            arrive();

            //2阶段 开始吃饭
            eat();

            //3阶段 吃完走人
            leave();

            //4阶段新郎新娘入洞房
            hug();
        }
    }
}


