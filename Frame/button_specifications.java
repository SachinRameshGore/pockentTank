

public interface button_specifications{

	
	//********This is for angle buttons **************!!!!!
	
	public static int angle_label_x = 40+160-130+2;
	public static int angle_label_y = 410+120-20-5-5-3; //380+120
	
	public static int angle_x = 50+160-130+10-3-2;
	public static int angle_y = 410+120-5-5+10-3;
	
	public static int inc_ang_x = 50+160-130+30+5+7;///100+160-130
	public static int inc_ang_y = 410+120-5-5+10-3;
	
	public static int dnc_ang_x = 50+160-130-15; //20+160-130
	public static int dnc_ang_y = 410+120-5-5+10-3;
	
	//********This is for Fire button **************!!!!!
	
	public static int Fire_x = 300+160-90;
	public static int Fire_y = 410+120-15-5-5-3;
	
	//********This is for POwer button **************!!!!!

	public static int power_label_x = 480+160+10;
	public static int power_label_y = 410+120-22-5-5-3;	//380+120
	
	public static int power_x = 500+160+4;
	public static int power_y = 410+120-5-5-3+8;
	
	public static int inc_pow_x = 500+160+30+5+7; //545+160
	public static int inc_pow_y = 410+120-5-5-3+8;
	
	public static int dnc_pow_x = 500+160-15-1; //470+160
	public static int dnc_pow_y = 410+120-5-5-3+8;
	
	/*****************************************************/
	public static int drop_down_label_x = angle_label_x+115;
	public static int drop_down_label_y = angle_label_y+10;
	
	public static int drop_down_inc_x = drop_down_label_x+120+1;
	public static int drop_down_inc_y = drop_down_label_y+20;
	
	public static int drop_down_dnc_x = drop_down_label_x+120+1;
	public static int drop_down_dnc_y = drop_down_label_y;
	
	//************Start up menu *********************!!!!
	
	
	//		-***start button ***--
	public static int start_x = 300+160-190;
	public static int start_y = 100+120-50;
	
	//    --*** Help Button******
	public static int help_x = 300+160-190;
	public static int help_y = 210+120-50;
	
	//***** --- Quit Button ------******
	
	public static int quit_x = 300+160-190;
	public static int quit_y = 320+120-50;
	
	
	/******************terrain*********************/
	
	public int Pixel_size = 2;
	
	public int screen_height = 480+120;
	public int screen_width = 640+160;
	
	
	public int Width = 800;
	public int Height = 470;
	
	
	public int rows = Height/Pixel_size;
	public int cols = Width/Pixel_size;
	
	
	
	
	
	
	
	public int fieldX = 150+30,fieldY =  410+75;
	public int fieldWidth = 150,fieldHeight = 20;
	
	public int listSelectorWidth = 20,listSelectorHeight = fieldHeight;
	public int listSelectorX = (fieldX+fieldWidth)-listSelectorWidth,listSelectorY = fieldY;
	
	public int fireSelectorWidth = 20,fireSelectorHeight =20;
	public int fireSelectorX = fieldX+fieldWidth+50,fireSelectorY = fieldY;
	
	
	
	
	
	
	

}