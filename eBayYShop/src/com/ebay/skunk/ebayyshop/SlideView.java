package com.ebay.skunk.ebayyshop;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * SlideView will load the layout for view_content and the holder
 *
 */
public class SlideView extends LinearLayout {

	/**
	 * Parameter Description:
	 * 1.  TAG: tag name
	 * 2.  mContext;
	 * 3.  mViewContent: stores all the view 
	 * 4.  mHolder: stores the delete button
	 * 5.  mScroller: provide the spring animation
	 * 6.  mOnSlideListener: info the super class about the slide event
	 * 7.  mHolderWidth: the width (dp) of the Holder view
	 * 8.  mLastX: slide coordinate for the last time
	 * 9.  mLastY: slide coordinate for the last time
	 * 10. TAN: control the slip angle, only when tan a = deltaX / deltaY > 2, then it could be slide
	 */
    public Context mContext;  
    public LinearLayout mViewContent;  
    public Scroller mScroller;  
    public OnSlideListener mOnSlideListener;  
    public int mHolderWidth = 120;    
    public int mLastX = 0;  
    public int mLastY = 0;  
    public static final int TAN = 2;
    
    public int down_x = 0;
    public int down_y = 0;
    
    public int up_x = 0;
    public int up_y = 0;
	
	/**
	 * pass the three slide status to the slide view
	 */
	public interface OnSlideListener {
        public static final int SLIDE_STATUS_OFF = 0;  
        public static final int SLIDE_STATUS_START_SCROLL = 1;  
        public static final int SLIDE_STATUS_ON = 2;  
       
        public void onSlide(View view, int status); 
	}    
    

	public SlideView(Context context) {
		super(context);
        initView();  
	}

	public SlideView(Context context, AttributeSet attrs) {
		super(context, attrs);
        initView();  
	}

   private void initView() {  
        mContext = getContext();
        mScroller = new Scroller(mContext);  
        setOrientation(LinearLayout.HORIZONTAL);  
        View.inflate(mContext, R.layout.slide_view_merge, this);  
        mViewContent = (LinearLayout) findViewById(R.id.view_content);  
        mHolderWidth = Math.round(TypedValue.applyDimension(  
                TypedValue.COMPLEX_UNIT_DIP, mHolderWidth, getResources()  
                        .getDisplayMetrics()));  
    }  	

   public void setContentView(View view) {  
       mViewContent.addView(view);  
   }  
 
   public void setOnSlideListener(OnSlideListener onSlideListener) {  
       mOnSlideListener = onSlideListener;  
   }     
	
   public void shrink() {  
       if (getScrollX() != 0) {  
           this.smoothScrollTo(0, 0);  
       }  
   }  
   

   public void onRequireTouchEvent(MotionEvent event) {  
       int x = (int) event.getX();  
       int y = (int) event.getY();  
       int scrollX = getScrollX();  
 
       switch (event.getAction()) {  
       case MotionEvent.ACTION_DOWN: {  
           if (!mScroller.isFinished()) {  
               mScroller.abortAnimation();  
           }  
           if (mOnSlideListener != null) {  
               mOnSlideListener.onSlide(this,  
                       OnSlideListener.SLIDE_STATUS_START_SCROLL);  
           }
           down_x = x;
           down_y = y;
           break;  
       }  
       case MotionEvent.ACTION_MOVE: {  
           int deltaX = x - mLastX;  
           int deltaY = y - mLastY;
           if (Math.abs(deltaX) < Math.abs(deltaY) * TAN) {  
               // break for not meet the slide condition 
               break;  
           }  
 
           // in case slide over border
           int newScrollX = scrollX - deltaX;  
           if (deltaX != 0) {  
               if (newScrollX < 0) {  
                   newScrollX = 0;  
               } else if (newScrollX > mHolderWidth) {  
                   newScrollX = mHolderWidth;  
               }  
               this.scrollTo(newScrollX, 0);  
           }  
           break;  
       }  
       case MotionEvent.ACTION_UP: {  
           int newScrollX = 0;  
    	   up_x = x;
    	   up_y = y;           
    	   
           // According the current scroll position, judge where the scroll should slide to
           if (scrollX - mHolderWidth * 0.75 > 0) {  
               newScrollX = mHolderWidth;  
           }
           
           // smooth scroll to the target position  
           this.smoothScrollTo(newScrollX, 0);  
           
           
           // info the slide activity  
           if (mOnSlideListener != null) {

               mOnSlideListener.onSlide(this,newScrollX == 0?OnSlideListener.SLIDE_STATUS_OFF:OnSlideListener.SLIDE_STATUS_ON);  
           }  
           
           
           break;  
       }  
       default:  
           break;  
       }  
 
       mLastX = x;  
       mLastY = y;  
   }     
   
   private void smoothScrollTo(int destX, int destY) {  
       // smooth scroll to the target position  
       int scrollX = getScrollX();  
       int delta = destX - scrollX;  
       mScroller.startScroll(scrollX, 0, delta, 0, Math.abs(delta) * 3);  
       invalidate();  
   }  
   
   @Override  
   public void computeScroll() {  
       if (mScroller.computeScrollOffset()) {  
           scrollTo(mScroller.getCurrX(), mScroller.getCurrY());  
           postInvalidate();  
       }  
   }  
   
}
