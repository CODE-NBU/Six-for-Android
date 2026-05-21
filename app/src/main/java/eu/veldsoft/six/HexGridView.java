package eu.veldsoft.six;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * The hexagon grid view for the game.
 */
public class HexGridView extends View {
	/**
	 * The paint for the hexagons representing empty cells.
	 */
	private Paint emptyPaint;

	/**
	 * The paint for the min player cells.
	 */
	private Paint minPaint;

	/**
	 * The paint for the max player cells.
	 */
	private Paint maxPaint;

	/**
	 * The paint for the hexagons representing selected cells.
	 */
	private Paint selectPaint;

	/**
	 * The paint for the grid lines.
	 */
	private Paint gridPaint;

	/**
	 * Number of rows in the grid.
	 */
	private int rows = 0;

	/**
	 * Number of columns in the grid.
	 */
	private int columns = 0;

	/**
	 * The radius of the hexagons.
	 */
	private float radius = 0f;

	/**
	 * The X offset of the grid.
	 */
	private int offsetX = 0;

	/**
	 * The Y offset of the grid.
	 */
	private int offsetY = 0;

	/**
	 * Selected cell column.
	 */
	private int selectedColumn = -1;

	/**
	 * Selected cell row.
	 */
	private int selectedRow = -1;

	public HexGridView(Context context, @Nullable AttributeSet attributes) {
		super(context, attributes);

		TypedArray array = context.getTheme().obtainStyledAttributes(
							   attributes,
							   R.styleable.HexGridView,
							   0, 0);

		int emptyColor, selectColor, gridColor, maxColor, minColor;
		try {
			rows = array.getInteger(R.styleable.HexGridView_rows, 0);
			columns = array.getInteger(R.styleable.HexGridView_columns, 0);
			radius = array.getFloat(R.styleable.HexGridView_radius, 0F);
			offsetX = array.getInteger(R.styleable.HexGridView_offset_x, 0);
			offsetY = array.getInteger(R.styleable.HexGridView_offset_y, 0);
			maxColor = array.getColor(R.styleable.HexGridView_max_color, Color.RED);
			minColor = array.getColor(R.styleable.HexGridView_min_color, Color.BLUE);
			emptyColor = array.getColor(R.styleable.HexGridView_empty_color, Color.GRAY);
			selectColor = array.getColor(R.styleable.HexGridView_select_color, Color.YELLOW);
			gridColor = array.getColor(R.styleable.HexGridView_grid_color, Color.BLACK);
		} finally {
			array.recycle();
		}

		emptyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		emptyPaint.setColor(emptyColor);
		emptyPaint.setStyle(Paint.Style.FILL);

		selectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		selectPaint.setColor(selectColor);
		selectPaint.setStyle(Paint.Style.FILL);

		minPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		minPaint.setColor(minColor);
		minPaint.setStyle(Paint.Style.FILL);

		maxPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		maxPaint.setColor(maxColor);
		maxPaint.setStyle(Paint.Style.FILL);

		gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		gridPaint.setColor(gridColor);
		gridPaint.setStyle(Paint.Style.STROKE);
		gridPaint.setStrokeWidth(2f);
	}


	public int rows() {
		return rows;
	}

	public void rows(int rows) {
		this.rows = rows;
	}

	public int columns() {
		return columns;
	}

	public void columns(int columns) {
		this.columns = columns;
	}

	public float radius() {
		return radius;
	}

	public void radius(float radius) {
		this.radius = radius;
	}

	public int offsetX() {
		return offsetX;
	}

	public void offsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int offsetY() {
		return offsetY;
	}

	public void offsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	private float[] hexToPixel(int c, int r) {
		float x = (float) (radius * Math.sqrt(3) * (c + r / 2.0));
		float y = radius * 3f / 2f * r;

		x += offsetX;
		y += offsetY;

		return new float[] {x, y};
	}

	private Path createHexPath(float cx, float cy) {
		Path path = new Path();

		for (int i = 0; i < 6; i++) {
			double angle = Math.PI / 180 * (60 * i - 30);

			float x = (float) (cx + radius * Math.cos(angle));
			float y = (float) (cy + radius * Math.sin(angle));

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
			for (int c = 0; c < columns; c++) {
				float[] center = hexToPixel(c, r);
				Path hex = createHexPath(center[0], center[1]);

				if (c == selectedColumn && r == selectedRow) {
					canvas.drawPath(hex, selectPaint);
				} else {
					canvas.drawPath(hex, emptyPaint);
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
		x -= offsetX;
		y -= offsetY;

		double q = (Math.sqrt(3) / 3 * x - 1.0 / 3 * y) / radius;
		double r = (2.0 / 3 * y) / radius;

		return hexRound(q, r);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			int[] hex = pixelToHex(event.getX(), event.getY());
			selectedColumn = hex[0];
			selectedRow = hex[1];

			invalidate();
			return true;
		}

		return super.onTouchEvent(event);
	}
}
