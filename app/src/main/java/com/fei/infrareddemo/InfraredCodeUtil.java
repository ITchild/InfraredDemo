package com.fei.infrareddemo;

public class InfraredCodeUtil {

    public static int[] normlStartCode ={9000,4500,  // 引导码
            560,560, 560,560, 560,560, 560,560,
            560,560, 560,560, 560,560, 560,560,   // 0X 00FF
            560,1690, 560,1690, 560,1690, 560,1690,
            560,1690, 565,1690, 560,1690, 560,1690} ;

    public static int[] normlEndCode = {9000,2250,2250,94000 ,9000,2250,2250,94000};


    public static String [] contorlNameArr = {"Power","Home","MENU","静/开音",
                                                "箭头","↑","上翻","音量加",
                                                "←","OK","→","音量减",
                                                "TAB","↓","下翻","返回",
                                                "1","2","3","4",
                                                "5","6","7","8",
                                                "9","0","·","删除",
                                                "点播","直播","暂停","播放"};

    public static int [] contorlCodeArr = {0x0F,0x52,0x10,0x58,
                                            0x5E,0x4D,0x11,0x5C,
                                            0x57,0x5B,0x5F,0x54,
                                            0x56,0x5A,0x12,0x53,
                                            0x17,0x1B,0x1F,0x16,
                                            0x1A,0x1E,0x15,0x19,
                                            0x1D,0x18,0x14,0x1C,
                                            0x13,0x50,0x59,0x55};



    public static int[] getArrayCode(int code){
        int[] returnCode = new int[74];
        for (int i=0;i< normlStartCode.length;i++){
            returnCode[i] = normlStartCode[i];
        }
        String hexString = Integer.toBinaryString(code);
        hexString = changeString(hexString,8);
        for (int i=0;i<hexString.length();i++){
            String flag = hexString.charAt(i)+"";
            if(flag.equals("1")){
                returnCode[i*2+34] = 560;
                returnCode[i*2+35] = 560;
                returnCode[i*2+50] = 560;
                returnCode[i*2+51] = 1690;
            }else{
                returnCode[i*2+34] = 560;
                returnCode[i*2+35] = 1690;
                returnCode[i*2+50] = 560;
                returnCode[i*2+51] = 560;
            }
        }
        for (int i=0;i<normlEndCode.length;i++){
            returnCode[i+66] = normlEndCode[i];
        }
        return returnCode;
    }

    private static String changeString(String str, int len){
        if(str.length()<len){
            str = "0"+str;
        }else{
            return str;
        }
        return changeString(str,len);
    }

}
