package org.classapp.tidseries1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomeActivity extends AppCompatActivity {
    private ChipNavigationBar chipNavigationBar;
    private Fragment fragment=null;
    private ImageButton  h_comedy, h_family, h_romance, h_animation, h_horror, h_action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        chipNavigationBar=findViewById(R.id.chipNavigation);

        chipNavigationBar.setItemSelected(R.id.home,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.home:
                        fragment=new HomeFragment();
                        break;
                    case R.id.comu:
                        fragment= new comuFragment();
                        break;
                    case R.id.us:
                        fragment=new usFragment();
                        break;
                }

                if (fragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                }
            }
        });



        h_comedy = findViewById(R.id.h_comedy);
        h_family = findViewById(R.id.h_family);
        h_romance = findViewById(R.id.h_romance);
        h_animation = findViewById(R.id.h_animation);
        h_horror = findViewById(R.id.h_horror);
        h_action = findViewById(R.id.h_action);

        h_comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HomeComedy.class));

            }
        });
        h_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HomeFamily.class));

            }
        });
        h_romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HomeRomance.class));

            }
        });
        h_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HomeAnimation.class));

            }
        });
        h_horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HomeHorror.class));

            }
        });
        h_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HomeAction.class));

            }
        });








    }

}