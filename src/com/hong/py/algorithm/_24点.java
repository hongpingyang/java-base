package com.hong.py.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * author: hongpy
 * create time: 2020-07-06 16:13
 * description:
 * life for code
 */
public class _24点 {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String input=scanner.nextLine();
        String[] list=input.split(" ");

        boolean flag=false;
        for (int i = 0; i < 4; i++) {
            String[] collections=new String[list.length];
            collections[0]=list[i];
            for (int j = 0; j < 4&&j!=i; j++) {
                collections[1]=list[j];
                for (int x = 0; x < 4&&x!=i&&x!=j; x++) {
                    collections[2]=list[x];
                    for (int y = 0; y < 4&&y!=i&&y!=j&&y!=x; y++) {
                        collections[3]=list[y];
                        flag=dfs(collections,0,new ArrayList());
                    }
                }
            }
        }
        if(!flag){
            System.out.println("NONE");
        }
    }

    public static boolean dfs(String[] datas, int begin, List<String> result){
        if(begin==datas.length){
            int value=0;
            String ysf="";
            for(int i=0;i<result.size();i++){
                switch(result.get(i)){
                    case "+":
                        ysf="+";
                        break;
                    case "-":
                        ysf="-";
                        break;
                    case "*":
                        ysf="*";
                        break;
                    case "/":
                        ysf="/";
                        break;
                    case "J":
                        value=doAction(value,ysf,11);
                        break;
                    case "Q":
                        value=doAction(value,ysf,12);
                        break;
                    case "K":
                        value=doAction(value,ysf,13);
                        break;
                    case "A":
                        value=doAction(value,ysf,1);
                        break;
                    case "joker":
                        System.out.println("ERROR");
                        return false;
                    case "JOKER":
                        System.out.println("ERROR");
                        return false;
                    default :
                        value=doAction(value,ysf,Integer.parseInt(result.get(i)));
                        break;
                }
            }
            if(value==24){
                //System.out.println("");
                for(String str:result){
                    System.out.print(str);
                }
                return true;
            }else{
                return false;
            }
        }else if(begin>datas.length){
            return false;
        }

        for(int i=begin;i<datas.length;i++){
            result.add(datas[i]);
            if(i!=datas.length-1){

                result.add("+");
                dfs(datas,i+1,result);


                result.remove(result.size()-1);
                result.remove(result.size()-1);
                result.add(datas[i]);
                result.add("-");
                dfs(datas,i+1,result);


                result.remove(result.size()-1);
                result.remove(result.size()-1);
                result.add(datas[i]);
                result.add("*");
                dfs(datas,i+1,result);


                result.remove(result.size()-1);
                result.remove(result.size()-1);
                result.add(datas[i]);
                result.add("/");
                dfs(datas,i+1,result);
            }else {
                dfs(datas,i+1,result);
            }
        }

        return false;
    }


    public static int doAction(int value,String ysf,int v){
        switch(ysf){
            case "+":
                value+=v;
                break;
            case "-":
                value-=v;
                break;
            case "*":
                value*=v;
                break;
            case "/":
                value/=v;
                break;
            default:
                value=v;
                break;
        }
        return value;
    }
}
