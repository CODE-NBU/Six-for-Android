package eu.veldsoft.six;

import java.util.List;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import eu.veldsoft.six.model.Board;

class HexGridView extends View {

	private Paint hexPaint;
	private Paint selectedPaint;
	private Paint gridPaint;

	private int rows = 5;
	private int cols = 5;
	private float hexSize = 60f;

	private int selectedQ = -1;
	private int selectedR = -1;

	public HexGridView(Context context, @Nullable AttributeSet attributes) {
		super(context, attributes);

		hexPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		hexPaint.setColor(Color.LTGRAY);
		hexPaint.setStyle(Paint.Style.FILL);

		selectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		selectedPaint.setColor(Color.YELLOW);
		selectedPaint.setStyle(Paint.Style.FILL);

		gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		gridPaint.setColor(Color.BLACK);
		gridPaint.setStyle(Paint.Style.STROKE);
		gridPaint.setStrokeWidth(2f);
	}

	private float[] hexToPixel(int q, int r) {
		float x = (float) (hexSize * Math.sqrt(3) * (q + r / 2.0));
		float y = hexSize * 3f / 2f * r;

		x += 100;
		y += 100;

		return new float[] {x, y};
	}

	private Path createHexPath(float cx, float cy) {
		Path path = new Path();

		for (int i = 0; i < 6; i++) {
			double angle = Math.PI / 180 * (60 * i - 30);

			float x = (float) (cx + hexSize * Math.cos(angle));
			float y = (float) (cy + hexSize * Math.sin(angle));

			if (i == 0) {
				path.moveTo(x, y);
			} else {
				path.lineTo(x, y);
			}
		}

		path.close();

		return path;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		for (int r = 0; r < rows; r++) {
			for (int q = 0; q < cols; q++) {

				float[] center = hexToPixel(q, r);
				Path hex = createHexPath(center[0], center[1]);

				if (q == selectedQ && r == selectedR) {
					canvas.drawPath(hex, selectedPaint);
				} else {
					canvas.drawPath(hex, hexPaint);
				}

				canvas.drawPath(hex, gridPaint);
			}
		}
	}

	private int[] hexRound(double q, double r) {
		double s = -q - r;

		int rq = (int) Math.round(q);
		int rr = (int) Math.round(r);
		int rs = (int) Math.round(s);

		double q_diff = Math.abs(rq - q);
		double r_diff = Math.abs(rr - r);
		double s_diff = Math.abs(rs - s);

		if (q_diff > r_diff && q_diff > s_diff) {
			rq = -rr - rs;
		} else if (r_diff > s_diff) {
			rr = -rq - rs;
		}

		return new int[] {rq, rr};
	}

	private int[] pixelToHex(float x, float y) {
		x -= 100;
		y -= 100;

		double q = (Math.sqrt(3)/3 * x - 1.0/3 * y) / hexSize;
		double r = (2.0/3 * y) / hexSize;

		return hexRound(q, r);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			int[] hex = pixelToHex(event.getX(), event.getY());
			selectedQ = hex[0];
			selectedR = hex[1];

			invalidate();
			return true;
		}

		return super.onTouchEvent(event);
	}
}

/**
 * The main activity of the application.
 */
public class MainActivity extends Activity {

	/**
	 * The identifier for players list activity.
	 */
	private static final int LAUNCH_PLAYERS_LIST_ACTIVITY = 1;

	/**
	 * The object for communication with the OOP model.
	 */
	private final Board board = new Board();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		HexGridView grid = findViewById(R.id.gameGrid);
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		/*
		 * Do not handle other results than OK.
		 */
		if (resultCode != RESULT_OK) {
			return;
		}

		if (requestCode == LAUNCH_PLAYERS_LIST_ACTIVITY) {
			List<String> names = new ArrayList<String>();

			names.add(data.getCharSequenceExtra("player1Name").toString());
			names.add(data.getCharSequenceExtra("player2Name").toString());

			if(board.newGame(names) == false) {
				Toast.makeText(MainActivity.this, R.string.game_not_started_text, Toast.LENGTH_LONG).show();
			} else {
			}
		}
	}
}

