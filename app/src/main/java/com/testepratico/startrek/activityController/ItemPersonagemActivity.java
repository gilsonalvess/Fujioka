package com.testepratico.startrek.activityController;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.testepratico.startrek.R;
import com.testepratico.startrek.model.Personagem;
import com.testepratico.startrek.repository.PersonagemDAO;

public class ItemPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_personagem);

        final EditText nome = (EditText) findViewById(R.id.id_edt_nome);
        final EditText funcao = (EditText) findViewById(R.id.id_edt_funcao);
        final EditText frota = (EditText) findViewById(R.id.id_edt_frota);
        final ImageView foto = (ImageView) findViewById(R.id.id_img_cad_pers);
        final TextView textNovoTrip = (TextView) findViewById(R.id.id_txt_novo_trip);
        final Button btnCadastro = (Button) findViewById(R.id.id_btn_salvar);

        final Personagem personagem = (Personagem) getIntent().getSerializableExtra("personagem");
        foto.setImageResource(R.drawable.foto_pers_default_img);

        if (personagem != null) {
            nome.setText(personagem.getNome());
            funcao.setText(personagem.getFuncao());
            frota.setText(personagem.getFrota());

            if (personagem.getImagem() != null) {
                int idImagem = getBaseContext().getResources().getIdentifier(personagem.getImagem(), "drawable", getBaseContext().getPackageName());
                foto.setImageResource(idImagem);
            } else {
                foto.setImageResource(R.drawable.foto_pers_default_img);
            }

            btnCadastro.setText("Editar");
            textNovoTrip.setText("Alterar Cadastro");
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Tripulante");
        }

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonagemDAO crud = new PersonagemDAO(getBaseContext());

                String nomeString = nome.getText().toString();
                String funcaoString = funcao.getText().toString();
                String frotaString = frota.getText().toString();

                if (nomeString.equals("") || funcaoString.equals("") || frotaString.equals("")) {
                    Toast.makeText(ItemPersonagemActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                String resultado;

                if (btnCadastro.getText().equals("Editar") && personagem != null) {
                    personagem.setNome(nomeString);
                    personagem.setFuncao(funcaoString);
                    personagem.setFrota(frotaString);
                    resultado = crud.atualizaDados(personagem);
                } else {
                    resultado = crud.inserirPersonagem(nomeString, null, frotaString, funcaoString);
                }
                Toast.makeText(ItemPersonagemActivity.this, resultado, Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
