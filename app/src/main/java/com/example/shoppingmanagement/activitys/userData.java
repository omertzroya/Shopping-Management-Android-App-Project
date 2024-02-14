package com.example.shoppingmanagement.activitys;

import android.util.Log;

import java.util.ArrayList;

public class userData {

    private static final ArrayList<user> userList = new ArrayList<>();

    public static void addUser(user user) {
        userList.add(user);
    }

   // print all users in arraylist
      public static void printAllUsers() {
            for (user user : userList) {
                Log.d("user", user.toString());
            }
        }

        public static ArrayList<user> getUserList() {
            return userList;
        }

}
