package me.jaxbot.minesweeper;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Boolean.FALSE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
static EditText fscore;
 static   me.jaxbot.minesweeper.Mybutton buttons[][];
    LinearLayout rows[];
    LinearLayout mainLayout;
   static Boolean gameOver;
static     Boolean win;
   static int n=8;
static int result =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (LinearLayout) findViewById(R.id.activity_main);
        setupboard();
fscore=(EditText)findViewById(R.id.editText);

    }




    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.newGame){

              resetBoard();
        }else if(id == R.id.boardSize_easy){

            n=8;
            setupboard();
            }

    else
            if(id == R.id.boardSize_medium){
            n = 12;
            setupboard();
        }else if(id == R.id.boardSize_hard){
            n = 14;
            setupboard();
        }

        return true;
    }

    private void resetBoard() {


        gameOver = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buttons[i][j].setText("");
buttons[i][j].setStatus("0");
                buttons[i][j].setEnabled(true);
            }
        }
    }

private void setupboard()
{
    gameOver=false;
    win=false;
        buttons = new Mybutton[n][n];
        rows = new LinearLayout[n];
        gameOver = false;
        mainLayout.removeAllViews();

        for (int i = 0; i < n; i++) {
            rows[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
           params.setMargins(1, 1, 1, 1);
            rows[i].setLayoutParams(params);
            rows[i].setOrientation(LinearLayout.HORIZONTAL);
            mainLayout.addView(rows[i]);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buttons[i][j] = new Mybutton(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                params.setMargins(1, 1, 1, 1);
                buttons[i][j].setLayoutParams(params);
                buttons[i][j].setText("");
                buttons[i][j].setBackgroundColor(getResources().getColor(R.color.colorAccent));
                buttons[i][j].setOnLongClickListener(this);
                buttons[i][j].Setaddressx(i);
                buttons[i][j].Setaddressy(j);

                buttons[i][j].setOnClickListener(this);
                rows[i].addView(buttons[i][j]);
            }

        }


                randomgenerate(buttons);
    }

    @Override
    public void onClick(View view) {
        me.jaxbot.minesweeper.Mybutton b = (me.jaxbot.minesweeper.Mybutton) view;
        if (gameOver) {
            return;
        }
        else
        if(win)
        {
            Toast.makeText(this, " WIN !!", Toast.LENGTH_SHORT).show();

            return;
        }
        else
        if (b.getStatus().equals("*")) {
            displaycomplete();
            gameOver = true;
            Toast.makeText(this, " GAME OVER !!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {

            wintest();

          //  Log.d("lalala","This is it");

            b.setText(b.getStatus());


           if( b.getStatus().equals("0")) {
               b.setStatus("-1");
               b.setEnabled(FALSE);
               dispnext(b);
           }else{
               b.setStatus("-1");
               b.setEnabled(FALSE);
           }

    }

    }
    public  static void wintest()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {

                if((!buttons[i][j].getStatus().equals("*")) || (!buttons[i][j].getStatus().equals("-1")))
                {
                    win=false;
                    return;
                }
            }
        }
    }
    static void randomgenerate(Mybutton[][] arrtemp)
    {
        int[]   steprow= {-1,0,1,1,1,0,-1,-1};
        int[] stepcolumn={-1,-1,-1,0,1,1,1,0};

        for(int i=0;i<n;i++)
        {
            Random ran = new Random();
            int ran1=ran.nextInt(n);
            int ran2=ran.nextInt(n);
            if(!arrtemp[ran1][ran2].getStatus().equals("*"))
            {
                arrtemp[ran1][ran2].setStatus("*");


                for(int q=0;q<8;q++) {
                   int nextx=ran1+steprow[q];
                    int nexty=ran2+stepcolumn[q];
                    if(inRange(nextx,nexty,n))
                    {
                        if(arrtemp[nextx][nexty].getStatus().equals("*"))
                            continue;
                        else
                        {
                            String temp=arrtemp[nextx][nexty].getStatus();
                            Integer val=Integer.parseInt(temp);
                            val++;
                            arrtemp[nextx][nexty].setStatus(String.valueOf(val));

                        }
                    }
                }

        }
        }
    }

    private static boolean inRange(int nextx, int nexty, int n) {

        if(nextx>=0&&nextx<n&&nexty>=0&&nexty<n)
            return true;
        else
            return false;
    }


    @Override
    public boolean onLongClick(View view) {
        me.jaxbot.minesweeper.Mybutton b = (me.jaxbot.minesweeper.Mybutton) view;
        Toast.makeText(this, "Long click hPPWNWS", Toast.LENGTH_SHORT).show();
        b.setText("!");
        return false;
    }
    static void displaycomplete()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                buttons[i][j].setText(buttons[i][j].getStatus());
            }
        }
    }
     static void dispnext(Mybutton p)
    {


                int i=p.getAddressx();
                int j=p.getAddressy();
                    int[]   steprow= {-1,0,1,1,1,0,-1,-1};
                    int[] stepcolumn={-1,-1,-1,0,1,1,1,0};
                    for(int q=0;q<8;q++) {
                        int nextx2=i+steprow[q];
                        int nexty2=j+stepcolumn[q];
                        if(inRange(nextx2,nexty2,n))
                        {
                            if(buttons[nextx2][nexty2].getStatus().equals("*") && !buttons[nextx2][nexty2].isEnabled())
                            {
                                buttons[nextx2][nexty2].setText(buttons[nextx2][nexty2].getStatus());
                                buttons[nextx2][nexty2].setEnabled(false);
                                return;
                            }

                            else
                            if( buttons[nextx2][nexty2].getStatus().equals("0") && !buttons[nextx2][nexty2].isEnabled())
                            {
                                    buttons[nextx2][nexty2].setText(p.getStatus());
                                buttons[nextx2][nexty2].setEnabled(false);
                                dispnext(buttons[nextx2][nexty2]);

                       //        fscore.setText(result);

                            }
                            else if(buttons[nextx2][nexty2].isEnabled())
                            {
                                return;
                            }
                            else
                            {
                                buttons[nextx2][nexty2].setText(p.getStatus());
                                buttons[nextx2][nexty2].setEnabled(false);
                              continue;
                            }
                        }

                }
            }
        }



