package com.dao;

import com.model.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface RoomDao {
    //显示推荐的房间
    List<Room> findRoom(Map<String,Object> startAndEndIndex) throws Exception;
    //房间评价
    List<star> findStar() throws Exception;
    //我的评价
    List<star> findPersonalStar(String userName) throws Exception;
    //住房后的评价
    void myStars(star appraise) throws Exception;
    //房间类型
    String findRoomtype(String location) throws Exception;
    //显示各个类型的房间
    List<Room> findTypeRoom(Room room) throws Exception;
    //房间详情
    Room roomInformation(String location) throws Exception;
    //查找入住的房间
    CheckIn findCheckIn(HashMap hashMap) throws Exception;
    //查找预定的房间
    BookRoom findBookRoom(HashMap hashMap) throws Exception;
    //房间的密码，用于判断，不能重复
    List<String> roomPassword() throws Exception;
    //入住
    void checkIn(CheckIn checkIn) throws Exception;
    //把房间号和密码写进key表
    void locationAndRoomPassword(Map<String,Object> map) throws Exception;
    //查找该房间有没有被预定
    BookRoom findRoomIfBooked(Map<String,Object> map) throws Exception;
    //预定
    void bookRoom(BookRoom bookRoom) throws Exception;
    //入住了，把room表的该房间的状态改为1，使之不在前台显示
    void modifyRoomState(Map<String,Object> map) throws Exception;
    //房价
    int roomPrice(String location) throws Exception;
    //入住房退房后，修改退房时间和房态
    void checkRoomDateAndRoomState(Map<String,Object> map) throws Exception;
    //预定房退房后，修改退房时间和房态
    void bookRoomDateAndRoomState(Map<String,Object> map) throws Exception;
    //房间的入住次数
    int findRoomTimes(String location) throws Exception;
    //入住或退房，房间的入住次数都有加1
    void addRoomTimes(Map<String,Object> map) throws Exception;
    //入住房，退房后，room表的状态要从1改为0，使之在前台显示
    void afterModifyRoomState(Map<String,Object> map) throws Exception;
    //入住房续住后，入住天数和退房时间要改
    void checkInStayOver(Map<String,Object> map) throws Exception;
    //预定房续住后，入住天数和退房时间要改
    void bookStayOver(Map<String,Object> map) throws Exception;
    //住房记录
    List<RoomRecord> findRoomRecord(Map<String,Object> map) throws Exception;
    //住房记录总的行数
    int findTotalRecode(Map<String,Object> map) throws Exception;
    //用户分页的住房记录
    List<RoomRecord> RoomRecordWithPage(Map<String,Object> map) throws Exception;
    //房间类型和房价
    HashMap<String,Object> priceAndroomType(String location) throws Exception;
    //把退房信息写进roomState表
    void checkOutRecord(RoomRecord roomRecord) throws Exception;
}
