package muti_network;


import java.awt.Color;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;



import muti_network.TTS;



public class MultiClientThread extends Thread{
    private MultiClient mc;
    
    public MultiClientThread(MultiClient mc){
        this.mc = mc;
    }
    
 
    public void run(){
        String message = null;
        String[] receivedMsg = null;
        
        boolean isStop = false;
        while(!isStop){
            try{
                message = (String)mc.getOis().readObject();
                receivedMsg = message.split("#");//내가 텍스트를 보낸다.
                
                TTS tts = new TTS();
                		tts.setInput(receivedMsg[0] + "의 메세지입니다." + receivedMsg[1]);
                		tts.main(null);
                		
                		//if(receivedMsg[0].equals(anObject))
            }catch(Exception e){
                e.printStackTrace();
                isStop = true;
            }
            System.out.println(receivedMsg[0]+","+receivedMsg[1]); //콘솔창에찍히는부분
            if(receivedMsg[0].equals(mc.getId())) {
            	mc.getJta().setForeground(new Color(13, 242, 5));
            } else {
            	mc.getJta().setForeground(new Color(242, 116, 5));
            }
            if(receivedMsg[1].equals("exit")){
                if(receivedMsg[0].equals(mc.getId())){
                    mc.exit();
                }else{
                    mc.getJta().append(
                    receivedMsg[0] +"님이 종료 하셨습니다."+
                    System.getProperty("line.separator"));
                    mc.getJta().setCaretPosition(
                    mc.getJta().getDocument().getLength());
                }
            }else if(receivedMsg[1].equals("change")){               
                mc.changepower=true;
                mc.getJta().append("바꿀 아이디를 입력하세요"+ System.getProperty("line.separator"));
                String name = receivedMsg[1];
                mc.SetName(name);
            }else if(receivedMsg[1].equals("clear")){               
            	mc.Clear();
            }else if(receivedMsg[0].equals(mc.getId())){
            	mc.getJta().append(
            			receivedMsg[0] +" : "+receivedMsg[1]+
                        System.getProperty("line.separator"));
                        mc.getJta().setCaretPosition(
                            mc.getJta().getDocument().getLength());  
            	
            }else if(receivedMsg[1].equals("/r")) { //
                if (receivedMsg[2].equals(mc.getId())) { 
                    mc.getJta().append("도착"+receivedMsg[0]+" : "+receivedMsg[3] + System.getProperty("line.separator"));
                 }
                 /*if (receivedMsg[0].equals(mc.getId())) {
                    mc.getJta().append("보냄"+receivedMsg[2]+" : "+receivedMsg[3] + System.getProperty("line.separator"));
                 }*/

            }else if(receivedMsg[0].equals("list")){            	
            	int len =receivedMsg.length-1;
            	String numStr2 = String.valueOf(len);
            	mc.getJta().append("현재접속인원 :"+numStr2+System.getProperty("line.separator"));
            	for(int i=0;i<receivedMsg.length;i++){
            		mc.getJta().append(receivedMsg[i]+System.getProperty("line.separator"));
            	}       
            }else if(receivedMsg[1].equals("칼퇴")){               
                mc.getJta().append("이 메시지는 부적절합니다."+ System.getProperty("line.separator"));
                
            }else{               
                mc.getJta().append(
                receivedMsg[0] +" : "+receivedMsg[1]+
                System.getProperty("line.separator"));
                mc.getJta().setCaretPosition(
                    mc.getJta().getDocument().getLength());     
            }
        }
    }
}