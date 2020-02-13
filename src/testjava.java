//package com.company;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args) {
//	// write your code here
//        Scanner temp = new Scanner(System.in);
//        String tempin = temp.nextLine();
//        String[] tempnums = tempin.split(" ");
//
//
//
////        int[] nums = new int[tempnums.length];
////        for (int i = 0;i < tempnums.length;i++){
////            nums[i] = Integer.parseInt(tempnums[i]);
////        }
////        quicksort(nums,0,nums.length-1);
////        StringBuffer tempout = new StringBuffer();
////        for(int item:nums){
////            tempout.append(Integer.toString(item));
////            tempout.append(" ");
////        }
////        System.out.println(tempout);
//
//
//        ArrayList<Integer> ob = new ArrayList<>();
//        for (String item:tempnums){
//            ob.add(Integer.parseInt(item));
//        }
//
//        System.out.println(ob);
//    }
////    public static void quicksort(int[] arr,int low,int high){
////        if(low < high){
////            int pi = partition(arr,low,high);
////            quicksort(arr,low,pi-1);
////            quicksort(arr,pi+1,high);
////        }
////    }
////    public static int partition(int[] arr,int low,int high){
////        int i = low;
////        int temp = arr[high];
////        for (int j = low;j<high;j++){
////            if (arr[j]<=temp){
////                int tempitem = arr[i];
////                arr[i] = arr[j];
////                arr[j] = tempitem;
////                i+=1;
////            }
////        }
////        int tempitem = arr[i];
////        arr[i] = arr[high];
////        arr[high] = tempitem;
////        return i;
////    }
//
//}


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class testjava{
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "2672411561";

    public static void main(String[] args){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = "select id,name,age,sex from user where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,1);
            ResultSet resultSet = stmt.executeQuery();
            printRS(resultSet);

            stmt = conn.prepareStatement("select id,name,age,sex from user where age=? and name=?");
            stmt.setInt(1,12);
            stmt.setString(2,"yan");
            resultSet = stmt.executeQuery();
            printRS(resultSet);

            stmt = conn.prepareStatement("select id,name,age,sex from user where age>?");
            stmt.setInt(1,20);
            resultSet = stmt.executeQuery();
            printRS(resultSet);
            resultSet.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if(stmt!= null){
                    stmt.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            try{
                if(conn!=null){
                    conn.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    private static void printRS(ResultSet resultSet) throws SQLException{
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            String sex = resultSet.getString("sex");
            System.out.println("id:"+id+"name:"+name+"age:"+age+"sex:"+sex);
        }
    }
}
