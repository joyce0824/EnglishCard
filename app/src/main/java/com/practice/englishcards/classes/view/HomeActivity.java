package com.practice.englishcards.classes.view;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.practice.englishcards.classes.module.BookEntry;
import com.practice.englishcards.R;
import com.socks.library.KLog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private List<BookEntry> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initToolBar();
        initDrawerLayout();
    }

    private void initDrawerLayout() {
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle taggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(taggle);
        taggle.syncState();
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        closeDrawer();
        return true;
    }

    private void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    private void dummyData() {
        KLog.i("JOJO", "dummyData");
        String data = "{\"book\":\"book1\",\"classes\":[{\"className\":\"01-Songs & Stories_1\",\"listening\":[\"01/01\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],\"shadowing\":[\"01/01\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],\"word\":[{\"En\":\"place\",\"Ch\":\"地方\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"party\",\"Ch\":\"舞會\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"poster\",\"Ch\":\"海報\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"present\",\"Ch\":\"禮物\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"fault\",\"Ch\":\"錯誤\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"idea\",\"Ch\":\"想法\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"joke\",\"Ch\":\"笑話\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"question\",\"Ch\":\"問題\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"story\",\"Ch\":\"故事\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"sentence\",\"Ch\":\"句子\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"dictionary\",\"Ch\":\"字典\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"conversation\",\"Ch\":\"會話\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"interest\",\"Ch\":\"興趣\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"bath\",\"Ch\":\"洗澡\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"trip\",\"Ch\":\"旅行\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"medicine\",\"Ch\":\"藥\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"experience\",\"Ch\":\"經驗\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"different\",\"Ch\":\"不同\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"communication\",\"Ch\":\"溝通\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"software\",\"Ch\":\"軟體\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]}]},{\"className\":\"07-At the Zoo\",\"listening\":[\"01/01\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],\"shadowing\":[\"01/01\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],\"word\":[{\"En\":\"place\",\"Ch\":\"地方\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"party\",\"Ch\":\"舞會\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"poster\",\"Ch\":\"海報\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"present\",\"Ch\":\"禮物\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"fault\",\"Ch\":\"錯誤\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"idea\",\"Ch\":\"想法\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"joke\",\"Ch\":\"笑話\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"question\",\"Ch\":\"問題\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"story\",\"Ch\":\"故事\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"sentence\",\"Ch\":\"句子\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"dictionary\",\"Ch\":\"字典\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"conversation\",\"Ch\":\"會話\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"interest\",\"Ch\":\"興趣\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"bath\",\"Ch\":\"洗澡\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"trip\",\"Ch\":\"旅行\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"medicine\",\"Ch\":\"藥\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"experience\",\"Ch\":\"經驗\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"different\",\"Ch\":\"不同\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"communication\",\"Ch\":\"溝通\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]},{\"En\":\"software\",\"Ch\":\"軟體\",\"Day_EnToCh\":[\"02/03\",\"\",\"\"],\"Day_ChToEn\":[\"02/03\",\"\",\"\"]}]}]}";
//        entries = GsonUtil.fromJson(data,BookEntry.class);
    }
}
