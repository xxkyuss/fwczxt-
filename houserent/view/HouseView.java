package com.hspedu.houserent.view;

import com.hspedu.houserent.domain.House;
import com.hspedu.houserent.servise.HouseSerVice;
import com.hspedu.houserent.utils.Utility;

public class HouseView {

    private boolean loop = true;
    private char key = ' ';//接收用户输入的是哪个菜单
    private HouseSerVice houseSerVice = new HouseSerVice(10);//设置数组的大小为10

    //编写修改功能
    public void update() {
        System.out.println("=============修改房屋信息=============");
        System.out.println("请输入待修改房屋编号(-1退出):");
        int upDateId = Utility.readInt();
        if (upDateId == -1) {
            System.out.println("=============放弃了修改=============");
            return;
        }
        House house = houseSerVice.find(upDateId);
        if (house == null) {
            System.out.println("=============修改失败,该房子不存在=============");
            return;
        }
            System.out.print("姓名(" + house.getName() + "):");
            String name = Utility.readString(8, "");
            if (!"".equals(name)) {
                house.setName(name);
            }
            System.out.print("电话(" + house.getPhone() + "):");
            String phone = Utility.readString(13, "");
            if (!"".equals(phone)) {
                house.setPhone(phone);
            }
            System.out.print("地址(" + house.getAddress() + "):");
            String address = Utility.readString(20, "");
            if (!"".equals(address)) {
                house.setAddress(address);
            }
            System.out.print("租金(" + house.getRent() + "):");
            int rent = Utility.readInt(-1);
            if (rent != -1) {
                house.setRent(rent);
            }
            System.out.print("状态(" + house.getState() + "):");
            String state = Utility.readString(13, "");
            if (!"".equals(state)) {
                house.setState(state);
            }
            System.out.println("=============修改成功=============");
    }

    //编写查找房屋功能
    public void findHouse() {
        System.out.println("=============查找房屋信息=============");
        System.out.print("请输入待查找房屋编号(-1退出):");
        int findId = Utility.readInt();
        House house = houseSerVice.find(findId);
        if (house != null) {
            System.out.println("=============查找成功=============");
        } else {
            System.out.println("=============查找失败,该房子不存在=============");
        }
    }

    //编写房屋删除功能
    public void delHouse() {
        System.out.println("=============删除房屋信息=============");
        System.out.print("请输入待删除房屋编号(-1退出):");
        int delId = Utility.readInt();
        if (delId == -1) {
            System.out.println("=============放弃了删除=============");
            return;
        }
        //必须输入Y/N,否则出不来
        char choice = Utility.readConfirmSelection();
        if (choice == 'Y') {
            if (houseSerVice.del(delId)) {
                System.out.println("=============删除成功=============");
            } else {
                System.out.println("=============删除失败,房子编号不存在=============");
            }
        } else {
            System.out.println("=============放弃了删除=============");
        }
    }

    //编写添加房屋功能
    public void addHouse() {
        System.out.println("=============添加房屋=============");
        System.out.println("姓名: ");
        String name = Utility.readString(7);
        System.out.println("电话: ");
        String phone = Utility.readString(12);
        System.out.println("地址: ");
        String address = Utility.readString(20);
        System.out.println("月租: ");
        int rent = Utility.readInt();
        System.out.println("状态: ");
        String state = Utility.readString(3);
        //创建一个新的house对象,注意,id是系统自动分配的,用户不能指定
        House house = new House(0, name, phone, address, rent, state);
        if (houseSerVice.add(house)) {
            System.out.println("=============添加成功=============");
        } else {
            System.out.println("=============添加失败=============");
        }
    }

    //编写listHouse()输出房屋列表
    public void listHouse() {
        System.out.println("=============房屋列表=============");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House[] houses = houseSerVice.list();//得到所有房屋信息
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null) {
                break;//如果为空了,就不要再显示了
            }
            System.out.println(houses[i]);
        }
        System.out.println("=============房屋列表显示完毕=============");
    }

    //显示主菜单
    public void mainMeNu() {

        do {
            System.out.println("=============房屋出租系统菜单=============");
            System.out.println("\t\t\t1 新 增 房 源");
            System.out.println("\t\t\t2 查 找 房 屋");
            System.out.println("\t\t\t3 删 除 房 屋 信 息");
            System.out.println("\t\t\t4 修 改 房 屋 信 息");
            System.out.println("\t\t\t5 房 屋 列 表");
            System.out.println("\t\t\t6 退      出");
            System.out.println("请输入你的选择(1~6)");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    update();
                    break;
                case '5':
                    listHouse();
                    break;
                case '6':
                    Utility.readConfirmSelection();
                    loop = false;
                    break;

            }
        } while (loop);
    }
}
