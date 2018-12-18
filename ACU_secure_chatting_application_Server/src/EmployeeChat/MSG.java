/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package EmployeeChat;


import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author adelElawady
 */
public class MSG  implements Serializable{
    public Date SendDate;
    public Date ReciveDate;
    public String MsgContent;
    public Object OtherObject;
    public String Recipient;
    public String Sender;
    public String EncryptionTYPE;
    public String ID;
    
    public MSG(Date SD ,Date RD,String MSGContent,Object OBJ,String Recip,String SeNDR){
        SendDate=SD;
        ReciveDate=RD;
        MsgContent=MSGContent;
        OtherObject=OBJ;
        Recipient=Recip;
        Sender=SeNDR;
        ID=Sender+"+"+new Random().nextInt(99999);
    }
    
    
    
}
