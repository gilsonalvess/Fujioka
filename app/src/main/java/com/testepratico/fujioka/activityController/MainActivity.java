package com.testepratico.fujioka.activityController;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.testepratico.fujioka.R;
import com.testepratico.fujioka.model.Personagem;
import com.testepratico.fujioka.model.Planeta;
import com.testepratico.fujioka.repository.PersonagemDAO;
import com.testepratico.fujioka.services.ApiCliente;
import com.testepratico.fujioka.services.ApiInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_planetas) {
            Intent intent = new Intent(this, PersonagemActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_personagens) {
            carregaLista();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void cadastrarPersonagem(View.OnClickListener view) {
        Intent intent = new Intent(this, ItemPersonagemActivity.class);
        startActivity(intent);
    }

    protected void carregaLista() {

        apiInterface = ApiCliente.getClient().create(ApiInterface.class);
        Call<ArrayList<Planeta>> call = apiInterface.getPlanetas();
        call.enqueue(new Callback<ArrayList<Planeta>>() {
            @Override
            public void onResponse(Call<ArrayList<Planeta>> call, Response<ArrayList<Planeta>> response) {
                final Intent intent = new Intent(getBaseContext(), PlanetasActivity.class);
                if(response.isSuccessful()){
                    ArrayList<Planeta> planetas = response.body();
                    intent.putExtra("planetas", planetas);
                    startActivity(intent);
                }
                //TODO melhorar validações(Verificar se existe conexão com a internet)
                //TODO setar as imagens nos itens da lista
                //TODO Criar splash  para update da lista
                //TODO Colocar "voltar" no toolbar da view das listas
                Log.d("TAG", response.code() + "");
                //Toast.makeText(LoginActivity.this, "Email e/ou senha inválidos. Tente novamente!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrayList<Planeta>> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
