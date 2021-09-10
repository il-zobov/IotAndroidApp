package com.example.relays2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final int relayNom =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button goBack = (Button) findViewById(R.id.backToMain);
        TextView nightTemp  = ((TextView) findViewById(R.id.nightTime));
        Button sendBut = (Button) findViewById(R.id.backToMain);




        ((Button) findViewById(R.id.sendUpdate)).setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RelayState dataToSend = new RelayState(new Morning(), new Day(),new Evening(), new Night());
                dataToSend.relay = relayNom;
                try {

                    String[] splitstr;
                    splitstr  = ((TextView) findViewById(R.id.nightTime)).getText().toString().split(":",2);
                    dataToSend.night.temp =  Double.parseDouble(((TextView) findViewById(R.id.nitghtTemp)).getText().toString());
                    dataToSend.night.h = Integer.parseInt(splitstr[0]);
                    dataToSend.night.m = Integer.parseInt(splitstr[1]);

                    splitstr  = ((TextView) findViewById(R.id.morningTime)).getText().toString().split(":",2);
                    dataToSend.morning.temp =  Double.parseDouble(((TextView) findViewById(R.id.morningTemp)).getText().toString());
                    dataToSend.morning.h = Integer.parseInt(splitstr[0]);
                    dataToSend.morning.m = Integer.parseInt(splitstr[1]);

                    splitstr  = ((TextView) findViewById(R.id.dayTime)).getText().toString().split(":",2);
                    dataToSend.day.temp =  Double.parseDouble(((TextView) findViewById(R.id.dayTemp)).getText().toString());
                    dataToSend.day.h = Integer.parseInt(splitstr[0]);
                    dataToSend.day.m = Integer.parseInt(splitstr[1]);

                    splitstr  = ((TextView) findViewById(R.id.eveningTime)).getText().toString().split(":",2);
                    dataToSend.evening.temp =  Double.parseDouble(((TextView) findViewById(R.id.eveningTemp)).getText().toString());
                    dataToSend.evening.h = Integer.parseInt(splitstr[0]);
                    dataToSend.evening.m = Integer.parseInt(splitstr[1]);


                }catch (NumberFormatException e) {
                    ///todo Error...
                }
                // ..
                NetworkService.getInstance()
                        .getJSONApi()
                        .postRelayState(dataToSend)
                        .enqueue(new Callback<RelayState>() {
                            @Override
                            public void onResponse(Call<RelayState> call, Response<RelayState> response) {

                            }

                            @Override
                            public void onFailure(Call<RelayState> call, Throwable t) {
                               Toast.makeText(MainActivity.this, "Faled to Send Data",Toast.LENGTH_SHORT).show();
                                t.printStackTrace();
                            }
                        });
            }
        });

        //  final TextView nightTime = (TextView) findViewById(R.id.nightTime);
        NetworkService.getInstance()
                .getJSONApi()
                .getAllRelays()
                .enqueue(new Callback<List<RelayState>>() {
                    @Override
                    public void onResponse(Call<List<RelayState>> call, Response<List<RelayState>> response) {

                        List<RelayState> relayState = response.body();


                        ((TextView) findViewById(R.id.nightTime)).setText(relayState.get(relayNom-1).night.h+":"+ relayState.get(relayNom-1).night.m);
                        ((TextView) findViewById(R.id.nitghtTemp)).setText(String.valueOf(relayState.get(1).night.temp));

                        ((TextView) findViewById(R.id.morningTime)).setText(relayState.get(relayNom-1).morning.h+":"+ relayState.get(relayNom-1).morning.m);
                        ((TextView) findViewById(R.id.morningTemp)).setText(String.valueOf(relayState.get(relayNom-1).morning.temp));

                        ((TextView) findViewById(R.id.dayTime)).setText(relayState.get(relayNom-1).day.h+":"+ relayState.get(relayNom-1).day.m);
                        ((TextView) findViewById(R.id.dayTemp)).setText(String.valueOf(relayState.get(relayNom-1).day.temp));

                        ((TextView) findViewById(R.id.eveningTime)).setText(relayState.get(relayNom-1).evening.h+":"+ relayState.get(relayNom-1).evening.m);
                        ((TextView) findViewById(R.id.eveningTemp)).setText(String.valueOf(relayState.get(relayNom-1).evening.temp));

                        ((TextView) findViewById(R.id.currTemp)).setText(String.valueOf(relayState.get(relayNom-1).currentTemp));
                    }

                    @Override
                    public void onFailure(Call<List<RelayState>> call, Throwable t) {
                         Toast.makeText(MainActivity.this, "Faled to get data ",Toast.LENGTH_SHORT).show();

                        // textView.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }

                });


     //   textView.setSingleLine(false);

        Button clickButton = (Button) findViewById(R.id.forward);

        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RelayA2.class);
                startActivity(intent);
            }
        });


    }


}
