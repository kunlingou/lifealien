- <https://blog.csdn.net/qq_34763699/article/details/78650272> 

一、十六进制（Hex）与字节（byte）的概念
十六进制（Hex）：计算机中数据的一种表示方法，它由0-9，A-F组成，字母不区分大小写。与10进制的对应关系是：0-9对应0-9；A-F对应10-15。

字节（byte）：   java中一个byte为8个二进制位。

转换原理：

    每个二进制位有两种状态，分别为0,1

    因此，两个二进制位有4种状态，分别为：00,01,10,11

    三个二进制位有8种状态，分别为000,001,010,011,100,101,110,111

    四个二进制位有十六种状态，0000,0001......1110,1111.   即十六进制

 

    一个十六进制数（Hex），正好为4个二进制位。一个字节（byte）为8个二进制位。因此，一个字节可表示为两个十六进制数字。

    因此，我们可以将一个byte用两个Hex表示，同理，我们也可以将两个Hex转换为一个byte。

二、Java中Hex与byte的相互转换
在java中，很多地方经常需要进行byte与Hex之间的转换，比如：某些加密的过程（MD5），通信的过程（TCP）。

1.byte转Hex
/** 
 * 字节转十六进制 
 * @param b 需要进行转换的byte字节 
 * @return  转换后的Hex字符串 
    */  
    public static String byteToHex(byte b){  
    String hex = Integer.toHexString(b & 0xFF);  
    if(hex.length() < 2){  
        hex = "0" + hex;  
    }  
    return hex;  
    }  
    很多时候，我们需要转换的是一个byte数组，一个一个byte调用上面的方法显然太麻烦。
    /** 
 * 字节数组转16进制 
 * @param bytes 需要转换的byte数组 
 * @return  转换后的Hex字符串 
    */  
    public static String bytesToHex(byte[] bytes) {  
    StringBuffer sb = new StringBuffer();  
    for(int i = 0; i < bytes.length; i++) {  
        String hex = Integer.toHexString(bytes[i] & 0xFF);  
        if(hex.length() < 2){  
            sb.append(0);  
        }  
        sb.append(hex);  
    }  
    return sb.toString();  
    }  
    2.Hex转byte
    需注意的是，Hex的字符串必须为十六进制的字符，否则会抛出异常。Hex的范围为0x00到0xFF。

/** 
 * Hex字符串转byte 
 * @param inHex 待转换的Hex字符串 
 * @return  转换后的byte 
    */  
    public static byte hexToByte(String inHex){  
       return (byte)Integer.parseInt(inHex,16);  
    }  
    如果Hex超过0xFF，显然转换后结果不是一个byte，而是一个byte数组
    /** 
 * hex字符串转byte数组 
 * @param inHex 待转换的Hex字符串 
 * @return  转换后的byte数组结果 
    */  
    public static byte[] hexToByteArray(String inHex){  
    int hexlen = inHex.length();  
    byte[] result;  
    if (hexlen % 2 == 1){  
        //奇数  
        hexlen++;  
        result = new byte[(hexlen/2)];  
        inHex="0"+inHex;  
    }else {  
        //偶数  
        result = new byte[(hexlen/2)];  
    }  
    int j=0;  
    for (int i = 0; i < hexlen; i+=2){  
        result[j]=hexToByte(inHex.substring(i,i+2));  
        j++;  
    }  
    return result;   
    }  

都是静态方法，复制粘贴直接用就可以了。喜欢的同学请点赞
