package com.example.keystrokedynamics;

/**
 * Created by Mukesh on 10-03-2016.
 */

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.util.Log;
        import android.widget.Toast;

public class errorBackPropagation {
    //public static double bias_hidden[];
    //public static double bias_output[];
    public static double input_layer[];
    public static double hidden_layer[];
    public static double weight_input_to_hidden[][];
    public static double input_hidden_layer[];
    public static double input_output_layer[];
    public static double output_layer[];
    public static double target_output[];
    public static double weight_hidden_to_output[][];
    public static final double learning_rate=0.6;
    public static double error_output[];
    public static double error_hidden[];
    public static double change_in_weight_hidden_and_output[][];
    public static double change_in_weight_hidden_and_input[][];
    //public static double change_in_bias_hidden_and_output[];
    //public static double change_in_bias_hidden_and_input[];
    Context con;
    errorBackPropagation(Context con){
        this.con=con;
    }

    public void initialiseWeightsAndBias(){
        weight_input_to_hidden=new double[input_layer.length][hidden_layer.length];
        weight_hidden_to_output=new double[hidden_layer.length][output_layer.length];
        //bias_hidden=new double[8];//bias for hidden layer
        //bias_output=new double[2];//bias for each output layer
    }
    public void initialiseInputLayer(){
        input_layer=new double[45];
    }
    public void initialiseHiddenLayer(){
        hidden_layer=new double[8];
    }
    public void initialiseOutputLayer(){
        output_layer=new double[1];
    }
    public void initialiseTargetOutput(){
        target_output=new double[1];
    }
    public void setLegitimatetargetOutput(){
        target_output[0]=1;
        //target_output[1]=1;
    }
    public void setIllegitimatetargetOutput(){
        //target_output[0]=1;
        target_output[0]=0;
    }
    public void initialiseError(){
        error_output=new double[output_layer.length];
        error_hidden=new double[hidden_layer.length];
    }
    public void initialiseChangeInWeightsAndBias(){
        change_in_weight_hidden_and_output=new double[hidden_layer.length][output_layer.length];
        change_in_weight_hidden_and_input=new double[input_layer.length][hidden_layer.length];
        //change_in_bias_hidden_and_output=new double[bias_output.length];
        //change_in_bias_hidden_and_input=new double[bias_hidden.length];
    }
    public void initialiseInputForHiddenLayer(){
        input_hidden_layer=new double[hidden_layer.length];
    }
    public void initialiseInputForOutputLayer(){
        input_output_layer=new double[output_layer.length];
    }
    public double activationFunction(double lambda,int i) {
        double a=Math.exp(lambda);
        double b=1.0/(double)a;
        double c=1.0+b;
        double x=1.0/(double)c;
        return x;
    }
    public double derivativeOfActivationFunction(double output_layer) {
        return output_layer*(1-output_layer);
    }
    public void functionHiddenLayer(){
        for(int i=0;i<input_hidden_layer.length;i++){
            //input_hidden_layer[i]=bias_hidden[i];
            for(int j=0;j<input_layer.length;j++){
                input_hidden_layer[i]+=input_layer[j]*weight_input_to_hidden[j][i];
            }
            hidden_layer[i]=activationFunction(input_hidden_layer[i],i);
            if(i == 0){
                /*Toast.makeText(con, "Input Layer : "+input_hidden_layer[i] + "", Toast.LENGTH_SHORT).show();
                Toast.makeText(con, "Hidden : "+hidden_layer[i] + "", Toast.LENGTH_SHORT).show();
                */
                Log.e("Input Layer  : ", input_hidden_layer[i] +"");
                Log.e("Input Layer  : ", hidden_layer[i] + "");
            }


        }
    }
    public void functionOutputLayer(){
        for(int i=0;i<input_output_layer.length;i++){
            //input_output_layer[i]=bias_output[i];
            for(int j=0;j<hidden_layer.length;j++){
                input_output_layer[i]+=hidden_layer[j]*weight_hidden_to_output[j][i];
                Log.e("Hid",  hidden_layer[j]+"");
                Log.e("We Op",  weight_hidden_to_output[j][i]+"");
            }
            Log.e("Ip to Op Layer", input_output_layer[i] +"");
            output_layer[i]=activationFunction(input_output_layer[i],i);
            /*if(i==0) {
                Toast.makeText(con, activationFunction(5.5, i) + "", Toast.LENGTH_SHORT).show();
                Toast.makeText(con, activationFunction(2.5, i) + "", Toast.LENGTH_SHORT).show();

            }*/
            //Toast.makeText(con,"Output : "+output_layer[i] ,Toast.LENGTH_SHORT).show();
        }
    }
    public void functionErrorOutputLayer(){
        for(int i=0;i<output_layer.length;i++){
            error_output[i]=(target_output[i]-output_layer[i])*derivativeOfActivationFunction(output_layer[i]);
        }
    }
    public void functionChangeInWeightsHiddenToOutput() {
        for(int i=0;i<output_layer.length;i++){
            for(int j=0;j<hidden_layer.length;j++){
                change_in_weight_hidden_and_output[j][i]=learning_rate*error_output[i]*hidden_layer[j];
            }
            //change_in_bias_hidden_and_output[i]=learning_rate*error_output[i];
        }
    }
    public void functionErrorHiddenLayer(){
        for(int j=0;j<hidden_layer.length;j++){
            double x=0;
            for(int k=0;k<output_layer.length;k++){
                x+=error_output[k]*weight_hidden_to_output[j][k];
            }
            error_hidden[j]=x*derivativeOfActivationFunction(hidden_layer[j]);
        }
    }
    public void functionChangeInWeightsInputToHidden() {
        for(int i=0;i<hidden_layer.length;i++){
            for(int j=0;j<input_layer.length;j++){
                change_in_weight_hidden_and_input[j][i]=learning_rate*error_hidden[i]*input_layer[j];
            }
            //change_in_bias_hidden_and_input[i]=learning_rate*error_hidden[i];
        }
    }
    //Weight Updation
    public void updateWeights(){
        for(int i=0;i<output_layer.length;i++){
            for(int j=0;j<hidden_layer.length;j++){
                weight_hidden_to_output[j][i]=weight_hidden_to_output[j][i]+change_in_weight_hidden_and_output[j][i];
            }
            //bias_output[i]+=change_in_bias_hidden_and_output[i];
        }
        for(int i=0;i<hidden_layer.length;i++){
            for(int j=0;j<input_layer.length;j++){
                weight_input_to_hidden[j][i]=weight_input_to_hidden[j][i]+change_in_weight_hidden_and_input[j][i];
            }
            //bias_hidden[i]+=change_in_bias_hidden_and_input[i];
        }
    }
    public void randomizeWeights(){
        int i=0;
        for(;i<input_layer.length;i++) {
            for(int j=0;j<hidden_layer.length;) {
                double min = 0 ;
                double max = (double)(3)/(Math.sqrt(45));
                double x=min + Math.random()*(max-min);
                if(x>=min && x<=max) {

                        weight_input_to_hidden[i][j] = x;
                        j++;

                }
            }
        }
        i=0;
        /*for(;i<bias_hidden.length;i++) {
            bias_hidden[i]=Math.random();
        }*/
        i=0;
        for(;i<hidden_layer.length;i++) {
            for(int j=0;j<output_layer.length;j++) {
                /*if(i==0){
                    bias_output[j]=Math.random();
                }*/
                double min = 0 ;
                double max = 0.7 ;
                double x = Math.random();
                if(x>=min && x<=max) {
                    weight_hidden_to_output[i][j] = x ;
                }
            }
        }
    }
    //Testing Methods
    public void getInputLayer(double input_layer[]){
        this.input_layer=input_layer;
    }
    public void getFinalWeightsFromInputToHidden(Context c){
        String col[]=new String[]{
                DatabaseNetwork.wih11,
                DatabaseNetwork.wih21,
                DatabaseNetwork.wih31,
                DatabaseNetwork.wih41,
                DatabaseNetwork.wih51,
                DatabaseNetwork.wih61,
                DatabaseNetwork.wih71,
                DatabaseNetwork.wih81,
                DatabaseNetwork.wih91,
                DatabaseNetwork.wih101,
                DatabaseNetwork.wih111,
                DatabaseNetwork.wih121,
                DatabaseNetwork.wih131,
                DatabaseNetwork.wih141,
                DatabaseNetwork.wih151,
                DatabaseNetwork.wih161,
                DatabaseNetwork.wih171,
                DatabaseNetwork.wih181,
                DatabaseNetwork.wih191,
                DatabaseNetwork.wih201,
                DatabaseNetwork.wih211,
                DatabaseNetwork.wih221,
                DatabaseNetwork.wih231,
                DatabaseNetwork.wih241,
                DatabaseNetwork.wih251,
                DatabaseNetwork.wih261,
                DatabaseNetwork.wih271,
                DatabaseNetwork.wih281,
                DatabaseNetwork.wih291,
                DatabaseNetwork.wih301,
                DatabaseNetwork.wih311,
                DatabaseNetwork.wih321,
                DatabaseNetwork.wih331,
                DatabaseNetwork.wih341,
                DatabaseNetwork.wih351,
                DatabaseNetwork.wih361,
                DatabaseNetwork.wih371,
                DatabaseNetwork.wih381,
                DatabaseNetwork.wih391,
                DatabaseNetwork.wih401,
                DatabaseNetwork.wih411,
                DatabaseNetwork.wih421,
                DatabaseNetwork.wih431,
                DatabaseNetwork.wih441,
                DatabaseNetwork.wih451,
                DatabaseNetwork.wih12,
                DatabaseNetwork.wih22,
                DatabaseNetwork.wih32,
                DatabaseNetwork.wih42,
                DatabaseNetwork.wih52,
                DatabaseNetwork.wih62,
                DatabaseNetwork.wih72,
                DatabaseNetwork.wih82,
                DatabaseNetwork.wih92,
                DatabaseNetwork.wih102,
                DatabaseNetwork.wih112,
                DatabaseNetwork.wih122,
                DatabaseNetwork.wih132,
                DatabaseNetwork.wih142,
                DatabaseNetwork.wih152,
                DatabaseNetwork.wih162,
                DatabaseNetwork.wih172,
                DatabaseNetwork.wih182,
                DatabaseNetwork.wih192,
                DatabaseNetwork.wih202,
                DatabaseNetwork.wih212,
                DatabaseNetwork.wih222,
                DatabaseNetwork.wih232,
                DatabaseNetwork.wih242,
                DatabaseNetwork.wih252,
                DatabaseNetwork.wih262,
                DatabaseNetwork.wih272,
                DatabaseNetwork.wih282,
                DatabaseNetwork.wih292,
                DatabaseNetwork.wih302,
                DatabaseNetwork.wih312,
                DatabaseNetwork.wih322,
                DatabaseNetwork.wih332,
                DatabaseNetwork.wih342,
                DatabaseNetwork.wih352,
                DatabaseNetwork.wih362,
                DatabaseNetwork.wih372,
                DatabaseNetwork.wih382,
                DatabaseNetwork.wih392,
                DatabaseNetwork.wih402,
                DatabaseNetwork.wih412,
                DatabaseNetwork.wih422,
                DatabaseNetwork.wih432,
                DatabaseNetwork.wih442,
                DatabaseNetwork.wih452,
                DatabaseNetwork.wih13,
                DatabaseNetwork.wih23,
                DatabaseNetwork.wih33,
                DatabaseNetwork.wih43,
                DatabaseNetwork.wih53,
                DatabaseNetwork.wih63,
                DatabaseNetwork.wih73,
                DatabaseNetwork.wih83,
                DatabaseNetwork.wih93,
                DatabaseNetwork.wih103,
                DatabaseNetwork.wih113,
                DatabaseNetwork.wih123,
                DatabaseNetwork.wih133,
                DatabaseNetwork.wih143,
                DatabaseNetwork.wih153,
                DatabaseNetwork.wih163,
                DatabaseNetwork.wih173,
                DatabaseNetwork.wih183,
                DatabaseNetwork.wih193,
                DatabaseNetwork.wih203,
                DatabaseNetwork.wih213,
                DatabaseNetwork.wih223,
                DatabaseNetwork.wih233,
                DatabaseNetwork.wih243,
                DatabaseNetwork.wih253,
                DatabaseNetwork.wih263,
                DatabaseNetwork.wih273,
                DatabaseNetwork.wih283,
                DatabaseNetwork.wih293,
                DatabaseNetwork.wih303,
                DatabaseNetwork.wih313,
                DatabaseNetwork.wih323,
                DatabaseNetwork.wih333,
                DatabaseNetwork.wih343,
                DatabaseNetwork.wih353,
                DatabaseNetwork.wih363,
                DatabaseNetwork.wih373,
                DatabaseNetwork.wih383,
                DatabaseNetwork.wih393,
                DatabaseNetwork.wih403,
                DatabaseNetwork.wih413,
                DatabaseNetwork.wih423,
                DatabaseNetwork.wih433,
                DatabaseNetwork.wih443,
                DatabaseNetwork.wih453,
                DatabaseNetwork.wih14,
                DatabaseNetwork.wih24,
                DatabaseNetwork.wih34,
                DatabaseNetwork.wih44,
                DatabaseNetwork.wih54,
                DatabaseNetwork.wih64,
                DatabaseNetwork.wih74,
                DatabaseNetwork.wih84,
                DatabaseNetwork.wih94,
                DatabaseNetwork.wih104,
                DatabaseNetwork.wih114,
                DatabaseNetwork.wih124,
                DatabaseNetwork.wih134,
                DatabaseNetwork.wih144,
                DatabaseNetwork.wih154,
                DatabaseNetwork.wih164,
                DatabaseNetwork.wih174,
                DatabaseNetwork.wih184,
                DatabaseNetwork.wih194,
                DatabaseNetwork.wih204,
                DatabaseNetwork.wih214,
                DatabaseNetwork.wih224,
                DatabaseNetwork.wih234,
                DatabaseNetwork.wih244,
                DatabaseNetwork.wih254,
                DatabaseNetwork.wih264,
                DatabaseNetwork.wih274,
                DatabaseNetwork.wih284,
                DatabaseNetwork.wih294,
                DatabaseNetwork.wih304,
                DatabaseNetwork.wih314,
                DatabaseNetwork.wih324,
                DatabaseNetwork.wih334,
                DatabaseNetwork.wih344,
                DatabaseNetwork.wih354,
                DatabaseNetwork.wih364,
                DatabaseNetwork.wih374,
                DatabaseNetwork.wih384,
                DatabaseNetwork.wih394,
                DatabaseNetwork.wih404,
                DatabaseNetwork.wih414,
                DatabaseNetwork.wih424,
                DatabaseNetwork.wih434,
                DatabaseNetwork.wih444,
                DatabaseNetwork.wih454,
                DatabaseNetwork.wih15,DatabaseNetwork.wih25,
                DatabaseNetwork.wih35,DatabaseNetwork.wih45,
                DatabaseNetwork.wih55,DatabaseNetwork.wih65,
                DatabaseNetwork.wih75,DatabaseNetwork.wih85,
                DatabaseNetwork.wih95,DatabaseNetwork.wih105,
                DatabaseNetwork.wih115,
                DatabaseNetwork.wih125,
                DatabaseNetwork.wih135,
                DatabaseNetwork.wih145,
                DatabaseNetwork.wih155,
                DatabaseNetwork.wih165,
                DatabaseNetwork.wih175,
                DatabaseNetwork.wih185,
                DatabaseNetwork.wih195,
                DatabaseNetwork.wih205,
                DatabaseNetwork.wih215,
                DatabaseNetwork.wih225,
                DatabaseNetwork.wih235,
                DatabaseNetwork.wih245,
                DatabaseNetwork.wih255,
                DatabaseNetwork.wih265,
                DatabaseNetwork.wih275,
                DatabaseNetwork.wih285,
                DatabaseNetwork.wih295,
                DatabaseNetwork.wih305,
                DatabaseNetwork.wih315,
                DatabaseNetwork.wih325,
                DatabaseNetwork.wih335,
                DatabaseNetwork.wih345,
                DatabaseNetwork.wih355,
                DatabaseNetwork.wih365,
                DatabaseNetwork.wih375,
                DatabaseNetwork.wih385,
                DatabaseNetwork.wih395,
                DatabaseNetwork.wih405,
                DatabaseNetwork.wih415,
                DatabaseNetwork.wih425,
                DatabaseNetwork.wih435,
                DatabaseNetwork.wih445,
                DatabaseNetwork.wih455,
                DatabaseNetwork.wih16,DatabaseNetwork.wih26,
                DatabaseNetwork.wih36,DatabaseNetwork.wih46,
                DatabaseNetwork.wih56,DatabaseNetwork.wih66,
                DatabaseNetwork.wih76,DatabaseNetwork.wih86,
                DatabaseNetwork.wih96,DatabaseNetwork.wih106,
                DatabaseNetwork.wih116,
                DatabaseNetwork.wih126,
                DatabaseNetwork.wih136,
                DatabaseNetwork.wih146,
                DatabaseNetwork.wih156,
                DatabaseNetwork.wih166,
                DatabaseNetwork.wih176,
                DatabaseNetwork.wih186,
                DatabaseNetwork.wih196,
                DatabaseNetwork.wih206,
                DatabaseNetwork.wih216,
                DatabaseNetwork.wih226,
                DatabaseNetwork.wih236,
                DatabaseNetwork.wih246,
                DatabaseNetwork.wih256,
                DatabaseNetwork.wih266,
                DatabaseNetwork.wih276,
                DatabaseNetwork.wih286,
                DatabaseNetwork.wih296,
                DatabaseNetwork.wih306,
                DatabaseNetwork.wih316,
                DatabaseNetwork.wih326,
                DatabaseNetwork.wih336,
                DatabaseNetwork.wih346,
                DatabaseNetwork.wih356,
                DatabaseNetwork.wih366,
                DatabaseNetwork.wih376,
                DatabaseNetwork.wih386,
                DatabaseNetwork.wih396,
                DatabaseNetwork.wih406,
                DatabaseNetwork.wih416,
                DatabaseNetwork.wih426,
                DatabaseNetwork.wih436,
                DatabaseNetwork.wih446,
                DatabaseNetwork.wih456,
                DatabaseNetwork.wih17,DatabaseNetwork.wih27,
                DatabaseNetwork.wih37,DatabaseNetwork.wih47,
                DatabaseNetwork.wih57,DatabaseNetwork.wih67,
                DatabaseNetwork.wih77,DatabaseNetwork.wih87,
                DatabaseNetwork.wih97,DatabaseNetwork.wih107,
                DatabaseNetwork.wih117,
                DatabaseNetwork.wih127,
                DatabaseNetwork.wih137,
                DatabaseNetwork.wih147,
                DatabaseNetwork.wih157,
                DatabaseNetwork.wih167,
                DatabaseNetwork.wih177,
                DatabaseNetwork.wih187,
                DatabaseNetwork.wih197,
                DatabaseNetwork.wih207,
                DatabaseNetwork.wih217,
                DatabaseNetwork.wih227,
                DatabaseNetwork.wih237,
                DatabaseNetwork.wih247,
                DatabaseNetwork.wih257,
                DatabaseNetwork.wih267,
                DatabaseNetwork.wih277,
                DatabaseNetwork.wih287,
                DatabaseNetwork.wih297,
                DatabaseNetwork.wih307,
                DatabaseNetwork.wih317,
                DatabaseNetwork.wih327,
                DatabaseNetwork.wih337,
                DatabaseNetwork.wih347,
                DatabaseNetwork.wih357,
                DatabaseNetwork.wih367,
                DatabaseNetwork.wih377,
                DatabaseNetwork.wih387,
                DatabaseNetwork.wih397,
                DatabaseNetwork.wih407,
                DatabaseNetwork.wih417,
                DatabaseNetwork.wih427,
                DatabaseNetwork.wih437,
                DatabaseNetwork.wih447,
                DatabaseNetwork.wih457,
                DatabaseNetwork.wih18,DatabaseNetwork.wih28,
                DatabaseNetwork.wih38,DatabaseNetwork.wih48,
                DatabaseNetwork.wih58,DatabaseNetwork.wih68,
                DatabaseNetwork.wih78,DatabaseNetwork.wih88,
                DatabaseNetwork.wih98,DatabaseNetwork.wih108,
                DatabaseNetwork.wih118,
                DatabaseNetwork.wih128,
                DatabaseNetwork.wih138,
                DatabaseNetwork.wih148,
                DatabaseNetwork.wih158,
                DatabaseNetwork.wih168,
                DatabaseNetwork.wih178,
                DatabaseNetwork.wih188,
                DatabaseNetwork.wih198,
                DatabaseNetwork.wih208,
                DatabaseNetwork.wih218,
                DatabaseNetwork.wih228,
                DatabaseNetwork.wih238,
                DatabaseNetwork.wih248,
                DatabaseNetwork.wih258,
                DatabaseNetwork.wih268,
                DatabaseNetwork.wih278,
                DatabaseNetwork.wih288,
                DatabaseNetwork.wih298,
                DatabaseNetwork.wih308,
                DatabaseNetwork.wih318,
                DatabaseNetwork.wih328,
                DatabaseNetwork.wih338,
                DatabaseNetwork.wih348,
                DatabaseNetwork.wih358,
                DatabaseNetwork.wih368,
                DatabaseNetwork.wih378,
                DatabaseNetwork.wih388,
                DatabaseNetwork.wih398,
                DatabaseNetwork.wih408,
                DatabaseNetwork.wih418,
                DatabaseNetwork.wih428,
                DatabaseNetwork.wih438,
                DatabaseNetwork.wih448,
                DatabaseNetwork.wih458



        };
        DBHelper mydbhelper=new DBHelper(c, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        Cursor cur=mydatabase.query(DatabaseNetwork.NETWORK_TABLE_NAME, col, null, null, null, null, null);
        int k=0;
        cur.moveToLast();
        for(int i=0;i<hidden_layer.length;i++){
            for(int j=0;j<input_layer.length;j++) {
                weight_input_to_hidden[j][i] = Double.parseDouble(cur.getString(k++));
            }
        }
        mydbhelper.close();
    }
    public void getFinalWeightsFromHiddenToOutput(){
        String col[]=new String[]{
                DatabaseNetwork.who11,
                DatabaseNetwork.who21,
                DatabaseNetwork.who31,
                DatabaseNetwork.who41,
                DatabaseNetwork.who51,
                DatabaseNetwork.who61,
                DatabaseNetwork.who71,
                DatabaseNetwork.who81
        };
        DBHelper mydbhelper=new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        Cursor cur=mydatabase.query(DatabaseNetwork.hidden_to_output, col, null, null, null, null, null);
        cur.moveToLast();
        weight_hidden_to_output[0][0]=Double.parseDouble(cur.getString(0));
        weight_hidden_to_output[1][0]=Double.parseDouble(cur.getString(1));
        weight_hidden_to_output[2][0]=Double.parseDouble(cur.getString(2));
        weight_hidden_to_output[3][0]=Double.parseDouble(cur.getString(3));

        weight_hidden_to_output[4][0]=Double.parseDouble(cur.getString(4));
        weight_hidden_to_output[5][0]=Double.parseDouble(cur.getString(5));
        weight_hidden_to_output[6][0]=Double.parseDouble(cur.getString(6));
        weight_hidden_to_output[7][0]=Double.parseDouble(cur.getString(7));

    }
    /*public void getFinalBiasHidden(){
        String col[]=new String[]{
                DatabaseNetwork.bh1,
                DatabaseNetwork.bh2,
                DatabaseNetwork.bh3,
                DatabaseNetwork.bh4
        };
        DBHelper mydbhelper=new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        Cursor cur=mydatabase.query(DatabaseNetwork.bias_hidden, col, null, null, null, null, null);
        cur.moveToLast();

        bias_hidden[0]=Double.parseDouble(cur.getString(0));
        bias_hidden[1]=Double.parseDouble(cur.getString(1));
        bias_hidden[2]=Double.parseDouble(cur.getString(2));
        bias_hidden[3]=Double.parseDouble(cur.getString(3));

    }
    */
    /*public void getFinalBiasOutput(){
        String col[]=new String[]{
                DatabaseNetwork.bo1,
                DatabaseNetwork.bo2
        };
        DBHelper mydbhelper=new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        Cursor cur=mydatabase.query(DatabaseNetwork.bias_output, col, null, null, null, null, null);
        cur.moveToLast();

        //bias_output[0]=Double.parseDouble(cur.getString(0));
        //bias_output[1]=Double.parseDouble(cur.getString(1));
    }*/
    /*public void setFinalBiasHidden(){
        ContentValues cv = new ContentValues();
        int i=0;
        cv.put(DatabaseNetwork.bh1,bias_hidden[i++]);
        cv.put(DatabaseNetwork.bh2,bias_hidden[i++]);
        cv.put(DatabaseNetwork.bh3,bias_hidden[i++]);
        cv.put(DatabaseNetwork.bh4,bias_hidden[i++]);
        DBHelper mydbhelper=new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase=mydbhelper.getWritableDatabase();
        long r=mydatabase.insertOrThrow(DatabaseNetwork.bias_hidden, null, cv);
    }*/
    /*public void setFinalBiasOutput(){
        ContentValues cv = new ContentValues();
        int i=0;
        cv.put(DatabaseNetwork.bo1,bias_output[i++]);
        cv.put(DatabaseNetwork.bo2,bias_output[i++]);
        DBHelper mydbhelper=new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase=mydbhelper.getWritableDatabase();
        long r=mydatabase.insertOrThrow(DatabaseNetwork.bias_output, null, cv);
    }*/
    public void setFinalWeightsFromHiddenToOutput(){
        ContentValues cv = new ContentValues();
        for(int i=0;i<output_layer.length;i++) {
            int j=0;
            switch(i) {
                case 0:
                    j=0;
                    cv.put(DatabaseNetwork.who11, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who21, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who31, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who41, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who51, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who61, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who71, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who81, weight_hidden_to_output[j++][i]);
                    break;
                /*case 1:
                    j=0;
                    cv.put(DatabaseNetwork.who12, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who22, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who32, weight_hidden_to_output[j++][i]);
                    cv.put(DatabaseNetwork.who42, weight_hidden_to_output[j++][i]);
                    break;
*/
            }
        }
        DBHelper mydbhelper=new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase=mydbhelper.getWritableDatabase();
        long r=mydatabase.insertOrThrow(DatabaseNetwork.hidden_to_output, null, cv);

    }
    public void setFinalWeightsFromInputToHidden(Context c){


        ContentValues cv = new ContentValues();
        for(int i=0;i<hidden_layer.length;i++){
            int j=0;
            switch(i) {
                case 0:
                    //Toast.makeText(c,weight_input_to_hidden[0][0]+"",Toast.LENGTH_LONG).show();
                    j=0;
                    cv.put(DatabaseNetwork.wih11,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih21,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih31,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih41,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih51,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih61,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih71,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih81,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih91,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih101,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih111,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih121,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih131,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih141,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih151,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih161,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih171,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih181,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih191,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih201,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih211,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih221,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih231,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih241,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih251,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih261,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih271,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih281,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih291,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih301,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih311,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih321,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih331,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih341,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih351,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih361,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih371,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih381,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih391,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih401,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih411,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih421,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih431,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih441,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih451,weight_input_to_hidden[j++][i] );
                    break;
                case 1:j=0;
                    cv.put(DatabaseNetwork.wih12,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih22,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih32,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih42,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih52,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih62,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih72,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih82,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih92,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih102,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih112,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih122,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih132,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih142,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih152,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih162,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih172,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih182,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih192,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih202,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih212,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih222,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih232,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih242,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih252,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih262,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih272,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih282,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih292,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih302,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih312,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih322,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih332,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih342,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih352,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih362,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih372,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih382,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih392,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih402,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih412,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih422,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih432,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih442,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih452,weight_input_to_hidden[j++][i] );
                    break;
                case 2:
                    j=0;
                    cv.put(DatabaseNetwork.wih13,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih23,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih33,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih43,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih53,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih63,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih73,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih83,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih93,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih103,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih113,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih123,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih133,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih143,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih153,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih163,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih173,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih183,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih193,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih203,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih213,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih223,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih233,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih243,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih253,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih263,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih273,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih283,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih293,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih303,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih313,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih323,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih333,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih343,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih353,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih363,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih373,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih383,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih393,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih403,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih413,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih423,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih433,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih443,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih453,weight_input_to_hidden[j++][i] );
                    break;
                case 3:
                    j=0;
                    cv.put(DatabaseNetwork.wih14,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih24,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih34,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih44,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih54,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih64,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih74,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih84,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih94,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih104,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih114,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih124,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih134,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih144,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih154,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih164,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih174,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih184,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih194,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih204,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih214,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih224,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih234,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih244,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih254,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih264,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih274,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih284,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih294,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih304,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih314,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih324,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih334,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih344,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih354,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih364,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih374,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih384,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih394,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih404,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih414,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih424,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih434,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih444,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih454,weight_input_to_hidden[j++][i] );
                    break;
                case 4: j=0;
                    cv.put(DatabaseNetwork.wih15,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih25,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih35,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih45,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih55,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih65,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih75,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih85,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih95,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih105,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih115,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih125,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih135,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih145,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih155,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih165,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih175,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih185,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih195,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih205,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih215,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih225,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih235,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih245,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih255,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih265,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih275,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih285,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih295,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih305,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih315,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih325,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih335,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih345,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih355,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih365,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih375,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih385,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih395,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih405,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih415,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih425,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih435,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih445,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih455,weight_input_to_hidden[j++][i] );

                    break;
                case 5:
                    j=0;
                    cv.put(DatabaseNetwork.wih16,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih26,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih36,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih46,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih56,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih66,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih76,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih86,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih96,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih106,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih116,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih126,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih136,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih146,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih156,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih166,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih176,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih186,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih196,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih206,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih216,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih226,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih236,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih246,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih256,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih266,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih276,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih286,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih296,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih306,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih316,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih326,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih336,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih346,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih356,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih366,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih376,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih386,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih396,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih406,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih416,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih426,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih436,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih446,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih456,weight_input_to_hidden[j++][i] );

                    break;
                case 6: j=0;
                    cv.put(DatabaseNetwork.wih17,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih27,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih37,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih47,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih57,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih67,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih77,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih87,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih97,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih107,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih117,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih127,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih137,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih147,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih157,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih167,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih177,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih187,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih197,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih207,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih217,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih227,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih237,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih247,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih257,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih267,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih277,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih287,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih297,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih307,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih317,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih327,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih337,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih347,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih357,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih367,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih377,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih387,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih397,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih407,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih417,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih427,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih437,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih447,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih457,weight_input_to_hidden[j++][i] );



                    break;
                case 7: j=0;
                    cv.put(DatabaseNetwork.wih18,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih28,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih38,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih48,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih58,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih68,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih78,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih88,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih98,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih108,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih118,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih128,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih138,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih148,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih158,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih168,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih178,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih188,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih198,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih208,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih218,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih228,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih238,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih248,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih258,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih268,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih278,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih288,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih298,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih308,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih318,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih328,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih338,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih348,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih358,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih368,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih378,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih388,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih398,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih408,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih418,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih428,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih438,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih448,weight_input_to_hidden[j++][i] );
                    cv.put(DatabaseNetwork.wih458,weight_input_to_hidden[j++][i] );


                    break;

            }

        }
        DBHelper mydbhelper=new DBHelper(c, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase=mydbhelper.getWritableDatabase();
        long r=mydatabase.insertOrThrow(DatabaseNetwork.NETWORK_TABLE_NAME, null, cv);
        /*if(r>0){
            Toast.makeText(c,"Last",Toast.LENGTH_LONG).show();
        }*/
    }
    public void getTotalmeanSquareError(){
        String col[]=new String[]{
                DatabaseNetwork.error1,
               // DatabaseNetwork.error2
        };
        DBHelper mydbhelper=new DBHelper(con, Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase = mydbhelper.getWritableDatabase();
        Cursor cur=mydatabase.query(DatabaseNetwork.error_table, col, null, null, null, null, null);
        double error = 0;
        for(cur.moveToFirst();!cur.isAfterLast();cur.moveToNext()){
            error += Double.parseDouble(cur.getString(0))*Double.parseDouble(cur.getString(0));//+Double.parseDouble(cur.getString(1))*Double.parseDouble(cur.getString(1));
            error/=2;
        }

    }
    public void setError(){
        DBHelper mydbhelper=new DBHelper(con , Database.KEYSTROKE_DYNAMICS, null, Database.VERSION);
        SQLiteDatabase mydatabase=mydbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(DatabaseNetwork.error1,error_output[0]);
        //cv.put(DatabaseNetwork.error2,error_output[1]);

        long r=mydatabase.insertOrThrow(DatabaseNetwork.error_table, null, cv);

    }
    public void functionOutputLayerTesting(){
        for(int i=0;i<input_output_layer.length;i++){
            //input_output_layer[i]=bias_output[i];
            for(int j=0;j<hidden_layer.length;j++){
                input_output_layer[i]+=hidden_layer[j]*weight_hidden_to_output[j][i];
               // Log.e("Hid",  hidden_layer[j]+"");
                //Log.e("We Op",  weight_hidden_to_output[j][i]+"");
            }
            //Log.e("Ip to Op Layer", input_output_layer[i] +"");
            output_layer[i]=activationFunction(input_output_layer[i],i);
            //Toast.makeText(con,"Output : "+output_layer[i] ,Toast.LENGTH_SHORT).show();
            if(output_layer[i]>(target_output[0]-(0.05*target_output[0]))){
                Toast.makeText(con,"Legitimate" ,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(con,"Illegitimate" ,Toast.LENGTH_LONG).show();

            }
        }
    }
}
