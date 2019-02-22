package com.testepratico.startrek.activityController;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.testepratico.startrek.R;
import com.testepratico.startrek.model.Personagem;
import com.testepratico.startrek.model.Planeta;
import com.testepratico.startrek.repository.PersonagemDAO;
import com.testepratico.startrek.services.ApiCliente;
import com.testepratico.startrek.services.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarPersonagem(this);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        isOnline();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_planetas) {
            carregaListaPlanetas();
        } else if (id == R.id.nav_personagens) {
            carregaListaPersonagens();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void cadastrarPersonagem(View.OnClickListener view) {
        Intent intent = new Intent(this, ItemPersonagemActivity.class);
        startActivity(intent);
    }

    protected void carregaListaPlanetas() {
        if (isOnline()) {
            apiInterface = ApiCliente.getClient().create(ApiInterface.class);
            Call<ArrayList<Planeta>> call = apiInterface.getPlanetas();
            loading();
            call.enqueue(new Callback<ArrayList<Planeta>>() {
                @Override
                public void onResponse(Call<ArrayList<Planeta>> call, Response<ArrayList<Planeta>> response) {
                    final Intent intent = new Intent(getBaseContext(), PlanetasActivity.class);
                    if (response.isSuccessful()) {
                        ArrayList<Planeta> planetas = response.body();
                        intent.putExtra("planetas", planetas);
                        startActivity(intent);
                    }
                    Log.d("TAG", response.code() + "");
                }

                @Override
                public void onFailure(Call<ArrayList<Planeta>> call, Throwable t) {
                    call.cancel();
                }
            });
        }
    }

    protected void carregaListaPersonagens() {
        if (isOnline()) {
            apiInterface = ApiCliente.getClient().create(ApiInterface.class);
            Call<ArrayList<Personagem>> call = apiInterface.getPersonagens();
            loading();
            call.enqueue(new Callback<ArrayList<Personagem>>() {
                @Override
                public void onResponse(Call<ArrayList<Personagem>> call, Response<ArrayList<Personagem>> response) {
                    final Intent intent = new Intent(getBaseContext(), PersonagemActivity.class);
                    if (response.isSuccessful()) {
                        ArrayList<Personagem> personagens = response.body();
                        salvarPersonagens(personagens);
                        startActivity(intent);
                    }
                    Log.d("TAG", response.code() + "");
                }

                @Override
                public void onFailure(Call<ArrayList<Personagem>> call, Throwable t) {
                    call.cancel();
                }
            });
        }
    }

    protected void salvarPersonagens(ArrayList<Personagem> personagens) {
        PersonagemDAO personagemDAO = new PersonagemDAO(getBaseContext());
        ArrayList<Personagem> personagensExist = personagemDAO.carregarPersonagens();

        if (personagensExist.size() == 0) {
            Toast.makeText(MainActivity.this, "Baixando lista...", Toast.LENGTH_SHORT).show();
            for (Personagem personagem : personagens) {
                personagemDAO.inserirPersonagem(
                        personagem.getNome(),
                        personagem.getImagem(),
                        personagem.getFuncao(),
                        personagem.getFrota()
                );
            }
        }
    }

    protected boolean isOnline() {
        if (!ApiCliente.isOnline(this)) {
            Toast.makeText(this, "Você está sem conexão com internet! Não será possível baixar as listas", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    protected void loading() {
        Toast.makeText(MainActivity.this, "Carregando...", Toast.LENGTH_SHORT).show();
    }
}
