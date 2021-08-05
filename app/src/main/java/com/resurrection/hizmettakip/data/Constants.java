package com.resurrection.hizmettakip.data;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Constants {
   public static long idCreater() {
        Date nowDateTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyykkmmss");
        String date = dateFormat.format(nowDateTime);
        System.out.println(date);
        return Long.valueOf(date);
    }

   public static String getDate() {
        java.sql.Date nowDateTime = new java.sql.Date(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy-kk:mm");
        String date = dateFormat.format(nowDateTime);
        System.out.println(date);
        return date;
    }



}
