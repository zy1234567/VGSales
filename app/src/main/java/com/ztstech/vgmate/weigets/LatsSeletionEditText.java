package com.ztstech.vgmate.weigets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

/**
 * Created by BugMonkey on 2017/10/19.
 */

@SuppressLint("AppCompatCustomView")
public class LatsSeletionEditText extends android.widget.EditText implements View.OnKeyListener {
    /**
     * 开始部分内容
     */
    private String mStrString="";
    /**
     * 是否有开头的文字
     */
    private boolean mSetStart=false;
    /**
     * 开头的文字
     */
    private SpannableString mForegroundColorSpan;

    public LatsSeletionEditText(Context context) {
        super(context);
    }

    public LatsSeletionEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LatsSeletionEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {

        super.onSelectionChanged(selStart, selEnd);
        if(mSetStart){
            if(this!=null&&getText()!=null&&getSelectionEnd()<=mStrString.length()){
                //保证光标始终在最后面
                if (selStart == selEnd) {//防止不能多选
                    setSelection(getText().length());
                }
            }


        }

    }

    /**
     * 每次都需要
     * @param isHasStringAndColor
     */
    public void setHasStringAndColor(boolean isHasStringAndColor){
        this.mSetStart=isHasStringAndColor;
        setText("");
    }
    /**
     * 设置edittext开始部分不可删除的文字和颜色
     * @param str
     */
     public  void setStrStringAndColor(String str, int strColor, int endColor){
         if(mSetStart){
             mStrString=str;
             String[] strs = new String[]{mStrString, ""};
             int[] colors = new int[]{strColor, endColor};
             setSpanninable(strs, colors);
             setOnKeyListener(this);
         }

     }


    /**
     * 获得输入框的内容
     * @return
     */
     public String getActText(){
         if(mSetStart){
            return this.getText().toString().replace(mStrString,"");
         }else {
             return this.getText().toString();
         }
     }

    /**
     * 设置文字
     * @param actText
     */
     public void setActText(String actText){
         if(mSetStart){
              setText(mForegroundColorSpan+actText);
         }else {
             setText(actText);
         }
         setSelection(getText().length());

     }

    /**
     * 设置颜色
     * @param texts
     * @param colors
     */
     private void setSpanninable(String[] texts, int[] colors){
         StringBuilder totalText = new StringBuilder();
         for (int i = 0; i < texts.length; i++) {
             if (texts[i] == null) {
                 continue;
             }
             totalText.append(texts[i]);
         }
         SpannableString builder = new SpannableString(totalText.toString());
         int currentIndex = 0;
         for (int i = 0; i < texts.length; i++) {
             if (texts[i] == null) {
                 continue;
             }
             ForegroundColorSpan span = new ForegroundColorSpan(colors[i]);
             builder.setSpan(span, currentIndex, currentIndex + texts[i].length(),
                     Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
             currentIndex = currentIndex + texts[i].length();
         }
         this.mForegroundColorSpan=builder;
         setText(mForegroundColorSpan);
         setSelection(getText().length());
     }

    /**
     * 是否有开始部分
     * @return
     */
     public boolean IsHasStart(){
        return mSetStart;
    }



    /**
     * Called when a hardware key is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     * <p>Key presses in software keyboards will generally NOT trigger this method,
     * although some may elect to do so in some situations. Do not assume a
     * software input method has to be key-based; even if it is, it may use key presses
     * in a different way than you expect, so there is no way to reliably catch soft
     * input key presses.
     *
     * @param v       The view the key has been dispatched to.
     * @param keyCode The code for the physical key that was pressed
     * @param event   The KeyEvent object containing full information about
     *                the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (!mSetStart) {
                //  return false;
            } else {
                if(getText().length()<=mStrString.length()&&getSelectionEnd()<mStrString.length()){
                    setText(mForegroundColorSpan);
                    return true;
                }else {

                }

//               return true;////true以后既不会继续传

            }
        }
        return false;
    }
}
