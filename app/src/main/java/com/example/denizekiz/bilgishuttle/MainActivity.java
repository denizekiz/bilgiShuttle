package com.example.denizekiz.bilgishuttle;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {

public TextView counttext;
    public TextView fromText;
    public TextView toText;
    public Button btn;


    private CountDownTimer countDownTimer;

    public String fromStat="";
    public String toStat="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
counttext= (TextView)this.findViewById(R.id.timer);
        final ListView from = (ListView) findViewById(R.id.listView);
        final ListView tos= (ListView)findViewById(R.id.listView2);
        fromText= (TextView) findViewById(R.id.textView);
        toText= (TextView) findViewById(R.id.textView2);
       btn= (Button) findViewById(R.id.button);

        String[] values = new String[] { "Santral","Kabatas", "Halicioglu" };




        final ArrayList<String> fromlist = new ArrayList<String>();
        final ArrayList<String> tolist= new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            fromlist.add(values[i]);
            tolist.add(values[i]);
        }
        final StableArrayAdapter toadapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, tolist);
        final StableArrayAdapter fromadapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, fromlist);

        btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {

                long[] tar=timerarray(fromStat,toStat);
                fromText.setText(fromStat +" ");
                toText.setText(toStat);

            long currenttime= System.currentTimeMillis()%86400000 ;
                int i=0;
                while((tar[i]-currenttime)<0 && i< tar.length)
                {
                   i++;
                }
                long diff= tar[i]-currenttime;
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                countDownTimer=new CountDownTimer(diff, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {


                        long seconds = millisUntilFinished / 1000;
                        long minutes = seconds / 60;
                        long hours = minutes / 60;

                        String time = hours % 24 + ":" + minutes % 60 + ":" + seconds % 60;
                        counttext.setText( time);
                    }

                    @Override
                    public void onFinish() {
                        counttext.setText("0:0:0");
                        countDownTimer.cancel();
                    }
                }.start();
            }
        });


        from.setAdapter(fromadapter);
    tos.setAdapter(toadapter);

        from.setOnItemClickListener(new AdapterView.OnItemClickListener() {




            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                if(!toStat.equals(item))
                {
                fromStat=item;}
                /*view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                fromlist.remove(item);
                                fromadapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });*/
            }

        });

        tos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                if(!fromStat.equals(item)){
                toStat=item;}
                /*view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                fromlist.remove(item);
                                fromadapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });*/
            }

        });


    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

    public long[] timerarray(String from, String to)
    {
        if(from.equals("Santral")& to.equals("Kabatas"))
        {
           long[] sk=new long[]{36000000,39600000,43200000,46800000,50400000,54000000};
            return sk;
        }
        else if(from.equals("Santral")& to.equals("Halicioglu"))
        {
            long[] sk=new long[]{36100000,39700000,43300000,46900000,50500000,55000000};
            return sk;
        }
        else if(from.equals("Kabatas")& to.equals("Santral"))
        {
            long[] sk=new long[]{36800000,39800000,43500000,47000000,50500000,50400000};
            return sk;
        }
        else if(from.equals("Kabatas")& to.equals("Halicioglu"))
        {
            long[] sk=new long[]{36500000,39900000,43500000,46900000,50400000,54000000};
            return sk;
        }
        else if(from.equals("Halicioglu")& to.equals("Kabatas"))
        {
            long[] sk=new long[]{32000000,37600000,41200000,44800000,49400000,52000000};
            return sk;
        }
        else if(from.equals("Halicioglu")& to.equals("Santral"))
        {
            long[] sk=new long[]{33000000,40600000,46200000,49800000,58400000,59000000};
            return sk;
        }
        else {
      long [] sk= new long[]{0};
        return sk;}

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
