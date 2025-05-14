package eu.veldsoft.six;

import android.app.Activity;
import android.os.Bundle;

import eu.veldsoft.six.model.Board;

/**
 * {@inheritDoc}
 */
public class MainActivity extends Activity {
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
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    }
}
