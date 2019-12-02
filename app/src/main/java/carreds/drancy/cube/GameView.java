package carreds.drancy.cube;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;



import java.util.Random;

public class GameView extends View {


    private static SharedPreferences prefs;
    private int highScore = 0;
    public String sHighScore;
    private String saveScore = "Best";

    private Bitmap carre1, carre2, carre3, carre4, carrePlayer;
    private float x1, y1, x2, y2, x3, y3, x4, y4;
    private float speedX1, speedY1, speedX2, speedY2, speedX3, speedY3, speedX4, speedY4;
    private int xRect, yRect;

    private float xPlayer, yPlayer;

    private float xSpeedPlayer, ySpeedPlayer;
    private int xMax, yMax;


    private String text = "Touch to";
    private String text2 = "Start";
    private String text3 = "Game";
    private String text4 = "Over";

    private int compte = 0;
    public String compteur = "0";


    Rect rect = new Rect();
    Rect rect2 = new Rect();

    Paint blue = new Paint();
    Paint green = new Paint();

    Paint paint = new Paint();
    Paint paintScore = new Paint();
    Paint paintScore2 = new Paint();
    Paint paintText = new Paint();
    Paint somePaint = new Paint();

    public boolean start = false;
    private Boolean finish = false;
    private Boolean afficheText = true;
    private Boolean restart = false;

    Random r = new Random();






    public GameView(Context context) {
        super(context);
        init(null, 0);
        prefs = context.getSharedPreferences("the.score", context.MODE_PRIVATE);


    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
        prefs = context.getSharedPreferences("the.score", context.MODE_PRIVATE);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
        prefs = context.getSharedPreferences("the.score", context.MODE_PRIVATE);
    }

    public void init(AttributeSet attrs, int defStyleAttr) {

        String spackage = "the.score";
        carre1 = BitmapFactory.decodeResource(getResources(), R.drawable.carrer);
        carre2 = BitmapFactory.decodeResource(getResources(), R.drawable.carrer);
        carre3 = BitmapFactory.decodeResource(getResources(), R.drawable.carrer);
        carre4 = BitmapFactory.decodeResource(getResources(), R.drawable.carrer);
        carrePlayer = BitmapFactory.decodeResource(getResources(), R.drawable.carreb);

        xPlayer = 0;
        yPlayer = 0;




        this.setFocusableInTouchMode(true);



        green.setColor(Color.GREEN);
        green.setStyle(Paint.Style.STROKE);

        blue.setColor(Color.BLUE);
        blue.setStyle(Paint.Style.STROKE);

        paintText.setColor(Color.RED);

        somePaint.setColor(Color.BLUE);
        somePaint.setStyle(Paint.Style.FILL_AND_STROKE);


        paintScore.setColor(Color.BLUE);
        paintScore.setStyle(Paint.Style.FILL_AND_STROKE);

        paintScore2.setColor(Color.BLUE);
        paintScore2.setStyle(Paint.Style.FILL_AND_STROKE);

    }







    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);




        somePaint.setTextSize(xMax / 9);
        paintText.setTextSize(xMax / 6);
        paintScore.setTextSize(xMax / 17);
        paintScore2.setTextSize(xMax / 16);

        highScore = prefs.getInt(saveScore, 0);
        sHighScore = Integer.toString(highScore);

        if(compte > highScore){
            highScore = compte;
            sHighScore = Integer.toString(highScore);
            prefs.edit().putInt(saveScore, highScore).commit();

        }



        xRect = getWidth();
        yRect = getHeight();


        if (xPlayer == 0 && yPlayer == 0) {

            xPlayer = (xRect / 2) - (carrePlayer.getWidth() / 2);
            yPlayer = (yRect / 2) - (carrePlayer.getHeight() / 2);
            x1 = xMax / 14;
            y1 = yMax / 7;
            x2 = (xMax - (xMax / 14)) - carre2.getWidth();
            y2 = yMax / 7;
            x3 = xMax / 14;
            y3 = (yMax - (xMax / 5) - carre3.getHeight());
            x4 = (xMax - (xMax / 14)) - carre4.getWidth();
            y4 = (yMax - (xMax / 5) - carre4.getHeight());

            speedX1 = (xMax / 105) + r.nextInt((xMax / 70)  - (xMax / 105));
            speedX2 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));
            speedX3 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));
            speedX4 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));
            speedY1 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));
            speedY2 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));
            speedY3 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));
            speedY4 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));


        }


        rect.set(xRect / 14, yRect / 7, xRect - (xRect / 14), yRect - (xRect / 5));
        rect2.set(0, 0, xMax + 5, yMax / 11);

        canvas.drawRect(rect, green);
        canvas.drawRect(rect2, blue);


        canvas.drawBitmap(carrePlayer, xPlayer, yPlayer, paint);
        canvas.drawBitmap(carre1, x1, y1, paint);
        canvas.drawBitmap(carre2, x2, y2, paint);
        canvas.drawBitmap(carre3, x3, y3, paint);
        canvas.drawBitmap(carre4, x4, y4, paint);


        canvas.drawText(compteur, xMax / 15 , yMax / 14, somePaint);

        canvas.drawText(saveScore, xRect - (xRect / 5) , yRect / 21, paintScore);
        canvas.drawText(sHighScore, xRect - (xRect / 5) , yRect / 12, paintScore2);

        if (afficheText == true) {
            canvas.drawText(text, xMax / 5, yPlayer - (xMax / 18), paintText);
            canvas.drawText(text2, xMax / 3, yPlayer + carrePlayer.getHeight() + (xMax / 8), paintText);

        }

            if (finish == true && restart == false) {
                canvas.drawText(text3, (xMax / 4) + (xMax / 20), (yMax / 2) + (xMax / 100), paintText);
                canvas.drawText(text4, (xMax / 3) + (xMax / 50), (yMax / 2) + (xMax / 6), paintText);

        }

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {

        }

        if (restart == true) {
            restart();
            finish = false;

        }



        moveSquare();
        invalidate();



    }





    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        float currentY = event.getY();
        float deltaX, deltaY;
        float scalingFactor = 600.0f / ((yMax < xMax) ? yMax : xMax);


        switch (event.getAction()) {


            case MotionEvent.ACTION_MOVE:
                // Modify rotational angles according to movement

                if (finish == false && start == true) {


                    deltaX = currentX - (xPlayer + (carrePlayer.getWidth() / 2));
                    deltaY = currentY - (yPlayer + (carrePlayer.getWidth() / 2));
                    xPlayer += deltaX * scalingFactor;
                    yPlayer += deltaY * scalingFactor;


                    // Save current x, y
                    xSpeedPlayer = currentX;
                    ySpeedPlayer = currentY;



                }
                break;


            case MotionEvent.ACTION_DOWN:

                if (finish == false) {
                    if (currentX >= xPlayer && currentX <= xPlayer + carrePlayer.getWidth() && currentY >= yPlayer && currentY <= yPlayer + carrePlayer.getHeight()) {
                        start = true;



                    }
                }


                if (finish == true) {
                    if (currentX >= xRect / 14 && currentX <= xRect - (xRect / 14) && currentY >= yRect / 7 && currentY <= yRect - (xRect / 5)) {

                        restart = true;
                        afficheText = true;

                        compte = 0;
                        compteur = Integer.toString(compte);

                    }
                }
                break;
        }


        return true;

        // / Event handled
    }




    public void onSizeChanged(int w, int h, int oldW, int oldH) {

        xMax = w - 1;
        yMax = h - 1;
    }

    public void restart() {



        speedX1 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));
        speedX2 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));
        speedX3 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));
        speedX4 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));
        speedY1 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));
        speedY2 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));
        speedY3 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));
        speedY4 = (xMax / 105) + r.nextInt((xMax / 70) - (xMax / 105));


        xPlayer = (xRect / 2) - (carrePlayer.getWidth() / 2);
        yPlayer = (yRect / 2) - (carrePlayer.getHeight() / 2);
        x1 = xMax / 14;
        y1 = yMax / 7;
        x2 = (xMax - (xMax / 14)) - carre2.getWidth();
        y2 = yMax / 7;
        x3 = xMax / 14;
        y3 = (yMax - (xMax / 5) - carre3.getHeight());
        x4 = (xMax - (xMax / 14)) - carre4.getWidth();
        y4 = (yMax - (xMax / 5) - carre4.getHeight());


        restart = false;


    }




    public void moveSquare() {


        if (start == true) {
            x1 = x1 + speedX1;
            y1 = y1 + speedY1;
            x2 = x2 - speedX2;
            y2 = y2 + speedY2;
            x3 = x3 + speedX3;
            y3 = y3 - speedY3;
            x4 = x4 - speedX4;
            y4 = y4 - speedY4;
            afficheText = false;



            if (x1 <= (xMax / 14)) {
                speedX1 = -speedX1;
                compte ++;
                compteur = Integer.toString(compte);
            }
            if (y1 <= (yMax / 7)) {
                speedY1 = -speedY1;
                compte++;
                compteur = Integer.toString(compte);
            }

            if (x1 >= (xMax - (xMax / 14) - carre1.getWidth())) {
                speedX1 = -speedX1;
                compte++;
                compteur = Integer.toString(compte);
            }

            if (y1 >= (yMax - (xMax / 5) - carre1.getHeight())) {
                speedY1 = -speedY1;
                compte++;
                compteur = Integer.toString(compte);
            }

            if (x2 <= (xMax / 14)) {
                speedX2 = -speedX2;
                compte++;
                compteur = Integer.toString(compte);
            }

            if (y2 <= (yMax / 7)) {
                speedY2 = -speedY2;
                compte++;
                compteur = Integer.toString(compte);
            }

            if (x2 >= (xMax - (xMax / 14) - carre2.getWidth())) {
                speedX2 = -speedX2;
                compte++;
                compteur = Integer.toString(compte);
            }

            if (y2 >= (yMax - (xMax / 5) - carre1.getHeight())) {
                speedY2 = -speedY2;
                compte++;
                compteur = Integer.toString(compte);
            }

            if (x3 <= (xMax / 14)) {
                speedX3 = -speedX3;
                compte++;
                compteur = Integer.toString(compte);
            }
            if (y3 <= (yMax / 7)) {
                speedY3 = -speedY3;
                compte++;
                compteur = Integer.toString(compte);
            }

            if (x3 >= (xMax - (xMax / 14) - carre3.getWidth())) {
                speedX3 = -speedX3;
                compte++;
                compteur = Integer.toString(compte);
            }

            if (y3 >= (yMax - (xMax / 5) - carre3.getHeight())) {
                speedY3 = -speedY3;
                compte++;
                compteur = Integer.toString(compte);
            }

            if (x4 <= (xMax / 14)) {
                speedX4 = -speedX4;
                compte++;
                compteur = Integer.toString(compte);
            }

            if (y4 <= (yMax / 7)) {
                speedY4 = -speedY4;
                compte++;
                compteur = Integer.toString(compte);
            }

            if (x4 >= (xMax - (xMax / 14) - carre4.getWidth())) {
                speedX4 = -speedX4;
                compte++;
                compteur = Integer.toString(compte);
            }

            if (y4 >= (yMax - (xMax / 5) - carre4.getHeight())) {
                speedY4 = -speedY4;
                compte++;
                compteur = Integer.toString(compte);
            }

            if (yPlayer < (yMax / 7) || xPlayer < (xMax / 14) || xPlayer > (xMax - (xMax / 14) - carrePlayer.getWidth()) || yPlayer > (yMax - (xMax / 5)) - carrePlayer.getHeight()) {
                finish = true;

            }

            if ((xPlayer <= x1 + carre1.getWidth() - 10)      // trop à droite
                    && (xPlayer + carrePlayer.getWidth() - 10 >= x1) // trop à gauche
                    && (yPlayer <= y1 + carre1.getHeight() - 10) // trop en bas
                    && (yPlayer + carrePlayer.getHeight() - 10 >= y1)) {
                finish = true;

            }

            if ((xPlayer <= x2 + carre2.getWidth() - 10)      // trop à droite
                    && (xPlayer + carrePlayer.getWidth() - 10 >= x2) // trop à gauche
                    && (yPlayer <= y2 + carre2.getHeight() - 10) // trop en bas
                    && (yPlayer + carrePlayer.getHeight() - 10 >= y2)) {
                finish = true;

            }

            if ((xPlayer <= x3 + carre3.getWidth() - 10)      // trop à droite
                    && (xPlayer + carrePlayer.getWidth() - 10 >= x3) // trop à gauche
                    && (yPlayer <= y3 + carre3.getHeight() - 10) // trop en bas
                    && (yPlayer + carrePlayer.getHeight() - 10 >= y3)) {
                finish = true;

            }

            if ((xPlayer <= x4 + carre4.getWidth() - 10)      // trop à droite
                    && (xPlayer + carrePlayer.getWidth() - 10 >= x4) // trop à gauche
                    && (yPlayer <= y4 + carre4.getHeight() - 10) // trop en bas
                    && (yPlayer + carrePlayer.getHeight() - 10 >= y4)) {
                finish = true;

            }


            if (finish == true) {
                speedX1 = 0;
                speedY1 = 0;
                speedX2 = 0;
                speedY2 = 0;
                speedX3 = 0;
                speedY3 = 0;
                speedX4 = 0;
                speedY4 = 0;

                start = false;


            }

        }

    }



}


