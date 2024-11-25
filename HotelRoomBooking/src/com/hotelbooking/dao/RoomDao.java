package com.hotelbooking.dao;

import com.hotelbooking.model.Rooms;

import java.util.List;

public interface RoomDao {
    Rooms getRoomsByType(String roomTypeName);

    Rooms getRoomsById(int roomId);

    void updateAvailableRooms(int roomsTypeId, int availableRooms);

    List<Rooms> roomAvailable();

}
