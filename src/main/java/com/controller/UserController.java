package com.controller;

import com.com.utils.SendMail;
import com.model.BookRoom;
import com.model.CheckIn;
import com.model.Money;
import com.model.User;
import com.service.RoomService;
import com.service.UserService;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    User users;
    @Autowired
    SendMail sendMail;
    @Autowired
    RoomService roomService;
    @RequestMapping("/Login")
    //登陆
    public String login(HttpServletRequest request, HttpServletResponse response, @RequestParam("loginName") String loginName, @RequestParam("password") String password, @RequestParam("code") String code) {
        try {
            //判断用户名或密码是否为空
            if(loginName==""||password==""){
                JSONObject json = new JSONObject();
                System.out.println("用户名或密码不能为空");
                response.setContentType("text/html;charset=utf-8");
                json.put("wrongPassword", "用户名或密码不能为空");
                response.getWriter().println(json);
            }else {
                System.out.println(loginName + ":" + password + ":" + code);
                this.users.setUserName(loginName);
                this.users.setPasswordd(Integer.valueOf(password));
                User use = this.userService.login(this.users);
                String sessionCode = request.getSession().getAttribute("code").toString();
                System.out.println(sessionCode + "123");
                JSONObject json;
                if (use == null) {
                    System.out.println("用户名或密码错误");
                    response.setContentType("text/html;charset=utf-8");
                    json = new JSONObject();
                    json.put("wrongPassword", "账号或密码错误");
                    response.getWriter().println(json);
                } else if (!code.equalsIgnoreCase(sessionCode)) {
                    System.out.println("用户名和密码正确但验证码错误");
                    json = new JSONObject();
                    json.put("wrongCode", "验证码错误，请重新输入");
                    response.getWriter().println(json);
                } else {
                    System.out.println("登陆成功，我是：" + use.getNamee());
                    request.getSession().setAttribute("user", use);
                    request.getSession().setMaxInactiveInterval(600);
                    HashMap<String, Object> hashMap = new HashMap();
                    hashMap.put("userName", loginName);
                    hashMap.put("roomState", "1");
                    CheckIn checkIn = this.roomService.findCheckIn(hashMap);
                    BookRoom bookRoom = this.roomService.findBookRoom(hashMap);
                    if (null != checkIn) {
                        System.out.println("已经入住了房间");
                        request.getSession().setAttribute("myRoom", checkIn);
                    } else if (null != bookRoom) {
                        System.out.println("已经预定了房间");
                        CheckIn checkIn1 = new CheckIn();
                        checkIn1.setLocation(bookRoom.getLocation());
                        checkIn1.setCheckDay(bookRoom.getBookDay());
                        checkIn1.setDay(bookRoom.getDay());
                        checkIn1.setOutDay(bookRoom.getOutDay());
                        request.getSession().setAttribute("myRoom", checkIn1);
                    }

                    JSONObject json1 = new JSONObject();
                    json1.put("success", "登陆成功");
                    response.getWriter().println(json1);
                }
            }

        } catch (Exception var12) {
            var12.printStackTrace();
        }

        return null;
    }

    //用户注册
    @RequestMapping("/Register")
    public String register(User userRegister,HttpServletRequest request){
        try{
            //用户注册
            userRegister.setUserType("0");
            userRegister.setPeopleCode("null");
            userService.register(userRegister);
            System.out.println("注册成功了");
            //注册后把用户名和余额写入money表
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("userName",userRegister.getUserName());
            map.put("balance",new Integer(0));
            userService.userNameAndBalance(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "registerLogin";
    }

    //用户个人信息
    @RequestMapping("/balance")
    public String personalInfo(Model model,@RequestParam(value = "userName") String userName){
        try{
            Money money = userService.personalInfo(userName);
            model.addAttribute("balance",money.getBalance());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "personalInfo";
    }

    //充值的过渡
    public void chongzhi(int money,HttpServletRequest request,HttpServletResponse response){
        try{
            request.getSession().setAttribute("chizhongMoney",money);
            request.getRequestDispatcher("recharge.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //充值
    @RequestMapping("/recharge")
    public String userRecharge(Model model,HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "userName") String userName){

        try {
            Money money = userService.personalInfo(userName);
            //余额
            int balance = money.getBalance();
            //充值的金额，在上一个方法 chongzhi() 中已经从支付宝回调取得了充值的金额，保存在session中了
            int figure = (int)request.getSession().getAttribute("chizhongMoney");
            //充值后的总额
            int sumMoney = balance + figure;
            System.out.println("充值的金额:"+figure+":余额："+balance+":总额："+sumMoney);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("userName",userName);
            map.put("sumMoney",sumMoney);
            //充值
            userService.userRecharge(map);
            //充值成功后再次读取出来
            int totalMoney = userService.personalInfo(userName).getBalance();
            //放在request作用域中
            System.out.println(totalMoney);
            //利用ajax回调，利用了window.href.location来跳转页面，取不到request作用域的值
            //model.addAttribute("balance",totalMoney);
            //然后就放在了session作用域中，就可以了
            request.getSession().setAttribute("balance",totalMoney);
            JSONObject json = new JSONObject();
            json.put("success","充值成功");
            //ajax回调
            response.getWriter().println(json);
        }catch (Exception e){
            e.printStackTrace();
        }
     return null;
    }

    //修改手机号和邮箱
    @RequestMapping("/modifyPhoneAndEmail")
    public void modifyPhoneAndEmail(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam("newPhone") String newPhone, @RequestParam("newEmail") String newEmail,
                                    @RequestParam("userName") String userName){
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("userName",userName);
            map.put("newPhone",newPhone);
            map.put("newEmail",newEmail);
            userService.modifyPhoneAndEmail(map);
            //从获取session中的user(登陆时保存的)
            User users = (User)request.getSession().getAttribute("user");
            System.out.println("修改前电话："+users.getPhone()+"\t修改前邮箱："+users.getEmail());
            //使旧的user失效，因为电话和邮箱已经修改过了，user要更新
            request.getSession().invalidate();
            //修改过的user
            User use = userService.login(users);
            System.out.println("修改后电话："+use.getPhone()+"\t修改后邮箱："+use.getEmail());
            //修改后的user保存在session中，很多页面要用到，名字要和旧的一模一样
            request.getSession().setAttribute("user",use);
            //ajax回调
            JSONObject json = new JSONObject();
            json.put("modifySuccess","修改成功");
            json.put("newPhone",use.getPhone());
            json.put("newEmail",use.getEmail());
            response.getWriter().println(json);
        }catch (Exception e){
              e.printStackTrace();
        }
    }
    //取消修改手机号和邮箱
    @RequestMapping("/cancelModifyPhoneAndEmail")
    public void cancelModifyPhoneAndEmail(HttpServletResponse response,@RequestParam("userName") String userName){
      try{
          HashMap<String,Object> cancelPhoneAndEmail = userService.cancelModifyPhoneAndEmail(userName);
         // System.out.println("手机号："+cancelPhoneAndEmail.get("cancelPhone")+"：邮箱："+cancelPhoneAndEmail.get("cancelEmail"));
          //ajax回调
          JSONObject json = new JSONObject();
          json.put("cancelPhone",cancelPhoneAndEmail.get("cancelPhone"));
          json.put("cancelEmail",cancelPhoneAndEmail.get("cancelEmail"));
          response.getWriter().println(json);
      }catch (Exception e){
          e.printStackTrace();
      }
    }
    //修改密码
    @RequestMapping("/modifyPassword")
    public void modifyPassword(HttpServletResponse response,@RequestParam("newPassword") String newPassword,
                               @RequestParam("userName") String userName){
         try{
             Map<String,Object> map = new HashMap<String,Object>();
             map.put("userName",userName);
             map.put("newPassword",newPassword);
             userService.modifyPassword(map);
             //ajax回调
             JSONObject json = new JSONObject();
             json.put("modifySuccess","修改密码成功,下次登陆记得使用新密码");
             response.getWriter().println(json);
         }catch (Exception e){
             e.printStackTrace();
         }
    }

    //通过手机号找回密码和用户名
    @RequestMapping("findPasswordByPhone")
    public void findPasswordByPhone(HttpServletRequest request,HttpServletResponse response,@RequestParam("phone") String phone){
        try {
            //利用手机号找回的用户名
            String userName = userService.findUserNameByPhone(phone);
            //利用用户名找回的密码
            String password = userService.findUserPasswordByPhone(phone);

            System.out.println("您的用户名为："+userName+"\t您的密码为："+password);

            JSONObject json = new JSONObject();
            json.put("wrongPhone","该号码没有注册，请重新输入");
            //判断用户输入的电话是否正确,如果没找到数据，则说明错误
            if(userName==null){
                System.out.println("该号码没有注册，请重新输入");
                response.getWriter().println(json);
            }else{
                //用来区别时入住还是找回密码
                String sign = "用密";
                String time = "time";
                //发送短信，static方法直接用类名来调用就可以
                sendMessage(sign,userName,phone, password,time,request,response);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    //发送短信
    public static void sendMessage(String sign,String userNameOrLocation,String phone,String message,String time,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // RoomService roomservice = new RoomServiceImpl();
        //发送短信
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        //找回密码、入住和预定都需要发送短信，且短信的内容不一样，用sign区分开
        if(sign=="用密"){
            NameValuePair[] data ={ new NameValuePair("Uid", "大海知天空"),//中国网建sms平台注册注册的用户名
                    new NameValuePair("Key", "d41d8cd98f00b204e980"),//短信密钥
                    new NameValuePair("smsMob",phone),//接受短信的手机号码
                    new NameValuePair("smsText","温馨提心您，您的用户名为："+userNameOrLocation+" ，密码为："+message+"。")};//短信内容
            post.setRequestBody(data);
            client.executeMethod(post);
        }else if(sign=="房密"){
            NameValuePair[] data ={ new NameValuePair("Uid", "大海知天空"),//中国网建sms平台注册注册的用户名
                    new NameValuePair("Key", "d41d8cd98f00b204e980"),//短信密钥
                    new NameValuePair("smsMob",phone),//接受短信的手机号码
                    new NameValuePair("smsText","您入住的房间号："+userNameOrLocation+",密码为："+message+",时间为："+time+",请及时入住。")};//短信内容
            post.setRequestBody(data);
            client.executeMethod(post);
            System.out.println("房间和钥匙");
        }else{
            try {
                //获得预定的时间
               // BookRoom bookRoomInfo = roomservice.bookInfo(userNameOrLocation);
                NameValuePair[] data ={ new NameValuePair("Uid", "大海知天空"),//中国网建sms平台注册注册的用户名
                        new NameValuePair("Key", "d41d8cd98f00b204e980"),//短信密钥
                        new NameValuePair("smsMob",phone),//接受短信的手机号码
                        new NameValuePair("smsText","您预定的房间号:"+userNameOrLocation+",密码为："+message+ ",时间为："+time+",请按时入住。")};//短信内容
                post.setRequestBody(data);
                client.executeMethod(post);
                System.out.println("预定的房间和钥匙");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for(Header h : headers)
        {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result); //打印返回消息状态
        post.releaseConnection();
        //跳转到登陆界面
        if(sign=="用密"){
            System.out.println("找回密码，发送短信了！！");
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("success","找回密码成功，请登录");
            response.getWriter().println(jsonObj);
        }else if(sign=="房密"){
            System.out.println("入住房间，发送短信了！！");
            JSONObject json = new JSONObject();
            json.put("checkSuccess","成功入住!!");
            response.getWriter().println(json);
        }else{
            System.out.println("预定房间，发送短信了！！");
            JSONObject json = new JSONObject();
            json.put("bookSuccess","成功预定!!");
            response.getWriter().println(json);
        }
    }
    //通过邮箱找回密码和用户名
    @RequestMapping("/findPasswordByEmail")
    public void findPasswordByEmail(HttpServletResponse response,@RequestParam("email") String email){
        try{
            //通过邮件找回电话号码
            String phone = userService.findPhoneByEmail(email);
            String userName = null;
            String password = null;  //fe
            //如果输入的email没有注册，则不进行查询密码和手机号
            if(phone!=null){
                //利用手机号找回的用户名
                 userName = userService.findUserNameByPhone(phone);
                //利用号码找回的密码
                 password = userService.findUserPasswordByPhone(phone);
            }

            System.out.println("您的用2户名为："+userName+"\t您的1密码为："+password);
            //把基本数据类型转为json格式，回调
            JSONObject json = new JSONObject();
            //输入的email没有注册
            if(userName==null){
                json.put("wrongEmail","该邮箱没有注册，请重新输入");
                response.getWriter().println(json);
            }else{
                String emailContext = "您的用户名为："+userName+"，密码为："+password;
                sendMail.sendMail(email,emailContext);
                json.put("success","找回密码成功，请登录");
                response.getWriter().println(json);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //用户退出
    @RequestMapping("/Quit")
    public String quit(HttpServletRequest request){
        //使session失效，退出，
        request.getSession().invalidate();
        return "uindex";
    }

}
