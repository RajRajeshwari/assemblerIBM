/*
NAME: RAJ RAJESHWARI KAYAN
CLASS: B.TECH BATCH -'B'
SMART ID: BTBTC16032
EXAMINATION ROLL NO.: 10601
*/


import java.io.*;
import static java.lang.System.*;

public class assemblerprg 
{
    public static void main(String args[]) throws IOException   ///try using linked list of strings
    {
       String s,o;
       int flag=0,prg_size=0;
       BufferedReader prg = new BufferedReader(new FileReader("D:/java/JavaApplication1/assembler/myprg.txt"));
       BufferedWriter bwr = new BufferedWriter(new FileWriter("D:/java/JavaApplication1/assembler/myobj.txt"));
       while((s=prg.readLine())!=null)
       {
           s=s.trim();
           String seg[] = s.split("[\\s\\,\\(\\)]");
           //out.println(seg[0]);
           //opcode to hexadecimal using sting array...
           BufferedReader opc = new BufferedReader(new FileReader("D:/java/JavaApplication1/assembler/opcode.txt"));
           while((o=opc.readLine())!=null)
           {
               String op[] = o.split("\\s"); //here we could include the addressing modes and would have got the info in op[2] and we can use this to generalise it to RS, SI etc.
               //out.println("not yet//again?");
               if(seg[0].compareTo(op[0])==0)
               {
                   seg[0]=op[1];  //out.println(op[1]); // i will do ->
                   flag = 1;
                   break;
               }
            }
            if(flag==0)
               {
                   out.println("Not a RR or RX instruction");
                   exit(0);
               }
               else
                   flag = 0;
           //now we need to extract base, index and offset in case of rx and source and destination in case of rr.
           //out.println(seg.length);   if rx->size=5 else size = 3
           if(seg.length==3)
           {
               prg_size+=2; 
               seg[1]=Integer.toHexString(Integer.parseInt(seg[1]));
               seg[2]=Integer.toHexString(Integer.parseInt(seg[2]));
           }
           else
           {
               prg_size+=4; //in bytes
               //seg[0] is sorted, nothing to do in it.
               //seg[3] needs to be send to the end and also converted to hexadecimal equivalent...
               String temp = Integer.toHexString(Integer.parseInt(seg[2]));
               seg[1]=Integer.toHexString(Integer.parseInt(seg[1]));
               seg[2]=Integer.toHexString(Integer.parseInt(seg[3]));
               seg[3]=Integer.toHexString(Integer.parseInt(seg[4]));
               seg[4]=temp;
           }
          for(int a=0;a<seg.length;a++)  //checking output
              bwr.write(seg[a].toUpperCase());
          bwr.newLine();
          opc.close();
        }
       bwr.close();
       prg.close();
       out.println("Size of program: "+prg_size);
    }
}
