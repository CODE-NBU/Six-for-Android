package eu.veldsoft.six;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * {@inheritDoc}
 */
public class PlayersActivity extends Activity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        
        ((Button) findViewById(R.id.playersDone))
        	.setOnClickListener(new View.OnClickListener() {
        		@Override
                	public void onClick(View view) {
                		SharedPreferences.Editor editor = getSharedPreferences("eu.veldsoft.six.PlayersActivity", Context.MODE_PRIVATE).edit();
                		

                        editor.putString("player1Name", ((EditText) findViewById(R.id.textNamePlayer1)).getText().toString());
                        editor.putBoolean("player1Computer", ((CheckBox) findViewById(R.id.computerPlayer1)).isChecked());

                        editor.putString("player2Name", ((EditText) findViewById(R.id.textNamePlayer2)).getText().toString());
                        editor.putBoolean("player2Computer", ((CheckBox) findViewById(R.id.computerPlayer2)).isChecked());
                		
                        	editor.apply();
                        	                	
                		Intent intent = new Intent();
                		
                		intent.putExtra("player1Name", ((EditText) findViewById(R.id.textNamePlayer1)).getText().toString());
                		intent.putExtra("player2Name", ((EditText) findViewById(R.id.textNamePlayer2)).getText().toString());
                		intent.putExtra("player1Computer", ((CheckBox) findViewById(R.id.computerPlayer1)).isChecked());
                		intent.putExtra("player2Computer", ((CheckBox) findViewById(R.id.computerPlayer2)).isChecked());
                		
                		setResult(Activity.RESULT_OK, intent);
                		
                		PlayersActivity.this.finish();
                	}
        	});
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void onResume() {
        super.onResume();
        
        SharedPreferences preferences = getSharedPreferences("eu.veldsoft.six.PlayersActivity", Context.MODE_PRIVATE);
        
        ((EditText) findViewById(R.id.textNamePlayer1)).setText(preferences.getString("player1Name", ""));
        ((CheckBox) findViewById(R.id.computerPlayer1)).setChecked(preferences.getBoolean("player1Computer", false));

        ((EditText) findViewById(R.id.textNamePlayer2)).setText(preferences.getString("player2Name", ""));
        ((CheckBox) findViewById(R.id.computerPlayer2)).setChecked(preferences.getBoolean("player2Computer", false));
    }
}

