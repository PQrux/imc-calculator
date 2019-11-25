package com.example.imccalculator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class HistoricoView extends View implements View.OnClickListener {
    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    private String data = "00/00/0000";
    private float imc = 24.5f;
    private float caloriasManter = 120050;
    private float caloriasEmagrecer = 12000;
    private  float caloriasEngordar = 120050;

    private TextPaint dataTextPaint;
    private TextPaint imcTextPaint;
    private TextPaint caloriasTextPaint;
    private Paint iconsPaint;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    public HistoricoView(Context context) {
        super(context);
        init(null, 0);
    }

    public HistoricoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public HistoricoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        this.setOnClickListener(this);
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.HistoricoView, defStyle, 0);
        if(a.hasValue(R.styleable.HistoricoView_data)) data = a.getString(R.styleable.HistoricoView_data);
        imc = a.getFloat(R.styleable.HistoricoView_imc,imc);
        caloriasEmagrecer = a.getFloat(R.styleable.HistoricoView_caloriasEmagrecer, caloriasEmagrecer);
        caloriasManter = a.getFloat(R.styleable.HistoricoView_caloriasManter, caloriasManter);
        caloriasEngordar = a.getFloat(R.styleable.HistoricoView_caloriasEngordar, caloriasEngordar);

        dataTextPaint = new TextPaint();
        dataTextPaint.setTextSize(30);
        dataTextPaint.setColor(Color.BLACK);
        dataTextPaint.setTextAlign(Paint.Align.LEFT);

        imcTextPaint = new TextPaint();
        imcTextPaint.setTextSize(30);
        imcTextPaint.setColor(Color.BLACK);
        imcTextPaint.setTextAlign(Paint.Align.RIGHT);

        iconsPaint = new Paint();
        iconsPaint.setColor(Color.BLACK);

        caloriasTextPaint = new TextPaint();
        caloriasTextPaint.setTextSize(25);
        caloriasTextPaint.setColor(Color.BLACK);
        caloriasTextPaint.setTextAlign(Paint.Align.CENTER);

        mExampleString = a.getString(
                R.styleable.HistoricoView_exampleString);
        mExampleColor = a.getColor(
                R.styleable.HistoricoView_exampleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.HistoricoView_exampleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.HistoricoView_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.HistoricoView_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        imcTextPaint.setColor(HistoricoView.defineImcColor(imc));

        Bitmap manterPeso = BitmapFactory.decodeResource(getResources(), R.drawable.manter_peso);
        Bitmap emagrecer = BitmapFactory.decodeResource(getResources(), R.drawable.emagrecer);
        Bitmap engordar = BitmapFactory.decodeResource(getResources(), R.drawable.engordar);

        canvas.drawBitmap(manterPeso, 70, 20, iconsPaint);
        canvas.drawBitmap(emagrecer, paddingLeft + (contentWidth - emagrecer.getWidth()) / 2, 20, iconsPaint);
        canvas.drawBitmap(engordar, contentWidth-70-engordar.getWidth(), 20, iconsPaint);
        canvas.drawText(Float.toString(caloriasManter), 70+(manterPeso.getWidth()/2), 50+manterPeso.getHeight(), caloriasTextPaint);
        canvas.drawText(Float.toString(caloriasEmagrecer), paddingLeft + (contentWidth - emagrecer.getWidth()/3) / 2, 50+emagrecer.getHeight(), caloriasTextPaint);
        canvas.drawText(Float.toString(caloriasEngordar), contentWidth-70-(engordar.getWidth()/2), 50+engordar.getHeight(), caloriasTextPaint);

        canvas.drawText(data,paddingLeft+20,contentHeight-30, dataTextPaint);
        canvas.drawText(HistoricoView.formatImc(imc), contentWidth-20, contentHeight-30,imcTextPaint);

        /*
        canvas.drawText(mExampleString,
                paddingLeft + (contentWidth - mTextWidth) / 2,
                paddingTop + (contentHeight + mTextHeight) / 2,
                mTextPaint);
        */
        // Draw the example drawable on top of the text.
        //this.setBackgroundColor(Color.rgb(182, 211, 225));
        if (mExampleDrawable != null) {
            mExampleDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mExampleDrawable.draw(canvas);
        }
    }

    @Override
    public void onClick(View view){

    }
    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getExampleString() {
        return mExampleString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(String exampleString) {
        mExampleString = exampleString;
        this.invalidate();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }
    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
    public static String formatImc(float imc){
        String result = String.format("%.2f - ", imc);
        if(imc < 18.5f){
            result+="Magreza";
        }
        else if(imc < 24.9f){
            result+="Normal";
        }
        else if(imc < 29.9f){
            result+="Sobrepeso";
        }
        else if(imc < 39.9f){
            result+="Obesidade";
        }
        else{
            result+="Obesidade Grave";
        }
        return result;
    }
    public static int defineImcColor(float imc){
        int result = Color.BLACK;
        if(imc < 18.5f){
            result = Color.BLUE;
        }
        else if(imc < 24.9f){
            result = Color.GREEN;
        }
        else if(imc < 29.9f){
            result = Color.rgb(245, 209, 66);
        }
        else if(imc < 39.9f){
            result = Color.rgb(245, 126, 66);
        }
        else{
            result = Color.RED;
        }
        return result;
    }
    public void setProperties(String data,float imc, float caloriasEmagrecer, float caloriasEngordar, float caloriasManter){
        this.data = data;
        this.imc = imc;
        this.caloriasEmagrecer = caloriasEmagrecer;
        this.caloriasEngordar = caloriasEngordar;
        this.caloriasManter = caloriasManter;
        invalidate();
    }
}
