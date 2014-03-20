package mardaso.sensor.fir.sensor.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_1{

public static void LS_800x480_1(java.util.HashMap<String, anywheresoftware.b4a.objects.ViewWrapper<?>> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="ImageView1.Left = 12.5%x"[1/800x480,scale=1]
views.get("imageview1").setLeft((int)((12.5d / 100 * width)));
//BA.debugLineNum = 3;BA.debugLine="ImageView1.Top = 12dip"[1/800x480,scale=1]
views.get("imageview1").setTop((int)((12d * scale)));
//BA.debugLineNum = 4;BA.debugLine="ImageView1.Width = 75%x"[1/800x480,scale=1]
views.get("imageview1").setWidth((int)((75d / 100 * width)));
//BA.debugLineNum = 5;BA.debugLine="ImageView1.Height = 80%y"[1/800x480,scale=1]
views.get("imageview1").setHeight((int)((80d / 100 * height)));
//BA.debugLineNum = 6;BA.debugLine="BtnAlpha.Right = 100%x"[1/800x480,scale=1]
views.get("btnalpha").setLeft((int)((100d / 100 * width) - (views.get("btnalpha").getWidth())));
//BA.debugLineNum = 7;BA.debugLine="BPalette.Right = 100%x"[1/800x480,scale=1]
views.get("bpalette").setLeft((int)((100d / 100 * width) - (views.get("bpalette").getWidth())));
//BA.debugLineNum = 8;BA.debugLine="Autoscale2.Right = 100%x"[1/800x480,scale=1]
views.get("autoscale2").setLeft((int)((100d / 100 * width) - (views.get("autoscale2").getWidth())));
//BA.debugLineNum = 9;BA.debugLine="btnMaxPlus.Right = 100%x"[1/800x480,scale=1]
views.get("btnmaxplus").setLeft((int)((100d / 100 * width) - (views.get("btnmaxplus").getWidth())));
//BA.debugLineNum = 10;BA.debugLine="btnMaxMinus.Right = btnMaxPlus.Left -4dip"[1/800x480,scale=1]
views.get("btnmaxminus").setLeft((int)((views.get("btnmaxplus").getLeft())-(4d * scale) - (views.get("btnmaxminus").getWidth())));
//BA.debugLineNum = 11;BA.debugLine="MaxTemp.Right = btnMaxMinus.Left -4dip"[1/800x480,scale=1]
views.get("maxtemp").setLeft((int)((views.get("btnmaxminus").getLeft())-(4d * scale) - (views.get("maxtemp").getWidth())));
//BA.debugLineNum = 12;BA.debugLine="LabAlpha.Right = BtnAlpha.Left -4dip"[1/800x480,scale=1]
views.get("labalpha").setLeft((int)((views.get("btnalpha").getLeft())-(4d * scale) - (views.get("labalpha").getWidth())));
//BA.debugLineNum = 13;BA.debugLine="btnMaxPlus.Bottom = 100%y"[1/800x480,scale=1]
views.get("btnmaxplus").setTop((int)((100d / 100 * height) - (views.get("btnmaxplus").getHeight())));
//BA.debugLineNum = 14;BA.debugLine="btnMaxMinus.Bottom = 100%y"[1/800x480,scale=1]
views.get("btnmaxminus").setTop((int)((100d / 100 * height) - (views.get("btnmaxminus").getHeight())));
//BA.debugLineNum = 15;BA.debugLine="btnMinPlus.Bottom = 100%y"[1/800x480,scale=1]
views.get("btnminplus").setTop((int)((100d / 100 * height) - (views.get("btnminplus").getHeight())));
//BA.debugLineNum = 16;BA.debugLine="btnMinMinus.Bottom = 100%y"[1/800x480,scale=1]
views.get("btnminminus").setTop((int)((100d / 100 * height) - (views.get("btnminminus").getHeight())));
//BA.debugLineNum = 17;BA.debugLine="MaxTemp.Bottom = 100%y"[1/800x480,scale=1]
views.get("maxtemp").setTop((int)((100d / 100 * height) - (views.get("maxtemp").getHeight())));
//BA.debugLineNum = 18;BA.debugLine="MinTemp.Bottom = 100%y"[1/800x480,scale=1]
views.get("mintemp").setTop((int)((100d / 100 * height) - (views.get("mintemp").getHeight())));
//BA.debugLineNum = 19;BA.debugLine="Label1.Bottom = btnMaxPlus.Top - 8"[1/800x480,scale=1]
views.get("label1").setTop((int)((views.get("btnmaxplus").getTop())-8d - (views.get("label1").getHeight())));
//BA.debugLineNum = 20;BA.debugLine="Label2.Bottom = btnMaxPlus.Top - 8"[1/800x480,scale=1]
views.get("label2").setTop((int)((views.get("btnmaxplus").getTop())-8d - (views.get("label2").getHeight())));
//BA.debugLineNum = 21;BA.debugLine="Label3.Top = 50%y - MaxTemp.Height"[1/800x480,scale=1]
views.get("label3").setTop((int)((50d / 100 * height)-(views.get("maxtemp").getHeight())));
//BA.debugLineNum = 22;BA.debugLine="Label3.Left = 50%x - Label3.Width/2"[1/800x480,scale=1]
views.get("label3").setLeft((int)((50d / 100 * width)-(views.get("label3").getWidth())/2d));
//BA.debugLineNum = 23;BA.debugLine="PalView2.Left = MinTemp.Right +5dip"[1/800x480,scale=1]
views.get("palview2").setLeft((int)((views.get("mintemp").getLeft() + views.get("mintemp").getWidth())+(5d * scale)));
//BA.debugLineNum = 24;BA.debugLine="PalView2.Width = (MaxTemp.Left - MinTemp.Right) - 10dip"[1/800x480,scale=1]
views.get("palview2").setWidth((int)(((views.get("maxtemp").getLeft())-(views.get("mintemp").getLeft() + views.get("mintemp").getWidth()))-(10d * scale)));
//BA.debugLineNum = 25;BA.debugLine="PalView2.Height = btnMaxPlus.Height"[1/800x480,scale=1]
views.get("palview2").setHeight((int)((views.get("btnmaxplus").getHeight())));
//BA.debugLineNum = 26;BA.debugLine="PalView2.Bottom = 100%y - 2dip"[1/800x480,scale=1]
views.get("palview2").setTop((int)((100d / 100 * height)-(2d * scale) - (views.get("palview2").getHeight())));
//BA.debugLineNum = 27;BA.debugLine="SpotTemp.Bottom = Label2.Bottom"[1/800x480,scale=1]
views.get("spottemp").setTop((int)((views.get("label2").getTop() + views.get("label2").getHeight()) - (views.get("spottemp").getHeight())));
//BA.debugLineNum = 28;BA.debugLine="SpotTemp.Right = BtnAlpha.Left -4dip"[1/800x480,scale=1]
views.get("spottemp").setLeft((int)((views.get("btnalpha").getLeft())-(4d * scale) - (views.get("spottemp").getWidth())));

}
public static void LS_1280x720_1(java.util.HashMap<String, anywheresoftware.b4a.objects.ViewWrapper<?>> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("imageview1").setLeft((int)((12.5d / 100 * width)));
views.get("imageview1").setTop((int)((12d * scale)));
views.get("imageview1").setWidth((int)((75d / 100 * width)));
views.get("imageview1").setHeight((int)((80d / 100 * height)));
views.get("btnalpha").setLeft((int)((100d / 100 * width) - (views.get("btnalpha").getWidth())));
views.get("bpalette").setLeft((int)((100d / 100 * width) - (views.get("bpalette").getWidth())));
views.get("autoscale2").setLeft((int)((100d / 100 * width) - (views.get("autoscale2").getWidth())));
views.get("btnmaxplus").setLeft((int)((100d / 100 * width) - (views.get("btnmaxplus").getWidth())));
views.get("btnmaxminus").setLeft((int)((views.get("btnmaxplus").getLeft())-(6d * scale) - (views.get("btnmaxminus").getWidth())));
views.get("maxtemp").setLeft((int)((views.get("btnmaxminus").getLeft())-(6d * scale) - (views.get("maxtemp").getWidth())));
views.get("labalpha").setLeft((int)((views.get("btnalpha").getLeft())-(4d * scale) - (views.get("labalpha").getWidth())));
views.get("btnmaxplus").setTop((int)((100d / 100 * height) - (views.get("btnmaxplus").getHeight())));
views.get("btnmaxminus").setTop((int)((100d / 100 * height) - (views.get("btnmaxminus").getHeight())));
views.get("label1").setTop((int)((views.get("btnmaxplus").getTop())-8d - (views.get("label1").getHeight())));
views.get("label2").setTop((int)((views.get("btnmaxplus").getTop())-8d - (views.get("label2").getHeight())));
views.get("label3").setTop((int)((50d / 100 * height)-(views.get("maxtemp").getHeight())));
views.get("label3").setLeft((int)((50d / 100 * width)-(views.get("label3").getWidth())/2d));
views.get("btnminplus").setTop((int)((100d / 100 * height) - (views.get("btnminplus").getHeight())));
views.get("btnminminus").setTop((int)((100d / 100 * height) - (views.get("btnminminus").getHeight())));
views.get("maxtemp").setTop((int)((100d / 100 * height) - (views.get("maxtemp").getHeight())));
views.get("mintemp").setTop((int)((100d / 100 * height) - (views.get("mintemp").getHeight())));
views.get("palview2").setLeft((int)((views.get("mintemp").getLeft() + views.get("mintemp").getWidth())+(5d * scale)));
views.get("palview2").setWidth((int)(((views.get("maxtemp").getLeft())-(views.get("mintemp").getLeft() + views.get("mintemp").getWidth()))-(10d * scale)));
views.get("palview2").setHeight((int)((views.get("btnmaxplus").getHeight())));
views.get("palview2").setTop((int)((100d / 100 * height)-(2d * scale) - (views.get("palview2").getHeight())));
views.get("spottemp").setTop((int)((views.get("label2").getTop() + views.get("label2").getHeight()) - (views.get("spottemp").getHeight())));
//BA.debugLineNum = 28;BA.debugLine="SpotTemp.Right = BtnAlpha.Left -6dip"[1/1280x720,scale=1]
views.get("spottemp").setLeft((int)((views.get("btnalpha").getLeft())-(6d * scale) - (views.get("spottemp").getWidth())));

}
public static void LS_general(java.util.HashMap<String, anywheresoftware.b4a.objects.ViewWrapper<?>> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3d);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}