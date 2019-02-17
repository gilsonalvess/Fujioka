package com.testepratico.fujioka.activityController;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.testepratico.fujioka.R;
import com.testepratico.fujioka.model.Planeta;

import java.util.ArrayList;

public class PlanetasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planetas);

        ArrayList<Planeta> planetas = (ArrayList<Planeta>) getIntent().getSerializableExtra("planetas");
        final PlanetaAdapter adapter = new PlanetaAdapter(planetas, this);
        final ListView listView = (ListView) findViewById(R.id.id_list_planetas);

        if(planetas != null){
            listView.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
