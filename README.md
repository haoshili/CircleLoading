# CircleLoading
---- 
一款仿网易邮箱下拉刷新的动画，分析一下，其实还是蛮简单的。 
1. 先绘制扇叶
2. 让扇叶旋转起来
3. 改变扇叶半径大小
4. 改变画笔粗细
效果图：
![][image-1]

### 1.  绘制扇叶  
 
	 for (int i = 0; i < count; i++) {
	            setPaintColour(i);
	            canvas.drawArc(getReact(), routeDegree + i * (360 / count),  getDrawDegree(), false, paint);
	        }

### 2.扇叶旋转
原理就是不停的调用view的`invalidate()`方法
### 3.改变扇叶半径大小
因为绘制扇叶的弧线的的第一个参数就是`RectF`，而这个参数也是改变弧线半径的决定参数，所以只要这个`RectF`就可以了。
	private RectF getReact() {
	        rectF = new RectF();
	        length = width > height ? height : width;
	        rectF.set(Math.abs(length - width) / 2 + getPaintTrueWidth(),
	                Math.abs(length - height) / 2 + getPaintTrueWidth(),
	                Math.abs(length - width) / 2 + length - getPaintTrueWidth(),
	                Math.abs(length - height) / 2 + length - getPaintTrueWidth());
	        return rectF;
	    }
	  private float getPaintTrueWidth() {
	        //获取圆弧矩形的偏移距离
	        return (float) (getRouteNumber() * length / 2 * distances + paintWith);
	    }
### 4.改变画笔宽度
	     /**
	     * 获取画笔的宽度
	     *
	     * @return
	     */
	    private float getPaintWidth() {
	        //画笔的初始大小为30，变化范围为15
	        return (float) (getRouteNumber() * 60 + 30);
	    }
	
	    /**
	     * 获取旋转系数
	     * <p>
	     * 该系数为0到1的sin值
	     *
	     * @return
	     */
	    private float getRouteNumber() {
	        return (float) ((1.0 + Math.sin(Math.PI * routeDegree / 180))) / 2;
	    }
### 5. 使用方法
在xml中  
 
	<com.message.test.progresscircel.activityview.view.ProgressCircelView
	        android:id="@+id/loading"
	        android:layout_marginTop="30dip"
	        android:layout_width="200dip"
	        android:layout_height="200dip" />
	
	
	    <com.message.test.progresscircel.activityview.view.ProgressCircelView
	        android:id="@+id/loading2"
	        android:layout_marginTop="30dip"
	        android:layout_width="200dip"
	        android:layout_height="200dip" />
在activity中  

	ProgressCircelView progressCircelView = (ProgressCircelView) findViewById(R.id.loading);
	        progressCircelView.beginRoute();
	
	        ProgressCircelView progressCircelView2= (ProgressCircelView) findViewById(R.id.loading2);
	        progressCircelView2.setCount(2);
	        progressCircelView2.beginRoute();

[image-1]:	https://raw.githubusercontent.com/haoshili/CircleLoading/master/CircelLoading/previewimage.gif