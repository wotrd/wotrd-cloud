package com.wotrd.dubbo.common.algorithm;

public class Test {
    public static final char FLAG[]={'E', 'S', 'W', 'N'};
    public String finish(Integer xByInit,Integer yByInit,Character flag,String cmd){
        cmd=cmd.toUpperCase();
        int len=FLAG.length;
        // 朝向
        int loc=0;
        for (int i = 0; i < FLAG.length; i++) {
            if(FLAG[i]==flag){
                loc=i;
                break;
            }
        }
        //  'L'和'R'使漫游车分别向左或向右旋转90度，而不会从当前地点移动
        //  'M'表示前进一个网格点，并保持相同的方向
        char[] cmdByChar=cmd.toCharArray();
        for (int i = 0; i <cmdByChar.length; i++) {
            if(cmdByChar[i] == 'L'){
                loc = (loc - 1 + len) % len;
            }else if(cmdByChar[i] == 'R'){
                loc = (loc + 1) % len;
            }else if(cmdByChar[i] == 'M'){
                switch (FLAG[loc]){
                    case 'E':
                        xByInit += 1;
                        break;
                    case 'S':
                        yByInit -= 1;
                        break;
                    case 'W':
                        xByInit -= 1;
                        break;
                    case 'N':
                        yByInit += 1;
                        break;
                }
            }
        }
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(xByInit);
        stringBuilder.append(" ");
        stringBuilder.append(yByInit);
        stringBuilder.append(" ");
        stringBuilder.append(FLAG[loc]);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        int xByInit=1;
        int yByInit=2;
        char flag='N';
        String cmd="LMLMLMLMM";
        System.out.println(new Test().finish(xByInit,yByInit,flag,cmd));
    }
}
