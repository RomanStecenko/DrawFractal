package com.example.DrawFractal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

/**
 * Created by Roman on 07.05.2014.
 */
public class DrawView extends View {
    private Paint paint;
    private int parameters[];

    public DrawView(Context context, int parameters[]) {
        super(context);
        init(parameters);
    }

    private void init(int[] parameters) {
        paint = new Paint();
        this.parameters = parameters;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        firstLine(canvas);
    }

    private void firstLine(Canvas canvas) {
        int startX = parameters[0] / 2;
        int startY = parameters[1] / 2-100;
        Point firstPoint = new Point(startX, startY);
        Point firstPointEnd = new Point(startX, (int) (startY * 1.5));
        canvas.drawLine(firstPoint.x, firstPoint.y, firstPointEnd.x, firstPointEnd.y, paint);
        fractal(canvas, 0, firstPoint, halfDistance(firstPoint, firstPointEnd, true));
    }

    private void fractal(Canvas canvas, int number, Point point, int distance) {
        if (number == parameters[2]) return;
        if (distance == 0) return;
        number++;
        Point nextPoint1 = new Point();
        Point nextPoint2 = new Point();
        if (number % 2 == 1) {
            nextPoint1.x = point.x - distance;
            nextPoint1.y = point.y;
            nextPoint2.x = point.x + distance;
            nextPoint2.y = point.y;
            canvas.drawLine(nextPoint1.x, nextPoint1.y, nextPoint2.x, nextPoint2.y, paint);
            fractal(canvas, number, nextPoint1, halfDistance(point, nextPoint1, false));
            fractal(canvas, number, nextPoint2, halfDistance(point, nextPoint2, false));
        } else {
            nextPoint1.x = point.x;
            nextPoint1.y = point.y - distance;
            nextPoint2.x = point.x;
            nextPoint2.y = point.y + distance;
            canvas.drawLine(nextPoint1.x, nextPoint1.y, nextPoint2.x, nextPoint2.y, paint);
            fractal(canvas, number, nextPoint1, halfDistance(point, nextPoint1, true));
            fractal(canvas, number, nextPoint2, halfDistance(point, nextPoint2, true));
        }
    }

    private int halfDistance(Point point1, Point point2, boolean mod) {
        int distance;
        if (mod) {
            distance = Math.abs(point1.y - point2.y);
        } else {
            distance = Math.abs(point1.x - point2.x);
        }
        return distance / 2;
    }
}
