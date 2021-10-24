package findPassword;

import com.com.utils.SendMail;

public class send {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SendMail sendMail = new SendMail();
        String email = "835114627@qq.com";
        String context = "helloWorld"+"，你好世界！！";
        sendMail.sendMail(email,context);
    }

}
