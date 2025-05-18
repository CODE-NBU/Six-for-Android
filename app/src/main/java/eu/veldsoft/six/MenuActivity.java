package eu.veldsoft.six;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        /*
         * Application exit.
         */
        findViewById(R.id.exit_game).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finishAndRemoveTask();
                    }
                }
        );
        
        /*
         * Open new game screen.
         */
        findViewById(R.id.single_game).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MenuActivity.this, MainActivity.class));
                    }
                }
        );
    }
}
