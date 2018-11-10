import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Player {
    private String name;

    Player(String name) {
        this.name = name;
        System.out.println("欢迎" + name + "加入游戏");
    }

}

class PlayPoker {
    private int cardsX[] = new int[10];         //初始生成的牌
    private int cards1[] = new int[5];
    private int cards2[] = new int[5];

    private int cardsXTemp[] = new int[10];     //用于处理JQK这种大于10的数，让他们等于10
    private int cards1Temp[] = new int[5];
    private int cards2Temp[] = new int[5];

    private int cardsXTempX[] = new int[10];    //用于打印数组
    private int cards1TempX[] = new int[5];
    private int cards2TempX[] = new int[5];

    private int max1 = cards1[0];
    private int max2 = cards2[0];


    public void play() {

        Random ran = new Random();      //生成牌并解决了重复的问题
        Set<Integer> set = new HashSet<>();
        while (set.size() != 10) {
            set.add(ran.nextInt(52) + 1);
        }
        Object[] obj = set.toArray();
        for (int i = 0; i < 10; i++) {
            cardsX[i] = (int) obj[i];
        }
        //生成两个人的牌
        System.arraycopy(cardsX, 0, cards1, 0, 5);      //P1的手牌
        System.arraycopy(cardsX, 5, cards2, 0, 5);      //P2的手牌

        System.arraycopy(cardsX, 0, cardsXTemp, 0, 10);     //数组复制，目的是处理JQK为10
        System.arraycopy(cards1, 0, cards1Temp, 0, 5);
        System.arraycopy(cards2, 0, cards2Temp, 0, 5);

        System.arraycopy(cardsX, 0, cardsXTempX, 0, 10);        //为了输出扑克
        System.arraycopy(cards1, 0, cards1TempX, 0, 5);
        System.arraycopy(cards2, 0, cards2TempX, 0, 5);

        System.out.println("\n游戏开始:");
        System.out.println("说明:该游戏无庄家 ; J,Q,K为11,12,13");
        System.out.println("有请Dealer发牌:");
        System.out.println();
        System.out.print("玩家一的牌为:");
        for (int i = 0; i < 5; i++) {
            if (cards1TempX[i] < 14 && cards1TempX[i] > 0) {
                System.out.print("♦");

            } else if (cards1TempX[i] <= 26 && cards1TempX[i] >= 14) {
                System.out.print("♣");
                cards1TempX[i] -= 13;
            } else if (cards1TempX[i] <= 39 && cards1TempX[i] >= 27) {
                System.out.print("♥");
                cards1TempX[i] -= 26;
            } else {
                System.out.print("♠");
                cards1TempX[i] -= 39;
            }
            System.out.print(cards1TempX[i] + " ");
        }
        System.out.println();
        System.out.print("玩家二的牌为:");
        for (int i = 0; i < 5; i++) {
            if (cards2TempX[i] < 14 && cards2TempX[i] > 0) {
                System.out.print("♦");
            } else if (cards2TempX[i] <= 26 && cards2TempX[i] >= 14) {
                System.out.print("♣");
                cards2TempX[i] -= 13;
            } else if (cards2TempX[i] <= 39 && cards2TempX[i] >= 27) {
                System.out.print("♥");
                cards2TempX[i] -= 26;
            } else {
                System.out.print("♠");
                cards2TempX[i] -= 39;
            }
            System.out.print(cards2TempX[i] + " ");
        }

        //////////////////////////////////////////////比牌//////////////////////////////////////////////////////
        System.out.println();
        System.out.println();
        System.out.println("激烈的游戏过程...");
        System.out.println();
        System.out.println("游戏结束:");

        for (int i = 0; i < 10; i++) {      //改JQK的大小为10
            if ((cardsXTemp[i]) % 13 == 0 || (cardsXTemp[i]) % 13 == 11 || (cardsXTemp[i]) % 13 == 12) {
                cardsXTemp[i] = 10;
            }
        }
        for (int i = 0; i < 5; i++) {
            if ((cards1Temp[i]) % 13 == 0 || (cards1Temp[i]) % 13 == 11 || (cards1Temp[i]) % 13 == 12) {
                cards1Temp[i] = 10;
            }
            if ((cards2Temp[i]) % 13 == 0 || (cards2Temp[i]) % 13 == 11 || (cards2Temp[i]) % 13 == 12) {
                cards2Temp[i] = 10;
            }
        }

        if ((cards1[0] + cards1[1] + cards1[2]) % 10 == 0 || (cards1[0] + cards1[1] + cards1[3]) % 10 == 0 || (cards1[0] + cards1[1] + cards1[4]) % 10 == 0 || (cards1[1] + cards1[2] + cards1[3]) % 10 == 0 || (cards1[1] + cards1[2] + cards1[4]) % 10 == 0 || (cards1[2] + cards1[3] + cards1[4]) % 10 == 0) {
            //如果P1有牛
            if ((cards2[0] + cards2[1] + cards2[2]) % 10 == 0 || (cards2[0] + cards2[1] + cards2[3]) % 10 == 0 || (cards2[0] + cards2[1] + cards2[4]) % 10 == 0 || (cards2[1] + cards2[2] + cards2[3]) % 10 == 0 || (cards2[1] + cards2[2] + cards2[4]) % 10 == 0 || (cards2[2] + cards2[3] + cards2[4]) % 10 == 0) {
                //如果P2也有牛
                if (sum(cards1Temp) % 10 == sum(cards2Temp) % 10) {
                    System.out.println("平局！");

                    if (sum(cards1Temp) % 10 == 0) {
                        System.out.println("双方牌型都为牛牛");
                    }
                }
                if (sum(cards1Temp) % 10 > sum(cards2Temp) % 10) {
                    System.out.println("玩家一 胜！");
                    System.out.println("玩家一的牌型为:牛" + sum(cards1Temp) % 10);
                }
                if (sum(cards1Temp) % 10 < sum(cards2Temp) % 10) {
                    System.out.println("玩家二 胜！");
                    System.out.println("玩家二的牌型为:牛" + sum(cards2Temp) % 10);
                }
            } else {
                System.out.println("玩家一 胜！");
                System.out.println("玩家一的牌型为:牛" + sum(cards1Temp) % 10);
            }
        } else {//P1无牛
            if ((cards2[0] + cards2[1] + cards2[2]) % 10 == 0 || (cards2[0] + cards2[1] + cards2[3]) % 10 == 0 || (cards2[0] + cards2[1] + cards2[4]) % 10 == 0 || (cards2[1] + cards2[2] + cards2[3]) % 10 == 0 || (cards2[1] + cards2[2] + cards2[4]) % 10 == 0 || (cards2[2] + cards2[3] + cards2[4]) % 10 == 0) {
                //P2有牛
                if (sum(cards1Temp) % 10 == 0) {
                    System.out.println("玩家二 胜！");
                    System.out.println("玩家二的牌型为:牛牛");
                } else {
                    System.out.println("玩家二 胜！");
                    System.out.println("玩家二的牌型为:牛" + sum(cards2Temp) % 10);
                }
            } else {
                //P2无牛(双无牛)
                for (int i = 0; i < 5; i++) {
                    if (max1 < cards1[i]) {
                        max1 = cards1[i];
                    } else if (max2 < cards2[i]) {
                        max2 = cards2[i];
                    }
                }
                if (max1 > max2) {
                    System.out.println("玩家一 胜!");
                    System.out.println("玩家一的牌型为:无牛");
                } else if (max1 < max2) {
                    System.out.println("玩家二 胜!");
                    System.out.println("玩家二的牌型为:无牛");
                } else {
                    System.out.println("平局!");
                    System.out.println("两玩家的牌型同为:无牛");
                }
            }
        }

    }

    public int sum(int cards[]) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += cards[i];
        }
        return sum;
    }


}

public class PokerBullFighting {
    public static void main(String[] args) {
        Player P1 = new Player("玩家一");
        Player P2 = new Player("玩家二");
        PlayPoker Dealer = new PlayPoker();
        Dealer.play();
    }
}