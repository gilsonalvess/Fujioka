package com.testepratico.fujioka.activityController;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.testepratico.fujioka.R;
import com.testepratico.fujioka.model.Personagem;
import com.testepratico.fujioka.repository.PersonagemDAO;

import java.util.ArrayList;

public class PersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personagem);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Personagens");
        }
        carregaListaDePersonagens();
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
        carregaListaDePersonagens();
    }

    protected void editarPersonagem(View view, int position, ArrayList<Personagem> listaDePersonagens) {
        Intent intent = new Intent(this, ItemPersonagemActivity.class);
        intent.putExtra("personagem", listaDePersonagens.get(position));
        startActivity(intent);
    }

    protected void carregaListaDePersonagens(){
        PersonagemDAO personagemDAO = new PersonagemDAO(this);

        final ArrayList<Personagem> listaDePersonagens = personagemDAO.carregarPersonagens();
        final PersonagemAdapter adapter = new PersonagemAdapter(listaDePersonagens, this);
        final ListView listView = (ListView) findViewById(R.id.id_list_personagens);

        if(listaDePersonagens != null){
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editarPersonagem(view, position, listaDePersonagens);
            }
        });
    }
}
