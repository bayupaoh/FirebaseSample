package me.bayupaoh.firebasesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import me.bayupaoh.firebasesample.model.ModelGarasi;

public class GarageActivity extends AppCompatActivity {

    TextView garage1,garage2,garage3,garage4;
    Firebase firebase;
    boolean gr1=true,gr2=false,gr3=true,gr4=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
        deklarasiWidget();
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://samplesmok.firebaseio.com/");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.print("Cek bayu : "+dataSnapshot.getValue());
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    ModelGarasi modelGarasi = postSnapshot.getValue(ModelGarasi.class);
                    Log.d(postSnapshot.getKey()+" Garasi : ", String.valueOf(modelGarasi.getStatus()));

                    if(postSnapshot.getKey().equals("posisi1")){
                        if(modelGarasi.getStatus() == 1) {
                            gr1=true;
                            garage1.setBackgroundColor(getResources().getColor(R.color.colorBooked));
                        }else{
                            gr1=false;
                            garage1.setBackgroundColor(getResources().getColor(R.color.colorEmpty));
                        }
                    }else if(postSnapshot.getKey().equals("posisi2")){
                        if(modelGarasi.getStatus() == 1) {
                            gr2=true;
                            garage2.setBackgroundColor(getResources().getColor(R.color.colorBooked));
                        }else{
                            gr2=false;
                            garage2.setBackgroundColor(getResources().getColor(R.color.colorEmpty));
                        }

                    }else if(postSnapshot.getKey().equals("posisi3")){

                        if(modelGarasi.getStatus() == 1) {
                            gr3=true;
                            garage3.setBackgroundColor(getResources().getColor(R.color.colorBooked));
                        }else{
                            gr3=false;
                            garage3.setBackgroundColor(getResources().getColor(R.color.colorEmpty));
                        }
                    }else if(postSnapshot.getKey().equals("posisi4")){
                        if(modelGarasi.getStatus() == 1) {
                            gr4=true;
                            garage4.setBackgroundColor(getResources().getColor(R.color.colorBooked));
                        }else{
                            gr4=false;
                            garage4.setBackgroundColor(getResources().getColor(R.color.colorEmpty));
                        }

                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("Rerro firebase : ",firebaseError.getMessage());
            }
        });

        garage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelGarasi modelGarasi = new ModelGarasi();
                if(gr1) {
                    modelGarasi.setStatus(0);
                    firebase.child("posisi1").setValue(modelGarasi);
                }else {
                    gr1=true;
                    modelGarasi.setStatus(1);
                    firebase.child("posisi1").setValue(modelGarasi);
                }
            }
        });

        garage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelGarasi modelGarasi = new ModelGarasi();
                if(gr2) {
                    modelGarasi.setStatus(0);
                    firebase.child("posisi2").setValue(modelGarasi);
                }else{
                    gr2=true;
                    modelGarasi.setStatus(1);
                    firebase.child("posisi2").setValue(modelGarasi);
                }
            }
        });

        garage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelGarasi modelGarasi = new ModelGarasi();
                if(gr3) {
                    modelGarasi.setStatus(0);
                    firebase.child("posisi3").setValue(modelGarasi);
                }else{
                    gr3=true;
                    modelGarasi.setStatus(1);
                    firebase.child("posisi3").setValue(modelGarasi);
                }
            }
        });

        garage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelGarasi modelGarasi = new ModelGarasi();
                if(gr4) {
                    modelGarasi.setStatus(0);
                    firebase.child("posisi4").setValue(modelGarasi);
                }else {
                    gr4=true;
                    modelGarasi.setStatus(1);
                    firebase.child("posisi4").setValue(modelGarasi);
                }
            }
        });
    }

    private void deklarasiWidget() {
        garage1 = (TextView) findViewById(R.id.garage_1);
        garage2 = (TextView) findViewById(R.id.garage_2);
        garage3 = (TextView) findViewById(R.id.garage_3);
        garage4 = (TextView) findViewById(R.id.garage_4);
    }
}
