package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.data.NavMenuItemsDataBase;
import br.com.bittencourt.boni.lucas.blueshoes.domain.NavMenuItem;
import br.com.bittencourt.boni.lucas.blueshoes.domain.User;
import br.com.bittencourt.boni.lucas.blueshoes.util.NavMenuItemDetailsLookup;
import br.com.bittencourt.boni.lucas.blueshoes.util.NavMenuItemKeyProvider;
import br.com.bittencourt.boni.lucas.blueshoes.util.NavMenuItemPredicate;

/**
 * Icones tirados de http://materialdesignicons.com/
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String ID_SELECTED_ITEM_LOGGED = "id-selected-item-logged";
    public static final String ID_SELECTED_ITEM = "id-selected-item";
    public static final String FRAGMENT_TAG  = "frag-tag";
    public static final String  FRAGMENT_ID = "frag-id";

    private Toolbar toolbar;
    private RecyclerView rv_menu_items;
    private RecyclerView rv_menu_items_logged;
    private DrawerLayout drawer_layout;
    private RoundedImageView iv_user;
    private TextView tv_user;
    private RelativeLayout rl_header_user_not_logged;
    private RelativeLayout rl_header_user_logged;
    private View v_nav_vertical_line;
    private Button bt_login;



    private User user = new User( "Lucas Boni", R.drawable.user,false);

    private SelectionTracker<Long> selectNavMenuItems;
    private List<NavMenuItem> navMenuItems;


    private List<NavMenuItem> navMenuItemsLogged;
    private SelectionTracker<Long> selectNavMenuItemsLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_menu_items = findViewById(R.id.rv_menu_items);
        rv_menu_items_logged = findViewById(R.id.rv_menu_items_logged);
        iv_user = findViewById(R.id.iv_user);
        tv_user = findViewById(R.id.tv_user);
        rl_header_user_not_logged = findViewById(R.id.rl_header_user_not_logged);
        rl_header_user_logged = findViewById(R.id.rl_header_user_logged);
        v_nav_vertical_line = findViewById(R.id.v_nav_vertical_line);
        drawer_layout = findViewById(R.id.drawer_layout);
        bt_login = findViewById(R.id.bt_login);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        initNavMenu( savedInstanceState );//inicia os menus do navbar
        initFragment();//inicia o fragmenteinicial

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callLoginActivity(v);
            }
        });

    }

    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if(savedInstanceState!=null) {
            if (selectNavMenuItems != null) {
                selectNavMenuItems.onSaveInstanceState(savedInstanceState);
            }
            if (selectNavMenuItemsLogged != null) {
                selectNavMenuItemsLogged.onSaveInstanceState(savedInstanceState);
            }
        }
    }


    private void fillUserHeaderNavMenu(){
        if( user.isStatus() ) { /* Conectado */
            iv_user.setImageResource(user.getImage());
            tv_user.setText(user.getName());
        }
    }

    /*
     * Método responsável por esconder itens do menu gaveta de
     * acordo com o status do usuário (conectado ou não).
     * */
    private void showHideNavMenuViews(){
        if( user.isStatus() ){ /* Conectado */
            rl_header_user_not_logged.setVisibility(View.GONE);//esconde as opçoes de fazer login
            fillUserHeaderNavMenu();//mostra as opções de usuario
        }
        else{  /* Não conectado */
            rl_header_user_logged.setVisibility(View.GONE);//esconde as inforações do usuario
            v_nav_vertical_line.setVisibility(View.GONE);//esconde a linha de separação
            rv_menu_items_logged.setVisibility(View.GONE);//esconde os itens para o usuario logado
        }
    }


    /*
     * Método de inicialização do menu gaveta, responsável por
     * apresentar o cabeçalho e itens de menu de acordo com o
     * status do usuário (logado ou não).
     * */
    private void initNavMenu(Bundle savedInstanceState ){

        NavMenuItemsDataBase navMenu = new NavMenuItemsDataBase(this);//carrega a base de dados
        navMenuItems = navMenu.getItems();//obtem os itens padroes
        navMenuItemsLogged = navMenu.getItemsLogged();//obtem os itens de ususario logado

        showHideNavMenuViews();//verifica se o usuraio esta logado ou nao e mostra o que se deve

        initNavMenuItems();//seta os parametros para a lista que sempre é exibida
        initNavMenuItemsLogged();//seta os parametros para a lista que sempre é exibido quando logado

        if( savedInstanceState != null ){//erifica se tem um status para recuperar
            selectNavMenuItems.onRestoreInstanceState( savedInstanceState );
            selectNavMenuItemsLogged.onRestoreInstanceState( savedInstanceState );
        }
        else{//se nao selecionado o objeto co o promeiro id

            /*
             * Verificando se há algum item ID em intent. Caso não,
             * utilize o ID do primeiro item.
             * */
            int fragId = 0;
            if(getIntent()!=null) {
                fragId = getIntent().getIntExtra(FRAGMENT_ID, 0);
            }
            if(fragId == 0){
                fragId = R.id.item_all_shoes;
            }

            /*
             * O primeiro item do menu gaveta deve estar selecionado
             * caso não seja uma reinicialização de tela / atividade.
             * O primeiro item aqui é o de ID 1.
             * */
            selectNavMenuItems.select((long)fragId );
        }
        addObserverList();//coloca depois para nenhum dos dois menus ficarem null
    }


    /*
     * Método que inicializa a lista de itens de menu gaveta
     * que estará presente quando o usuário estiver ou não
     * conectado ao aplicativo.
     * */
    private void initNavMenuItems() {
        rv_menu_items.setHasFixedSize(false);//deixa sem tamanho fixo
        rv_menu_items.setLayoutManager(new LinearLayoutManager(this));//comporamento linear
        rv_menu_items.setAdapter(new NavMenuItemsAdapter(navMenuItems));//inicia o adaper enviando os itens do database referente

        initNavMenuItemsSelection();//inicializa objeto de selecao
    }

    /*
     * Método responsável por inicializar o objeto de seleção
     * de itens de menu gaveta, seleção dos itens que aparecem
     * para usuário conectado ou não.
     * */
    private void initNavMenuItemsSelection(){

        selectNavMenuItems = new SelectionTracker.Builder<Long>(
                ID_SELECTED_ITEM,
                rv_menu_items,
                new NavMenuItemKeyProvider( navMenuItems ),
                new NavMenuItemDetailsLookup( rv_menu_items ),//permite obter os detalhes
                StorageStrategy.createLongStorage())
                .withSelectionPredicate( new NavMenuItemPredicate( this ))//logica de negocio de selecao
                .build();

        ((NavMenuItemsAdapter)rv_menu_items.getAdapter()).setSelectionTracker(selectNavMenuItems);//seta o objeto de selecao
    }


    /*
     * Método responsável por inicializar o objeto de seleção
     * de itens de menu gaveta, seleção dos itens que aparecem
     * somente quando o usuário está conectado ao app.
     * */
    private void initNavMenuItemsLoggedSelection(){

        selectNavMenuItemsLogged = new SelectionTracker.Builder<Long>(
                ID_SELECTED_ITEM_LOGGED,
                rv_menu_items_logged,
                new NavMenuItemKeyProvider( navMenuItemsLogged ),
                new NavMenuItemDetailsLookup( rv_menu_items_logged ),
                StorageStrategy.createLongStorage()
        )
                .withSelectionPredicate( new NavMenuItemPredicate( this ) )
                .build();

        ((NavMenuItemsAdapter)rv_menu_items_logged.getAdapter()).setSelectionTracker(selectNavMenuItemsLogged);
    }


    private void addObserverList(){//adiciona para uma lista observar a outra quando selecionada
        selectNavMenuItemsLogged.addObserver(new SelectObserverNavMenuItems(selectNavMenuItems ));
        selectNavMenuItems.addObserver(new SelectObserverNavMenuItems(selectNavMenuItemsLogged));
    }


    /*
     * Método que inicializa a parte de lista de itens de menu
     * gaveta que estará presente somente quando o usuário
     * estiver conectado ao aplicativo.
     * */
    private void initNavMenuItemsLogged() {
        rv_menu_items_logged.setHasFixedSize(true);
        rv_menu_items_logged.setLayoutManager(new LinearLayoutManager(this));
        rv_menu_items_logged.setAdapter(new NavMenuItemsAdapter(new NavMenuItemsDataBase(this).getItemsLogged()));

        initNavMenuItemsLoggedSelection();
    }


    private void initFragment(){
        FragmentManager supFrag  = getSupportFragmentManager();
        Fragment fragment  = supFrag .findFragmentByTag(FRAGMENT_TAG);
        /*
         * Se não for uma reconstrução de atividade, então não
         * haverá um fragmento em memória, então busca-se o
         * inicial.
         * */
        int fragId=0;
        if( fragment == null ){

            /*
             * Caso haja algum ID de fragmento em intent, então
             * é este fragmento que deve ser acionado. Caso
             * contrário, abra o fragmento comum de início.
             * */

            if(getIntent()!=null) {
                fragId = getIntent().getIntExtra(FRAGMENT_ID, 0);
            }
            if( fragId == 0 ){
                fragId = R.id.item_about;
            }
        }

        fragment = getFragment(fragId);

        replaceFragment(fragment);
    }


    private Fragment getFragment(long fragmentId) {
        switch ((int) fragmentId) {//busca o fragente pelo id do item selecionado na lista
            case R.id.item_about:
                return new AboutFragment();
            case R.id.item_contact:
                return new ContactFragment();
            default:
                return new AboutFragment();
        }
    }



    private void replaceFragment( Fragment fragment ){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.fl_fragment_container,
                        fragment,
                        FRAGMENT_TAG
                )
                .commit();
    }



    public SelectionTracker<Long> getSelectNavMenuItems() {
        return selectNavMenuItems;
    }

    public SelectionTracker<Long> getSelectNavMenuItemsLogged() {
        return selectNavMenuItemsLogged;
    }

    public void updateToolbarTitleInFragment(int about_frag_title) {
        toolbar.setTitle(about_frag_title);
    }

    void callLoginActivity(  View view){
        Intent intent = new Intent( this, LoginActivity.class);
        startActivity( intent );
    }


    void callPrivacyPolicyFragment( View  view){
        Intent intent = new Intent(this,MainActivity.class);

        /*
         * Para saber qual fragmento abrir quando a
         * MainActivity voltar ao foreground.
         * */
        intent.putExtra(MainActivity.FRAGMENT_ID,R.id.item_privacy_policy);

        /*
         * Removendo da pilha de atividades a primeira
         * MainActivity aberta (e a LoginActivity), para
         * deixar somente a nova MainActivity com uma nova
         * configuração de fragmento aberto.
         * */
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity( intent );
    }


    public class SelectObserverNavMenuItems extends SelectionTracker.SelectionObserver<Long>{


        private SelectionTracker<Long>  selectionTracker;//referencia da outra lista

        public SelectObserverNavMenuItems(SelectionTracker<Long> selectionTracker) {
            this.selectionTracker = selectionTracker;
        }

        //private Method callbackRemoveSelection;

        /*public SelectObserverNavMenuItems(Method callbackRemoveSelection) {
            this.callbackRemoveSelection = callbackRemoveSelection;
        }*/

        /*
         * Método responsável por permitir que seja possível
         * disparar alguma ação de acordo com a mudança de
         * status de algum item em algum dos objetos de seleção
         * de itens de menu gaveta. Aqui vamos proceder com
         * alguma ação somente em caso de item obtendo seleção,
         * para item perdendo seleção não haverá processamento,
         * pois este status não importa na lógica de negócio
         * deste método.
         * */
        @Override
        public void onItemStateChanged(@NonNull Long key, boolean selected) {
            super.onItemStateChanged(key, selected);
            /*
             * Padrão Cláusula de Guarda para não seguirmos
             * com o processamento em caso de item perdendo
             * seleção. O processamento posterior ao condicional
             * abaixo é somente para itens obtendo a seleção,
             * selected = true.
             * */
            if( !selected ){//se ele nao mudou nao faz nada
                return;
            }

            /*
             * Para garantir que somente um item de lista se
             * manterá selecionado, é preciso acessar o objeto
             * de seleção da lista de itens de usuário conectado
             * para então remover qualquer possível seleção
             * ainda presente nela. Sempre haverá somente um
             * item selecionado, mas infelizmente o método
             * clearSelection() não estava respondendo como
             * esperado, por isso a estratégia a seguir.
             * */


            selectionTracker.clearSelection();//apaga todos os selecionados da outra lista

            /*for(long id:selectNavMenuItemsLogged.getSelection()){
                selectNavMenuItemsLogged.deselect(id);
            }*/

            //callbackRemoveSelection();

            /*
             * TODO: Mudança de Fragment
             * */


            Fragment fragment = getFragment( key );
            replaceFragment( fragment );


            /*
             * Fechando o menu gaveta.
             * */
            drawer_layout.closeDrawer( GravityCompat.START );//fechar a gaveta que esta aberta
        }

    }


}
