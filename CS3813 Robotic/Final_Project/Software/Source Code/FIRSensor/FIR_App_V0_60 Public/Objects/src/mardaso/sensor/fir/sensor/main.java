package mardaso.sensor.fir.sensor;

import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "mardaso.sensor.fir.sensor", "mardaso.sensor.fir.sensor.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
		BA.handler.postDelayed(new WaitForLayout(), 5);

	}
	private static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "mardaso.sensor.fir.sensor", "mardaso.sensor.fir.sensor.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.shellMode) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "mardaso.sensor.fir.sensor.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
		return true;
	}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
		this.setIntent(intent);
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.Serial _serial1 = null;
public static anywheresoftware.b4a.objects.streams.File.TextReaderWrapper _textreader1 = null;
public static anywheresoftware.b4a.objects.streams.File.TextWriterWrapper _textwriter1 = null;
public static anywheresoftware.b4a.objects.Timer _timer1 = null;
public static anywheresoftware.b4a.objects.Timer _timer2 = null;
public static boolean _connected = false;
public static int[][] _ir_palette = null;
public static anywheresoftware.b4a.objects.Serial.BluetoothAdmin _admin = null;
public static anywheresoftware.b4a.objects.collections.List _founddevices = null;
public static mardaso.sensor.fir.sensor.main._nameandmac _connecteddevice = null;
public anywheresoftware.b4a.objects.CameraW _camera1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _touchpanel = null;
public anywheresoftware.b4a.objects.PanelWrapper _irdata = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtlog = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtsend = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar1 = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar2 = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar3 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _palview = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _palview2 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview1 = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _csvpanelview = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _csvpalview = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect1 = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect2 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _checkbox1 = null;
public static boolean _autoscale = false;
public static boolean _interpol = false;
public anywheresoftware.b4a.objects.LabelWrapper _ptat = null;
public anywheresoftware.b4a.objects.LabelWrapper _pix_0 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lmin = null;
public anywheresoftware.b4a.objects.LabelWrapper _lmax = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _mintemp = null;
public anywheresoftware.b4a.objects.LabelWrapper _maxtemp = null;
public anywheresoftware.b4a.objects.LabelWrapper _spottemp = null;
public anywheresoftware.b4a.objects.LabelWrapper _ptattemp = null;
public anywheresoftware.b4a.objects.LabelWrapper _scalestatus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lpalette = null;
public anywheresoftware.b4a.objects.LabelWrapper _labalpha = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edittext1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edittext2 = null;
public static int[] _irval = null;
public static int[] _regval = null;
public static float[] _t_ir = null;
public static float[][] _ir_array = null;
public static double _kt1 = 0;
public static double _kt2 = 0;
public static double _vth = 0;
public static double _tgc = 0;
public static double _acp = 0;
public static double _bcp = 0;
public static byte _bi_scale = (byte)0;
public static int[] _bi = null;
public static int[] _ai = null;
public static int[] _d_alpha = null;
public static double _emmission = 0;
public static double _alpha_0_scale = 0;
public static double _alpha_d_scale = 0;
public static double _alpha_common = 0;
public static int[] _eval = null;
public static int _valr = 0;
public static int _valg = 0;
public static int _valb = 0;
public static float _calcx = 0f;
public static float _calcy = 0f;
public static float _tx = 0f;
public static float _ty = 0f;
public static float _thx = 0f;
public static float _thy = 0f;
public static boolean _holdactive = false;
public static boolean _seron = false;
public static float _scalemax = 0f;
public static float _scalemin = 0f;
public static int _ipalette = 0;
public static int _ipause = 0;
public static int _ihz = 0;
public static int _automode = 0;
public static String _datatxt = "";
public static String _datacalc = "";
public static String _freq = "";
public anywheresoftware.b4a.objects.ButtonWrapper _btnminplus = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnmaxminus = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnminminus = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnmaxplus = null;
public anywheresoftware.b4a.objects.ButtonWrapper _autoscale2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bpalette = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnpause = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnhz = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btncam = null;
public anywheresoftware.b4a.agraham.reflection.Reflection _refl = null;
public static int _refrirdata = 0;
public static int _icam = 0;
public static int _refreshtime = 0;
public static int _oldfadecam = 0;
public static int _oldfadenocam = 0;
public static int _fade = 0;
public static int _xoffset = 0;
public static int _yoffset = 0;
public static int _timercount = 0;
public static String _temp = "";
public anywheresoftware.b4a.objects.MediaPlayerWrapper _mp = null;
public anywheresoftware.b4a.agraham.dialogs.InputDialog _id = null;
public static float _inputtemp = 0f;
public static int _loadid = 0;
public anywheresoftware.b4a.audio.SoundPoolWrapper _sp = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static class _nameandmac{
public boolean IsInitialized;
public String Name;
public String Mac;
public void Initialize() {
IsInitialized = true;
Name = "";
Mac = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 112;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 115;BA.debugLine="ID.Show(\"Enter Temperature\", \"Temperature\", \"OK\", \"Cancel\", \"\", Null)";
mostCurrent._id.Show("Enter Temperature","Temperature","OK","Cancel","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 116;BA.debugLine="inputTemp = ID.Input";
_inputtemp = (float)(Double.parseDouble(mostCurrent._id.getInput()));
 //BA.debugLineNum = 118;BA.debugLine="mp.Initialize2(\"MP\")";
mostCurrent._mp.Initialize2(processBA,"MP");
 //BA.debugLineNum = 120;BA.debugLine="RefreshTime = 100 '100 '80 '25 best?";
_refreshtime = (int) (100);
 //BA.debugLineNum = 121;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 122;BA.debugLine="serial1.Initialize(\"Serial1\")";
_serial1.Initialize("Serial1");
 //BA.debugLineNum = 123;BA.debugLine="Timer1.Initialize(\"Timer1\", RefreshTime)";
_timer1.Initialize(processBA,"Timer1",(long) (_refreshtime));
 //BA.debugLineNum = 124;BA.debugLine="Timer2.Initialize(\"Timer2\", 100)";
_timer2.Initialize(processBA,"Timer2",(long) (100));
 };
 //BA.debugLineNum = 126;BA.debugLine="Activity.LoadLayout(\"1\")";
mostCurrent._activity.LoadLayout("1",mostCurrent.activityBA);
 //BA.debugLineNum = 127;BA.debugLine="Activity.AddMenuItem(\"SecConnect\", \"mnuConnect\")";
mostCurrent._activity.AddMenuItem("SecConnect","mnuConnect");
 //BA.debugLineNum = 128;BA.debugLine="Activity.AddMenuItem(\"InsConnect\", \"mnuInsConnect\")";
mostCurrent._activity.AddMenuItem("InsConnect","mnuInsConnect");
 //BA.debugLineNum = 129;BA.debugLine="Activity.AddMenuItem(\"Disconnect\", \"mnuDisconnect\")";
mostCurrent._activity.AddMenuItem("Disconnect","mnuDisconnect");
 //BA.debugLineNum = 130;BA.debugLine="Activity.AddMenuItem(\"Settings\", \"mnuSettings\")";
mostCurrent._activity.AddMenuItem("Settings","mnuSettings");
 //BA.debugLineNum = 131;BA.debugLine="Activity.TitleColor = Colors.RGB(154,205,50)";
mostCurrent._activity.setTitleColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (154),(int) (205),(int) (50)));
 //BA.debugLineNum = 132;BA.debugLine="Activity.Title = \"FIRsensor V0.60 demo source code MarDaSo 2013\"";
mostCurrent._activity.setTitle((Object)("FIRsensor V0.60 demo source code MarDaSo 2013"));
 //BA.debugLineNum = 133;BA.debugLine="Panel2.Initialize (\"\")";
mostCurrent._panel2.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 134;BA.debugLine="Activity.AddView (Panel2, Round(12.5%x) , 12dip, 75%x, 80%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._panel2.getObject()),(int) (anywheresoftware.b4a.keywords.Common.Round(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (12.5),mostCurrent.activityBA))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (75),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (80),mostCurrent.activityBA));
 //BA.debugLineNum = 136;BA.debugLine="Panel2.SendToBack";
mostCurrent._panel2.SendToBack();
 //BA.debugLineNum = 137;BA.debugLine="CalcX = -1 + Round(75%x/144)";
_calcx = (float) (-1+anywheresoftware.b4a.keywords.Common.Round(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (75),mostCurrent.activityBA)/(double)144));
 //BA.debugLineNum = 138;BA.debugLine="CalcX = CalcX * 144";
_calcx = (float) (_calcx*144);
 //BA.debugLineNum = 139;BA.debugLine="CalcY = CalcX/4";
_calcy = (float) (_calcx/(double)4);
 //BA.debugLineNum = 140;BA.debugLine="Xoffset = 20dip";
_xoffset = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20));
 //BA.debugLineNum = 141;BA.debugLine="Yoffset = 0dip";
_yoffset = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0));
 //BA.debugLineNum = 142;BA.debugLine="IRData.Initialize (\"\")";
mostCurrent._irdata.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 143;BA.debugLine="Activity.AddView (IRData, (Panel2.Left+Xoffset+(Panel2.Width -CalcX)/2) , (Panel2.Top + Yoffset+(Panel2.Height/2 - CalcY/2)), CalcX, CalcY)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._irdata.getObject()),(int) ((mostCurrent._panel2.getLeft()+_xoffset+(mostCurrent._panel2.getWidth()-_calcx)/(double)2)),(int) ((mostCurrent._panel2.getTop()+_yoffset+(mostCurrent._panel2.getHeight()/(double)2-_calcy/(double)2))),(int) (_calcx),(int) (_calcy));
 //BA.debugLineNum = 144;BA.debugLine="csvPanelView.Initialize(IRData)";
mostCurrent._csvpanelview.Initialize((android.view.View)(mostCurrent._irdata.getObject()));
 //BA.debugLineNum = 145;BA.debugLine="Imageview1.Color = Colors.Black";
mostCurrent._imageview1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 146;BA.debugLine="Imageview1.SendToBack";
mostCurrent._imageview1.SendToBack();
 //BA.debugLineNum = 147;BA.debugLine="PalView.Initialize (\"\")";
mostCurrent._palview.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 148;BA.debugLine="Activity.AddView (PalView,PalView2.Left ,PalView2.Top ,PalView2.Width ,PalView2.Height )";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._palview.getObject()),mostCurrent._palview2.getLeft(),mostCurrent._palview2.getTop(),mostCurrent._palview2.getWidth(),mostCurrent._palview2.getHeight());
 //BA.debugLineNum = 149;BA.debugLine="csvPalview.Initialize(PalView)";
mostCurrent._csvpalview.Initialize((android.view.View)(mostCurrent._palview.getObject()));
 //BA.debugLineNum = 150;BA.debugLine="DrawPalettte(100,0)";
_drawpalettte(BA.NumberToString(100),(int) (0));
 //BA.debugLineNum = 151;BA.debugLine="scaleMin = 0";
_scalemin = (float) (0);
 //BA.debugLineNum = 152;BA.debugLine="scaleMax = 50";
_scalemax = (float) (50);
 //BA.debugLineNum = 153;BA.debugLine="MinTemp.Text = Round2(scaleMin,1)";
mostCurrent._mintemp.setText((Object)(anywheresoftware.b4a.keywords.Common.Round2(_scalemin,(int) (1))));
 //BA.debugLineNum = 154;BA.debugLine="MaxTemp.Text = Round2(scaleMax,1)";
mostCurrent._maxtemp.setText((Object)(anywheresoftware.b4a.keywords.Common.Round2(_scalemax,(int) (1))));
 //BA.debugLineNum = 155;BA.debugLine="AUTOSCALE = True";
_autoscale = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 156;BA.debugLine="AutoMode = 1";
_automode = (int) (1);
 //BA.debugLineNum = 157;BA.debugLine="IPalette = 0";
_ipalette = (int) (0);
 //BA.debugLineNum = 158;BA.debugLine="IHz = 4 '4Hz default";
_ihz = (int) (4);
 //BA.debugLineNum = 159;BA.debugLine="refrIRData = 0";
_refrirdata = (int) (0);
 //BA.debugLineNum = 160;BA.debugLine="Fade = 128";
_fade = (int) (128);
 //BA.debugLineNum = 161;BA.debugLine="OldFadeCam = 128";
_oldfadecam = (int) (128);
 //BA.debugLineNum = 162;BA.debugLine="OldFadeNoCam = 255";
_oldfadenocam = (int) (255);
 //BA.debugLineNum = 163;BA.debugLine="LabAlpha.Text = Fade";
mostCurrent._labalpha.setText((Object)(_fade));
 //BA.debugLineNum = 164;BA.debugLine="ICam = 1";
_icam = (int) (1);
 //BA.debugLineNum = 165;BA.debugLine="Timercount = 0";
_timercount = (int) (0);
 //BA.debugLineNum = 166;BA.debugLine="EEPROM_Defaults";
_eeprom_defaults();
 //BA.debugLineNum = 167;BA.debugLine="InterPol = False";
_interpol = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 168;BA.debugLine="SerOn = False";
_seron = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 169;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
anywheresoftware.b4a.phone.Phone.PhoneWakeState _pws = null;
 //BA.debugLineNum = 192;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 193;BA.debugLine="Dim pws As PhoneWakeState";
_pws = new anywheresoftware.b4a.phone.Phone.PhoneWakeState();
 //BA.debugLineNum = 194;BA.debugLine="If UserClosed Then";
if (_userclosed) { 
 //BA.debugLineNum = 195;BA.debugLine="serial1.StopListening";
_serial1.StopListening();
 //BA.debugLineNum = 196;BA.debugLine="serial1.Disconnect";
_serial1.Disconnect();
 //BA.debugLineNum = 197;BA.debugLine="connected = False";
_connected = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 198;BA.debugLine="SerOn = False";
_seron = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 200;BA.debugLine="pws.ReleaseKeepAlive    ' display stays off";
_pws.ReleaseKeepAlive();
 //BA.debugLineNum = 201;BA.debugLine="camera1.StopPreview";
mostCurrent._camera1.StopPreview();
 //BA.debugLineNum = 202;BA.debugLine="camera1.Release";
mostCurrent._camera1.Release();
 //BA.debugLineNum = 203;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
anywheresoftware.b4a.phone.Phone.PhoneWakeState _pws = null;
 //BA.debugLineNum = 179;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 180;BA.debugLine="Dim pws As PhoneWakeState";
_pws = new anywheresoftware.b4a.phone.Phone.PhoneWakeState();
 //BA.debugLineNum = 181;BA.debugLine="If serial1.IsEnabled = False Then";
if (_serial1.IsEnabled()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 182;BA.debugLine="Msgbox(\"Please enable Bluetooth.\", \"\")";
anywheresoftware.b4a.keywords.Common.Msgbox("Please enable Bluetooth.","",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 184;BA.debugLine="serial1.StopListening";
_serial1.StopListening();
 //BA.debugLineNum = 185;BA.debugLine="serial1.Disconnect";
_serial1.Disconnect();
 //BA.debugLineNum = 186;BA.debugLine="connected = False";
_connected = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 187;BA.debugLine="SerOn = False";
_seron = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 189;BA.debugLine="pws.KeepAlive(True)     ' display stays on";
_pws.KeepAlive(processBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 190;BA.debugLine="camera1.Initialize(Panel2, \"Camera1\")";
mostCurrent._camera1.Initialize(mostCurrent.activityBA,(android.view.ViewGroup)(mostCurrent._panel2.getObject()),"Camera1");
 //BA.debugLineNum = 191;BA.debugLine="End Sub";
return "";
}
public static String  _btnminminus_click() throws Exception{
int _i = 0;
String _str = "";
 //BA.debugLineNum = 232;BA.debugLine="Sub btnMinMinus_Click";
 //BA.debugLineNum = 234;BA.debugLine="Temp = \"\"";
mostCurrent._temp = "";
 //BA.debugLineNum = 236;BA.debugLine="For i = 0 To 63";
{
final int step179 = 1;
final int limit179 = (int) (63);
for (_i = (int) (0); (step179 > 0 && _i <= limit179) || (step179 < 0 && _i >= limit179); _i = ((int)(0 + _i + step179))) {
 //BA.debugLineNum = 237;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 239;BA.debugLine="If i Mod 13 = 0 AND i <> 0 Then";
if (_i%13==0 && _i!=0) { 
 //BA.debugLineNum = 240;BA.debugLine="Temp = Temp & CRLF";
mostCurrent._temp = mostCurrent._temp+anywheresoftware.b4a.keywords.Common.CRLF;
 };
 //BA.debugLineNum = 243;BA.debugLine="str = Round2(T_Ir(i),1)";
_str = BA.NumberToString(anywheresoftware.b4a.keywords.Common.Round2(_t_ir[_i],(int) (1)));
 //BA.debugLineNum = 244;BA.debugLine="Temp = Temp & str & \" \"";
mostCurrent._temp = mostCurrent._temp+_str+" ";
 }
};
 //BA.debugLineNum = 247;BA.debugLine="Msgbox(Temp, \"IR Value\")";
anywheresoftware.b4a.keywords.Common.Msgbox(mostCurrent._temp,"IR Value",mostCurrent.activityBA);
 //BA.debugLineNum = 250;BA.debugLine="End Sub";
return "";
}
public static String  _btnminplus_click() throws Exception{
 //BA.debugLineNum = 227;BA.debugLine="Sub btnMinPlus_Click";
 //BA.debugLineNum = 229;BA.debugLine="Msgbox(inputTemp, \"Hello\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.NumberToString(_inputtemp),"Hello",mostCurrent.activityBA);
 //BA.debugLineNum = 230;BA.debugLine="End Sub";
return "";
}
public static String  _calculation() throws Exception{
int _xpos = 0;
int _rpos = 0;
int _epos = 0;
int _spos = 0;
byte _raddr = (byte)0;
int _pval = 0;
int _ival = 0;
float _rval = 0f;
double _imax = 0;
double _imin = 0;
byte _iaddr = (byte)0;
int _ivalir = 0;
int _eaddr = 0;
double _ta = 0;
double _vcp = 0;
double _vir = 0;
double _t0 = 0;
double _vcp_off_comp = 0;
double _vir_off_comp = 0;
double _vir_tgc_comp = 0;
double _vir_comp = 0;
double _alpha = 0;
int _xa = 0;
int _ya = 0;
float _x = 0f;
float _y = 0f;
 //BA.debugLineNum = 350;BA.debugLine="Sub Calculation";
 //BA.debugLineNum = 351;BA.debugLine="Dim xPos As Int";
_xpos = 0;
 //BA.debugLineNum = 352;BA.debugLine="Dim rPos As Int";
_rpos = 0;
 //BA.debugLineNum = 353;BA.debugLine="Dim ePos As Int";
_epos = 0;
 //BA.debugLineNum = 354;BA.debugLine="Dim sPos As Int";
_spos = 0;
 //BA.debugLineNum = 355;BA.debugLine="Dim rAddr As Byte";
_raddr = (byte)0;
 //BA.debugLineNum = 356;BA.debugLine="Dim pVal As Int";
_pval = 0;
 //BA.debugLineNum = 357;BA.debugLine="Dim iVal As Int";
_ival = 0;
 //BA.debugLineNum = 358;BA.debugLine="Dim rVal As Float";
_rval = 0f;
 //BA.debugLineNum = 359;BA.debugLine="Dim iMax As Double";
_imax = 0;
 //BA.debugLineNum = 360;BA.debugLine="Dim iMin As Double";
_imin = 0;
 //BA.debugLineNum = 361;BA.debugLine="Dim iAddr As Byte";
_iaddr = (byte)0;
 //BA.debugLineNum = 362;BA.debugLine="Dim iValIR As Int";
_ivalir = 0;
 //BA.debugLineNum = 363;BA.debugLine="Dim eAddr As Int";
_eaddr = 0;
 //BA.debugLineNum = 364;BA.debugLine="Dim ta As Double";
_ta = 0;
 //BA.debugLineNum = 365;BA.debugLine="Dim Vcp As Double";
_vcp = 0;
 //BA.debugLineNum = 366;BA.debugLine="Dim Vir As Double";
_vir = 0;
 //BA.debugLineNum = 367;BA.debugLine="Dim T0 As Double";
_t0 = 0;
 //BA.debugLineNum = 368;BA.debugLine="Dim Vcp_off_comp As Double";
_vcp_off_comp = 0;
 //BA.debugLineNum = 369;BA.debugLine="Dim Vir_off_comp As Double";
_vir_off_comp = 0;
 //BA.debugLineNum = 370;BA.debugLine="Dim Vir_TGC_comp As Double";
_vir_tgc_comp = 0;
 //BA.debugLineNum = 371;BA.debugLine="Dim Vir_comp As Double";
_vir_comp = 0;
 //BA.debugLineNum = 372;BA.debugLine="Dim Alpha As Double";
_alpha = 0;
 //BA.debugLineNum = 373;BA.debugLine="Dim xa As Int";
_xa = 0;
 //BA.debugLineNum = 374;BA.debugLine="Dim ya As Int";
_ya = 0;
 //BA.debugLineNum = 375;BA.debugLine="Dim x As Float";
_x = 0f;
 //BA.debugLineNum = 376;BA.debugLine="Dim y As Float";
_y = 0f;
 //BA.debugLineNum = 386;BA.debugLine="If IPause = 0 Then";
if (_ipause==0) { 
 //BA.debugLineNum = 387;BA.debugLine="Datatxt = DataCalc";
mostCurrent._datatxt = mostCurrent._datacalc;
 //BA.debugLineNum = 388;BA.debugLine="If Datatxt.StartsWith(\"E\") = True  Then";
if (mostCurrent._datatxt.startsWith("E")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 389;BA.debugLine="eAddr = 0";
_eaddr = (int) (0);
 //BA.debugLineNum = 390;BA.debugLine="rPos = Datatxt.IndexOf(\"E\")";
_rpos = mostCurrent._datatxt.indexOf("E");
 //BA.debugLineNum = 391;BA.debugLine="ePos = Datatxt.IndexOf(\"EX\")";
_epos = mostCurrent._datatxt.indexOf("EX");
 //BA.debugLineNum = 392;BA.debugLine="For xPos = 0 To ((ePos - 5) - rPos) Step 3";
{
final int step305 = (int) (3);
final int limit305 = (int) (((_epos-5)-_rpos));
for (_xpos = (int) (0); (step305 > 0 && _xpos <= limit305) || (step305 < 0 && _xpos >= limit305); _xpos = ((int)(0 + _xpos + step305))) {
 //BA.debugLineNum = 393;BA.debugLine="sPos = rPos + xPos";
_spos = (int) (_rpos+_xpos);
 //BA.debugLineNum = 394;BA.debugLine="eVal(eAddr) = Datatxt.Substring2(sPos + 1, sPos + 4)";
_eval[_eaddr] = (int)(Double.parseDouble(mostCurrent._datatxt.substring((int) (_spos+1),(int) (_spos+4))));
 //BA.debugLineNum = 395;BA.debugLine="eAddr = eAddr + 1";
_eaddr = (int) (_eaddr+1);
 }
};
 //BA.debugLineNum = 397;BA.debugLine="VTH = 256*eVal(219)+eVal(218)'6752";
_vth = 256*_eval[(int) (219)]+_eval[(int) (218)];
 //BA.debugLineNum = 398;BA.debugLine="If VTH > 32767 Then";
if (_vth>32767) { 
 //BA.debugLineNum = 399;BA.debugLine="VTH = VTH - 65536";
_vth = _vth-65536;
 };
 //BA.debugLineNum = 402;BA.debugLine="Kt1 = 256*eVal(221) + eVal(220)";
_kt1 = 256*_eval[(int) (221)]+_eval[(int) (220)];
 //BA.debugLineNum = 403;BA.debugLine="If Kt1 > 32767 Then";
if (_kt1>32767) { 
 //BA.debugLineNum = 404;BA.debugLine="Kt1 = Kt1 - 65536";
_kt1 = _kt1-65536;
 };
 //BA.debugLineNum = 406;BA.debugLine="Kt1 = Kt1/1024";
_kt1 = _kt1/(double)1024;
 //BA.debugLineNum = 408;BA.debugLine="Kt2 = (256*eVal(223)+eVal(222))";
_kt2 = (256*_eval[(int) (223)]+_eval[(int) (222)]);
 //BA.debugLineNum = 409;BA.debugLine="If Kt2 > 32767 Then";
if (_kt2>32767) { 
 //BA.debugLineNum = 410;BA.debugLine="Kt2 = Kt2 - 65536";
_kt2 = _kt2-65536;
 };
 //BA.debugLineNum = 412;BA.debugLine="Kt2 = Kt2/1048576";
_kt2 = _kt2/(double)1048576;
 //BA.debugLineNum = 414;BA.debugLine="Emmission = (256*(eVal(229))+eVal(228))/32768 '0.949981";
_emmission = (256*(_eval[(int) (229)])+_eval[(int) (228)])/(double)32768;
 //BA.debugLineNum = 415;BA.debugLine="Bi_scale = eVal(217) '8";
_bi_scale = (byte) (_eval[(int) (217)]);
 //BA.debugLineNum = 416;BA.debugLine="TGC = eVal(216)";
_tgc = _eval[(int) (216)];
 //BA.debugLineNum = 417;BA.debugLine="If TGC > 32767 Then";
if (_tgc>32767) { 
 //BA.debugLineNum = 418;BA.debugLine="TGC = TGC - 65536";
_tgc = _tgc-65536;
 };
 //BA.debugLineNum = 421;BA.debugLine="Acp = eVal(212)";
_acp = _eval[(int) (212)];
 //BA.debugLineNum = 422;BA.debugLine="If Acp > 127 Then";
if (_acp>127) { 
 //BA.debugLineNum = 423;BA.debugLine="Acp = Acp - 256";
_acp = _acp-256;
 };
 //BA.debugLineNum = 426;BA.debugLine="Bcp = eVal(213)";
_bcp = _eval[(int) (213)];
 //BA.debugLineNum = 427;BA.debugLine="If Bcp > 127 Then";
if (_bcp>127) { 
 //BA.debugLineNum = 428;BA.debugLine="Bcp = Bcp - 256";
_bcp = _bcp-256;
 };
 //BA.debugLineNum = 431;BA.debugLine="For xPos = 128 To 191";
{
final int step338 = 1;
final int limit338 = (int) (191);
for (_xpos = (int) (128); (step338 > 0 && _xpos <= limit338) || (step338 < 0 && _xpos >= limit338); _xpos = ((int)(0 + _xpos + step338))) {
 //BA.debugLineNum = 432;BA.debugLine="D_Alpha(xPos-128) = eVal(xPos)";
_d_alpha[(int) (_xpos-128)] = _eval[_xpos];
 }
};
 //BA.debugLineNum = 435;BA.debugLine="For xPos = 64 To 127";
{
final int step341 = 1;
final int limit341 = (int) (127);
for (_xpos = (int) (64); (step341 > 0 && _xpos <= limit341) || (step341 < 0 && _xpos >= limit341); _xpos = ((int)(0 + _xpos + step341))) {
 //BA.debugLineNum = 436;BA.debugLine="If eVal(xPos) > 127 Then";
if (_eval[_xpos]>127) { 
 //BA.debugLineNum = 437;BA.debugLine="Bi(xPos-64) = eVal(xPos)-256";
_bi[(int) (_xpos-64)] = (int) (_eval[_xpos]-256);
 }else {
 //BA.debugLineNum = 439;BA.debugLine="Bi(xPos-64) = eVal(xPos)";
_bi[(int) (_xpos-64)] = _eval[_xpos];
 };
 }
};
 //BA.debugLineNum = 443;BA.debugLine="For xPos = 0 To 63";
{
final int step348 = 1;
final int limit348 = (int) (63);
for (_xpos = (int) (0); (step348 > 0 && _xpos <= limit348) || (step348 < 0 && _xpos >= limit348); _xpos = ((int)(0 + _xpos + step348))) {
 //BA.debugLineNum = 444;BA.debugLine="If eVal(xPos) > 127 Then";
if (_eval[_xpos]>127) { 
 //BA.debugLineNum = 445;BA.debugLine="Ai(xPos) = eVal(xPos)-256";
_ai[_xpos] = (int) (_eval[_xpos]-256);
 }else {
 //BA.debugLineNum = 447;BA.debugLine="Ai(xPos) = eVal(xPos)";
_ai[_xpos] = _eval[_xpos];
 };
 }
};
 //BA.debugLineNum = 451;BA.debugLine="Alpha_0_scale = eVal(226)";
_alpha_0_scale = _eval[(int) (226)];
 //BA.debugLineNum = 452;BA.debugLine="Alpha_d_scale = eVal(227)";
_alpha_d_scale = _eval[(int) (227)];
 //BA.debugLineNum = 453;BA.debugLine="Alpha_common = 	(256*eVal(225)+eVal(224))/Power(2, Alpha_0_scale)";
_alpha_common = (256*_eval[(int) (225)]+_eval[(int) (224)])/(double)anywheresoftware.b4a.keywords.Common.Power(2,_alpha_0_scale);
 //BA.debugLineNum = 455;BA.debugLine="label1.TextColor = Colors.RGB(154,205,50)";
mostCurrent._label1.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (154),(int) (205),(int) (50)));
 //BA.debugLineNum = 456;BA.debugLine="label1.Text= \"E\"";
mostCurrent._label1.setText((Object)("E"));
 };
 //BA.debugLineNum = 459;BA.debugLine="rPos = Datatxt.IndexOf(\"I\")";
_rpos = mostCurrent._datatxt.indexOf("I");
 //BA.debugLineNum = 460;BA.debugLine="ePos = Datatxt.IndexOf(\"X\")";
_epos = mostCurrent._datatxt.indexOf("X");
 //BA.debugLineNum = 462;BA.debugLine="If Datatxt.StartsWith(\"R\") = True Then";
if (mostCurrent._datatxt.startsWith("R")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 463;BA.debugLine="regVal(0) = Datatxt.Substring2(4, 10)";
_regval[(int) (0)] = (int)(Double.parseDouble(mostCurrent._datatxt.substring((int) (4),(int) (10))));
 //BA.debugLineNum = 464;BA.debugLine="regVal(1) = Datatxt.Substring2(13, 19)";
_regval[(int) (1)] = (int)(Double.parseDouble(mostCurrent._datatxt.substring((int) (13),(int) (19))));
 };
 //BA.debugLineNum = 469;BA.debugLine="rVal = regVal(0)";
_rval = (float) (_regval[(int) (0)]);
 //BA.debugLineNum = 470;BA.debugLine="Vcp = regVal(1)";
_vcp = _regval[(int) (1)];
 //BA.debugLineNum = 471;BA.debugLine="If Vcp > 32767 Then";
if (_vcp>32767) { 
 //BA.debugLineNum = 472;BA.debugLine="Vcp = Vcp - 65536";
_vcp = _vcp-65536;
 };
 //BA.debugLineNum = 475;BA.debugLine="ta = (((-1 * Kt1) + Sqrt((Kt1 * Kt1) - (4 * Kt2) * (VTH - rVal))) / (2 * Kt2)) + 25";
_ta = (((-1*_kt1)+anywheresoftware.b4a.keywords.Common.Sqrt((_kt1*_kt1)-(4*_kt2)*(_vth-_rval)))/(double)(2*_kt2))+25;
 //BA.debugLineNum = 476;BA.debugLine="PTATTemp.text = Round2(ta, 2)";
mostCurrent._ptattemp.setText((Object)(anywheresoftware.b4a.keywords.Common.Round2(_ta,(int) (2))));
 //BA.debugLineNum = 477;BA.debugLine="Vcp_off_comp = Vcp - (Acp + (Bcp / Power(2, Bi_scale) * (ta - 25)))";
_vcp_off_comp = _vcp-(_acp+(_bcp/(double)anywheresoftware.b4a.keywords.Common.Power(2,_bi_scale)*(_ta-25)));
 //BA.debugLineNum = 479;BA.debugLine="If rPos > 0 Then";
if (_rpos>0) { 
 //BA.debugLineNum = 480;BA.debugLine="iMax = 0";
_imax = 0;
 //BA.debugLineNum = 481;BA.debugLine="iMin = 100";
_imin = 100;
 //BA.debugLineNum = 482;BA.debugLine="iAddr = -1";
_iaddr = (byte) (-1);
 //BA.debugLineNum = 483;BA.debugLine="If VTH = 0 Then";
if (_vth==0) { 
 //BA.debugLineNum = 484;BA.debugLine="EEPROM_Defaults";
_eeprom_defaults();
 };
 //BA.debugLineNum = 486;BA.debugLine="For xPos = 0 To ((ePos - 7) - rPos) Step 6";
{
final int step382 = (int) (6);
final int limit382 = (int) (((_epos-7)-_rpos));
for (_xpos = (int) (0); (step382 > 0 && _xpos <= limit382) || (step382 < 0 && _xpos >= limit382); _xpos = ((int)(0 + _xpos + step382))) {
 //BA.debugLineNum = 487;BA.debugLine="sPos = rPos + xPos";
_spos = (int) (_rpos+_xpos);
 //BA.debugLineNum = 488;BA.debugLine="iAddr = iAddr + 1";
_iaddr = (byte) (_iaddr+1);
 //BA.debugLineNum = 489;BA.debugLine="irVal(iAddr) = Datatxt.Substring2(sPos + 1, sPos + 7)";
_irval[(int) (_iaddr)] = (int)(Double.parseDouble(mostCurrent._datatxt.substring((int) (_spos+1),(int) (_spos+7))));
 //BA.debugLineNum = 490;BA.debugLine="iValIR = irVal(iAddr)";
_ivalir = _irval[(int) (_iaddr)];
 //BA.debugLineNum = 491;BA.debugLine="If iValIR > 127 Then";
if (_ivalir>127) { 
 //BA.debugLineNum = 492;BA.debugLine="iValIR = iValIR - 256";
_ivalir = (int) (_ivalir-256);
 };
 //BA.debugLineNum = 496;BA.debugLine="Vir_off_comp = iValIR - (Ai(iAddr) + (Bi(iAddr) / Power(2, Bi_scale) * (ta - 25)))";
_vir_off_comp = _ivalir-(_ai[(int) (_iaddr)]+(_bi[(int) (_iaddr)]/(double)anywheresoftware.b4a.keywords.Common.Power(2,_bi_scale)*(_ta-25)));
 //BA.debugLineNum = 497;BA.debugLine="Alpha =  Alpha_common  + (D_Alpha(iAddr)/Power(2,Alpha_d_scale))  '0.000000029097";
_alpha = _alpha_common+(_d_alpha[(int) (_iaddr)]/(double)anywheresoftware.b4a.keywords.Common.Power(2,_alpha_d_scale));
 //BA.debugLineNum = 498;BA.debugLine="Vir_TGC_comp = Vir_off_comp - ((TGC / 32) * Vcp_off_comp)";
_vir_tgc_comp = _vir_off_comp-((_tgc/(double)32)*_vcp_off_comp);
 //BA.debugLineNum = 499;BA.debugLine="Vir_comp = Vir_TGC_comp / Emmission";
_vir_comp = _vir_tgc_comp/(double)_emmission;
 //BA.debugLineNum = 500;BA.debugLine="T0 = Power((Vir_comp / Alpha + Power((ta + 273.15), 4)), 0.25) - 273.15";
_t0 = anywheresoftware.b4a.keywords.Common.Power((_vir_comp/(double)_alpha+anywheresoftware.b4a.keywords.Common.Power((_ta+273.15),4)),0.25)-273.15;
 //BA.debugLineNum = 501;BA.debugLine="T_Ir(iAddr) = Round2(T0, 2)";
_t_ir[(int) (_iaddr)] = (float) (anywheresoftware.b4a.keywords.Common.Round2(_t0,(int) (2)));
 //BA.debugLineNum = 502;BA.debugLine="If T_Ir(iAddr) > iMax Then";
if (_t_ir[(int) (_iaddr)]>_imax) { 
 //BA.debugLineNum = 503;BA.debugLine="iMax = T_Ir(iAddr)";
_imax = _t_ir[(int) (_iaddr)];
 //BA.debugLineNum = 504;BA.debugLine="SpotTemp.Text = Round2(iMax, 2)";
mostCurrent._spottemp.setText((Object)(anywheresoftware.b4a.keywords.Common.Round2(_imax,(int) (2))));
 };
 //BA.debugLineNum = 506;BA.debugLine="If AUTOSCALE  = True Then";
if (_autoscale==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 507;BA.debugLine="If AutoMode = 1 Then";
if (_automode==1) { 
 //BA.debugLineNum = 508;BA.debugLine="scaleMax = Round2(iMax, 1)";
_scalemax = (float) (anywheresoftware.b4a.keywords.Common.Round2(_imax,(int) (1)));
 }else {
 //BA.debugLineNum = 510;BA.debugLine="scaleMax = Ceil((scaleMax + Ceil(iMax)) /2)";
_scalemax = (float) (anywheresoftware.b4a.keywords.Common.Ceil((_scalemax+anywheresoftware.b4a.keywords.Common.Ceil(_imax))/(double)2));
 };
 //BA.debugLineNum = 512;BA.debugLine="If T_Ir(iAddr) < iMin Then";
if (_t_ir[(int) (_iaddr)]<_imin) { 
 //BA.debugLineNum = 513;BA.debugLine="iMin = T_Ir(iAddr)";
_imin = _t_ir[(int) (_iaddr)];
 //BA.debugLineNum = 514;BA.debugLine="If AutoMode = 1 Then";
if (_automode==1) { 
 //BA.debugLineNum = 515;BA.debugLine="scaleMin = Round2(iMin, 1)";
_scalemin = (float) (anywheresoftware.b4a.keywords.Common.Round2(_imin,(int) (1)));
 }else {
 //BA.debugLineNum = 517;BA.debugLine="scaleMin = Floor((scaleMin + Floor(iMin))/2)";
_scalemin = (float) (anywheresoftware.b4a.keywords.Common.Floor((_scalemin+anywheresoftware.b4a.keywords.Common.Floor(_imin))/(double)2));
 };
 };
 //BA.debugLineNum = 520;BA.debugLine="If AutoMode = 1 Then";
if (_automode==1) { 
 //BA.debugLineNum = 521;BA.debugLine="If scaleMin >= (scaleMax -5) Then";
if (_scalemin>=(_scalemax-5)) { 
 //BA.debugLineNum = 522;BA.debugLine="scaleMin = scaleMax - 5";
_scalemin = (float) (_scalemax-5);
 };
 }else {
 //BA.debugLineNum = 525;BA.debugLine="If scaleMin >= (scaleMax -10) Then";
if (_scalemin>=(_scalemax-10)) { 
 //BA.debugLineNum = 526;BA.debugLine="scaleMin = scaleMax - 10";
_scalemin = (float) (_scalemax-10);
 };
 };
 }else {
 //BA.debugLineNum = 531;BA.debugLine="iMin = scaleMin";
_imin = _scalemin;
 //BA.debugLineNum = 532;BA.debugLine="iMax = scaleMax";
_imax = _scalemax;
 };
 }
};
 };
 };
 //BA.debugLineNum = 540;BA.debugLine="If InterPol = False Then";
if (_interpol==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 541;BA.debugLine="For y = 0 To 3";
{
final double step431 = 1;
final double limit431 = (float) (3);
for (_y = (float) (0); (step431 > 0 && _y <= limit431) || (step431 < 0 && _y >= limit431); _y = ((float)(0 + _y + step431))) {
 //BA.debugLineNum = 542;BA.debugLine="For x = 0 To 15";
{
final double step432 = 1;
final double limit432 = (float) (15);
for (_x = (float) (0); (step432 > 0 && _x <= limit432) || (step432 < 0 && _x >= limit432); _x = ((float)(0 + _x + step432))) {
 //BA.debugLineNum = 543;BA.debugLine="iAddr = x*4 + y";
_iaddr = (byte) (_x*4+_y);
 //BA.debugLineNum = 544;BA.debugLine="For ya = y*9 To (y*9 + 8)";
{
final int step434 = 1;
final int limit434 = (int) ((_y*9+8));
for (_ya = (int) (_y*9); (step434 > 0 && _ya <= limit434) || (step434 < 0 && _ya >= limit434); _ya = ((int)(0 + _ya + step434))) {
 //BA.debugLineNum = 545;BA.debugLine="For xa = x*9 To (x*9 + 8)";
{
final int step435 = 1;
final int limit435 = (int) ((_x*9+8));
for (_xa = (int) (_x*9); (step435 > 0 && _xa <= limit435) || (step435 < 0 && _xa >= limit435); _xa = ((int)(0 + _xa + step435))) {
 //BA.debugLineNum = 546;BA.debugLine="Ir_array(ya,xa) = T_Ir(iAddr)";
_ir_array[_ya][_xa] = _t_ir[(int) (_iaddr)];
 }
};
 }
};
 }
};
 }
};
 };
 //BA.debugLineNum = 553;BA.debugLine="End Sub";
return "";
}
public static String  _camera1_ready(boolean _success) throws Exception{
 //BA.debugLineNum = 171;BA.debugLine="Sub Camera1_Ready (Success As Boolean)";
 //BA.debugLineNum = 172;BA.debugLine="If Success Then";
if (_success) { 
 //BA.debugLineNum = 173;BA.debugLine="camera1.StartPreview";
mostCurrent._camera1.StartPreview();
 }else {
 //BA.debugLineNum = 175;BA.debugLine="ToastMessageShow(\"Cannot open camera.\", True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Cannot open camera.",anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 177;BA.debugLine="End Sub";
return "";
}
public static String  _checkhuman() throws Exception{
int _x = 0;
int _count = 0;
 //BA.debugLineNum = 865;BA.debugLine="Sub checkHuman()";
 //BA.debugLineNum = 867;BA.debugLine="Dim x As Int";
_x = 0;
 //BA.debugLineNum = 868;BA.debugLine="Dim count As Int";
_count = 0;
 //BA.debugLineNum = 869;BA.debugLine="count = 0";
_count = (int) (0);
 //BA.debugLineNum = 871;BA.debugLine="For x = 0 To 63";
{
final int step711 = 1;
final int limit711 = (int) (63);
for (_x = (int) (0); (step711 > 0 && _x <= limit711) || (step711 < 0 && _x >= limit711); _x = ((int)(0 + _x + step711))) {
 //BA.debugLineNum = 873;BA.debugLine="If checkTemp(T_Ir(x)) Then";
if (BA.ObjectToBoolean(_checktemp(_t_ir[_x]))) { 
 //BA.debugLineNum = 874;BA.debugLine="count = count + 1";
_count = (int) (_count+1);
 };
 //BA.debugLineNum = 877;BA.debugLine="If count > 30 Then";
if (_count>30) { 
 //BA.debugLineNum = 883;BA.debugLine="If mp.IsPlaying Then";
if (mostCurrent._mp.IsPlaying()) { 
 //BA.debugLineNum = 884;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 886;BA.debugLine="mp.Load(File.DirAssets, \"A.mp3\")";
mostCurrent._mp.Load(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"A.mp3");
 //BA.debugLineNum = 887;BA.debugLine="mp.Play";
mostCurrent._mp.Play();
 //BA.debugLineNum = 888;BA.debugLine="Return";
if (true) return "";
 };
 }
};
 //BA.debugLineNum = 894;BA.debugLine="End Sub";
return "";
}
public static String  _checktemp(float _x) throws Exception{
int _lowboundary = 0;
int _highboundary = 0;
int _lowoffset = 0;
int _highoffset = 0;
 //BA.debugLineNum = 843;BA.debugLine="Sub checkTemp(x As Float)";
 //BA.debugLineNum = 845;BA.debugLine="Dim lowBoundary, highBoundary, lowOffset, highOffset As Int";
_lowboundary = 0;
_highboundary = 0;
_lowoffset = 0;
_highoffset = 0;
 //BA.debugLineNum = 847;BA.debugLine="lowOffset = 7";
_lowoffset = (int) (7);
 //BA.debugLineNum = 848;BA.debugLine="highOffset = 15";
_highoffset = (int) (15);
 //BA.debugLineNum = 850;BA.debugLine="lowBoundary = inputTemp + lowOffset";
_lowboundary = (int) (_inputtemp+_lowoffset);
 //BA.debugLineNum = 851;BA.debugLine="highBoundary = inputTemp + highOffset";
_highboundary = (int) (_inputtemp+_highoffset);
 //BA.debugLineNum = 853;BA.debugLine="If highBoundary > 37 Then";
if (_highboundary>37) { 
 //BA.debugLineNum = 854;BA.debugLine="highBoundary = 37";
_highboundary = (int) (37);
 };
 //BA.debugLineNum = 857;BA.debugLine="If x > lowBoundary AND x < highBoundary Then";
if (_x>_lowboundary && _x<_highboundary) { 
 //BA.debugLineNum = 858;BA.debugLine="Return True";
if (true) return BA.ObjectToString(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 860;BA.debugLine="Return False";
if (true) return BA.ObjectToString(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 863;BA.debugLine="End Sub";
return "";
}
public static String  _drawirdata() throws Exception{
 //BA.debugLineNum = 341;BA.debugLine="Sub DrawIRDATA()";
 //BA.debugLineNum = 342;BA.debugLine="MinTemp.Text = Round2(scaleMin,1)";
mostCurrent._mintemp.setText((Object)(anywheresoftware.b4a.keywords.Common.Round2(_scalemin,(int) (1))));
 //BA.debugLineNum = 343;BA.debugLine="MaxTemp.Text = Round2(scaleMax,1)";
mostCurrent._maxtemp.setText((Object)(anywheresoftware.b4a.keywords.Common.Round2(_scalemax,(int) (1))));
 //BA.debugLineNum = 345;BA.debugLine="If InterPol = True Then";
if (_interpol==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 346;BA.debugLine="Interpolation";
_interpolation();
 };
 //BA.debugLineNum = 348;BA.debugLine="End Sub";
return "";
}
public static String  _drawirpixel(int _x,int _y,int _r,int _g,int _b) throws Exception{
int _pixelh = 0;
int _pixelw = 0;
int _startposx = 0;
int _startposy = 0;
 //BA.debugLineNum = 701;BA.debugLine="Sub DrawIRPixel(x As Int, y As Int, R As Int ,G As Int, B As Int)";
 //BA.debugLineNum = 702;BA.debugLine="Dim pixelh As Int";
_pixelh = 0;
 //BA.debugLineNum = 703;BA.debugLine="Dim pixelw As Int";
_pixelw = 0;
 //BA.debugLineNum = 704;BA.debugLine="Dim startposX As Int";
_startposx = 0;
 //BA.debugLineNum = 705;BA.debugLine="Dim startposY As Int";
_startposy = 0;
 //BA.debugLineNum = 707;BA.debugLine="pixelw = Round(IRData.Width/144)";
_pixelw = (int) (anywheresoftware.b4a.keywords.Common.Round(mostCurrent._irdata.getWidth()/(double)144));
 //BA.debugLineNum = 708;BA.debugLine="pixelh = pixelw";
_pixelh = _pixelw;
 //BA.debugLineNum = 709;BA.debugLine="startposX = x * pixelw";
_startposx = (int) (_x*_pixelw);
 //BA.debugLineNum = 710;BA.debugLine="startposY = y * pixelh";
_startposy = (int) (_y*_pixelh);
 //BA.debugLineNum = 711;BA.debugLine="Rect1.Initialize(startposX, startposY, (startposX + pixelw), (startposY + pixelh))";
mostCurrent._rect1.Initialize(_startposx,_startposy,(int) ((_startposx+_pixelw)),(int) ((_startposy+_pixelh)));
 //BA.debugLineNum = 712;BA.debugLine="csvPanelView.DrawRect( Rect1, Colors.ARGB(Fade, R, G, B), True, 2dip)";
mostCurrent._csvpanelview.DrawRect((android.graphics.Rect)(mostCurrent._rect1.getObject()),anywheresoftware.b4a.keywords.Common.Colors.ARGB(_fade,_r,_g,_b),anywheresoftware.b4a.keywords.Common.True,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))));
 //BA.debugLineNum = 713;BA.debugLine="End Sub";
return "";
}
public static String  _drawpalettte(String _pstep,int _palette) throws Exception{
int _pixpalh = 0;
int _pixpalw = 0;
int _startpalposx = 0;
int _lstep = 0;
 //BA.debugLineNum = 768;BA.debugLine="Sub DrawPalettte(pstep, Palette As Int)";
 //BA.debugLineNum = 769;BA.debugLine="Dim pixPalh As Int";
_pixpalh = 0;
 //BA.debugLineNum = 770;BA.debugLine="Dim pixPalw As Int";
_pixpalw = 0;
 //BA.debugLineNum = 771;BA.debugLine="Dim startPalposX As Int";
_startpalposx = 0;
 //BA.debugLineNum = 772;BA.debugLine="Dim lstep As Int";
_lstep = 0;
 //BA.debugLineNum = 774;BA.debugLine="pixPalw = Round((PalView.Width/pstep))";
_pixpalw = (int) (anywheresoftware.b4a.keywords.Common.Round((mostCurrent._palview.getWidth()/(double)(double)(Double.parseDouble(_pstep)))));
 //BA.debugLineNum = 775;BA.debugLine="pixPalh = PalView.Height";
_pixpalh = mostCurrent._palview.getHeight();
 //BA.debugLineNum = 776;BA.debugLine="For lstep = 0 To (pstep -1)";
{
final int step640 = 1;
final int limit640 = (int) (((double)(Double.parseDouble(_pstep))-1));
for (_lstep = (int) (0); (step640 > 0 && _lstep <= limit640) || (step640 < 0 && _lstep >= limit640); _lstep = ((int)(0 + _lstep + step640))) {
 //BA.debugLineNum = 777;BA.debugLine="startPalposX = lstep * pixPalw";
_startpalposx = (int) (_lstep*_pixpalw);
 //BA.debugLineNum = 778;BA.debugLine="PixColor((lstep+1), 0, pstep, Palette)";
_pixcolor((float) ((_lstep+1)),BA.NumberToString(0),_pstep,_palette);
 //BA.debugLineNum = 779;BA.debugLine="IR_Palette((lstep+1),0) = ValR";
_ir_palette[(int) ((_lstep+1))][(int) (0)] = _valr;
 //BA.debugLineNum = 780;BA.debugLine="IR_Palette((lstep+1),1) = ValG";
_ir_palette[(int) ((_lstep+1))][(int) (1)] = _valg;
 //BA.debugLineNum = 781;BA.debugLine="IR_Palette((lstep+1),2) = ValB";
_ir_palette[(int) ((_lstep+1))][(int) (2)] = _valb;
 //BA.debugLineNum = 782;BA.debugLine="Rect2.Initialize(startPalposX, 0, (startPalposX + pixPalw), pixPalh)";
mostCurrent._rect2.Initialize(_startpalposx,(int) (0),(int) ((_startpalposx+_pixpalw)),_pixpalh);
 //BA.debugLineNum = 783;BA.debugLine="csvPalview.DrawRect( Rect2, Colors.ARGB(255, ValR, ValG, ValB), True, 2dip)";
mostCurrent._csvpalview.DrawRect((android.graphics.Rect)(mostCurrent._rect2.getObject()),anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),_valr,_valg,_valb),anywheresoftware.b4a.keywords.Common.True,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))));
 //BA.debugLineNum = 784;BA.debugLine="PalView.Invalidate2(Rect2)";
mostCurrent._palview.Invalidate2((android.graphics.Rect)(mostCurrent._rect2.getObject()));
 }
};
 //BA.debugLineNum = 786;BA.debugLine="Rect2.Initialize(0, 0, PalView.Width, pixPalh)";
mostCurrent._rect2.Initialize((int) (0),(int) (0),mostCurrent._palview.getWidth(),_pixpalh);
 //BA.debugLineNum = 787;BA.debugLine="csvPalview.DrawRect( Rect2, Colors.ARGB(255, 255, 255, 255), False, 4dip)";
mostCurrent._csvpalview.DrawRect((android.graphics.Rect)(mostCurrent._rect2.getObject()),anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (255),(int) (255),(int) (255)),anywheresoftware.b4a.keywords.Common.False,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4))));
 //BA.debugLineNum = 788;BA.debugLine="PalView.Invalidate2(Rect2)";
mostCurrent._palview.Invalidate2((android.graphics.Rect)(mostCurrent._rect2.getObject()));
 //BA.debugLineNum = 789;BA.debugLine="End Sub";
return "";
}
public static String  _drawpaletttev(String _pstep,int _palette) throws Exception{
int _pixpalh = 0;
int _pixpalw = 0;
int _startpalposx = 0;
int _lstep = 0;
 //BA.debugLineNum = 791;BA.debugLine="Sub DrawPalettteV(pstep, Palette As Int)";
 //BA.debugLineNum = 793;BA.debugLine="Dim pixPalh As Int";
_pixpalh = 0;
 //BA.debugLineNum = 794;BA.debugLine="Dim pixPalw As Int";
_pixpalw = 0;
 //BA.debugLineNum = 795;BA.debugLine="Dim startPalposX As Int";
_startpalposx = 0;
 //BA.debugLineNum = 796;BA.debugLine="Dim lstep As Int";
_lstep = 0;
 //BA.debugLineNum = 798;BA.debugLine="pixPalw = Round((PalView.Height/pstep))";
_pixpalw = (int) (anywheresoftware.b4a.keywords.Common.Round((mostCurrent._palview.getHeight()/(double)(double)(Double.parseDouble(_pstep)))));
 //BA.debugLineNum = 799;BA.debugLine="pixPalh = PalView.Width";
_pixpalh = mostCurrent._palview.getWidth();
 //BA.debugLineNum = 800;BA.debugLine="For lstep = 0 To (pstep -1)";
{
final int step661 = 1;
final int limit661 = (int) (((double)(Double.parseDouble(_pstep))-1));
for (_lstep = (int) (0); (step661 > 0 && _lstep <= limit661) || (step661 < 0 && _lstep >= limit661); _lstep = ((int)(0 + _lstep + step661))) {
 //BA.debugLineNum = 801;BA.debugLine="startPalposX = lstep * pixPalw";
_startpalposx = (int) (_lstep*_pixpalw);
 //BA.debugLineNum = 802;BA.debugLine="PixColor((pstep - lstep+1), 0, pstep, Palette)";
_pixcolor((float) (((double)(Double.parseDouble(_pstep))-_lstep+1)),BA.NumberToString(0),_pstep,_palette);
 //BA.debugLineNum = 803;BA.debugLine="IR_Palette((pstep - lstep+1),0) = ValR";
_ir_palette[(int) (((double)(Double.parseDouble(_pstep))-_lstep+1))][(int) (0)] = _valr;
 //BA.debugLineNum = 804;BA.debugLine="IR_Palette((pstep - lstep+1),1) = ValG";
_ir_palette[(int) (((double)(Double.parseDouble(_pstep))-_lstep+1))][(int) (1)] = _valg;
 //BA.debugLineNum = 805;BA.debugLine="IR_Palette((pstep - lstep+1),2) = ValB";
_ir_palette[(int) (((double)(Double.parseDouble(_pstep))-_lstep+1))][(int) (2)] = _valb;
 //BA.debugLineNum = 806;BA.debugLine="Rect2.Initialize(0, startPalposX, pixPalh, (startPalposX + pixPalw))";
mostCurrent._rect2.Initialize((int) (0),_startpalposx,_pixpalh,(int) ((_startpalposx+_pixpalw)));
 //BA.debugLineNum = 807;BA.debugLine="csvPalview.DrawRect( Rect2, Colors.ARGB(255, ValR, ValG, ValB), True, 2dip)";
mostCurrent._csvpalview.DrawRect((android.graphics.Rect)(mostCurrent._rect2.getObject()),anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),_valr,_valg,_valb),anywheresoftware.b4a.keywords.Common.True,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))));
 //BA.debugLineNum = 808;BA.debugLine="PalView.Invalidate2(Rect2)";
mostCurrent._palview.Invalidate2((android.graphics.Rect)(mostCurrent._rect2.getObject()));
 }
};
 //BA.debugLineNum = 810;BA.debugLine="Rect2.Initialize(0, 0, PalView.Width, PalView.Height )";
mostCurrent._rect2.Initialize((int) (0),(int) (0),mostCurrent._palview.getWidth(),mostCurrent._palview.getHeight());
 //BA.debugLineNum = 811;BA.debugLine="csvPalview.DrawRect( Rect2, Colors.ARGB(255, 255, 255, 255), False, 4dip)";
mostCurrent._csvpalview.DrawRect((android.graphics.Rect)(mostCurrent._rect2.getObject()),anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (255),(int) (255),(int) (255)),anywheresoftware.b4a.keywords.Common.False,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4))));
 //BA.debugLineNum = 812;BA.debugLine="PalView.Invalidate2(Rect2)";
mostCurrent._palview.Invalidate2((android.graphics.Rect)(mostCurrent._rect2.getObject()));
 //BA.debugLineNum = 813;BA.debugLine="End Sub";
return "";
}
public static String  _drawpixels() throws Exception{
int _x = 0;
int _y = 0;
float _scalep = 0f;
float _tempst = 0f;
 //BA.debugLineNum = 715;BA.debugLine="Sub DrawPixels()";
 //BA.debugLineNum = 716;BA.debugLine="Dim x As Int";
_x = 0;
 //BA.debugLineNum = 717;BA.debugLine="Dim y As Int";
_y = 0;
 //BA.debugLineNum = 718;BA.debugLine="Dim scaleP As Float";
_scalep = 0f;
 //BA.debugLineNum = 719;BA.debugLine="Dim TempSt As Float";
_tempst = 0f;
 //BA.debugLineNum = 720;BA.debugLine="ValR = 128";
_valr = (int) (128);
 //BA.debugLineNum = 721;BA.debugLine="ValG = 20";
_valg = (int) (20);
 //BA.debugLineNum = 722;BA.debugLine="ValB = 45";
_valb = (int) (45);
 //BA.debugLineNum = 723;BA.debugLine="csvPanelView.DrawColor(Colors.Transparent)";
mostCurrent._csvpanelview.DrawColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 724;BA.debugLine="For y = 0 To 35";
{
final int step597 = 1;
final int limit597 = (int) (35);
for (_y = (int) (0); (step597 > 0 && _y <= limit597) || (step597 < 0 && _y >= limit597); _y = ((int)(0 + _y + step597))) {
 //BA.debugLineNum = 725;BA.debugLine="For x = 0 To 143";
{
final int step598 = 1;
final int limit598 = (int) (143);
for (_x = (int) (0); (step598 > 0 && _x <= limit598) || (step598 < 0 && _x >= limit598); _x = ((int)(0 + _x + step598))) {
 //BA.debugLineNum = 726;BA.debugLine="If Ir_array(y,x) > (scaleMax - (scaleMax * 0.01))  Then";
if (_ir_array[_y][_x]>(_scalemax-(_scalemax*0.01))) { 
 //BA.debugLineNum = 727;BA.debugLine="If IPalette = 0 Then";
if (_ipalette==0) { 
 //BA.debugLineNum = 728;BA.debugLine="ValR = 255";
_valr = (int) (255);
 //BA.debugLineNum = 729;BA.debugLine="ValG = 255";
_valg = (int) (255);
 //BA.debugLineNum = 730;BA.debugLine="ValB = 255";
_valb = (int) (255);
 }else {
 //BA.debugLineNum = 732;BA.debugLine="ValR = 255";
_valr = (int) (255);
 //BA.debugLineNum = 733;BA.debugLine="ValG = 0";
_valg = (int) (0);
 //BA.debugLineNum = 734;BA.debugLine="ValB = 0";
_valb = (int) (0);
 };
 }else {
 //BA.debugLineNum = 737;BA.debugLine="TempSt = (scaleMax - scaleMin) / 100  	'1";
_tempst = (float) ((_scalemax-_scalemin)/(double)100);
 //BA.debugLineNum = 738;BA.debugLine="scaleP = Round((Ir_array(y,x) - scaleMin) / TempSt)	'V = 50 , 50";
_scalep = (float) (anywheresoftware.b4a.keywords.Common.Round((_ir_array[_y][_x]-_scalemin)/(double)_tempst));
 //BA.debugLineNum = 739;BA.debugLine="If scaleP <= 0 Then";
if (_scalep<=0) { 
 //BA.debugLineNum = 740;BA.debugLine="If IPalette = 0 Then";
if (_ipalette==0) { 
 //BA.debugLineNum = 741;BA.debugLine="ValR = 0";
_valr = (int) (0);
 //BA.debugLineNum = 742;BA.debugLine="ValG = 0";
_valg = (int) (0);
 //BA.debugLineNum = 743;BA.debugLine="ValB = 0";
_valb = (int) (0);
 }else {
 //BA.debugLineNum = 745;BA.debugLine="ValR = 0";
_valr = (int) (0);
 //BA.debugLineNum = 746;BA.debugLine="ValG = 0";
_valg = (int) (0);
 //BA.debugLineNum = 747;BA.debugLine="ValB = 100";
_valb = (int) (100);
 };
 }else {
 //BA.debugLineNum = 750;BA.debugLine="ValR = IR_Palette(scaleP,0)";
_valr = _ir_palette[(int) (_scalep)][(int) (0)];
 //BA.debugLineNum = 751;BA.debugLine="ValG = IR_Palette(scaleP,1)";
_valg = _ir_palette[(int) (_scalep)][(int) (1)];
 //BA.debugLineNum = 752;BA.debugLine="ValB = IR_Palette(scaleP,2)";
_valb = _ir_palette[(int) (_scalep)][(int) (2)];
 };
 };
 }
};
 }
};
 //BA.debugLineNum = 761;BA.debugLine="IRData.BringToFront";
mostCurrent._irdata.BringToFront();
 //BA.debugLineNum = 762;BA.debugLine="IRData.Invalidate";
mostCurrent._irdata.Invalidate();
 //BA.debugLineNum = 765;BA.debugLine="End Sub";
return "";
}
public static String  _eeprom_defaults() throws Exception{
byte _x = (byte)0;
 //BA.debugLineNum = 317;BA.debugLine="Sub EEPROM_Defaults";
 //BA.debugLineNum = 319;BA.debugLine="Dim x As Byte";
_x = (byte)0;
 //BA.debugLineNum = 320;BA.debugLine="Kt1 = 22.7998";
_kt1 = 22.7998;
 //BA.debugLineNum = 321;BA.debugLine="Kt2 = -0.0044441";
_kt2 = -0.0044441;
 //BA.debugLineNum = 322;BA.debugLine="VTH = 6752";
_vth = 6752;
 //BA.debugLineNum = 323;BA.debugLine="TGC = 0 '35";
_tgc = 0;
 //BA.debugLineNum = 324;BA.debugLine="Acp = -48";
_acp = -48;
 //BA.debugLineNum = 325;BA.debugLine="Bcp = -54";
_bcp = -54;
 //BA.debugLineNum = 326;BA.debugLine="Bi_scale = 8";
_bi_scale = (byte) (8);
 //BA.debugLineNum = 327;BA.debugLine="Emmission = 0.949981";
_emmission = 0.949981;
 //BA.debugLineNum = 328;BA.debugLine="Alpha_0_scale = 42";
_alpha_0_scale = 42;
 //BA.debugLineNum = 329;BA.debugLine="Alpha_d_scale = 33";
_alpha_d_scale = 33;
 //BA.debugLineNum = 330;BA.debugLine="Alpha_common = 54756/Power(2,42)";
_alpha_common = 54756/(double)anywheresoftware.b4a.keywords.Common.Power(2,42);
 //BA.debugLineNum = 331;BA.debugLine="For x = 0 To 63";
{
final int step257 = 1;
final int limit257 = (byte) (63);
for (_x = (byte) (0); (step257 > 0 && _x <= limit257) || (step257 < 0 && _x >= limit257); _x = ((byte)(0 + _x + step257))) {
 //BA.debugLineNum = 332;BA.debugLine="D_Alpha(x) = 170";
_d_alpha[(int) (_x)] = (int) (170);
 //BA.debugLineNum = 333;BA.debugLine="Ai(x) = -42";
_ai[(int) (_x)] = (int) (-42);
 //BA.debugLineNum = 334;BA.debugLine="Bi(x) = -63";
_bi[(int) (_x)] = (int) (-63);
 }
};
 //BA.debugLineNum = 336;BA.debugLine="label1.TextColor = Colors.Red";
mostCurrent._label1.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 337;BA.debugLine="label1.Text= \"D\"";
mostCurrent._label1.setText((Object)("D"));
 //BA.debugLineNum = 338;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _globals() throws Exception{
 //BA.debugLineNum = 52;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 53;BA.debugLine="Dim camera1 As Camera";
mostCurrent._camera1 = new anywheresoftware.b4a.objects.CameraW();
 //BA.debugLineNum = 54;BA.debugLine="Dim Panel2, TouchPanel, IRData As Panel";
mostCurrent._panel2 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._touchpanel = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._irdata = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Dim txtLog As EditText";
mostCurrent._txtlog = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Dim txtSend As EditText";
mostCurrent._txtsend = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Dim SeekBar1,SeekBar2,SeekBar3 As SeekBar";
mostCurrent._seekbar1 = new anywheresoftware.b4a.objects.SeekBarWrapper();
mostCurrent._seekbar2 = new anywheresoftware.b4a.objects.SeekBarWrapper();
mostCurrent._seekbar3 = new anywheresoftware.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Dim PalView, PalView2, Imageview1 As ImageView";
mostCurrent._palview = new anywheresoftware.b4a.objects.ImageViewWrapper();
mostCurrent._palview2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
mostCurrent._imageview1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Dim csvPanelView, csvPalview As Canvas";
mostCurrent._csvpanelview = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
mostCurrent._csvpalview = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Dim Rect1, Rect2 As Rect";
mostCurrent._rect1 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
mostCurrent._rect2 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Dim CheckBox1 As CheckBox";
mostCurrent._checkbox1 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Dim AUTOSCALE, InterPol As Boolean";
_autoscale = false;
_interpol = false;
 //BA.debugLineNum = 64;BA.debugLine="Dim PTAT, PIX_0, Lmin, LMax, label1, Label2, Label3, Label4 As Label";
mostCurrent._ptat = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._pix_0 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lmin = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lmax = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._label2 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._label4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 65;BA.debugLine="Dim MinTemp, MaxTemp, SpotTemp, PTATTemp, ScaleStatus, LPalette, LabAlpha  As Label";
mostCurrent._mintemp = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._maxtemp = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._spottemp = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._ptattemp = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._scalestatus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lpalette = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._labalpha = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 66;BA.debugLine="Dim EditText1, EditText2  As EditText";
mostCurrent._edittext1 = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._edittext2 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 67;BA.debugLine="Dim irVal(64) As Int";
_irval = new int[(int) (64)];
;
 //BA.debugLineNum = 68;BA.debugLine="Dim regVal(4) As Int";
_regval = new int[(int) (4)];
;
 //BA.debugLineNum = 69;BA.debugLine="Dim T_Ir(64) As Float";
_t_ir = new float[(int) (64)];
;
 //BA.debugLineNum = 70;BA.debugLine="Dim Ir_array(36,144) As Float";
_ir_array = new float[(int) (36)][];
{
int d0 = _ir_array.length;
int d1 = (int) (144);
for (int i0 = 0;i0 < d0;i0++) {
_ir_array[i0] = new float[d1];
}
}
;
 //BA.debugLineNum = 71;BA.debugLine="Dim Kt1 As Double";
_kt1 = 0;
 //BA.debugLineNum = 72;BA.debugLine="Dim Kt2 As Double";
_kt2 = 0;
 //BA.debugLineNum = 73;BA.debugLine="Dim VTH As Double";
_vth = 0;
 //BA.debugLineNum = 74;BA.debugLine="Dim TGC As Double";
_tgc = 0;
 //BA.debugLineNum = 75;BA.debugLine="Dim Acp As Double";
_acp = 0;
 //BA.debugLineNum = 76;BA.debugLine="Dim Bcp As Double";
_bcp = 0;
 //BA.debugLineNum = 77;BA.debugLine="Dim Bi_scale As Byte";
_bi_scale = (byte)0;
 //BA.debugLineNum = 78;BA.debugLine="Dim Bi(64) As Int";
_bi = new int[(int) (64)];
;
 //BA.debugLineNum = 79;BA.debugLine="Dim Ai(64) As Int";
_ai = new int[(int) (64)];
;
 //BA.debugLineNum = 80;BA.debugLine="Dim D_Alpha(64) As Int";
_d_alpha = new int[(int) (64)];
;
 //BA.debugLineNum = 81;BA.debugLine="Dim Emmission As Double";
_emmission = 0;
 //BA.debugLineNum = 82;BA.debugLine="Dim Alpha_0_scale As Double";
_alpha_0_scale = 0;
 //BA.debugLineNum = 83;BA.debugLine="Dim Alpha_d_scale As Double";
_alpha_d_scale = 0;
 //BA.debugLineNum = 84;BA.debugLine="Dim Alpha_common As Double";
_alpha_common = 0;
 //BA.debugLineNum = 85;BA.debugLine="Dim eVal(255) As Int";
_eval = new int[(int) (255)];
;
 //BA.debugLineNum = 86;BA.debugLine="Dim ValR As Int";
_valr = 0;
 //BA.debugLineNum = 87;BA.debugLine="Dim ValG As Int";
_valg = 0;
 //BA.debugLineNum = 88;BA.debugLine="Dim ValB As Int";
_valb = 0;
 //BA.debugLineNum = 89;BA.debugLine="Dim CalcX, CalcY As Float";
_calcx = 0f;
_calcy = 0f;
 //BA.debugLineNum = 90;BA.debugLine="Dim Tx, Ty, Thx, Thy As Float";
_tx = 0f;
_ty = 0f;
_thx = 0f;
_thy = 0f;
 //BA.debugLineNum = 91;BA.debugLine="Dim HoldActive, SerOn As Boolean";
_holdactive = false;
_seron = false;
 //BA.debugLineNum = 92;BA.debugLine="Dim scaleMax, scaleMin As Float";
_scalemax = 0f;
_scalemin = 0f;
 //BA.debugLineNum = 93;BA.debugLine="Dim  IPalette, IPause, IHz, AutoMode As Int";
_ipalette = 0;
_ipause = 0;
_ihz = 0;
_automode = 0;
 //BA.debugLineNum = 94;BA.debugLine="Dim Datatxt, DataCalc, Freq As String";
mostCurrent._datatxt = "";
mostCurrent._datacalc = "";
mostCurrent._freq = "";
 //BA.debugLineNum = 95;BA.debugLine="Dim btnMinPlus As Button";
mostCurrent._btnminplus = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 96;BA.debugLine="Dim btnMaxMinus As Button";
mostCurrent._btnmaxminus = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 97;BA.debugLine="Dim btnMinMinus As Button";
mostCurrent._btnminminus = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 98;BA.debugLine="Dim btnMaxPlus As Button";
mostCurrent._btnmaxplus = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 99;BA.debugLine="Dim Autoscale2, BPalette, btnPause, BtnHz, BtnCam As Button";
mostCurrent._autoscale2 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._bpalette = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpause = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnhz = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btncam = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 100;BA.debugLine="Dim refl As Reflector";
mostCurrent._refl = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 101;BA.debugLine="Dim refrIRData, ICam, RefreshTime, OldFadeCam, OldFadeNoCam As Int";
_refrirdata = 0;
_icam = 0;
_refreshtime = 0;
_oldfadecam = 0;
_oldfadenocam = 0;
 //BA.debugLineNum = 102;BA.debugLine="Dim Fade, Xoffset, Yoffset As Int";
_fade = 0;
_xoffset = 0;
_yoffset = 0;
 //BA.debugLineNum = 103;BA.debugLine="Dim Timercount As Int";
_timercount = 0;
 //BA.debugLineNum = 104;BA.debugLine="Dim Temp As String";
mostCurrent._temp = "";
 //BA.debugLineNum = 105;BA.debugLine="Dim mp As MediaPlayer";
mostCurrent._mp = new anywheresoftware.b4a.objects.MediaPlayerWrapper();
 //BA.debugLineNum = 106;BA.debugLine="Dim ID As InputDialog";
mostCurrent._id = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 107;BA.debugLine="Dim inputTemp As Float";
_inputtemp = 0f;
 //BA.debugLineNum = 108;BA.debugLine="Dim LoadId As Int";
_loadid = 0;
 //BA.debugLineNum = 109;BA.debugLine="Dim sp As SoundPool";
mostCurrent._sp = new anywheresoftware.b4a.audio.SoundPoolWrapper();
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _interpolation() throws Exception{
 //BA.debugLineNum = 555;BA.debugLine="Sub Interpolation()";
 //BA.debugLineNum = 558;BA.debugLine="InterPol = False";
_interpol = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 559;BA.debugLine="End Sub";
return "";
}
public static String  _mnuconnect_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _paireddevices = null;
anywheresoftware.b4a.objects.collections.List _l = null;
int _i = 0;
int _res = 0;
 //BA.debugLineNum = 253;BA.debugLine="Sub mnuConnect_Click";
 //BA.debugLineNum = 255;BA.debugLine="Dim PairedDevices As Map";
_paireddevices = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 256;BA.debugLine="PairedDevices = serial1.GetPairedDevices";
_paireddevices = _serial1.GetPairedDevices();
 //BA.debugLineNum = 257;BA.debugLine="Dim L As List";
_l = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 258;BA.debugLine="L.Initialize";
_l.Initialize();
 //BA.debugLineNum = 259;BA.debugLine="For I = 0 To PairedDevices.Size - 1";
{
final int step194 = 1;
final int limit194 = (int) (_paireddevices.getSize()-1);
for (_i = (int) (0); (step194 > 0 && _i <= limit194) || (step194 < 0 && _i >= limit194); _i = ((int)(0 + _i + step194))) {
 //BA.debugLineNum = 260;BA.debugLine="L.Add(PairedDevices.GetKeyAt(I))";
_l.Add(_paireddevices.GetKeyAt(_i));
 }
};
 //BA.debugLineNum = 262;BA.debugLine="Dim res As Int";
_res = 0;
 //BA.debugLineNum = 263;BA.debugLine="res = InputList(L, \"Choose device\", -1) 'show list with paired devices";
_res = anywheresoftware.b4a.keywords.Common.InputList(_l,"Choose device",(int) (-1),mostCurrent.activityBA);
 //BA.debugLineNum = 264;BA.debugLine="If res <> DialogResponse.CANCEL Then";
if (_res!=anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) { 
 //BA.debugLineNum = 265;BA.debugLine="serial1.Connect(PairedDevices.Get(L.Get(res))) 'convert the name to mac address";
_serial1.Connect(processBA,BA.ObjectToString(_paireddevices.Get(_l.Get(_res))));
 };
 //BA.debugLineNum = 267;BA.debugLine="End Sub";
return "";
}
public static String  _mnudisconnect_click() throws Exception{
 //BA.debugLineNum = 303;BA.debugLine="Sub mnuDisconnect_Click";
 //BA.debugLineNum = 305;BA.debugLine="serial1.StopListening";
_serial1.StopListening();
 //BA.debugLineNum = 306;BA.debugLine="serial1.Disconnect";
_serial1.Disconnect();
 //BA.debugLineNum = 307;BA.debugLine="connected = False";
_connected = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 308;BA.debugLine="SerOn = False";
_seron = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 309;BA.debugLine="End Sub";
return "";
}
public static String  _mnuinsconnect_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _paireddevices = null;
anywheresoftware.b4a.objects.collections.List _l = null;
int _i = 0;
int _res = 0;
 //BA.debugLineNum = 269;BA.debugLine="Sub mnuInsConnect_Click";
 //BA.debugLineNum = 271;BA.debugLine="Dim PairedDevices As Map";
_paireddevices = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 272;BA.debugLine="PairedDevices = serial1.GetPairedDevices";
_paireddevices = _serial1.GetPairedDevices();
 //BA.debugLineNum = 273;BA.debugLine="Dim L As List";
_l = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 274;BA.debugLine="L.Initialize";
_l.Initialize();
 //BA.debugLineNum = 275;BA.debugLine="For I = 0 To PairedDevices.Size - 1";
{
final int step208 = 1;
final int limit208 = (int) (_paireddevices.getSize()-1);
for (_i = (int) (0); (step208 > 0 && _i <= limit208) || (step208 < 0 && _i >= limit208); _i = ((int)(0 + _i + step208))) {
 //BA.debugLineNum = 276;BA.debugLine="L.Add(PairedDevices.GetKeyAt(I))";
_l.Add(_paireddevices.GetKeyAt(_i));
 }
};
 //BA.debugLineNum = 278;BA.debugLine="Dim res As Int";
_res = 0;
 //BA.debugLineNum = 279;BA.debugLine="res = InputList(L, \"Choose device\", -1) 'show list with paired devices";
_res = anywheresoftware.b4a.keywords.Common.InputList(_l,"Choose device",(int) (-1),mostCurrent.activityBA);
 //BA.debugLineNum = 280;BA.debugLine="If res <> DialogResponse.CANCEL Then";
if (_res!=anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) { 
 //BA.debugLineNum = 281;BA.debugLine="serial1.ConnectInsecure(admin,PairedDevices.Get(L.Get(res)), 1)";
_serial1.ConnectInsecure(processBA,_admin,BA.ObjectToString(_paireddevices.Get(_l.Get(_res))),(int) (1));
 };
 //BA.debugLineNum = 283;BA.debugLine="End Sub";
return "";
}
public static String  _mnusettings_click() throws Exception{
 //BA.debugLineNum = 313;BA.debugLine="Sub mnuSettings_Click";
 //BA.debugLineNum = 314;BA.debugLine="ToastMessageShow(\"Working on this!\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Working on this!",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 315;BA.debugLine="End Sub";
return "";
}
public static String  _palcolor(float _value,String _sclmax,int _sclmin) throws Exception{
float _scalep = 0f;
float _tempst = 0f;
 //BA.debugLineNum = 675;BA.debugLine="Sub PalColor(Value As Float, sclMax, sclMin As Int)";
 //BA.debugLineNum = 676;BA.debugLine="Dim scaleP As Float";
_scalep = 0f;
 //BA.debugLineNum = 677;BA.debugLine="Dim TempSt As Float";
_tempst = 0f;
 //BA.debugLineNum = 678;BA.debugLine="ValR = 0";
_valr = (int) (0);
 //BA.debugLineNum = 679;BA.debugLine="ValG = 0";
_valg = (int) (0);
 //BA.debugLineNum = 680;BA.debugLine="ValB = 0";
_valb = (int) (0);
 //BA.debugLineNum = 682;BA.debugLine="If Value > (sclMax - (sclMax * 0.01)) Then";
if (_value>((double)(Double.parseDouble(_sclmax))-((double)(Double.parseDouble(_sclmax))*0.01))) { 
 //BA.debugLineNum = 684;BA.debugLine="ValR = 255";
_valr = (int) (255);
 //BA.debugLineNum = 685;BA.debugLine="ValG = 255";
_valg = (int) (255);
 //BA.debugLineNum = 686;BA.debugLine="ValB = 255";
_valb = (int) (255);
 }else {
 //BA.debugLineNum = 688;BA.debugLine="TempSt = (sclMax - sclMin) / 100  	'1";
_tempst = (float) (((double)(Double.parseDouble(_sclmax))-_sclmin)/(double)100);
 //BA.debugLineNum = 689;BA.debugLine="scaleP = Round((Value - sclMin) / TempSt)	'V = 50 , 50";
_scalep = (float) (anywheresoftware.b4a.keywords.Common.Round((_value-_sclmin)/(double)_tempst));
 //BA.debugLineNum = 690;BA.debugLine="If scaleP < 0 Then";
if (_scalep<0) { 
 //BA.debugLineNum = 691;BA.debugLine="ValR = 255";
_valr = (int) (255);
 //BA.debugLineNum = 692;BA.debugLine="ValG = 255";
_valg = (int) (255);
 //BA.debugLineNum = 693;BA.debugLine="ValB = 255";
_valb = (int) (255);
 }else {
 //BA.debugLineNum = 695;BA.debugLine="ValR = IR_Palette(scaleP,0)";
_valr = _ir_palette[(int) (_scalep)][(int) (0)];
 //BA.debugLineNum = 696;BA.debugLine="ValG = IR_Palette(scaleP,1)";
_valg = _ir_palette[(int) (_scalep)][(int) (1)];
 //BA.debugLineNum = 697;BA.debugLine="ValB = IR_Palette(scaleP,2)";
_valb = _ir_palette[(int) (_scalep)][(int) (2)];
 };
 };
 //BA.debugLineNum = 700;BA.debugLine="End Sub";
return "";
}
public static String  _pixcolor(float _value,String _sclmin,String _sclmax,int _palette) throws Exception{
float _scalepos = 0f;
float _tempstep = 0f;
float _calr = 0f;
float _calg = 0f;
float _calb = 0f;
 //BA.debugLineNum = 562;BA.debugLine="Sub PixColor(Value As Float, sclMin , sclMax , Palette As Int)";
 //BA.debugLineNum = 564;BA.debugLine="Dim scalePos As Float";
_scalepos = 0f;
 //BA.debugLineNum = 565;BA.debugLine="Dim TempStep As Float";
_tempstep = 0f;
 //BA.debugLineNum = 566;BA.debugLine="Dim CalR As Float";
_calr = 0f;
 //BA.debugLineNum = 567;BA.debugLine="Dim CalG As Float";
_calg = 0f;
 //BA.debugLineNum = 568;BA.debugLine="Dim CalB As Float";
_calb = 0f;
 //BA.debugLineNum = 569;BA.debugLine="ValR = 0";
_valr = (int) (0);
 //BA.debugLineNum = 570;BA.debugLine="ValG = 0";
_valg = (int) (0);
 //BA.debugLineNum = 571;BA.debugLine="ValB = 0";
_valb = (int) (0);
 //BA.debugLineNum = 572;BA.debugLine="Select Palette";
switch (_palette) {
case 0:
 //BA.debugLineNum = 575;BA.debugLine="If Value > (sclMax - (sclMax * 0.01)) Then";
if (_value>((double)(Double.parseDouble(_sclmax))-((double)(Double.parseDouble(_sclmax))*0.01))) { 
 //BA.debugLineNum = 577;BA.debugLine="ValR = 255";
_valr = (int) (255);
 //BA.debugLineNum = 578;BA.debugLine="ValG = 255";
_valg = (int) (255);
 //BA.debugLineNum = 579;BA.debugLine="ValB = 255";
_valb = (int) (255);
 }else {
 //BA.debugLineNum = 581;BA.debugLine="TempStep = (sclMax - sclMin) / 100  	'1";
_tempstep = (float) (((double)(Double.parseDouble(_sclmax))-(double)(Double.parseDouble(_sclmin)))/(double)100);
 //BA.debugLineNum = 582;BA.debugLine="scalePos = (Value - sclMin) / TempStep	'V = 50 , 50";
_scalepos = (float) ((_value-(double)(Double.parseDouble(_sclmin)))/(double)_tempstep);
 //BA.debugLineNum = 583;BA.debugLine="CalR = Round((scalePos - 15)  * 2.55 * 2)      '50-15 = 35 * 2 * 2.55 = 70%";
_calr = (float) (anywheresoftware.b4a.keywords.Common.Round((_scalepos-15)*2.55*2));
 //BA.debugLineNum = 584;BA.debugLine="If CalR > 255 Then";
if (_calr>255) { 
 //BA.debugLineNum = 585;BA.debugLine="ValR = 255";
_valr = (int) (255);
 }else {
 //BA.debugLineNum = 587;BA.debugLine="If CalR < 0 Then";
if (_calr<0) { 
 //BA.debugLineNum = 588;BA.debugLine="ValR = 0";
_valr = (int) (0);
 }else {
 //BA.debugLineNum = 590;BA.debugLine="ValR = CalR";
_valr = (int) (_calr);
 };
 };
 //BA.debugLineNum = 593;BA.debugLine="CalG = Round((scalePos - 50)  * 2.55 * 2.0)     '0";
_calg = (float) (anywheresoftware.b4a.keywords.Common.Round((_scalepos-50)*2.55*2.0));
 //BA.debugLineNum = 594;BA.debugLine="If (scalePos - 50) > 0 Then";
if ((_scalepos-50)>0) { 
 //BA.debugLineNum = 595;BA.debugLine="If CalG > 255 Then";
if (_calg>255) { 
 //BA.debugLineNum = 596;BA.debugLine="ValG = 255";
_valg = (int) (255);
 }else {
 //BA.debugLineNum = 598;BA.debugLine="If CalG < 0 Then";
if (_calg<0) { 
 //BA.debugLineNum = 599;BA.debugLine="ValG = 0";
_valg = (int) (0);
 }else {
 //BA.debugLineNum = 601;BA.debugLine="ValG = CalG";
_valg = (int) (_calg);
 };
 };
 }else {
 //BA.debugLineNum = 605;BA.debugLine="ValG = 0";
_valg = (int) (0);
 };
 //BA.debugLineNum = 607;BA.debugLine="CalB = scalePos  * 2.55";
_calb = (float) (_scalepos*2.55);
 //BA.debugLineNum = 608;BA.debugLine="If scalePos > 50 Then";
if (_scalepos>50) { 
 //BA.debugLineNum = 609;BA.debugLine="ValB = Round((100 - scalePos) * 2.55)    'CByte(255 - CalB / 2)";
_valb = (int) (anywheresoftware.b4a.keywords.Common.Round((100-_scalepos)*2.55));
 }else {
 //BA.debugLineNum = 611;BA.debugLine="If CalB < 0 Then";
if (_calb<0) { 
 //BA.debugLineNum = 612;BA.debugLine="ValB = 0";
_valb = (int) (0);
 }else {
 //BA.debugLineNum = 614;BA.debugLine="ValB = CalB";
_valb = (int) (_calb);
 };
 };
 };
 break;
case 1:
 //BA.debugLineNum = 620;BA.debugLine="If Value > (sclMax - (sclMax * 0.01)) Then";
if (_value>((double)(Double.parseDouble(_sclmax))-((double)(Double.parseDouble(_sclmax))*0.01))) { 
 //BA.debugLineNum = 622;BA.debugLine="ValR = 255";
_valr = (int) (255);
 //BA.debugLineNum = 623;BA.debugLine="ValG = 0";
_valg = (int) (0);
 //BA.debugLineNum = 624;BA.debugLine="ValB = 0";
_valb = (int) (0);
 }else {
 //BA.debugLineNum = 626;BA.debugLine="TempStep = (sclMax - sclMin) / 100  	'1";
_tempstep = (float) (((double)(Double.parseDouble(_sclmax))-(double)(Double.parseDouble(_sclmin)))/(double)100);
 //BA.debugLineNum = 627;BA.debugLine="scalePos = Round((Value - sclMin) / TempStep)	'V = 50 , 50";
_scalepos = (float) (anywheresoftware.b4a.keywords.Common.Round((_value-(double)(Double.parseDouble(_sclmin)))/(double)_tempstep));
 //BA.debugLineNum = 628;BA.debugLine="CalR = Round((scalePos - 50)  * 2.55 * 2)      '50-15 = 35 * 2 * 2.55 = 70%";
_calr = (float) (anywheresoftware.b4a.keywords.Common.Round((_scalepos-50)*2.55*2));
 //BA.debugLineNum = 629;BA.debugLine="If (scalePos - 50) > 0 Then";
if ((_scalepos-50)>0) { 
 //BA.debugLineNum = 630;BA.debugLine="If CalR > 255 Then";
if (_calr>255) { 
 //BA.debugLineNum = 631;BA.debugLine="ValR = 255";
_valr = (int) (255);
 }else {
 //BA.debugLineNum = 633;BA.debugLine="If CalR < 0 Then";
if (_calr<0) { 
 //BA.debugLineNum = 634;BA.debugLine="ValR = 0";
_valr = (int) (0);
 }else {
 //BA.debugLineNum = 636;BA.debugLine="ValR = CalR";
_valr = (int) (_calr);
 };
 };
 };
 //BA.debugLineNum = 640;BA.debugLine="CalG = Round(scalePos * 2.55) 'Round((scalePos - 15) * 4)";
_calg = (float) (anywheresoftware.b4a.keywords.Common.Round(_scalepos*2.55));
 //BA.debugLineNum = 641;BA.debugLine="If ((scalePos - 15) > 0) AND (scalePos < 51) Then";
if (((_scalepos-15)>0) && (_scalepos<51)) { 
 //BA.debugLineNum = 642;BA.debugLine="If CalG > 255 Then";
if (_calg>255) { 
 //BA.debugLineNum = 643;BA.debugLine="ValG = 255";
_valg = (int) (255);
 }else {
 //BA.debugLineNum = 645;BA.debugLine="If CalG < 0 Then";
if (_calg<0) { 
 //BA.debugLineNum = 646;BA.debugLine="ValG = 0";
_valg = (int) (0);
 }else {
 //BA.debugLineNum = 648;BA.debugLine="ValG = CalG";
_valg = (int) (_calg);
 };
 };
 }else {
 //BA.debugLineNum = 652;BA.debugLine="If scalePos > 50 AND scalePos < 85 Then";
if (_scalepos>50 && _scalepos<85) { 
 //BA.debugLineNum = 653;BA.debugLine="ValG = 255 - Round(scalePos * 2.55)";
_valg = (int) (255-anywheresoftware.b4a.keywords.Common.Round(_scalepos*2.55));
 }else {
 //BA.debugLineNum = 655;BA.debugLine="If scalePos = 50 Then";
if (_scalepos==50) { 
 }else {
 //BA.debugLineNum = 658;BA.debugLine="ValG = 0";
_valg = (int) (0);
 };
 };
 };
 //BA.debugLineNum = 662;BA.debugLine="If scalePos > 0 AND scalePos < 50 Then";
if (_scalepos>0 && _scalepos<50) { 
 //BA.debugLineNum = 663;BA.debugLine="ValB = 255 - Round((50 + scalePos) * 2.55)";
_valb = (int) (255-anywheresoftware.b4a.keywords.Common.Round((50+_scalepos)*2.55));
 }else {
 //BA.debugLineNum = 665;BA.debugLine="If scalePos = 0 Then";
if (_scalepos==0) { 
 //BA.debugLineNum = 666;BA.debugLine="ValB = 255 ' CalB";
_valb = (int) (255);
 }else {
 //BA.debugLineNum = 668;BA.debugLine="ValB = 0";
_valb = (int) (0);
 };
 };
 };
 break;
}
;
 //BA.debugLineNum = 673;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 38;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 39;BA.debugLine="Dim serial1 As Serial";
_serial1 = new anywheresoftware.b4a.objects.Serial();
 //BA.debugLineNum = 40;BA.debugLine="Dim TextReader1 As TextReader";
_textreader1 = new anywheresoftware.b4a.objects.streams.File.TextReaderWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Dim TextWriter1 As TextWriter";
_textwriter1 = new anywheresoftware.b4a.objects.streams.File.TextWriterWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Dim Timer1, Timer2 As Timer";
_timer1 = new anywheresoftware.b4a.objects.Timer();
_timer2 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 43;BA.debugLine="Dim connected As Boolean";
_connected = false;
 //BA.debugLineNum = 44;BA.debugLine="Dim IR_Palette(120,3) As Int";
_ir_palette = new int[(int) (120)][];
{
int d0 = _ir_palette.length;
int d1 = (int) (3);
for (int i0 = 0;i0 < d0;i0++) {
_ir_palette[i0] = new int[d1];
}
}
;
 //BA.debugLineNum = 45;BA.debugLine="Dim admin As BluetoothAdmin";
_admin = new anywheresoftware.b4a.objects.Serial.BluetoothAdmin();
 //BA.debugLineNum = 46;BA.debugLine="Dim foundDevices As List";
_founddevices = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 47;BA.debugLine="Type NameAndMac (Name As String, Mac As String)";
;
 //BA.debugLineNum = 48;BA.debugLine="Dim connectedDevice As NameAndMac";
_connecteddevice = new mardaso.sensor.fir.sensor.main._nameandmac();
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public static String  _sendserialdata(String _data) throws Exception{
 //BA.debugLineNum = 205;BA.debugLine="Sub SendserialData(Data As String)";
 //BA.debugLineNum = 206;BA.debugLine="If connected = True Then";
if (_connected==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 207;BA.debugLine="TextWriter1.Write(Data & Chr(13) & Chr(10))";
_textwriter1.Write(_data+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (13)))+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (10))));
 //BA.debugLineNum = 208;BA.debugLine="TextWriter1.Flush";
_textwriter1.Flush();
 }else {
 //BA.debugLineNum = 210;BA.debugLine="Msgbox(\"Not connected.\", \"\")";
anywheresoftware.b4a.keywords.Common.Msgbox("Not connected.","",mostCurrent.activityBA);
 };
 //BA.debugLineNum = 212;BA.debugLine="End Sub";
return "";
}
public static String  _serial1_connected(boolean _success) throws Exception{
 //BA.debugLineNum = 285;BA.debugLine="Sub Serial1_Connected (Success As Boolean)";
 //BA.debugLineNum = 286;BA.debugLine="If Success Then";
if (_success) { 
 //BA.debugLineNum = 287;BA.debugLine="ToastMessageShow(\"Connected successfully\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Connected successfully",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 288;BA.debugLine="TextReader1.Initialize(serial1.InputStream)";
_textreader1.Initialize(_serial1.getInputStream());
 //BA.debugLineNum = 289;BA.debugLine="TextWriter1.Initialize(serial1.OutputStream)";
_textwriter1.Initialize(_serial1.getOutputStream());
 //BA.debugLineNum = 290;BA.debugLine="Timer1.Enabled = True";
_timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 291;BA.debugLine="Timer2.Enabled = True";
_timer2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 292;BA.debugLine="connected = True";
_connected = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 293;BA.debugLine="SerOn = True";
_seron = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 294;BA.debugLine="SendserialData(\"R001\")";
_sendserialdata("R001");
 }else {
 //BA.debugLineNum = 296;BA.debugLine="SerOn = False";
_seron = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 297;BA.debugLine="connected = False";
_connected = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 298;BA.debugLine="Timer1.Enabled = False";
_timer1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 299;BA.debugLine="Timer2.Enabled = False";
_timer2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 300;BA.debugLine="Msgbox(LastException.Message, \"Error connecting.\")";
anywheresoftware.b4a.keywords.Common.Msgbox(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),"Error connecting.",mostCurrent.activityBA);
 };
 //BA.debugLineNum = 302;BA.debugLine="End Sub";
return "";
}
public static String  _seronoff(boolean _onoff) throws Exception{
 //BA.debugLineNum = 214;BA.debugLine="Sub SerOnOff(OnOff As Boolean )";
 //BA.debugLineNum = 216;BA.debugLine="If connected = True Then";
if (_connected==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 217;BA.debugLine="If OnOff = True Then";
if (_onoff==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 218;BA.debugLine="serial1.Listen";
_serial1.Listen(processBA);
 //BA.debugLineNum = 219;BA.debugLine="SerOn = True";
_seron = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 221;BA.debugLine="serial1.StopListening";
_serial1.StopListening();
 //BA.debugLineNum = 222;BA.debugLine="SerOn = False";
_seron = anywheresoftware.b4a.keywords.Common.False;
 };
 };
 //BA.debugLineNum = 225;BA.debugLine="End Sub";
return "";
}
public static String  _timer1_tick() throws Exception{
 //BA.debugLineNum = 815;BA.debugLine="Sub Timer1_Tick";
 //BA.debugLineNum = 817;BA.debugLine="If connected = True Then";
if (_connected==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 818;BA.debugLine="If SerOn = True Then";
if (_seron==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 819;BA.debugLine="If TextReader1.Ready  Then 'check if there is any data waiting to be read";
if (_textreader1.Ready()) { 
 //BA.debugLineNum = 820;BA.debugLine="DataCalc = TextReader1.ReadLine  & CRLF";
mostCurrent._datacalc = _textreader1.ReadLine()+anywheresoftware.b4a.keywords.Common.CRLF;
 //BA.debugLineNum = 821;BA.debugLine="If  DataCalc.Length > 192 Then";
if (mostCurrent._datacalc.length()>192) { 
 //BA.debugLineNum = 822;BA.debugLine="Timer2.Enabled = False";
_timer2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 823;BA.debugLine="Calculation";
_calculation();
 //BA.debugLineNum = 824;BA.debugLine="Timer2.Enabled = True";
_timer2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 };
 };
 };
 //BA.debugLineNum = 830;BA.debugLine="End Sub";
return "";
}
public static String  _timer2_tick() throws Exception{
 //BA.debugLineNum = 832;BA.debugLine="Sub Timer2_Tick";
 //BA.debugLineNum = 835;BA.debugLine="checkHuman";
_checkhuman();
 //BA.debugLineNum = 841;BA.debugLine="End Sub";
return "";
}
}
