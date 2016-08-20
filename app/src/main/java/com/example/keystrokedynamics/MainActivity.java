package com.example.keystrokedynamics;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;



import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    String app_password;
    private ProgressDialog mProgressDialog;
    double pressure_t;
    private int _progress = 0;
    private Handler _progressHandler;
    boolean checksum;
    int current_pointer;
    double lastDown;
    double total_time;
    TextView e1, e2, e3, e4, e5, e6;
    double[] starttime, presstime, difference, digraph, trigraph, t_x, t_y, t_keypressedDuration, t_size, t_pressure;
    double[] input_layer;
    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t0, ok, viewRecords, attempt, right_wrong;
    double t1_x, t1_y, t2_x, t2_y, t3_x, t3_y, t4_x, t4_y, t5_x, t5_y, t6_x, t6_y, t7_x, t7_y, t8_x, t8_y, t9_x, t9_y, t0_x, t0_y;
    int[] location = new int[2];
    int contentHeight, contentWidth;
    Switch mySwitch;
    int status;
    int counter;
    double t1_keyPressedDuration, t2_keyPressedDuration, t3_keyPressedDuration, t4_keyPressedDuration,
            t5_keyPressedDuration, t6_keyPressedDuration, t7_keyPressedDuration, t8_keyPressedDuration,
            t9_keyPressedDuration, t0_keyPressedDuration;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        counter = 0;

        e1 = (TextView) findViewById(R.id.password_1);
        e2 = (TextView) findViewById(R.id.password_2);
        e3 = (TextView) findViewById(R.id.password_3);
        e4 = (TextView) findViewById(R.id.password_4);
        e5 = (TextView) findViewById(R.id.password_5);
        e6 = (TextView) findViewById(R.id.password_6);

        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);
        t5 = (TextView) findViewById(R.id.t5);
        t6 = (TextView) findViewById(R.id.t6);
        t7 = (TextView) findViewById(R.id.t7);
        t8 = (TextView) findViewById(R.id.t8);
        t9 = (TextView) findViewById(R.id.t9);
        t0 = (TextView) findViewById(R.id.t0);
        ok = (TextView) findViewById(R.id.tdone);
        viewRecords = (TextView) findViewById(R.id.tclear);
        attempt = (TextView) findViewById(R.id.attempt_no);
        right_wrong = (TextView) findViewById(R.id.right_wrong);
        mySwitch = (Switch) findViewById(R.id.switch1);
        initialiseVariables();
        status = 0;

        mySwitch.setChecked(false);
        //attach a listener to check for changes in state
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    status = 1;
                } else {
                    status = 0;
                }

            }
        });


        t0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPassword("0");
                normalizeData(0);

            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPassword("1");
                normalizeData(1);

            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPassword("2");
                normalizeData(2);

            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPassword("3");
                normalizeData(3);

            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPassword("4");
                normalizeData(4);

            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPassword("5");
                normalizeData(5);

            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPassword("6");
                normalizeData(6);

            }
        });
        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPassword("7");
                normalizeData(7);

            }
        });
        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPassword("8");
                normalizeData(8);

            }
        });
        t9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPassword("9");
                normalizeData(9);

            }
        });
        t0.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                t0_x = (double) event.getRawX();
                t0_y = (double) event.getRawY();

                t_size[current_pointer] = 0.5 - (double) event.getSize();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                    starttime[current_pointer] = lastDown;
                    if((double) event.getPressure() > 1){                         pressure_t = (double) event.getPressure() / 1000;                     }else{                         pressure_t = (double) event.getPressure();                     }                     t_pressure[current_pointer] = 1 - pressure_t;
                    if (current_pointer != 0) {
                        difference[current_pointer - 1] = 0.35 + ((starttime[current_pointer] - starttime[current_pointer - 1] - presstime[current_pointer - 1] * 1000) / 1000);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    t0_keyPressedDuration = ((System.currentTimeMillis() - lastDown) / 1000);
                    presstime[current_pointer] = 0.3 - t0_keyPressedDuration;
                    if (current_pointer != 0) {
                        digraph[current_pointer - 1] = 0.9 - (presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1]);
                        if (current_pointer > 1) {
                            trigraph[current_pointer - 2] = 1.3 - (presstime[current_pointer - 2] + presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1] + difference[current_pointer - 2]);
                        }
                    }
                }
                return false;
            }

        });
        t1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                t1_x = (double) event.getRawX();
                t1_y = (double) event.getRawY();

                t_size[current_pointer] = 0.5 - (double) event.getSize();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                    starttime[current_pointer] = lastDown;
                    if((double) event.getPressure() > 1){                         pressure_t = (double) event.getPressure() / 1000;                     }else{                         pressure_t = (double) event.getPressure();                     }                     t_pressure[current_pointer] = 1 - pressure_t;
                    if (current_pointer != 0) {
                        difference[current_pointer - 1] = 0.35 + ((starttime[current_pointer] - starttime[current_pointer - 1] - presstime[current_pointer - 1] * 1000) / 1000);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    t1_keyPressedDuration = ((System.currentTimeMillis() - lastDown) / 1000);
                    presstime[current_pointer] = 0.3 - t1_keyPressedDuration;
                    if (current_pointer != 0) {
                        digraph[current_pointer - 1] = 0.9 - (presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1]);
                        if (current_pointer > 1) {
                            trigraph[current_pointer - 2] = 1.3 - (presstime[current_pointer - 2] + presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1] + difference[current_pointer - 2]);
                        }
                    }
                }
                return false;
            }

        });
        t2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                t2_x = (double) event.getRawX();
                t2_y = (double) event.getRawY();

                t_size[current_pointer] = 0.5 - (double) event.getSize();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                    starttime[current_pointer] = lastDown;
                    if((double) event.getPressure() > 1){                         pressure_t = (double) event.getPressure() / 1000;                     }else{                         pressure_t = (double) event.getPressure();                     }                     t_pressure[current_pointer] = 1 - pressure_t;
                    if (current_pointer != 0) {
                        difference[current_pointer - 1] = 0.35 + ((starttime[current_pointer] - starttime[current_pointer - 1] - presstime[current_pointer - 1] * 1000) / 1000);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    t2_keyPressedDuration = ((System.currentTimeMillis() - lastDown) / 1000);
                    presstime[current_pointer] = 0.3 - t2_keyPressedDuration;
                    if (current_pointer != 0) {
                        digraph[current_pointer - 1] = 0.9 - (presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1]);
                        if (current_pointer > 1) {
                            trigraph[current_pointer - 2] = 1.3 - (presstime[current_pointer - 2] + presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1] + difference[current_pointer - 2]);
                        }
                    }
                }
                return false;
            }

        });
        t3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                t3_x = (double) event.getRawX();
                t3_y = (double) event.getRawY();

                t_size[current_pointer] = 0.5 - (double) event.getSize();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                    starttime[current_pointer] = lastDown;
                    if((double) event.getPressure() > 1){                         pressure_t = (double) event.getPressure() / 1000;                     }else{                         pressure_t = (double) event.getPressure();                     }                     t_pressure[current_pointer] = 1 - pressure_t;
                    if (current_pointer != 0) {
                        difference[current_pointer - 1] = 0.35 + ((starttime[current_pointer] - starttime[current_pointer - 1] - presstime[current_pointer - 1] * 1000) / 1000);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    t3_keyPressedDuration = ((System.currentTimeMillis() - lastDown) / 1000);
                    presstime[current_pointer] = 0.3 - t3_keyPressedDuration;
                    if (current_pointer != 0) {
                        digraph[current_pointer - 1] = 0.9 - (presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1]);
                        if (current_pointer > 1) {
                            trigraph[current_pointer - 2] = 1.3 - (presstime[current_pointer - 2] + presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1] + difference[current_pointer - 2]);
                        }
                    }
                }
                return false;
            }

        });
        t4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                t4_x = (double) event.getRawX();
                t4_y = (double) event.getRawY();

                t_size[current_pointer] = 0.5 - (double) event.getSize();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                    starttime[current_pointer] = lastDown;
                    if((double) event.getPressure() > 1){                         pressure_t = (double) event.getPressure() / 1000;                     }else{                         pressure_t = (double) event.getPressure();                     }                     t_pressure[current_pointer] = 1 - pressure_t;
                    if (current_pointer != 0) {
                        difference[current_pointer - 1] = 0.35 + ((starttime[current_pointer] - starttime[current_pointer - 1] - presstime[current_pointer - 1] * 1000) / 1000);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    t4_keyPressedDuration = ((System.currentTimeMillis() - lastDown) / 1000);
                    presstime[current_pointer] = 0.3 - t4_keyPressedDuration;
                    if (current_pointer != 0) {
                        digraph[current_pointer - 1] = 0.9 - (presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1]);
                        if (current_pointer > 1) {
                            trigraph[current_pointer - 2] = 1.3 - (presstime[current_pointer - 2] + presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1] + difference[current_pointer - 2]);
                        }
                    }
                }
                return false;
            }

        });
        t5.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                t5_x = (double) event.getRawX();
                t5_y = (double) event.getRawY();

                t_size[current_pointer] = 0.5 - (double) event.getSize();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                    starttime[current_pointer] = lastDown;
                    if((double) event.getPressure() > 1){                         pressure_t = (double) event.getPressure() / 1000;                     }else{                         pressure_t = (double) event.getPressure();                     }                     t_pressure[current_pointer] = 1 - pressure_t;
                    if (current_pointer != 0) {
                        difference[current_pointer - 1] = 0.35 + ((starttime[current_pointer] - starttime[current_pointer - 1] - presstime[current_pointer - 1] * 1000) / 1000);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    t5_keyPressedDuration = ((System.currentTimeMillis() - lastDown) / 1000);
                    presstime[current_pointer] = 0.3 - t5_keyPressedDuration;
                    if (current_pointer != 0) {
                        digraph[current_pointer - 1] = 0.9 - (presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1]);
                        if (current_pointer > 1) {
                            trigraph[current_pointer - 2] = 1.3 - (presstime[current_pointer - 2] + presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1] + difference[current_pointer - 2]);
                        }
                    }
                }
                return false;
            }

        });
        t6.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                t6_x = (double) event.getRawX();
                t6_y = (double) event.getRawY();

                t_size[current_pointer] = 0.5 - (double) event.getSize();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                    starttime[current_pointer] = lastDown;
                    if((double) event.getPressure() > 1){                         pressure_t = (double) event.getPressure() / 1000;                     }else{                         pressure_t = (double) event.getPressure();                     }                     t_pressure[current_pointer] = 1 - pressure_t;
                    if (current_pointer != 0) {
                        difference[current_pointer - 1] = 0.35 + ((starttime[current_pointer] - starttime[current_pointer - 1] - presstime[current_pointer - 1] * 1000) / 1000);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    t6_keyPressedDuration = ((System.currentTimeMillis() - lastDown) / 1000);
                    presstime[current_pointer] = 0.3 - t6_keyPressedDuration;
                    if (current_pointer != 0) {
                        digraph[current_pointer - 1] = 0.9 - (presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1]);
                        if (current_pointer > 1) {
                            trigraph[current_pointer - 2] = 1.3 - (presstime[current_pointer - 2] + presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1] + difference[current_pointer - 2]);
                        }
                    }
                }
                return false;
            }

        });
        t7.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                t7_x = (double) event.getRawX();
                t7_y = (double) event.getRawY();

                t_size[current_pointer] = 0.5 - (double) event.getSize();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                    starttime[current_pointer] = lastDown;
                    if((double) event.getPressure() > 1){                         pressure_t = (double) event.getPressure() / 1000;                     }else{                         pressure_t = (double) event.getPressure();                     }                     t_pressure[current_pointer] = 1 - pressure_t;
                    if (current_pointer != 0) {
                        difference[current_pointer - 1] = 0.35 + ((starttime[current_pointer] - starttime[current_pointer - 1] - presstime[current_pointer - 1] * 1000) / 1000);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    t7_keyPressedDuration = ((System.currentTimeMillis() - lastDown) / 1000);
                    presstime[current_pointer] = 0.3 - t7_keyPressedDuration;
                    if (current_pointer != 0) {
                        digraph[current_pointer - 1] = 0.9 - (presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1]);
                        if (current_pointer > 1) {
                            trigraph[current_pointer - 2] = 1.3 - (presstime[current_pointer - 2] + presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1] + difference[current_pointer - 2]);
                        }
                    }
                }
                return false;
            }

        });
        t8.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                t8_x = (double) event.getRawX();
                t8_y = (double) event.getRawY();

                t_size[current_pointer] = 0.5 - (double) event.getSize();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                    starttime[current_pointer] = lastDown;
                    if((double) event.getPressure() > 1){                         pressure_t = (double) event.getPressure() / 1000;                     }else{                         pressure_t = (double) event.getPressure();                     }                     t_pressure[current_pointer] = 1 - pressure_t;
                    if (current_pointer != 0) {
                        difference[current_pointer - 1] = 0.35 + ((starttime[current_pointer] - starttime[current_pointer - 1] - presstime[current_pointer - 1] * 1000) / 1000);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    t8_keyPressedDuration = ((System.currentTimeMillis() - lastDown) / 1000);
                    presstime[current_pointer] = 0.3 - t8_keyPressedDuration;
                    if (current_pointer != 0) {
                        digraph[current_pointer - 1] = 0.9 - (presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1]);
                        if (current_pointer > 1) {
                            trigraph[current_pointer - 2] = 1.3 - (presstime[current_pointer - 2] + presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1] + difference[current_pointer - 2]);
                        }
                    }
                }
                return false;
            }

        });
        t9.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                t9_x = (double) event.getRawX();
                t9_y = (double) event.getRawY();

                t_size[current_pointer] = 0.5 - (double) event.getSize();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                    starttime[current_pointer] = lastDown; 
                    if((double) event.getPressure() > 1){
                        pressure_t = (double) event.getPressure() / 1000;
                    }else{
                        pressure_t = (double) event.getPressure();
                    }
                    t_pressure[current_pointer] = 1 - pressure_t;
                    if (current_pointer != 0) {
                        difference[current_pointer - 1] = 0.35 + ((starttime[current_pointer] - starttime[current_pointer - 1] - presstime[current_pointer - 1] * 1000) / 1000);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    t9_keyPressedDuration = ((System.currentTimeMillis() - lastDown) / 1000);
                    presstime[current_pointer] = 0.3 - t9_keyPressedDuration;
                    if (current_pointer != 0) {
                        digraph[current_pointer - 1] = 0.9 - (presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1]);
                        if (current_pointer > 1) {
                            trigraph[current_pointer - 2] = 1.3 - (presstime[current_pointer - 2] + presstime[current_pointer - 1] + presstime[current_pointer] + difference[current_pointer - 1] + difference[current_pointer - 2]);
                        }
                    }
                }
                return false;
            }

        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checksum && current_pointer >= app_password.length()) {
                    DBHelper mydbhelper = new DBHelper(MainActivity.this, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
                    SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
                    ContentValues cv = new ContentValues();

                    cv.put(Database.b1_x, t_x[0]);
                    cv.put(Database.b1_y, t_y[0]);
                    cv.put(Database.b2_x, t_x[1]);
                    cv.put(Database.b2_y, t_y[1]);
                    cv.put(Database.b3_x, t_x[2]);
                    cv.put(Database.b3_y, t_y[2]);
                    cv.put(Database.b4_x, t_x[3]);
                    cv.put(Database.b4_y, t_y[3]);
                    cv.put(Database.b5_x, t_x[4]);
                    cv.put(Database.b5_y, t_y[4]);
                    cv.put(Database.b6_x, t_x[5]);
                    cv.put(Database.b6_y, t_y[5]);
                    cv.put(Database.b1_size, t_size[0]);
                    cv.put(Database.b2_size, t_size[1]);
                    cv.put(Database.b3_size, t_size[2]);
                    cv.put(Database.b4_size, t_size[3]);
                    cv.put(Database.b5_size, t_size[4]);
                    cv.put(Database.b6_size, t_size[5]);
                    cv.put(Database.p1_press, presstime[0]);
                    cv.put(Database.p2_press, presstime[1]);
                    cv.put(Database.p3_press, presstime[2]);
                    cv.put(Database.p4_press, presstime[3]);
                    cv.put(Database.p5_press, presstime[4]);
                    cv.put(Database.p6_press, presstime[5]);
                    cv.put(Database.b1_pressure, t_pressure[0]);
                    cv.put(Database.b2_pressure, t_pressure[1]);
                    cv.put(Database.b3_pressure, t_pressure[2]);
                    cv.put(Database.b4_pressure, t_pressure[3]);
                    cv.put(Database.b5_pressure, t_pressure[4]);
                    cv.put(Database.b6_pressure, t_pressure[5]);
                    cv.put(Database.p1_p2_flight, difference[0]);
                    cv.put(Database.p2_p3_flight, difference[1]);
                    cv.put(Database.p3_p4_flight, difference[2]);
                    cv.put(Database.p4_p5_flight, difference[3]);
                    cv.put(Database.p5_p6_flight, difference[4]);
                    cv.put(Database.p1_p2_trigraph, trigraph[0]);
                    cv.put(Database.p2_p3_trigraph, trigraph[1]);
                    cv.put(Database.p3_p4_trigraph, trigraph[2]);
                    cv.put(Database.p4_p5_trigraph, trigraph[3]);
                    cv.put(Database.p1_p2_digraph, digraph[0]);
                    cv.put(Database.p2_p3_digraph, digraph[1]);
                    cv.put(Database.p3_p4_digraph, digraph[2]);
                    cv.put(Database.p4_p5_digraph, digraph[3]);
                    cv.put(Database.p5_p6_digraph, digraph[4]);
                    //total_time = (difference[0]) + (difference[1]) + (difference[2]) + (difference[3]) + (presstime[0]) + (presstime[1]) + (presstime[2]) + (presstime[3]) + (presstime[4])+ (presstime[5]);;
                    total_time = 0;
                    cv.put(Database.total_time, total_time);

                    long r = mydatabase.insertOrThrow(Database.TABLE_NAME, null, cv);
                    if (r > 0) {

                        Toast.makeText(MainActivity.this, "Keystroke Data Fetched Successfully", Toast.LENGTH_SHORT).show();

                        Cursor cur = mydatabase.rawQuery("SELECT * from " + Database.TABLE_NAME, null);
                        int count = cur.getCount();
                        Cursor cur_training = mydatabase.rawQuery("SELECT * from " + Database.TRAINING_TABLE_NAME, null);
                        int count_training = cur_training.getCount();
                        String col[] = new String[]{Database.TRAINING_STATUS};
                        Cursor c = mydatabase.query(Database.TRAINING_TABLE_NAME, col, null, null, null, null, null);
                        c.moveToLast();
                        int stat = Integer.parseInt(c.getString(0));


                        if(count_training < 31 || stat == 0) {
                            if (count < 30) {
                                ContentValues cval = new ContentValues();
                                cval.put(Database.TRAINING_STATUS, 0+"");
                                mydatabase.insertOrThrow(Database.TRAINING_TABLE_NAME, null, cval);
                                right_wrong.setTextColor(Color.GREEN);
                                right_wrong.setText("Attempt No." + count + " was approved");
                                initialiseVariables();
                            } else {
                                /** Creating a progress dialog window */
                                mProgressDialog = new ProgressDialog(MainActivity.this);

                                /** Close the dialog window on pressing back button */
                                mProgressDialog.setCancelable(false);

                                /** Setting a spinner style progress bar */
                                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                mProgressDialog.show();
                                mProgressDialog.setMessage(" Training the Network ");
                                final Handler mHandler = new Handler(Looper.getMainLooper());
                                final Timer timer = new Timer();
                                timer.schedule(new TimerTask() {
                                    public void run() {
                                        mHandler.post(new Runnable() {
                                            public void run() {

                                                trainingData training = new trainingData(MainActivity.this);
                                                training.generateLegitimateDataset();
                                                Log.e("Data  : ", "Dataset Loaded Successfully");
                                                trainingActivity ta = new trainingActivity(MainActivity.this);
                                                ta.performTraining();
                                                mProgressDialog.dismiss();

                                            }
                                        });
                                        timer.cancel();
                                    }
                                }, 100);
                                right_wrong.setTextColor(Color.GREEN);
                                right_wrong.setText("Training Completed Successfully..!!");
                                ContentValues cval = new ContentValues();
                                cval.put(Database.TRAINING_STATUS, 1 + "");
                                mydatabase.insertOrThrow(Database.TRAINING_TABLE_NAME, null, cval);
                            }
                        }else{
                            attempt.setText("Testing Phase ");
                            startTesting();
                            initialiseVariables();
                        }

                    } else {
                        Cursor cur = mydatabase.rawQuery("SELECT * from " + Database.TABLE_NAME, null);
                        int count = cur.getCount();
                        Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        initialiseVariables();
                        right_wrong.setTextColor(Color.RED);
                        right_wrong.setText("Attempt no." + count + " failed. Please retry.");
                        attempt.setText("#Attempt no. " + count);
                    }
                } else {

                    Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    initialiseVariables();
                    right_wrong.setTextColor(Color.RED);
                    right_wrong.setText("Attempt failed. Please retry.");

                }
            }
        });
        viewRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialiseVariables();
            }
        });

    }

    private void startTesting() {
        errorBackPropagation e = new errorBackPropagation(MainActivity.this);

        e.initialiseHiddenLayer();
        e.initialiseInputLayer();
        e.initialiseOutputLayer();
        e.initialiseWeightsAndBias();
        e.initialiseTargetOutput();
        e.setLegitimatetargetOutput();
        e.initialiseInputForHiddenLayer();
        e.initialiseInputForOutputLayer();

        e.getFinalWeightsFromInputToHidden(MainActivity.this);
        e.getFinalWeightsFromHiddenToOutput();
        //e.getFinalBiasHidden();
        //e.getFinalBiasOutput();

        input_layer = new double[45];
        setInputLayer();
        e.getInputLayer(input_layer);
        e.functionHiddenLayer();
        e.functionOutputLayerTesting();


    }

    private void normalizeData(int i) {

        switch (i) {
            case 0:
                t0.getLocationOnScreen(location);
                t0.post(new Runnable() {
                    public void run() {

                        contentWidth = t0.getWidth() + location[0];
                        contentHeight = t0.getHeight() + location[1];
                        if (contentWidth != 0) {
                            t0_x = ((t0_x - location[0]) / (contentWidth - location[0])) / 10;
                            t0_y = ((t0_y - location[1]) / (contentHeight - location[1])) / 10;
                            t_x[current_pointer - 1] = t0_x;
                            t_y[current_pointer - 1] = t0_y;

                        }

                    }
                });
                break;
            case 1:
                t1.getLocationOnScreen(location);
                t1.post(new Runnable() {
                    public void run() {

                        contentWidth = t1.getWidth() + location[0];
                        contentHeight = t1.getHeight() + location[1];
                        if (contentWidth != 0) {
                            t1_x = ((t1_x - location[0]) / (contentWidth - location[0])) / 10;
                            t1_y = ((t1_y - location[1]) / (contentHeight - location[1])) / 10;
                            t_x[current_pointer - 1] = t1_x;
                            t_y[current_pointer - 1] = t1_y;
                        }
                    }
                });

                break;
            case 2:
                t2.getLocationOnScreen(location);
                t2.post(new Runnable() {
                    public void run() {

                        contentHeight = t2.getHeight() + location[1];
                        contentWidth = t2.getWidth() + location[0];
                        if (contentWidth != 0) {

                            t2_x = ((t2_x - location[0]) / (contentWidth - location[0])) / 10;
                            t2_y = ((t2_y - location[1]) / (contentHeight - location[1])) / 10;
                            t_x[current_pointer - 1] = t2_x;
                            t_y[current_pointer - 1] = t2_y;
                        }

                    }
                });
                break;
            case 3:
                t3.getLocationOnScreen(location);
                t3.post(new Runnable() {
                    public void run() {

                        contentHeight = t3.getHeight() + location[1];
                        contentWidth = t3.getWidth() + location[0];
                        if (contentWidth != 0) {
                            t3_x = ((t3_x - location[0]) / (contentWidth - location[0])) / 10;
                            t3_y = ((t3_y - location[1]) / (contentHeight - location[1])) / 10;
                            t_x[current_pointer - 1] = t3_x;
                            t_y[current_pointer - 1] = t3_y;
                        }
                    }
                });
                break;
            case 4:
                t4.getLocationOnScreen(location);
                t4.post(new Runnable() {
                    public void run() {

                        contentHeight = t4.getHeight() + location[1];
                        contentWidth = t4.getWidth() + location[0];
                        if (contentWidth != 0) {
                            t4_x = ((t4_x - location[0]) / (contentWidth - location[0])) / 10;
                            t4_y = ((t4_y - location[1]) / (contentHeight - location[1])) / 10;
                            t_x[current_pointer - 1] = t4_x;
                            t_y[current_pointer - 1] = t4_y;
                        }
                    }
                });
                break;
            case 5:
                t5.getLocationOnScreen(location);
                t5.post(new Runnable() {
                    public void run() {

                        contentHeight = t5.getHeight() + location[1];
                        contentWidth = t5.getWidth() + location[0];
                        if (contentWidth != 0) {
                            t5_x = ((t5_x - location[0]) / (contentWidth - location[0])) / 10;
                            t5_y = ((t5_y - location[1]) / (contentHeight - location[1])) / 10;
                            t_x[current_pointer - 1] = t5_x;
                            t_y[current_pointer - 1] = t5_y;
                        }
                    }
                });
                break;
            case 6:
                t6.getLocationOnScreen(location);
                t6.post(new Runnable() {
                    public void run() {

                        contentHeight = t6.getHeight() + location[1];
                        contentWidth = t6.getWidth() + location[0];
                        if (contentWidth != 0) {
                            t6_x = ((t6_x - location[0]) / (contentWidth - location[0])) / 10;
                            t6_y = ((t6_y - location[1]) / (contentHeight - location[1])) / 10;
                            t_x[current_pointer - 1] = t6_x;
                            t_y[current_pointer - 1] = t6_y;
                        }
                    }
                });
                break;
            case 7:
                t7.getLocationOnScreen(location);
                t7.post(new Runnable() {
                    public void run() {

                        contentHeight = t7.getHeight() + location[1];
                        contentWidth = t7.getWidth() + location[0];
                        if (contentWidth != 0) {
                            t7_x = ((t7_x - location[0]) / (contentWidth - location[0])) / 10;
                            t7_y = ((t7_y - location[1]) / (contentHeight - location[1])) / 10;
                            t_x[current_pointer - 1] = t7_x;
                            t_y[current_pointer - 1] = t7_y;
                        }
                    }
                });
                break;
            case 8:
                t8.getLocationOnScreen(location);
                t8.post(new Runnable() {
                    public void run() {

                        contentHeight = t8.getHeight() + location[1];
                        contentWidth = t8.getWidth() + location[0];
                        if (contentWidth != 0) {
                            t8_x = ((t8_x - location[0]) / (contentWidth - location[0])) / 10;
                            t8_y = ((t8_y - location[1]) / (contentHeight - location[1])) / 10;
                            t_x[current_pointer - 1] = t8_x;
                            t_y[current_pointer - 1] = t8_y;
                        }
                    }
                });
                break;
            case 9:
                t9.getLocationOnScreen(location);
                t9.post(new Runnable() {
                    public void run() {

                        contentHeight = t9.getHeight() + location[1];
                        contentWidth = t9.getWidth() + location[0];
                        if (contentWidth != 0) {
                            t9_x = ((t9_x - location[0]) / (contentWidth - location[0])) / 10;
                            t9_y = ((t9_y - location[1]) / (contentHeight - location[1])) / 10;
                            t_x[current_pointer - 1] = t9_x;
                            t_y[current_pointer - 1] = t9_y;
                        }
                    }
                });
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.keystroke:
                Intent i = new Intent(MainActivity.this, showValues.class);
                startActivity(i);
                return true;
            case R.id.weight:
                Intent in = new Intent(MainActivity.this, showweights.class);
                startActivity(in);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setInputLayer() {
        int q = 0;
        input_layer[q++] = t_x[0];
        input_layer[q++] = t_y[0];
        input_layer[q++] = t_x[1];
        input_layer[q++] = t_y[1];
        input_layer[q++] = t_x[2];
        input_layer[q++] = t_y[2];
        input_layer[q++] = t_x[3];
        input_layer[q++] = t_y[3];
        input_layer[q++] = t_x[4];
        input_layer[q++] = t_y[4];
        input_layer[q++] = t_x[5];
        input_layer[q++] = t_y[5];
        input_layer[q++] = t_size[0];
        input_layer[q++] = t_size[1];
        input_layer[q++] = t_size[2];
        input_layer[q++] = t_size[3];
        input_layer[q++] = t_size[4];
        input_layer[q++] = t_size[5];
        input_layer[q++] = t_pressure[0];
        input_layer[q++] = t_pressure[1];
        input_layer[q++] = t_pressure[2];
        input_layer[q++] = t_pressure[3];
        input_layer[q++] = t_pressure[4];
        input_layer[q++] = t_pressure[5];
        input_layer[q++] = presstime[0];
        input_layer[q++] = presstime[1];
        input_layer[q++] = presstime[2];
        input_layer[q++] = presstime[3];
        input_layer[q++] = presstime[4];
        input_layer[q++] = presstime[5];
        input_layer[q++] = difference[0];
        input_layer[q++] = difference[1];
        input_layer[q++] = difference[2];
        input_layer[q++] = difference[3];
        input_layer[q++] = difference[4];
        input_layer[q++] = trigraph[0];
        input_layer[q++] = trigraph[1];
        input_layer[q++] = trigraph[2];
        input_layer[q++] = trigraph[3];
        input_layer[q++] = digraph[0];
        input_layer[q++] = digraph[1];
        input_layer[q++] = digraph[2];
        input_layer[q++] = digraph[3];
        input_layer[q++] = digraph[4];
        input_layer[q++] = total_time;
        q = 0;
        String print = "";
        for (int i = 0; i < 45; i++)
            print += input_layer[i] + ",";

        Log.e("login activity", print);

    }


    private void initialiseVariables() {
        DBHelper mydbhelper = new DBHelper(this, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        Cursor cur = mydatabase.rawQuery("SELECT * from " + Database.PASSWORD_TABLE_NAME, null);
        int count = cur.getCount();
        if (count == 0) {
            //set Password
            setPassword();
        } else {
            //retrieve password
            String col[] = new String[]{Database.password};
            Cursor c = mydatabase.query(Database.PASSWORD_TABLE_NAME, col, null, null, null, null, null);
            c.moveToLast();
            app_password = c.getString(0);
        }
        cur = mydatabase.rawQuery("SELECT * from " + Database.TABLE_NAME, null);
        count = cur.getCount();
        attempt.setText("#Attempt No. " + (count + 1));


        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        e5.setText("");
        e6.setText("");


        t1.setEnabled(true);
        t2.setEnabled(true);
        t3.setEnabled(true);
        t4.setEnabled(true);
        t5.setEnabled(true);
        t6.setEnabled(true);
        t7.setEnabled(true);
        t8.setEnabled(true);
        t9.setEnabled(true);
        t0.setEnabled(true);
        ok.setEnabled(false);

        t1_y = 0;
        t1_x = 0;
        t2_y = 0;
        t2_x = 0;
        t3_y = 0;
        t3_x = 0;
        t3_y = 0;
        t3_x = 0;
        t4_y = 0;
        t4_x = 0;
        t5_y = 0;
        t5_x = 0;
        t6_y = 0;
        t6_x = 0;
        t7_y = 0;
        t7_x = 0;
        t8_y = 0;
        t8_x = 0;
        t9_y = 0;
        t9_x = 0;
        t0_y = 0;
        t0_x = 0;

        viewRecords.setEnabled(true);
        current_pointer = 0;
        checksum = true;
        starttime = new double[6];
        presstime = new double[6];
        difference = new double[6];
        digraph = new double[6];
        trigraph = new double[6];
        t_x = new double[6];
        t_y = new double[6];
        t_size = new double[6];
        t_pressure = new double[6];
        t_keypressedDuration = new double[6];
        total_time = 0;
    }

    private void checkPassword(String s) {
        char[] pass = new char[app_password.length()];
        pass = app_password.toCharArray();
        if (current_pointer <= 6) {
            switch (current_pointer) {
                case 0:
                    e1.setText("*");

                    if (Integer.parseInt(pass[0] + "") != Integer.parseInt(s)) {

                        checksum = false;
                        break;
                    } else {


                        break;
                    }
                case 1:
                    e2.setText("*");

                    if (Integer.parseInt(pass[1] + "") != Integer.parseInt(s)) {

                        checksum = false;
                        break;
                    } else {

                        break;
                    }
                case 2:
                    e3.setText("*");

                    if (Integer.parseInt(pass[2] + "") != Integer.parseInt(s)) {
                        checksum = false;
                        break;
                    } else {

                        break;
                    }
                case 3:
                    e4.setText("*");

                    if (Integer.parseInt(pass[3] + "") != Integer.parseInt(s)) {
                        checksum = false;
                        break;
                    } else {

                        break;
                    }
                case 4:
                    e5.setText("*");

                    if (Integer.parseInt(pass[4] + "") != Integer.parseInt(s)) {
                        checksum = false;
                        break;
                    } else {

                        break;
                    }
                case 5:
                    e6.setText("*");

                    if (Integer.parseInt(pass[5] + "") != Integer.parseInt(s)) {
                        checksum = false;
                        break;
                    } else {

                        t1.setEnabled(false);
                        t2.setEnabled(false);
                        t3.setEnabled(false);
                        t4.setEnabled(false);
                        t5.setEnabled(false);
                        t6.setEnabled(false);
                        t7.setEnabled(false);
                        t8.setEnabled(false);
                        t9.setEnabled(false);
                        t0.setEnabled(false);
                        ok.setEnabled(true);
                        viewRecords.setEnabled(true);
                        break;
                    }
            }
            current_pointer++;
        }
    }

    private void setPassword() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Instructions");
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.instructions, null);
        alertDialogBuilder.setPositiveButton("Set Password", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("Set Password");
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(R.layout.set_password, null);
                alertDialogBuilder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Dialog d = (Dialog) arg0;
                        EditText password = (EditText) d.findViewById(R.id.password);
                        EditText retype_password = (EditText) d.findViewById(R.id.retype_password);
                        if(password.getText().toString().length()==6) {

                            if (password.getText().toString().equals(retype_password.getText().toString())) {
                                app_password = password.getText().toString();
                                DBHelper mydbhelper = new DBHelper(MainActivity.this, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
                                SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
                                ContentValues c = new ContentValues();
                                c.put(Database.password, app_password);
                                mydatabase.insertOrThrow(Database.PASSWORD_TABLE_NAME, null, c);
                                ContentValues cv = new ContentValues();
                                cv.put(Database.TRAINING_STATUS, 0+"");
                                mydatabase.insertOrThrow(Database.TRAINING_TABLE_NAME, null, cv);
                                mydbhelper.close();
                                Toast.makeText(MainActivity.this, "Password set successfully", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(MainActivity.this, "Passwords do not match. Please try again", Toast.LENGTH_SHORT).show();
                                setPassword();

                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Password Length should be 6 Characters only.", Toast.LENGTH_SHORT).show();
                            setPassword();
                            }
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setView(view);
                alertDialog.show();


            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setView(view);
        alertDialog.show();

    }
}
