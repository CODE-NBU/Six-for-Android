package eu.veldsoft.six;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import eu.veldsoft.six.model.Board;

/**
 * {@inheritDoc}
 */
public class MainActivity extends Activity {

    /**
     * The identifier for players list activity.
     */
    private static final int LAUNCH_PLAYERS_LIST_ACTIVITY = 1;
    
    /**
     * The object for communication with the OOP model.
     */
    private Board board = new Board();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	//TODO Move title bar in redraw helping function.        
        String title = "";
        title += getString(R.string.app_name);
        setTitle(title);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.new_game) {
        	startActivityForResult(new Intent(MainActivity.this, PlayersActivity.class), LAUNCH_PLAYERS_LIST_ACTIVITY);
        }
        
        if (item.getItemId() == R.id.help) {
        	startActivity(new Intent(MainActivity.this, HelpActivity.class));
        }
        
        if (item.getItemId() == R.id.about) {
        	startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }
        
        if (item.getItemId() == R.id.exit) {
        	finish();
        }

    	return true;
    }
}

