package com.testepratico.fujioka.activityController;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.testepratico.fujioka.R;
import com.testepratico.fujioka.model.Personagem;
import com.testepratico.fujioka.repository.PersonagemDAO;

public class ItemPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_personagem);

        final EditText nome = (EditText) findViewById(R.id.id_edt_nome);
        final EditText funcao = (EditText) findViewById(R.id.id_edt_funcao);
        final EditText frota = (EditText) findViewById(R.id.id_edt_frota);

        final Button btnCadastro = (Button) findViewById(R.id.id_btn_salvar);

        final Personagem personagem = (Personagem) getIntent().getSerializableExtra("personagem");

        if(personagem != null){
            nome.setText(personagem.getNome());
            funcao.setText(personagem.getFuncao());
            frota.setText(personagem.getFrota());

            btnCadastro.setText("Editar");
        }

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonagemDAO crud = new PersonagemDAO(getBaseContext());

                String nomeString = nome.getText().toString();
                String funcaoString = funcao.getText().toString();
                String frotaString = frota.getText().toString();

                if(nomeString.equals("") || funcaoString.equals("") || frotaString.equals("")){
                    Toast.makeText(ItemPersonagemActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                String resultado;

                if(btnCadastro.getText().equals("Editar") && personagem != null){
                    personagem.setNome(nomeString);
                    personagem.setFuncao(funcaoString);
                    personagem.setFrota(frotaString);
                    resultado = crud.atualizaDados(personagem);
                }else{
                    resultado = crud.inserirPersonagem(nomeString, null, frotaString, funcaoString);
                }
                Toast.makeText(ItemPersonagemActivity.this, resultado, Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
