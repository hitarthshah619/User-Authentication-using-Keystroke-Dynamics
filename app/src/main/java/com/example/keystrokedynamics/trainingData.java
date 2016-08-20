package com.example.keystrokedynamics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Random;

public class trainingData {
    Context con;
    double [] newinputset_add1,newinputset_add2,newinputset_sub1,newinputset_sub2;
    double [] illegitimateDataset_1,illegitimateDataset_2,illegitimateDataset_3,illegitimateDataset_4 ;
    public trainingData(Context con){
        this.con=con;
    }
    public void generateLegitimateDataset(){
        DBHelper mydbhelper=new DBHelper(con,Database.KEYSTROKE_DYNAMICS,null,Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        String []col=new String[]{
                Database.b1_x,Database.b1_y,
                Database.b2_x,Database.b2_y,
                Database.b3_x,Database.b3_y,
                Database.b4_x,Database.b4_y,
                Database.b5_x,Database.b5_y,
                Database.b6_x,Database.b6_y,
                Database.b1_size, Database.b2_size,
                Database.b3_size, Database.b4_size,
                Database.b5_size, Database.b6_size,
                Database.b1_pressure,Database.b2_pressure,
                Database.b3_pressure,Database.b4_pressure,
                Database.b5_pressure,Database.b6_pressure,
                Database.p1_press,Database.p2_press,
                Database.p3_press,Database.p4_press,
                Database.p5_press,Database.p6_press,
                Database.p1_p2_flight,
                Database.p2_p3_flight,
                Database.p3_p4_flight,
                Database.p4_p5_flight,
                Database.p5_p6_flight,
                Database.p1_p2_trigraph,
                Database.p2_p3_trigraph,
                Database.p3_p4_trigraph,
                Database.p4_p5_trigraph,
                Database.p1_p2_digraph,
                Database.p2_p3_digraph,
                Database.p3_p4_digraph,
                Database.p4_p5_digraph,
                Database.p5_p6_digraph,
                Database.total_time,


        };
        
        Cursor c=mydatabase.query(Database.TABLE_NAME,col,null,null,null,null,null);

        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()) {
            newinputset_add1 = new double[45];
            newinputset_sub1 = new double[45];
            newinputset_add2 = new double[45];
            newinputset_sub2 = new double[45];
            Random random = new Random();
            int add_sub;
            for(int j=0,i=0,q=0,k=0;j<45;j++,i++,k++,q++) {
                add_sub = random.nextInt(2);
                if(add_sub == 1)
                    newinputset_add1[i] = Double.parseDouble(c.getString(q)) + (0.01 * Double.parseDouble(c.getString(k)));
                else
                    newinputset_add1[i] = Double.parseDouble(c.getString(q)) - (0.01 * Double.parseDouble(c.getString(k)));

                add_sub = random.nextInt(2);
                if(add_sub == 1)
                    newinputset_sub1[i] = Double.parseDouble(c.getString(q)) - (0.02 * Double.parseDouble(c.getString(k)));
                else
                    newinputset_sub1[i] = Double.parseDouble(c.getString(q)) + (0.02 * Double.parseDouble(c.getString(k)));

                add_sub = random.nextInt(2);
                if(add_sub == 1)
                    newinputset_add2[i] = Double.parseDouble(c.getString(q)) + (0.03 * Double.parseDouble(c.getString(k)));
                else
                    newinputset_add2[i] = Double.parseDouble(c.getString(q)) - (0.03 * Double.parseDouble(c.getString(k)));

                add_sub = random.nextInt(2);
                if(add_sub == 1)
                    newinputset_sub2[i] = Double.parseDouble(c.getString(q)) - (0.04 * Double.parseDouble(c.getString(k)));
                else
                    newinputset_sub2[i] = Double.parseDouble(c.getString(q)) + (0.04 * Double.parseDouble(c.getString(k)));

            }
            putNewData();
        }
    }

    private void putNewData() {
        DBHelper mydbhelper = new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        int i=0;
        cv.put(Database.b1_x, newinputset_add1[i++]);
        cv.put(Database.b1_y, newinputset_add1[i++]);
        cv.put(Database.b2_x, newinputset_add1[i++]);
        cv.put(Database.b2_y, newinputset_add1[i++]);
        cv.put(Database.b3_x, newinputset_add1[i++]);
        cv.put(Database.b3_y, newinputset_add1[i++]);
        cv.put(Database.b4_x, newinputset_add1[i++]);
        cv.put(Database.b4_y, newinputset_add1[i++]);
        cv.put(Database.b5_x, newinputset_add1[i++]);
        cv.put(Database.b5_y, newinputset_add1[i++]);
        cv.put(Database.b6_x, newinputset_add1[i++]);
        cv.put(Database.b6_y, newinputset_add1[i++]);
        cv.put(Database.b1_size, newinputset_add1[i++]);
        cv.put(Database.b2_size, newinputset_add1[i++]);
        cv.put(Database.b3_size, newinputset_add1[i++]);
        cv.put(Database.b4_size, newinputset_add1[i++]);
        cv.put(Database.b5_size, newinputset_add1[i++]);
        cv.put(Database.b6_size, newinputset_add1[i++]);
        cv.put(Database.p1_press, newinputset_add1[i++]);
        cv.put(Database.p2_press, newinputset_add1[i++]);
        cv.put(Database.p3_press, newinputset_add1[i++]);
        cv.put(Database.p4_press, newinputset_add1[i++]);
        cv.put(Database.p5_press, newinputset_add1[i++]);
        cv.put(Database.p6_press, newinputset_add1[i++]);
        cv.put(Database.b1_pressure, newinputset_add1[i++]);
        cv.put(Database.b2_pressure, newinputset_add1[i++]);
        cv.put(Database.b3_pressure, newinputset_add1[i++]);
        cv.put(Database.b4_pressure, newinputset_add1[i++]);
        cv.put(Database.b5_pressure, newinputset_add1[i++]);
        cv.put(Database.b6_pressure, newinputset_add1[i++]);
        cv.put(Database.p1_p2_flight, newinputset_add1[i++]);
        cv.put(Database.p2_p3_flight, newinputset_add1[i++]);
        cv.put(Database.p3_p4_flight, newinputset_add1[i++]);
        cv.put(Database.p4_p5_flight, newinputset_add1[i++]);
        cv.put(Database.p5_p6_flight, newinputset_add1[i++]);
        cv.put(Database.p1_p2_trigraph, newinputset_add1[i++]);
        cv.put(Database.p2_p3_trigraph, newinputset_add1[i++]);
        cv.put(Database.p3_p4_trigraph, newinputset_add1[i++]);
        cv.put(Database.p4_p5_trigraph, newinputset_add1[i++]);
        cv.put(Database.p1_p2_digraph, newinputset_add1[i++]);
        cv.put(Database.p2_p3_digraph, newinputset_add1[i++]);
        cv.put(Database.p3_p4_digraph, newinputset_add1[i++]);
        cv.put(Database.p4_p5_digraph, newinputset_add1[i++]);
        cv.put(Database.p5_p6_digraph, newinputset_add1[i++]);
        cv.put(Database.total_time, newinputset_add1[i++]);

        long r = mydatabase.insertOrThrow(Database.TABLE_NAME, null, cv);

        cv = new ContentValues();
        i=0;
        cv.put(Database.b1_x, newinputset_sub1[i++]);
        cv.put(Database.b1_y, newinputset_sub1[i++]);
        cv.put(Database.b2_x, newinputset_sub1[i++]);
        cv.put(Database.b2_y, newinputset_sub1[i++]);
        cv.put(Database.b3_x, newinputset_sub1[i++]);
        cv.put(Database.b3_y, newinputset_sub1[i++]);
        cv.put(Database.b4_x, newinputset_sub1[i++]);
        cv.put(Database.b4_y, newinputset_sub1[i++]);
        cv.put(Database.b5_x, newinputset_sub1[i++]);
        cv.put(Database.b5_y, newinputset_sub1[i++]);
        cv.put(Database.b6_x, newinputset_sub1[i++]);
        cv.put(Database.b6_y, newinputset_sub1[i++]);
        cv.put(Database.b1_size, newinputset_sub1[i++]);
        cv.put(Database.b2_size, newinputset_sub1[i++]);
        cv.put(Database.b3_size, newinputset_sub1[i++]);
        cv.put(Database.b4_size, newinputset_sub1[i++]);
        cv.put(Database.b5_size, newinputset_sub1[i++]);
        cv.put(Database.b6_size, newinputset_sub1[i++]);
        cv.put(Database.p1_press, newinputset_sub1[i++]);
        cv.put(Database.p2_press, newinputset_sub1[i++]);
        cv.put(Database.p3_press, newinputset_sub1[i++]);
        cv.put(Database.p4_press, newinputset_sub1[i++]);
        cv.put(Database.p5_press, newinputset_sub1[i++]);
        cv.put(Database.p6_press, newinputset_sub1[i++]);
        cv.put(Database.b1_pressure, newinputset_sub1[i++]);
        cv.put(Database.b2_pressure, newinputset_sub1[i++]);
        cv.put(Database.b3_pressure, newinputset_sub1[i++]);
        cv.put(Database.b4_pressure, newinputset_sub1[i++]);
        cv.put(Database.b5_pressure, newinputset_sub1[i++]);
        cv.put(Database.b6_pressure, newinputset_sub1[i++]);
        cv.put(Database.p1_p2_flight, newinputset_sub1[i++]);
        cv.put(Database.p2_p3_flight, newinputset_sub1[i++]);
        cv.put(Database.p3_p4_flight, newinputset_sub1[i++]);
        cv.put(Database.p4_p5_flight, newinputset_sub1[i++]);
        cv.put(Database.p5_p6_flight, newinputset_sub1[i++]);
        cv.put(Database.p1_p2_trigraph, newinputset_sub1[i++]);
        cv.put(Database.p2_p3_trigraph, newinputset_sub1[i++]);
        cv.put(Database.p3_p4_trigraph, newinputset_sub1[i++]);
        cv.put(Database.p4_p5_trigraph, newinputset_sub1[i++]);
        cv.put(Database.p1_p2_digraph, newinputset_sub1[i++]);
        cv.put(Database.p2_p3_digraph, newinputset_sub1[i++]);
        cv.put(Database.p3_p4_digraph, newinputset_sub1[i++]);
        cv.put(Database.p4_p5_digraph, newinputset_sub1[i++]);
        cv.put(Database.p5_p6_digraph, newinputset_sub1[i++]);
        cv.put(Database.total_time, newinputset_sub1[i++]);

        r = mydatabase.insertOrThrow(Database.TABLE_NAME, null, cv);

        cv = new ContentValues();
        i=0;
        cv.put(Database.b1_x, newinputset_add2[i++]);
        cv.put(Database.b1_y, newinputset_add2[i++]);
        cv.put(Database.b2_x, newinputset_add2[i++]);
        cv.put(Database.b2_y, newinputset_add2[i++]);
        cv.put(Database.b3_x, newinputset_add2[i++]);
        cv.put(Database.b3_y, newinputset_add2[i++]);
        cv.put(Database.b4_x, newinputset_add2[i++]);
        cv.put(Database.b4_y, newinputset_add2[i++]);
        cv.put(Database.b5_x, newinputset_add2[i++]);
        cv.put(Database.b5_y, newinputset_add2[i++]);
        cv.put(Database.b6_x, newinputset_add2[i++]);
        cv.put(Database.b6_y, newinputset_add2[i++]);
        cv.put(Database.b1_size, newinputset_add2[i++]);
        cv.put(Database.b2_size, newinputset_add2[i++]);
        cv.put(Database.b3_size, newinputset_add2[i++]);
        cv.put(Database.b4_size, newinputset_add2[i++]);
        cv.put(Database.b5_size, newinputset_add2[i++]);
        cv.put(Database.b6_size, newinputset_add2[i++]);
        cv.put(Database.p1_press, newinputset_add2[i++]);
        cv.put(Database.p2_press, newinputset_add2[i++]);
        cv.put(Database.p3_press, newinputset_add2[i++]);
        cv.put(Database.p4_press, newinputset_add2[i++]);
        cv.put(Database.p5_press, newinputset_add2[i++]);
        cv.put(Database.p6_press, newinputset_add2[i++]);
        cv.put(Database.b1_pressure, newinputset_add2[i++]);
        cv.put(Database.b2_pressure, newinputset_add2[i++]);
        cv.put(Database.b3_pressure, newinputset_add2[i++]);
        cv.put(Database.b4_pressure, newinputset_add2[i++]);
        cv.put(Database.b5_pressure, newinputset_add2[i++]);
        cv.put(Database.b6_pressure, newinputset_add2[i++]);
        cv.put(Database.p1_p2_flight, newinputset_add2[i++]);
        cv.put(Database.p2_p3_flight, newinputset_add2[i++]);
        cv.put(Database.p3_p4_flight, newinputset_add2[i++]);
        cv.put(Database.p4_p5_flight, newinputset_add2[i++]);
        cv.put(Database.p5_p6_flight, newinputset_add2[i++]);
        cv.put(Database.p1_p2_trigraph, newinputset_add2[i++]);
        cv.put(Database.p2_p3_trigraph, newinputset_add2[i++]);
        cv.put(Database.p3_p4_trigraph, newinputset_add2[i++]);
        cv.put(Database.p4_p5_trigraph, newinputset_add2[i++]);
        cv.put(Database.p1_p2_digraph, newinputset_add2[i++]);
        cv.put(Database.p2_p3_digraph, newinputset_add2[i++]);
        cv.put(Database.p3_p4_digraph, newinputset_add2[i++]);
        cv.put(Database.p4_p5_digraph, newinputset_add2[i++]);
        cv.put(Database.p5_p6_digraph, newinputset_add2[i++]);
        cv.put(Database.total_time, newinputset_add2[i++]);

        r = mydatabase.insertOrThrow(Database.TABLE_NAME, null, cv);

        cv = new ContentValues();
        i=0;
        cv.put(Database.b1_x, newinputset_sub2[i++]);
        cv.put(Database.b1_y, newinputset_sub2[i++]);
        cv.put(Database.b2_x, newinputset_sub2[i++]);
        cv.put(Database.b2_y, newinputset_sub2[i++]);
        cv.put(Database.b3_x, newinputset_sub2[i++]);
        cv.put(Database.b3_y, newinputset_sub2[i++]);
        cv.put(Database.b4_x, newinputset_sub2[i++]);
        cv.put(Database.b4_y, newinputset_sub2[i++]);
        cv.put(Database.b5_x, newinputset_sub2[i++]);
        cv.put(Database.b5_y, newinputset_sub2[i++]);
        cv.put(Database.b6_x, newinputset_sub2[i++]);
        cv.put(Database.b6_y, newinputset_sub2[i++]);
        cv.put(Database.b1_size, newinputset_sub2[i++]);
        cv.put(Database.b2_size, newinputset_sub2[i++]);
        cv.put(Database.b3_size, newinputset_sub2[i++]);
        cv.put(Database.b4_size, newinputset_sub2[i++]);
        cv.put(Database.b5_size, newinputset_sub2[i++]);
        cv.put(Database.b6_size, newinputset_sub2[i++]);
        cv.put(Database.p1_press, newinputset_sub2[i++]);
        cv.put(Database.p2_press, newinputset_sub2[i++]);
        cv.put(Database.p3_press, newinputset_sub2[i++]);
        cv.put(Database.p4_press, newinputset_sub2[i++]);
        cv.put(Database.p5_press, newinputset_sub2[i++]);
        cv.put(Database.p6_press, newinputset_sub2[i++]);
        cv.put(Database.b1_pressure, newinputset_sub2[i++]);
        cv.put(Database.b2_pressure, newinputset_sub2[i++]);
        cv.put(Database.b3_pressure, newinputset_sub2[i++]);
        cv.put(Database.b4_pressure, newinputset_sub2[i++]);
        cv.put(Database.b5_pressure, newinputset_sub2[i++]);
        cv.put(Database.b6_pressure, newinputset_sub2[i++]);
        cv.put(Database.p1_p2_flight, newinputset_sub2[i++]);
        cv.put(Database.p2_p3_flight, newinputset_sub2[i++]);
        cv.put(Database.p3_p4_flight, newinputset_sub2[i++]);
        cv.put(Database.p4_p5_flight, newinputset_sub2[i++]);
        cv.put(Database.p5_p6_flight, newinputset_sub2[i++]);
        cv.put(Database.p1_p2_trigraph, newinputset_sub2[i++]);
        cv.put(Database.p2_p3_trigraph, newinputset_sub2[i++]);
        cv.put(Database.p3_p4_trigraph, newinputset_sub2[i++]);
        cv.put(Database.p4_p5_trigraph, newinputset_sub2[i++]);
        cv.put(Database.p1_p2_digraph, newinputset_sub2[i++]);
        cv.put(Database.p2_p3_digraph, newinputset_sub2[i++]);
        cv.put(Database.p3_p4_digraph, newinputset_sub2[i++]);
        cv.put(Database.p4_p5_digraph, newinputset_sub2[i++]);
        cv.put(Database.p5_p6_digraph, newinputset_sub2[i++]);
        cv.put(Database.total_time, newinputset_sub2[i++]);

        r = mydatabase.insertOrThrow(Database.TABLE_NAME, null, cv);
    }

    public void generateIllegitimateDataset(){
        DBHelper mydbhelper=new DBHelper(con,Database.KEYSTROKE_DYNAMICS,null,Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        String []col=new String[]{
                Database.b1_x,Database.b1_y,
                Database.b2_x,Database.b2_y,
                Database.b3_x,Database.b3_y,
                Database.b4_x,Database.b4_y,
                Database.b5_x,Database.b5_y,
                Database.b6_x,Database.b6_y,
                Database.b1_size, Database.b2_size,
                Database.b3_size, Database.b4_size,
                Database.b5_size, Database.b6_size,
                Database.b1_pressure,Database.b2_pressure,
                Database.b3_pressure,Database.b4_pressure,
                Database.b5_pressure,Database.b6_pressure,
                Database.p1_press,Database.p2_press,
                Database.p3_press,Database.p4_press,
                Database.p5_press,Database.p6_press,
                Database.p1_p2_flight,
                Database.p2_p3_flight,
                Database.p3_p4_flight,
                Database.p4_p5_flight,
                Database.p5_p6_flight,
                Database.p1_p2_trigraph,
                Database.p2_p3_trigraph,
                Database.p3_p4_trigraph,
                Database.p4_p5_trigraph,
                Database.p1_p2_digraph,
                Database.p2_p3_digraph,
                Database.p3_p4_digraph,
                Database.p4_p5_digraph,
                Database.p5_p6_digraph,
                Database.total_time
        };
        Cursor c=mydatabase.query(Database.TABLE_NAME,col,null,null,null,null,null);
        int count = 0;
        c.moveToFirst();
        Random random = new Random();
        int add_sub;
        double percent;
        while (count <= 1) {
            illegitimateDataset_1= new double[45];
            illegitimateDataset_2= new double[45];
            illegitimateDataset_3= new double[45];
            illegitimateDataset_4= new double[45];

            for(int j=0,i=0,q=0,k=0; j<45;j++,i++,q++,k++){

                percent = random.nextInt(8);
                percent += 120;
                percent /= 100;
                if(j>23 && j<30 || j==44)
                    illegitimateDataset_1[i] = Double.parseDouble(c.getString(q)) + (percent * Double.parseDouble(c.getString(k)));
                else
                    illegitimateDataset_1[i] = Double.parseDouble(c.getString(q)) - (percent * Double.parseDouble(c.getString(k)));

                percent = 120 + random.nextInt(8);

                percent /= 100;
                if(j>23 && j<30 || j==44)
                    illegitimateDataset_2[i] = Double.parseDouble(c.getString(q)) + (percent * Double.parseDouble(c.getString(k)));
                else
                    illegitimateDataset_2[i] = Double.parseDouble(c.getString(q)) - (percent * Double.parseDouble(c.getString(k)));

                percent = 120 + random.nextInt(8);

                percent /= 100;
                if(j>23 && j<30 || j==44)
                    illegitimateDataset_3[i] = Double.parseDouble(c.getString(q)) + (percent * Double.parseDouble(c.getString(k)));
                else
                    illegitimateDataset_3[i] = Double.parseDouble(c.getString(q)) - (percent * Double.parseDouble(c.getString(k)));

                percent = 120 + random.nextInt(8);

                percent /= 100;
                if(j>23 && j<30 || j==44)
                    illegitimateDataset_4[i] = Double.parseDouble(c.getString(q)) + (percent * Double.parseDouble(c.getString(k)));
                else
                    illegitimateDataset_4[i] = Double.parseDouble(c.getString(q)) - (percent * Double.parseDouble(c.getString(k)));
            }
            count++;
            c.moveToNext();
            putIllegitimateData();
        }
    }

    private void putIllegitimateData() {
        DBHelper mydbhelper = new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        int i=0;
        cv.put(Database.b1_x, illegitimateDataset_1[i++]);
        cv.put(Database.b1_y, illegitimateDataset_1[i++]);
        cv.put(Database.b2_x, illegitimateDataset_1[i++]);
        cv.put(Database.b2_y, illegitimateDataset_1[i++]);
        cv.put(Database.b3_x, illegitimateDataset_1[i++]);
        cv.put(Database.b3_y, illegitimateDataset_1[i++]);
        cv.put(Database.b4_x, illegitimateDataset_1[i++]);
        cv.put(Database.b4_y, illegitimateDataset_1[i++]);
        cv.put(Database.b5_x, illegitimateDataset_1[i++]);
        cv.put(Database.b5_y, illegitimateDataset_1[i++]);
        cv.put(Database.b6_x, illegitimateDataset_1[i++]);
        cv.put(Database.b6_y, illegitimateDataset_1[i++]);
        cv.put(Database.b1_size, illegitimateDataset_1[i++]);
        cv.put(Database.b2_size, illegitimateDataset_1[i++]);
        cv.put(Database.b3_size, illegitimateDataset_1[i++]);
        cv.put(Database.b4_size, illegitimateDataset_1[i++]);
        cv.put(Database.b5_size, illegitimateDataset_1[i++]);
        cv.put(Database.b6_size, illegitimateDataset_1[i++]);
        cv.put(Database.p1_press, illegitimateDataset_1[i++]);
        cv.put(Database.p2_press, illegitimateDataset_1[i++]);
        cv.put(Database.p3_press, illegitimateDataset_1[i++]);
        cv.put(Database.p4_press, illegitimateDataset_1[i++]);
        cv.put(Database.p5_press, illegitimateDataset_1[i++]);
        cv.put(Database.p6_press, illegitimateDataset_1[i++]);
        cv.put(Database.b1_pressure, illegitimateDataset_1[i++]);
        cv.put(Database.b2_pressure, illegitimateDataset_1[i++]);
        cv.put(Database.b3_pressure, illegitimateDataset_1[i++]);
        cv.put(Database.b4_pressure, illegitimateDataset_1[i++]);
        cv.put(Database.b5_pressure, illegitimateDataset_1[i++]);
        cv.put(Database.b6_pressure, illegitimateDataset_1[i++]);
        cv.put(Database.p1_p2_flight, illegitimateDataset_1[i++]);
        cv.put(Database.p2_p3_flight, illegitimateDataset_1[i++]);
        cv.put(Database.p3_p4_flight, illegitimateDataset_1[i++]);
        cv.put(Database.p4_p5_flight, illegitimateDataset_1[i++]);
        cv.put(Database.p5_p6_flight, illegitimateDataset_1[i++]);
        cv.put(Database.p1_p2_trigraph, illegitimateDataset_1[i++]);
        cv.put(Database.p2_p3_trigraph, illegitimateDataset_1[i++]);
        cv.put(Database.p3_p4_trigraph, illegitimateDataset_1[i++]);
        cv.put(Database.p4_p5_trigraph, illegitimateDataset_1[i++]);
        cv.put(Database.p1_p2_digraph, illegitimateDataset_1[i++]);
        cv.put(Database.p2_p3_digraph, illegitimateDataset_1[i++]);
        cv.put(Database.p3_p4_digraph, illegitimateDataset_1[i++]);
        cv.put(Database.p4_p5_digraph, illegitimateDataset_1[i++]);
        cv.put(Database.p5_p6_digraph, illegitimateDataset_1[i++]);
        cv.put(Database.total_time, illegitimateDataset_1[i++]);

        mydatabase.insertOrThrow(Database.ILLEGITIMATE_TABLE_NAME, null, cv);

        cv = new ContentValues();
        i=0;
        cv.put(Database.b1_x, illegitimateDataset_2[i++]);
        cv.put(Database.b1_y, illegitimateDataset_2[i++]);
        cv.put(Database.b2_x, illegitimateDataset_2[i++]);
        cv.put(Database.b2_y, illegitimateDataset_2[i++]);
        cv.put(Database.b3_x, illegitimateDataset_2[i++]);
        cv.put(Database.b3_y, illegitimateDataset_2[i++]);
        cv.put(Database.b4_x, illegitimateDataset_2[i++]);
        cv.put(Database.b4_y, illegitimateDataset_2[i++]);
        cv.put(Database.b5_x, illegitimateDataset_2[i++]);
        cv.put(Database.b5_y, illegitimateDataset_2[i++]);
        cv.put(Database.b6_x, illegitimateDataset_2[i++]);
        cv.put(Database.b6_y, illegitimateDataset_2[i++]);
        cv.put(Database.b1_size, illegitimateDataset_2[i++]);
        cv.put(Database.b2_size, illegitimateDataset_2[i++]);
        cv.put(Database.b3_size, illegitimateDataset_2[i++]);
        cv.put(Database.b4_size, illegitimateDataset_2[i++]);
        cv.put(Database.b5_size, illegitimateDataset_2[i++]);
        cv.put(Database.b6_size, illegitimateDataset_2[i++]);
        cv.put(Database.p1_press, illegitimateDataset_2[i++]);
        cv.put(Database.p2_press, illegitimateDataset_2[i++]);
        cv.put(Database.p3_press, illegitimateDataset_2[i++]);
        cv.put(Database.p4_press, illegitimateDataset_2[i++]);
        cv.put(Database.p5_press, illegitimateDataset_2[i++]);
        cv.put(Database.p6_press, illegitimateDataset_2[i++]);
        cv.put(Database.b1_pressure, illegitimateDataset_2[i++]);
        cv.put(Database.b2_pressure, illegitimateDataset_2[i++]);
        cv.put(Database.b3_pressure, illegitimateDataset_2[i++]);
        cv.put(Database.b4_pressure, illegitimateDataset_2[i++]);
        cv.put(Database.b5_pressure, illegitimateDataset_2[i++]);
        cv.put(Database.b6_pressure, illegitimateDataset_2[i++]);
        cv.put(Database.p1_p2_flight, illegitimateDataset_2[i++]);
        cv.put(Database.p2_p3_flight, illegitimateDataset_2[i++]);
        cv.put(Database.p3_p4_flight, illegitimateDataset_2[i++]);
        cv.put(Database.p4_p5_flight, illegitimateDataset_2[i++]);
        cv.put(Database.p5_p6_flight, illegitimateDataset_2[i++]);
        cv.put(Database.p1_p2_trigraph, illegitimateDataset_2[i++]);
        cv.put(Database.p2_p3_trigraph, illegitimateDataset_2[i++]);
        cv.put(Database.p3_p4_trigraph, illegitimateDataset_2[i++]);
        cv.put(Database.p4_p5_trigraph, illegitimateDataset_2[i++]);
        cv.put(Database.p1_p2_digraph, illegitimateDataset_2[i++]);
        cv.put(Database.p2_p3_digraph, illegitimateDataset_2[i++]);
        cv.put(Database.p3_p4_digraph, illegitimateDataset_2[i++]);
        cv.put(Database.p4_p5_digraph, illegitimateDataset_2[i++]);
        cv.put(Database.p5_p6_digraph, illegitimateDataset_2[i++]);
        cv.put(Database.total_time, illegitimateDataset_2[i++]);

        mydatabase.insertOrThrow(Database.ILLEGITIMATE_TABLE_NAME, null, cv);

        cv = new ContentValues();
        i=0;
        cv.put(Database.b1_x, illegitimateDataset_3[i++]);
        cv.put(Database.b1_y, illegitimateDataset_3[i++]);
        cv.put(Database.b2_x, illegitimateDataset_3[i++]);
        cv.put(Database.b2_y, illegitimateDataset_3[i++]);
        cv.put(Database.b3_x, illegitimateDataset_3[i++]);
        cv.put(Database.b3_y, illegitimateDataset_3[i++]);
        cv.put(Database.b4_x, illegitimateDataset_3[i++]);
        cv.put(Database.b4_y, illegitimateDataset_3[i++]);
        cv.put(Database.b5_x, illegitimateDataset_3[i++]);
        cv.put(Database.b5_y, illegitimateDataset_3[i++]);
        cv.put(Database.b6_x, illegitimateDataset_3[i++]);
        cv.put(Database.b6_y, illegitimateDataset_3[i++]);
        cv.put(Database.b1_size, illegitimateDataset_3[i++]);
        cv.put(Database.b2_size, illegitimateDataset_3[i++]);
        cv.put(Database.b3_size, illegitimateDataset_3[i++]);
        cv.put(Database.b4_size, illegitimateDataset_3[i++]);
        cv.put(Database.b5_size, illegitimateDataset_3[i++]);
        cv.put(Database.b6_size, illegitimateDataset_3[i++]);
        cv.put(Database.p1_press, illegitimateDataset_3[i++]);
        cv.put(Database.p2_press, illegitimateDataset_3[i++]);
        cv.put(Database.p3_press, illegitimateDataset_3[i++]);
        cv.put(Database.p4_press, illegitimateDataset_3[i++]);
        cv.put(Database.p5_press, illegitimateDataset_3[i++]);
        cv.put(Database.p6_press, illegitimateDataset_3[i++]);
        cv.put(Database.b1_pressure, illegitimateDataset_3[i++]);
        cv.put(Database.b2_pressure, illegitimateDataset_3[i++]);
        cv.put(Database.b3_pressure, illegitimateDataset_3[i++]);
        cv.put(Database.b4_pressure, illegitimateDataset_3[i++]);
        cv.put(Database.b5_pressure, illegitimateDataset_3[i++]);
        cv.put(Database.b6_pressure, illegitimateDataset_3[i++]);
        cv.put(Database.p1_p2_flight, illegitimateDataset_3[i++]);
        cv.put(Database.p2_p3_flight, illegitimateDataset_3[i++]);
        cv.put(Database.p3_p4_flight, illegitimateDataset_3[i++]);
        cv.put(Database.p4_p5_flight, illegitimateDataset_3[i++]);
        cv.put(Database.p5_p6_flight, illegitimateDataset_3[i++]);
        cv.put(Database.p1_p2_trigraph, illegitimateDataset_3[i++]);
        cv.put(Database.p2_p3_trigraph, illegitimateDataset_3[i++]);
        cv.put(Database.p3_p4_trigraph, illegitimateDataset_3[i++]);
        cv.put(Database.p4_p5_trigraph, illegitimateDataset_3[i++]);
        cv.put(Database.p1_p2_digraph, illegitimateDataset_3[i++]);
        cv.put(Database.p2_p3_digraph, illegitimateDataset_3[i++]);
        cv.put(Database.p3_p4_digraph, illegitimateDataset_3[i++]);
        cv.put(Database.p4_p5_digraph, illegitimateDataset_3[i++]);
        cv.put(Database.p5_p6_digraph, illegitimateDataset_3[i++]);
        cv.put(Database.total_time, illegitimateDataset_3[i++]);

        mydatabase.insertOrThrow(Database.ILLEGITIMATE_TABLE_NAME, null, cv);

        cv = new ContentValues();
        i=0;
        cv.put(Database.b1_x, illegitimateDataset_4[i++]);
        cv.put(Database.b1_y, illegitimateDataset_4[i++]);
        cv.put(Database.b2_x, illegitimateDataset_4[i++]);
        cv.put(Database.b2_y, illegitimateDataset_4[i++]);
        cv.put(Database.b3_x, illegitimateDataset_4[i++]);
        cv.put(Database.b3_y, illegitimateDataset_4[i++]);
        cv.put(Database.b4_x, illegitimateDataset_4[i++]);
        cv.put(Database.b4_y, illegitimateDataset_4[i++]);
        cv.put(Database.b5_x, illegitimateDataset_4[i++]);
        cv.put(Database.b5_y, illegitimateDataset_4[i++]);
        cv.put(Database.b6_x, illegitimateDataset_4[i++]);
        cv.put(Database.b6_y, illegitimateDataset_4[i++]);
        cv.put(Database.b1_size, illegitimateDataset_4[i++]);
        cv.put(Database.b2_size, illegitimateDataset_4[i++]);
        cv.put(Database.b3_size, illegitimateDataset_4[i++]);
        cv.put(Database.b4_size, illegitimateDataset_4[i++]);
        cv.put(Database.b5_size, illegitimateDataset_4[i++]);
        cv.put(Database.b6_size, illegitimateDataset_4[i++]);
        cv.put(Database.p1_press, illegitimateDataset_4[i++]);
        cv.put(Database.p2_press, illegitimateDataset_4[i++]);
        cv.put(Database.p3_press, illegitimateDataset_4[i++]);
        cv.put(Database.p4_press, illegitimateDataset_4[i++]);
        cv.put(Database.p5_press, illegitimateDataset_4[i++]);
        cv.put(Database.p6_press, illegitimateDataset_4[i++]);
        cv.put(Database.b1_pressure, illegitimateDataset_4[i++]);
        cv.put(Database.b2_pressure, illegitimateDataset_4[i++]);
        cv.put(Database.b3_pressure, illegitimateDataset_4[i++]);
        cv.put(Database.b4_pressure, illegitimateDataset_4[i++]);
        cv.put(Database.b5_pressure, illegitimateDataset_4[i++]);
        cv.put(Database.b6_pressure, illegitimateDataset_4[i++]);
        cv.put(Database.p1_p2_flight, illegitimateDataset_4[i++]);
        cv.put(Database.p2_p3_flight, illegitimateDataset_4[i++]);
        cv.put(Database.p3_p4_flight, illegitimateDataset_4[i++]);
        cv.put(Database.p4_p5_flight, illegitimateDataset_4[i++]);
        cv.put(Database.p5_p6_flight, illegitimateDataset_4[i++]);
        cv.put(Database.p1_p2_trigraph, illegitimateDataset_4[i++]);
        cv.put(Database.p2_p3_trigraph, illegitimateDataset_4[i++]);
        cv.put(Database.p3_p4_trigraph, illegitimateDataset_4[i++]);
        cv.put(Database.p4_p5_trigraph, illegitimateDataset_4[i++]);
        cv.put(Database.p1_p2_digraph, illegitimateDataset_4[i++]);
        cv.put(Database.p2_p3_digraph, illegitimateDataset_4[i++]);
        cv.put(Database.p3_p4_digraph, illegitimateDataset_4[i++]);
        cv.put(Database.p4_p5_digraph, illegitimateDataset_4[i++]);
        cv.put(Database.p5_p6_digraph, illegitimateDataset_4[i++]);
        cv.put(Database.total_time, illegitimateDataset_4[i++]);

        mydatabase.insertOrThrow(Database.ILLEGITIMATE_TABLE_NAME, null, cv);
    }
}
