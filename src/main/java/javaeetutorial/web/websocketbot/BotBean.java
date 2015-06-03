/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.web.websocketbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import javax.inject.Named;
import javax.swing.JOptionPane;

@Named
public class BotBean {

    private static List<String> lst = new ArrayList<String>();  //lst用来存数据文件"药品信息.txt"中的每一行内容（即一种药物）
    private static List<String> medicine = new ArrayList<String>(); //medicine用来存储药物名称中具有实际意义的部分(数据文件中被'#'包围的内容)，下面字符串匹配时会用到

    //private static List<String> medicine = new ArrayList<String>();
    static {
//        lst.add("品名：阿莫西林胶囊 规格：0.25g 单位：24盒 制药企业：安徽安科恒益药业有限公司 零售价：￥4.8");
//        medicine.add("阿莫西林");
//        lst.add("品名：阿司匹林片 规格：25mg*100s	单位：瓶	零售价:￥0.57");
//        medicine.add("阿司匹林");
//        lst.add("品名：安乃近片 规格：0.5g 单位：支 制药企业：石药集团中诺药业(石家庄)有限公司 ￥1.1");
//        medicine.add("安乃近");
//        lst.add("品名：复方板蓝根颗粒 规格：15g*20袋 零售价：￥3.85 ");
//        medicine.add("板蓝根");
//        lst.add("品名：布洛芬 规格：0.1g(10万IU) 单位：100瓶 制药企业：江苏正大丰海制药有限公司 零售价：￥13.6");
//        medicine.add("布洛芬");
//        lst.add("品名：复方阿胶浆 规格：20ml*12 单位：盒 零售价：￥32.50 ");
//        medicine.add("复方阿胶");
//        lst.add("品名：黄氏响声丸 规格：400s 单位：瓶 零售价：￥14.31 ");
//        medicine.add("黄氏响声");
//        lst.add("品名：藿香正气水 规格：10ml*10支 单位：盒 零售价：￥1.26 ");
//        medicine.add("藿香正气");
//        lst.add("品名：急支糖浆 规格：200ml 单位：瓶 零售价：￥12.83 ");
//        medicine.add("急支糖浆");
//        lst.add("品名：颈复康颗粒 规格：5g*10袋 单位：盒 零售价：￥22.38");
//        medicine.add("颈复康");
//        lst.add("品名：六味地黄丸 规格：200s 单位：瓶 零售价：￥12.49 ");
//        medicine.add("六味地黄");
//        lst.add("品名：葡萄糖注射液 规格：20ml:10g	单位：支	零售价：￥0.26");
//        medicine.add("葡萄糖");
//        lst.add("品名：头孢拉定颗粒 规格：0.125g*12s 单位：盒 零售价：￥2.99 ");
//        medicine.add("头孢拉定");
//        lst.add("品名：云南白药胶囊 规格：0.25*16s	单位：盒	零售价：￥18.39");
//        medicine.add("云南白药");
//        lst.add("品名：正红花油 规格：20ml 单位：瓶 零售价：￥3.89");
//        medicine.add("红花油");
//        lst.add("品名：左氧氟沙星片 规格：0.1g*6s	单位：盒	零售价：￥7.90");
//        medicine.add("氧氟沙星");
//        lst.add("品名：复方氨酚烷胺片(感康) 规格：12片　单位：盒 零售价：￥10.50");
//        medicine.add("感康");
//       for(String str : lst)
//       {
//           medicine.add(str.split(" ")[0].substring(3));
//       }
        try {
            //System.out.println("***************************************");
            String encoding = "utf-8";
//           FileWriter fw = new FileWriter("test.txt");
//           fw.write("Hello world!!!\n");
//           System.out.println("文件写入完成...");
//           if(fw != null)
//           {
//               fw.close();
//           }
            //File file = new File("../applications/__internal/websocketbot/药品信息.txt");
//            System.out.println("*********************************************************");
//            String str = "*半夏糖*浆 100ml 瓶 1.30";
//            System.out.println(str.substring(str.indexOf("*") + 1, str.lastIndexOf("*")));
//            System.out.println("*********************************************************");
            File file = new File("药品信息.txt");   //读取数据文件，注意文件存放路径，不然系统会找不到文件！！！正确路径为：glassfish安装目录下的domains/domain1/config/药品信息.txt，例如：(D:\Program Files\glassfish-4.1\glassfish\domains\domain1\config)
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = "";
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    lst.add(lineTxt);   //向lst逐行追加文件内容
                    //String str2 = lineTxt.substring(lineTxt.indexOf("*") + 1, lineTxt.lastIndexOf("*"));
                    //System.out.println(lineTxt.substring(lineTxt.indexOf("*") + 1, lineTxt.lastIndexOf("*")));
                    medicine.add(lineTxt.substring(lineTxt.indexOf("#") + 1, lineTxt.lastIndexOf("#")));    //medicine中的内容为两个'#'之间的部分
                    //System.out.println(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
//    	public static List<String> truncatePhrase(String str)
//	{
//		List<String> lst = new ArrayList<String>();
//		//Set set = new HashSet<>();
//		for(int len = 2; len < str.length(); len++)
//		{
//			for(int j=0;j< str.length()-len+1;j++)
//			{
//				String str2 = str.substring(j, j+len);
//				if(str2.length() <= 2)
//					continue;
//				lst.add(str2);
//			}
//		}
//		return lst;
//	}

//    public static boolean isAllSame(int arr[],int len)
//    {
//        boolean flag = true;
//        int val = arr[0];
//        for(int i = 1; i < len; i++)
//        {
//                if(arr[i] != val)
//                {
//                    flag = false;
//                    break;
//                }
//        }
//        return flag;
//    }
//    public static String postProcess(String input,String response)
//    {
//        System.out.println(response);
//        if(response == null || response.equals(""))
//            return "";
//       String[] str = response.split("\t");
//       int length = str.length;
//       if(length == 4)
//           return response;
//       String[] name = new String[length/4];
//       int arr_count[] = new int[length/4];
//       int k = 0;
//       for(int i = 0; i < length;i++)
//       {
//           if(i % 4 == 0)
//               name[k++] = str[i].substring(3);
//       }
//       int count = 0;
//      for(int j = 0; j<k;j++)
//      {
//          count = 0;
//          for(int n = 0; n < input.length();n++)
//          {
//              if(name[j].contains(input.substring(n, n+1)))
//                  count++;
//          }
//          arr_count[j]=count;
//      }
//      int max_count = 0;
//      int pos = 0;
//      String rst = "";
//      int space = 0;
//      char ch = ' ';
//      if(isAllSame(arr_count,arr_count.length))
//        return response;
//      else
//      {
//          for(int p = 0; p < k;p++)
//          {
//              if(arr_count[p]>max_count)
//              {
//                  max_count = arr_count[p];
//                  pos = p;
//              }
//          }
//          int start = response.indexOf(name[pos]);
//          while(start < response.length())
//          {
//              ch = response.charAt(start);
//              rst += ch;
//              start++;
//              if(ch == '\t')
//                  space++;
//              if(space == 4)
//                  break;
//          }
//          return rst;
//      }
//    }
//    public static float matchRatio(String str1, String str2) {
//        int count = 0;
//        for (int i = 0; i < str1.length(); i++) {
//            if (str2.contains(str1.substring(i, i + 1))) {
//                count++;
//            }
//        }
//        return (float)count/(str2.length()>str1.length()?str2.length():str1.length());
//    }
    public static String isSimilar(String str1, String str2) {  //该函数用来判断两个字符串是否相似。str1表示用户输入的内容（不一定是正确的药物名称），
        //str2必须是medicine中的元素（药品名称中具有实际意义的部分），例如：用户输入"板的的蓝的的根的呀"("蓝"字错，应为“兰”，且药品名称被无意义的字符打散)，该函数可以判断出正确的药物名称为“板兰根”
        int count = 0;
        String temp = "";
        String tst = str2;
        for (int i = 0; i < str1.length(); i++) {
            temp = str1.substring(i, i + 1);
            if (str2.contains(temp)) {//计算用户输入中被正确的药物名称包含的字数，如上面，用户输入中被“板兰根”包含的字数为3
                count++;
                str1 = str1.replaceFirst(temp, "");
                str2 = str2.replaceFirst(temp, "");
            }
        }
        //System.out.println(count);
        if (tst.length() != 2) {   //分情况，正确的药物名称的字数不等于2时
            if ((float) count / tst.length() >= 0.5) {     //此种情况下，用户输入中被正确的药物名称包含的字数占正确药物名称的比例应大于50%，这样认为这两个字符串相似，判断用户想输入的应为str2
                return tst;
            }
        } else {    //正确的药物名称的字数等于2时，比如正确药物名称为“感康”
            if (count == 2) {   //此时，用户输入中被正确的药物名称包含的字数必须也等于2才认为这两个字符串相似，即用户输入中必须同时包含“感”和“康”两个字（排除这样一种情况：用户输入“感冒”，系统判断用户想输入“感康”）
                return tst;
            }
        }
        return null;
    }

    public List<String> truncatePhrase(String str) { //该函数用来将用户输入切分成不同长度的字符串，比如用户输入“感康多少钱”，该函数返回一个List，List中的元素分别为：
        //“感康”、“康多”、“多少”、“少钱”、“感康多”、“康多少”、“多少钱”、“感康多少”、“康多少钱”、“感康多少钱”，该函数主要为了从用户输入中找出正确的药物名称
        List<String> lst = new ArrayList<String>();
        // Set set = new HashSet<>();
        for (int len = 2; len <= str.length(); len++) {
            for (int j = 0; j < str.length() - len + 1; j++) {
                String str2 = str.substring(j, j + len);
                if (str2.length() < 1) {
                    continue;
                }
                lst.add(str2);
            }
        }
        return lst;
    }

    public int getSeqNum(String str) { //该函数用来从用户输入中获取数字（序列号）
        int seq = 0;
        int max = 0;
//        List<String> lst = new ArrayList<String>();
        // Set set = new HashSet<>();
        for (int len = 1; len <= str.length(); len++) { //与truncatePhrase函数的唯一区别就在于对len的初始化，这里=1
            for (int j = 0; j < str.length() - len + 1; j++) {
                String str2 = str.substring(j, j + len);
                if (isNumeric(str2)) {
                    seq = Integer.parseInt(str2);
                    if (seq > max) {
                        max = seq;
                    }
                }
                if (str2.length() < 1) {
                    continue;
                }
            }
        }
        return max;
    }

    public String strFormat(String line) {  //格式化字符串函数，需要去除从原始数据文件中读到的'#'，同时添加一些显示辅助信息，比如“品名”、“规格”等
        String rst = "";
        if (line == null || line.equals("")) {
            return rst;
        }
        String str[] = line.replace("#", "").split(" ");
        rst += "<品名：" + str[0] + '\t';
        rst += "规格：" + str[1] + '\t';
        rst += "单位：" + str[2] + '\t';
        rst += "零售价：￥" + str[3] + ">";
        return rst;
    }

    public String findMatchedString(List<String> lst, String str) { //该函数用来从lst中找到包含正确的药物名称str的那条记录的内容
        String targetStr = "";
        if (str.equals("")) {
            return targetStr;
        }
        String temp = "";
        int i = 1;
        for (String string : lst) {
            string = string.replace("#", "");
            String var = string.split(" ")[0];
            if (var.contains(str)) {
                if (!temp.contains(string)) {
                    //string = i + string;
                    temp += string;
                    targetStr += i + "." + strFormat(string) + "\t";
                    //break;
                }
            }
            i++;
        }
        return targetStr;
    }

    public boolean isNumeric(String str) //判断给定字符串是否是纯数字
    {
        if (str.matches("[0-9]+")) {
            return true;
        }
        return false;
    }

    public String getPartialInfo(String msg, String response) //从整条药物记录中提取部分字段，以满足用户的个性化需求
    {
        if (response.equals("")) {
            return "";
        }
        if (!msg.contains("名称") && !msg.contains("名字") && !msg.contains("全名") && !msg.contains("规格") && !msg.contains("单位") && !msg.contains("价格") && !msg.contains("零售价") && !msg.contains("价钱") && !msg.contains("多少钱")) {
            return response;
        } else {
            String rst = "";
            int i = 0;
            String tmp = "";
            String[] temp = response.split("<");
            for (int k = 1; k < temp.length; k++) {
                String[] tst = temp[k].split("\t");
                //if (!msg.contains("名称") && !msg.contains("名字") && !msg.contains("全名")) {
                rst += "<" + tst[0] + "\t";
                //}
//                if (msg.contains("名称") || msg.contains("名字")) {
//                    rst += "<" + tst[0] + "\t";
//                    i++;
//                }
                if (msg.contains("规格")) {
                    rst += tst[1] + "\t";
                    i++;
                }
                if (msg.contains("单位")) {
                    rst += tst[2] + "\t";
                    i++;
                }
                if (msg.contains("价格") || msg.contains("零售价") || msg.contains("价钱") || msg.contains("多少钱")) {
                    rst += tst[3].replace(">", "") + "\t";
                    i++;
                }
                rst = rst.substring(0, rst.length() - 1); //删除文本内容与">"之间的tab空格
                rst += ">\t";
                if (tst.length == 5) {
                    rst += tst[4];
                }
                //tmp = tst[tst.length-1];
            }
            rst = temp[0] + rst;
            return rst;
        }

    }

    public boolean containNumber(String str) //判断给定字符串中是否包含数字
    {
        char ch = ' ';
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    /* Respond to a message from the chat */
    public String respond(String msg) {
        String response;

        /* Remove question marks */
        msg = msg.toLowerCase().replaceAll("\\?", "");//移除用户输入中的?
        msg = msg.replace(" ", ""); //移除用户输入中的空格

        //int seq_num = 0;    //初始化序列号

        /*if (isNumeric(msg)) {   //判断用户是否输入了一个序列号
         int ind = Integer.parseInt(msg);
         if (ind >= 1 && ind <= lst.size()) {
         response = strFormat(lst.get(ind - 1).toString().trim());
         } else {
         response = "您输入的药品序列号不在合法范围内(1~" + lst.size() + ")，请核对后重新输入！";
         }
         } else*/
        if (containNumber(msg)) //用于处理用户输入为”66 价格“这种情况（序列号+字段）
        {
            int ind = getSeqNum(msg);
            if (ind >= 1 && ind <= lst.size()) {
                response = ind + "." + strFormat(lst.get(ind - 1).toString().trim());
                response = getPartialInfo(msg, response);
            } else {
                response = "您输入的药品序列号不在合法范围内(1~" + lst.size() + ")，请核对后重新输入！";
            }
        } else {
            response = findMatchedString(lst, msg);  //如果用户输入内容msg是正确的药物名称
            response = getPartialInfo(msg, response);

            List phrase = null;
            if (response.equals("")) //用户输入不是正确的药物名称
            {
                phrase = truncatePhrase(msg);  //先切分用户输入的内容，找到正确的药物名称，注意，这个if语句块只能处理用户输入中包含了正确的药物名称，而且他们之间是紧密相连的情况，比如
                //用户输入“我想知道感康的价格”，此时这个代码段可以处理，但如果正确的药物名称分散在用户输入的内容中，比如用户输入"感冒的康的药"，则需要下一个if语句块处理
                for (int i = 0; i < phrase.size(); i++) {
                    String rst = findMatchedString(lst, phrase.get(i).toString().trim());//对于每一个切分出来的字符串，都去lst中查找，看能否找到对应的药物信息
                    if (!rst.equals("")) {//说明当前切分出来的字符串是正确的药物名称
                        if (!response.contains(rst.split("\t")[0])) {//如果这条药物记录信息还未被添加到response中，则添加
                            response += rst + "\t";
                            System.out.println(response);
                        }
                    }
                }
                response = getPartialInfo(msg, response);
            }

            //Scanner sc = new Scanner(System.in);
            //JOptionPane jop = new JOptionPane();
//            int k = 0;
            if (response.equals("")) //如果程序进入这个区段，说明前两步都还没有找到正确的药物信息，此时正确的药物名称分散在用户输入的内容中
            {
                //for (int i = 0; i < phrase.size(); i++) {
                for (String st : medicine) {
                    //String tst = phrase.get(i).toString().trim();
                    //if (isSimilar(tst, st) != null) {
                    if (isSimilar(msg, st) != null) {
//                        if (k == 0) {
//                            response = "根据您的输入，系统已为您筛选出相近词条信息. ";
//                        }
                        System.out.println("inside here" + msg + "\t" + st);
                        String line = findMatchedString(lst, st);
                        if (!line.equals("")) {
                            if (!response.contains(line)) {
                                response += line + "\t";
                                //System.out.println(response);
                            }
//                            k++;
                        }
                    }
                }
                response = getPartialInfo(msg, response);
                if (!response.equals("")) {
                    response = "根据您的输入，系统已为您筛选出相近词条信息.\t" + response;
                }
//                System.out.println("********************************");
//                System.out.println("440输出结果：" + response);
//                System.out.println("********************************");
            }

            if (response.equals("")) {
                if (msg.contains("药品零售价")) {
                    response = "请输入您要查询的药物名称 ...";
                } else if (msg.contains("帮助")) {
                    response = "尊敬的用户，您可以选择以下3种方式之一进行查询：\n";
                    response += "1. 输入药品全名（精确匹配）或缩写（模糊查询），例如：\"阿莫西林\"或\"感康\";\n";
                    response += "2. 输入药品序列号，例如：\"126\"（序列号范围：1~" + lst.size() + ");\n";
                    response += "3. 输入药品名称或序列号同时加上字段名称（如\"全名\"、\"规格\"、\"单位\"、\"价格\"），例如：\"感康的规格和价格\"或者\"126的单位\";";
                    response += "如欲查询系统数据中的药物名称和序列号，请输入\"药品名称\"或\"序列号\".";
                } else if ((msg.contains("药品名称") || msg.contains("序列号")) || msg.contains("哪些") || msg.contains("什么") || msg.contains("全部") || msg.contains("所有")) {
                    response = "药品总计" + lst.size() + "种，分别为：\n";
                    for (int i = 0; i < lst.size(); i++) {
                        response += "序列号" + (i + 1) + ". " + lst.get(i).split(" ")[0].replace("#", "") + "\t\t";
                        if (i % 3 == 2) {
                            response += '\n';
                        }
                    }
                } else {
                    response = "输入有误！您可输入\"帮助\"查看系统帮助信息！";
                }
            }
        }
        try {
            Thread.sleep(1200);
        } catch (InterruptedException ex) {
        }
        return response;
    }
}
