package test.message.com.messagetestdemo.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyViewPager extends ViewPager {

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();

	}
	public boolean isCanScroll=true;
	private float firstX;
	private float moveX;
	
	 public void setCanScroll(boolean isCanScroll){  
	        this.isCanScroll = isCanScroll;  
	    }  
	  
	  
	  /*  @Override
    public void scrollTo(int x, int y){  
	        if (isCanScroll){  
	            super.scrollTo(x, y);  
	        }  
	    }*/
	    private boolean isFirst=true;
   @Override
public boolean onTouchEvent(MotionEvent event) {
	   
		/*   if (!(getCurrentItem()==0)) {
				return super.onTouchEvent(event);
			} else {
				return false;
			}*/
	   switch (event.getAction()){
		   case MotionEvent.ACTION_MOVE:
			   break;
	   }
	 super.onTouchEvent(event);
	   return  true;

  }
   public View canMoveView;
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		//如果是触摸到比价曲线位置 直接调外层的ontouch
		if(canMoveView!=null&&isOnTouchView(canMoveView,arg0)){
			onTouchEvent(arg0);
		}
		boolean onIntercept = false;
		try {
			onIntercept = super.onInterceptTouchEvent(arg0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return onIntercept;

	}
	public boolean isOnTouchView(View v, MotionEvent event) {
		if (v != null) {
			int[] leftTop = {0, 0};
			//获取输入框当前的location位置
			v.getLocationInWindow (leftTop);
			int left = leftTop[0];
			int top = leftTop[1];
			int bottom = top + v.getHeight ();
			int right = left + v.getWidth ();
			if (event.getX () > left && event.getX () < right && event.getY () > top && event.getY () < bottom) {
				// 点击的是输入框区域，保留点击EditText的事件
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
