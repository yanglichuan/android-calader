
package com.example.bittt4;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

class MyCalaryView extends View {
    public int oldX = 0;
    public int oldY = 0;

    public int defaultW = 200;
    public int defaultH = 200;

    public String[] weekNames = new String[] {
            "日", "一", "二", "三", "四", "五", "六"
    };

    public MyCalaryView(Context context) {
        super(context);
    }

    public MyCalaryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 设置显示的月份
    public void setShowMonth(int year, int month) {
        mYear = year;
        mMonth = month;
        invalidate();
    }

    // 设置显示的月份
    public void setShowMonth(Calendar calendar) {
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH) +1;
        invalidate();
    }

    public Context context;
    public Paint paint;

    public int textsize1 = 10;
    public int textsize2 = 10;
    public int weekTitleSize1 = 50;
    public int weekTitleSize2 = 50;
    public MyCalaryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC));
        paint.setTextSize(39);

        //
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyCalaryView);
        WeekTitleH = array.getDimensionPixelSize(R.styleable.MyCalaryView_week_title_h, 50);
        DayH = array.getDimensionPixelSize(R.styleable.MyCalaryView_day_h, 50);
        
//        textsize1 = array.getDimensionPixelSize(R.styleable.MyCalaryView_textsize1, 50);
//        textsize2 = array.getDimensionPixelSize(R.styleable.MyCalaryView_textsize2, 50);
        weekTitleSize1 = array.getDimensionPixelSize(R.styleable.MyCalaryView_weekTitleSize1, 50);
        weekTitleSize2 = array.getDimensionPixelSize(R.styleable.MyCalaryView_weekTitleSize2, 50);
        array.recycle();

        // 先创建出来
        for (int i = 0; i < 31; i++) {
            balls.add(new DayBall());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) defaultW + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by
                // measureSpec
                result = Math.min(result, specSize);
            }
        }
        screenW = result;
        perW = screenW / weekNames.length;
        return result;
    }

    public int screenH = 0;
    public int screenW = 0;
    public int perW = 0;

    /**
     * Determines the height of this view
     * 
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = defaultH + getPaddingTop()
                    + getPaddingBottom();
            Log.i("bbb", "sdfsdf::" + result);
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by
                // measureSpec
                result = Math.min(result, specSize);
            }
        }

        screenH = result;
        return result;
    }

    public int WeekTitleH = 0;
    public int firstWeekTitleX = 40;
    public int firstDayX = 60;
    public int DayH = 0;

    public int weekTitleColor = Color.CYAN;
    public int dayaSizeColor = Color.BLUE;

    public int currentDateTitle = 100;// todo
    public int mYear = 2015;
    public int mMonth = 1;
    public int mCurrentYear = CaladerUtil.getCurrentData_array_int()[0];
    public int mCurrentMonth = CaladerUtil.getCurrentData_array_int()[1];
    public int mCurrentDay = CaladerUtil.getCurrentData_array_int()[2];

    public int drawWeekTitleY_1() {
        return WeekTitleH * 2 / 6;
    }

    public int drawWeekTitleY_2() {
        return WeekTitleH * 5 / 6;
    }

    // index
    public int drawPerDayY(int index) {
        return DayH * 4 / 6 + index * DayH;
    }
    
    public void setTitleFontSize1(Paint paint, int height){
        paint.setTextSize(1);
        boolean bShow =true;
        while(bShow){
            int t = getTypeFaceHeight(paint);
            if(t < height/4){
                paint.setTextSize(paint.getTextSize()+1);
            }else{
                break;
            }
        }
    }
    
    public void setTitleFontSize2(Paint paint, int height){
        paint.setTextSize(1);
        boolean bShow =true;
        while(bShow){
            int t = getTypeFaceHeight(paint);
            if(t < height/4){
                paint.setTextSize(paint.getTextSize()+1);
            }else{
                break;
            }
        }
    }
    
    // 得到文字高度
    public int getTypeFaceHeight(Paint paint) {
        FontMetrics sF = paint.getFontMetrics();
        // int textH = (int) Math.ceil(sF.descent - sF.top) + 2;
        int textH = (int) (Math.abs(2 * sF.ascent - sF.top)) + 5;
        return textH;
    }
    
    

    @Override
    protected void onDraw(Canvas canvas) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        canvas.drawColor(Color.CYAN);

        // 开始设置头部的距离
        canvas.save();
        paint.setColor(Color.GRAY);
        paint.setStyle(Style.FILL);
        RectF r = new RectF(0, 0, measuredWidth, WeekTitleH);
        canvas.drawRect(r, paint);
        canvas.restore();

        // 开始写星期文字
        paint.setColor(weekTitleColor);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(0);
        paint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
        //paint.setTextSize(weekTitleSize1);
        setTitleFontSize1(paint, WeekTitleH);
        int tempWW = (int) paint.measureText(mYear + "年" + mMonth + "月");
        canvas.drawText(mYear + "年" + mMonth + "月", screenW / 2 - tempWW / 2, drawWeekTitleY_1(),
                paint);
        //paint.setTextSize(weekTitleSize2);
        setTitleFontSize1(paint, WeekTitleH);
        for (int i = 0; i < weekNames.length; i++) {
            canvas.drawText(weekNames[i], i * perW + firstWeekTitleX, drawWeekTitleY_2(), paint);
        }

        canvas.save();
        // canvas.translate(0, WeekTitleH);
        // 开始设置星期
        paint.setColor(dayaSizeColor);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(0);
        paint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
        paint.setTextSize(textsize1);
        int monthDays = CaladerUtil.getDays(mYear, mMonth);
        int j = 0;// 表示行
        int z = CaladerUtil.getweekdayOnFirstDayOfMonth_int(mYear, mMonth) - 1;
        for (int i = 0; i < monthDays; i++) {
            if ((z * perW + firstWeekTitleX) > screenW) {
                j++;
                z = 0;
            }
            DayBall ball = balls.get(i);
            ball.ballTextColor = dayaSizeColor;
            ball.ball_daymark = "" + (i + 1);
            ball.representDatas = new int[] {
                    mYear, mMonth, (i + 1)
            };
            ball.ball_r = getBallRToSet();
            ball.ball_rXbbbb = z * perW + firstWeekTitleX + ball.ball_r / 2;
            ball.ball_rYbbbb = drawPerDayY(j) + WeekTitleH;
            if(ball.bPressed){
                paint.setColor(Color.RED);
            }else{
                paint.setColor(ball.getBallColor());
            }

            // 画圆形
            paint.setStyle(Style.FILL);
            //是否是今天
            if(ball.isCururentToday()){
                canvas.drawCircle(ball.ball_rXbbbb, ball.ball_rYbbbb, ball.ball_r+15, paint);
            }else{
                canvas.drawCircle(ball.ball_rXbbbb, ball.ball_rYbbbb, ball.ball_r, paint);
            }
            
            // 画几号
            ball.setFontSize1(paint);
            paint.setStyle(Style.STROKE);
            paint.setColor(ball.ballTextColor);
            canvas.drawText(ball.ball_daymark, ball.getText_rXY(paint, ball.ball_daymark)[0],
                    ball.getText_rXY(paint, ball.ball_daymark)[1], paint);

            ball.setFontSize2(paint);
            paint.setColor(Color.WHITE);
            canvas.drawText(ball.getNoingliDate(), ball.getText_rXY(paint, ball.nongli)[0],
                    ball.getText_rXY(paint, ball.nongli)[1] +
                            getTypeFaceHeight(paint), paint);
            z++;
        }
        canvas.restore();
    }

    public void getCenterFromDay(Paint paint, String str) {

    }

    public int getBallRToSet() {
        return perW * 2 / 5;
    }

    public ArrayList<DayBall> balls = new ArrayList<MyCalaryView.DayBall>();

    public class DayBall {
        public boolean bPressed = false;
        public int ballColor = Color.GRAY;
        public int ballTextColor = dayaSizeColor;
        public String ball_daymark;
        public int ball_dayInt;
        public int ballTextX = 0;
        public int ballTextY = 0;
        public int ball_rXbbbb = 0;
        public int ball_rYbbbb = 0;

        public int ball_r = 0;
        public boolean bToday = false;
        public int[] representDatas;
        public String nongli = "未知";
        
        
        // 几号
        public void setFontSize1(Paint paint){
            paint.setTextSize(1);
            boolean bShow =true;
            while(bShow){
                int t = getTypeFaceHeight(paint);
                if(t < ball_r){
                    paint.setTextSize(paint.getTextSize()+1);
                }else{
                    break;
                }
            }
        }
        
        public void setFontSize2(Paint paint){
            paint.setTextSize(1);
            boolean bShow =true;
            while(bShow){
                int t = getTypeFaceHeight(paint);
                if(t < ball_r/2){
                    paint.setTextSize(paint.getTextSize()+1);
                }else{
                    break;
                }
            }
        }
        
        public int getBallColor(){
            // 判断是否是今天
            if (this.isCururentToday()) {
                ballColor = Color.RED;
            }else{
                // 判断是否是周末
                if (this.isZhouMo()) {
                    ballColor = Color.GREEN;
                }else{
                    ballColor = Color.GRAY;
                }
            }
            return ballColor;
        }

        public String getNoingliDate() {
            Calendar today = Calendar.getInstance();
            try {
                today.setTime(Lunar.chineseDateFormat.parse(mYear + "年" + mMonth + "月"
                        + ball_daymark + "日"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Lunar lunar = new Lunar(today);
            return lunar.getNongLiData();
        }

        // 判断是否是周末
        public boolean isZhouMo() {
            String specificWeekday = CaladerUtil.getSpecificWeekday(representDatas[0],
                    representDatas[1], representDatas[2]);
            if (Integer.valueOf(specificWeekday) == 1 || Integer.valueOf(specificWeekday) == 7) {
                return true;
            }
            return false;
        }

        // 判断是否是今天
        public boolean isCururentToday() {
            for (int i = 0; i < 3; i++) {
                if (representDatas[i] != CaladerUtil.getCurrentData_array_int()[i]) {
                    return false;
                }
            }
            return true;
        }

        // 得到文字的坐标
        public int[] getText_rXY(Paint paint, String content) {
            FontMetrics sF = paint.getFontMetrics();
            int textH = (int) Math.ceil(sF.descent - sF.top) + 2;
            int textW = (int) paint.measureText(content);
            ballTextX = ball_rXbbbb - textW / 2;
            ballTextY = ball_rYbbbb;
            return new int[] {
                    ballTextX, ballTextY
            };
        }

        // 得到包裹矩形
        public Rect getRect() {
            return new Rect(ball_rXbbbb - ball_r, ball_rYbbbb - ball_r, ball_rXbbbb + ball_r,
                    ball_rYbbbb + ball_r);
        }

        // 判断是否被touch了
        public boolean beTouched(MotionEvent ev) {
            int x = (int) ev.getX();
            int y = (int) ev.getY();
            Log.i("ppp", "x=" + x + " y=" + y);
            Log.i("ppp", (ball_rXbbbb - ball_r) + "    " + (ball_rYbbbb - ball_r) + "   "
                    + (ball_rXbbbb + ball_r) + "     " + (ball_rYbbbb + ball_r));
            if (getRect().contains(x, y)) {
                Log.i("ppp", "被点击了");
                return true;
            } else {
                return false;
            }
        }
    }

    public DayBall pressBall = null;
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                oldX = (int) event.getX();
//                oldY = (int) event.getY();
//                pressBall = null;
//                for (int i = 0; i < CaladerUtil.getDays(mYear, mMonth); i++) {
//                    if (balls.get(i).beTouched(event)){
//                        pressBall = balls.get(i);
//                        pressBall.bPressed = true;
//                        break;
//                    }
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int x = (int) event.getX();
//                int y = (int) event.getY();
//                int dx = x - oldX;
//                int dy = y - oldY;
//
//                oldX = x;
//                oldY = y;
//                break;
//            case MotionEvent.ACTION_UP:
//                if (pressBall != null) {
//                    pressBall.bPressed = false;
//                }
//                break;
//            default:
//                break;
//        }
//
//        invalidate();
//        return true;
//    }

}
