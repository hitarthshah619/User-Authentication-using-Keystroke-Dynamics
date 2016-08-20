package com.example.keystrokedynamics;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String c="CREATE TABLE "+Database.TABLE_NAME+" ( "+
                Database.b1_x+" TEXT,"+
                Database.b1_y+" TEXT,"+
                Database.b2_x+" TEXT,"+
                Database.b2_y+" TEXT,"+
                Database.b3_x+" TEXT,"+
                Database.b3_y+" TEXT,"+
                Database.b4_x+" TEXT,"+
                Database.b4_y+" TEXT,"+
                Database.b5_x+" TEXT,"+
                Database.b5_y+" TEXT,"+
                Database.b6_x+" TEXT,"+
                Database.b6_y+" TEXT,"+
                Database.b1_size+" TEXT,"+
                Database.b2_size+" TEXT,"+
                Database.b3_size+" TEXT,"+
                Database.b4_size+" TEXT,"+
                Database.b5_size+" TEXT,"+
                Database.b6_size+" TEXT,"+
                Database.p1_press+" TEXT,"+
                Database.p2_press+" TEXT,"+
                Database.p3_press+" TEXT,"+
                Database.p4_press+" TEXT,"+
                Database.p5_press+" TEXT,"+
                Database.p6_press+" TEXT,"+
                Database.b1_pressure+" TEXT,"+
                Database.b2_pressure+" TEXT,"+
                Database.b3_pressure+" TEXT,"+
                Database.b4_pressure+" TEXT,"+
                Database.b5_pressure+" TEXT,"+
                Database.b6_pressure+" TEXT,"+
                Database.p1_p2_flight+" TEXT,"+
                Database.p2_p3_flight+" TEXT,"+
                Database.p3_p4_flight+" TEXT,"+
                Database.p4_p5_flight+" TEXT,"+
                Database.p5_p6_flight+" TEXT,"+
                Database.p1_p2_digraph+" TEXT,"+
                Database.p2_p3_digraph+" TEXT,"+
                Database.p3_p4_digraph+" TEXT,"+
                Database.p4_p5_digraph+" TEXT,"+
                Database.p5_p6_digraph+" TEXT,"+
                Database.p1_p2_trigraph+" TEXT,"+
                Database.p2_p3_trigraph+" TEXT,"+
                Database.p3_p4_trigraph+" TEXT,"+
                Database.p4_p5_trigraph+" TEXT,"+
                Database.total_time+" TEXT );";
        db.execSQL(c);

            String cl="CREATE TABLE "+Database.ILLEGITIMATE_TABLE_NAME+" ( "+
                    Database.b1_x+" TEXT,"+
                    Database.b1_y+" TEXT,"+
                    Database.b2_x+" TEXT,"+
                    Database.b2_y+" TEXT,"+
                    Database.b3_x+" TEXT,"+
                    Database.b3_y+" TEXT,"+
                    Database.b4_x+" TEXT,"+
                    Database.b4_y+" TEXT,"+
                    Database.b5_x+" TEXT,"+
                    Database.b5_y+" TEXT,"+
                    Database.b6_x+" TEXT,"+
                    Database.b6_y+" TEXT,"+
                    Database.b1_size+" TEXT,"+
                    Database.b2_size+" TEXT,"+
                    Database.b3_size+" TEXT,"+
                    Database.b4_size+" TEXT,"+
                    Database.b5_size+" TEXT,"+
                    Database.b6_size+" TEXT,"+
                    Database.p1_press+" TEXT,"+
                    Database.p2_press+" TEXT,"+
                    Database.p3_press+" TEXT,"+
                    Database.p4_press+" TEXT,"+
                    Database.p5_press+" TEXT,"+
                    Database.p6_press+" TEXT,"+
                    Database.b1_pressure+" TEXT,"+
                    Database.b2_pressure+" TEXT,"+
                    Database.b3_pressure+" TEXT,"+
                    Database.b4_pressure+" TEXT,"+
                    Database.b5_pressure+" TEXT,"+
                    Database.b6_pressure+" TEXT,"+
                    Database.p1_p2_flight+" TEXT,"+
                    Database.p2_p3_flight+" TEXT,"+
                    Database.p3_p4_flight+" TEXT,"+
                    Database.p4_p5_flight+" TEXT,"+
                    Database.p5_p6_flight+" TEXT,"+
                    Database.p1_p2_digraph+" TEXT,"+
                    Database.p2_p3_digraph+" TEXT,"+
                    Database.p3_p4_digraph+" TEXT,"+
                    Database.p4_p5_digraph+" TEXT,"+
                    Database.p5_p6_digraph+" TEXT,"+
                    Database.p1_p2_trigraph+" TEXT,"+
                    Database.p2_p3_trigraph+" TEXT,"+
                    Database.p3_p4_trigraph+" TEXT,"+
                    Database.p4_p5_trigraph+" TEXT,"+
                    Database.total_time+" TEXT );";
            db.execSQL(cl);

        String p="CREATE TABLE "+Database.PASSWORD_TABLE_NAME+" ( "+
                Database.password + " TEXT );";
        db.execSQL(p);

            String n="CREATE TABLE "+DatabaseNetwork.NETWORK_TABLE_NAME+" ( "+
                    DatabaseNetwork.wih11+" TEXT,"+
                    DatabaseNetwork.wih21+" TEXT,"+
                    DatabaseNetwork.wih31+" TEXT,"+
                    DatabaseNetwork.wih41+" TEXT,"+
                    DatabaseNetwork.wih51+" TEXT,"+
                    DatabaseNetwork.wih61+" TEXT,"+
                    DatabaseNetwork.wih71+" TEXT,"+
                    DatabaseNetwork.wih81+" TEXT,"+
                    DatabaseNetwork.wih91+" TEXT,"+
                    DatabaseNetwork.wih101+" TEXT,"+
                    DatabaseNetwork.wih111+" TEXT,"+
                    DatabaseNetwork.wih121+" TEXT,"+
                    DatabaseNetwork.wih131+" TEXT,"+
                    DatabaseNetwork.wih141+" TEXT,"+
                    DatabaseNetwork.wih151+" TEXT,"+
                    DatabaseNetwork.wih161+" TEXT,"+
                    DatabaseNetwork.wih171+" TEXT,"+
                    DatabaseNetwork.wih181+" TEXT,"+
                    DatabaseNetwork.wih191+" TEXT,"+
                    DatabaseNetwork.wih201+" TEXT,"+
                    DatabaseNetwork.wih211+" TEXT,"+
                    DatabaseNetwork.wih221+" TEXT,"+
                    DatabaseNetwork.wih231+" TEXT,"+
                    DatabaseNetwork.wih241+" TEXT,"+
                    DatabaseNetwork.wih251+" TEXT,"+
                    DatabaseNetwork.wih261+" TEXT,"+
                    DatabaseNetwork.wih271+" TEXT,"+
                    DatabaseNetwork.wih281+" TEXT,"+
                    DatabaseNetwork.wih291+" TEXT,"+
                    DatabaseNetwork.wih301+" TEXT,"+
                    DatabaseNetwork.wih311+" TEXT,"+
                    DatabaseNetwork.wih321+" TEXT,"+
                    DatabaseNetwork.wih331+" TEXT,"+
                    DatabaseNetwork.wih341+" TEXT,"+
                    DatabaseNetwork.wih351+" TEXT,"+
                    DatabaseNetwork.wih361+" TEXT,"+
                    DatabaseNetwork.wih371+" TEXT,"+
                    DatabaseNetwork.wih381+" TEXT,"+
                    DatabaseNetwork.wih391+" TEXT,"+
                    DatabaseNetwork.wih401+" TEXT,"+
                    DatabaseNetwork.wih411+" TEXT,"+
                    DatabaseNetwork.wih421+" TEXT,"+
                    DatabaseNetwork.wih431+" TEXT,"+
                    DatabaseNetwork.wih441+" TEXT,"+
                    DatabaseNetwork.wih451+" TEXT,"+

                    DatabaseNetwork.wih12+" TEXT,"+
                    DatabaseNetwork.wih22+" TEXT,"+
                    DatabaseNetwork.wih32+" TEXT,"+
                    DatabaseNetwork.wih42+" TEXT,"+
                    DatabaseNetwork.wih52+" TEXT,"+
                    DatabaseNetwork.wih62+" TEXT,"+
                    DatabaseNetwork.wih72+" TEXT,"+
                    DatabaseNetwork.wih82+" TEXT,"+
                    DatabaseNetwork.wih92+" TEXT,"+
                    DatabaseNetwork.wih102+" TEXT,"+
                    DatabaseNetwork.wih112+" TEXT,"+
                    DatabaseNetwork.wih122+" TEXT,"+
                    DatabaseNetwork.wih132+" TEXT,"+
                    DatabaseNetwork.wih142+" TEXT,"+
                    DatabaseNetwork.wih152+" TEXT,"+
                    DatabaseNetwork.wih162+" TEXT,"+
                    DatabaseNetwork.wih172+" TEXT,"+
                    DatabaseNetwork.wih182+" TEXT,"+
                    DatabaseNetwork.wih192+" TEXT,"+
                    DatabaseNetwork.wih202+" TEXT,"+
                    DatabaseNetwork.wih212+" TEXT,"+
                    DatabaseNetwork.wih222+" TEXT,"+
                    DatabaseNetwork.wih232+" TEXT,"+
                    DatabaseNetwork.wih242+" TEXT,"+
                    DatabaseNetwork.wih252+" TEXT,"+
                    DatabaseNetwork.wih262+" TEXT,"+
                    DatabaseNetwork.wih272+" TEXT,"+
                    DatabaseNetwork.wih282+" TEXT,"+
                    DatabaseNetwork.wih292+" TEXT,"+
                    DatabaseNetwork.wih302+" TEXT,"+
                    DatabaseNetwork.wih312+" TEXT,"+
                    DatabaseNetwork.wih322+" TEXT,"+
                    DatabaseNetwork.wih332+" TEXT,"+
                    DatabaseNetwork.wih342+" TEXT,"+
                    DatabaseNetwork.wih352+" TEXT,"+
                    DatabaseNetwork.wih362+" TEXT,"+
                    DatabaseNetwork.wih372+" TEXT,"+
                    DatabaseNetwork.wih382+" TEXT,"+
                    DatabaseNetwork.wih392+" TEXT,"+
                    DatabaseNetwork.wih402+" TEXT,"+
                    DatabaseNetwork.wih412+" TEXT,"+
                    DatabaseNetwork.wih422+" TEXT,"+
                    DatabaseNetwork.wih432+" TEXT,"+
                    DatabaseNetwork.wih442+" TEXT,"+
                    DatabaseNetwork.wih452+" TEXT,"+

                    DatabaseNetwork.wih13+" TEXT,"+
                    DatabaseNetwork.wih23+" TEXT,"+
                    DatabaseNetwork.wih33+" TEXT,"+
                    DatabaseNetwork.wih43+" TEXT,"+
                    DatabaseNetwork.wih53+" TEXT,"+
                    DatabaseNetwork.wih63+" TEXT,"+
                    DatabaseNetwork.wih73+" TEXT,"+
                    DatabaseNetwork.wih83+" TEXT,"+
                    DatabaseNetwork.wih93+" TEXT,"+
                    DatabaseNetwork.wih103+" TEXT,"+
                    DatabaseNetwork.wih113+" TEXT,"+
                    DatabaseNetwork.wih123+" TEXT,"+
                    DatabaseNetwork.wih133+" TEXT,"+
                    DatabaseNetwork.wih143+" TEXT,"+
                    DatabaseNetwork.wih153+" TEXT,"+
                    DatabaseNetwork.wih163+" TEXT,"+
                    DatabaseNetwork.wih173+" TEXT,"+
                    DatabaseNetwork.wih183+" TEXT,"+
                    DatabaseNetwork.wih193+" TEXT,"+
                    DatabaseNetwork.wih203+" TEXT,"+
                    DatabaseNetwork.wih213+" TEXT,"+
                    DatabaseNetwork.wih223+" TEXT,"+
                    DatabaseNetwork.wih233+" TEXT,"+
                    DatabaseNetwork.wih243+" TEXT,"+
                    DatabaseNetwork.wih253+" TEXT,"+
                    DatabaseNetwork.wih263+" TEXT,"+
                    DatabaseNetwork.wih273+" TEXT,"+
                    DatabaseNetwork.wih283+" TEXT,"+
                    DatabaseNetwork.wih293+" TEXT,"+
                    DatabaseNetwork.wih303+" TEXT,"+
                    DatabaseNetwork.wih313+" TEXT,"+
                    DatabaseNetwork.wih323+" TEXT,"+
                    DatabaseNetwork.wih333+" TEXT,"+
                    DatabaseNetwork.wih343+" TEXT,"+
                    DatabaseNetwork.wih353+" TEXT,"+
                    DatabaseNetwork.wih363+" TEXT,"+
                    DatabaseNetwork.wih373+" TEXT,"+
                    DatabaseNetwork.wih383+" TEXT,"+
                    DatabaseNetwork.wih393+" TEXT,"+
                    DatabaseNetwork.wih403+" TEXT,"+
                    DatabaseNetwork.wih413+" TEXT,"+
                    DatabaseNetwork.wih423+" TEXT,"+
                    DatabaseNetwork.wih433+" TEXT,"+
                    DatabaseNetwork.wih443+" TEXT,"+
                    DatabaseNetwork.wih453+" TEXT,"+

                    DatabaseNetwork.wih14+" TEXT,"+
                    DatabaseNetwork.wih24+" TEXT,"+
                    DatabaseNetwork.wih34+" TEXT,"+
                    DatabaseNetwork.wih44+" TEXT,"+
                    DatabaseNetwork.wih54+" TEXT,"+
                    DatabaseNetwork.wih64+" TEXT,"+
                    DatabaseNetwork.wih74+" TEXT,"+
                    DatabaseNetwork.wih84+" TEXT,"+
                    DatabaseNetwork.wih94+" TEXT,"+
                    DatabaseNetwork.wih104+" TEXT,"+
                    DatabaseNetwork.wih114+" TEXT,"+
                    DatabaseNetwork.wih124+" TEXT,"+
                    DatabaseNetwork.wih134+" TEXT,"+
                    DatabaseNetwork.wih144+" TEXT,"+
                    DatabaseNetwork.wih154+" TEXT,"+
                    DatabaseNetwork.wih164+" TEXT,"+
                    DatabaseNetwork.wih174+" TEXT,"+
                    DatabaseNetwork.wih184+" TEXT,"+
                    DatabaseNetwork.wih194+" TEXT,"+
                    DatabaseNetwork.wih204+" TEXT,"+
                    DatabaseNetwork.wih214+" TEXT,"+
                    DatabaseNetwork.wih224+" TEXT,"+
                    DatabaseNetwork.wih234+" TEXT,"+
                    DatabaseNetwork.wih244+" TEXT,"+
                    DatabaseNetwork.wih254+" TEXT,"+
                    DatabaseNetwork.wih264+" TEXT,"+
                    DatabaseNetwork.wih274+" TEXT,"+
                    DatabaseNetwork.wih284+" TEXT,"+
                    DatabaseNetwork.wih294+" TEXT,"+
                    DatabaseNetwork.wih304+" TEXT,"+
                    DatabaseNetwork.wih314+" TEXT,"+
                    DatabaseNetwork.wih324+" TEXT,"+
                    DatabaseNetwork.wih334+" TEXT,"+
                    DatabaseNetwork.wih344+" TEXT,"+
                    DatabaseNetwork.wih354+" TEXT,"+
                    DatabaseNetwork.wih364+" TEXT,"+
                    DatabaseNetwork.wih374+" TEXT,"+
                    DatabaseNetwork.wih384+" TEXT,"+
                    DatabaseNetwork.wih394+" TEXT,"+
                    DatabaseNetwork.wih404+" TEXT,"+
                    DatabaseNetwork.wih414+" TEXT,"+
                    DatabaseNetwork.wih424+" TEXT,"+
                    DatabaseNetwork.wih434+" TEXT,"+
                    DatabaseNetwork.wih444+" TEXT,"+
                    DatabaseNetwork.wih454+" TEXT,"+

                    DatabaseNetwork.wih15+" TEXT,"+
                    DatabaseNetwork.wih25+" TEXT,"+
                    DatabaseNetwork.wih35+" TEXT,"+
                    DatabaseNetwork.wih45+" TEXT,"+
                    DatabaseNetwork.wih55+" TEXT,"+
                    DatabaseNetwork.wih65+" TEXT,"+
                    DatabaseNetwork.wih75+" TEXT,"+
                    DatabaseNetwork.wih85+" TEXT,"+
                    DatabaseNetwork.wih95+" TEXT,"+
                    DatabaseNetwork.wih105+" TEXT,"+
                    DatabaseNetwork.wih115+" TEXT,"+
                    DatabaseNetwork.wih125+" TEXT,"+
                    DatabaseNetwork.wih135+" TEXT,"+
                    DatabaseNetwork.wih145+" TEXT,"+
                    DatabaseNetwork.wih155+" TEXT,"+
                    DatabaseNetwork.wih165+" TEXT,"+
                    DatabaseNetwork.wih175+" TEXT,"+
                    DatabaseNetwork.wih185+" TEXT,"+
                    DatabaseNetwork.wih195+" TEXT,"+
                    DatabaseNetwork.wih205+" TEXT,"+
                    DatabaseNetwork.wih215+" TEXT,"+
                    DatabaseNetwork.wih225+" TEXT,"+
                    DatabaseNetwork.wih235+" TEXT,"+
                    DatabaseNetwork.wih245+" TEXT,"+
                    DatabaseNetwork.wih255+" TEXT,"+
                    DatabaseNetwork.wih265+" TEXT,"+
                    DatabaseNetwork.wih275+" TEXT,"+
                    DatabaseNetwork.wih285+" TEXT,"+
                    DatabaseNetwork.wih295+" TEXT,"+
                    DatabaseNetwork.wih305+" TEXT,"+
                    DatabaseNetwork.wih315+" TEXT,"+
                    DatabaseNetwork.wih325+" TEXT,"+
                    DatabaseNetwork.wih335+" TEXT,"+
                    DatabaseNetwork.wih345+" TEXT,"+
                    DatabaseNetwork.wih355+" TEXT,"+
                    DatabaseNetwork.wih365+" TEXT,"+
                    DatabaseNetwork.wih375+" TEXT,"+
                    DatabaseNetwork.wih385+" TEXT,"+
                    DatabaseNetwork.wih395+" TEXT,"+
                    DatabaseNetwork.wih405+" TEXT,"+
                    DatabaseNetwork.wih415+" TEXT,"+
                    DatabaseNetwork.wih425+" TEXT,"+
                    DatabaseNetwork.wih435+" TEXT,"+
                    DatabaseNetwork.wih445+" TEXT,"+
                    DatabaseNetwork.wih455+" TEXT,"+

                    DatabaseNetwork.wih16+" TEXT,"+
                    DatabaseNetwork.wih26+" TEXT,"+
                    DatabaseNetwork.wih36+" TEXT,"+
                    DatabaseNetwork.wih46+" TEXT,"+
                    DatabaseNetwork.wih56+" TEXT,"+
                    DatabaseNetwork.wih66+" TEXT,"+
                    DatabaseNetwork.wih76+" TEXT,"+
                    DatabaseNetwork.wih86+" TEXT,"+
                    DatabaseNetwork.wih96+" TEXT,"+
                    DatabaseNetwork.wih106+" TEXT,"+
                    DatabaseNetwork.wih116+" TEXT,"+
                    DatabaseNetwork.wih126+" TEXT,"+
                    DatabaseNetwork.wih136+" TEXT,"+
                    DatabaseNetwork.wih146+" TEXT,"+
                    DatabaseNetwork.wih156+" TEXT,"+
                    DatabaseNetwork.wih166+" TEXT,"+
                    DatabaseNetwork.wih176+" TEXT,"+
                    DatabaseNetwork.wih186+" TEXT,"+
                    DatabaseNetwork.wih196+" TEXT,"+
                    DatabaseNetwork.wih206+" TEXT,"+
                    DatabaseNetwork.wih216+" TEXT,"+
                    DatabaseNetwork.wih226+" TEXT,"+
                    DatabaseNetwork.wih236+" TEXT,"+
                    DatabaseNetwork.wih246+" TEXT,"+
                    DatabaseNetwork.wih256+" TEXT,"+
                    DatabaseNetwork.wih266+" TEXT,"+
                    DatabaseNetwork.wih276+" TEXT,"+
                    DatabaseNetwork.wih286+" TEXT,"+
                    DatabaseNetwork.wih296+" TEXT,"+
                    DatabaseNetwork.wih306+" TEXT,"+
                    DatabaseNetwork.wih316+" TEXT,"+
                    DatabaseNetwork.wih326+" TEXT,"+
                    DatabaseNetwork.wih336+" TEXT,"+
                    DatabaseNetwork.wih346+" TEXT,"+
                    DatabaseNetwork.wih356+" TEXT,"+
                    DatabaseNetwork.wih366+" TEXT,"+
                    DatabaseNetwork.wih376+" TEXT,"+
                    DatabaseNetwork.wih386+" TEXT,"+
                    DatabaseNetwork.wih396+" TEXT,"+
                    DatabaseNetwork.wih406+" TEXT,"+
                    DatabaseNetwork.wih416+" TEXT,"+
                    DatabaseNetwork.wih426+" TEXT,"+
                    DatabaseNetwork.wih436+" TEXT,"+
                    DatabaseNetwork.wih446+" TEXT,"+
                    DatabaseNetwork.wih456+" TEXT,"+

                    DatabaseNetwork.wih17+" TEXT,"+
                    DatabaseNetwork.wih27+" TEXT,"+
                    DatabaseNetwork.wih37+" TEXT,"+
                    DatabaseNetwork.wih47+" TEXT,"+
                    DatabaseNetwork.wih57+" TEXT,"+
                    DatabaseNetwork.wih67+" TEXT,"+
                    DatabaseNetwork.wih77+" TEXT,"+
                    DatabaseNetwork.wih87+" TEXT,"+
                    DatabaseNetwork.wih97+" TEXT,"+
                    DatabaseNetwork.wih107+" TEXT,"+
                    DatabaseNetwork.wih117+" TEXT,"+
                    DatabaseNetwork.wih127+" TEXT,"+
                    DatabaseNetwork.wih137+" TEXT,"+
                    DatabaseNetwork.wih147+" TEXT,"+
                    DatabaseNetwork.wih157+" TEXT,"+
                    DatabaseNetwork.wih167+" TEXT,"+
                    DatabaseNetwork.wih177+" TEXT,"+
                    DatabaseNetwork.wih187+" TEXT,"+
                    DatabaseNetwork.wih197+" TEXT,"+
                    DatabaseNetwork.wih207+" TEXT,"+
                    DatabaseNetwork.wih217+" TEXT,"+
                    DatabaseNetwork.wih227+" TEXT,"+
                    DatabaseNetwork.wih237+" TEXT,"+
                    DatabaseNetwork.wih247+" TEXT,"+
                    DatabaseNetwork.wih257+" TEXT,"+
                    DatabaseNetwork.wih267+" TEXT,"+
                    DatabaseNetwork.wih277+" TEXT,"+
                    DatabaseNetwork.wih287+" TEXT,"+
                    DatabaseNetwork.wih297+" TEXT,"+
                    DatabaseNetwork.wih307+" TEXT,"+
                    DatabaseNetwork.wih317+" TEXT,"+
                    DatabaseNetwork.wih327+" TEXT,"+
                    DatabaseNetwork.wih337+" TEXT,"+
                    DatabaseNetwork.wih347+" TEXT,"+
                    DatabaseNetwork.wih357+" TEXT,"+
                    DatabaseNetwork.wih367+" TEXT,"+
                    DatabaseNetwork.wih377+" TEXT,"+
                    DatabaseNetwork.wih387+" TEXT,"+
                    DatabaseNetwork.wih397+" TEXT,"+
                    DatabaseNetwork.wih407+" TEXT,"+
                    DatabaseNetwork.wih417+" TEXT,"+
                    DatabaseNetwork.wih427+" TEXT,"+
                    DatabaseNetwork.wih437+" TEXT,"+
                    DatabaseNetwork.wih447+" TEXT,"+
                    DatabaseNetwork.wih457+" TEXT,"+

                    DatabaseNetwork.wih18+" TEXT,"+
                    DatabaseNetwork.wih28+" TEXT,"+
                    DatabaseNetwork.wih38+" TEXT,"+
                    DatabaseNetwork.wih48+" TEXT,"+
                    DatabaseNetwork.wih58+" TEXT,"+
                    DatabaseNetwork.wih68+" TEXT,"+
                    DatabaseNetwork.wih78+" TEXT,"+
                    DatabaseNetwork.wih88+" TEXT,"+
                    DatabaseNetwork.wih98+" TEXT,"+
                    DatabaseNetwork.wih108+" TEXT,"+
                    DatabaseNetwork.wih118+" TEXT,"+
                    DatabaseNetwork.wih128+" TEXT,"+
                    DatabaseNetwork.wih138+" TEXT,"+
                    DatabaseNetwork.wih148+" TEXT,"+
                    DatabaseNetwork.wih158+" TEXT,"+
                    DatabaseNetwork.wih168+" TEXT,"+
                    DatabaseNetwork.wih178+" TEXT,"+
                    DatabaseNetwork.wih188+" TEXT,"+
                    DatabaseNetwork.wih198+" TEXT,"+
                    DatabaseNetwork.wih208+" TEXT,"+
                    DatabaseNetwork.wih218+" TEXT,"+
                    DatabaseNetwork.wih228+" TEXT,"+
                    DatabaseNetwork.wih238+" TEXT,"+
                    DatabaseNetwork.wih248+" TEXT,"+
                    DatabaseNetwork.wih258+" TEXT,"+
                    DatabaseNetwork.wih268+" TEXT,"+
                    DatabaseNetwork.wih278+" TEXT,"+
                    DatabaseNetwork.wih288+" TEXT,"+
                    DatabaseNetwork.wih298+" TEXT,"+
                    DatabaseNetwork.wih308+" TEXT,"+
                    DatabaseNetwork.wih318+" TEXT,"+
                    DatabaseNetwork.wih328+" TEXT,"+
                    DatabaseNetwork.wih338+" TEXT,"+
                    DatabaseNetwork.wih348+" TEXT,"+
                    DatabaseNetwork.wih358+" TEXT,"+
                    DatabaseNetwork.wih368+" TEXT,"+
                    DatabaseNetwork.wih378+" TEXT,"+
                    DatabaseNetwork.wih388+" TEXT,"+
                    DatabaseNetwork.wih398+" TEXT,"+
                    DatabaseNetwork.wih408+" TEXT,"+
                    DatabaseNetwork.wih418+" TEXT,"+
                    DatabaseNetwork.wih428+" TEXT,"+
                    DatabaseNetwork.wih438+" TEXT,"+
                    DatabaseNetwork.wih448+" TEXT,"+
                    DatabaseNetwork.wih458+" TEXT);";


            db.execSQL(n);


            String o="CREATE TABLE "+DatabaseNetwork.hidden_to_output+" ( "+
                DatabaseNetwork.who11 + " TEXT ,"+
                DatabaseNetwork.who21 + " TEXT ,"+
                DatabaseNetwork.who31 + " TEXT ,"+
                DatabaseNetwork.who41 + " TEXT ,"+
                DatabaseNetwork.who51 + " TEXT ,"+
                DatabaseNetwork.who61 + " TEXT ,"+
                DatabaseNetwork.who71 + " TEXT ,"+
                DatabaseNetwork.who81 + " TEXT );";
        db.execSQL(o);
/*
        String bh="CREATE TABLE "+DatabaseNetwork.bias_hidden+" ( "+
                DatabaseNetwork.bh1 + " TEXT ,"+
                DatabaseNetwork.bh2 + " TEXT ,"+
                DatabaseNetwork.bh3 + " TEXT ,"+
                DatabaseNetwork.bh4 + " TEXT );";
        db.execSQL(bh);

        String bo="CREATE TABLE "+DatabaseNetwork.bias_output+" ( "+
                DatabaseNetwork.bo1 + " TEXT ,"+
                DatabaseNetwork.bo2 + " TEXT );";

        db.execSQL(bo);
*/


        String training="CREATE TABLE "+Database.TRAINING_TABLE_NAME+" ( "+
                    Database.TRAINING_STATUS + " TEXT );";
        db.execSQL(training);

        String error="CREATE TABLE "+DatabaseNetwork.error_table+" ( "+
                    DatabaseNetwork.error1 + " TEXT );";
                    //DatabaseNetwork.error2 + " TEXT );";

        db.execSQL(error);
        String target="CREATE TABLE "+DatabaseNetwork.target_output_table+" ( "+
                    DatabaseNetwork.output1 + " TEXT );";
                    //DatabaseNetwork.output2 + " TEXT );";

        db.execSQL(target);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}

