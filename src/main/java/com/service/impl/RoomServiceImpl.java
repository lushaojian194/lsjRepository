package com.service.impl;

import com.dao.RoomDao;
import com.model.*;
import com.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;
    //查找全部推荐的房间
    @Override
    public List<Room> findRoom(Map<String,Object> startAndEndIndex) throws Exception {
        System.out.println("service方法");
        return roomDao.findRoom(startAndEndIndex);
    }
    //房间评价
    @Override
    public List<star> findStar() throws Exception {
        System.out.println("查找star方法");
        return roomDao.findStar();
    }
    //我的评价
    @Override
    public List<star> findPersonalStar(String userName) throws Exception {
        return roomDao.findPersonalStar(userName);
    }

    //住房后评价
    @Override
    public void myStars(star appraise) throws Exception {
        System.out.println("评价房间方法");
        roomDao.myStars(appraise);
    }

    //房间类型
    @Override
    public String findRoomtype(String location) throws Exception {
        return roomDao.findRoomtype(location);
    }

    //显示各个类型的房间
    @Override
    public List<Room> findTypeRoom(Room room) throws Exception {
        System.out.println("service各个类型的房间");
        return roomDao.findTypeRoom(room);
    }
    //房间详情
    @Override
    public Room roomInformation(String location) throws Exception {
        return roomDao.roomInformation(location);
    }
    //入住的房间
    @Override
    public CheckIn findCheckIn(HashMap hashMap) throws Exception {
        return roomDao.findCheckIn(hashMap);
    }
    //预定的房间
    @Override
    public BookRoom findBookRoom(HashMap hashMap) throws Exception {
        return roomDao.findBookRoom(hashMap);
    }

    //房间的密码，判断不能重复
    @Override
    public List<String> roomPassword() throws Exception {
        return roomDao.roomPassword();
    }

    //入住
    @Override
    public void checkIn(CheckIn checkIn) throws Exception {
        roomDao.checkIn(checkIn);
    }

    //把房间号和密码写进key表
    @Override
    public void locationAndRoomPassword(Map<String, Object> map) throws Exception {
         roomDao.locationAndRoomPassword(map);
    }

    //查找该房间是否被预定
    @Override
    public BookRoom findRoomIfBooked(Map<String, Object> map) throws Exception {
        return roomDao.findRoomIfBooked(map);
    }

    //预定
    @Override
    public void bookRoom(BookRoom bookRoom) throws Exception {
        roomDao.bookRoom(bookRoom);
    }

    //入住了，把room表的该房间的状态改为1，使之不在前台显示
    @Override
    public void modifyRoomState(Map<String, Object> map) throws Exception {
        roomDao.modifyRoomState(map);
    }

    //查找房价
    @Override
    public int roomPrice(String location) throws Exception {
        return roomDao.roomPrice(location);
    }

    //入住房退房后，修改退房时间和房态
    @Override
    public void checkRoomDateAndRoomState(Map<String, Object> map) throws Exception {
        roomDao.checkRoomDateAndRoomState(map);
    }

    //预定房退房后，修改退房时间和房态
    @Override
    public void bookRoomDateAndRoomState(Map<String, Object> map) throws Exception {
        roomDao.bookRoomDateAndRoomState(map);
    }

    //房间的入住次数
    @Override
    public int findRoomTimes(String location) throws Exception {
        return roomDao.findRoomTimes(location);
    }

    //入住或预定，退房后房价的状态都有加1
    @Override
    public void addRoomTimes(Map<String, Object> map) throws Exception {
        roomDao.addRoomTimes(map);
    }

    //入住房退房后，要把room表的状态从1改为0，使之在前台显示
    @Override
    public void afterModifyRoomState(Map<String, Object> map) throws Exception {
        roomDao.afterModifyRoomState(map);
    }

    //入住房续住后，入住天数和退房时间要改
    @Override
    public void checkInStayOver(Map<String, Object> map) throws Exception {
        roomDao.checkInStayOver(map);
    }

    //预定房续住后，入住天数和退房时间要改
    @Override
    public void bookStayOver(Map<String, Object> map) throws Exception {
        roomDao.bookStayOver(map);
    }

    //住房记录
    @Override
    public List<RoomRecord> findRoomRecord(Map<String,Object> map) throws Exception {
        return roomDao.findRoomRecord(map);
    }

    //住房记录总的行数
    @Override
    public int findTotalRecode(Map<String,Object> map) throws Exception {
        return roomDao.findTotalRecode(map);
    }

    //用户分页的住房记录
    @Override
    public List<RoomRecord> RoomRecordWithPage(Map<String, Object> map) throws Exception {
        return roomDao.RoomRecordWithPage(map);
    }

    //房间类型和房价
    @Override
    public HashMap<String, Object> priceAndroomType(String location) throws Exception {
        return roomDao.priceAndroomType(location);
    }

    //退房了，把退房信息写进roomRecord表
    @Override
    public void checkOutRecord(RoomRecord roomRecord) throws Exception {
        roomDao.checkOutRecord(roomRecord);
    }


}
