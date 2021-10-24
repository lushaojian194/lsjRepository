package com.controller;

import com.model.*;
import com.service.RoomService;
import com.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@SessionAttributes(value = {"roomDetail","balance","myRoom"}) //把request作用域的roomDetail对象同时放入session作用域中
@Controller
@RequestMapping("/find")
public class RoomController {
   @Autowired
   private RoomService roomService;
   @Autowired
   private UserService userService;
   @Autowired
   private RoomRecord roomRecord;
   @Autowired
   private star appraise;
    //推荐的房间
    @RequestMapping("/Room")
    public void findRoom(HttpServletResponse response, @RequestParam(value="end") String endIndex) throws Exception{
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("roomState","0");  //房间状态为0，为1的已经入住
            map.put("times","times");  //按照入住次数来推荐
            map.put("end",new Integer(endIndex)); //推荐房间的个数
            List<Room> roomList = roomService.findRoom(map);
            System.out.println("controller方法");
            //设置字符集
            response.setContentType("text/html;charset=utf-8");
            //集合转换为json数组
            JSONArray jsonArray = JSONArray.fromObject(roomList);
            //把json的数组发送到页面,回调
            response.getWriter().println(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
        }
   }

    //房间评价
    @RequestMapping("/Star")
    public String findStar(Model model) throws Exception{
        try{

            List<star> stars = roomService.findStar();
            model.addAttribute("stars", stars);
            for(int i=0;i<stars.size();i++){
                System.out.println("姓名："+stars.get(i).getNamee());
            }
            System.out.println("房间评价方法");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "evaluate";
    }
    //我的房间评价
    @RequestMapping("/personalStar")
    public String findPersonalStar(Model model,@RequestParam(value="userName") String userName) throws Exception{
        try{
            System.out.println("用户名："+userName);
            List<star> stars = roomService.findPersonalStar(userName);
            model.addAttribute("stars", stars);
            for(int i=0;i<stars.size();i++){
                System.out.println("姓名："+stars.get(i).getNamee());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "evaluate";
    }
    //住房后的评价
    @RequestMapping("/myStars")
    public void myStar(HttpServletResponse response,Model model,@RequestParam(value="userName") String userName,@RequestParam(value="roomType") String roomType,@RequestParam(value="outDay") String outDay
    ,@RequestParam(value="stars") int stars,@RequestParam(value="evaluate") String evaluate) throws Exception{
        try{
            System.out.println("1234679878");
            int darkStats = 5-stars;
            System.out.println(userName+":"+roomType+":"+outDay+":"+stars+":"+evaluate+":"+darkStats);
            appraise.setNamee(userName);
            appraise.setOutDay(outDay);
            appraise.setRoomType(roomType);
            appraise.setEvaluate(evaluate);
            appraise.setStarLight(stars);
            appraise.setStarDark(darkStats);
            roomService.myStars(appraise);
            //因为登陆时把myRoom存放到了session中，退房了，要清除，不然在我的房间还会显示
            //用了很多方法都不行，还是直接用一个空的对象(属性要一致)，覆盖掉session中的myRoom对象
            //然后在roomDetail.jsp页面用 ${myRoom.location}!=" " 来判断是否预定或入住了房间
            //因为退房了，checkOutRoom 对象为空，所以checkOutRoom.getLocation()也为空，就是没有预定或入住房间
            CheckOutRoom checkOutRoom = new CheckOutRoom();
            model.addAttribute("myRoom",checkOutRoom);
            System.out.println("房间号"+checkOutRoom.getLocation());

            //Ajax回调
            JSONObject json1 = new JSONObject();
            json1.put("success", "我的评价");
            response.getWriter().println(json1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //显示各个类型的房间
    @RequestMapping("/RoomType")
    public void findTypeRoom(HttpServletResponse response,@RequestParam(value="roomType") String roomType) throws Exception{
        try{
            //实例化一个房间的实体，输入参数为实体类的对象
            Room room = new Room();
            room.setRoomState("0");
            room.setRoomType(roomType);
            List<Room> roomList = roomService.findTypeRoom(room);
            //设置字符集
            response.setContentType("text/html;charset=utf-8");
            //集合转换为json数组
            JSONArray jsonArray = JSONArray.fromObject(roomList);
            //把json的数组发送到页面,回调
            response.getWriter().println(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //房间详情
    @RequestMapping("/roomInformation")
    public String roomInformation(Model model,@RequestParam(value="location") String location) throws Exception{
        Room room = roomService.roomInformation(location);
        model.addAttribute("roomDetail",room);
        return "roomDetail";
    }

    //我的房间
    @RequestMapping("/myRoom")
    public String myRoom(Model model,@RequestParam(value = "userName") String userName){
             try{
                 HashMap<String,Object> hashMap = new HashMap<String,Object>();
                 hashMap.put("userName",userName);
                 hashMap.put("roomState","1");
                 //直接入住的房间
                 CheckIn checkIn = roomService.findCheckIn(hashMap);
                 System.out.println(checkIn);
                 if(null == checkIn){
                     //预定的房间
                     BookRoom bookRoom = roomService.findBookRoom(hashMap);
                     System.out.println(bookRoom);
                     if(bookRoom == null){
                         System.out.println("两种类型的房间都没有入住");
                     }else{
                         //把预定房对象转为入住房对象，在myRoom.jsp显示不会冲突
                         CheckIn checkIn2 = new CheckIn();
                         checkIn2.setLocation(bookRoom.getLocation());
                         checkIn2.setOutDay(bookRoom.getOutDay());
                         checkIn2.setCheckDay(bookRoom.getBookDay());
                         checkIn2.setDay(bookRoom.getDay());
                         model.addAttribute("myRoom",checkIn2);
                     }
                 }else{
                     System.out.println(checkIn.getLocation()+"哈啊哈");
                     model.addAttribute("myRoom",checkIn);
                 }
             }catch (Exception e){
                 e.printStackTrace();
             }
        return "myRoom";
    }

    //入住
    @RequestMapping("/checkIn")
    public void checkIn(Model model,HttpServletRequest request,HttpServletResponse response,
                        @RequestParam("location") String roomLocation, @RequestParam("day") String days,
                        @RequestParam("price") String roomPrice,@RequestParam("checkDay") String checkDays) {
        try {
            //时间字符串转换格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String location =  roomLocation;  //接收房间号
            int price = Integer.parseInt(roomPrice);  //房间价格
            //用户电话号码，用于发短信，房间号和密码
            User user = (User) request.getSession().getAttribute("user");
            String userName = user.getUserName(); //用户名
            String phone = user.getPhone();//电话号码
            //余额，用于判断是否够钱入住
            int balance =  userService.personalInfo(userName).getBalance();
            String checkDay = checkDays;//入住时间
            int day = Integer.parseInt(days);  //入住天数
            int roomCost = price * day;    //入住总费用
            //现在的时间
            Date nowDate = new Date();
            //退房时间等于现在的时间加上入住的天数
            Calendar now = Calendar.getInstance();
            now.setTime(nowDate);
            now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
            Date outDay = now.getTime();
            //退房时间，转为字符串
            String checkOutDay = simpleDateFormat.format(outDay);
            System.out.println("location:"+location+"\tprice:"+price+"\tusrename:"+userName+
                    "\tphone"+phone+"\tbalance:"+balance+"\tcheckDay:"+checkDay+
                    "\tday:"+day+"\troomcast:"+roomCost+"\tcheckOutDay:"+checkOutDay);
            //房间的密码
            List<String> passwordList = roomService.roomPassword();
            for(String str:passwordList){
                System.out.println(str);
            }
            String roomPassword = null;
            while (true) {
                //随机生成一个6位数的房间密码,转为字符串
                roomPassword = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
                //判断是否有重复的值,如果不重复，则退出循环
                if (!passwordList.contains(roomPassword)) {
                    break;
                }
            }
            System.out.println("不重复密码："+roomPassword);
            //如果余额不足，不能入住
            if (roomCost > balance) {
                //余额不足时，在个人信息页面显示余额
                //ajax跳转页面，取不到request作用域的值，@SessionAttributes(),放在session作用域中
                model.addAttribute("balance",balance);
                System.out.println("余额："+balance);
                JSONObject json = new JSONObject();
                json.put("notEnough","余额不足,请先充值");
                response.getWriter().println(json);
            }else{
                System.out.println("ajax跳转后还往下执行");
                //封装入住的对象
                CheckIn checkIn = new CheckIn();
                checkIn.setUserName(userName);
                checkIn.setLocation(location);
                checkIn.setCheckDay(checkDay);
                checkIn.setDay(day);
                checkIn.setOutDay(checkOutDay);
                checkIn.setRoomState("1");  //入住了房间状态为1，表示入住，退房了状态为0

                //查找该房间是不是已经被预定
                HashMap<String,Object> hashMap = new HashMap<String,Object>();
                hashMap.put("location",location);
                hashMap.put("roomState","1");
                //从预定表查找,条件为location,且房间状态为1
                BookRoom bookRoom = roomService.findRoomIfBooked(hashMap);
                //如果改房间没有被预定，可以直接入住
                if (null == bookRoom) {
                    System.out.println("没有预定，可以直接入住");
                    //入住
                    roomService.checkIn(checkIn);
                    //把房间号和密码写进key表
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("location",location);
                    map.put("roomPassword",roomPassword);
                    roomService.locationAndRoomPassword(map);

                    //入住了，把room表的该房间的状态改为1，使之不在前台显示
                    /*Map<String,Object> map1 = new HashMap<String,Object>();
                    map1.put("location",location);
                    map1.put("roomState","1");*/
                    roomService.modifyRoomState(hashMap);

                    //读取房间信息显示在myRoom.jsp
                    HashMap<String,Object> roomMap = new HashMap<String,Object>();
                    roomMap.put("userName",userName);
                    roomMap.put("roomState","1");
                    //直接入住的房间
                    CheckIn checkIn2 = roomService.findCheckIn(roomMap);
                    //添加到request作用域中，@SessionAttributes()添加到session作用域中
                    model.addAttribute("myRoom",checkIn2);
                    //标记，用于区分是入住还是找回密码
                    String sign = "房密";
                    //发送短信,静态方法可以直接用类名调用
                    UserController.sendMessage(sign, location, phone, roomPassword,checkDay, request, response);
                }else{
                    //如果改房间被预定了，如果条件满足，也是可以入住的
                    //预定房间入住的日期
                    String bookday = bookRoom.getBookDay();
                    Date date = null;
                    //预定房的时间，字符串转为时间，为了使用getDate()方法获得到具体的日
                    date = simpleDateFormat.parse(bookday);

                    //outDay为退房时间，如果入住时退房的时间在预定的时间之后，不能入住
                    System.out.println("退房日期:" + Integer.parseInt(String.format("%td",outDay)) + ":预定日期:" + Integer.parseInt(String.format("%td",date)));
                    if (Integer.parseInt(String.format("%td",outDay)) >= Integer.parseInt(String.format("%td",date))) {
                        //model.addAttribute("bookRoomInfo",bookRoom);
                        System.out.println("预定时间："+bookRoom.getBookDay()+"\t退房时间："+bookRoom.getOutDay());
                        //request.setAttribute("bookRoomInfo", bookRoom);
                        JSONObject json = new JSONObject();
                        json.put("alreadyBook","该房间在："+bookRoom.getBookDay()+" ,已经被预定，预计退房时间："+bookRoom.getOutDay()+"，请选择别的房间。");
                        response.getWriter().println(json);
                        //request.setAttribute("alreadyBook", "已经预定");
                        System.out.println("已经预定，且条件不满足，不能入住");
                    } else {
                        //入住
                        roomService.checkIn(checkIn);
                        //CheckIn checkIn3 = roomservice.findChecnIn(userName);
                        model.addAttribute("myRoom",checkIn);
                        //request.setAttribute("myRoom", checkIn);
                        //把房间号和密码写进key表
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("location",location);
                        map.put("roomPassword",roomPassword);
                        roomService.locationAndRoomPassword(map);

                        //入住了，把room表的该房间的状态改为1，使之不在前台显示
                        Map<String,Object> map2 = new HashMap<String,Object>();
                        map2.put("location",location);
                        map2.put("roomState","1");
                        roomService.modifyRoomState(map2);

                        String sign = "房密";
                        //发送短信
                        UserController.sendMessage(sign, location, phone, roomPassword,checkDay, request, response);
                        System.out.println("房间被预定，但条件满足，可以入住");
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //判断预定时间是否准确，时间只能的年份只能是今年，月份只能是当前月或下一个月，只能提前7天预定
    @RequestMapping("/bookTime")
    public void bookTime(Model model,HttpServletRequest request,HttpServletResponse response,
                         @RequestParam("location") String roomLocation, @RequestParam("day") String days,
                         @RequestParam("price") String roomPrice,@RequestParam("bookDay") String bookDays){
       try{

           //时间字符串转换格式
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String bookDay = bookDays;//预定时间
           Date yudingDay = simpleDateFormat.parse(bookDays); //预定的时间，转为date，为了获取年，月，日
           int year = Integer.parseInt(String.format("%tY",yudingDay));
           int month = Integer.parseInt(String.format("%tm",yudingDay));
           int day = Integer.parseInt(String.format("%td",yudingDay));
           System.out.println("年："+year+"\t月："+month+"\t日："+day);
           //判断是哪个月，对应的月有多少天
           int realMonthDays;
           switch (month){
               case 1:
               case 3:
               case 5:
               case 7:
               case 8:
               case 10:
               case 12:realMonthDays = 31;break;
               case 4:
               case 6:
               case 9:
               case 11:realMonthDays = 30;break;
               case 2:realMonthDays = 28;break;
               default:    realMonthDays = 0; break;
           }
           System.out.println("月的天数："+realMonthDays);

           Date nowDate = new Date();
           int nowYear = Integer.parseInt(String.format("%tY",nowDate));
           int nowMonth = Integer.parseInt(String.format("%tm",nowDate));
           int nowDay = Integer.parseInt(String.format("%td",nowDate));
           //提前预定的时间只能是今年，且月份只能是当前月或下一个月
           if(year == nowYear && (month == nowMonth || month == nowMonth+1)){
               System.out.println("时间正确"+year+":"+month+":"+day);
               //预定的月份是当前月
               if(month == nowMonth){
                   //day是预定的日期，nowDay是现在的日期
                   if(day-nowDay<0){
                       System.out.println("不能选择过去的时间");
                       JSONObject json = new JSONObject();
                       json.put("pastDate","时间错误，不能选择过去的日期");
                       response.getWriter().println(json);
                   }else if(day-nowDay>7){
                       System.out.println("最多只能提前7天预定");
                       JSONObject json = new JSONObject();
                       json.put("forWordSevenDays","时间错误，最多只能提前7天预定");
                       response.getWriter().println(json);
                   }else{
                       System.out.println("时间和日期都正确，可以预定1111111！！！");
                       //调用预定方法
                       bookRoom(model,request,response,roomLocation,days,roomPrice,bookDays);
                   }
               }else{ //预定的月份是当前月的下一个月份

                   //如果预定时间是下个月的时间，则天数等于 当前月的总日期减去当前日期加上预定的日期
                   //比如：假如现在是9月28号(9月有30天)，预定时间是10月2号，提前预定的天数等于
                   //  2 + (30-28) = 4
                   int totalDays = day +  (realMonthDays-nowDay);
                   if(totalDays>7){
                       System.out.println("最多只能提前7天预定");
                       JSONObject json = new JSONObject();
                       json.put("forWordSevenDays","时间错误，最多只能提前7天预定");
                       response.getWriter().println(json);
                   }else{
                       System.out.println("时间和日期都正确，可以预定2222222！！！");
                       //调用预定方法
                       bookRoom(model,request,response,roomLocation,days,roomPrice,bookDays);
                   }
               }
           }else{
               System.out.println("时间错误"+year+":"+month+":"+day);
               JSONObject json = new JSONObject();
               json.put("wrongDate","请选择正确的时间，只能选择当前月份或当前月份下一个月份。");
               response.getWriter().println(json);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    //预定房间
    @RequestMapping("/bookRoom")
    public void bookRoom(Model model,HttpServletRequest request,HttpServletResponse response,
                         String roomLocation, String days, String roomPrice,String bookDays){

           try{
               //时间字符串转换格式
               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String location =  roomLocation;  //接收房间号
               int price = Integer.parseInt(roomPrice);  //房间价格
               //用户电话号码，用于发短信，房间号和密码
               User user = (User) request.getSession().getAttribute("user");
               String userName = user.getUserName(); //用户名
               String phone = user.getPhone();//电话号码
               //余额，用于判断是否够钱入住
               int balance =  userService.personalInfo(userName).getBalance();
               String bookDay = bookDays;//预定时间
               int day = Integer.parseInt(days);  //入住天数
               int roomCost = price * day;    //预定总费用

               Date yudingDay = simpleDateFormat.parse(bookDays); //预定的时间，date
               Calendar now = Calendar.getInstance(); 	//退房时间，等于预定的时间加上入住的天数
               now.setTime(yudingDay);
               now.set(Calendar.DATE, now.get(Calendar.DATE)+day);

               String outDay = simpleDateFormat.format(now.getTime()); //退房的时间，转为字符串
               int yuDay = Integer.parseInt(String.format("%td",yudingDay)); 	//预定的日
               int tuiday = Integer.parseInt(String.format("%td",now.getTime()));	 //退房的日
               //现在的时间
               Date date = new Date();
               int nowDay = Integer.parseInt(String.format("%td",date));  //现在的日
               System.out.println("location:"+location+"\tprice:"+price+"\tusrename:"+userName+
                       "\tphone"+phone+"\tbalance:"+balance+"\tbookDay:"+bookDay+
                       "\tday:"+day+"\troomcast:"+roomCost);

               //房间的密码
               List<String> passwordList = roomService.roomPassword();
               for(String str:passwordList){
                   System.out.println(str);
               }

               String roomPassword = null;
               while (true) {
                   //随机生成一个6位数的房间密码,转为字符串
                   roomPassword = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
                   //判断是否有重复的值,如果不重复，则退出循环
                   if (!passwordList.contains(roomPassword)) {
                       break;
                   }
               }
               System.out.println("不重复密码："+roomPassword);

               //如果余额不足，不能入住
               if (roomCost > balance) {
                   //余额不足时，在个人信息页面显示余额
                   //ajax跳转页面，取不到request作用域的值，@SessionAttributes(),放在session作用域中
                   model.addAttribute("balance", balance);
                   System.out.println("余额：" + balance);
                   JSONObject json = new JSONObject();
                   json.put("notEnough", "余额不足,请先充值");
                   response.getWriter().println(json);
               }else{
                   System.out.println("ajax跳转后还往下执行");
                   //封装入住的对象
                   BookRoom bookRoom = new BookRoom();
                   bookRoom.setUserName(userName);
                   bookRoom.setLocation(location);
                   bookRoom.setBookDay(bookDay);
                   bookRoom.setDay(day);
                   bookRoom.setOutDay(outDay);
                   bookRoom.setRoomState("1");  //入住了房间状态为1，表示入住，退房了状态为0

                   //查找该房间是不是已经被预定
                   HashMap<String,Object> hashMap = new HashMap<String,Object>();
                   hashMap.put("location",location);
                   hashMap.put("roomState","1");
                   //从预定表查找,条件为location,且房间状态为1
                   BookRoom bookRoom1 = roomService.findRoomIfBooked(hashMap);
                   //如果改房间没有被预定，可以直接预定
                   if (null == bookRoom1) {
                       System.out.println("没有预定，可以直接入住");
                       //可以直接预定
                       roomService.bookRoom(bookRoom);
                       //把房间号和密码写进key表
                       Map<String,Object> map = new HashMap<String,Object>();
                       map.put("location",location);
                       map.put("roomPassword",roomPassword);
                       roomService.locationAndRoomPassword(map);

                       //读取房间信息显示在myRoom.jsp
                       HashMap<String,Object> roomMap = new HashMap<String,Object>();
                       roomMap.put("userName",userName);
                       roomMap.put("roomState","1");
                       //预定的房间
                       BookRoom bookRoom2 = roomService.findBookRoom(roomMap);
                       //转为checkIn对象，不然在myRoom.jsp页面显示会有冲突
                       CheckIn checkIn4 = new CheckIn();
                       checkIn4.setLocation(bookRoom2.getLocation());
                       checkIn4.setCheckDay(bookRoom2.getBookDay());
                       checkIn4.setDay(bookRoom2.getDay());
                       checkIn4.setOutDay(bookRoom2.getOutDay());

                       //添加到request作用域中，@SessionAttributes()添加到session作用域中
                       model.addAttribute("myRoom",checkIn4);
                       //标记，用于区分是入住还是找回密码
                       String sign = "预房密";
                       //发送短信,静态方法可以直接用类名调用
                       System.out.println("预定成功");
                       UserController.sendMessage(sign, location, phone, roomPassword,bookDay,request, response);
                   }else{

                       //已经被预定房间的日期，转为Date
                       Date alreadyBookDay = simpleDateFormat.parse(bookRoom1.getBookDay());
                       Date alreadyOutDay = simpleDateFormat.parse(bookRoom1.getOutDay());
                       //已预定房的预定日和退房日
                       int alreadyYuDay = Integer.parseInt(String.format("%td",alreadyBookDay));
                       int alreadyTuiDay = Integer.parseInt(String.format("%td",alreadyOutDay));
                       //如果预定房间退房的日期 小于 已经预定的房间预定的日期，可以预定
                       if(tuiday<alreadyYuDay){

                           System.out.println("预定房间退房的日期 小于 已经预定的房间预定的日期，可以预定");
                           //可以预定
                           roomService.bookRoom(bookRoom);
                           //把房间号和密码写进key表
                           Map<String,Object> map = new HashMap<String,Object>();
                           map.put("location",location);
                           map.put("roomPassword",roomPassword);
                           roomService.locationAndRoomPassword(map);
                           //显示在myRoom.jsp
                          // BookRoom bookRoom2 = roomservice.findBookRoom(userName);
                           CheckIn checkIn5 = new CheckIn();
                           checkIn5.setLocation(bookRoom.getLocation());
                           checkIn5.setCheckDay(bookRoom.getBookDay());
                           checkIn5.setDay(bookRoom.getDay());
                           checkIn5.setOutDay(bookRoom.getOutDay());
                           model.addAttribute("myRoom",checkIn5);
                           String sign = "预房密";
                           UserController.sendMessage(sign, location, phone, roomPassword,bookDay, request, response);

                           //如果预定房间预定的日期 大于 已经预定的房间退房的日期，可以预定
                       }else if(yuDay>alreadyTuiDay){
                           System.out.println("如果预定房间预定的日期 大于 已经预定的房间退房的日期，可以预定");
                           //可以预定
                           roomService.bookRoom(bookRoom);
                           //房间和密码
                           //把房间号和密码写进key表
                           Map<String,Object> map = new HashMap<String,Object>();
                           map.put("location",location);
                           map.put("roomPassword",roomPassword);
                           roomService.locationAndRoomPassword(map);
                           //显示在myRoom.jsp
                           //BookRoom bookRoom3 = roomservice.findBookRoom(userName);
                           CheckIn checkIn6 = new CheckIn();
                           checkIn6.setLocation(bookRoom.getLocation());
                           checkIn6.setCheckDay(bookRoom.getBookDay());
                           checkIn6.setDay(bookRoom.getDay());
                           checkIn6.setOutDay(bookRoom.getOutDay());
                           model.addAttribute("myRoom",checkIn6);
                           String sign = "预房密";
                           UserController.sendMessage(sign, location, phone, roomPassword,bookDay, request, response);
                       }else{
                           System.out.println("时间冲突，不可以预定");
                           JSONObject json = new JSONObject();
                           json.put("alreadyBook","该房间在："+bookRoom1.getBookDay()+" ,已经被预定，预计退房时间："+bookRoom1.getOutDay()+"，请选择别的房间。");
                           response.getWriter().println(json);
                       }
                   }
               }
           }catch (Exception e){
               e.printStackTrace();
           }

    }

    //续住
    @RequestMapping("/stayOver")
    public void stayOver(Model model,HttpServletRequest request,HttpServletResponse response,@RequestParam("location") String locations,@RequestParam("checkDay") String checkday,
                         @RequestParam("xuday") String stayOverDay,@RequestParam("ruday") String ruday){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int day = Integer.parseInt(ruday);//入住的天数
            int xuzhuday = Integer.parseInt(stayOverDay);//续住的天数
            String location = locations; //房间号
            int price = roomService.roomPrice(location); //房价
            User user = (User)request.getSession().getAttribute("user");
            String userName = user.getUserName();//用户名
            //余额
            int balance = userService.personalInfo(userName).getBalance();
            int totalDay = day + xuzhuday;  //续住后的天数
            //续住后的总金额，用于判断是否够钱续住
            int totalMoney = (int)totalDay * price;
            //String checkDay = request.getParameter("checkDay");
            //入住字符串转成时间，为了获得续住后的退房时间
            Date date = sdf.parse(checkday);
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.set(Calendar.DATE, now.get(Calendar.DATE)+totalDay);
            //续住后的退房时间
            String outDay = sdf.format(now.getTime());
            //用于查找入住或预定房的信息
            HashMap<String,Object> hashMap = new HashMap<String,Object>();
            hashMap.put("userName",userName);
            hashMap.put("roomState","1");
            //用于修改续住后的天数和退房时间
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("userName",userName);
            map.put("day",totalDay);
            map.put("outDay",outDay);
            map.put("roomState","1");

            System.out.println("余额"+balance+"房间号："+location+",入住天数："+day+",续住天数："+xuzhuday+",房价："+price+",入住时间："+checkday+",退房时间："+outDay);
            if(totalMoney > balance){
                System.out.println("余额不足，请先充值");
                //余额不足，跳到个人信息界面，余额要显示
                model.addAttribute("balance",balance);
                JSONObject json = new JSONObject();
                json.put("notEnoughMoney","余额不足，请先充值");
                response.getWriter().println(json);
            }else{
                //直接入住的房间
                CheckIn checkIn = roomService.findCheckIn(hashMap);
                //不知道是入住房的续住还是预定房的续住，要判断
                if(checkIn!=null){
                    System.out.println("入住房的续住");
                    //续住后，修改入住天数和退房时间
                    roomService.checkInStayOver(map);
                    //修改后再次取出来，在myRoom.jsp页面显示
                    CheckIn checkIn2 = roomService.findCheckIn(hashMap);
                    //ajax回调
                    JSONObject json = new JSONObject();
                    json.put("checkInStayOver",checkIn2);
                    response.getWriter().println(json);
                }else{
                    System.out.println("预定房的续住");
                    //续住后，修改入住天数和退房时间
                    roomService.bookStayOver(map);
                    //续住后再次读取出来，在myRoom.jsp页面显示
                    BookRoom bookRoom = roomService.findBookRoom(hashMap);
                    //ajax回调
                    JSONObject json = new JSONObject();
                    json.put("checkInStayOver",bookRoom);
                    response.getWriter().println(json);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //分页的住房记录
    @RequestMapping({"/roomRecord"})
    public String RoomRecord(HttpServletRequest request, Model model, @RequestParam("num") int num) {
        try {
            User user = (User)request.getSession().getAttribute("user");
            String userName = user.getUserName();
            System.out.println("分页记录进来了");
            Map<String, Object> map = new HashMap();
            map.put("userName", userName);
            int totalRecord = this.roomService.findTotalRecode(map);
            System.out.println("总页123数为：" + totalRecord);
            PageUtils pageUtils = new PageUtils(num, totalRecord, 4);
            map.put("outDay", "outDay");
            map.put("startIndex", pageUtils.getStartIndex());
            map.put("endIndex", pageUtils.getEndIndex());
            List<RoomRecord> list = this.roomService.RoomRecordWithPage(map);

            for(int i = 0; i < list.size(); ++i) {
                System.out.println("房间号：" + ((RoomRecord)list.get(i)).getLocation() + "退房时间：" + ((RoomRecord)list.get(i)).getOutDay());
            }

            System.out.println("大小：" + list.size());
            System.out.println("123456789");
            pageUtils.setList(list);
            pageUtils.setUrl("/find/roomRecord");
            model.addAttribute("page", pageUtils);
        } catch (Exception var12) {
            var12.printStackTrace();
        }

        return "roomRecord";
    }
    //退房
    @RequestMapping("/checkOut")
    public String checkOut(Model model,HttpServletRequest request,@RequestParam("location") String location,@RequestParam("rudays") String ruday,
                           @RequestParam("outDay") String outDay, @RequestParam("checkDay") String checkDay){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(); //现在的时间
            int nowMonth = Integer.parseInt(String.format("%tm",date));//当前月份
            int outMonth = Integer.parseInt(String.format("%tm",sdf.parse(outDay)));//退房的月份
            //现在的时间，就是退房的时间
            String today = sdf.format(date);
            int nowday = Integer.parseInt(String.format("%td",date));//今天的日期，如 14 号
            //String checkDay = checkDays; //入住的时间
            int checkday = Integer.parseInt(String.format("%td",sdf.parse(checkDay)));//入住的日期  如13号
            //String location = locations;  //房间号
            int rudays = Integer.parseInt(ruday);  //入住天数
            //String outDay = outDays;  //预计退房时间
            int tuiday = Integer.parseInt(String.format("%td",sdf.parse(outDay)));//退房的日期
            //int tuiDay = sdf.parse(outDay).getDate();
            int chaDay = tuiday - nowday;  //两个日期的差，如果为0说明是当天，没有提前退房
            System.out.println("现在："+nowday+"\t入住："+checkday+"\t天数："+rudays+"\t退房："+tuiday);
            int price = roomService.roomPrice(location);   //房价
            User user = (User)request.getSession().getAttribute("user");
            //int balance = moneyService.findMoney(user.getUserName());	//余额
            Money money = userService.personalInfo(user.getUserName());
            int balance = money.getBalance();
            System.out.println("房价："+price+"\t退房前余额："+balance);
            //两个日期的差，如果为0则说明没有提前退房；如果为1且入住和退房的天数差1，比如14号住，只住一天，但是在14号退房
            //如果入住的天数是一天，则不存在提前退房，比如14号住，住1天，无论14号还是15号退，都是正常退房
            int normalMoney=0,extraMoney= 0;

            if(rudays==1){
                normalMoney = rudays*price;  //下面写进roomRecord表时要用到
                balance = balance - rudays*price;
            }else if(nowday<checkday){ //比如14号预定了15号，住2天，但忽然在14号就想退房，还是得支付未住天数的20%的费用
                extraMoney = (int)(rudays*price*0.2);  //下面写进roomRecord表时要用到
                balance = balance - (int)(rudays*price*0.2);
            }else{
                //如果入住天数不是1天，则要分以下情况来判断是否是提前退房
                //如果入住的月份和退房的月份是同一个月份
                if(nowMonth==outMonth){
                     System.out.println("入住月份和退房月份相同");
                       //分为:chaDay==0为当天退房，否则是提前退房
                       if(chaDay==0){
                           normalMoney = rudays*price;  //下面写进roomRecord表时要用到
                           balance = balance - rudays*price;
                       }else if(nowday==checkday){  //14号住，住2天，但14号就退，也要算入住了1天
                            System.out.println("当天退房，也算入住一天");
                            normalMoney = 1*price;
                            extraMoney = (int)((tuiday-nowday-1)*price*0.2);
                            balance = balance - normalMoney - extraMoney;
                       }else{
                           //tuiday为预计退房的日，nowday为实际退房的日，checkday为入住房的日
                            normalMoney = (nowday-checkday)*price;  //已入住的费用
                            extraMoney = (int)((tuiday-nowday)*price*0.2); //未入住的天数需支付20%的费用
                            balance = balance - normalMoney - extraMoney;  //提前退房后的余额
                       }
                }else{
                    System.out.println("入住月份和退房月份不相同");
                    //不知道是哪个月，算当月有多少天
                    int monthday = 0;
                    switch (nowMonth){
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12:  monthday = 31;break;
                        case 4:
                        case 6:
                        case 9:
                        case 11:  monthday = 30;break;
                        case 2:   monthday = 28;break;   //2月28天，不分闰年了
                        default:  monthday = 0; break;
                    }
                    System.out.println("月的天数："+monthday);
                    //如果入住的月份和退房的月份不是同一个月份
                    if(nowday>checkday){
                        //假如是9月28号住，9月有30天，住4天，正常是10月2号退房
                        //加入29号提前退房，则29>28,已入住的天数=29-28，未入住的天数=30-29+2
                        normalMoney = (nowday-checkday)*price;   //已入住的费用
                        extraMoney = (int)((monthday-nowday+tuiday)*price*0.2);  //未入住的天数需支付20%的费用
                        balance = balance - normalMoney - extraMoney;  //提前退房后的余额
                    }else if(nowday==checkday && tuiday>checkday){  //同上
                        System.out.println("不同月份，同一天退，也要算入住一天");
                        normalMoney = 1*price;   //如 25号住，住3天，但25号退房(正常28号退)，算住1天，未住的=28-25-1
                        extraMoney = (int)((tuiday-nowday-1)*price*0.2);
                        balance = balance - normalMoney - extraMoney;
                    }else if(nowday==checkday && tuiday<checkday){
                        System.out.println("不同月份，同一天退，也要算入住一天");
                        normalMoney = 1*price;   //如 9月28住，4天，但28号退房(正常10月2号退房)，算住了1天，未住的=30-28-1+2
                        extraMoney = (int)((monthday-nowday-1+tuiday)*price*0.2);
                        balance = balance - normalMoney -extraMoney;
                    }else{
                        //假如是10月1号退房，则1<28,已入住的天数=30-28+1，未入住的天数=2-1
                         normalMoney = (monthday-checkday+nowday)*price;  //已入住的费用
                         extraMoney = (int)((tuiday - nowday)*price*0.2);
                         balance = balance - normalMoney - extraMoney;
                    }
                }
            }
            System.out.println("退房后余额："+balance);
            //退房后的余额写进money表
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("userName",user.getUserName());
            map.put("sumMoney",balance);
            userService.userRecharge(map);
           // RoomRecord roomRecord = new RoomRecord();
            //退房后，要把记录写进roomRecord表
            roomRecord.setUserName(((User) request.getSession().getAttribute("user")).getUserName());
            roomRecord.setLocation(location);
            //返回参数是一个HashMap,使用别名来获得其值，在sql语句已经定义了别名
            HashMap<String,Object> map3 = roomService.priceAndroomType(location);
            roomRecord.setRoomType(String.valueOf(map3.get("roomType")));

            //Object不能直接转化成Integer,你首先需要把它转化成String,再使用Integer.parseInt()转为int;
            roomRecord.setPrice(Integer.parseInt(map3.get("roomPrice").toString()));
            roomRecord.setCheckDay(checkDay);
            roomRecord.setDay(rudays);
            roomRecord.setOutDay(outDay);
            if(rudays!=1 && chaDay!=0){
                roomRecord.setSign("是");  //提前退房
            }else{
                roomRecord.setSign("否");  //入住一天，永远不会提前退房，chaDay为0，则说明是退房当天退房
            }
            roomRecord.setTotalMoney(normalMoney+extraMoney);
            //把退房记录写进roomRecord表
            roomService.checkOutRecord(roomRecord);

            //退房后checkIn表或book表的退房的时间，房间的状态都要改变,不知道是预定房间还是入住的房间退房，还要判断
            HashMap<String,Object> hashMap = new HashMap<String,Object>();
            hashMap.put("userName",user.getUserName());
            hashMap.put("roomState","1");
            //入住的房间
            CheckIn checkIn = roomService.findCheckIn(hashMap);

            HashMap<String,Object> hashMap2 = new HashMap<String,Object>();
            hashMap2.put("userName",user.getUserName());
            hashMap2.put("realDate",today);  //退房时间就是现在的时间
            hashMap2.put("beforeRoomstate","1"); //用来判断
            hashMap2.put("afterRoomstate","0");   //房间状态要改为0
            //如果不是直接入住的房间，就是预定的房间
            if(null == checkIn){
               roomService.bookRoomDateAndRoomState(hashMap2);
            }else{
                roomService.checkRoomDateAndRoomState(hashMap2);
                //如果是直接入住，要把room表的该房间的状态从1改为0
                Map<String,Object> map2 = new HashMap<String,Object>();
                map2.put("location",location);
                map2.put("roomState","0");
                roomService.afterModifyRoomState(map2);
            }
             //房间的入住次数
            int roomTimes = roomService.findRoomTimes(location);
            System.out.println("房间的入住次数："+roomTimes);
            //无论是入住还是预定，退房后入住次数都要加1
            int afterToomTimes = roomTimes + 1;
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("location",location);
            map1.put("afterToomTimes",afterToomTimes);
            //无论是入住还是预定，退房后入住次数都要加1
            roomService.addRoomTimes(map1);

            //roomservice.checkOutTime(today,location);
            //余额，在personalInfo.jsp显示
            model.addAttribute("balance",balance);
            //房间类型
            String roomType = roomService.findRoomtype(location);
            model.addAttribute("type",roomType);
            System.out.println("房间类型："+roomType);
            //因为登陆时把myRoom存放到了session中，退房了，要清除，不然在我的房间还会显示
            //用了很多方法都不行，还是直接用一个空的对象(属性要一致)，覆盖掉session中的myRoom对象
            //然后在roomDetail.jsp页面用 ${myRoom.location}!=" " 来判断是否预定或入住了房间
            //因为退房了，checkOutRoom 对象为空，所以checkOutRoom.getLocation()也为空，就是没有预定或入住房间
            /*CheckOutRoom checkOutRoom = new CheckOutRoom();
            model.addAttribute("myRoom",checkOutRoom);
            System.out.println("房间号"+checkOutRoom.getLocation());*/
        }catch (Exception e){
            e.printStackTrace();
        }

        //return "personalInfo";
       return "star";
    }
}
