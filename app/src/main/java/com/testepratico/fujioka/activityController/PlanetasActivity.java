package com.testepratico.fujioka.activityController;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Planetas");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
