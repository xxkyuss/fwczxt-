package com.hspedu.houserent.servise;

import com.hspedu.houserent.domain.House;

public class HouseSerVice {
    private House[] houses;
    private int houseNums = 1;
    private int idCounter = 1;
    //查找房源
    public House find(int findId){
        System.out.println("查找到一项:");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        for(int i = 0;i < houseNums;i++){
            if(findId == houses[i].getId()){
                System.out.println(houses[i]);
                return houses[i];
            }
        }
        return null;
    }
    //del删除一个房屋信息
    public boolean del(int delId){
        //应当先寻找到房屋信息对应的下标
        int index = -1;
        for (int i = 0;i < houseNums;i++) {
            if(delId == houses[i].getId()){
                index = i;
            }
        }
        if(index == -1){
            return false;
        }
        //把后面的移动到前面,再把后面的滞空
        for(int i = index;i < houseNums - 1;i++){
            houses[i] = houses[i+1];
        }
        houses[--houseNums] = null;
        //少一个
        return true;
    }
    public HouseSerVice(int size) {
        houses = new House[size];
        houses[0] = new House(1,"jacky","138","官渡区",5000,"未出租");
    }
    //添加
    public boolean add(House newHouse){
        if(houseNums == houses.length){
            System.out.println("位置已满,正在扩容");
                House arrNew[] = new House[houses.length + 1];
                for(int i = 0;i < houses.length;i++){
                    arrNew[i] = houses[i];
                }
                arrNew[arrNew.length - 1] = null;
                houses = arrNew;
                System.out.println("扩容完毕");
        }
        houses[houseNums++] = newHouse;
        newHouse.setId(++idCounter);
        return true;
    }
    public House[] list(){
        return houses;
    }
}
